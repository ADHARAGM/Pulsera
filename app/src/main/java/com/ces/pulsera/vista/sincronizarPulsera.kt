package com.ces.pulsera.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ces.pulsera.data.remote.model.ResponseMac
import com.ces.pulsera.data.local.MacResponse
import com.ces.pulsera.databinding.ActivitySincronizarPulseraBinding
import com.ces.pulsera.herramientas.toast
import com.ces.pulsera.viewmodel.SincronizarPulseraViewModel
import com.google.zxing.integration.android.IntentIntegrator

class sincronizarPulsera : AppCompatActivity()  {
    private lateinit var  binding: ActivitySincronizarPulseraBinding
    private val PulseraViewModel: SincronizarPulseraViewModel by viewModels()
    var listMac: List<ResponseMac>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySincronizarPulseraBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
     fun scanMarginScanner(view: View?) {
        IntentIntegrator(this).initiateScan()
    }
     fun guardarMac(view: View?) {
         val mac= binding.txtMac.text.toString()
         PulseraViewModel.getListadoMacs(mac);
         observarResultado()
         observarListadoMacs()
         mensaje()
    }

    private fun mensaje() {
        var msj =PulseraViewModel.mensaje()
        if(msj!=""){
            toast(msj)
        }
    }

    private fun observarResultado(){
        PulseraViewModel.observarResultado().observe(this, object :Observer<MacResponse>{
            override fun onChanged(value: MacResponse) {
                    toast((value.mensaje))
            }
        })
    }
    private fun observarListadoMacs() {
        PulseraViewModel.observarLiveData().observe(this, object :Observer<ResponseMac>{
            override fun onChanged(value: ResponseMac) {
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult( resultCode, data)
        binding.txtMac.text=result.contents

    }
}