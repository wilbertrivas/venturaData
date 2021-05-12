package ModuloEquipo.Model;

import Catalogo.Model.MotivoNoLavado;

public class MvtoEquipoAgregar extends MvtoEquipo{
    private String lavadoVehiculo;
    private String lavadoVehiculo_Observacion;
    private MotivoNoLavado motivoNoLavado;

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

    public MotivoNoLavado getMotivoNoLavado() {
        return motivoNoLavado;
    }

    public void setMotivoNoLavado(MotivoNoLavado motivoNoLavado) {
        this.motivoNoLavado = motivoNoLavado;
    }
}
