package Catalogo.Model;

public class TarifaEquipo {
    private String codigo;
    private String año;
    private String codigoEquipo;
    private String valorHoraOperacion;
    private String valorHoraAlquiler;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    /*private String valorLavadoVehiculo;*/
    private String costoLavadoVehiculo;
    private String valorRecaudoEmpresa;
    private String valorRecaudoEquipo;

    public TarifaEquipo() {
    }

    public TarifaEquipo(String codigo, String año, String codigoEquipo, String valorHoraOperacion, String valorHoraAlquiler) {
        this.codigo = codigo;
        this.año = año;
        this.codigoEquipo = codigoEquipo;
        this.valorHoraOperacion = valorHoraOperacion;
        this.valorHoraAlquiler = valorHoraAlquiler;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getCodigoEquipo() {
        return codigoEquipo;
    }

    public void setCodigoEquipo(String codigoEquipo) {
        this.codigoEquipo = codigoEquipo;
    }

    public String getValorHoraOperacion() {
        return valorHoraOperacion;
    }

    public void setValorHoraOperacion(String valorHoraOperacion) {
        this.valorHoraOperacion = valorHoraOperacion;
    }

    public String getValorHoraAlquiler() {
        return valorHoraAlquiler;
    }

    public void setValorHoraAlquiler(String valorHoraAlquiler) {
        this.valorHoraAlquiler = valorHoraAlquiler;
    }

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    /*public String getValorLavadoVehiculo() {
        return valorLavadoVehiculo;
    }

    public void setValorLavadoVehiculo(String valorLavadoVehiculo) {
        this.valorLavadoVehiculo = valorLavadoVehiculo;
    }*/

    public String getCostoLavadoVehiculo() {
        return costoLavadoVehiculo;
    }

    public void setCostoLavadoVehiculo(String costoLavadoVehiculo) {
        this.costoLavadoVehiculo = costoLavadoVehiculo;
    }

    public String getValorRecaudoEmpresa() {
        return valorRecaudoEmpresa;
    }

    public void setValorRecaudoEmpresa(String valorRecaudoEmpresa) {
        this.valorRecaudoEmpresa = valorRecaudoEmpresa;
    }

    public String getValorRecaudoEquipo() {
        return valorRecaudoEquipo;
    }

    public void setValorRecaudoEquipo(String valorRecaudoEquipo) {
        this.valorRecaudoEquipo = valorRecaudoEquipo;
    }

    
    
}
