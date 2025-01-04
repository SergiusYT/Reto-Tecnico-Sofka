package view;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class TiquetesView extends CustomizacionComponentes {

        private static final long serialVersionUID = 1L;

    // Componentes para el formulario
    private JTextField txtClienteID, txtEstacionID, txtFecha;
    private JComboBox<String> cbTipoTiqueteID;
    private JButton btnAgregar, btnEditar, btnEliminar, btnIngreso;
    private JTable tablaTiquetes, tablaClientes, tablaAtracciones;
    private DefaultTableModel modeloTablaTiquetes, modeloTablaClientes, modeloTablaAtracciones;

    public TiquetesView() {
        setLayout(new BorderLayout());
        setBackground(primaryColor);

        // Banner superior
        add(crearBanner("Gestión de Tiquetes"), BorderLayout.NORTH);

        // Panel dividido
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);
        splitPane.setDividerSize(10);

        // Formulario y tablas
        splitPane.setLeftComponent(crearFormulario());
        splitPane.setRightComponent(crearTablas());

        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel crearFormulario() {
        JPanel formularioPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formularioPanel.setBackground(secondaryColor);
        formularioPanel.setBorder(BorderFactory.createTitledBorder(null, "Formulario de Clientes", 0, 0, labelFont, Color.BLACK));

        txtClienteID = crearTextField();
        txtClienteID.setBackground(secondaryColor);
        txtEstacionID = crearTextField();
        txtEstacionID.setBackground(secondaryColor);
        txtFecha = crearTextField();
        txtFecha.setBackground(secondaryColor);
        txtFecha.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        cbTipoTiqueteID = new JComboBox<>();
        cbTipoTiqueteID.setFont(textFieldFont);

        formularioPanel.add(crearLabel("Cliente ID:"));
        formularioPanel.add(txtClienteID);
        formularioPanel.add(crearLabel("Estación ID:"));
        formularioPanel.add(txtEstacionID);
        formularioPanel.add(crearLabel("Fecha:"));
        formularioPanel.add(txtFecha);
        formularioPanel.add(crearLabel("Tipo Tiquete ID:"));
        formularioPanel.add(cbTipoTiqueteID);

        JPanel botonesPanel = new JPanel(new FlowLayout());
        botonesPanel.setBackground(primaryColor);

        btnAgregar = crearBoton("Agregar");
        btnEditar = crearBoton("Editar");
        btnEliminar = crearBoton("Eliminar");
        btnIngreso = crearBoton("Ingreso"); 

        botonesPanel.add(btnAgregar);
        botonesPanel.add(btnEditar);
        botonesPanel.add(btnEliminar);
        botonesPanel.add(btnIngreso); 

        JPanel formularioCompletoPanel = new JPanel(new BorderLayout());
        formularioCompletoPanel.setBackground(secondaryColor);
        formularioCompletoPanel.add(formularioPanel, BorderLayout.CENTER);
        formularioCompletoPanel.add(botonesPanel, BorderLayout.SOUTH);

        return formularioCompletoPanel;
    }

    private JPanel crearTablas() {
        JPanel tablasPanel = new JPanel(new GridLayout(3, 1));
        tablasPanel.add(crearTablaTiquetes());
        tablasPanel.add(crearTablaClientes());
        tablasPanel.add(crearTablaAtracciones());
        return tablasPanel;
    }

    private JScrollPane crearTablaClientes() {
        String[] columnas = {"ID", "Nombre", "N Identificación", "Teléfono", "Correo", "Estatura", "Edad", "Nombre Familiar", "Teléfono Familiar", "Visitas"};
        modeloTablaClientes = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer todas las celdas no editables
            }
        };

        tablaClientes = new JTable(modeloTablaClientes);
        tablaClientes.setFillsViewportHeight(true);
        tablaClientes.setFont(textFieldFont);
        tablaClientes.getTableHeader().setFont(labelFont);
        tablaClientes.getTableHeader().setBackground(primaryColor);
        tablaClientes.getTableHeader().setForeground(Color.WHITE);

        return new JScrollPane(tablaClientes);
    }

    private JScrollPane crearTablaAtracciones() {
        String[] columnas = {"ID", "Nombre", "Descripción", "Clasificación", "Estatura Mínima", "Condiciones", "Estado", "Empleado"};
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

    private JScrollPane crearTablaTiquetes() {
        String[] columnas = {"ID", "Cliente ID", "Estación ID", "Fecha", "Tipo Tiquete ID"};
         modeloTablaTiquetes = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hacer todas las celdas no editables
            }
        };
    
        tablaTiquetes = new JTable(modeloTablaTiquetes);
        tablaTiquetes.setFillsViewportHeight(true);
        tablaTiquetes.setFont(textFieldFont);
        tablaTiquetes.getTableHeader().setFont(labelFont);
        tablaTiquetes.getTableHeader().setBackground(primaryColor);
        tablaTiquetes.getTableHeader().setForeground(Color.WHITE);
    
        return new JScrollPane(tablaTiquetes);
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

    public JButton getBtnIngreso() {
        return btnIngreso;
    }

    public JTextField getTxtClienteID() {
        return txtClienteID;
    }

    public JTextField getTxtEstacionID() {
        return txtEstacionID;
    }

    public JTextField getTxtFecha() {
        return txtFecha;
    }

    public JComboBox<String> getCbTipoTiqueteID() {
        return cbTipoTiqueteID;
    }

    public void setCbTipoTiqueteID(java.util.List<Object[]> cbTipoTiqueteID) {
        for (Object[] tipoTiquete : cbTipoTiqueteID) {
            this.cbTipoTiqueteID.addItem(tipoTiquete[0].toString());
        }
    }

    public JTable getTablaTiquetes() {
        return tablaTiquetes;
    }

    public JTable getTablaClientes() {
        return tablaClientes;
    }

    public JTable getTablaAtracciones() {
        return tablaAtracciones;
    }

    public DefaultTableModel getModeloTablaTiquetes() {
        return modeloTablaTiquetes;
    }

    public DefaultTableModel getModeloTablaClientes() {
        return modeloTablaClientes;
    }

    public DefaultTableModel getModeloTablaAtracciones() {
        return modeloTablaAtracciones;
    }

    public JButton getBotonHome() {
        return botonHome;
    }
}
