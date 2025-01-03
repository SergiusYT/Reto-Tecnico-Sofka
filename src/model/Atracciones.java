package model;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;

public class Atracciones {


    private AtraccionesDAO atraccionesDAO;
    private Map<Integer, String> estadosPrevios; // Para rastrear el estado previo por atracción
    private boolean notificarCambioEstado; // Bandera para controlar la notificación



    public Atracciones() {
        atraccionesDAO = new AtraccionesDAO();
        estadosPrevios = new HashMap<>();
    }

    // Método para agregar una atracción
    public void agregarAtraccion(String nombre, String descripcion, int clasificacionId, Double estaturaMinima, String condicionesUso, String estado, int empleadoId) throws SQLException {
        atraccionesDAO.create(nombre, descripcion, clasificacionId, estaturaMinima, condicionesUso, estado, empleadoId);
    }

    // Método para buscar una atracción por ID
    public String buscarAtraccion(int id) throws SQLException {
        return atraccionesDAO.read(id);
    }

    // Método para listar todas las atracciones
    public List<Object[]> listarAtracciones() throws SQLException {
        return atraccionesDAO.readAll();
    }

    // Método para actualizar una atracción
    public void actualizarAtraccion(int id, String nombre, String descripcion, int clasificacionId, Double estaturaMinima, String condicionesUso, String estado, int empleadoId) throws SQLException {
        String estadoPrevio = estadosPrevios.getOrDefault(id, "");
        
        // Si el estado cambia, notificar
        if (!estadoPrevio.equals(estado)) {
            estadosPrevios.put(id, estado); // Actualizar el estado previo
        }

        atraccionesDAO.update(id, nombre, descripcion, clasificacionId, estaturaMinima, condicionesUso, estado, empleadoId);
    }

    // Método para eliminar una atracción por ID
    public void eliminarAtraccion(int id) throws SQLException {
        atraccionesDAO.delete(id);
    }

        // Método que se llama cuando se presiona "TiquetesView"
    public void verificarCambiosEstado() {
        if (notificarCambioEstado) {
            for (Map.Entry<Integer, String> entry : estadosPrevios.entrySet()) {
                mostrarNotificacionCambioEstado(entry.getKey(), entry.getValue());
            }
            notificarCambioEstado = false; // Desactivar la notificación tras mostrarla
        }
    }

    // Método para activar la notificación
    public void activarNotificacionCambioEstado() {
        notificarCambioEstado = true;
    }
    

    private void mostrarNotificacionCambioEstado(int id, String estado) {
        JOptionPane.showMessageDialog(null, 
            "El estado de la atracción con ID " + id + " ha cambiado a: " + estado, 
            "Cambio de Estado", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para obtener las clasificaciones disponibles
    public List<String> obtenerClasificaciones() throws SQLException {
        return atraccionesDAO.getClasificaciones();
    }

}
