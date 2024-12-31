package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ClientesView extends CustomizacionComponentes{

    private static final long serialVersionUID = 1L;

    // Componentes para el formulario
    private JTextField  txtNombre, txtNIdentificacion, txtTelefono, txtCorreo, txtEstatura, txtEdad, txtNombreFamiliar, txtTelefonoFamiliar;
    private JButton btnAgregar, btnEditar, btnEliminar;
    private JLabel lblAdvertencia;
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;

    public ClientesView() {
        setLayout(new BorderLayout());
        setBackground(primaryColor);


        // Banner superior
        add(crearBanner("Gestión de Clientes"), BorderLayout.NORTH);

        // Panel dividido
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);
        splitPane.setDividerSize(10);

        // Formulario y tabla
        splitPane.setLeftComponent(crearFormulario());
        splitPane.setRightComponent(crearTabla());

        add(splitPane, BorderLayout.CENTER);
    }



    public JPanel crearFormulario() {
        // Panel principal del formulario
        JPanel formularioCompletoPanel = new JPanel(new BorderLayout());
        formularioCompletoPanel.setBackground(secondaryColor);
    
        // Panel para el mensaje de advertencia
        JPanel advertenciaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        advertenciaPanel.setBackground(secondaryColor);
    
        lblAdvertencia = new JLabel();
        lblAdvertencia.setForeground(Color.RED);
        advertenciaPanel.add(lblAdvertencia);
    
        // Panel para los campos del formulario
        JPanel formularioPanel = new JPanel(new GridLayout(8, 2, 10, 10));
        formularioPanel.setBackground(secondaryColor);
        formularioPanel.setBorder(BorderFactory.createTitledBorder(null, "Formulario de Clientes",
                0, 0, labelFont, Color.BLACK));
    
        txtNombre = crearTextField();
        txtNombre.setBackground(secondaryColor);
        txtNIdentificacion = crearTextField();
        txtNIdentificacion.setBackground(secondaryColor);
        txtTelefono = crearTextField();
        txtTelefono.setBackground(secondaryColor);
        txtCorreo = crearTextField();
        txtCorreo.setBackground(secondaryColor);
        txtEstatura = crearTextField();
        txtEstatura.setBackground(secondaryColor);
        txtEdad = crearTextField();
        txtEdad.setBackground(secondaryColor);
        txtNombreFamiliar = crearTextField();
        txtNombreFamiliar.setBackground(secondaryColor);
        txtTelefonoFamiliar = crearTextField();
        txtTelefonoFamiliar.setBackground(secondaryColor);
    
        formularioPanel.add(crearLabel("Nombre:"));
        formularioPanel.add(txtNombre);
        formularioPanel.add(crearLabel("N Identificación:"));
        formularioPanel.add(txtNIdentificacion);
        formularioPanel.add(crearLabel("Teléfono:"));
        formularioPanel.add(txtTelefono);
        formularioPanel.add(crearLabel("Correo:"));
        formularioPanel.add(txtCorreo);
        formularioPanel.add(crearLabel("Estatura:"));
        formularioPanel.add(txtEstatura);
        formularioPanel.add(crearLabel("Edad:"));
        formularioPanel.add(txtEdad);
        formularioPanel.add(crearLabel("Nombre Familiar:"));
        formularioPanel.add(txtNombreFamiliar);
        formularioPanel.add(crearLabel("Teléfono Familiar:"));
        formularioPanel.add(txtTelefonoFamiliar);
    
        // Panel para los botones
        JPanel botonesPanel = new JPanel(new FlowLayout());
        botonesPanel.setBackground(primaryColor);
    
        btnAgregar = crearBoton("Agregar");
        btnEditar = crearBoton("Editar");
        btnEliminar = crearBoton("Eliminar");
    
        botonesPanel.add(btnAgregar);
        botonesPanel.add(btnEditar);
        botonesPanel.add(btnEliminar);
    
        // Agregar paneles al panel principal del formulario
        formularioCompletoPanel.add(advertenciaPanel, BorderLayout.NORTH);
        formularioCompletoPanel.add(formularioPanel, BorderLayout.CENTER);
        formularioCompletoPanel.add(botonesPanel, BorderLayout.SOUTH);
    
        return formularioCompletoPanel;
    }

    private JScrollPane crearTabla() {
        String[] columnas = {"ID", "Nombre", "N Identificación", "Teléfono", "Correo", "Estatura", "Edad", "Nombre Familiar", "Teléfono Familiar", "Visitas"};
    
        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas son no editables
            }
        };
    
        tablaClientes = new JTable(modeloTabla);
        tablaClientes.setFillsViewportHeight(true);
        tablaClientes.setFont(textFieldFont);
        tablaClientes.getTableHeader().setFont(labelFont);
        tablaClientes.getTableHeader().setBackground(primaryColor);
        tablaClientes.getTableHeader().setForeground(Color.WHITE);
    
        return new JScrollPane(tablaClientes);
    }
    

    // Getters para los controladores


    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtNIdentificacion() {
        return txtNIdentificacion;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JTextField getTxtEstatura() {
        return txtEstatura;
    }

    public JTextField getTxtEdad() {
        return txtEdad;
    }

    public JTextField getTxtNombreFamiliar() {
        return txtNombreFamiliar;
    }

    public JTextField getTxtTelefonoFamiliar() {
        return txtTelefonoFamiliar;
    }

    public JLabel getLblAdvertencia() {
        return lblAdvertencia;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JTable getTablaClientes() {
        return tablaClientes;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }
}
