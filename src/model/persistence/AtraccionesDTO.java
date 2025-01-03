package model.persistence;

public class AtraccionesDTO {

    private int id;
    private String nombre;
    private String descripcion;
    private int clasificacionId;
    private Double estaturaMinima;
    private String condicionesUso;
    private String estado; // Disponible, No Disponible, Mantenimiento
    private int contadorTiquetes;
    private int empleadoId;

    // Getters y Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getClasificacionId() {
        return clasificacionId;
    }

    public void setClasificacionId(int clasificacionId) {
        this.clasificacionId = clasificacionId;
    }

    public Double getEstaturaMinima() {
        return estaturaMinima;
    }

    public void setEstaturaMinima(Double estaturaMinima) {
        this.estaturaMinima = estaturaMinima;
    }

    public String getCondicionesUso() {
        return condicionesUso;
    }

    public void setCondicionesUso(String condicionesUso) {
        this.condicionesUso = condicionesUso;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getContadorTiquetes() {
        return contadorTiquetes;
    }

    public void setContadorTiquetes(int contadorTiquetes) {
        this.contadorTiquetes = contadorTiquetes;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }
}


