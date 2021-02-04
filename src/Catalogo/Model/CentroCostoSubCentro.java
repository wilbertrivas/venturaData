package Catalogo.Model;

public class CentroCostoSubCentro {
    private int codigo;
    private String descripcion;
    private String estado;
    private CentroOperacion centroOperacion;
    private String centroCostoRequiereLaborRealizada;

    public CentroCostoSubCentro() {
    }

    public CentroCostoSubCentro(int codigo, String descripcion, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public CentroCostoSubCentro(int codigo, String descripcion, String estado, CentroOperacion centroOperacion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.centroOperacion = centroOperacion;
    }

    public CentroCostoSubCentro(int codigo, String descripcion, String estado, CentroOperacion centroOperacion, String centroCostoRequiereLaborRealizada) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.centroOperacion = centroOperacion;
        this.centroCostoRequiereLaborRealizada = centroCostoRequiereLaborRealizada;
    }
    

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
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

    public CentroOperacion getCentroOperacion() {
        return centroOperacion;
    }

    public void setCentroOperacion(CentroOperacion centroOperacion) {
        this.centroOperacion = centroOperacion;
    }

    public String getCentroCostoRequiereLaborRealizada() {
        return centroCostoRequiereLaborRealizada;
    }

    public void setCentroCostoRequiereLaborRealizada(String centroCostoRequiereLaborRealizada) {
        this.centroCostoRequiereLaborRealizada = centroCostoRequiereLaborRealizada;
    }
    
    
}
