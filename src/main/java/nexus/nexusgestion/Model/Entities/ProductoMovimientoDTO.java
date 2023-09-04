package nexus.nexusgestion.Model.Entities;

import java.util.List;

public class ProductoMovimientoDTO {

    private List<String> codigoIdentificacion;
    private List<Integer> cantidades;
    private List<String> origen;


    public List<String> getCodigoIdentificacion() {
        return codigoIdentificacion;
    }
    public void setCodigoIdentificacion(List<String> codigoIdentificacion) {
        this.codigoIdentificacion = codigoIdentificacion;
    }
   
    public List<String> getOrigen() {
        return origen;
    }
    public void setOrigen(List<String> origen) {
        this.origen = origen;
    }
    public List<Integer> getCantidades() {
        return cantidades;
    }
    public void setCantidades(List<Integer> cantidades) {
        this.cantidades = cantidades;
    }

    

    
}
