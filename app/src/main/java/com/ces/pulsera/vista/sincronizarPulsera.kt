package com.ces.pulsera.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ces.pulsera.data.local.ResponseMac
import com.ces.pulsera.databinding.ActivitySincronizarPulseraBinding
import com.ces.pulsera.herramientas.toast
import com.ces.pulsera.viewmodel.SincronizarPulseraViewModel
import com.google.zxing.integration.android.IntentIntegrator

class sincronizarPulsera : AppCompatActivity()  {
    private lateinit var  binding: ActivitySincronizarPulseraBinding
    private val sincronizarPulseraViewModel: SincronizarPulseraViewModel by viewModels()
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

       val mac= binding.txtMac.text.toString();
        //sincronizarPulseraViewModel.SincronizarPulseraViewModel(mac)
       sincronizarPulseraViewModel.getMacpersonaVm()?.observe(this, Observer {
            listMac= it?.data as List<ResponseMac>?
           //toast(listMac!!.get(0).idPersona)
           // toast(listMac!!.size.toString())
        })
       //sincronizarPulseraViewModel.
        //sincronizarPulseraViewModel.getMacpersonaVm()

        //sincronizarPulseraViewModel.SincronizarPulseraViewModel(mac);

        //sincronizarPulseraViewModel.getIdPersonaMac(mac);
        //val msj = sincronizarPulseraViewModel.getMacpersonaVm().value!!.mensaje.toString()
        //toast(msj)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        val result = IntentIntegrator.parseActivityResult( resultCode, data)
        binding.txtMac.text=result.contents
        sincronizarPulseraViewModel.SincronizarPulseraViewModel(result.contents)
    }
}