package model.persistence;

public class EmpleadosDTO {

    private int id;
    private String nombre;
    private String cedula;
    private String telefono;
    private String correo;
    private int rolId;
    private String horarioLaboral;

    @Override
    public String toString() {
        return "Id: " + id + "\n" +
               "Nombre: " + nombre + "\n" +
               "Cedula: " + cedula + "\n" +
               "Teléfono: " + telefono + "\n" +
               "Correo: " + correo + "\n" +
               "Rol Id: " + rolId + "\n"+
               "horario Laboral: " + horarioLaboral + "\n";
    }

    // Getters y setters
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getHorarioLaboral() {
        return horarioLaboral;
    }

    public void setHorarioLaboral(String horarioLaboral) {
        this.horarioLaboral = horarioLaboral;
    }

    public int getRolId() {
        return rolId;
    }

    public void setRolId(int rolId) {
        this.rolId = rolId;
    }
}
