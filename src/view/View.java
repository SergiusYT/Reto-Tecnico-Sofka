package view;


import java.awt.*;
import javax.swing.*;


public class View extends JFrame{

    private static final long serialVersionUID = 1L;
	private JPanel cardPanel;
    private CardLayout cardLayout;
    private PrincipalView principalView;
    private ClientesView clientesView;
    private EmpleadosView empleadosView;
    private AtraccionesView atraccionesView;
    private TiquetesView tiquetesView;
    private ReportesView reportesView;

    public View() {
		//------------------- propiedades y caracteristica que contendra el JFrame -----------------------------------
		
        setSize(600, 400); // tamaño del jframe
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Establece el JFrame en modo pantalla completa
        setResizable(false); // Evita que el usuario redimensione la ventana
        setTitle("Salitre Magico"); // Establece el titulo de la ventana
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


       //----------------------Inicializacion de objetos ------------------------------------------------------------

       principalView = new PrincipalView();
       clientesView = new ClientesView();
       empleadosView = new EmpleadosView();
       atraccionesView = new AtraccionesView();
       tiquetesView = new TiquetesView();
       reportesView = new ReportesView();




        //------------------------ Actualizacion de paneles en el mismo JFrame----------------------------------------
                
        cardPanel = new JPanel();
        cardLayout = new CardLayout(); // esto me permitira cambiar los paneles cuando necesite de manera mas comoda 
        cardPanel.setLayout(cardLayout);


        cardPanel.add(principalView, "PrincipalView");
        cardPanel.add(clientesView, "ClientesView");
        cardPanel.add(empleadosView, "EmpleadosView");
        cardPanel.add(atraccionesView, "AtraccionesView");
        cardPanel.add(tiquetesView, "TiquetesView");
        cardPanel.add(reportesView, "ReportesView");
        add(cardPanel);
    }


    public PrincipalView getPrincipalView() {
        return principalView;
    }

    public ClientesView getClientesView() {
        return clientesView;
    }

    public EmpleadosView getEmpleadosView() {
        return empleadosView;
    }

    public AtraccionesView getAtraccionesView() {
        return atraccionesView;
    }

    public TiquetesView getTiquetesView() {
        return tiquetesView;
    }

    public void setPrincipalView(){
        cardLayout.show(cardPanel, "PrincipalView");
    }

    public void setClientesView(){
        cardLayout.show(cardPanel, "ClientesView");
    }

    public void setEmpleadosView(){
        cardLayout.show(cardPanel, "EmpleadosView");
    }

    public void setAtraccionesView(){
        cardLayout.show(cardPanel, "AtraccionesView");
    }   

    public void setTiquetesView(){
        cardLayout.show(cardPanel, "TiquetesView");
    }

    public void setReportesView(){
        cardLayout.show(cardPanel, "ReportesView");
    }
}
