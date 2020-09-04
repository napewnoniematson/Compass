package com.napewnoniematson.compass.logic.reader.location

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.napewnoniematson.compass.logic.reader.Reader
import com.napewnoniematson.compass.model.geo.GeoPoint

class UserLocationReaderImpl(private val context: Context) : UserLocationReader, LocationListener,
    Reader {

    private val TAG: String? = UserLocationReaderImpl::class.simpleName

    private val neededPermissions = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    private val LOCATION_REGISTRATION_MINIMUM_TIME_INTERVAL: Long = 500
    private val LOCATION_REGISTRATION_MINIMUM_DISTANCE: Float = 200f

    private val locationManager =
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    private val locationGeoPoint: GeoPoint = GeoPoint()
    private val locationGeoPointLP = MutableLiveData<GeoPoint>()

    @SuppressLint("MissingPermission")
    override fun start() {
        if (!hasPermissions()) {
            Log.d(TAG, "NEED PERMISSION FOR LOCATION")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(context as Activity, neededPermissions, 1)
            }
            return
        }
        locationManager.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            LOCATION_REGISTRATION_MINIMUM_TIME_INTERVAL,
            LOCATION_REGISTRATION_MINIMUM_DISTANCE,
            this
        )
    }

    override fun stop() {
        locationManager.removeUpdates(this)
    }

    private fun hasPermission(permission: String): Boolean {
        return ActivityCompat.checkSelfPermission(
            context, permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun hasPermissions(): Boolean {
        var status = true
        for (permission: String in neededPermissions) {
            status = status && hasPermission(permission)
        }
        return status
    }

    override fun onLocationChanged(location: Location) {
        locationGeoPoint.latitude = location.latitude.toFloat()
        locationGeoPoint.longitude = location.longitude.toFloat()
        locationGeoPointLP.value = locationGeoPoint
        Log.d(TAG, "Latitude = ${location.latitude} | Longitude = ${location.longitude}")
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        Log.d(TAG, "provider status")
    }

    override fun onProviderEnabled(provider: String?) {
        Log.d(TAG, "provider enabled")
    }

    override fun onProviderDisabled(provider: String?) {
        Log.d(TAG, "provider disabled")
    }

    override fun getLocation() = locationGeoPointLP as LiveData<GeoPoint>
}