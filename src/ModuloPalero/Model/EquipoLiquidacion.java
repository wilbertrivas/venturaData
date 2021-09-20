
package ModuloPalero.Model;

import Catalogo.Model.Equipo;


public class EquipoLiquidacion {
    private String codigo;
    private Equipo equipo;
    private String estado;

    public EquipoLiquidacion() {
    }

    public EquipoLiquidacion(String codigo, Equipo equipo, String estado) {
        this.codigo = codigo;
        this.equipo = equipo;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
