package Catalogo.Model;

public class CentroCostoMayor {
    private String codigo;
    private Cliente cliente;
    private CentroCostoSubCentro centroCostoSubcentro;
    private String descripcion;
    private String estado;
    private String clienteBaseDatos;

    public CentroCostoMayor() {
    }

    public CentroCostoMayor(String codigo, Cliente cliente, CentroCostoSubCentro centroCostoSubcentro, String descripcion, String estado) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.centroCostoSubcentro = centroCostoSubcentro;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public CentroCostoSubCentro getCentroCostoSubcentro() {
        return centroCostoSubcentro;
    }

    public void setCentroCostoSubcentro(CentroCostoSubCentro centroCostoSubcentro) {
        this.centroCostoSubcentro = centroCostoSubcentro;
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

    public String getClienteBaseDatos() {
        return clienteBaseDatos;
    }

    public void setClienteBaseDatos(String clienteBaseDatos) {
        this.clienteBaseDatos = clienteBaseDatos;
    }
    
}
