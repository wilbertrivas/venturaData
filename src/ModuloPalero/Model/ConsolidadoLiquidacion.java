
package ModuloPalero.Model;

import ModuloPersonal.Model.Persona;

public class ConsolidadoLiquidacion {
    private String codigo;
    private ConfiguracionLiquidacion configuracionLiquidacion;
    private Persona persona;
    private String fecha;
    private String dia;
    private String cantidadVehiculosDescargados;
    private String descripcionVehiculosDescargados;
    private String cantidadToneladasDescargadas;
    private String cantidadToneladasAsignadas;
    private String estado;

    public ConsolidadoLiquidacion() {
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

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getCantidadVehiculosDescargados() {
        return cantidadVehiculosDescargados;
    }

    public void setCantidadVehiculosDescargados(String cantidadVehiculosDescargados) {
        this.cantidadVehiculosDescargados = cantidadVehiculosDescargados;
    }

    public String getCantidadToneladasDescargadas() {
        return cantidadToneladasDescargadas;
    }

    public void setCantidadToneladasDescargadas(String cantidadToneladasDescargadas) {
        this.cantidadToneladasDescargadas = cantidadToneladasDescargadas;
    }

    public String getCantidadToneladasAsignadas() {
        return cantidadToneladasAsignadas;
    }

    public void setCantidadToneladasAsignadas(String cantidadToneladasAsignadas) {
        this.cantidadToneladasAsignadas = cantidadToneladasAsignadas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcionVehiculosDescargados() {
        return descripcionVehiculosDescargados;
    }

    public void setDescripcionVehiculosDescargados(String descripcionVehiculosDescargados) {
        this.descripcionVehiculosDescargados = descripcionVehiculosDescargados;
    }
    
    
}
