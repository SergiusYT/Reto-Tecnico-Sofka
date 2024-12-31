package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.SalitreMagicoFacade;
import view.View;

public class Controller implements ActionListener {

    private SalitreMagicoFacade modelo;
    private View view;

    public Controller(){
        modelo = new SalitreMagicoFacade();
        view = new View();
        agregarBotones();
        agregarListeners(); // Agregar los listeners
        cargarClientesEnTabla();
        ejecutarApp();
    }     


    public void ejecutarApp(){
        view.setClientesView();
        view.setVisible(true);
    }


      //--------------------------- metodo donde manejaremos los alias para tomar y luego asignarle eventos a un objeto ------------------
  
    public void agregarBotones() {      
        
        //----------------------------------- ClientesView --------------------------------------------
        view.getClientesView().getBtnAgregar().addActionListener(this);
        view.getClientesView().getBtnAgregar().setActionCommand("AgregarClientesView");
        view.getClientesView().getBtnEditar().addActionListener(this);
        view.getClientesView().getBtnEditar().setActionCommand("EditarClientesView");
        view.getClientesView().getBtnEliminar().addActionListener(this);
        view.getClientesView().getBtnEliminar().setActionCommand("EliminarClientesView");

    }


    public  void agregarListeners() {

            // Listener para la tabla
        view.getClientesView().getTablaClientes().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                manejarSeleccionFila();
            }
        });

            // Listener para manejar clics en la tabla
    view.getClientesView().getTablaClientes().addMouseListener(new java.awt.event.MouseAdapter() {
        private int ultimaFilaSeleccionada = -1;

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            int filaSeleccionada = view.getClientesView().getTablaClientes().getSelectedRow();
            
            // Verifica si el clic fue en la misma fila ya seleccionada
            if (filaSeleccionada == ultimaFilaSeleccionada) {
                view.getClientesView().getTablaClientes().clearSelection(); // Deseleccionar
                view.getClientesView().getTxtNombre().setText("");
                view.getClientesView().getTxtNIdentificacion().setText("");
                view.getClientesView().getTxtTelefono().setText("");
                view.getClientesView().getTxtCorreo().setText("");
                view.getClientesView().getTxtEstatura().setText("");
                view.getClientesView().getTxtEdad().setText("");
                view.getClientesView().getTxtNombreFamiliar().setText("");
                view.getClientesView().getTxtTelefonoFamiliar().setText("");                   ultimaFilaSeleccionada = -1; // Resetea la última fila seleccionada
            } else {
                ultimaFilaSeleccionada = filaSeleccionada; // Actualiza la última fila seleccionada
            }
        }
    });
        // Listener para el campo de edad
        view.getClientesView().getTxtEdad().getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                verificarEdad();
            }
    
            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                verificarEdad();
            }
    
            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                verificarEdad();
            }
        });
    }
    
    public void verificarEdad() {
        try {
            // Obtener la edad ingresada
            int edad = Integer.parseInt(view.getClientesView().getTxtEdad().getText().trim());
    
            // Desactivar o activar los campos según la edad
            boolean esMenorDeEdad = edad < 18;
            view.getClientesView().getTxtNombreFamiliar().setEnabled(esMenorDeEdad);
            view.getClientesView().getTxtTelefonoFamiliar().setEnabled(esMenorDeEdad);
    
            // Opcional: limpiar los campos si se desactivan
            if (esMenorDeEdad) {
                view.getClientesView().getLblAdvertencia().setText("El cliente es menor de edad. Ingrese la información del familiar.");
            }else{
                view.getClientesView().getLblAdvertencia().setText("");
                view.getClientesView().getTxtNombreFamiliar().setText("");
                view.getClientesView().getTxtTelefonoFamiliar().setText("");
            }
        } catch (NumberFormatException ex) {
            view.getClientesView().getLblAdvertencia().setText("");
            // Si el campo de edad está vacío o tiene texto inválido, asegurarse de desactivar los campos
            view.getClientesView().getTxtNombreFamiliar().setEnabled(false);
            view.getClientesView().getTxtTelefonoFamiliar().setEnabled(false);
        }
    }
    
    

        // Método para cargar datos en la tabla
    private void cargarClientesEnTabla() {
        try {
            List<?> listaClientes = modelo.getClientes().listarClientes(); // Obtener lista desde el modelo
            DefaultTableModel modeloTabla = view.getClientesView().getModeloTabla();
            modeloTabla.setRowCount(0); // Limpiar tabla

            for (Object obj : listaClientes) {
                Object[] datos = (Object[]) obj; // Asegúrate de que cada cliente sea devuelto como un array de objetos
                modeloTabla.addRow(datos); // Añadir fila a la tabla
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manejarSeleccionFila() {
        int filaSeleccionada = view.getClientesView().getTablaClientes().getSelectedRow();
    
        if (filaSeleccionada >= 0) { // Hay una fila seleccionada
            // Obtener datos de la fila seleccionada
            DefaultTableModel modeloTabla = view.getClientesView().getModeloTabla();
            Object nombre = modeloTabla.getValueAt(filaSeleccionada, 1);
            Object numeroIdentificacion = modeloTabla.getValueAt(filaSeleccionada, 2);
            Object telefono = modeloTabla.getValueAt(filaSeleccionada, 3);
            Object correo = modeloTabla.getValueAt(filaSeleccionada, 4);
            Object estatura = modeloTabla.getValueAt(filaSeleccionada, 5);
            Object edad = modeloTabla.getValueAt(filaSeleccionada, 6);
            Object nombreFamiliar = modeloTabla.getValueAt(filaSeleccionada, 7);
            Object telefonoFamiliar = modeloTabla.getValueAt(filaSeleccionada, 8);
    
            // Rellenar los campos del formulario
            view.getClientesView().getTxtNombre().setText(nombre.toString());
            view.getClientesView().getTxtNIdentificacion().setText(numeroIdentificacion.toString());
            view.getClientesView().getTxtTelefono().setText(telefono.toString());
            view.getClientesView().getTxtCorreo().setText(correo.toString());
            view.getClientesView().getTxtEstatura().setText(estatura.toString());
            view.getClientesView().getTxtEdad().setText(edad.toString());
            view.getClientesView().getTxtNombreFamiliar().setText(nombreFamiliar != null ? nombreFamiliar.toString() : "");
            view.getClientesView().getTxtTelefonoFamiliar().setText(telefonoFamiliar != null ? telefonoFamiliar.toString() : "");
        } else { // Deselección
            view.getClientesView().getTxtNombre().setText("");
            view.getClientesView().getTxtNIdentificacion().setText("");
            view.getClientesView().getTxtTelefono().setText("");
            view.getClientesView().getTxtCorreo().setText("");
            view.getClientesView().getTxtEstatura().setText("");
            view.getClientesView().getTxtEdad().setText("");
            view.getClientesView().getTxtNombreFamiliar().setText("");
            view.getClientesView().getTxtTelefonoFamiliar().setText("");        }
    }
    


    //--------------------------- metodo que se ejecuta cuando se produce un evento ------------------
    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){
          case "AgregarClientesView":
            try {
                // Obtener datos del formulario
                String nombre = view.getClientesView().getTxtNombre().getText().trim();
                String numeroIdentificacion = view.getClientesView().getTxtNIdentificacion().getText().trim();
                String telefono = view.getClientesView().getTxtTelefono().getText().trim();
                String correo = view.getClientesView().getTxtCorreo().getText().trim();
                double estatura = Double.parseDouble(view.getClientesView().getTxtEstatura().getText().trim());
                int edad = Integer.parseInt(view.getClientesView().getTxtEdad().getText().trim());
                String nombreFamiliar = view.getClientesView().getTxtNombreFamiliar().getText().trim();
                String telefonoFamiliar = view.getClientesView().getTxtTelefonoFamiliar().getText().trim();
                int visitas = 1; // Se pone 0 visitas porque es usuario nueva
                
                // Llamar al modelo para agregar el cliente
                modelo.getClientes().agregarCliente(nombre, numeroIdentificacion, telefono, correo, estatura, edad, nombreFamiliar, telefonoFamiliar, visitas);
                
                // Actualizar la tabla
                cargarClientesEnTabla();
                
                // Limpiar los campos del formulario
                view.getClientesView().getTxtNombre().setText("");
                view.getClientesView().getTxtNIdentificacion().setText("");
                view.getClientesView().getTxtTelefono().setText("");
                view.getClientesView().getTxtCorreo().setText("");
                view.getClientesView().getTxtEstatura().setText("");
                view.getClientesView().getTxtEdad().setText("");
                view.getClientesView().getTxtNombreFamiliar().setText("");
                view.getClientesView().getTxtTelefonoFamiliar().setText("");   

                // Confirmación
                JOptionPane.showMessageDialog(view, "Cliente agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Error al agregar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "Por favor, ingresa valores válidos en los campos numéricos.", "Error de validación", JOptionPane.WARNING_MESSAGE);
            }
          break;
          case "EditarClientesView":
          try {
            // Verificar que haya una fila seleccionada
            int filaSeleccionada = view.getClientesView().getTablaClientes().getSelectedRow();
            if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(view, "Por favor, seleccione un cliente para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            }
    
            // Obtener el ID del cliente
            int id = Integer.parseInt(view.getClientesView().getModeloTabla().getValueAt(filaSeleccionada, 0).toString());
    
            // Obtener los nuevos datos del formulario
            String nombre = view.getClientesView().getTxtNombre().getText().trim();
            String numeroIdentificacion = view.getClientesView().getTxtNIdentificacion().getText().trim();
            String telefono = view.getClientesView().getTxtTelefono().getText().trim();
            String correo = view.getClientesView().getTxtCorreo().getText().trim();
            double estatura = Double.parseDouble(view.getClientesView().getTxtEstatura().getText().trim());
            int edad = Integer.parseInt(view.getClientesView().getTxtEdad().getText().trim());
            String nombreFamiliar = view.getClientesView().getTxtNombreFamiliar().getText().trim();
            String telefonoFamiliar = view.getClientesView().getTxtTelefonoFamiliar().getText().trim();

            int visitas = Integer.parseInt(view.getClientesView().getModeloTabla().getValueAt(filaSeleccionada, 9).toString());
    
            // Actualizar cliente en la base de datos
            modelo.getClientes().actualizarCliente(id, nombre, numeroIdentificacion, telefono, correo, estatura, edad, nombreFamiliar, telefonoFamiliar, visitas);
    
            // Actualizar la tabla
            cargarClientesEnTabla();
    
            // Limpiar el formulario
            view.getClientesView().getTxtNombre().setText("");
            view.getClientesView().getTxtNIdentificacion().setText("");
            view.getClientesView().getTxtTelefono().setText("");
            view.getClientesView().getTxtCorreo().setText("");
            view.getClientesView().getTxtEstatura().setText("");
            view.getClientesView().getTxtEdad().setText("");
            view.getClientesView().getTxtNombreFamiliar().setText("");
            view.getClientesView().getTxtTelefonoFamiliar().setText("");    
            // Confirmación
            JOptionPane.showMessageDialog(view, "Cliente actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al actualizar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Por favor, ingresa valores válidos en los campos numéricos.", "Error de validación", JOptionPane.WARNING_MESSAGE);
        }
          
          break;
          case "EliminarClientesView":

          try {
            // Verificar que haya una fila seleccionada
            int filaSeleccionada = view.getClientesView().getTablaClientes().getSelectedRow();
            if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(view, "Por favor, seleccione un cliente para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            }
    
            // Confirmar eliminación
            int confirmacion = JOptionPane.showConfirmDialog(
                view,
                "¿Está seguro de que desea eliminar este cliente?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
            );
    
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Obtener el ID del cliente
                int id = Integer.parseInt(view.getClientesView().getModeloTabla().getValueAt(filaSeleccionada, 0).toString());
    
                // Eliminar cliente del modelo
                modelo.getClientes().eliminarCliente(id);
    
                // Actualizar la tabla
                cargarClientesEnTabla();
    
                // Limpiar el formulario
                view.getClientesView().getTxtNombre().setText("");
                view.getClientesView().getTxtNIdentificacion().setText("");
                view.getClientesView().getTxtTelefono().setText("");
                view.getClientesView().getTxtCorreo().setText("");
                view.getClientesView().getTxtEstatura().setText("");
                view.getClientesView().getTxtEdad().setText("");
                view.getClientesView().getTxtNombreFamiliar().setText("");
                view.getClientesView().getTxtTelefonoFamiliar().setText("");
    
                // Mensaje de éxito
                JOptionPane.showMessageDialog(view, "Cliente eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
          } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al eliminar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
          }
          break;

          default:

          break;
        }
    }

}
