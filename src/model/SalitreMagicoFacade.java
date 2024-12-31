package model;


public class SalitreMagicoFacade {

    private Clientes clientes;

    public SalitreMagicoFacade() {
        clientes = new Clientes();
    }


    public Clientes getClientes() {
        return clientes;
    }
}
