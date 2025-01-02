package view;

import java.awt.*;
import javax.swing.*;


public class PrincipalView extends CustomizacionComponentes{

    JButton botonClientes, botonEmpleados, botonAtracciones, botonTickets, botonReportes, botonSalir;

    public PrincipalView() {
        // Configuración del layout principal
        setLayout(new BorderLayout());

        // Banner superior
        JPanel banner = crearBanner("Panel Principal");
        add(banner, BorderLayout.NORTH);

        // Panel central para los botones
        JPanel panelBotones = new JPanel(new GridLayout(2, 3, 20, 20));
        panelBotones.setBackground(secondaryColor);
        panelBotones.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Creación de botones con nombres e íconos
        botonClientes = crearBotonConIcono("Gestión de Clientes", "Clientes");
        botonEmpleados = crearBotonConIcono("Gestión de Empleados", "Empleados");
        botonAtracciones = crearBotonConIcono("Gestión de Atracciones", "Atracciones");
        botonTickets = crearBotonConIcono("Gestión de Tickets", "Tickets");
        botonReportes = crearBotonConIcono("Reportes", "Reportes");
        botonSalir = crearBotonConIcono("Salir", "Salir");
        

        // Añadir botones al panel
        panelBotones.add(botonClientes);
        panelBotones.add(botonEmpleados);
        panelBotones.add(botonAtracciones);
        panelBotones.add(botonTickets);
        panelBotones.add(botonReportes);
        panelBotones.add(botonSalir);

        add(panelBotones, BorderLayout.CENTER);
    }


    private JButton crearBotonConIcono(String texto, String nombreIcono) {
        JButton boton = crearBoton(texto);
        boton.setFocusPainted(false); // Opcional: eliminar el borde al enfocar
        boton.setFont(new Font("Arial", Font.ITALIC, 24)); // Ajustar la fuente del texto
        boton.setHorizontalTextPosition(SwingConstants.CENTER);
        boton.setVerticalTextPosition(SwingConstants.BOTTOM);
        
        // Ruta al ícono PNG
        String rutaIcono = "resources/icon/" + nombreIcono + ".png";
        try {
            // Cargar y escalar la imagen
            ImageIcon icono = new ImageIcon(rutaIcono);
            Image imagenEscalada = icono.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
            boton.setIcon(new ImageIcon(imagenEscalada));
        } catch (Exception e) {
            System.err.println("Error al cargar el ícono: " + rutaIcono);
            e.printStackTrace();
        }
    
        return boton;
    }


    public JButton getBotonClientes(){
        return botonClientes;
    }
    
    public JButton getBotonEmpleados(){
        return botonEmpleados;
    }
    
    public JButton getBotonAtracciones(){
        return botonAtracciones;
    }
    
    public JButton getBotonTickets(){
        return botonTickets;
    }
    
    public JButton getBotonReportes(){
        return botonReportes;
    }
    
    public JButton getBotonSalir(){
        return botonSalir;
    }

    public JButton getBotonHome() {
        return botonHome;
    }
    
}
