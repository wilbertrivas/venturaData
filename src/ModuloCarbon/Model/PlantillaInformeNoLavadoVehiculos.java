package ModuloCarbon.Model;


public class PlantillaInformeNoLavadoVehiculos {
    private String cliente;
    private String articulo;
    private String zonaOperacion;
    private String equipo;
    private String motivoNoLavado;
    private String cantidadVehículo;

    public PlantillaInformeNoLavadoVehiculos() {
    }

    public PlantillaInformeNoLavadoVehiculos(String cliente, String articulo, String zonaOperacion, String motivoNoLavado, String cantidadVehículo) {
        this.cliente = cliente;
        this.articulo = articulo;
        this.zonaOperacion = zonaOperacion;
        this.motivoNoLavado = motivoNoLavado;
        this.cantidadVehículo = cantidadVehículo;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getZonaOperacion() {
        return zonaOperacion;
    }

    public void setZonaOperacion(String zonaOperacion) {
        this.zonaOperacion = zonaOperacion;
    }

    public String getMotivoNoLavado() {
        return motivoNoLavado;
    }

    public void setMotivoNoLavado(String motivoNoLavado) {
        this.motivoNoLavado = motivoNoLavado;
    }

    public String getCantidadVehículo() {
        return cantidadVehículo;
    }

    public void setCantidadVehículo(String cantidadVehículo) {
        this.cantidadVehículo = cantidadVehículo;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
    
    
}
