
package ModuloPalero.Model;

import ModuloCarbon.Model.MvtoCarbon_ListadoEquipos;

public class MvtoCarbon_ListadoEquipos_LiquidacionPaleros extends MvtoCarbon_ListadoEquipos{
    private String ano;
    private String mes;
    private String dia;
    private String color;

    public MvtoCarbon_ListadoEquipos_LiquidacionPaleros() {
    }

    public MvtoCarbon_ListadoEquipos_LiquidacionPaleros(String ano, String mes, String dia) {
        this.ano = ano;
        this.mes = mes;
        this.dia = dia;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    
    
}
