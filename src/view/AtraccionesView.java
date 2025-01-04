package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class AtraccionesView extends CustomizacionComponentes {

        private static final long serialVersionUID = 1L;

    // Componentes para el formulario de atracciones
    private JTextField txtNombre, txtDescripcion, txtEstaturaMinima, txtCondicionesUso, txtEmpleado;
    private JComboBox<String> cbClasificacion, cbEstado;
    private JButton btnAgregar, btnEditar, btnEliminar;
    private JTable tablaAtracciones, tablaEmpleados;
    private DefaultTableModel modeloTablaAtracciones, modeloTablaEmpleados;

    public AtraccionesView() {
        setLayout(new BorderLayout());
        setBackground(primaryColor);

        // Banner superior
        add(crearBanner("Gestión de Atracciones"), BorderLayout.NORTH);

        // Panel dividido
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);
        splitPane.setDividerSize(10);

        // Formulario y tabla
        splitPane.setLeftComponent(crearFormulario());
        splitPane.setRightComponent(crearTablas());

        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel crearFormulario() {
        JPanel formularioPanel = new JPanel(new GridLayout(7, 2, 10, 10));
        formularioPanel.setBackground(secondaryColor);
        formularioPanel.setBorder(BorderFactory.createTitledBorder(null,"Formulario de Atracciones", 0, 0, labelFont, Color.BLACK));

        txtNombre = crearTextField();
        txtNombre.setBackground(secondaryColor);
        txtDescripcion = crearTextField();
        txtDescripcion.setBackground(secondaryColor);
        cbClasificacion = new JComboBox<>();
        txtEstaturaMinima = crearTextField();
        txtEstaturaMinima.setBackground(secondaryColor);
        txtCondicionesUso = crearTextField();
        txtCondicionesUso.setBackground(secondaryColor);
        cbClasificacion = new JComboBox<>();
        cbEstado = new JComboBox<>(new String[]{"Disponible", "No Disponible", "Mantenimiento"});
        txtEmpleado =  crearTextField();
        txtEmpleado.setBackground(secondaryColor);
        txtEmpleado.setEnabled(false); // Deshabilitar campo
        txtEmpleado.setDisabledTextColor(Color.BLACK); 

        formularioPanel.add(crearLabel("Nombre:"));
        formularioPanel.add(txtNombre);
        formularioPanel.add(crearLabel("Descripción:"));
        formularioPanel.add(txtDescripcion);
        formularioPanel.add(crearLabel("Clasificación:"));
        formularioPanel.add(cbClasificacion);
        formularioPanel.add(crearLabel("Estatura mínima:"));
        formularioPanel.add(txtEstaturaMinima);
        formularioPanel.add(crearLabel("Condiciones de uso:"));
        formularioPanel.add(txtCondicionesUso);
        formularioPanel.add(crearLabel("Estado:"));
        formularioPanel.add(cbEstado);
        formularioPanel.add(crearLabel("Empleado Asignado:"));
        formularioPanel.add(txtEmpleado);


        JPanel botonesPanel = new JPanel(new FlowLayout());
        botonesPanel.setBackground(primaryColor);

        btnAgregar = crearBoton("Agregar");
        btnEditar = crearBoton("Editar");
        btnEliminar = crearBoton("Eliminar");

        botonesPanel.add(btnAgregar);
        botonesPanel.add(btnEditar);
        botonesPanel.add(btnEliminar);

        JPanel formularioCompletoPanel = new JPanel(new BorderLayout());
        formularioCompletoPanel.setBackground(secondaryColor);
        formularioCompletoPanel.add(formularioPanel, BorderLayout.CENTER);
        formularioCompletoPanel.add(botonesPanel, BorderLayout.SOUTH);

        return formularioCompletoPanel;
    }

    private JPanel crearTablas() {
        JPanel tablasPanel = new JPanel(new GridLayout(2, 1));
        tablasPanel.add(crearTablaAtracciones());
        tablasPanel.add(crearTablaEmpleados());
        return tablasPanel;
    }

    private JScrollPane crearTablaAtracciones() {
        String[] columnas = {"ID", "Nombre", "Descripción", "Clasificación", "Estatura Mínima", "Condiciones", "Estado",  "Empleado"};
        modeloTablaAtracciones = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer todas las celdas no editables
            }
        };
        
        tablaAtracciones = new JTable(modeloTablaAtracciones);
        tablaAtracciones.setFillsViewportHeight(true);
        tablaAtracciones.setFont(textFieldFont);
        tablaAtracciones.getTableHeader().setFont(labelFont);
        tablaAtracciones.getTableHeader().setBackground(primaryColor);
        tablaAtracciones.getTableHeader().setForeground(Color.WHITE);
    
        return new JScrollPane(tablaAtracciones);
    }
    
    private JScrollPane crearTablaEmpleados() {
        String[] columnas = {"ID", "Nombre", "Cédula", "Teléfono", "Correo", "Rol", "Horario Laboral"};
        modeloTablaEmpleados = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer todas las celdas no editables
            }
        };
    
        tablaEmpleados = new JTable(modeloTablaEmpleados);
        tablaEmpleados.setFillsViewportHeight(true);
        tablaEmpleados.setFont(textFieldFont);
        tablaEmpleados.getTableHeader().setFont(labelFont);
        tablaEmpleados.getTableHeader().setBackground(primaryColor);
        tablaEmpleados.getTableHeader().setForeground(Color.WHITE);
    
        return new JScrollPane(tablaEmpleados);
    }
    

    // Métodos Getters y Setters
    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtDescripcion() {
        return txtDescripcion;
    }

    public JTextField getTxtEstaturaMinima() {
        return txtEstaturaMinima;
    }

    public JComboBox<String> getCbClasificacion() {
        return cbClasificacion;
    }

    public void setCbClasificacion(java.util.List<Object[]> cbClasificacion) {
        for (Object[] clasificacion : cbClasificacion) {
            this.cbClasificacion.addItem(clasificacion[0].toString());
        }
    }

    public JComboBox<String> getCbEstado() {
        return cbEstado;
    }

    public void setCbEstado(java.util.List<Object[]> cbEstado) {
        for (Object[] estado : cbEstado) {
            this.cbEstado.addItem(estado[0].toString());
        }
    }

    public JTextField getTxtEmpleado() {
        return txtEmpleado;
    }

    public JTextField getTxtCondicionesUso() {
        return txtCondicionesUso;
    }

    public JTable getTablaAtracciones() {
        return tablaAtracciones;
    }

    public JTable getTablaEmpleados() {
        return tablaEmpleados;
    }

    public DefaultTableModel getModeloTablaAtracciones() {
        return modeloTablaAtracciones;
    }

    public DefaultTableModel getModeloTablaEmpleados() {
        return modeloTablaEmpleados;
    }

    public void setModeloTablaAtracciones(DefaultTableModel modeloTablaAtracciones) {
        this.modeloTablaAtracciones = modeloTablaAtracciones;
    }

    public void setModeloTablaEmpleados(DefaultTableModel modeloTablaEmpleados) {
        this.modeloTablaEmpleados = modeloTablaEmpleados;
    }

    public JButton getBotonHome() {
        return botonHome;
    }

}
