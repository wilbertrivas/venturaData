package Consumo.Model2;
    
import Sistema.Model.Usuario;
import Consumo.Model2.Insumo;

public class Compra {
    private int codigo;
    private Insumo insumo;
    private String fechaCompra;
    private int cantidad;
    private String observacion;
    private Usuario usuario;

    public Compra() {
    }

    public Compra(int codigo, Insumo insumo, String fechaCompra, int cantidad, String observacion, Usuario usuario) {
        this.codigo = codigo;
        this.insumo = insumo;
        this.fechaCompra = fechaCompra;
        this.cantidad = cantidad;
        this.observacion = observacion;
        this.usuario = usuario;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public String getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

   
    
}
