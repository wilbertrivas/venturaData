
package ModuloPalero.Model;

import ModuloPersonal.Model.CargoNomina;
import Sistema.Model.Usuario;

public class ConfiguracionLiquidacion {
    private String codigo;
    private CargoNomina cargoNomina;
    private String fechaInicio;
    private String fechaFinalizacion;
    private int cantidadDias;
    private String ano;
    private String descripcion;
    private int valorTonelada;
    private int cantidadToneladaSalario;
    private Usuario usuario;
    private String estado;

    public ConfiguracionLiquidacion() {
    }

    public ConfiguracionLiquidacion(String codigo, CargoNomina cargoNomina, String fechaInicio, String fechaFinalizacion, int cantidadDias, String ano, String descripcion, int valorTonelada, int cantidadToneladaSalario, Usuario usuario, String estado) {
        this.codigo = codigo;
        this.cargoNomina = cargoNomina;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.cantidadDias = cantidadDias;
        this.ano = ano;
        this.descripcion = descripcion;
        this.valorTonelada = valorTonelada;
        this.cantidadToneladaSalario = cantidadToneladaSalario;
        this.usuario = usuario;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public CargoNomina getCargoNomina() {
        return cargoNomina;
    }

    public void setCargoNomina(CargoNomina cargoNomina) {
        this.cargoNomina = cargoNomina;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public int getCantidadDias() {
        return cantidadDias;
    }

    public void setCantidadDias(int cantidadDias) {
        this.cantidadDias = cantidadDias;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getValorTonelada() {
        return valorTonelada;
    }

    public void setValorTonelada(int valorTonelada) {
        this.valorTonelada = valorTonelada;
    }

    public int getCantidadToneladaSalario() {
        return cantidadToneladaSalario;
    }

    public void setCantidadToneladaSalario(int cantidadToneladaSalario) {
        this.cantidadToneladaSalario = cantidadToneladaSalario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }    
    
}
