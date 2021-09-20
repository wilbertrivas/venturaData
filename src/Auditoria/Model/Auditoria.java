
package Auditoria.Model;

import Sistema.Model.Usuario;

public class Auditoria {
    private String  codigo;
    private String  fecha;
    private Usuario usuarioQuienRegistra;
    private String  nombreDispositivoDondeRegistra;
    private String  ipDispositivoDondeRegistra;
    private String  macDispositivoDondeRegistra;
    private String  codigoMovimiento;
    private String descMovimiento;
    private String  detalleMovimiento;

    public Auditoria() {
    }

    public Auditoria(String codigo, String fecha, Usuario usuarioQuienRegistra, String nombreDispositivoDondeRegistra, String ipDispositivoDondeRegistra, String macDispositivoDondeRegistra, String codigoMovimiento, String descMovimiento, String detalleMovimiento) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.usuarioQuienRegistra = usuarioQuienRegistra;
        this.nombreDispositivoDondeRegistra = nombreDispositivoDondeRegistra;
        this.ipDispositivoDondeRegistra = ipDispositivoDondeRegistra;
        this.macDispositivoDondeRegistra = macDispositivoDondeRegistra;
        this.codigoMovimiento = codigoMovimiento;
        this.descMovimiento = descMovimiento;
        this.detalleMovimiento = detalleMovimiento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuarioQuienRegistra() {
        return usuarioQuienRegistra;
    }

    public void setUsuarioQuienRegistra(Usuario usuarioQuienRegistra) {
        this.usuarioQuienRegistra = usuarioQuienRegistra;
    }

    public String getNombreDispositivoDondeRegistra() {
        return nombreDispositivoDondeRegistra;
    }

    public void setNombreDispositivoDondeRegistra(String nombreDispositivoDondeRegistra) {
        this.nombreDispositivoDondeRegistra = nombreDispositivoDondeRegistra;
    }

    public String getIpDispositivoDondeRegistra() {
        return ipDispositivoDondeRegistra;
    }

    public void setIpDispositivoDondeRegistra(String ipDispositivoDondeRegistra) {
        this.ipDispositivoDondeRegistra = ipDispositivoDondeRegistra;
    }

    public String getMacDispositivoDondeRegistra() {
        return macDispositivoDondeRegistra;
    }

    public void setMacDispositivoDondeRegistra(String macDispositivoDondeRegistra) {
        this.macDispositivoDondeRegistra = macDispositivoDondeRegistra;
    }

    public String getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(String codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public String getDescMovimiento() {
        return descMovimiento;
    }

    public void setDescMovimiento(String descMovimiento) {
        this.descMovimiento = descMovimiento;
    }

    public String getDetalleMovimiento() {
        return detalleMovimiento;
    }

    public void setDetalleMovimiento(String detalleMovimiento) {
        this.detalleMovimiento = detalleMovimiento;
    }

}