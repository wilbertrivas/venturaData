package Consumo.Model;
 
public class Permiso {
    private int cdgo;
    private String descripcion;

    public Permiso() {
    }

    public Permiso(int cdgo, String descripcion) {
        this.cdgo = cdgo;
        this.descripcion = descripcion;
    }

    public int getCdgo() {
        return cdgo;
    }

    public void setCdgo(int cdgo) {
        this.cdgo = cdgo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
