package com.ces.pulsera.herramientas

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.location.Location
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.lifecycle.Observer
import com.ces.pulsera.R
import com.ces.pulsera.data.local.MacDatabase
import com.ces.pulsera.data.pojo.MacResponse
import com.ces.pulsera.data.pojo.RequestGuardaAlerta
import com.ces.pulsera.data.services.RetrofitInstance
import com.ces.pulsera.herramientas.servicesLocation.UbicacionServiceA
import com.ces.pulsera.herramientas.servicesLocation.UbicacionServiceM
import com.ces.pulsera.vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.altbeacon.beacon.Beacon
import org.altbeacon.beacon.BeaconManager
import org.altbeacon.beacon.BeaconParser
import org.altbeacon.beacon.Identifier
import org.altbeacon.beacon.MonitorNotifier
import org.altbeacon.beacon.Region
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext

class BeaconReferenceApplication() : Application(), CoroutineScope {
    var mac:String=""
    var id_persona:String=""
    private val location: com.ces.pulsera.herramientas.Location = Location()
    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job
    override fun onCreate() {
        instance = this
        super.onCreate()
        val beaconManager = BeaconManager.getInstanceForApplication(this)
        BeaconManager.setDebug(true)
        // By default the AndroidBeaconLibrary will only find AltBeacons.  If you wish to make it
        // find a different type of beacon, you must specify the byte layout for that beacon's
        // advertisement with a line like below.  The example shows how to find a beacon with the
        // same byte layout as AltBeacon but with a beaconTypeCode of 0xaabb.  To find the proper
        // layout expression for other beacon types, do a web search for "setBeaconLayout"
        // including the quotes.
        //
        //beaconManager.getBeaconParsers().clear();
        //beaconManager.getBeaconParsers().add(new BeaconParser().
        //        setBeaconLayout("m:0-1=4c00,i:2-24v,p:24-24"));
        // By default the AndroidBeaconLibrary will only find AltBeacons.  If you wish to make it
        // find a different type of beacon like Eddystone or iBeacon, you must specify the byte layout
        // for that beacon's advertisement with a line like below.
        //
        // If you don't care about AltBeacon, you can clear it from the defaults:
        //beaconManager.getBeaconParsers().clear()

        // Uncomment if you want to block the library from updating its distance model database
        //BeaconManager.setDistanceModelUpdateUrl("")

        // The example shows how to find iBeacon.
        val parser = BeaconParser().
        setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24")
        parser.setHardwareAssistManufacturerCodes(arrayOf(0x004c).toIntArray())
        beaconManager.getBeaconParsers().add(
            parser)
        //consultaBase().observe(this, O
        // enabling debugging will send lots of verbose debug information from the library to Logcat
        // this is useful for troubleshooting problmes
        // BeaconManager.setDebug(true)


        // The BluetoothMedic code here, if included, will watch for problems with the bluetooth
        // stack and optionally:
        // - power cycle bluetooth to recover on bluetooth problems
        // - periodically do a proactive scan or transmission to verify the bluetooth stack is OK
        // BluetoothMedic.getInstance().legacyEnablePowerCycleOnFailures(this) // Android 4-12 only
        // BluetoothMedic.getInstance().enablePeriodicTests(this, BluetoothMedic.SCAN_TEST + BluetoothMedic.TRANSMIT_TEST)


    }
    // CAMBIAR AQUI EL UID POR LA MAC
    fun setupBeaconScanning() {

        val beaconManager = BeaconManager.getInstanceForApplication(this)
        beaconManager.setBackgroundBetweenScanPeriod(10000);
       // beaconManager.setBackgroundBetweenScanPeriod(8000);
        // By default, the library will scan in the background every 5 minutes on Android 4-7,
        // which will be limited to scan jobs scheduled every ~15 minutes on Android 8+
        // If you want more frequent scanning (requires a foreground service on Android 8+),
        // configure that here.
        // If you want to continuously range beacons in the background more often than every 15 mintues,
        // you can use the library's built-in foreground service to unlock this behavior on Android
        // 8+.   the method below shows how you set that up.
        val macDatabase=MacDatabase.getInstance(getContext())
        var getSelectMacs =macDatabase.listMacDao().getMac()
        getSelectMacs.observeForever(Observer {

                listMacs->
            if(listMacs.isEmpty()){
                //mac="C6EB07647825"
                mac="C5EB00000000"
            }else
            {
                mac=listMacs[0].mac
                id_persona=listMacs[0].idPersona
            }

            var uid=Identifier.parse("FDA50693-A4E2-4FB1-AFCF-"+mac)//Identifier.parse("FDA50693-A4E2-4FB1-AFCF-C6EB07647825")

            var region = Region("all-beacons", uid, null, null)

            try {
                setupForegroundService()

            }
            catch (e: SecurityException) {
                // On Android TIRAMUSU + this security exception will happen
                // if location permission has not been granted when we start
                // a foreground service.  In this case, wait to set this up
                // until after that permission is granted
                Log.d(TAG, "Not setting up foreground service scanning until location permission granted by user")

            }
            //beaconManager.setEnableScheduledScanJobs(false);
            //beaconManager.setBackgroundBetweenScanPeriod(0);
            //beaconManager.setBackgroundScanPeriod(1100);

            // Ranging callbacks will drop out if no beacons are detected
            // Monitoring callbacks will be delayed by up to 25 minutes on region exit
            // beaconManager.setIntentScanningStrategyEnabled(true)

            // The code below will start "monitoring" for beacons matching the region definition at the top of this file
            beaconManager.startMonitoring(region)
            beaconManager.startRangingBeacons(region)
            // These two lines set up a Live Data observer so this Activity can get beacon data from the Application class
            val regionViewModel = BeaconManager.getInstanceForApplication(this).getRegionViewModel(region)
            // observer will be called each time the monitored regionState changes (inside vs. outside region)
            regionViewModel.regionState.observeForever( centralMonitoringObserver)
            // observer will be called each time a new list of beacons is ranged (typically ~1 second in the foreground)
            regionViewModel.rangedBeacons.observeForever( centralRangingObserver)


        })



    }

