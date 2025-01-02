package model;


public class SalitreMagicoFacade {

    private Clientes clientes;
    private Empleados empleados;

    public SalitreMagicoFacade() {
        clientes = new Clientes();
        empleados = new Empleados();
    }


    public Clientes getClientes() {
        return clientes;
    }

    public Empleados getEmpleados() {
        return empleados;
    }
}
