package model;


public class SalitreMagicoFacade {

    private Clientes clientes;
    private Empleados empleados;
    private Atracciones atracciones;
    private Tiquetes tickets;

    public SalitreMagicoFacade() {
        clientes = new Clientes();
        empleados = new Empleados();
        atracciones = new Atracciones();
        tickets = new Tiquetes();
    }


    public Clientes getClientes() {
        return clientes;
    }

    public Empleados getEmpleados() {
        return empleados;
    }

    public Atracciones getAtracciones() {
        return atracciones;
    }

    public Tiquetes getTickets() {
        return tickets;
    }

}
