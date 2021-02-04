
package Catalogo.Model;
 
public class Cliente {
    
    private String codigo;
    private String descripcion;
    private String estado;
    private int valorRecobro;

    public Cliente() {
    }

    public Cliente(String codigo, String descripcion, String estado, int valorRecobro) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.valorRecobro = valorRecobro;
    }
     public Cliente(String codigo, String descripcion, String estado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getValorRecobro() {
        return valorRecobro;
    }

    public void setValorRecobro(int valorRecobro) {
        this.valorRecobro = valorRecobro;
    }

   
}
