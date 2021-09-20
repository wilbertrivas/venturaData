package ModuloPalero.Model;

import ModuloPersonal.Model.Persona;

public class Liquidacion {
   private String codigo;
   private ConfiguracionLiquidacion configuracionLiquidacion;
   private String fecha;
   private Persona persona;
   private String cantidadToneladaAcumulada;
   private String cantidadToneladaSalario;
   private String cantidadToneladaAPagar;
   private String totalValorAPagar;
   private String estado;

    public Liquidacion() {
    }

    public Liquidacion(String codigo, ConfiguracionLiquidacion configuracionLiquidacion, String fecha, Persona persona, String cantidadToneladaAcumulada, String cantidadToneladaSalario, String cantidadToneladaAPagar, String totalValorAPagar, String estado) {
        this.codigo = codigo;
        this.configuracionLiquidacion = configuracionLiquidacion;
        this.fecha = fecha;
        this.persona = persona;
        this.cantidadToneladaAcumulada = cantidadToneladaAcumulada;
        this.cantidadToneladaSalario = cantidadToneladaSalario;
        this.cantidadToneladaAPagar = cantidadToneladaAPagar;
        this.totalValorAPagar = totalValorAPagar;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public String getCantidadToneladaAcumulada() {
        return cantidadToneladaAcumulada;
    }

    public void setCantidadToneladaAcumulada(String cantidadToneladaAcumulada) {
        this.cantidadToneladaAcumulada = cantidadToneladaAcumulada;
    }

    public String getCantidadToneladaSalario() {
        return cantidadToneladaSalario;
    }

    public void setCantidadToneladaSalario(String cantidadToneladaSalario) {
        this.cantidadToneladaSalario = cantidadToneladaSalario;
    }

    public String getCantidadToneladaAPagar() {
        return cantidadToneladaAPagar;
    }

    public void setCantidadToneladaAPagar(String cantidadToneladaAPagar) {
        this.cantidadToneladaAPagar = cantidadToneladaAPagar;
    }

    public String getTotalValorAPagar() {
        return totalValorAPagar;
    }

    public void setTotalValorAPagar(String totalValorAPagar) {
        this.totalValorAPagar = totalValorAPagar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
   
   
}
