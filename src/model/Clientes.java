package model;

import java.sql.SQLException;
import java.util.List;

public class Clientes {

    private ClientesDAO clienteDAO;

    public Clientes() {

        clienteDAO = new ClientesDAO();
    }     


    // ------------------ METODOS CRUD DEL DAO
    public void agregarCliente(String nombre, String numeroIdentificacion, String telefono, String correo,
                                double estatura, int edad, String nombreFamiliar, String telefonoFamiliar, int visitas) throws SQLException {
        clienteDAO.create(nombre, numeroIdentificacion, telefono, correo, estatura, edad, nombreFamiliar, telefonoFamiliar, visitas);
    }

    public Object buscarCliente(int id) throws SQLException {
        return clienteDAO.read(id);
    }

    public List<Object[]> listarClientes() throws SQLException {
        return clienteDAO.readAll();
    }
    

    public void actualizarCliente(int id, String nombre, String numeroIdentificacion, String telefono, String correo,
                                   double estatura, int edad, String nombreFamiliar, String telefonoFamiliar, int visitas) throws SQLException {
        clienteDAO.update(id, nombre, numeroIdentificacion, telefono, correo, estatura, edad, nombreFamiliar, telefonoFamiliar, visitas);
    }

    public void eliminarCliente(int id) throws SQLException {
        clienteDAO.delete(id);
    }

    public void incrementarVisitas(int clienteId) throws SQLException {
        clienteDAO.incrementarVisitas(clienteId);
    }
    
}

