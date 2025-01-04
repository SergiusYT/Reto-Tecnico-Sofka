package model.persistence;

public class TiquetesDTO {

    private int id;
    private int clienteId;
    private int estacionId;
    private String fecha;
    private int tipoTiqueteId;
    private int tiquetesAtraccionesId;
    private int tiqueteId;
    private int atraccionId;

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getEstacionId() {
        return estacionId;
    }

    public void setEstacionId(int estacionId) {
        this.estacionId = estacionId;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getTipoTiqueteId() {
        return tipoTiqueteId;
    }

    public void setTipoTiqueteId(int tipoTiqueteId) {
        this.tipoTiqueteId = tipoTiqueteId;
    }

    public int getTiquetesAtraccionesId() {
        return tiquetesAtraccionesId;
    }

    public void setTiquetesAtraccionesId(int tiquetesAtraccionesId) {
        this.tiquetesAtraccionesId = tiquetesAtraccionesId;
    }

    public int getTiqueteId() {
        return tiqueteId;
    }

    public void setTiqueteId(int tiqueteId) {
        this.tiqueteId = tiqueteId;
    }

    public int getAtraccionId() {
        return atraccionId;
    }

    public void setAtraccionId(int atraccionId) {
        this.atraccionId = atraccionId;
    }
    
}


