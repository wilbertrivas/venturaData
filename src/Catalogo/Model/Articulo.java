 
package Catalogo.Model;
 
public class Articulo {
    private String codigo;
    private TipoArticulo tipoArticulo;
    private String descripcion;
    private String estado; 

    public Articulo() {
    }

    public Articulo(String codigo, String descripcion, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Articulo(String codigo, TipoArticulo tipoArticulo, String descripcion, String estado) {
        this.codigo = codigo;
        this.tipoArticulo = tipoArticulo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoArticulo getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(TipoArticulo tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
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
