package Catalogo.Model;

public class ListadoSitiosZonaTrabajo {
    private ZonaTrabajo zonaTrabajo;
    private CentroCostoAuxiliar centroCostoAuxiliar;
    private String estado;

    public ListadoSitiosZonaTrabajo() {
    }

    public ListadoSitiosZonaTrabajo(ZonaTrabajo zonaTrabajo, CentroCostoAuxiliar centroCostoAuxiliar, String estado) {
        this.zonaTrabajo = zonaTrabajo;
        this.centroCostoAuxiliar = centroCostoAuxiliar;
        this.estado = estado;
    }

    public ZonaTrabajo getZonaTrabajo() {
        return zonaTrabajo;
    }

    public void setZonaTrabajo(ZonaTrabajo zonaTrabajo) {
        this.zonaTrabajo = zonaTrabajo;
    }

    public CentroCostoAuxiliar getCentroCostoAuxiliar() {
        return centroCostoAuxiliar;
    }

    public void setCentroCostoAuxiliar(CentroCostoAuxiliar centroCostoAuxiliar) {
        this.centroCostoAuxiliar = centroCostoAuxiliar;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

   
    
}
