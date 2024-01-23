
package com.ces.pulsera.data.remote.model;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName ="macInformation" )
//@Generated("jsonschema2pojo")
public class ResponseMac {

    @PrimaryKey
    @NonNull
    @SerializedName("idPersona")
    @Expose
    private String idPersona;

    @SerializedName("mac")
    @Expose
    private String mac;

    @NonNull
    public String getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(@NonNull String idPersona) {
        this.idPersona = idPersona;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }
}
