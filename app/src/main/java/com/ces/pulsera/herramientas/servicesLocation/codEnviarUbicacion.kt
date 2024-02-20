package com.ces.pulsera.herramientas.servicesLocation

import android.content.Context
import android.location.Location
import com.ces.pulsera.data.pojo.PostUbicationResponse
import com.ces.pulsera.data.pojo.RequestGuardaAlerta
import com.ces.pulsera.data.services.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

 class codEnviarUbicacion() : CoroutineScope {
    var msj:String="";
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    private val location: com.ces.pulsera.herramientas.Location =        com.ces.pulsera.herramientas.Location()

    fun codEnviarUbicacion(id_persona:String, tipo:Char, ctx:Context) : String{
        launch  {
            var latitude : Double?=null
            var longitude : Double?= null
            launch  {
                val result: Location? = location.getUserLocation(ctx)
                if (result != null) {
                    latitude = result.latitude
                    longitude = result.longitude
                    if(latitude!=null && longitude!=null) {
                        RetrofitInstance.api.PostUbicacion(
                            RequestGuardaAlerta(
                                id_persona,
                                latitude,
                                longitude,
                                tipo
                            )
                        ).enqueue(object : Callback<PostUbicationResponse> {
                            override fun onResponse(
                                call: Call<PostUbicationResponse>,
                                response: Response<PostUbicationResponse>
                            ) {
                                if (response.body() != null) {
                                    val resultado: PostUbicationResponse = response.body()!!
                                    msj= (resultado.mensaje.toString())
                                } else {
                                    msj=("error recibir")
                                }
                            }
                            override fun onFailure(call: Call<PostUbicationResponse>, t: Throwable) {
                                msj=("error conexion")
                            }
                        })
                    }
                }else{
                    msj= ("error ubicacion")
                }
            }
        }
        return msj
    }



}