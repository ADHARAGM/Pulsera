
package com.ces.pulsera.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ces.pulsera.data.local.MacDatabase
import com.ces.pulsera.data.pojo.ResponseMac
import com.ces.pulsera.data.pojo.MacResponse
import com.ces.pulsera.data.services.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.contracts.Returns

class SincronizarPulseraViewModel(val listMacDataBase: MacDatabase) : ViewModel(){

    private  var listaMacLiveData=MutableLiveData<ResponseMac>()
    private  var listadoResultado=MutableLiveData<MacResponse>()
    private  var getSelectMacs = listMacDataBase.listMacDao().getMac()

    //private var LiveData=

    var msj:String =""
    fun getListadoMacs(mac:String){
        RetrofitInstance.api.getMacService(mac).enqueue(object : Callback<MacResponse>{
            override fun onResponse(call: Call<MacResponse>, response: Response<MacResponse>) {
                if(response.body() != null) {
                    val resultado: MacResponse = response.body()!!
                    val listaMacs: ResponseMac = response.body()!!.objeto[0]
                    listadoResultado.value=resultado
                    listaMacLiveData.value=listaMacs

                }else{
                    msj="ERROR DE CONEXIÓN"
                }
            }
            override fun onFailure(call: Call<MacResponse>, t: Throwable) {
                msj="ERROR DE CONEXIÓN "+t.message.toString()
                Log.d("Error ",t.message.toString())
            }


        })
    }
    fun observarLiveData():LiveData<ResponseMac>{
        return  listaMacLiveData
    }
    fun observarResultado():LiveData<MacResponse>{
        return  listadoResultado
    }
    fun mensaje():String{
        return msj;
    }
    fun insertDataBase(Respmac:ResponseMac){
        CoroutineScope(Dispatchers.IO).launch {
            listMacDataBase.listMacDao().insertMac(Respmac)
        }
    }
    fun consultaBase():LiveData<List<ResponseMac>>{
        return getSelectMacs
    }
    fun deleteMac(){
        CoroutineScope(Dispatchers.IO).launch {
            listMacDataBase.listMacDao().deleteMac()
        }
    }


}