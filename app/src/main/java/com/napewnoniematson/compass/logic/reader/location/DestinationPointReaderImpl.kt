package com.napewnoniematson.compass.logic.reader.location

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.napewnoniematson.compass.R
import com.napewnoniematson.compass.model.geo.GeoPoint


interface PositiveDialogButtonHandler {
    fun onClick(coordinateName: String, coordinateValue: Float)
}

class DestinationPointReaderImpl(private val context: Context) : DestinationPointReader, PositiveDialogButtonHandler{


    private val TAG: String? = DestinationPointReaderImpl::class.simpleName

    private var isLatitudeUpdated = false
    private var isLongitudeUpdated = false

    private val LATITUDE = context.getString(R.string.latitude)
    private val LONGITUDE = context.getString(R.string.longitude)

    private val destinationPoint = GeoPoint()
    private val destinationPointLD = MutableLiveData<GeoPoint>()

    override fun onClick(coordinateName: String,  coordinateValue: Float) {
        Log.d(TAG, "CoordinateName: $coordinateName | CoordinateValue $coordinateValue")
        when (coordinateName) {
            LATITUDE -> {
                if (isLatitudeInRange(coordinateValue)) {
                    destinationPoint.latitude = coordinateValue
                    isLatitudeUpdated = true
                } else {
                    showLatitudeNotInRangeToast()
                }
            }
            LONGITUDE -> {
                if (isLongitudeInRange(coordinateValue)) {
                    destinationPoint.longitude = coordinateValue
                    isLongitudeUpdated = true
                } else {
                    showLongitudeNotInRangeToast()
                }
            }
        }

        if (areCoordinatesUpdated())
            destinationPointLD.value = destinationPoint
    }

    private fun isLatitudeInRange(latitude: Float) = -90f <= latitude && latitude <= 90f
    private fun isLongitudeInRange(latitude: Float) = -180f <= latitude && latitude <= 180f

    private fun areCoordinatesUpdated() = isLatitudeUpdated && isLongitudeUpdated

    private fun showCoordinateNotInRangeToast(messageId: Int) {
        Toast.makeText(context, context.getString(messageId), Toast.LENGTH_LONG).show()
    }

    private fun showLatitudeNotInRangeToast() {
        showCoordinateNotInRangeToast(R.string.latitude_not_in_range)
    }

    private fun showLongitudeNotInRangeToast() {
        showCoordinateNotInRangeToast(R.string.longitude_not_in_range)
    }

    override fun getPoint() = destinationPointLD as LiveData<GeoPoint>
}