package com.w2016.noacceleration;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class SensorHelper {

    private final SensorEventListener attached_sensorEventListener;

    public SensorHelper(SensorEventListener sensorEventListener){
        attached_sensorEventListener = sensorEventListener;
        processSensorReplacement();
    }

    public SensorEventListener getAttached_sensorEventListener() {
        return attached_sensorEventListener;
    }

    private void processSensorReplacement(){
        if (attached_sensorEventListener != null){
            try {
                XposedHelpers.findAndHookMethod(attached_sensorEventListener.getClass(), "onSensorChanged", android.hardware.SensorEvent.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        if (param.args[0] != null) {
                            SensorEvent sensorEvent = (SensorEvent) param.args[0];
                            int sensorType = sensorEvent.sensor.getType();
                            switch (sensorType) {
                                case Sensor.TYPE_MAGNETIC_FIELD:
                                case Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED:
                                case Sensor.TYPE_ACCELEROMETER:
                                case Sensor.TYPE_ACCELEROMETER_UNCALIBRATED:
                                case Sensor.TYPE_LINEAR_ACCELERATION:
                                    sensorEvent.values[0] = (float) Math.floor(sensorEvent.values[0]);
                                    sensorEvent.values[1] = (float) Math.floor(sensorEvent.values[1]);
                                    sensorEvent.values[2] = (float) Math.floor(sensorEvent.values[2]);
                                    break;
                                default:
                                    break;
                            }

                        }
                    }
                });
            } catch (Throwable ee) {
                XposedBridge.log(ee);
            }
        }
    }
}
