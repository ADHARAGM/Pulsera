package com.ces.pulsera.data.pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetStatusDispositivoResponse {

    @SerializedName("status")
    @Expose
    private var status: String? = null

    fun getStatus(): String? {
        return status
    }

    fun setStatus(status: String?) {
        this.status = status
    }
}