package ModuloEquipo.Model;

import Catalogo.Model.LaborRealizada;
import Catalogo.Model.MotivoParada;
import Catalogo.Model.ClienteRecobro;
import Catalogo.Model.ProveedorEquipo;
import Catalogo.Model.Cliente;
import Catalogo.Model.CentroCostoAuxiliar;
import Catalogo.Model.Articulo;
import Catalogo.Model.CentroCosto;
import Catalogo.Model.CentroCostoMayor;
import Catalogo.Model.CentroOperacion;
import Catalogo.Model.Motonave;
import Sistema.Model.Usuario;

public class MvtoEquipo {
    private String mes;
    private String codigo;
    private AsignacionEquipo asignacionEquipo;
    private String fechaRegistro;
    private ProveedorEquipo proveedorEquipo;
    private String numeroOrden;
    private CentroOperacion centroOperacion;
    private CentroCostoAuxiliar centroCostoAuxiliar;
    private CentroCosto CentroCosto;
    private LaborRealizada laborRealizada;
    private Cliente cliente;
    private Articulo articulo;
    private Motonave motonave;
    private String fechaHoraInicio;
    private String fechaHoraFin;
    private String totalMinutos;
    private String valorHora;
    private String costoTotal;
    private Recobro recobro;
    private ClienteRecobro clienteRecobro;
    private String costoTotalRecobroCliente;
    private Usuario usuarioQuieRegistra;
    private Usuario usuarioAutorizaRecobro;
    private AutorizacionRecobro autorizacionRecobro;
    private String observacionAutorizacion;
    private String inactividad;
    private CausaInactividad causaInactividad;
    private Usuario usuarioInactividad;
    private String motivoParadaEstado;
    private MotivoParada motivoParada;
    private String observacionMvtoEquipo;
    private String estado;
    private String desdeCarbon;
    private String centroCostoDescripción;
    private CentroCostoAuxiliar centroCostoAuxiliarDestino;
    private CentroCostoMayor centroCostoMayor;

    
    public MvtoEquipo() {
    }

