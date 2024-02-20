package com.ces.pulsera.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostUbicationResponse {
    @SerializedName("resultado")
    @Expose
    private Boolean resultado;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;

    public Boolean getResultado() {
        return resultado;
    }

    public void setResultado(Boolean resultado) {
        this.resultado = resultado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
