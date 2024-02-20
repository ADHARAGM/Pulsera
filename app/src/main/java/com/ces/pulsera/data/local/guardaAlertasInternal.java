package com.ces.pulsera.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName ="guardaAlertasInternal" )
public class guardaAlertasInternal {
    public guardaAlertasInternal( String id_persona, double latitud, double longitud) {

        this.id_persona = id_persona;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @SerializedName("id_alerta")
    @Expose
    private int id_alerta = 0; // or foodId: Int? = null

    @SerializedName("id_persona")
    @Expose
    private String id_persona;
    @SerializedName("latitud")
    @Expose
    private double latitud;
    @SerializedName("longitud")
    @Expose
    private double longitud;

    public int getId_alerta() {
        return id_alerta;
    }

    public void setId_alerta(int id_alerta) {
        this.id_alerta = id_alerta;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
