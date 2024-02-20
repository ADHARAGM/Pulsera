package com.ces.pulsera.herramientas.servicesLocation

import android.app.Service
import android.content.Intent
import android.location.Location
import android.os.Handler
import android.os.IBinder
import androidx.lifecycle.Observer
import com.ces.pulsera.data.local.MacDatabase
import com.ces.pulsera.data.pojo.PostUbicationResponse
import com.ces.pulsera.data.pojo.RequestGuardaAlerta
import com.ces.pulsera.data.services.RetrofitInstance
import com.ces.pulsera.herramientas.BeaconReferenceApplication
import com.ces.pulsera.herramientas.toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext


class UbicacionServiceH () : Service() , CoroutineScope {
    private var fusedLocationProviderClient: FusedLocationProviderClient? = null
    private val location: com.ces.pulsera.herramientas.Location =
        com.ces.pulsera.herramientas.Location()
    private var locationRequest: LocationRequest? = null
    private var job: Job = Job()
    var latitud: Double? =null
    var longuitud: Double? =null
    var id_persona:String=""
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val locationCallback: LocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            if (locationResult == null) {
                return
            }
            for (location in locationResult.locations) {
                // grabo la localizacion en la Base de Datos
                val mapgps: MutableMap<String, Any> = HashMap()
                longuitud= location.longitude
                latitud= location.latitude


            }
        }
    }


    override fun onStartCommand(intencion: Intent?, flags: Int, idArranque: Int): Int {
        //envia si sigue activo cada hora
        Handler().apply {
            val runnable= object :Runnable{
                override fun run() {
                    obtenerId();                        //3,600,000 60m
                    postDelayed(this,1600000) //60000 1m
                }
            }
            postDelayed(runnable,3600000)
         }


        return START_STICKY;//siempre se vuelva a generar el servicio aunque el sistema mate el servicio
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    override fun onBind(intent: Intent?): IBinder? {
        return null;
    }
    private fun obtenerId(){
        val macDatabase= MacDatabase.getInstance(BeaconReferenceApplication.getContext())
        var getSelectMacs =macDatabase.listMacDao().getMac()
        getSelectMacs.observeForever(Observer { listMacs->
            if(!listMacs.isEmpty()){
                //mac="C6EB07647825"
                id_persona=listMacs[0].idPersona
                sendFrecuente()
            }
        })
    }

    /*private fun sendSolicitud(){
        RetrofitInstance.api.requestLocation(id_persona ).enqueue(object : Callback<MacResponse> {
            override fun onResponse(
                call: Call<MacResponse>,
                response: Response<MacResponse>
            ) {
                if (response.body() != null) {
                    val resultado: MacResponse = response.body()!!

                } else {
                    toast("error recibir")
                }
            }

            override fun onFailure(call: Call<MacResponse>, t: Throwable) {
                toast("error conexion")
            }
        })
    }*/
    private fun sendFrecuente() {

        var latitude : Double?=null
        var longitude : Double?= null
        launch  {
            val result: Location? = location.getUserLocation(this@UbicacionServiceH)
            if (result != null) {
                latitude = result.latitude
                longitude = result.longitude
                if(latitude!=null && longitude!=null) {
                    RetrofitInstance.api.PostUbicacion(
                        RequestGuardaAlerta(
                            id_persona,
                            latitude,
                            longitude,
                            'F'
                        )
                    ).enqueue(object : Callback<PostUbicationResponse> {
                        override fun onResponse(
                            call: Call<PostUbicationResponse>,
                            response: Response<PostUbicationResponse>
                        ) {
                            if (response.body() != null) {
                                val resultado: PostUbicationResponse = response.body()!!
                                toast(resultado.mensaje.toString())
                            } else {
                                toast("error recibir Hora")
                            }
                        }
                        override fun onFailure(call: Call<PostUbicationResponse>, t: Throwable) {
                            toast("error conexion Hora")
                        }
                    })
                }
            }else{
                toast("error ubicacion Hora")
            }
        }
    }
}