package com.napewnoniematson.compass.logic.reader.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.napewnoniematson.compass.logic.reader.Reader
import com.napewnoniematson.compass.model.compass.Needle
import com.napewnoniematson.compass.view.widget.compass.NeedleView

class SensorReaderImpl(context: Context) : SensorEventListener, Reader, NeedleReader {

    private val TAG: String? = SensorReaderImpl::class.simpleName
    private val SENSOR_READ_DELAY: Long = 100
    private val ON_ACCURACY_CHANGED_LOG = "Accuracy changed"

    private var sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private var isAccelerometerRead = false
    private var isMagnetometerRead = false

    private val accelerometerReading = FloatArray(3)
    private val magnetometerReading = FloatArray(3)

    private val rotationMatrix = FloatArray(9)
    private val orientationAngles = FloatArray(3)

    private var lastUpdateTime: Long = 0

    private val needle: Needle = Needle()
    private val needleLD: MutableLiveData<Needle> = MutableLiveData<Needle>()

    override fun start() {
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)?.also { accelerometer ->
            sensorManager.registerListener(
                this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL
            )
        }
        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)?.also { magneticField ->
            sensorManager.registerListener(
                this, magneticField, SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    override fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d(TAG, ON_ACCURACY_CHANGED_LOG)
    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.d(TAG, event.sensor.name)
        when (event.sensor.type) {
            Sensor.TYPE_ACCELEROMETER -> {
                System.arraycopy(
                    event.values, 0, accelerometerReading, 0, accelerometerReading.size
                )
                isAccelerometerRead = true
            }
            Sensor.TYPE_MAGNETIC_FIELD -> {
                System.arraycopy(
                    event.values, 0, magnetometerReading, 0, magnetometerReading.size
                )
                isMagnetometerRead = true
            }

        }

        if (areSensorRead() && isWaitingFinished()) {
            updateOrientationAngles()
            needle.angle = getAzimuth()
            needleLD.value = needle
            lastUpdateTime = System.currentTimeMillis()
        }

    }

    private fun areSensorRead() = isAccelerometerRead && isMagnetometerRead

    private fun isWaitingFinished() =
        System.currentTimeMillis() - lastUpdateTime > SENSOR_READ_DELAY

    private fun updateOrientationAngles() {
        SensorManager.getRotationMatrix(
            rotationMatrix,
            null,
            accelerometerReading,
            magnetometerReading
        )
        SensorManager.getOrientation(rotationMatrix, orientationAngles)
    }

    private fun getAzimuth() = Math.toDegrees(orientationAngles[0].toDouble()).toFloat()

    override fun getNeedle() = needleLD as LiveData<Needle>
}