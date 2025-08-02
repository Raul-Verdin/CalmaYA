package com.example.calmaya_watch.presentation

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.Text
import com.google.android.gms.wearable.PutDataMapRequest
import com.google.android.gms.wearable.PutDataRequest
import com.google.android.gms.wearable.Wearable

class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var heartRateSensor: Sensor? = null

    private var currentBpm by mutableStateOf(0f)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Iniciar simulación de BPM cada 5 segundos
        startFakeBpmSender()

        // Inicializar sensores
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE)

        heartRateSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        } ?: run {
            // Si no hay sensor, simular BPM
            startFakeBpmSender()
        }

        // UI Compose
        setContent {
            MaterialTheme(
                colors = MaterialTheme.colors.copy(
                    background = Color.Black,
                    onBackground = Color.White,
                    surface = Color.Black,
                    onSurface = Color.White
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = "CalmaYA Watch", color = Color.White)
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "Ritmo cardíaco: ${currentBpm.toInt()} bpm",
                            color = Color.White
                        )
                    }
                }
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_HEART_RATE) {
            val bpm = event.values[0]
            currentBpm = bpm
            Log.d("CalmaYA", "BPM real: $bpm")

            // Enviar datos simulados (podrías evitar esto si solo quieres datos reales)
            val dataClient = Wearable.getDataClient(this)
            val putDataMapRequest = PutDataMapRequest.create("/stress_data")
            putDataMapRequest.dataMap.putFloat("bpm", bpm)
            val request = putDataMapRequest.asPutDataRequest()
            dataClient.putDataItem(request)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No es necesario manejarlo
    }

    override fun onDestroy() {
        super.onDestroy()
        sensorManager.unregisterListener(this)
    }

    private fun startFakeBpmSender() {
        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                val bpm = (60..100).random().toFloat()
                currentBpm = bpm

                Log.d("CalmaYA", "BPM simulado: $bpm")

                val dataClient = Wearable.getDataClient(this@MainActivity)
                val putDataMapRequest = PutDataMapRequest.create("/stress_data")
                putDataMapRequest.dataMap.putFloat("bpm", bpm)
                val request = putDataMapRequest.asPutDataRequest().setUrgent()
                dataClient.putDataItem(request)
                handler.postDelayed(this, 5000)
            }
        }

        handler.post(runnable)
    }

}
