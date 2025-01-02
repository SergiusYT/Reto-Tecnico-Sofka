package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.persistence.ConexionBd;
import model.persistence.EmpleadosDTO;

public class EmpleadosDAO {

        public void create(String nombre, String cedula, String telefono, String correo, int rolId, String horario) throws SQLException {
        EmpleadosDTO empleado = new EmpleadosDTO();
        empleado.setNombre(nombre);
        empleado.setCedula(cedula);
        empleado.setTelefono(telefono);
        empleado.setCorreo(correo);
        empleado.setRolId(rolId);
        empleado.setHorarioLaboral(horario);

        String sql = "INSERT INTO empleados (nombre, cedula, telefono, correo, rol_id, horario_laboral) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getCedula());
            ps.setString(3, empleado.getTelefono());
            ps.setString(4, empleado.getCorreo());
            ps.setInt(5, empleado.getRolId());
            ps.setString(6, empleado.getHorarioLaboral());
            ps.executeUpdate();
        }
    }

    public String read(int id) throws SQLException {
        String sql = "SELECT * FROM empleados WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    EmpleadosDTO empleado = new EmpleadosDTO();
                    empleado.setId(rs.getInt("id"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setCedula(rs.getString("cedula"));
                    empleado.setTelefono(rs.getString("telefono"));
                    empleado.setCorreo(rs.getString("correo"));
                    empleado.setRolId(rs.getInt("rol_id"));
                    empleado.setHorarioLaboral("horario_laboral");
                    return empleado.toString();
                }
            }
        }
        return "Empleado no encontrado.";
    }

    public List<Object[]> readAll() throws SQLException {
        String sql = "SELECT * FROM empleados";
        List<Object[]> empleados = new ArrayList<>();
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] empleado = new Object[] {
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("cedula"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getInt("rol_id"),
                    rs.getString("horario_laboral")
                };
                empleados.add(empleado);
            }
        }
        return empleados;
    }

    public void update(int id, String nombre, String cedula, String telefono, String correo, int rolId, String horario) throws SQLException {
        String sql = "UPDATE empleados SET nombre = ?, cedula = ?, telefono = ?, correo = ?, rol_id = ?, horario_laboral = ? WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, cedula);
            ps.setString(3, telefono);
            ps.setString(4, correo);
            ps.setInt(5, rolId);
            ps.setString(6, horario);
            ps.setInt(7, id);
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM empleados WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }


    public List<String> getRoles() throws SQLException {
        List<String> roles = new ArrayList<>();
        String sql = "SELECT nombre FROM roles_empleados"; // Asume que tienes una tabla `roles` con columna `nombre`.

        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                roles.add(rs.getString("nombre"));
            }
        }
        return roles;
    }
}
