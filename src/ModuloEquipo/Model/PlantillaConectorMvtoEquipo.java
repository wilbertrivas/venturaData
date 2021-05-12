/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEquipo.Model;

/**
 *
 * @author wrivas
 */
public class PlantillaConectorMvtoEquipo {
    private String subcentro;
    private String centroCostosAuxiliar;
    private String cliente;
    private String articulo;
    private String unidadNegocio;
    private String centroCosto;
    private String total;
    private String porcentaje;

    public PlantillaConectorMvtoEquipo() {
    }

    public PlantillaConectorMvtoEquipo(String subcentro, String centroCostosAuxiliar, String cliente, String articulo, String unidadNegocio, String centroCosto, String total, String porcentaje) {
        this.subcentro = subcentro;
        this.centroCostosAuxiliar = centroCostosAuxiliar;
        this.cliente = cliente;
        this.articulo = articulo;
        this.unidadNegocio = unidadNegocio;
        this.centroCosto = centroCosto;
        this.total = total;
        this.porcentaje = porcentaje;
    }

    public String getSubcentro() {
        return subcentro;
    }

    public void setSubcentro(String subcentro) {
        this.subcentro = subcentro;
    }

    public String getCentroCostosAuxiliar() {
        return centroCostosAuxiliar;
    }

    public void setCentroCostosAuxiliar(String centroCostosAuxiliar) {
        this.centroCostosAuxiliar = centroCostosAuxiliar;
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

    public String getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public String getCentroCosto() {
        return centroCosto;
    }

    public void setCentroCosto(String centroCosto) {
        this.centroCosto = centroCosto;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }
    
    
   
}
