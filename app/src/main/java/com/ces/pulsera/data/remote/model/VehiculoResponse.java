package com.ces.pulsera.data.remote.model;

import com.ces.pulsera.data.local.ResponseVehiculo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VehiculoResponse {
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("objeto")
    @Expose
    private List<ResponseVehiculo> objeto;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<ResponseVehiculo> getObjeto() {
        return objeto;
    }

    public void setObjeto(List<ResponseVehiculo> objeto) {
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
