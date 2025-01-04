package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.persistence.AtraccionesDTO;
import model.persistence.ConexionBd;

public class AtraccionesDAO {

        // Create
    public void create(String nombre, String descripcion, int clasificacionId, Double estaturaMinima, String condicionesUso, String estado, int empleadoId) throws SQLException {
        AtraccionesDTO atraccion = new AtraccionesDTO();
        atraccion.setNombre(nombre);
        atraccion.setDescripcion(descripcion);
        atraccion.setClasificacionId(clasificacionId);
        atraccion.setEstaturaMinima(estaturaMinima);
        atraccion.setCondicionesUso(condicionesUso);
        atraccion.setEstado(estado);
        atraccion.setEmpleadoId(empleadoId);

        String sql = "INSERT INTO atracciones (nombre, descripcion, clasificacion_id, estatura_minima, condiciones_uso, estado, empleado_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, atraccion.getNombre());
            ps.setString(2, atraccion.getDescripcion());
            ps.setInt(3, atraccion.getClasificacionId());
            ps.setObject(4, atraccion.getEstaturaMinima());
            ps.setString(5, atraccion.getCondicionesUso());
            ps.setString(6, atraccion.getEstado());
            ps.setInt(7, atraccion.getEmpleadoId());
            ps.executeUpdate();
        }
    }

    // Read by ID
    public String read(int id) throws SQLException {
        String sql = "SELECT * FROM atracciones WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    AtraccionesDTO atraccion = new AtraccionesDTO();
                    atraccion.setId(rs.getInt("id"));
                    atraccion.setNombre(rs.getString("nombre"));
                    atraccion.setDescripcion(rs.getString("descripcion"));
                    atraccion.setClasificacionId(rs.getInt("clasificacion_id"));
                    atraccion.setEstaturaMinima(rs.getDouble("estatura_minima"));
                    atraccion.setCondicionesUso(rs.getString("condiciones_uso"));
                    atraccion.setEstado(rs.getString("estado"));
                    atraccion.setEmpleadoId(rs.getInt("empleado_id"));
                    return atraccion.toString();
                }
            }
        }
        return "Atracción no encontrada.";
    }

    // Read all
    public List<Object[]> readAll() throws SQLException {
        String sql = "SELECT * FROM atracciones";
        List<Object[]> atracciones = new ArrayList<>();
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] atraccion = new Object[] {
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("clasificacion_id"),
                    rs.getDouble("estatura_minima"),
                    rs.getString("condiciones_uso"),
                    rs.getString("estado"),
                    rs.getInt("empleado_id")
                };
                atracciones.add(atraccion);
            }
        }
        return atracciones;
    }

    // Update
    public void update(int id, String nombre, String descripcion, int clasificacionId, Double estaturaMinima, String condicionesUso, String estado, int empleadoId) throws SQLException {
        String sql = "UPDATE atracciones SET nombre = ?, descripcion = ?, clasificacion_id = ?, estatura_minima = ?, condiciones_uso = ?, estado = ?, empleado_id = ? WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, descripcion);
            ps.setInt(3, clasificacionId);
            ps.setObject(4, estaturaMinima);
            ps.setString(5, condicionesUso);
            ps.setString(6, estado);
            ps.setInt(7, empleadoId);
            ps.setInt(8, id);
            ps.executeUpdate();
        }
    }

    // Delete
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM atracciones WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<String> getClasificaciones() throws SQLException {
        List<String> clasificaciones = new ArrayList<>();
        String sql = "SELECT nombre FROM clasificaciones_atracciones"; // Asume que tienes una tabla `roles` con columna `nombre`.

        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clasificaciones.add(rs.getString("nombre"));
            }
        }
        return clasificaciones;
    }
    
}

