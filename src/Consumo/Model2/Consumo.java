package Consumo.Model2;

import Catalogo.Model.BaseDatos;
import Catalogo.Model.Cliente;
import Sistema.Model.Usuario;

public class Consumo {
    private int codigo;
    private String fecha;
    private Cliente cliente;
    private Insumo insumo;
    private Usuario usuario;
    private int cantidad;
    private String observacion;
    private String fechaRegistroSistema;
    private String clienteBaseDatos;

    public Consumo(int codigo, String fecha, Cliente cliente, Insumo insumo, Usuario usuario, int cantidad, String observacion, String fechaRegistroSistema) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.cliente = cliente;
        this.insumo = insumo;
        this.usuario = usuario;
        this.cantidad = cantidad;
        this.observacion = observacion;
        this.fechaRegistroSistema = fechaRegistroSistema;
    }

    public Consumo() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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

    public String getFechaRegistroSistema() {
        return fechaRegistroSistema;
    }

    public void setFechaRegistroSistema(String fechaRegistroSistema) {
        this.fechaRegistroSistema = fechaRegistroSistema;
    }

    public String getClienteBaseDatos() {
        return clienteBaseDatos;
    }

    public void setClienteBaseDatos(String clienteBaseDatos) {
        this.clienteBaseDatos = clienteBaseDatos;
    }
    
}
