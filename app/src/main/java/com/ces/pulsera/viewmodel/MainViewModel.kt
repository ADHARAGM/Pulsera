package com.ces.pulsera.viewmodel

import android.app.Activity
import android.app.Application
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ces.pulsera.herramientas.BeaconReferenceApplication
import com.ces.pulsera.modelo.QuoteModelMain
import com.ces.pulsera.modelo.MainProvider

class MainViewModel : ViewModel(){

    var mac: String? = null
    var password : String? =null

    fun sincronizarOnClick(view: View){

    }
    val quoteModel= MutableLiveData<QuoteModelMain>()
    /*fun randomQuote(){
        val currentQuote:QuoteModelMain=MainProvider.random()
        quoteModel.postValue(currentQuote)
    }*/

}