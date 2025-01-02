package view;

import java.awt.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;


public class EmpleadosView extends CustomizacionComponentes {

    private static final long serialVersionUID = 1L;

    // Componentes para el formulario
    private JTextField txtNombre, txtCedula, txtTelefono, txtCorreo;
    private JComboBox<String> cbRol;
    private JFormattedTextField txtHorarioLaboral;
    private JButton btnAgregar, btnEditar, btnEliminar;
    private JLabel lblAdvertencia;
    private JTable tablaEmpleados;
    private DefaultTableModel modeloTabla;

    public EmpleadosView() {
        setLayout(new BorderLayout());
        setBackground(primaryColor);

        // Banner superior
        add(crearBanner("Gestión de Empleados"), BorderLayout.NORTH);

        // Panel dividido
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(400);
        splitPane.setDividerSize(10);

        // Formulario y tabla
        splitPane.setLeftComponent(crearFormulario());
        splitPane.setRightComponent(crearTabla());

        add(splitPane, BorderLayout.CENTER);
    }

    private JPanel crearFormulario() {
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
        JPanel formularioPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        formularioPanel.setBackground(secondaryColor);
        formularioPanel.setBorder(BorderFactory.createTitledBorder(null, "Formulario de Empleados",
                0, 0, labelFont, Color.BLACK));

        txtNombre = crearTextField();
        txtNombre.setBackground(secondaryColor);
        txtCedula = crearTextField();
        txtCedula.setBackground(secondaryColor);
        txtTelefono = crearTextField();
        txtTelefono.setBackground(secondaryColor);
        txtCorreo = crearTextField();
        txtCorreo.setBackground(secondaryColor);

        cbRol = new JComboBox<>();
        cbRol.setFont(textFieldFont);
        cbRol.setBackground(Color.WHITE);

        try {
            MaskFormatter horarioFormatter = new MaskFormatter("##:## - ##:##");
            horarioFormatter.setPlaceholderCharacter('_');
            txtHorarioLaboral = new JFormattedTextField(horarioFormatter);
            txtHorarioLaboral.setFont(textFieldFont);
            txtHorarioLaboral.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, primaryColor));
            txtHorarioLaboral.setCaretColor(primaryColor);
        } catch (ParseException e) {
            txtHorarioLaboral = new JFormattedTextField();
            e.printStackTrace();
        }

        formularioPanel.add(crearLabel("Nombre:"));
        formularioPanel.add(txtNombre);
        formularioPanel.add(crearLabel("Cédula:"));
        formularioPanel.add(txtCedula);
        formularioPanel.add(crearLabel("Teléfono:"));
        formularioPanel.add(txtTelefono);
        formularioPanel.add(crearLabel("Correo:"));
        formularioPanel.add(txtCorreo);
        formularioPanel.add(crearLabel("Rol:"));
        formularioPanel.add(cbRol);
        formularioPanel.add(crearLabel("Horario Laboral:"));
        formularioPanel.add(txtHorarioLaboral);

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
        String[] columnas = {"ID", "Nombre", "Cédula", "Teléfono", "Correo", "Rol", "Horario Laboral"};

        modeloTabla = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Todas las celdas son no editables
            }
        };

        tablaEmpleados = new JTable(modeloTabla);
        tablaEmpleados.setFillsViewportHeight(true);
        tablaEmpleados.setFont(textFieldFont);
        tablaEmpleados.getTableHeader().setFont(labelFont);
        tablaEmpleados.getTableHeader().setBackground(primaryColor);
        tablaEmpleados.getTableHeader().setForeground(Color.WHITE);

        return new JScrollPane(tablaEmpleados);
    }

    // Getters para los controladores
    public JTextField getTxtNombre() {
        return txtNombre;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }

    public JTextField getTxtCorreo() {
        return txtCorreo;
    }

    public JComboBox<String> getCbRol() {
        return cbRol;
    }

    public void setCbRol(java.util.List<Object[]> cbRol) {
        for (Object[] rol : cbRol) {
            this.cbRol.addItem(rol[0].toString());
        }
    }

    public JFormattedTextField getTxtHorarioLaboral() {
        return txtHorarioLaboral;
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

    public JTable getTablaEmpleados() {
        return tablaEmpleados;
    }

    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

    public JButton getBotonHome() {
        return botonHome;
    }
}
