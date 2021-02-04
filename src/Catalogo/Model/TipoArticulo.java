package Catalogo.Model;

public class TipoArticulo {
   private String codigo; 
   private String descripcion; 
   private String codigoERP; 
   private String unidadNegocio; 
   private String estado; 

    public TipoArticulo() {
    }

    public TipoArticulo(String codigo, String descripcion, String codigoERP, String unidadNegocio, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.codigoERP = codigoERP;
        this.unidadNegocio = unidadNegocio;
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

    public String getCodigoERP() {
        return codigoERP;
    }

    public void setCodigoERP(String codigoERP) {
        this.codigoERP = codigoERP;
    }

    public String getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
}
