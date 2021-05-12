package Catalogo.Model;

import Catalogo.Model.Cliente;
import Sistema.Model.Usuario;

public class ClienteRecobro {
    private String codigo;
    private Cliente cliente;
    private Usuario usuario;
    private String valorRecobro;
    private String fechaRegistro;
    private String clienteBaseDatos;

    public ClienteRecobro() {
    }

    public ClienteRecobro(String codigo, Cliente cliente, Usuario usuario, String valorRecobro, String fechaRegistro) {
        this.codigo = codigo;
        this.cliente = cliente;
        this.usuario = usuario;
        this.valorRecobro = valorRecobro;
        this.fechaRegistro = fechaRegistro;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getValorRecobro() {
        return valorRecobro;
    }

    public void setValorRecobro(String valorRecobro) {
        this.valorRecobro = valorRecobro;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getClienteBaseDatos() {
        return clienteBaseDatos;
    }

    public void setClienteBaseDatos(String clienteBaseDatos) {
        this.clienteBaseDatos = clienteBaseDatos;
    }
    
}