    fun setupForegroundService() {
        /*  */
        val builder = Notification.Builder(this, "BeaconReferenceApp")
        builder.setSmallIcon(R.mipmap.ic_wristband_foreground)
        builder.setContentTitle("Pulsera")
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT + PendingIntent.FLAG_IMMUTABLE
        )
        builder.setContentIntent(pendingIntent);
        val channel =  NotificationChannel("beacon-ref-notification-id",
            "My Notification Name", NotificationManager.IMPORTANCE_DEFAULT)
        channel.setDescription("My Notification Channel Description")
        val notificationManager =  getSystemService(
            Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel);
        builder.setChannelId(channel.getId());
        Log.d(TAG, "Calling enableForegroundServiceScanning")
        BeaconManager.getInstanceForApplication(this).enableForegroundServiceScanning(builder.build(), 456);
        Log.d(TAG, "Back from  enableForegroundServiceScanning")
    }

    val centralMonitoringObserver = Observer<Int> { state ->
        if (state == MonitorNotifier.OUTSIDE) {
            Log.d(TAG, "outside beacon region: ")//+region)
        }
        else {
            Log.d(TAG, "inside beacon region: ")//
            // +region)
            sendAlerta()

        }
    }

    private fun sendAlerta() {
        var latitude : Double?=null
        var longitude : Double?= null

        launch  {
            val result: Location? = location.getUserLocation(getContext())

            if (result != null) {
                latitude = result.latitude
                longitude = result.longitude
                if(latitude!=null && longitude!=null) {
                    RetrofitInstance.api.setMacService(
                        RequestGuardaAlerta(
                            id_persona,
                            latitude,
                            longitude
                        )
                    ).enqueue(object : Callback<MacResponse> {
                        override fun onResponse(
                            call: Call<MacResponse>,
                            response: Response<MacResponse>
                        ) {
                            if (response.body() != null) {
                                val resultado: MacResponse = response.body()!!
                                sendNotification(resultado.mensaje)
                                val intentpasos2 = Intent(this@BeaconReferenceApplication, UbicacionServiceM::class.java)
                                stopService(intentpasos2)
                                intentpasos2.putExtra("tipo", "A")
                                this@BeaconReferenceApplication.startService( intentpasos2 );
                            } else {
                                sendNotificationError()

                            }
                        }

                        override fun onFailure(call: Call<MacResponse>, t: Throwable) {
                            sendNotificationError()
                        }


                    })

                }
            }else{
                sendNotificationError()
            }

        }
    }

    val centralRangingObserver = Observer<Collection<Beacon>> { beacons ->
        val rangeAgeMillis = System.currentTimeMillis() - (beacons.firstOrNull()?.lastCycleDetectionTimestamp ?: 0)
        if (rangeAgeMillis < 10000) {
            Log.d(MainActivity.TAG, "Ranged: ${beacons.count()} beacons")
            for (beacon: Beacon in beacons) {
                Log.d(TAG, "$beacon about ${beacon.distance} meters away")
            }
        }
        else {
            Log.d(MainActivity.TAG, "Ignoring stale ranged beacons from $rangeAgeMillis millis ago")
        }
    }

    private fun sendNotification(mensaje:String) {

        val builder = NotificationCompat.Builder(this, "beacon-ref-notification-id")
            .setContentTitle("ALERTA")
            .setContentText(mensaje )
            .setSmallIcon(R.mipmap.ic_wristband_foreground)
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addNextIntent(Intent(this, MainActivity::class.java))
        val resultPendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT + PendingIntent.FLAG_IMMUTABLE
        )
        builder.setContentIntent(resultPendingIntent)
        val channel =  NotificationChannel("beacon-ref-notification-id",
            "My Notification Name", NotificationManager.IMPORTANCE_DEFAULT)
        channel.setDescription("My Notification Channel Description")
        val notificationManager =  getSystemService(
            Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel);
        builder.setChannelId(channel.getId());
        notificationManager.notify(1, builder.build())
    }
    private fun sendNotificationError() {

        val builder = NotificationCompat.Builder(this, "beacon-ref-notification-id")
            .setContentTitle("ERROR")
            .setContentText("Error al enviar alerta" )
            .setSmallIcon(R.mipmap.ic_wristband_foreground)
        val stackBuilder = TaskStackBuilder.create(this)
        stackBuilder.addNextIntent(Intent(this, MainActivity::class.java))
        val resultPendingIntent = stackBuilder.getPendingIntent(
            0,
            PendingIntent.FLAG_UPDATE_CURRENT + PendingIntent.FLAG_IMMUTABLE
        )
        builder.setContentIntent(resultPendingIntent)
        val channel =  NotificationChannel("beacon-ref-notification-id",
            "My Notification Name", NotificationManager.IMPORTANCE_DEFAULT)
        channel.setDescription("My Notification Channel Description")
        val notificationManager =  getSystemService(
            Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel);
        builder.setChannelId(channel.getId());
        notificationManager.notify(1, builder.build())
    }

    companion object {
        val TAG = "BeaconReference"
        private lateinit var instance : BeaconReferenceApplication
        fun getContext(): BeaconReferenceApplication{
            return instance;
        }
        fun getInstance(): BeaconReferenceApplication{
            return instance;
        }
    }



}
