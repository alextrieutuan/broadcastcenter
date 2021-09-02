package com.alext.demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alext.broadcastcenter.LocalBroadcastCenter
import com.alext.broadcastcenter.Payload

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LocalBroadcastCenter.init(this)

        // Listen
        LocalBroadcastCenter.registerForIntent(this, "IntentAction") {
            Log.d("LocalBroadcastCenter", "Received $it")
        }
        LocalBroadcastCenter.register<Float>(this,"FloatAction") {
            Log.d("LocalBroadcastCenter", "Received $it")
        }
        LocalBroadcastCenter.register<String>(this,"StringAction") {
            Log.d("LocalBroadcastCenter", "Received $it")
        }
        LocalBroadcastCenter.register<ExampleDataModel>(this,"ModelAction") {
            Log.d("LocalBroadcastCenter", "Received $it")
        }

        // Broadcast
        LocalBroadcastCenter.broadcast("IntentAction")
        LocalBroadcastCenter.broadcast("StringAction", Payload("Payload String"))
        LocalBroadcastCenter.broadcast("FloatAction", Payload(1.0f))
        LocalBroadcastCenter.broadcast("ModelAction", Payload(ExampleDataModel("hehehehe")))
    }

    private data class ExampleDataModel(val title: String)
}