package com.napewnoniematson.compass.logic.reader.location

import android.app.Application
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.napewnoniematson.compass.R
import com.napewnoniematson.compass.view.widget.GeoCoordinateButton
import com.napewnoniematson.compass.model.geo.GeoPoint

class DestinationPointReaderImpl(private val context: Context) : DestinationPointReader,
    View.OnClickListener {

    private val TAG: String? = DestinationPointReaderImpl::class.simpleName

    private var isLatitudeUpdated = false
    private var isLongitudeUpdated = false

    private val LATITUDE = context.getString(R.string.latitude)
    private val LONGITUDE = context.getString(R.string.longitude)

    private val destinationPoint = GeoPoint()
    private val destinationPointLD = MutableLiveData<GeoPoint>()

    override fun onClick(v: View?) {
        (v as GeoCoordinateButton).geoDialog.dialog.show()
        when (v.geoCoordinateName) {
            LATITUDE -> {
                if (isLatitudeInRange(v.coordinate)) {
                    destinationPoint.latitude = v.coordinate
                    isLatitudeUpdated = true
                } else {
                    showLatitudeNotInRangeToast()
                }
            }
            LONGITUDE -> {
                if (isLongitudeInRange(v.coordinate)) {
                    destinationPoint.longitude = v.coordinate
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