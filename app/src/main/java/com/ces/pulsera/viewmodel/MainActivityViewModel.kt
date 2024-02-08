package com.ces.pulsera.viewmodel

import android.location.Location
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ces.pulsera.data.local.MacDatabase
import com.ces.pulsera.data.pojo.MacResponse
import com.ces.pulsera.data.pojo.ResponseMac
import com.ces.pulsera.herramientas.BeaconReferenceApplication
import com.ces.pulsera.herramientas.toast
import kotlinx.coroutines.launch

class MainActivityViewModel (val listMacDataBase: MacDatabase) : ViewModel(){

    private  var getSelectMacs = listMacDataBase.listMacDao().getMac()

    fun consultaBase(): LiveData<List<ResponseMac>> {
        return getSelectMacs
    }


}