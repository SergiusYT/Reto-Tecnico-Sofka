package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Clientes;
import model.SalitreMagicoFacade;
import view.View;

public class Controller implements ActionListener {

    private SalitreMagicoFacade modelo;
    private View view;

    public Controller(){
        modelo = new SalitreMagicoFacade();
        view = new View();
        agregarBotones();
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
        view.getPrincipalView().getBotonReportes().addActionListener(this);
        view.getPrincipalView().getBotonReportes().setActionCommand("ReportesView");
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


        //----------------------------------- AtraccionesView --------------------------------------------
        view.getAtraccionesView().getBotonHome().addActionListener(this);
        view.getAtraccionesView().getBotonHome().setActionCommand("PrincipalView");
        view.getAtraccionesView().getBtnAgregar().addActionListener(this);
        view.getAtraccionesView().getBtnAgregar().setActionCommand("AgregarAtraccionesView");
        view.getAtraccionesView().getBtnEditar().addActionListener(this);
        view.getAtraccionesView().getBtnEditar().setActionCommand("EditarAtraccionesView");
        view.getAtraccionesView().getBtnEliminar().addActionListener(this);
        view.getAtraccionesView().getBtnEliminar().setActionCommand("EliminarAtraccionesView");

        //----------------------------------- TiquetesView --------------------------------------------
        view.getTiquetesView().getBotonHome().addActionListener(this);
        view.getTiquetesView().getBotonHome().setActionCommand("PrincipalView");
        view.getTiquetesView().getBtnAgregar().addActionListener(this);
        view.getTiquetesView().getBtnAgregar().setActionCommand("AgregarTiquetesView");
        view.getTiquetesView().getBtnEditar().addActionListener(this);
        view.getTiquetesView().getBtnEditar().setActionCommand("EditarTiquetesView");
        view.getTiquetesView().getBtnEliminar().addActionListener(this);
        view.getTiquetesView().getBtnEliminar().setActionCommand("EliminarTiquetesView");
        view.getTiquetesView().getBtnIngreso().addActionListener(this);
        view.getTiquetesView().getBtnIngreso().setActionCommand("IngresoTiquetesView");

        //----------------------------------- ReportesView --------------------------------------------
        view.getReportesView().getBotonHome().addActionListener(this);
        view.getReportesView().getBotonHome().setActionCommand("PrincipalView");
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


  //--------------------------- METODOS PARA ATRACCIONESView --------------------------------

  

    private void agregarListenersAtracciones() {
        // Listener para manejar selección de filas en la tabla de atracciones
        view.getAtraccionesView().getTablaAtracciones().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                manejarSeleccionFilaAtracciones();
            }
        });

        // Listener para manejar clics en la tabla de atracciones (deselección)
        view.getAtraccionesView().getTablaAtracciones().addMouseListener(new java.awt.event.MouseAdapter() {
            private int ultimaFilaSeleccionada = -1;

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int filaSeleccionada = view.getAtraccionesView().getTablaAtracciones().getSelectedRow();

                if (filaSeleccionada == ultimaFilaSeleccionada) {
                    view.getAtraccionesView().getTablaAtracciones().clearSelection();
                    view.getAtraccionesView().getTxtNombre().setText("");
                    view.getAtraccionesView().getTxtDescripcion().setText("");
                    view.getAtraccionesView().getCbClasificacion().setSelectedIndex(0);
                    view.getAtraccionesView().getTxtEstaturaMinima().setText("");
                    view.getAtraccionesView().getTxtCondicionesUso().setText("");
                    view.getAtraccionesView().getCbEstado().setSelectedIndex(0);
                    view.getAtraccionesView().getTxtEmpleado().setText("");                ultimaFilaSeleccionada = -1;
                } else {
                    ultimaFilaSeleccionada = filaSeleccionada;
                }
            }
        });


            // Listener para manejar selección de filas en la tabla de empleados operadores
        view.getAtraccionesView().getTablaEmpleados().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                manejarSeleccionFilaEmpleadosOperadores();
            }
        });

        // Listener para manejar clics en la tabla de empleados operadores (deselección)
        view.getAtraccionesView().getTablaEmpleados().addMouseListener(new java.awt.event.MouseAdapter() {
            private int ultimaFilaSeleccionadaEmpleados = -1;

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int filaSeleccionada = view.getAtraccionesView().getTablaEmpleados().getSelectedRow();

                if (filaSeleccionada == ultimaFilaSeleccionadaEmpleados) {
                    view.getAtraccionesView().getTxtEmpleado().setText(""); 
                    ultimaFilaSeleccionadaEmpleados = -1;
                } else {
                    ultimaFilaSeleccionadaEmpleados = filaSeleccionada;
                }
            }
        });


    }

    private void cargarDatosInicialesAtracciones() {
        cargarAtraccionesEnTabla();
        cargarEmpleadosOperadoresEnTabla();
        cargarClasificacionesEnComboBox();
    }

    private void cargarAtraccionesEnTabla() {
        try {
            List<?> listaAtracciones = modelo.getAtracciones().listarAtracciones();
            DefaultTableModel modeloTabla = view.getAtraccionesView().getModeloTablaAtracciones();
            modeloTabla.setRowCount(0);

            for (Object obj : listaAtracciones) {
                Object[] datos = (Object[]) obj;
                modeloTabla.addRow(datos);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error al cargar atracciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cargarEmpleadosOperadoresEnTabla() {
        try {
            int rolOperador = 4; // Rol operador
            List<Object[]> listaEmpleados = modelo.getEmpleados().listarEmpleadosPorRol(rolOperador); // Obtener empleados con rol 3
            DefaultTableModel modeloTabla = view.getAtraccionesView().getModeloTablaEmpleados();
            modeloTabla.setRowCount(0); // Limpiar la tabla
    
            for (Object[] datos : listaEmpleados) {
                modeloTabla.addRow(datos); // Añadir filas directamente
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error al cargar los datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
       }


    private void cargarClasificacionesEnComboBox() {
        try {
            List<String> clasificaciones = modelo.getAtracciones().obtenerClasificaciones();
            JComboBox<String> cbClasificacion = view.getAtraccionesView().getCbClasificacion();
            cbClasificacion.removeAllItems();
            for (String clasificacion : clasificaciones) {
                cbClasificacion.addItem(clasificacion);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error al cargar clasificaciones: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void manejarSeleccionFilaAtracciones() {
        int filaSeleccionada = view.getAtraccionesView().getTablaAtracciones().getSelectedRow();

        if (filaSeleccionada >= 0) {
            DefaultTableModel modeloTabla = view.getAtraccionesView().getModeloTablaAtracciones();
            String nombre = modeloTabla.getValueAt(filaSeleccionada, 1).toString();
            String descripcion = modeloTabla.getValueAt(filaSeleccionada, 2).toString();
            String clasificacion = modeloTabla.getValueAt(filaSeleccionada, 3).toString();
            String estaturaMinima = modeloTabla.getValueAt(filaSeleccionada, 4).toString();
            String condicionesUso = modeloTabla.getValueAt(filaSeleccionada, 5).toString();
            String estado = modeloTabla.getValueAt(filaSeleccionada, 6).toString();
            String empleado = modeloTabla.getValueAt(filaSeleccionada, 7).toString();

            // Rellenar formulario
            view.getAtraccionesView().getTxtNombre().setText(nombre);
            view.getAtraccionesView().getTxtDescripcion().setText(descripcion);
            view.getAtraccionesView().getCbClasificacion().setSelectedItem(clasificacion);
            view.getAtraccionesView().getTxtEstaturaMinima().setText(estaturaMinima);
            view.getAtraccionesView().getTxtCondicionesUso().setText(condicionesUso);
            view.getAtraccionesView().getCbEstado().setSelectedItem(estado);
            view.getAtraccionesView().getTxtEmpleado().setText(empleado);
        } else {
            view.getAtraccionesView().getTxtNombre().setText("");
            view.getAtraccionesView().getTxtDescripcion().setText("");
            view.getAtraccionesView().getCbClasificacion().setSelectedIndex(0);
            view.getAtraccionesView().getTxtEstaturaMinima().setText("");
            view.getAtraccionesView().getTxtCondicionesUso().setText("");
            view.getAtraccionesView().getCbEstado().setSelectedIndex(0);
            view.getAtraccionesView().getTxtEmpleado().setText("");    
        }
    }

    private void manejarSeleccionFilaEmpleadosOperadores() {
        int filaSeleccionada = view.getAtraccionesView().getTablaEmpleados().getSelectedRow();
    
        if (filaSeleccionada >= 0) {
            DefaultTableModel modeloTabla = view.getAtraccionesView().getModeloTablaEmpleados();
            String idEmpleado = modeloTabla.getValueAt(filaSeleccionada, 0).toString();
    
            // Rellenar formulario (o campos asociados)
            view.getAtraccionesView().getTxtEmpleado().setText(idEmpleado); 
        } else {
            view.getAtraccionesView().getTxtEmpleado().setText(""); // O el campo correspondiente para mostrar datos de empleados
        }
    }
    
    private void configurarVistaAdministrador() {
        view.getAtraccionesView().getBtnAgregar().setEnabled(true);
        view.getAtraccionesView().getBtnEliminar().setEnabled(true);
        view.getAtraccionesView().getTablaEmpleados().setVisible(true);
        view.getAtraccionesView().getTxtNombre().setEnabled(true);
        view.getAtraccionesView().getTxtDescripcion().setEnabled(true);
        view.getAtraccionesView().getTxtEstaturaMinima().setEnabled(true);
        view.getAtraccionesView().getTxtCondicionesUso().setEnabled(true);
        view.getAtraccionesView().getCbClasificacion().setEnabled(true);
        view.getAtraccionesView().getTxtEmpleado().setEnabled(false);
        view.getAtraccionesView().getCbEstado().setEnabled(false);
    }
    
    private void configurarVistaMantenimiento() {
        view.getAtraccionesView().getBtnAgregar().setEnabled(false);
        view.getAtraccionesView().getBtnEliminar().setEnabled(false);
        view.getAtraccionesView().getTablaEmpleados().setVisible(false);
        view.getAtraccionesView().getTxtNombre().setEnabled(false);
        view.getAtraccionesView().getTxtDescripcion().setEnabled(false);
        view.getAtraccionesView().getTxtEstaturaMinima().setEnabled(false);
        view.getAtraccionesView().getTxtCondicionesUso().setEnabled(false);
        view.getAtraccionesView().getCbClasificacion().setEnabled(false);
        view.getAtraccionesView().getCbEstado().setEnabled(true);
        view.getAtraccionesView().getTxtEmpleado().setEnabled(false);
    }
    
    
//----------------------------------TiquetesView---------------------------------------------

    private void agregarListenersTiquetesView() {

        // Listener para selección en la tabla de tiquetes
        view.getTiquetesView().getTablaTiquetes().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                manejarSeleccionFilaTiquetes();
            }
        });

        view.getTiquetesView().getTablaTiquetes().addMouseListener(new java.awt.event.MouseAdapter() {
            private int ultimaFilaSeleccionadaTiquetes = -1;

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int filaSeleccionada = view.getTiquetesView().getTablaTiquetes().getSelectedRow();
                if (filaSeleccionada == ultimaFilaSeleccionadaTiquetes) {
                    view.getTiquetesView().getTablaTiquetes().clearSelection();
                    view.getTiquetesView().getTxtClienteID().setText("");
                    view.getTiquetesView().getTxtEstacionID().setText("");
                    view.getTiquetesView().getTxtFecha().setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                    view.getTiquetesView().getCbTipoTiqueteID().setSelectedIndex(0);   
                    ultimaFilaSeleccionadaTiquetes = -1;
                } else {
                    ultimaFilaSeleccionadaTiquetes = filaSeleccionada;
                }
            }
        });


        // Listener para selección en la tabla de clientes
        view.getTiquetesView().getTablaClientes().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                manejarSeleccionFilaClientesTiquetes();
            }
        });

        view.getTiquetesView().getTablaClientes().addMouseListener(new java.awt.event.MouseAdapter() {
            private int ultimaFilaSeleccionadaClientes = -1;

            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int filaSeleccionada = view.getTiquetesView().getTablaClientes().getSelectedRow();
                if (filaSeleccionada == ultimaFilaSeleccionadaClientes) {
                    view.getTiquetesView().getTablaClientes().clearSelection();
                    view.getTiquetesView().getTxtClienteID().setText("");
                    ultimaFilaSeleccionadaClientes = -1;
                } else {
                    ultimaFilaSeleccionadaClientes = filaSeleccionada;
                }
            }
        });


    }

    private void cargarDatosInicialesTiquetes() {
        cargarTiquetesEnTabla();
        cargarClientesEnTablaTiquetes();
        cargarAtraccionesEnTablaTiquetes();
        cargarTiposTiquetesEnComboBox();
    }

    private void cargarTiquetesEnTabla() {
        try {
            List<Object[]> listaTiquetes = modelo.getTickets().obtenerTiquetes();
            DefaultTableModel modeloTabla = (DefaultTableModel) view.getTiquetesView().getModeloTablaTiquetes();
            modeloTabla.setRowCount(0);

            
            for (Object[] tiquete : listaTiquetes) {
                modeloTabla.addRow(tiquete);
            }
        } catch (SQLException e) {
            mostrarError("Error al cargar tiquetes", e);
        }
    }

    private void cargarClientesEnTablaTiquetes() {
        try {
            List<Object[]> listaClientes = modelo.getClientes().listarClientes();
            DefaultTableModel modeloTabla = view.getTiquetesView().getModeloTablaClientes();
            modeloTabla.setRowCount(0);

            for (Object[] cliente : listaClientes) {
                modeloTabla.addRow(cliente);
            }
        } catch (SQLException e) {
            mostrarError("Error al cargar clientes", e);
        }
    }

    private void cargarAtraccionesEnTablaTiquetes() {
        try {
            List<Object[]> listaAtracciones = modelo.getAtracciones().listarAtracciones();
            DefaultTableModel modeloTabla = view.getTiquetesView().getModeloTablaAtracciones();
            modeloTabla.setRowCount(0);

            for (Object[] atraccion : listaAtracciones) {
                modeloTabla.addRow(atraccion);
            }
        } catch (SQLException e) {
            mostrarError("Error al cargar atracciones", e);
        }
    }

    private void manejarSeleccionFilaTiquetes() {
        int filaSeleccionada = view.getTiquetesView().getTablaTiquetes().getSelectedRow();
        if (filaSeleccionada >= 0) {
            view.getTiquetesView().getTxtClienteID().setText(view.getTiquetesView().getTablaTiquetes().getValueAt(filaSeleccionada, 1).toString());
            view.getTiquetesView().getTxtEstacionID().setText(view.getTiquetesView().getTablaTiquetes().getValueAt(filaSeleccionada, 2).toString());
            view.getTiquetesView().getTxtFecha().setText(view.getTiquetesView().getTablaTiquetes().getValueAt(filaSeleccionada, 3).toString());

            // Obtener el índice para el JComboBox
            try {
                int indiceComboBox = Integer.parseInt(view.getTiquetesView().getTablaTiquetes().getValueAt(filaSeleccionada, 4).toString());
                if (indiceComboBox >= 0 && indiceComboBox < view.getTiquetesView().getCbTipoTiqueteID().getItemCount()) {
                    view.getTiquetesView().getCbTipoTiqueteID().setSelectedIndex(indiceComboBox);
                } else {
                    view.getTiquetesView().getCbTipoTiqueteID().setSelectedIndex(-1); // Ninguna selección si el índice es inválido
                }
            } catch (NumberFormatException e) {
                view.getTiquetesView().getCbTipoTiqueteID().setSelectedIndex(-1); // Ninguna selección si el valor no es un número
            }
        } else {
            view.getTiquetesView().getTxtClienteID().setText("");
            view.getTiquetesView().getTxtEstacionID().setText("");
            view.getTiquetesView().getTxtFecha().setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            view.getTiquetesView().getCbTipoTiqueteID().setSelectedIndex(0);   
     }
    }
    

    private void manejarSeleccionFilaClientesTiquetes() {
        int filaSeleccionada = view.getTiquetesView().getTablaClientes().getSelectedRow();
        if (filaSeleccionada >= 0) {
            view.getTiquetesView().getTxtClienteID().setText(view.getTiquetesView().getTablaClientes().getValueAt(filaSeleccionada, 0).toString());
        } else {
            view.getTiquetesView().getTxtClienteID().setText("");
        }
    }



    private void mostrarError(String mensaje, Exception e) {
        JOptionPane.showMessageDialog(view, mensaje + ": " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }


    private void cargarTiposTiquetesEnComboBox() {
        try {
            List<String> tipos = modelo.getTickets().obtenerTiposTiquetes();
            JComboBox<String> cbTipos = view.getTiquetesView().getCbTipoTiqueteID();
            cbTipos.removeAllItems();
            for (String tiposTiquetes : tipos) {
                cbTipos.addItem(tiposTiquetes);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(view, "Error al cargar tipos de tiquetes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void configurarVistaTiquetes(int rol) {
        if (rol == 4) { // Operador
            view.getTiquetesView().getBtnAgregar().setEnabled(false);
            view.getTiquetesView().getBtnEditar().setEnabled(false);
            view.getTiquetesView().getBtnEliminar().setEnabled(false);
            view.getTiquetesView().getTxtClienteID().setEnabled(false);
            view.getTiquetesView().getTxtEstacionID().setEnabled(false);
            view.getTiquetesView().getTxtFecha().setEnabled(false);
            view.getTiquetesView().getCbTipoTiqueteID().setEnabled(false);
            view.getTiquetesView().getBtnIngreso().setEnabled(true);
        } else if (rol == 2) { // Logística
            view.getTiquetesView().getBtnAgregar().setEnabled(true);
            view.getTiquetesView().getBtnEditar().setEnabled(true);
            view.getTiquetesView().getBtnEliminar().setEnabled(true);
            view.getTiquetesView().getTxtClienteID().setEnabled(true);
            view.getTiquetesView().getTxtEstacionID().setEnabled(false);
            view.getTiquetesView().getTxtEstacionID().setDisabledTextColor(Color.BLACK);
            view.getTiquetesView().getTxtFecha().setEnabled(true);
            view.getTiquetesView().getCbTipoTiqueteID().setEnabled(true);
            view.getTiquetesView().getBtnIngreso().setEnabled(false);
        }
    }

    //--------------------------------REPORTESVIEW--------------------------------

    private void cargarDatosReportes() {
    try {
        Map<String, Integer> visitasAtracciones = modelo.getTickets().obtenerVisitasAtracciones();
        Map<String, Integer> clientesFrecuentes = modelo.getClientes().obtenerClientesFrecuentes();
        view.getReportesView().actualizarTablaAtracciones(visitasAtracciones);
        view.getReportesView().actualizarGraficoAtracciones(visitasAtracciones);

        view.getReportesView().actualizarTablaClientes(clientesFrecuentes);
        view.getReportesView().actualizarGraficoClientes(clientesFrecuentes);

    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(view, "Error al cargar los datos de reportes: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
            agregarListenersClientes(); 
          break;  

          case "EmpleadosView":
           view.setEmpleadosView();
            cargarEmpleadosEnTabla();
            cargarRolesEnComboBox();
            agregarListenersEmpleados();
          break;
          
          case "AtraccionesView":

            String cedulaEmpleado = JOptionPane.showInputDialog("Ingrese su cédula:");
            if (cedulaEmpleado != null) {
                try {
                    int rol = modelo.getEmpleados().obtenerRolPorCedula(cedulaEmpleado);
                    if (rol == 1) { // Administrador
                        configurarVistaAdministrador();
                        view.setAtraccionesView();
                        agregarListenersAtracciones();
                        cargarDatosInicialesAtracciones();
                    } else if (rol == 5) { // Mantenimiento
                        configurarVistaMantenimiento();
                        view.setAtraccionesView();
                        agregarListenersAtracciones();
                        cargarDatosInicialesAtracciones();
                    } else {
                        JOptionPane.showMessageDialog(view, "Acceso denegado.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(view, "Error al validar el rol: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
                              
          break;

          case "TicketsView":

          String cedulaEmpleadoTiquetes = JOptionPane.showInputDialog("Ingrese su cédula:");
          if (cedulaEmpleadoTiquetes != null) {
              try {
                  int rol = modelo.getEmpleados().obtenerRolPorCedula(cedulaEmpleadoTiquetes);
                  if (rol == 2 || rol == 4) { // Operador (4) o Logística (2)
                      configurarVistaTiquetes(rol);
                      view.setTiquetesView();
                      agregarListenersTiquetesView();
                      cargarDatosInicialesTiquetes();
      
                      if (rol == 2) { // Logística
                          List<Object[]> estaciones = modelo.getTickets().obtenerEstaciones();
                          if (!estaciones.isEmpty()) {
                              String[] opcionesEstaciones = estaciones.stream()
                                  .map(estacion -> (String) estacion[1]) // Extraer el nombre de la estación
                                  .toArray(String[]::new);
      
                              JComboBox<String> comboBoxEstaciones = new JComboBox<>(opcionesEstaciones);
                              int resultado = JOptionPane.showConfirmDialog(
                                  null,
                                  comboBoxEstaciones,
                                  "Seleccione una estación",
                                  JOptionPane.OK_CANCEL_OPTION,
                                  JOptionPane.QUESTION_MESSAGE
                              );
      
                              if (resultado == JOptionPane.OK_OPTION) {
                                  int indiceSeleccionado = comboBoxEstaciones.getSelectedIndex();
                                  int estacionIdSeleccionada = (int) estaciones.get(indiceSeleccionado)[0]; // Obtener el ID de la estación seleccionada
                                  
                                  // Actualizar el campo `estado_id` con el ID seleccionado
                                  // Aquí puedes realizar cualquier operación que desees con el ID seleccionado
                                  JOptionPane.showMessageDialog(
                                      null,
                                      "Se ha seleccionado la estación con ID: " + estacionIdSeleccionada,
                                      "Confirmación",
                                      JOptionPane.INFORMATION_MESSAGE
                                  );

                                  view.getTiquetesView().getTxtEstacionID().setText(String.valueOf(estacionIdSeleccionada));

                              }
                          } else {
                              JOptionPane.showMessageDialog(
                                  null,
                                  "No hay estaciones disponibles para seleccionar.",
                                  "Información",
                                  JOptionPane.INFORMATION_MESSAGE
                              );
                          }
                      } else if (rol == 4) {
                          modelo.getAtracciones().verificarCambiosEstado(); // Mostrar notificaciones solo si hay cambios
                      }
                  } else {
                      JOptionPane.showMessageDialog(view, "Acceso denegado.", "Error", JOptionPane.ERROR_MESSAGE);
                  }
              } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(view, "Error al validar el rol: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
              }
          }

          break;

          case "ReportesView":
            view.setReportesView();
            cargarDatosReportes();
          break;

          case "Salir":
            System.exit(0);
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
                int visitas = 0; // Usuario nuevo

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

          case "AgregarAtraccionesView":

          try {
            // Obtener datos del formulario
            String nombre = view.getAtraccionesView().getTxtNombre().getText().trim();
            String descripcion = view.getAtraccionesView().getTxtDescripcion().getText().trim();
            int clasificacionId = view.getAtraccionesView().getCbClasificacion().getSelectedIndex() + 1;
            String estaturaText = view.getAtraccionesView().getTxtEstaturaMinima().getText().trim();
            String condicionesUso = view.getAtraccionesView().getTxtCondicionesUso().getText().trim();
            String estado = view.getAtraccionesView().getCbEstado().getSelectedItem().toString();
            String empleadoText = view.getAtraccionesView().getTxtEmpleado().getText().trim();
    
            // Validaciones básicas
            if (nombre.isEmpty() || descripcion.isEmpty() || condicionesUso.isEmpty()) {
                throw new IllegalArgumentException("Por favor, complete todos los campos obligatorios.");
            }

                    // Validar estatura mínima
        Double estaturaMinima;
        try {
            estaturaMinima = Double.parseDouble(estaturaText);
            if (estaturaMinima < 0.5 || estaturaMinima > 2.5) { // Valores lógicos de estatura en metros
                throw new IllegalArgumentException("La estatura mínima debe estar entre 0.5 m y 2.5 m.");
            }
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Por favor, ingrese una estatura válida.");
        }

        // Validar empleado ID
        int empleadoId;
        try {
            empleadoId = Integer.parseInt(empleadoText);
            if (empleadoId <= 0) {
                throw new IllegalArgumentException("El ID del empleado debe ser un número positivo.");
            }
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("Por favor, seleccione un empleado de la tabla de empleados.");
        }
    
            // Agregar atracción
            modelo.getAtracciones().agregarAtraccion(nombre, descripcion, clasificacionId, estaturaMinima, condicionesUso, estado, empleadoId);
            // Actualizar la tabla
            cargarAtraccionesEnTabla();
    
            // Limpiar formulario
            view.getAtraccionesView().getTxtNombre().setText("");
            view.getAtraccionesView().getTxtDescripcion().setText("");
            view.getAtraccionesView().getCbClasificacion().setSelectedIndex(0);
            view.getAtraccionesView().getTxtEstaturaMinima().setText("");
            view.getAtraccionesView().getTxtCondicionesUso().setText("");
            view.getAtraccionesView().getCbEstado().setSelectedIndex(0);
            view.getAtraccionesView().getTxtEmpleado().setText("");      
            // Mensaje de éxito
            JOptionPane.showMessageDialog(view, "Atracción agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        // Mensaje de éxito
        JOptionPane.showMessageDialog(view, "Atracción agregada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al agregar la atracción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

          break;
          case "EditarAtraccionesView":
            try {
                // Verificar selección en la tabla
                int filaSeleccionada = view.getAtraccionesView().getTablaAtracciones().getSelectedRow();
                if (filaSeleccionada < 0) {
                    JOptionPane.showMessageDialog(view, "Por favor, seleccione una atracción para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    break;
                }
        
                // Obtener ID y datos actualizados
                int id = Integer.parseInt(view.getAtraccionesView().getModeloTablaAtracciones().getValueAt(filaSeleccionada, 0).toString());
                String nombre = view.getAtraccionesView().getTxtNombre().getText().trim();
                String descripcion = view.getAtraccionesView().getTxtDescripcion().getText().trim();
                int clasificacionId = view.getAtraccionesView().getCbClasificacion().getSelectedIndex() + 1;
                String estaturaText = view.getAtraccionesView().getTxtEstaturaMinima().getText().trim();
                String condicionesUso = view.getAtraccionesView().getTxtCondicionesUso().getText().trim();
                String estado = view.getAtraccionesView().getCbEstado().getSelectedItem().toString();
                String empleadoText = view.getAtraccionesView().getTxtEmpleado().getText().trim();
        
            // Validaciones básicas
            if (nombre.isEmpty() || descripcion.isEmpty() || condicionesUso.isEmpty()) {
                throw new IllegalArgumentException("Por favor, complete todos los campos obligatorios.");
            }

                    // Validar estatura mínima
            Double estaturaMinima;
            try {
                estaturaMinima = Double.parseDouble(estaturaText);
                if (estaturaMinima < 0.5 || estaturaMinima > 2.5) { // Valores lógicos de estatura en metros
                    throw new IllegalArgumentException("La estatura mínima debe estar entre 0.5 m y 2.5 m.");
                }
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Por favor, ingrese una estatura válida.");
            }

                    // Validar empleado ID
            int empleadoId;
            try {
                empleadoId = Integer.parseInt(empleadoText);
                if (empleadoId <= 0) {
                    throw new IllegalArgumentException("El ID del empleado debe ser un número positivo.");
                }
            } catch (NumberFormatException ex) {
                throw new IllegalArgumentException("Por favor, seleccione un empleado de la tabla de empleados.");
            }
    
            // Actualizar atracción
            modelo.getAtracciones().actualizarAtraccion(id, nombre, descripcion, clasificacionId, estaturaMinima, condicionesUso, estado, empleadoId);
    
            // Actualizar tabla
            cargarAtraccionesEnTabla();

            // Activar bandera de notificación
            modelo.getAtracciones().activarNotificacionCambioEstado();
    
            // Limpiar formulario
            view.getAtraccionesView().getTxtNombre().setText("");
            view.getAtraccionesView().getTxtDescripcion().setText("");
            view.getAtraccionesView().getCbClasificacion().setSelectedIndex(0);
            view.getAtraccionesView().getTxtEstaturaMinima().setText("");
            view.getAtraccionesView().getTxtCondicionesUso().setText("");
            view.getAtraccionesView().getCbEstado().setSelectedIndex(0);
            view.getAtraccionesView().getTxtEmpleado().setText("");   

            // Mensaje de éxito
            JOptionPane.showMessageDialog(view, "Atracción actualizada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al editar la atracción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

          break;
          case "EliminarAtraccionesView":

          try {
            // Verificar selección en la tabla
            int filaSeleccionada = view.getAtraccionesView().getTablaAtracciones().getSelectedRow();
            if (filaSeleccionada < 0) {
                JOptionPane.showMessageDialog(view, "Por favor, seleccione una atracción para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            }
    
            // Confirmar eliminación
            int confirmacion = JOptionPane.showConfirmDialog(
                view,
                "¿Está seguro de que desea eliminar esta atracción?",
                "Confirmación",
                JOptionPane.YES_NO_OPTION
            );
    
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Obtener ID de la atracción
                int id = Integer.parseInt(view.getAtraccionesView().getModeloTablaAtracciones().getValueAt(filaSeleccionada, 0).toString());
    
                // Eliminar atracción
                modelo.getAtracciones().eliminarAtraccion(id);
    
                // Actualizar tabla
                cargarAtraccionesEnTabla();
    
                // Mensaje de éxito
                JOptionPane.showMessageDialog(view, "Atracción eliminada exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(view, "Error al eliminar la atracción: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
          
          break;
          case "AgregarTiquetesView":
   
            try {
                // Obtener datos del formulario
                String clienteIdText = view.getTiquetesView().getTxtClienteID().getText().trim();
                String estacionIdText = view.getTiquetesView().getTxtEstacionID().getText().trim();
                String fecha = view.getTiquetesView().getTxtFecha().getText().trim();
                int tipoTiqueteSeleccionado = view.getTiquetesView().getCbTipoTiqueteID().getSelectedIndex() + 1;
        
                // Validaciones básicas
                if (clienteIdText.isEmpty() || estacionIdText.isEmpty() || fecha.isEmpty()) {
                    throw new IllegalArgumentException("Por favor, complete todos los campos obligatorios.");
                }
        
                // Validar IDs numéricos
                int clienteId, estacionId;
                try {
                    clienteId = Integer.parseInt(clienteIdText);
                    estacionId = Integer.parseInt(estacionIdText);
                    if (clienteId <= 0 || estacionId <= 0) {
                        throw new IllegalArgumentException("Los IDs deben ser números positivos.");
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Por favor, ingrese valores numéricos válidos para los IDs.");
                }

                // Validar formato de la fecha
                try {
                    LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (DateTimeParseException ex) {
                    throw new IllegalArgumentException("El formato de la fecha es inválido. Use el formato yyyy-MM-dd.");
                }

        
                // Agregar tiquete
                modelo.getTickets().agregarTiquetes(clienteId, estacionId, fecha, tipoTiqueteSeleccionado);

                // Incrementar visitas del cliente
                modelo.getClientes().incrementarVisitas(clienteId);
        
                // Actualizar la tabla
                cargarTiquetesEnTabla();
                cargarClientesEnTabla();
        
                // Limpiar formulario
                view.getTiquetesView().getTxtClienteID().setText("");
                view.getTiquetesView().getTxtEstacionID().setText("");
                view.getTiquetesView().getTxtFecha().setText("");
                view.getTiquetesView().getCbTipoTiqueteID().setSelectedIndex(0);
        
                // Mensaje de éxito
                JOptionPane.showMessageDialog(view, "Tiquete agregado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Error al agregar el tiquete: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

          break;
          case "EditarTiquetesView":

            try {
                // Verificar selección en la tabla
                int filaSeleccionada = view.getTiquetesView().getTablaClientes().getSelectedRow();
                if (filaSeleccionada < 0) {
                    JOptionPane.showMessageDialog(view, "Por favor, seleccione un tiquete para editar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    break;
                }
        
                // Obtener ID y datos actualizados
                int id = Integer.parseInt(view.getTiquetesView().getModeloTablaClientes().getValueAt(filaSeleccionada, 0).toString());
                String clienteIdText = view.getTiquetesView().getTxtClienteID().getText().trim();
                String estacionIdText = view.getTiquetesView().getTxtEstacionID().getText().trim();
                String fecha = view.getTiquetesView().getTxtFecha().getText().trim();
                int tipoTiqueteSeleccionado = view.getTiquetesView().getCbTipoTiqueteID().getSelectedIndex() + 1;
        
                // Validaciones
                if (clienteIdText.isEmpty() || estacionIdText.isEmpty() || fecha.isEmpty()) {
                    throw new IllegalArgumentException("Por favor, complete todos los campos obligatorios.");
                }
        
                // Validar IDs numéricos
                int clienteId, estacionId;
                try {
                    clienteId = Integer.parseInt(clienteIdText);
                    estacionId = Integer.parseInt(estacionIdText);
                    if (clienteId <= 0 || estacionId <= 0) {
                        throw new IllegalArgumentException("Los IDs deben ser números positivos.");
                    }
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Por favor, ingrese valores numéricos válidos para los IDs.");
                }

                // Validar formato de la fecha
                try {
                    LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                } catch (DateTimeParseException ex) {
                    throw new IllegalArgumentException("El formato de la fecha es inválido. Use el formato yyyy-MM-dd.");
                }
        
                // Actualizar tiquete
                modelo.getTickets().actualizarTiquete(id, clienteId, estacionId, fecha, tipoTiqueteSeleccionado);
        
                // Actualizar tabla
                cargarTiquetesEnTabla();
        
                // Limpiar formulario
                view.getTiquetesView().getTxtClienteID().setText("");
                view.getTiquetesView().getTxtEstacionID().setText("");
                view.getTiquetesView().getTxtFecha().setText("");
                view.getTiquetesView().getCbTipoTiqueteID().setSelectedIndex(0);
        
                // Mensaje de éxito
                JOptionPane.showMessageDialog(view, "Tiquete actualizado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Error de validación", JOptionPane.WARNING_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Error al editar el tiquete: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
          break;
          case "EliminarTiquetesView":
          
            try {
                // Verificar selección en la tabla
                int filaSeleccionada = view.getTiquetesView().getTablaClientes().getSelectedRow();
                if (filaSeleccionada < 0) {
                    JOptionPane.showMessageDialog(view, "Por favor, seleccione un tiquete para eliminar.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    break;
                }
        
                // Confirmar eliminación
                int confirmacion = JOptionPane.showConfirmDialog(
                    view,
                    "¿Está seguro de que desea eliminar este tiquete?",
                    "Confirmación",
                    JOptionPane.YES_NO_OPTION
                );
        
                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Obtener ID del tiquete
                    int id = Integer.parseInt(view.getTiquetesView().getModeloTablaClientes().getValueAt(filaSeleccionada, 0).toString());
        
                    // Eliminar tiquete
                    modelo.getTickets().eliminarTiquete(id);
        
                    // Actualizar tabla
                    cargarTiquetesEnTabla();
        
                    // Mensaje de éxito
                    JOptionPane.showMessageDialog(view, "Tiquete eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Error al eliminar el tiquete: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
          break;
          case "IngresoTiquetesView":
          try {
            // Obtener cliente y atracción seleccionados
            int filaTiquete = view.getTiquetesView().getTablaTiquetes().getSelectedRow();
            int filaAtraccion = view.getTiquetesView().getTablaAtracciones().getSelectedRow();
    
            if (filaTiquete == -1 || filaAtraccion == -1) {
                JOptionPane.showMessageDialog(view, "Debe seleccionar un tiquete y una atracción.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Extraer datos de las tablas
            int tiqueteId = (int) view.getTiquetesView().getTablaTiquetes().getValueAt(filaTiquete, 0); // Columna del ID del tiquete
            int clienteId = (int) view.getTiquetesView().getTablaTiquetes().getValueAt(filaTiquete, 1); // Columna de cliente_id
    
            // Buscar al cliente por su ID en la base de datos
            Clientes modeloClientes = modelo.getClientes();
            String clienteData = (String) modeloClientes.buscarCliente(clienteId);
    
            if (clienteData == null || clienteData.equals("Cliente no encontrado.")) {
                JOptionPane.showMessageDialog(view, "Cliente no encontrado en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            // Parsear los datos del cliente
            String[] lineasCliente = clienteData.split("\n");
            if (lineasCliente.length < 6) {
                JOptionPane.showMessageDialog(view, "Los datos del cliente están incompletos o tienen un formato incorrecto.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
    
            String clienteNombre = lineasCliente[1].replace("Nombre: ", "").trim();
            double clienteEstatura = Double.parseDouble(lineasCliente[5].replace("Estatura: ", "").trim());
    
            // Extraer datos de la atracción seleccionada
            int atraccionId = (int) view.getTiquetesView().getTablaAtracciones().getValueAt(filaAtraccion, 0);
            String atraccionNombre = (String) view.getTiquetesView().getTablaAtracciones().getValueAt(filaAtraccion, 1);
            double estaturaMinima = (double) view.getTiquetesView().getTablaAtracciones().getValueAt(filaAtraccion, 4);
    
            
            // Validar la estatura
            if (clienteEstatura >= estaturaMinima) {
                JOptionPane.showMessageDialog(view, "El cliente " + clienteNombre + " puede ingresar a la atracción " + atraccionNombre + ".", "Acceso permitido", JOptionPane.INFORMATION_MESSAGE);
    
                // Registrar en la tabla intermedia
                modelo.getTickets().instarDatosIngresoAtracciones(tiqueteId, atraccionId);
            } else {
                JOptionPane.showMessageDialog(view, "El cliente " + clienteNombre + " no cumple con la estatura mínima para la atracción " + atraccionNombre + ".", "Acceso denegado", JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Error al validar el ingreso: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        break;

          default:

          break;
        }
    }

}
