package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.persistence.ConexionBd;
import model.persistence.TiquetesDTO;

public class TiquetesDAO {

    // Create
    public void create(int clienteId, int estacionId, String fecha, int tipoTiqueteId) throws SQLException {
        TiquetesDTO tiquete = new TiquetesDTO();
        tiquete.setClienteId(clienteId);
        tiquete.setEstacionId(estacionId);
        tiquete.setFecha(fecha);
        tiquete.setTipoTiqueteId(tipoTiqueteId);

        String sql = "INSERT INTO tiquetes (cliente_id, estacion_id, fecha, tipo_tiquete_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tiquete.getClienteId());
            ps.setInt(2, tiquete.getEstacionId());
            ps.setString(3, tiquete.getFecha());
            ps.setInt(4, tiquete.getTipoTiqueteId());
            ps.executeUpdate();
        }
    }

    // Read by ID
    public String read(int id) throws SQLException {
        String sql = "SELECT * FROM tiquetes WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TiquetesDTO tiquete = new TiquetesDTO();
                    tiquete.setId(rs.getInt("id"));
                    tiquete.setClienteId(rs.getInt("cliente_id"));
                    tiquete.setEstacionId(rs.getInt("estacion_id"));
                    tiquete.setFecha(rs.getString("fecha"));
                    tiquete.setTipoTiqueteId(rs.getInt("tipo_tiquete_id"));
                    return tiquete.toString();
                }
            }
        }
        return "Tiquete no encontrado.";
    }

    // Read all
    public List<Object[]> readAll() throws SQLException {
        String sql = "SELECT * FROM tiquetes";
        List<Object[]> tiquetes = new ArrayList<>();
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] tiquete = new Object[] {
                    rs.getInt("id"),
                    rs.getInt("cliente_id"),
                    rs.getInt("estacion_id"),
                    rs.getString("fecha"),
                    rs.getInt("tipo_tiquete_id")
                };
                tiquetes.add(tiquete);
            }
        }
        return tiquetes;
    }

    // Update
    public void update(int id, int clienteId, int estacionId, String fecha, int tipoTiqueteId) throws SQLException {
        String sql = "UPDATE tiquetes SET cliente_id = ?, estacion_id = ?, fecha = ?, tipo_tiquete_id = ? WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            ps.setInt(2, estacionId);
            ps.setString(3, fecha);
            ps.setInt(4, tipoTiqueteId);
            ps.setInt(5, id);
            ps.executeUpdate();
        }
    }

    // Delete
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM tiquetes WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Método para obtener todas las estaciones
    public List<Object[]> obtenerEstaciones() throws SQLException {
        String sql = "SELECT id, nombre, habilitada, empleado_id FROM estaciones";
        List<Object[]> estaciones = new ArrayList<>();
        
        try (Connection conn = ConexionBd.obtenerConexion();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] estacion = new Object[] {
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getBoolean("habilitada"),
                    rs.getInt("empleado_id")
                };
                estaciones.add(estacion);
            }
        }
        return estaciones;
    }


    public List<String> getTiposTiquetes() throws SQLException {
        List<String> tipos = new ArrayList<>();
        String sql = "SELECT nombre FROM tipo_tiquete"; // Asume que tienes una tabla `roles` con columna `nombre`.

        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                tipos.add(rs.getString("nombre"));
            }
        }
        return tipos;
    }

    public void instarDatosIngresoAtracciones(int tiqueteId, int atraccionId) throws SQLException{
        TiquetesDTO tiquete = new TiquetesDTO();
        tiquete.setTiqueteId(tiqueteId);;
        tiquete.setAtraccionId(atraccionId);

        String sql = "INSERT INTO tiquetes_atracciones (tiquete_id, atraccion_id) VALUES (?, ?)";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, tiquete.getTiqueteId());
            ps.setInt(2, tiquete.getAtraccionId());
            ps.executeUpdate();
        }
    }


        // Read all
        public List<Object[]> obtenerTablaIntermedia() throws SQLException {
            String sql = "SELECT * FROM tiquetes_atracciones";
            List<Object[]> tiquetes = new ArrayList<>();
            try (Connection conn = ConexionBd.obtenerConexion();
                 PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] tiquete = new Object[] {
                        rs.getInt("id"),
                        rs.getInt("tiquete_id"),
                        rs.getInt("atraccion_id")
                    };
                    tiquetes.add(tiquete);
                }
            }
            return tiquetes;
        }
}

