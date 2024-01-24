package com.ces.pulsera.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ces.pulsera.data.local.MacDatabase
import com.ces.pulsera.data.pojo.ResponseMac
import com.ces.pulsera.data.pojo.MacResponse
import com.ces.pulsera.databinding.ActivitySincronizarPulseraBinding
import com.ces.pulsera.herramientas.toast
import com.ces.pulsera.viewmodel.SincronizarPulseraViewModel
import com.ces.pulsera.viewmodel.SincronizarPulseraViewModelFactory
import com.google.zxing.integration.android.IntentIntegrator

class sincronizarPulsera : AppCompatActivity()  {
    private lateinit var  binding: ActivitySincronizarPulseraBinding
    //private val PulseraViewModel: SincronizarPulseraViewModel by viewModels()
    private  lateinit var PulseraViewModel:SincronizarPulseraViewModel
    private var responseMacToSave:ResponseMac?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySincronizarPulseraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val macDatabase=MacDatabase.getInstance(this)
        val viewModelFactory= SincronizarPulseraViewModelFactory(macDatabase)
        PulseraViewModel=ViewModelProvider(this,viewModelFactory)[SincronizarPulseraViewModel::class.java]

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
                responseMacToSave=value
            }
        })
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult( resultCode, data)
        binding.txtMac.text=result.contents

    }
}