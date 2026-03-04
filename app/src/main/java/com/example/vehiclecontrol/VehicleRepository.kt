package com.example.vehiclecontrol

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log

class VehicleRepository(private val context: Context) {
    private var vehicleInterface: IVehicleHardwareInterface? = null

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            vehicleInterface = IVehicleHardwareInterface.Stub.asInterface(service)
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            vehicleInterface = null
        }
    }

    fun connect() {
        val intent = Intent(context, MockVehicleService::class.java)
        context.bindService(intent, connection, Context.BIND_AUTO_CREATE)
    }

    fun setTemperature(temp: Float) {
        try {
            vehicleInterface?.setHvacTemperature(temp)
        } catch (e: RemoteException) {
            Log.e("VehicleRepo", "Call AIDL error: ${e.message}")
        }
    }
}