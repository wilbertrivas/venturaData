package Catalogo.Model;
  
public class CentroCostoAuxiliar {
    private int codigo;
    private CentroCostoSubCentro centroCostoSubCentro;
    private String descripcion;
    private String estado;

    public CentroCostoAuxiliar(int codigo, CentroCostoSubCentro centroCostoSubCentro, String descripcion, String estado) {
        this.codigo = codigo;
        this.centroCostoSubCentro = centroCostoSubCentro;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public CentroCostoAuxiliar() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public CentroCostoSubCentro getCentroCostoSubCentro() {
        return centroCostoSubCentro;
    }

    public void setCentroCostoSubCentro(CentroCostoSubCentro centroCostoSubCentro) {
        this.centroCostoSubCentro = centroCostoSubCentro;
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
