// WearDataReceiver.kt
package com.example.calmaya_mobile

import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.android.gms.wearable.*

class WearDataReceiver : WearableListenerService() {
    override fun onDataChanged(dataEvents: DataEventBuffer) {
        for (event in dataEvents) {
            if (event.type == DataEvent.TYPE_CHANGED) {
                val path = event.dataItem.uri.path
                if (path == "/stress_data") {
                    val dataMap = DataMapItem.fromDataItem(event.dataItem).dataMap
                    val bpm = dataMap.getFloat("bpm")

                    Log.d("WearDataReceiver", "BPM recibido: $bpm")

                    // Enviarlo a MainActivity
                    val intent = Intent("BPM_UPDATE")
                    intent.putExtra("bpm", bpm)
                    LocalBroadcastManager.getInstance(this).sendBroadcast(intent)
                }
            }
        }
    }
}