    public MvtoEquipo(String codigo, AsignacionEquipo asignacionEquipo, String fechaRegistro, ProveedorEquipo proveedorEquipo, String numeroOrden, CentroOperacion centroOperacion, CentroCostoAuxiliar centroCostoAuxiliar, CentroCosto CentroCosto, LaborRealizada laborRealizada, Cliente cliente, Articulo articulo, String fechaHoraInicio, String fechaHoraFin, String totalMinutos, String valorHora, String costoTotal, Recobro recobro, ClienteRecobro clienteRecobro, String costoTotalRecobroCliente, Usuario usuarioQuieRegistra, Usuario usuarioAutorizaRecobro, AutorizacionRecobro autorizacionRecobro, String observacionAutorizacion, String inactividad, CausaInactividad causaInactividad, Usuario usuarioInactividad, String motivoParadaEstado, MotivoParada motivoParada, String observacionMvtoEquipo, String estado, String desdeCarbon) {
        this.codigo = codigo;
        this.asignacionEquipo = asignacionEquipo;
        this.fechaRegistro = fechaRegistro;
        this.proveedorEquipo = proveedorEquipo;
        this.numeroOrden = numeroOrden;
        this.centroOperacion = centroOperacion;
        this.centroCostoAuxiliar = centroCostoAuxiliar;
        this.CentroCosto = CentroCosto;
        this.laborRealizada = laborRealizada;
        this.cliente = cliente;
        this.articulo = articulo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.totalMinutos = totalMinutos;
        this.valorHora = valorHora;
        this.costoTotal = costoTotal;
        this.recobro = recobro;
        this.clienteRecobro = clienteRecobro;
        this.costoTotalRecobroCliente = costoTotalRecobroCliente;
        this.usuarioQuieRegistra = usuarioQuieRegistra;
        this.usuarioAutorizaRecobro = usuarioAutorizaRecobro;
        this.autorizacionRecobro = autorizacionRecobro;
        this.observacionAutorizacion = observacionAutorizacion;
        this.inactividad = inactividad;
        this.causaInactividad = causaInactividad;
        this.usuarioInactividad = usuarioInactividad;
        this.motivoParadaEstado = motivoParadaEstado;
        this.motivoParada = motivoParada;
        this.observacionMvtoEquipo = observacionMvtoEquipo;
        this.estado = estado;
        this.desdeCarbon = desdeCarbon;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public AsignacionEquipo getAsignacionEquipo() {
        return asignacionEquipo;
    }

    public void setAsignacionEquipo(AsignacionEquipo asignacionEquipo) {
        this.asignacionEquipo = asignacionEquipo;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public ProveedorEquipo getProveedorEquipo() {
        return proveedorEquipo;
    }

    public void setProveedorEquipo(ProveedorEquipo proveedorEquipo) {
        this.proveedorEquipo = proveedorEquipo;
    }

    public String getNumeroOrden() {
        return numeroOrden;
    }

    public void setNumeroOrden(String numeroOrden) {
        this.numeroOrden = numeroOrden;
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

    public CentroCosto getCentroCosto() {
        return CentroCosto;
    }

    public void setCentroCosto(CentroCosto CentroCosto) {
        this.CentroCosto = CentroCosto;
    }

    public LaborRealizada getLaborRealizada() {
        return laborRealizada;
    }

    public void setLaborRealizada(LaborRealizada laborRealizada) {
        this.laborRealizada = laborRealizada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
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

    public String getTotalMinutos() {
        return totalMinutos;
    }

    public void setTotalMinutos(String totalMinutos) {
        this.totalMinutos = totalMinutos;
    }

    public String getValorHora() {
        return valorHora;
    }

    public void setValorHora(String valorHora) {
        this.valorHora = valorHora;
    }

    public String getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(String costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Recobro getRecobro() {
        return recobro;
    }

    public void setRecobro(Recobro recobro) {
        this.recobro = recobro;
    }

    public ClienteRecobro getClienteRecobro() {
        return clienteRecobro;
    }

    public void setClienteRecobro(ClienteRecobro clienteRecobro) {
        this.clienteRecobro = clienteRecobro;
    }

    public String getCostoTotalRecobroCliente() {
        return costoTotalRecobroCliente;
    }

    public void setCostoTotalRecobroCliente(String costoTotalRecobroCliente) {
        this.costoTotalRecobroCliente = costoTotalRecobroCliente;
    }

    public Usuario getUsuarioQuieRegistra() {
        return usuarioQuieRegistra;
    }

    public void setUsuarioQuieRegistra(Usuario usuarioQuieRegistra) {
        this.usuarioQuieRegistra = usuarioQuieRegistra;
    }

    public Usuario getUsuarioAutorizaRecobro() {
        return usuarioAutorizaRecobro;
    }

    public void setUsuarioAutorizaRecobro(Usuario usuarioAutorizaRecobro) {
        this.usuarioAutorizaRecobro = usuarioAutorizaRecobro;
    }

    public AutorizacionRecobro getAutorizacionRecobro() {
        return autorizacionRecobro;
    }

    public void setAutorizacionRecobro(AutorizacionRecobro autorizacionRecobro) {
        this.autorizacionRecobro = autorizacionRecobro;
    }

    public String getObservacionAutorizacion() {
        return observacionAutorizacion;
    }

    public void setObservacionAutorizacion(String observacionAutorizacion) {
        this.observacionAutorizacion = observacionAutorizacion;
    }

    public String getInactividad() {
        return inactividad;
    }

    public void setInactividad(String inactividad) {
        this.inactividad = inactividad;
    }

    public CausaInactividad getCausaInactividad() {
        return causaInactividad;
    }

    public void setCausaInactividad(CausaInactividad causaInactividad) {
        this.causaInactividad = causaInactividad;
    }

    public Usuario getUsuarioInactividad() {
        return usuarioInactividad;
    }

    public void setUsuarioInactividad(Usuario usuarioInactividad) {
        this.usuarioInactividad = usuarioInactividad;
    }

    public String getMotivoParadaEstado() {
        return motivoParadaEstado;
    }

    public void setMotivoParadaEstado(String motivoParadaEstado) {
        this.motivoParadaEstado = motivoParadaEstado;
    }

    public MotivoParada getMotivoParada() {
        return motivoParada;
    }

    public void setMotivoParada(MotivoParada motivoParada) {
        this.motivoParada = motivoParada;
    }

    public String getObservacionMvtoEquipo() {
        return observacionMvtoEquipo;
    }

    public void setObservacionMvtoEquipo(String observacionMvtoEquipo) {
        this.observacionMvtoEquipo = observacionMvtoEquipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDesdeCarbon() {
        return desdeCarbon;
    }

    public void setDesdeCarbon(String desdeCarbon) {
        this.desdeCarbon = desdeCarbon;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public Motonave getMotonave() {
        return motonave;
    }

    public void setMotonave(Motonave motonave) {
        this.motonave = motonave;
    }

    public String getCentroCostoDescripción() {
        return centroCostoDescripción;
    }

    public void setCentroCostoDescripción(String centroCostoDescripción) {
        this.centroCostoDescripción = centroCostoDescripción;
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

   
}
