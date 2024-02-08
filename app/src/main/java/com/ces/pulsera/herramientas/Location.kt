package com.ces.pulsera.herramientas

import android.annotation.SuppressLint
import android.app.Service
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationManager
import android.os.IBinder
import android.util.Log
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.CoroutineContext


@OptIn(ExperimentalCoroutinesApi::class)
class Location()  {


    @SuppressLint("MissingPermission")
    suspend fun getUserLocation(context:Context): Location?{
        val fusedLocationProviderClient= LocationServices.getFusedLocationProviderClient(context)
        val isUserLocationPermissionsGranted =true
        val locationManager:LocationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGPSEnabled:Boolean = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)|| locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if(!isGPSEnabled || !isUserLocationPermissionsGranted){
            return null
        }
        return  suspendCancellableCoroutine { cont ->
            fusedLocationProviderClient.lastLocation.apply {
                if (isComplete){
                    if(isSuccessful){
                        cont.resume(result){}
                    }else{
                        cont.resume(null){}
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it){}
                }
                addOnFailureListener{
                    cont.resume(null){}
                }
                addOnCanceledListener {  cont.resume(null){} }
            }
        }

    }

    companion object {
        private lateinit var instance : com.ces.pulsera.herramientas.Location
        fun getContext(): com.ces.pulsera.herramientas.Location {
            return instance;
        }


    }



}