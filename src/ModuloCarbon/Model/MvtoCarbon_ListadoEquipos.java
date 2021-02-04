package ModuloCarbon.Model;

import ModuloEquipo.Model.AsignacionEquipo;
import ModuloEquipo.Model.MvtoEquipo;

public class MvtoCarbon_ListadoEquipos {
    private String codigo;
    private MvtoCarbon mvtoCarbon;
    private AsignacionEquipo asignacionEquipo;
    private MvtoEquipo mvtoEquipo;
    private String estado;

    public MvtoCarbon_ListadoEquipos() {
    }

    public MvtoCarbon_ListadoEquipos(String codigo, MvtoCarbon mvtoCarbon, AsignacionEquipo asignacionEquipo, MvtoEquipo mvtoEquipo, String estado) {
        this.codigo = codigo;
        this.mvtoCarbon = mvtoCarbon;
        this.asignacionEquipo = asignacionEquipo;
        this.mvtoEquipo = mvtoEquipo;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public MvtoCarbon getMvtoCarbon() {
        return mvtoCarbon;
    }

    public void setMvtoCarbon(MvtoCarbon mvtoCarbon) {
        this.mvtoCarbon = mvtoCarbon;
    }

    public AsignacionEquipo getAsignacionEquipo() {
        return asignacionEquipo;
    }

    public void setAsignacionEquipo(AsignacionEquipo asignacionEquipo) {
        this.asignacionEquipo = asignacionEquipo;
    }

    public MvtoEquipo getMvtoEquipo() {
        return mvtoEquipo;
    }

    public void setMvtoEquipo(MvtoEquipo mvtoEquipo) {
        this.mvtoEquipo = mvtoEquipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
