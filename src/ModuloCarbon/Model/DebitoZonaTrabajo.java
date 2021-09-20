
package ModuloCarbon.Model;

import Catalogo.Model.ZonaTrabajo;
import Sistema.Model.Usuario;

public class DebitoZonaTrabajo {
    private String codigo;
    private String fechaMvomiento;
    private ZonaTrabajo zonaTrabajo;
    private String valor;
    private String descripcion;
    private String estado;
    private Usuario usuarioQuienRegistra;
    private String fechaRegistro;

    public DebitoZonaTrabajo() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getFechaMvomiento() {
        return fechaMvomiento;
    }

    public void setFechaMvomiento(String fechaMvomiento) {
        this.fechaMvomiento = fechaMvomiento;
    }

    public ZonaTrabajo getZonaTrabajo() {
        return zonaTrabajo;
    }

    public void setZonaTrabajo(ZonaTrabajo zonaTrabajo) {
        this.zonaTrabajo = zonaTrabajo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    public Usuario getUsuarioQuienRegistra() {
        return usuarioQuienRegistra;
    }

    public void setUsuarioQuienRegistra(Usuario usuarioQuienRegistra) {
        this.usuarioQuienRegistra = usuarioQuienRegistra;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    
    
}
