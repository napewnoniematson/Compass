package com.napewnoniematson.compass.logic

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.napewnoniematson.compass.model.compass.Needle
import kotlin.math.roundToInt
import kotlin.random.Random

class SensorReaderImpl(context: Context) : SensorEventListener, SensorReader, NeedleReader {

    private val TAG: String? = SensorReaderImpl::class.simpleName

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
                this,
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL
//                ,
//                SensorManager.SENSOR_DELAY_UI
            )
        }
        sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)?.also { magneticField ->
            sensorManager.registerListener(
                this,
                magneticField,
                SensorManager.SENSOR_DELAY_NORMAL
//                ,
//                SensorManager.SENSOR_DELAY_UI
            )
        }
    }

    override fun stop() {
        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        println("Accuracy has changed. Temporary do nothing")
    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.d(TAG, event.sensor.name)
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, accelerometerReading, 0, accelerometerReading.size)
            isAccelerometerRead = true
        } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, magnetometerReading, 0, magnetometerReading.size)
            isMagnetometerRead = true
        }
        if (isAccelerometerRead && isMagnetometerRead && System.currentTimeMillis() - lastUpdateTime > 100) {
            updateOrientationAngles()
            lastUpdateTime = System.currentTimeMillis()

            needle.angle = Math.toDegrees(orientationAngles[0].toDouble()).toFloat()
//            needle.angle =
//                (Math.toDegrees(orientationAngles[0].toDouble() + 360).toFloat() % 360).roundToInt()
//                    .toFloat()
            needleLD.value = needle
        }
    }

    private fun updateOrientationAngles() {
        SensorManager.getRotationMatrix(rotationMatrix,
            null,
            accelerometerReading,
            magnetometerReading
        )
        SensorManager.getOrientation(rotationMatrix, orientationAngles)
    }

    override fun getNeedle() = needleLD as LiveData<Needle>
}