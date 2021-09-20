package ModuloPersonal.Model;

import Catalogo.Model.Compañia;
import Catalogo.Model.Equipo;

public class Persona {

    private String codigo;
    private TipoDocumento tipoDocumento;
    private String nombre;
    private String apellido;
    private String telefono;
    private CargoNomina cargoNomina;
    private TipoContrato tipoContrato;
    private Compañia compania;
    private Equipo equipo;
    private String estado;

    private String pesoTrabajadoKilogramo;

    public Persona() {
    }

    public Persona(String codigo, TipoDocumento tipoDocumento, String nombre, String apellido, String telefono, CargoNomina cargoNomina, TipoContrato tipoContrato, Compañia compania, Equipo equipo, String estado) {
        this.codigo = codigo;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.cargoNomina = cargoNomina;
        this.tipoContrato = tipoContrato;
        this.compania = compania;
        this.equipo = equipo;
        this.estado = estado;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public CargoNomina getCargoNomina() {
        return cargoNomina;
    }

    public void setCargoNomina(CargoNomina cargoNomina) {
        this.cargoNomina = cargoNomina;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Compañia getCompania() {
        return compania;
    }

    public void setCompania(Compañia compania) {
        this.compania = compania;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPesoTrabajadoKilogramo() {
        return pesoTrabajadoKilogramo;
    }

    public void setPesoTrabajadoKilogramo(String pesoTrabajadoKilogramo) {
        this.pesoTrabajadoKilogramo = pesoTrabajadoKilogramo;
    }

}
