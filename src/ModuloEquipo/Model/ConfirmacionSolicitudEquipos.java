package ModuloEquipo.Model;

public class ConfirmacionSolicitudEquipos {
    private String codigo;
    private String descripcion;
    private String estado;

    public ConfirmacionSolicitudEquipos() {
    }

    public ConfirmacionSolicitudEquipos(String codigo, String descripcion, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public ConfirmacionSolicitudEquipos(String codigo) {
        this.codigo = codigo;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
