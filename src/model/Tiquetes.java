package model;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Tiquetes {

    private TiquetesDAO tiquetesDAO;

    public Tiquetes() {
        tiquetesDAO = new TiquetesDAO();
    }

    public void agregarTiquetes(int clienteId, int estacionId, String fecha, int tipoTiqueteId) throws SQLException {
        tiquetesDAO.create(clienteId, estacionId, fecha, tipoTiqueteId);
    }

    public String obtenerTiquete(int id) throws SQLException {
        return tiquetesDAO.read(id);
    }

    public List<Object[]> obtenerTiquetes() throws SQLException {
        return tiquetesDAO.readAll();
    }

    public void actualizarTiquete(int id, int clienteId, int estacionId, String fecha, int tipoTiqueteId) throws SQLException {
        tiquetesDAO.update(id, clienteId, estacionId, fecha, tipoTiqueteId);
    }

    public void eliminarTiquete(int id) throws SQLException {
        tiquetesDAO.delete(id);
    }

    public List<Object[]> obtenerEstaciones() throws SQLException {
        return tiquetesDAO.obtenerEstaciones();
    }

    public List<String> obtenerTiposTiquetes() throws SQLException {
        return tiquetesDAO.getTiposTiquetes();
    }

    public void instarDatosIngresoAtracciones(int tiqueteId, int atraccionId) throws SQLException {
        tiquetesDAO.instarDatosIngresoAtracciones(tiqueteId, atraccionId);
    }

    public List<Object[]> obtenerTablaIntermedia() throws SQLException {
        return tiquetesDAO.obtenerTablaIntermedia();
    }

    public Map<String, Integer> obtenerVisitasAtracciones() throws SQLException {
        return tiquetesDAO.obtenerVisitasAtracciones();
    }
    
}
