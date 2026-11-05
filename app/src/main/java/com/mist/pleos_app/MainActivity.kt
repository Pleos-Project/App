package com.mist.pleos_app

import ai.pleos.playground.vehicle.Vehicle
import ai.pleos.playground.vehicle.api.model.area.DoorArea
import ai.pleos.playground.vehicle.constant.area.DoorValue
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

/**
 * 개발 환경 확인용 시작 화면.
 * Vehicle SDK로 운전석 도어 위치를 읽어서 SDK 연결이 정상인지 보여준다.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var vehicle: Vehicle
    private lateinit var statusView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        statusView = findViewById(R.id.status)

        vehicle = Vehicle(applicationContext).also { it.initialize() }
        checkVehicleSdk()
    }

    private fun checkVehicleSdk() {
        vehicle.getDoor().getCurrentDoorPosition(
            area = DoorArea(DoorValue.ROW_1_LEFT),
            onComplete = { position: Int? ->
                Log.i(TAG, "Door ROW_1_LEFT position = $position")
                runOnUiThread { statusView.text = getString(R.string.sdk_ok, position) }
            },
            onFailed = { e: Exception ->
                Log.e(TAG, "Vehicle SDK call failed", e)
                runOnUiThread { statusView.text = getString(R.string.sdk_failed, e.message) }
            },
        )
    }

    override fun onDestroy() {
        vehicle.release()
        super.onDestroy()
    }

    private companion object {
        const val TAG = "PleosApp"
    }
}
