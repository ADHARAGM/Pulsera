package com.ces.pulsera.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MacResponse {
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("objeto")
    @Expose
    private List<ResponseMac> objeto;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<ResponseMac> getObjeto() {
        return objeto;
    }

    public void setObjeto(List<ResponseMac> objeto) {
        this.objeto = objeto;
    }
    /*
    private List<ResponseVehiculo> objeto;

    public List<ResponseVehiculo> getObjeto() {
        return objeto;
    }

    public void setObjeto(List<ResponseVehiculo> objeto) {
        this.objeto = objeto;
    }*/
}
