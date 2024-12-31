package model.persistence;

public class ClientesDTO {
 
    private int id;
    private String nombre;
    private String numeroIdentificacion;
    private String telefono;
    private String correo;
    private double estatura;
    private int edad;
    private String nombreFamiliar;
    private String telefonoFamiliar;
    private int visitas;

    @Override
    public String toString() {
        return "Id: " + id + "\n" +
                "Nombre: " + nombre + "\n" +
                "Número de identificación: " + numeroIdentificacion + "\n" +
                "Teléfono: " + telefono + "\n" +
                "Correo: " + correo + "\n" +
                "Estatura: " + estatura + "\n" +
                "Edad: " + edad + "\n" +
                "Nombre del familiar: " + nombreFamiliar + "\n" +
                "Teléfono del familiar: " + telefonoFamiliar + "\n" +
                "Visitas: " + visitas + "\n";
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

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public void setNumeroIdentificacion(String numeroIdentificacion) {
        this.numeroIdentificacion = numeroIdentificacion;
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

    public double getEstatura() {
        return estatura;
    }

    public void setEstatura(double estatura) {
        this.estatura = estatura;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombreFamiliar() {
        return nombreFamiliar;
    }

    public void setNombreFamiliar(String nombreFamiliar) {
        this.nombreFamiliar = nombreFamiliar;
    }

    public String getTelefonoFamiliar() {
        return telefonoFamiliar;
    }

    public void setTelefonoFamiliar(String telefonoFamiliar) {
        this.telefonoFamiliar = telefonoFamiliar;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visitas) {
        this.visitas = visitas;
    }

}
