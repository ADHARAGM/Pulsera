package com.ces.pulsera.data.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestGuardaAlerta {
    public RequestGuardaAlerta(String id_persona, Double latitud, Double longitud) {
        this.id_persona = id_persona;
        this.latitud = latitud;
        this.longitud = longitud;
    }
    public RequestGuardaAlerta(String id_persona, Double latitud, Double longitud,Character tipo) {
        this.id_persona = id_persona;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tipo=tipo;
    }
    @SerializedName("tipo")
    @Expose
    private Character tipo;
    @SerializedName("id_persona")
    @Expose
    private String id_persona;
    @SerializedName("latitud")
    @Expose
    private Double latitud;
    @SerializedName("longitud")
    @Expose
    private Double longitud;
    public Character getTipo() {
        return tipo;
    }
    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }
    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
