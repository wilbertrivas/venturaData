package ModuloCarbon.Model; 

import Catalogo.Model.Articulo;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Transportadora;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.CentroCosto;
import Catalogo.Model.CentroCostoMayor;
import Catalogo.Model.Motonave;
import Catalogo.Model.Cliente;
import Catalogo.Model.LaborRealizada;
import Sistema.Model.Usuario;
import java.util.ArrayList;

public class MvtoCarbon {
    private String codigo;
    private CentroOperacion centroOperacion;
    private CentroCostoAuxiliar centroCostoAuxiliar;
    private CentroCosto centroCosto;
    private LaborRealizada laborRealizada;
    private Articulo articulo;
    private Cliente cliente;
    private Transportadora transportadora;
    private Motonave motonave;
    private String fechaRegistro;
    private String Numero_orden;
    private String deposito;
    private String consecutivo;
    private String placa;
    private String pesoVacio;
    private String pesoLleno;
    private String pesoNeto;
    private String fechaEntradaVehiculo;
    private String fecha_SalidaVehiculo;
    private String fechaInicioDescargue;
    private String fechaFinDescargue;
    private String cantidadHorasDescargue;
    private Usuario usuarioRegistroMovil;
    private String observacion;
    private EstadoMvtoCarbon estadoMvtoCarbon;
    private String conexionPesoCcarga;
    private String registroManual;
    private Usuario usuarioRegistraManual;
    private CentroCostoAuxiliar centroCostoAuxiliarDestino;
    private CentroCostoMayor centroCostoMayor;
    private String lavadoVehiculo;
    private String lavadoVehiculo_Observacion;
    private ArrayList<MvtoCarbon_ListadoEquipos> listadoMvtoCarbon_ListadoEquipos;
    //Reporte
    private String mes;

    public MvtoCarbon() {
    }

