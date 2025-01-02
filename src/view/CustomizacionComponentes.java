package view;

import java.awt.*;
import javax.swing.*;


public abstract class CustomizacionComponentes extends JPanel {

    protected Font labelFont = new Font("Arial", Font.BOLD, 18);
    protected Font textFieldFont = new Font("Arial", Font.PLAIN, 14);
    protected Font buttonFont = new Font("Arial", Font.BOLD, 14);
    protected Color primaryColor = Color.decode("#7e4cea");
    protected Color secondaryColor = Color.decode("#80ff81");

    protected JButton botonHome;



    // Método para crear banners estilizados
    protected JPanel crearBanner(String texto) {
        JPanel bannerPanel = new JPanel(new BorderLayout());
        bannerPanel.setBackground(primaryColor);

        // Cargar la imagen desde la carpeta "resources"
        String rutaImagen = "resources/img/logo.png";
        ImageIcon icono = new ImageIcon(rutaImagen);
        JLabel imagenLabel = new JLabel(icono);
        bannerPanel.add(imagenLabel, BorderLayout.WEST); // Logo en el lado izquierdo
    
        JLabel bannerLabel = new JLabel("<html><h1 style='color:white;'>" + texto + "</h1></html>");
        bannerLabel.setHorizontalAlignment(SwingConstants.CENTER);
    
        bannerPanel.add(bannerLabel, BorderLayout.CENTER);

        // Botón con solo imagen (en el lado derecho)
        JButton botonImagen = crearBotonHome("resources/icon/Principal.png");
        bannerPanel.add(botonImagen, BorderLayout.EAST);        
        return bannerPanel;
    }
    

    // Método para crear etiquetas estilizadas
    protected JLabel crearLabel(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(labelFont);
        label.setForeground(primaryColor);
        return label;
    }

    // Método para crear botones estilizados
    protected JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(primaryColor);
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setFont(buttonFont);
        boton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(Color.BLACK);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(primaryColor);
            }
        });
        return boton;
    }

    // Método para crear TextFields estilizados
    protected JTextField crearTextField() {
        JTextField textField = new JTextField();
        textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, primaryColor));
        textField.setFont(textFieldFont);
        textField.setForeground(Color.BLACK);
        textField.setCaretColor(primaryColor);
        textField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#9c6cf0")));
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                textField.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, primaryColor));
            }
        });
        return textField;
    }


    // Método para crear un botón con solo imagen
    protected JButton crearBotonHome(String rutaImagen) {
        botonHome = new JButton();
        botonHome.setFocusPainted(false); // Eliminar bordes al enfocar
        botonHome.setBorderPainted(false); // Quitar el borde del botón
        botonHome.setContentAreaFilled(false); // Hacer el fondo transparente

    // Cargar y escalar la imagen
    try {
        ImageIcon icono = new ImageIcon(rutaImagen);
        Image imagenEscalada = icono.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        botonHome.setIcon(new ImageIcon(imagenEscalada));
    } catch (Exception e) {
        System.err.println("Error al cargar la imagen: " + rutaImagen);
        e.printStackTrace();
    }

    return botonHome;
}

    protected  JButton getBotonHome() {
        return botonHome;
    }
}
