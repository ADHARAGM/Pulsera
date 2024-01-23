
package com.ces.pulsera.viewmodel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ces.pulsera.data.local.ResponseMac
import com.ces.pulsera.data.network.Resource
import com.ces.pulsera.data.remote.model.MacResponse
import com.ces.pulsera.data.remote.services.GetMacService
import com.ces.pulsera.data.remote.services.MacRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SincronizarPulseraViewModel : ViewModel(){

  private var sincronizarPulseraModel: LiveData<Resource<List<ResponseMac?>?>?>? = null
    /*private var getVehiculoClient: GetVehiculoClient? = null*/
    lateinit var macRepository: MacRepository


    fun SincronizarPulseraViewModel(mac:String) {
      macRepository = MacRepository.getInstance()
        //macRepository = MacRepository()
        sincronizarPulseraModel = macRepository!!.getMacpersona(mac)
    }


    fun getMacpersonaVm(): LiveData<Resource<List<ResponseMac?>?>?>? {
        return sincronizarPulseraModel
    }

 /*  val sincronizarPulseraModel= MutableLiveData<MacResponse>()

    lateinit var macRepository: MacRepository
    lateinit var getMacService: GetMacService
    var msj:String=""
    fun getIdPersonaMac (mac: String):String {
        macRepository = MacRepository.getInstance()
        getMacService = macRepository.getMacService()
        val call = getMacService.getMacService( mac)

        call.enqueue(object : Callback<MacResponse?> {
            override fun onResponse(
                call: Call<MacResponse?>,
                response: Response<MacResponse?>
            ) {
                if (response.isSuccessful){
                    //val obj= response.body()?.objeto?.get(0);
                    if (response.body()!=null) {
                        //val random : MacResponse= response.body()!!
                        //sincronizarPulseraModel.value=random
                       sincronizarPulseraModel.postValue(response.body())

                        msj= response.body()?.mensaje!!

                    }
                }else{

                }

            }

            override fun onFailure(call: Call<MacResponse?>, t: Throwable) {

            }
        })
        return msj
    }

    fun getMacpersonaVm(): MutableLiveData<MacResponse> {
        return sincronizarPulseraModel
    }
*/

}