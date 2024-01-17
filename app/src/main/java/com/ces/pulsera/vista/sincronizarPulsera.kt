package com.ces.pulsera.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ces.pulsera.data.local.RequestVehiculo
import com.ces.pulsera.data.local.ResponseVehiculo
import com.ces.pulsera.data.remote.model.VehiculoResponse
import com.ces.pulsera.data.remote.services.GetVehiculoClient
import com.ces.pulsera.data.remote.services.GetVehiculoService
import com.ces.pulsera.databinding.ActivitySincronizarPulseraBinding
import com.ces.pulsera.herramientas.toast
import com.google.zxing.integration.android.IntentIntegrator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class sincronizarPulsera : AppCompatActivity() {
    private lateinit var  binding: ActivitySincronizarPulseraBinding
    lateinit var getVehiculoClient: GetVehiculoClient
    lateinit var getVehiculoService: GetVehiculoService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySincronizarPulseraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofinInit()
    }
    private fun retrofinInit() {
        getVehiculoClient = GetVehiculoClient.getInstance()
        getVehiculoService = getVehiculoClient.getVehiculoService()
    }
    fun scanMarginScanner(view: View?) {
        IntentIntegrator(this).initiateScan()

    }
    fun guardarMac(view: View?) {
       val mac= binding.txtMac.text.toString();
        creaConexion(mac);
    }
    fun creaConexion(mac:String){
        //val requestVehiculo = RequestVehiculo(1, mac)
        val call = getVehiculoService.getVehiculo(1, mac)
        call.enqueue(object : Callback<VehiculoResponse?> {
            override fun onResponse(
                call: Call<VehiculoResponse?>,
                response: Response<VehiculoResponse?>
            ) {
                if (response.isSuccessful){
                   //val obj= response.body()?.objeto?.get(0);
                    //if (obj != null) {
                        toast(response.body()!!.mensaje)
                }else{
                    toast("error con el servidor")
                }
            }

            override fun onFailure(call: Call<VehiculoResponse?>, t: Throwable) {
                toast("Problemas de conexion")
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult( resultCode, data)
        binding.txtMac.text=result.contents
    }
}