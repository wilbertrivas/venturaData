package Consumo.Model2;
 
import java.util.ArrayList;

public class Perfil {
    private int codigo;
    private String descripcion;
    private ArrayList<Permiso> listadoPermisos;
    private String estado;

    public Perfil() {
    }

    public Perfil(int codigo, String descripcion, ArrayList<Permiso> listadoPermisos, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.listadoPermisos = listadoPermisos;
        this.estado = estado;
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

    public ArrayList<Permiso> getListadoPermisos() {
        return listadoPermisos;
    }

    public void setListadoPermisos(ArrayList<Permiso> listadoPermisos) {
        this.listadoPermisos = listadoPermisos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
