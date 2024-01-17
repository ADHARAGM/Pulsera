
package com.ces.pulsera.data.local;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//@Generated("jsonschema2pojo")
public class RequestVehiculo {

    public RequestVehiculo(int tipo, String placa){
        super();
        this.tipo=tipo;
        this.placa=placa;
    }
    @SerializedName("tipo")
    @Expose
    private Integer tipo;
    @SerializedName("placa")
    @Expose
    private String placa;

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

}
