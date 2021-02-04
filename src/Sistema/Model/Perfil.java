package Sistema.Model;
 
import java.util.ArrayList;
 
public class Perfil {
    private String codigo;
    private String descripcion;
    private ArrayList<Permisos> permisos;
    private String estado;

    public Perfil() {
    }

    public Perfil(String codigo, String descripcion, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public Perfil(String codigo, String descripcion, ArrayList<Permisos> permisos, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.permisos = permisos;
        this.estado = estado;
    }
    public Perfil(String codigo, String descripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
    }

    public Perfil(String codigo) {
        this.codigo = codigo;
    }
    

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Permisos> getPermisos() {
        return permisos;
    }

    public void setPermisos(ArrayList<Permisos> permisos) {
        this.permisos = permisos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
}
