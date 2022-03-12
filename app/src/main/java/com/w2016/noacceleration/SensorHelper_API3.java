package com.w2016.noacceleration;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;

public class SensorHelper_API3 {
    private final SensorListener attached_sensorListener;

    public SensorHelper_API3(SensorListener sensorEventListener){
        attached_sensorListener = sensorEventListener;
        processSensorReplacement();
    }

    public SensorListener getAttached_sensorEventListener() {
        return attached_sensorListener;
    }

    private void processSensorReplacement(){
        if (attached_sensorListener != null){
            try {
                XposedHelpers.findAndHookMethod(attached_sensorListener.getClass(), "onSensorChanged", int.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        if (param.args[0] != null) {
                            SensorEvent sensorEvent = (SensorEvent) param.args[0];
                            int sensorType = sensorEvent.sensor.getType();
                            switch (sensorType) {
                                case SensorManager.SENSOR_ACCELEROMETER:
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
                XposedBridge.log(ee.toString()+ee.getMessage());
            }
        }
    }

}
