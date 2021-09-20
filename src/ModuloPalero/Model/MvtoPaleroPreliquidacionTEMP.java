/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloPalero.Model;

import ModuloPersonal.Model.Persona;

/**
 *
 * @author wrivas
 */
public class MvtoPaleroPreliquidacionTEMP {
    private String codigo;
    private MvtoVehiculoPalerosTEMP mvtoVehiculoPalerosTEMP;
    private ConfiguracionLiquidacion configuracionLiquidacion;
    private String fecha;
    private Persona persona;
    private EquipoLiquidacion equipoLiquidacion;
    private String pesoAsignado;
    private String estado;

    public MvtoPaleroPreliquidacionTEMP() {
    }

    
    public MvtoPaleroPreliquidacionTEMP(String codigo, MvtoVehiculoPalerosTEMP mvtoVehiculoPalerosTEMP, ConfiguracionLiquidacion configuracionLiquidacion, String fecha, Persona persona, EquipoLiquidacion equipoLiquidacion, String pesoAsignado, String estado) {
        this.codigo = codigo;
        this.mvtoVehiculoPalerosTEMP = mvtoVehiculoPalerosTEMP;
        this.configuracionLiquidacion = configuracionLiquidacion;
        this.fecha = fecha;
        this.persona = persona;
        this.equipoLiquidacion = equipoLiquidacion;
        this.pesoAsignado = pesoAsignado;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public MvtoVehiculoPalerosTEMP getMvtoVehiculoPalerosTEMP() {
        return mvtoVehiculoPalerosTEMP;
    }

    public void setMvtoVehiculoPalerosTEMP(MvtoVehiculoPalerosTEMP mvtoVehiculoPalerosTEMP) {
        this.mvtoVehiculoPalerosTEMP = mvtoVehiculoPalerosTEMP;
    }

    public ConfiguracionLiquidacion getConfiguracionLiquidacion() {
        return configuracionLiquidacion;
    }

    public void setConfiguracionLiquidacion(ConfiguracionLiquidacion configuracionLiquidacion) {
        this.configuracionLiquidacion = configuracionLiquidacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public EquipoLiquidacion getEquipoLiquidacion() {
        return equipoLiquidacion;
    }

    public void setEquipoLiquidacion(EquipoLiquidacion equipoLiquidacion) {
        this.equipoLiquidacion = equipoLiquidacion;
    }

    public String getPesoAsignado() {
        return pesoAsignado;
    }

    public void setPesoAsignado(String pesoAsignado) {
        this.pesoAsignado = pesoAsignado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
