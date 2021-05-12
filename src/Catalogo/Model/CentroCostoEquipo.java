package Catalogo.Model;

public class CentroCostoEquipo {
    private String codigo;
    private String codigoInterno;
    private String descripcion;
    private String estado;

    public CentroCostoEquipo() {
    }

    public CentroCostoEquipo(String codigo, String codigoInterno, String descripcion, String estado) {
        this.codigo = codigo;
        this.codigoInterno = codigoInterno;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
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
