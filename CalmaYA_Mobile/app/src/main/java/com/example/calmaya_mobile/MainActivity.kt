package com.example.calmaya_mobile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataEventBuffer
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.Wearable


class MainActivity : AppCompatActivity(), DataClient.OnDataChangedListener {

    private lateinit var bpmTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bpmTextView = findViewById(R.id.bpmTextView)

        // Iniciar con mensaje base
        bpmTextView.text = "Esperando BPM…"
    }

    override fun onStart() {
        super.onStart()
        Wearable.getDataClient(this).addListener(this)
    }

    override fun onStop() {
        super.onStop()
        Wearable.getDataClient(this).removeListener(this)
    }

    override fun onDataChanged(dataEvents: DataEventBuffer) {
        for (event in dataEvents) {
            if (event.type == DataEvent.TYPE_CHANGED) {
                val item = event.dataItem
                if (item.uri.path == "/stress_data") {
                    val dataMap = DataMapItem.fromDataItem(item).dataMap
                    val bpm = dataMap.getFloat("bpm")

                    Log.d("CalmaYA-Phone", "BPM recibido: $bpm")

                    runOnUiThread {
                        bpmTextView.text = "Ritmo cardíaco: ${bpm.toInt()} bpm"
                    }
                }
            }
        }
    }
}

