package Catalogo.Model;

public class CentroCosto{
    private String codigo;
    private CentroOperacion centroOperacion;
    private CentroCostoAuxiliar centroCostoAuxiliar;
    private Cliente Cliente;
    private String descripción;
    private String estado;
    private LaborRealizada laborRealizada;

    public CentroCosto() {
    }
    
    public CentroCosto(String codigo, CentroOperacion centroOperacion, CentroCostoAuxiliar centroCostoAuxiliar, Cliente Cliente, String descripción, String estado) {
        this.codigo = codigo;
        this.centroOperacion = centroOperacion;
        this.centroCostoAuxiliar = centroCostoAuxiliar;
        this.Cliente = Cliente;
        this.descripción = descripción;
        this.estado = estado;
    }

    public CentroCosto(String codigo, CentroOperacion centroOperacion, CentroCostoAuxiliar centroCostoAuxiliar, Cliente Cliente, String descripción, String estado, LaborRealizada laborRealizada) {
        this.codigo = codigo;
        this.centroOperacion = centroOperacion;
        this.centroCostoAuxiliar = centroCostoAuxiliar;
        this.Cliente = Cliente;
        this.descripción = descripción;
        this.estado = estado;
        this.laborRealizada = laborRealizada;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public CentroOperacion getCentroOperacion() {
        return centroOperacion;
    }

    public void setCentroOperacion(CentroOperacion centroOperacion) {
        this.centroOperacion = centroOperacion;
    }

    public CentroCostoAuxiliar getCentroCostoAuxiliar() {
        return centroCostoAuxiliar;
    }

    public void setCentroCostoAuxiliar(CentroCostoAuxiliar centroCostoAuxiliar) {
        this.centroCostoAuxiliar = centroCostoAuxiliar;
    }

    public Cliente getCliente() {
        return Cliente;
    }

    public void setCliente(Cliente Cliente) {
        this.Cliente = Cliente;
    }

    public String getDescripción() {
        return descripción;
    }

    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LaborRealizada getLaborRealizada() {
        return laborRealizada;
    }

    public void setLaborRealizada(LaborRealizada laborRealizada) {
        this.laborRealizada = laborRealizada;
    }

   
}
