package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
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
        agregarListenersClientes(); // Agregar los listeners
        ejecutarApp();
    }     


    private void ejecutarApp(){
        view.setPrincipalView();
        view.setVisible(true);
    }


      //--------------------------- metodo donde manejaremos los alias para tomar y luego asignarle eventos a un objeto ------------------
  
    private  void agregarBotones() {      
        
        //----------------------------------- PrincipalView --------------------------------------------
        view.getPrincipalView().getBotonClientes().addActionListener(this);
        view.getPrincipalView().getBotonClientes().setActionCommand("ClientesView");
        view.getPrincipalView().getBotonEmpleados().addActionListener(this);
        view.getPrincipalView().getBotonEmpleados().setActionCommand("EmpleadosView");
        view.getPrincipalView().getBotonAtracciones().addActionListener(this);
        view.getPrincipalView().getBotonAtracciones().setActionCommand("AtraccionesView");
        view.getPrincipalView().getBotonTickets().addActionListener(this);
        view.getPrincipalView().getBotonTickets().setActionCommand("TicketsView");
        view.getPrincipalView().getBotonSalir().addActionListener(this);        
        view.getPrincipalView().getBotonSalir().setActionCommand("Salir");
        // Asegúrate de manejar el evento de home en PrincipalView
        view.getPrincipalView().getBotonHome().addActionListener(this);
        view.getPrincipalView().getBotonHome().setActionCommand("PrincipalView");

        //----------------------------------- ClientesView --------------------------------------------
        view.getClientesView().getBtnAgregar().addActionListener(this);
        view.getClientesView().getBtnAgregar().setActionCommand("AgregarClientesView");
        view.getClientesView().getBtnEditar().addActionListener(this);
        view.getClientesView().getBtnEditar().setActionCommand("EditarClientesView");
        view.getClientesView().getBtnEliminar().addActionListener(this);
        view.getClientesView().getBtnEliminar().setActionCommand("EliminarClientesView");
        // Manejo del botón de Home en ClientesView
        view.getClientesView().getBotonHome().addActionListener(this);
        view.getClientesView().getBotonHome().setActionCommand("PrincipalView");


        //----------------------------------- EmpleadosView --------------------------------------------

        view.getEmpleadosView().getBotonHome().addActionListener(this);
        view.getEmpleadosView().getBotonHome().setActionCommand("PrincipalView");
        view.getEmpleadosView().getBtnAgregar().addActionListener(this);
        view.getEmpleadosView().getBtnAgregar().setActionCommand("AgreagarEmpleadosView");
        view.getEmpleadosView().getBtnEditar().addActionListener(this);
        view.getEmpleadosView().getBtnEditar().setActionCommand("EditarEmpleadosView");
        view.getEmpleadosView().getBtnEliminar().addActionListener(this);
        view.getEmpleadosView().getBtnEliminar().setActionCommand("EliminarEmpleadosView");

        
    }


    // ---------------------------------METODOS PARA ClientesView ------------------------------------
    private void manejarSeleccionFilaClientes() {
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




    private   void agregarListenersClientes() {

            // Listener para la tabla
        view.getClientesView().getTablaClientes().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                manejarSeleccionFilaClientes();
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
    
    private  void verificarEdad() {
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




   //----------------------------------------Metodos para  EmpleadosView--------------------------------

   private void manejarSeleccionFilaEmpleados() {
    int filaSeleccionada = view.getEmpleadosView().getTablaEmpleados().getSelectedRow();

    if (filaSeleccionada >= 0) { // Hay una fila seleccionada
        DefaultTableModel modeloTabla = view.getEmpleadosView().getModeloTabla();
        String nombre = modeloTabla.getValueAt(filaSeleccionada, 1).toString();
        String cedula = modeloTabla.getValueAt(filaSeleccionada, 2).toString();
        String telefono = modeloTabla.getValueAt(filaSeleccionada, 3).toString();
        String correo = modeloTabla.getValueAt(filaSeleccionada, 4).toString();
        int rolIndex = Integer.parseInt(modeloTabla.getValueAt(filaSeleccionada, 5).toString()) -1; 
        String horarioLaboral = modeloTabla.getValueAt(filaSeleccionada, 6).toString();

        // Rellenar los campos del formulario
        view.getEmpleadosView().getTxtNombre().setText(nombre);
        view.getEmpleadosView().getTxtCedula().setText(cedula);
        view.getEmpleadosView().getTxtTelefono().setText(telefono);
        view.getEmpleadosView().getTxtCorreo().setText(correo);
        // Seleccionar el rol en el ComboBox usando el índice
        JComboBox<String> cbRol = view.getEmpleadosView().getCbRol();
        if (rolIndex >= 0 && rolIndex < cbRol.getItemCount()) {
            cbRol.setSelectedIndex(rolIndex); // Selecciona el índice correspondiente
        } else {
            cbRol.setSelectedIndex(0); // Fallback si el índice es inválido
        }
        view.getEmpleadosView().getTxtHorarioLaboral().setText(horarioLaboral.toString());
    } else { // Deselección
        view.getEmpleadosView().getTxtNombre().setText("");
        view.getEmpleadosView().getTxtCedula().setText("");
        view.getEmpleadosView().getTxtTelefono().setText("");
        view.getEmpleadosView().getTxtCorreo().setText("");
        view.getEmpleadosView().getCbRol().setSelectedIndex(0); // Reinicia la selección
        view.getEmpleadosView().getTxtHorarioLaboral().setText("");    }
   }


    private void agregarListenersEmpleados() {

        // Listener para manejar selección de filas en la tabla de empleados
        view.getEmpleadosView().getTablaEmpleados().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                manejarSeleccionFilaEmpleados();
            }
        });

        // Listener para manejar clics en la tabla de empleados (deselección)
        view.getEmpleadosView().getTablaEmpleados().addMouseListener(new java.awt.event.MouseAdapter() {
        private int ultimaFilaSeleccionada = -1;

        @Override
        public void mouseClicked(java.awt.event.MouseEvent e) {
            int filaSeleccionada = view.getEmpleadosView().getTablaEmpleados().getSelectedRow();

            // Si se clickea en la misma fila seleccionada, deseleccionarla
            if (filaSeleccionada == ultimaFilaSeleccionada) {
                view.getEmpleadosView().getTablaEmpleados().clearSelection(); // Deseleccionar
                view.getEmpleadosView().getTxtNombre().setText("");
                view.getEmpleadosView().getTxtCedula().setText("");
                view.getEmpleadosView().getTxtTelefono().setText("");
                view.getEmpleadosView().getTxtCorreo().setText("");
                view.getEmpleadosView().getCbRol().setSelectedIndex(0); // Reinicia la selección
                view.getEmpleadosView().getTxtHorarioLaboral().setText("");
                ultimaFilaSeleccionada = -1; // Resetear la última fila seleccionada
            } else {
                ultimaFilaSeleccionada = filaSeleccionada; // Actualizar la última fila seleccionada
            }
        }
    });
    }




    // Método para cargar empleados en la tabla
    private void cargarEmpleadosEnTabla() {
     try {
        List<?> listaEmpleados = modelo.getEmpleados().listarEmpleados(); // Obtener lista desde el modelo
        DefaultTableModel modeloTabla = view.getEmpleadosView().getModeloTabla();
        modeloTabla.setRowCount(0); // Limpiar tabla

        for (Object obj : listaEmpleados) {
            Object[] datos = (Object[]) obj; // Convertir cada empleado a un array de objetos
            modeloTabla.addRow(datos); // Añadir fila a la tabla
        }
     } catch (SQLException e) {
        JOptionPane.showMessageDialog(view, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
     }
    }


    private void cargarRolesEnComboBox() {
        try {
            List<String> roles = modelo.getEmpleados().obtenerRoles();
    
            view.getEmpleadosView().getCbRol().removeAllItems(); // Limpiar el ComboBox existente
            for (String rol : roles) {
                view.getEmpleadosView().getCbRol().addItem(rol);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al cargar los roles: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }








    
    


    //--------------------------- metodo que se ejecuta cuando se produce un evento ------------------
    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){

           case "PrincipalView": 
                view.setPrincipalView();
            break; 

          case "ClientesView":
            view.setClientesView();
            cargarClientesEnTabla();
          break;  

          case "EmpleadosView":
           view.setEmpleadosView();
            cargarEmpleadosEnTabla();
            cargarRolesEnComboBox();
            agregarListenersEmpleados();
          break; 

          case "AgregarClientesView":
            try {
                // Obtener datos del formulario
                String nombre = view.getClientesView().getTxtNombre().getText().trim();
                String numeroIdentificacion = view.getClientesView().getTxtNIdentificacion().getText().trim();
                String telefono = view.getClientesView().getTxtTelefono().getText().trim();
                String correo = view.getClientesView().getTxtCorreo().getText().trim();
                String estaturaStr = view.getClientesView().getTxtEstatura().getText().trim();
                String edadStr = view.getClientesView().getTxtEdad().getText().trim();
                String nombreFamiliar = view.getClientesView().getTxtNombreFamiliar().getText().trim();
                String telefonoFamiliar = view.getClientesView().getTxtTelefonoFamiliar().getText().trim();
                int visitas = 1; // Usuario nuevo

                // Validaciones de Formato
                if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                    throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
                }
                if (!numeroIdentificacion.matches("^\\d+$")) {
                    throw new IllegalArgumentException("El número de identificación solo puede contener números.");
                }
                if (!telefono.matches("^\\d+$")) {
                    throw new IllegalArgumentException("El teléfono solo puede contener números.");
                }
                if (!correo.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                    throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
                }

                // Validaciones de Rango
                double estatura;
                try {
                    estatura = Double.parseDouble(estaturaStr);
                    if (estatura < 0.5 || estatura > 2.5) { // Ejemplo de rango para estatura en metros
                        throw new IllegalArgumentException("La estatura debe estar entre 0.5 y 2.5 metros.");
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("La estatura debe ser un número válido.");
                }

                int edad;
                try {
                    edad = Integer.parseInt(edadStr);
                    if (edad < 0 || edad > 120) { // Ejemplo de rango para edad
                        throw new IllegalArgumentException("La edad debe estar entre 0 y 120 años.");
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("La edad debe ser un número válido.");
                }

                // Validaciones de familiar solo si el cliente es menor de edad
                if (edad < 18) {
                    if (!nombreFamiliar.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                        throw new IllegalArgumentException("El nombre del familiar solo puede contener letras y espacios.");
                    }
                    if (!telefonoFamiliar.matches("^\\d+$")) {
                        throw new IllegalArgumentException("El teléfono del familiar solo puede contener números.");
                    }
                }

                
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
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Error al agregar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            String estaturaStr = view.getClientesView().getTxtEstatura().getText().trim();
            String edadStr = view.getClientesView().getTxtEdad().getText().trim();
            String nombreFamiliar = view.getClientesView().getTxtNombreFamiliar().getText().trim();
            String telefonoFamiliar = view.getClientesView().getTxtTelefonoFamiliar().getText().trim();
            int visitas = Integer.parseInt(view.getClientesView().getModeloTabla().getValueAt(filaSeleccionada, 9).toString());

            // Validaciones de Formato
            if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
            }
            if (!numeroIdentificacion.matches("^\\d+$")) {
                throw new IllegalArgumentException("El número de identificación solo puede contener números.");
            }
            if (!telefono.matches("^\\d+$")) {
                throw new IllegalArgumentException("El teléfono solo puede contener números.");
            }
            if (!correo.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
            }


            // Validaciones de Rango
            double estatura;
            try {
                estatura = Double.parseDouble(estaturaStr);
                if (estatura < 0.5 || estatura > 2.5) { // Ejemplo de rango para estatura en metros
                    throw new IllegalArgumentException("La estatura debe estar entre 0.5 y 2.5 metros.");
                }
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("La estatura debe ser un número válido.");
            }

            int edad;
            try {
                edad = Integer.parseInt(edadStr);
                if (edad < 0 || edad > 120) { // Ejemplo de rango para edad
                    throw new IllegalArgumentException("La edad debe estar entre 0 y 120 años.");
                }
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("La edad debe ser un número válido.");
            }

            // Validaciones de familiar solo si el cliente es menor de edad
            if (edad < 18) {
                if (!nombreFamiliar.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                    throw new IllegalArgumentException("El nombre del familiar solo puede contener letras y espacios.");
                }
                if (!telefonoFamiliar.matches("^\\d+$")) {
                    throw new IllegalArgumentException("El teléfono del familiar solo puede contener números.");
                }
            }

    
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
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al actualizar el cliente: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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

          case "AgreagarEmpleadosView":
          
           try {
            String nombre = view.getEmpleadosView().getTxtNombre().getText().trim();
            String cedula = view.getEmpleadosView().getTxtCedula().getText().trim();
            String telefono = view.getEmpleadosView().getTxtTelefono().getText().trim();
            String correo = view.getEmpleadosView().getTxtCorreo().getText().trim();
            String horarioLaboral = view.getEmpleadosView().getTxtHorarioLaboral().getText().trim();


            // Obtener el índice y el valor del rol seleccionado en el ComboBox
            int indiceRol = view.getEmpleadosView().getCbRol().getSelectedIndex() + 1;

            // Validaciones de Formato
            if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
            }
            if (!cedula.matches("^\\d+$")) {
                throw new IllegalArgumentException("La cédula solo puede contener números.");
            }
            if (!telefono.matches("^\\d+$")) {
                throw new IllegalArgumentException("El teléfono solo puede contener números.");
            }
            if (!correo.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
            }

            // Validaciones de Formato para horarioLaboral (MaskFormatter ya lo asegura, pero validar que no tenga placeholders)
            if (horarioLaboral.contains("_")) {
                throw new IllegalArgumentException("Por favor, completa el horario laboral correctamente.");
            }

            // Validación de formato y lógica para horario laboral
            if (!horarioLaboral.matches("^(?:[01]\\d|2[0-3]):[0-5]\\d - (?:[01]\\d|2[0-3]):[0-5]\\d$")) {
                throw new IllegalArgumentException("El horario laboral debe estar en un formato válido (HH:mm - HH:mm), con horas entre 00:00 y 23:59.");
            }


    
            modelo.getEmpleados().agregarEmpleado(nombre, cedula, telefono, correo, indiceRol, horarioLaboral);
            cargarEmpleadosEnTabla();

            view.getEmpleadosView().getTxtNombre().setText("");
            view.getEmpleadosView().getTxtCedula().setText("");
            view.getEmpleadosView().getTxtTelefono().setText("");
            view.getEmpleadosView().getTxtCorreo().setText("");
            view.getEmpleadosView().getCbRol().setSelectedIndex(0); // Reinicia la selección

            // Verifica si el campo es un JFormattedTextField con máscara
            if (view.getEmpleadosView().getTxtHorarioLaboral() instanceof JFormattedTextField) {
                ((JFormattedTextField) view.getEmpleadosView().getTxtHorarioLaboral()).setValue(null);
            } else {
                view.getEmpleadosView().getTxtHorarioLaboral().setText("");
            }


            JOptionPane.showMessageDialog(view, "Empleado agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
           } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
           } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Error al agregar el empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           }
          break;

          case "EditarEmpleadosView":
          
          try {
            // Verificar que haya una fila seleccionada
            int filaSeleccionada = view.getEmpleadosView().getTablaEmpleados().getSelectedRow();
            if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(view, "Por favor, seleccione un empleado para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            }
    
            // Obtener el ID del empleado
            int id = Integer.parseInt(view.getEmpleadosView().getModeloTabla().getValueAt(filaSeleccionada, 0).toString());
    
            // Obtener los nuevos datos del formulario
            String nombre = view.getEmpleadosView().getTxtNombre().getText().trim();
            String cedula = view.getEmpleadosView().getTxtCedula().getText().trim();
            String telefono = view.getEmpleadosView().getTxtTelefono().getText().trim();
            String correo = view.getEmpleadosView().getTxtCorreo().getText().trim();
            int indiceRol = view.getEmpleadosView().getCbRol().getSelectedIndex() +1;
            String horarioLaboral = view.getEmpleadosView().getTxtHorarioLaboral().getText().trim();

            // Validaciones de Formato
            if (!nombre.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$")) {
                throw new IllegalArgumentException("El nombre solo puede contener letras y espacios.");
            }
            if (!cedula.matches("^\\d+$")) {
                throw new IllegalArgumentException("La cédula solo puede contener números.");
            }
            if (!telefono.matches("^\\d+$")) {
                throw new IllegalArgumentException("El teléfono solo puede contener números.");
            }
            if (!correo.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
                throw new IllegalArgumentException("El correo electrónico no tiene un formato válido.");
            }
            
            // Validaciones de Formato para horarioLaboral (MaskFormatter ya lo asegura, pero validar que no tenga placeholders)
            if (horarioLaboral.contains("_")) {
                throw new IllegalArgumentException("Por favor, completa el horario laboral correctamente.");
            }
            
            // Validación de formato y lógica para horario laboral
            if (!horarioLaboral.matches("^(?:[01]\\d|2[0-3]):[0-5]\\d - (?:[01]\\d|2[0-3]):[0-5]\\d$")) {
                throw new IllegalArgumentException("El horario laboral debe estar en un formato válido (HH:mm - HH:mm), con horas entre 00:00 y 23:59.");
            }
            
            
    
            // Actualizar empleado en la base de datos
            modelo.getEmpleados().actualizarEmpleado(id, nombre, cedula, telefono, correo, indiceRol,horarioLaboral);
    
            // Actualizar la tabla
            cargarEmpleadosEnTabla();
    
            // Limpiar el formulario
            view.getEmpleadosView().getTxtNombre().setText("");
            view.getEmpleadosView().getTxtCedula().setText("");
            view.getEmpleadosView().getTxtTelefono().setText("");
            view.getEmpleadosView().getTxtCorreo().setText("");
            view.getEmpleadosView().getCbRol().setSelectedIndex(0); // Reinicia la selección
            // Verifica si el campo es un JFormattedTextField con máscara
            if (view.getEmpleadosView().getTxtHorarioLaboral() instanceof JFormattedTextField) {
                ((JFormattedTextField) view.getEmpleadosView().getTxtHorarioLaboral()).setValue(null);
            } else {
                view.getEmpleadosView().getTxtHorarioLaboral().setText("");
            }

            // Confirmación
            JOptionPane.showMessageDialog(view, "Empleado actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
          } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(view, "Error al actualizar el empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
          } catch (NumberFormatException ex) {
                 JOptionPane.showMessageDialog(view, "Por favor, ingresa valores válidos en los campos numéricos.", "Error de validación", JOptionPane.WARNING_MESSAGE);
          }
          break;

          case "EliminarEmpleadosView":
          try {
            // Verificar que haya una fila seleccionada
            int filaSeleccionada = view.getEmpleadosView().getTablaEmpleados().getSelectedRow();
            if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(view, "Por favor, seleccione un empleado para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            }

            // Confirmar eliminación
            int confirmacion = JOptionPane.showConfirmDialog(
                view,
                    "¿Está seguro de que desea eliminar este empleado?",
                        "Confirmación",
                        JOptionPane.YES_NO_OPTION
                    );
            
                if (confirmacion == JOptionPane.YES_OPTION) {
    
                    // Obtener el ID del empleado
                    int id = Integer.parseInt(view.getEmpleadosView().getModeloTabla().getValueAt(filaSeleccionada, 0).toString());
            
                    // Eliminar empleado de la base de datos
                    modelo.getEmpleados().eliminarEmpleado(id);
            
                    // Actualizar la tabla
                    cargarEmpleadosEnTabla();
            
                    // Confirmación
                    JOptionPane.showMessageDialog(view, "Empleado eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
           } catch (SQLException ex) {
                 JOptionPane.showMessageDialog(view, "Error al eliminar el empleado: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
           }
          break;


          default:

          break;
        }
    }

}
