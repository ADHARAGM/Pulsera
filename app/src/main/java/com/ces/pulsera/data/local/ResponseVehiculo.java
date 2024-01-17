
package com.ces.pulsera.data.local;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
@Entity(tableName ="Vehiculo" )
//@Generated("jsonschema2pojo")
public class ResponseVehiculo {

    @PrimaryKey
    @SerializedName("placa")
    @Expose
    private String placa;

    @SerializedName("serie")
    @Expose
    private String serie;
    @SerializedName("marca")
    @Expose
    private String marca;
    @SerializedName("submarca")
    @Expose
    private String submarca;
    @SerializedName("modelo")
    @Expose
    private Integer modelo;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("numeroMotor")
    @Expose
    private String numeroMotor;
    @SerializedName("entidad")
    @Expose
    private String entidad;
    @SerializedName("municipio")
    @Expose
    private String municipio;
    @SerializedName("colonia")
    @Expose
    private String colonia;
    @SerializedName("propietario")
    @Expose
    private String propietario;
    @SerializedName("robado911")
    @Expose
    private Integer robado911;
    @SerializedName("averiguacion")
    @Expose
    private String averiguacion;
    @SerializedName("fechaRobo")
    @Expose
    private String fechaRobo;
    @SerializedName("fechaReporte")
    @Expose
    private String fechaReporte;

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getSubmarca() {
        return submarca;
    }

    public void setSubmarca(String submarca) {
        this.submarca = submarca;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNumeroMotor() {
        return numeroMotor;
    }

    public void setNumeroMotor(String numeroMotor) {
        this.numeroMotor = numeroMotor;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public Integer getRobado911() {
        return robado911;
    }

    public void setRobado911(Integer robado911) {
        this.robado911 = robado911;
    }

    public String getAveriguacion() {
        return averiguacion;
    }

    public void setAveriguacion(String averiguacion) {
        this.averiguacion = averiguacion;
    }

    public String getFechaRobo() {
        return fechaRobo;
    }

    public void setFechaRobo(String fechaRobo) {
        this.fechaRobo = fechaRobo;
    }

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

}
