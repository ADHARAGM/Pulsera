package com.ces.pulsera.vista

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.ces.pulsera.herramientas.BeaconReferenceApplication
import com.ces.pulsera.databinding.ActivityMainBinding
import com.ces.pulsera.herramientas.BeaconScanPermissionsActivity
import com.ces.pulsera.viewmodel.MainViewModel
import org.altbeacon.beacon.BeaconManager


class MainActivity : AppCompatActivity() {

    lateinit var beaconReferenceApplication: BeaconReferenceApplication
    private lateinit var  binding: ActivityMainBinding
    private val quoteViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        beaconReferenceApplication = application as BeaconReferenceApplication


        quoteViewModel.quoteModel.observe(this, Observer {

        })
       binding.sincronizarBtn.setOnClickListener { }
       //beaconListView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayOf("--"))
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }
    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
        if (!BeaconScanPermissionsActivity.allPermissionsGranted(this,
                true)) {
            val intent = Intent(this, BeaconScanPermissionsActivity::class.java)
            intent.putExtra("backgroundAccessRequested", true)
            startActivity(intent)
        }
        else {
            // All permissions ar e granted now.  In the case where we are configured
            // to use a foreground service, we will not have been able to start scanning until
            // after permissions are graned.  So we will do so here.
            if (BeaconManager.getInstanceForApplication(this).monitoredRegions.size == 0) {
                (application as BeaconReferenceApplication).setupBeaconScanning()
            }
        }
    }





    companion object {
        val TAG = "MainActivity"
        val PERMISSION_REQUEST_BACKGROUND_LOCATION = 0
        val PERMISSION_REQUEST_BLUETOOTH_SCAN = 1
        val PERMISSION_REQUEST_BLUETOOTH_CONNECT = 2
        val PERMISSION_REQUEST_FINE_LOCATION = 3
    }


}
