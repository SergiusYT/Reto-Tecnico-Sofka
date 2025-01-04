package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.persistence.*;

public class ClientesDAO {

    public void create(String nombre, String numeroIdentificacion, String telefono, String correo, double estatura, int edad, String nombreFamiliar, String telefonoFamiliar, int visitas) throws SQLException {
        ClientesDTO cliente = new ClientesDTO();
        cliente.setNombre(nombre);
        cliente.setNumeroIdentificacion(numeroIdentificacion);
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setEstatura(estatura);
        cliente.setEdad(edad);
        cliente.setNombreFamiliar(nombreFamiliar);
        cliente.setTelefonoFamiliar(telefonoFamiliar);
        cliente.setVisitas(visitas);

        String sql = "INSERT INTO clientes (nombre, N_Identificacion, telefono, correo, estatura, edad, nombre_familiar, telefono_familiar, visitas) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getNumeroIdentificacion());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getCorreo());
            ps.setDouble(5, cliente.getEstatura());
            ps.setInt(6, cliente.getEdad());
            ps.setString(7, cliente.getNombreFamiliar());
            ps.setString(8, cliente.getTelefonoFamiliar());
            ps.setInt(9, cliente.getVisitas());
            ps.executeUpdate();
        }
    }

    public String read(int id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ClientesDTO cliente = new ClientesDTO();
                    cliente.setId(rs.getInt("id"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setNumeroIdentificacion(rs.getString("N_Identificacion"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setCorreo(rs.getString("correo"));
                    cliente.setEstatura(rs.getDouble("estatura"));
                    cliente.setEdad(rs.getInt("edad"));
                    cliente.setNombreFamiliar(rs.getString("nombre_familiar"));
                    cliente.setTelefonoFamiliar(rs.getString("telefono_familiar"));
                    cliente.setVisitas(rs.getInt("visitas"));
                    return cliente.toString();
                }
            }
        }
        return "Cliente no encontrado.";
    }

    public List<Object[]> readAll() throws SQLException {
        String sql = "SELECT * FROM clientes";
        List<Object[]> clientes = new ArrayList<>();
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Object[] cliente = new Object[] {
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("N_Identificacion"),
                    rs.getString("telefono"),
                    rs.getString("correo"),
                    rs.getDouble("estatura"),
                    rs.getInt("edad"),
                    rs.getString("nombre_familiar"),
                    rs.getString("telefono_familiar"),
                    rs.getInt("visitas")
                };
                clientes.add(cliente);
            }
        }
        return clientes;
    }
    

    public void update(int id, String nombre, String numeroIdentificacion, String telefono, String correo, double estatura, int edad, String nombreFamiliar, String telefonoFamiliar, Integer visitas) throws SQLException {
        // Obtener visitas actuales si no se especifica un nuevo valor
        if (visitas == null) {
            String sqlVisitas = "SELECT visitas FROM clientes WHERE id = ?";
            try (Connection conn = ConexionBd.obtenerConexion();
                 PreparedStatement psVisitas = conn.prepareStatement(sqlVisitas)) {
                psVisitas.setInt(1, id);
                try (ResultSet rs = psVisitas.executeQuery()) {
                    if (rs.next()) {
                        visitas = rs.getInt("visitas");
                    } else {
                        throw new SQLException("Cliente con ID " + id + " no encontrado.");
                    }
                }
            }
        }
    
        String sql = "UPDATE clientes SET nombre = ?, N_Identificacion = ?, telefono = ?, correo = ?, estatura = ?, edad = ?, " +
                     "nombre_familiar = ?, telefono_familiar = ?, visitas = ? WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, numeroIdentificacion);
            ps.setString(3, telefono);
            ps.setString(4, correo);
            ps.setDouble(5, estatura);
            ps.setInt(6, edad);
            ps.setString(7, nombreFamiliar);
            ps.setString(8, telefonoFamiliar);
            ps.setInt(9, visitas);
            ps.setInt(10, id);
            ps.executeUpdate();
        }
    }
    

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM clientes WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public void incrementarVisitas(int clienteId) throws SQLException {
        String sql = "UPDATE clientes SET visitas = visitas + 1 WHERE id = ?";
        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            int filasActualizadas = ps.executeUpdate();
            if (filasActualizadas == 0) {
                throw new SQLException("No se encontró un cliente con ID " + clienteId);
            }
        }
    }


        // Método para clasificar clientes frecuentes por número de visitas
    public Map<String, Integer> obtenerClientesFrecuentes() throws SQLException {
        Map<String, Integer> clientesFrecuentes = new HashMap<>();
        String sql = "SELECT " +
                     "    CASE " +
                     "        WHEN visitas >= 3 THEN '3+ visitas' " +
                     "        WHEN visitas BETWEEN 1 AND 2 THEN '1-2 visitas' " +
                     "        ELSE '0 visitas' " +
                     "    END AS categoria, " +
                     "    COUNT(*) AS total_clientes " +
                     "FROM clientes " +
                     "GROUP BY categoria";

        try (Connection conn = ConexionBd.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                clientesFrecuentes.put(rs.getString("categoria"), rs.getInt("total_clientes"));
            }
        }
        return clientesFrecuentes;
    }
}
    
    

