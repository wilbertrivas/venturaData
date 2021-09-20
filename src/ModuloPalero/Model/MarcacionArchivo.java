package ModuloPalero.Model;

import Sistema.Model.Usuario;
import java.util.ArrayList;

public class MarcacionArchivo {

    private String codigo;
    private String fecha;
    private Usuario usuario;
    private String estado;
    private ArrayList<MarcacionPersona> listadoMarcacionPersona;

    public MarcacionArchivo() {
    }

    public MarcacionArchivo(String codigo, String fecha, Usuario usuario, String estado, ArrayList<MarcacionPersona> listadoMarcacionPersona) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.usuario = usuario;
        this.estado = estado;
        this.listadoMarcacionPersona = listadoMarcacionPersona;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ArrayList<MarcacionPersona> getListadoMarcacionPersona() {
        return listadoMarcacionPersona;
    }

    public void setListadoMarcacionPersona(ArrayList<MarcacionPersona> listadoMarcacionPersona) {
        this.listadoMarcacionPersona = listadoMarcacionPersona;
    }

}
