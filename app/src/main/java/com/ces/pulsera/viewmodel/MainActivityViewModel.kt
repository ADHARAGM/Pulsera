package com.ces.pulsera.viewmodel

import android.content.Context
import android.content.Intent
import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ces.pulsera.data.local.MacDatabase
import com.ces.pulsera.data.pojo.MacResponse
import com.ces.pulsera.data.pojo.RequestGuardaAlerta
import com.ces.pulsera.data.pojo.ResponseMac
import com.ces.pulsera.data.services.RetrofitInstance
import com.ces.pulsera.herramientas.BeaconReferenceApplication
import com.ces.pulsera.herramientas.servicesLocation.UbicacionServiceM
import com.ces.pulsera.herramientas.toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class MainActivityViewModel (val listMacDataBase: MacDatabase) : ViewModel(), CoroutineScope {

    private  var getSelectMacs = listMacDataBase.listMacDao().getMac()
    private val location: com.ces.pulsera.herramientas.Location =
        com.ces.pulsera.herramientas.Location()
    private var job: Job = Job()
    var resultado: MacResponse= MacResponse()
    var msj:String =""
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    fun consultaBase(): LiveData<List<ResponseMac>> {
        return getSelectMacs
    }

    fun enviarAlerta(id_persona:String, ctx:Context){
            var latitude : Double?=null
            var longitude : Double?= null
            launch  {
                val result: Location? = location.getUserLocation(ctx)

                if (result != null) {
                    latitude = result.latitude
                    longitude = result.longitude
                    if(latitude!=null && longitude!=null) {

                       RetrofitInstance.api.setMacService(
                            RequestGuardaAlerta(
                                id_persona,
                                latitude,
                                longitude
                            )
                        ).enqueue(object : Callback<MacResponse> {
                            override fun onResponse(
                                call: Call<MacResponse>,
                                response: Response<MacResponse>
                            ) {
                                if (response.body() != null) {
                                    resultado = response.body()!!
                                    msj=resultado.mensaje
                                    /*val intentpasos2 = Intent(this@MainActivityViewModel, UbicacionServiceM::class.java)
                                    intentpasos2.putExtra("tipo", "A")
                                    this@MainActivityViewModel.startService( intentpasos2 );*/
                                } else {
                                    msj="ERROR DE CONEXIÓN"
                                }
                            }
                            override fun onFailure(call: Call<MacResponse>, t: Throwable) {
                                msj="ERROR DE CONEXIÓN"
                            }
                        })
                    }
                }else{
                }
            }
    }
    fun mensaje():String{
        return msj;
    }

}