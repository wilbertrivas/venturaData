
package Catalogo.Model;

public class ClasificadorEquipo {
    private String codigo;
    private String descripcion;
    private String estado;

    public ClasificadorEquipo() {
    }

    public ClasificadorEquipo(String codigo) {
        this.codigo = codigo;
    }

    public ClasificadorEquipo(String codigo, String descripcion, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
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
