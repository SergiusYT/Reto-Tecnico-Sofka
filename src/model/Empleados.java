package model;

import java.sql.SQLException;
import java.util.List;

public class Empleados {

    private EmpleadosDAO empleadoDAO;

    public Empleados() {
        empleadoDAO = new EmpleadosDAO();
    }

    public void agregarEmpleado(String nombre, String numeroIdentificacion, String telefono, String correo, int rolId, String horario) throws SQLException {
        empleadoDAO.create(nombre, numeroIdentificacion, telefono, correo, rolId, horario);
    }

    public Object buscarEmpleado(int id) throws SQLException {
        return empleadoDAO.read(id);
    }

    public List<Object[]> listarEmpleados() throws SQLException {
        return empleadoDAO.readAll();
    }

    public void actualizarEmpleado(int id, String nombre, String numeroIdentificacion, String telefono, String correo, int rolId, String horario) throws SQLException {
        empleadoDAO.update(id, nombre, numeroIdentificacion, telefono, correo, rolId, horario);
    }

    public void eliminarEmpleado(int id) throws SQLException {
        empleadoDAO.delete(id);
    }

    public List<String> obtenerRoles() throws SQLException {
        return empleadoDAO.getRoles();
    }
}
