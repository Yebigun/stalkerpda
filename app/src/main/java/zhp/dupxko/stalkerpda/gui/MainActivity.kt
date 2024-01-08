package zhp.dupxko.stalkerpda.gui

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import zhp.dupxko.stalkerpda.R
import zhp.dupxko.stalkerpda.databinding.ActivityMainBinding
import zhp.dupxko.stalkerpda.logic.LocationForegroundService

class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationClient: FusedLocationProviderClient       //lokalizacja
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Log.d("gaysex","NAM PIZDA")
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS),0)
            }
        } else {
            Log.d("gaysex","jechane z tym koksem")
        }

        Intent(applicationContext, LocationForegroundService::class.java).also {
            it.action = LocationForegroundService.Actions.START.toString()
            startService(it)
        }

        bindDeatils()


    }

    fun bindDeatils() {
        val intent = Intent(this, AudioPlayerActivity::class.java)
        intent.putExtra("testuwa", R.raw.expodchorazy)

        binding.button2.setOnClickListener {
            startActivity(intent)
        }
        binding.buttonGps.setOnClickListener {
            getLastKnownLocation()
        }
    }

    private fun getLastKnownLocation() {

        if (
            ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions( this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100);
            return
        }
        val location = fusedLocationClient.lastLocation
        binding.button2.text
        location.addOnSuccessListener {
            if (it != null) {
                val textAccuracy = it.accuracy.toString()
                val textLatitude =  it.latitude.toString()
                val textLongitude = it.longitude.toString()
                binding.longitude.text = textAccuracy
                binding.latitude.text = textLatitude
                Log.i("localization","$textLatitude $textLongitude $textAccuracy ${it.hasAccuracy()}")
            }
        }
    }
}