    public MvtoCarbon(String codigo, CentroOperacion centroOperacion, CentroCostoAuxiliar centroCostoAuxiliar, Articulo articulo, Cliente cliente, Transportadora transportadora, Motonave motonave, String fechaRegistro, String Numero_orden, String deposito, String consecutivo, String placa, String pesoVacio, String pesoLleno, String pesoNeto, String fechaEntradaVehiculo, String fecha_SalidaVehiculo, String fechaInicioDescargue, String fechaFinDescargue, Usuario usuarioRegistroMovil, String observacion, EstadoMvtoCarbon estadoMvtoCarbon, String conexionPesoCcarga, String registroManual, Usuario usuarioRegistraManual, ArrayList<MvtoCarbon_ListadoEquipos> listadoMvtoCarbon_ListadoEquipos) {
        this.codigo = codigo;
        this.centroOperacion = centroOperacion;
        this.centroCostoAuxiliar = centroCostoAuxiliar;
        this.articulo = articulo;
        this.cliente = cliente;
        this.transportadora = transportadora;
        this.motonave = motonave;
        this.fechaRegistro = fechaRegistro;
        this.Numero_orden = Numero_orden;
        this.deposito = deposito;
        this.consecutivo = consecutivo;
        this.placa = placa;
        this.pesoVacio = pesoVacio;
        this.pesoLleno = pesoLleno;
        this.pesoNeto = pesoNeto;
        this.fechaEntradaVehiculo = fechaEntradaVehiculo;
        this.fecha_SalidaVehiculo = fecha_SalidaVehiculo;
        this.fechaInicioDescargue = fechaInicioDescargue;
        this.fechaFinDescargue = fechaFinDescargue;
        this.usuarioRegistroMovil = usuarioRegistroMovil;
        this.observacion = observacion;
        this.estadoMvtoCarbon = estadoMvtoCarbon;
        this.conexionPesoCcarga = conexionPesoCcarga;
        this.registroManual = registroManual;
        this.usuarioRegistraManual = usuarioRegistraManual;
        this.listadoMvtoCarbon_ListadoEquipos = listadoMvtoCarbon_ListadoEquipos;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public MvtoCarbon(String codigo, CentroOperacion centroOperacion, CentroCostoAuxiliar centroCostoAuxiliar, Articulo articulo, Cliente cliente, Transportadora transportadora, Motonave motonave, String fechaRegistro, String Numero_orden, String deposito, String consecutivo, String placa, String pesoVacio, String pesoLleno, String pesoNeto, String fechaEntradaVehiculo, String fecha_SalidaVehiculo, String fechaInicioDescargue, String fechaFinDescargue, String cantidadHorasDescargue, Usuario usuarioRegistroMovil, String observacion, EstadoMvtoCarbon estadoMvtoCarbon, String conexionPesoCcarga, String registroManual, Usuario usuarioRegistraManual, ArrayList<MvtoCarbon_ListadoEquipos> listadoMvtoCarbon_ListadoEquipos) {
        this.codigo = codigo;
        this.centroOperacion = centroOperacion;
        this.centroCostoAuxiliar = centroCostoAuxiliar;
        this.articulo = articulo;
        this.cliente = cliente;
        this.transportadora = transportadora;
        this.motonave = motonave;
        this.fechaRegistro = fechaRegistro;
        this.Numero_orden = Numero_orden;
        this.deposito = deposito;
        this.consecutivo = consecutivo;
        this.placa = placa;
        this.pesoVacio = pesoVacio;
        this.pesoLleno = pesoLleno;
        this.pesoNeto = pesoNeto;
        this.fechaEntradaVehiculo = fechaEntradaVehiculo;
        this.fecha_SalidaVehiculo = fecha_SalidaVehiculo;
        this.fechaInicioDescargue = fechaInicioDescargue;
        this.fechaFinDescargue = fechaFinDescargue;
        this.cantidadHorasDescargue = cantidadHorasDescargue;
        this.usuarioRegistroMovil = usuarioRegistroMovil;
        this.observacion = observacion;
        this.estadoMvtoCarbon = estadoMvtoCarbon;
        this.conexionPesoCcarga = conexionPesoCcarga;
        this.registroManual = registroManual;
        this.usuarioRegistraManual = usuarioRegistraManual;
        this.listadoMvtoCarbon_ListadoEquipos = listadoMvtoCarbon_ListadoEquipos;
    }

    public CentroCosto getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(CentroCosto centroCosto) {
        this.centroCosto = centroCosto;
    }

    public LaborRealizada getLaborRealizada() {
        return laborRealizada;
    }

    public void setLaborRealizada(LaborRealizada laborRealizada) {
        this.laborRealizada = laborRealizada;
    }

    public String getCantidadHorasDescargue() {
        return cantidadHorasDescargue;
    }

    public void setCantidadHorasDescargue(String cantidadHorasDescargue) {
        this.cantidadHorasDescargue = cantidadHorasDescargue;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public CentroOperacion getCentroOperacion() {
        return centroOperacion;
    }

    public void setCentroOperacion(CentroOperacion centroOperacion) {
        this.centroOperacion = centroOperacion;
    }

    public CentroCostoAuxiliar getCentroCostoAuxiliar() {
        return centroCostoAuxiliar;
    }

    public void setCentroCostoAuxiliar(CentroCostoAuxiliar centroCostoAuxiliar) {
        this.centroCostoAuxiliar = centroCostoAuxiliar;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Motonave getMotonave() {
        return motonave;
    }

    public void setMotonave(Motonave motonave) {
        this.motonave = motonave;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNumero_orden() {
        return Numero_orden;
    }

    public void setNumero_orden(String Numero_orden) {
        this.Numero_orden = Numero_orden;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getPesoVacio() {
        return pesoVacio;
    }

    public void setPesoVacio(String pesoVacio) {
        this.pesoVacio = pesoVacio;
    }

    public String getPesoLleno() {
        return pesoLleno;
    }

    public void setPesoLleno(String pesoLleno) {
        this.pesoLleno = pesoLleno;
    }

    public String getPesoNeto() {
        return pesoNeto;
    }

    public void setPesoNeto(String pesoNeto) {
        this.pesoNeto = pesoNeto;
    }

    public String getFechaEntradaVehiculo() {
        return fechaEntradaVehiculo;
    }

    public void setFechaEntradaVehiculo(String fechaEntradaVehiculo) {
        this.fechaEntradaVehiculo = fechaEntradaVehiculo;
    }

    public String getFecha_SalidaVehiculo() {
        return fecha_SalidaVehiculo;
    }

    public void setFecha_SalidaVehiculo(String fecha_SalidaVehiculo) {
        this.fecha_SalidaVehiculo = fecha_SalidaVehiculo;
    }

    public String getFechaInicioDescargue() {
        return fechaInicioDescargue;
    }

    public void setFechaInicioDescargue(String fechaInicioDescargue) {
        this.fechaInicioDescargue = fechaInicioDescargue;
    }

    public String getFechaFinDescargue() {
        return fechaFinDescargue;
    }

    public void setFechaFinDescargue(String fechaFinDescargue) {
        this.fechaFinDescargue = fechaFinDescargue;
    }

    public Usuario getUsuarioRegistroMovil() {
        return usuarioRegistroMovil;
    }

    public void setUsuarioRegistroMovil(Usuario usuarioRegistroMovil) {
        this.usuarioRegistroMovil = usuarioRegistroMovil;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public EstadoMvtoCarbon getEstadoMvtoCarbon() {
        return estadoMvtoCarbon;
    }

    public void setEstadoMvtoCarbon(EstadoMvtoCarbon estadoMvtoCarbon) {
        this.estadoMvtoCarbon = estadoMvtoCarbon;
    }

    public String getConexionPesoCcarga() {
        return conexionPesoCcarga;
    }

    public void setConexionPesoCcarga(String conexionPesoCcarga) {
        this.conexionPesoCcarga = conexionPesoCcarga;
    }

    public String getRegistroManual() {
        return registroManual;
    }

    public void setRegistroManual(String registroManual) {
        this.registroManual = registroManual;
    }

    public Usuario getUsuarioRegistraManual() {
        return usuarioRegistraManual;
    }

    public void setUsuarioRegistraManual(Usuario usuarioRegistraManual) {
        this.usuarioRegistraManual = usuarioRegistraManual;
    }

    public ArrayList<MvtoCarbon_ListadoEquipos> getListadoMvtoCarbon_ListadoEquipos() {
        return listadoMvtoCarbon_ListadoEquipos;
    }

    public void setListadoMvtoCarbon_ListadoEquipos(ArrayList<MvtoCarbon_ListadoEquipos> listadoMvtoCarbon_ListadoEquipos) {
        this.listadoMvtoCarbon_ListadoEquipos = listadoMvtoCarbon_ListadoEquipos;
    }

    public CentroCostoAuxiliar getCentroCostoAuxiliarDestino() {
        return centroCostoAuxiliarDestino;
    }

    public void setCentroCostoAuxiliarDestino(CentroCostoAuxiliar centroCostoAuxiliarDestino) {
        this.centroCostoAuxiliarDestino = centroCostoAuxiliarDestino;
    }

    public CentroCostoMayor getCentroCostoMayor() {
        return centroCostoMayor;
    }

    public void setCentroCostoMayor(CentroCostoMayor centroCostoMayor) {
        this.centroCostoMayor = centroCostoMayor;
    }

    public String getLavadoVehiculo() {
        return lavadoVehiculo;
    }

    public void setLavadoVehiculo(String lavadoVehiculo) {
        this.lavadoVehiculo = lavadoVehiculo;
    }

    public String getLavadoVehiculo_Observacion() {
        return lavadoVehiculo_Observacion;
    }

    public void setLavadoVehiculo_Observacion(String lavadoVehiculo_Observacion) {
        this.lavadoVehiculo_Observacion = lavadoVehiculo_Observacion;
    }
    
}
