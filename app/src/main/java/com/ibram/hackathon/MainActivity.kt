package com.ibram.hackathon

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.drawable.BitmapDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng

class MainActivity : AppCompatActivity() , OnMapReadyCallback {

    private var locationManager : LocationManager? = null
    private lateinit var googleMapI : GoogleMap

    @SuppressLint("MissingPermission")
    override fun onMapReady(p0: GoogleMap?) {
        googleMapI = p0!!
        //val icon = BitmapDescriptorFactory.fromBitmap((resize(resources.getDrawable(R.drawable.ic_pharmacie)) as BitmapDrawable).bitmap)
        //localisation
        var locationManager= applicationContext?.getSystemService(Context.LOCATION_SERVICE) as LocationManager


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    1)
            }
        } else {
            locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10f, locationListener)
            locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10f, locationListener)
        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mapF = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapF.getMapAsync(this)

    }

    //localisation change
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            googleMapI.clear()
            googleMapI.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude,location.longitude), 13F))
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }



    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 10f, locationListener)
                    locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10f, locationListener)
                } else {
                    //nothing
                }
                return
            }
        }
    }


}
