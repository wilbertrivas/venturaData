/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloPalero.Model;

/**
 *
 * @author wrivas
 */
public class MvtoVehiculoPalerosTEMP {
    private String codigo;
    private MvtoCarbon_ListadoEquipos_LiquidacionPaleros mvtoCarbon_ListadoEquipos_LiquidacionPaleros;
    private String estado;

    public MvtoVehiculoPalerosTEMP() {
    }

    public MvtoVehiculoPalerosTEMP(String codigo, MvtoCarbon_ListadoEquipos_LiquidacionPaleros mvtoCarbon_ListadoEquipos_LiquidacionPaleros, String estado) {
        this.codigo = codigo;
        this.mvtoCarbon_ListadoEquipos_LiquidacionPaleros = mvtoCarbon_ListadoEquipos_LiquidacionPaleros;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public MvtoCarbon_ListadoEquipos_LiquidacionPaleros getMvtoCarbon_ListadoEquipos_LiquidacionPaleros() {
        return mvtoCarbon_ListadoEquipos_LiquidacionPaleros;
    }

    public void setMvtoCarbon_ListadoEquipos_LiquidacionPaleros(MvtoCarbon_ListadoEquipos_LiquidacionPaleros mvtoCarbon_ListadoEquipos_LiquidacionPaleros) {
        this.mvtoCarbon_ListadoEquipos_LiquidacionPaleros = mvtoCarbon_ListadoEquipos_LiquidacionPaleros;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
}
