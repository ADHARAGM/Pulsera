package com.ces.pulsera.vista

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ces.pulsera.data.local.MacDatabase
import com.ces.pulsera.databinding.ActivityMainBinding
import com.ces.pulsera.herramientas.BeaconReferenceApplication
import com.ces.pulsera.herramientas.BeaconScanPermissionsActivity
import com.ces.pulsera.herramientas.Location
import com.ces.pulsera.herramientas.servicesLocation.UbicacionServiceH
import com.ces.pulsera.viewmodel.MainActivityModelFactory
import com.ces.pulsera.viewmodel.MainActivityViewModel
import org.altbeacon.beacon.BeaconManager


class MainActivity : AppCompatActivity() {

    lateinit var beaconReferenceApplication: BeaconReferenceApplication
    private lateinit var  binding: ActivityMainBinding
    private  lateinit var mainActivityViewModel: MainActivityViewModel
    private  var myBluetooth: BluetoothAdapter? = null
    lateinit var mypairedDevices:Set<BluetoothDevice>
    val Request_Enable_Blutooth=1
    var mServiceIntent: Intent? = null
    private var mSensorService: Location? = null

    @SuppressLint("MissingPermission")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater);
        setContentView(binding.root)
        val macDatabase= MacDatabase.getInstance(this)
        val viewModelFactory= MainActivityModelFactory(macDatabase)
        mainActivityViewModel= ViewModelProvider(this,viewModelFactory)[MainActivityViewModel::class.java]
        mainActivityViewModel.consultaBase().observe(this, Observer { listMacs->
            if(!listMacs.isEmpty()){
                beaconReferenceApplication = application as BeaconReferenceApplication
                val intentpasos = Intent(this, UbicacionServiceH::class.java)
                this.startService( intentpasos );
            }
        })
        myBluetooth= BluetoothAdapter.getDefaultAdapter()
        if (!myBluetooth!!.isEnabled)
        {
            val enableBlutoothIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            startActivityForResult(enableBlutoothIntent, Request_Enable_Blutooth)
        }

      //  beaconReferenceApplication = application as BeaconReferenceApplication

    }


    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }
    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()
        mainActivityViewModel.consultaBase().observe(this, Observer { listMacs->
            if(!listMacs.isEmpty()){
                funcionOnresume()
            }
        })
    }
    fun funcionOnresume(){
        if (!BeaconScanPermissionsActivity.allPermissionsGranted(this,
                true)) {
            val intent = Intent(this, BeaconScanPermissionsActivity::class.java)
            intent.putExtra("backgroundAccessRequested", true)
            startActivity(intent)
        }
        else {
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
    fun irSincronizarPersona(view: View?) {
        val intent = Intent(this, sincronizarPulsera::class.java)
        startActivity(intent)
    }

}
