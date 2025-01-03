package model;


public class SalitreMagicoFacade {

    private Clientes clientes;
    private Empleados empleados;
    private Atracciones atracciones;

    public SalitreMagicoFacade() {
        clientes = new Clientes();
        empleados = new Empleados();
        atracciones = new Atracciones();
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

}
