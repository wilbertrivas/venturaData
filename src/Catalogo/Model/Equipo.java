package Catalogo.Model;
  
import ModuloPersonal.Model.Persona;
import java.util.ArrayList;

public class Equipo {   
    private String codigo;
    private TipoEquipo tipoEquipo;
    private String codigo_barra;
    private String referencia;
    private String producto;
    private String capacidad;
    private String marca;
    private String modelo;
    private String serial;
    private String descripcion;
    private ClasificadorEquipo clasificador1;
    private ClasificadorEquipo clasificador2;
    private ProveedorEquipo proveedorEquipo;
    private Pertenencia pertenenciaEquipo;
    private String observacion;
    private String estado;
    private TarifaEquipo tarifaEquipoAnterior;
    private TarifaEquipo tarifaEquipoNueva;
    private String activoFijo_codigo;
    private String activoFijo_referencia;
    private String activoFijo_descripcion;
    private CentroCostoEquipo centroCostoEquipo;
    private ArrayList<Persona> listadoPersonas;
    
    public Equipo() {
    }

    public Equipo(String codigo, TipoEquipo tipoEquipo, String codigo_barra, String referencia, String producto, String capacidad, String marca, String modelo, String serial, String descripcion, ClasificadorEquipo clasificador1, ClasificadorEquipo clasificador2, ProveedorEquipo proveedorEquipo, Pertenencia pertenenciaEquipo, String observacion, String estado, TarifaEquipo tarifaEquipoAnterior, TarifaEquipo tarifaEquipoNueva, String activoFijo_codigo, String activoFijo_referencia, String activoFijo_descripcion) {
        this.codigo = codigo;
        this.tipoEquipo = tipoEquipo;
        this.codigo_barra = codigo_barra;
        this.referencia = referencia;
        this.producto = producto;
        this.capacidad = capacidad;
        this.marca = marca;
        this.modelo = modelo;
        this.serial = serial;
        this.descripcion = descripcion;
        this.clasificador1 = clasificador1;
        this.clasificador2 = clasificador2;
        this.proveedorEquipo = proveedorEquipo;
        this.pertenenciaEquipo = pertenenciaEquipo;
        this.observacion = observacion;
        this.estado = estado;
        this.tarifaEquipoAnterior = tarifaEquipoAnterior;
        this.tarifaEquipoNueva = tarifaEquipoNueva;
        this.activoFijo_codigo = activoFijo_codigo;
        this.activoFijo_referencia = activoFijo_referencia;
        this.activoFijo_descripcion = activoFijo_descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoEquipo getTipoEquipo() {
        return tipoEquipo;
    }

    public void setTipoEquipo(TipoEquipo tipoEquipo) {
        this.tipoEquipo = tipoEquipo;
    }

    public String getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ClasificadorEquipo getClasificador1() {
        return clasificador1;
    }

    public void setClasificador1(ClasificadorEquipo clasificador1) {
        this.clasificador1 = clasificador1;
    }

    public ClasificadorEquipo getClasificador2() {
        return clasificador2;
    }

    public void setClasificador2(ClasificadorEquipo clasificador2) {
        this.clasificador2 = clasificador2;
    }

    public ProveedorEquipo getProveedorEquipo() {
        return proveedorEquipo;
    }

    public void setProveedorEquipo(ProveedorEquipo proveedorEquipo) {
        this.proveedorEquipo = proveedorEquipo;
    }

    public Pertenencia getPertenenciaEquipo() {
        return pertenenciaEquipo;
    }

    public void setPertenenciaEquipo(Pertenencia pertenenciaEquipo) {
        this.pertenenciaEquipo = pertenenciaEquipo;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public TarifaEquipo getTarifaEquipoAnterior() {
        return tarifaEquipoAnterior;
    }

    public void setTarifaEquipoAnterior(TarifaEquipo tarifaEquipoAnterior) {
        this.tarifaEquipoAnterior = tarifaEquipoAnterior;
    }

    public TarifaEquipo getTarifaEquipoNueva() {
        return tarifaEquipoNueva;
    }

    public void setTarifaEquipoNueva(TarifaEquipo tarifaEquipoNueva) {
        this.tarifaEquipoNueva = tarifaEquipoNueva;
    }

    public String getActivoFijo_codigo() {
        return activoFijo_codigo;
    }

    public void setActivoFijo_codigo(String activoFijo_codigo) {
        this.activoFijo_codigo = activoFijo_codigo;
    }

    public String getActivoFijo_referencia() {
        return activoFijo_referencia;
    }

    public void setActivoFijo_referencia(String activoFijo_referencia) {
        this.activoFijo_referencia = activoFijo_referencia;
    }

    public String getActivoFijo_descripcion() {
        return activoFijo_descripcion;
    }

    public void setActivoFijo_descripcion(String activoFijo_descripcion) {
        this.activoFijo_descripcion = activoFijo_descripcion;
    }

    public CentroCostoEquipo getCentroCostoEquipo() {
        return centroCostoEquipo;
    }

    public void setCentroCostoEquipo(CentroCostoEquipo centroCostoEquipo) {
        this.centroCostoEquipo = centroCostoEquipo;
    }

    public ArrayList<Persona> getListadoPersonas() {
        return listadoPersonas;
    }

    public void setListadoPersonas(ArrayList<Persona> listadoPersonas) {
        this.listadoPersonas = listadoPersonas;
    }

    
    
}
