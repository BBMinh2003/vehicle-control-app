// IVehicleHardwareInterface.aidl
package com.example.vehiclecontrol;

// Declare any non-default types here with import statements

interface IVehicleHardwareInterface {
    float getHvacTemperature();
    void setHvacTemperature(float temp);
    int getWindowStatus(int windowId);
    void setWindowStatus(int windowId, int position);
}