package com.w2016.noacceleration;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Build;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookMain implements IXposedHookLoadPackage {
    public static SensorEventListener sensorEventListener;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorEventListener.class, Sensor.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                if (param.args[0] != null) {
                    sensorEventListener = (SensorEventListener) param.args[0];
                    process_listener(sensorEventListener);
                }
            }
        });

        XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorEventListener.class, Sensor.class, int.class, android.os.Handler.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                if (param.args[0] != null) {
                    sensorEventListener = (SensorEventListener) param.args[0];
                    process_listener(sensorEventListener);
                }
            }
        });

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {

            XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorEventListener.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null) {
                        sensorEventListener = (SensorEventListener) param.args[0];
                        process_listener(sensorEventListener);
                    }
                }
            });

            XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorEventListener.class, int.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null) {
                        sensorEventListener = (SensorEventListener) param.args[0];
                        process_listener(sensorEventListener);
                    }
                }
            });
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorEventListener.class, Sensor.class, int.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null) {
                        sensorEventListener = (SensorEventListener) param.args[0];
                        process_listener(sensorEventListener);
                    }
                }
            });

            XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorEventListener.class, Sensor.class, int.class, int.class, android.os.Handler.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null) {
                        sensorEventListener = (SensorEventListener) param.args[0];
                        process_listener(sensorEventListener);
                    }
                }
            });
        }


    }

    public void process_listener(SensorEventListener sensorEventListener1) {
        if (sensorEventListener1 != null) {
            XposedHelpers.findAndHookMethod(sensorEventListener1.getClass(), "onSensorChanged", android.hardware.SensorEvent.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null) {
                        SensorEvent sensorEvent = (SensorEvent) param.args[0];
                        int sensorType = sensorEvent.sensor.getType();
                        if (sensorType == Sensor.TYPE_ACCELEROMETER || sensorType == Sensor.TYPE_ACCELEROMETER_UNCALIBRATED || sensorType == Sensor.TYPE_LINEAR_ACCELERATION) {
                            sensorEvent.values[0] = (float) 0.0001;
                            sensorEvent.values[1] = (float) 0.0001;
                            sensorEvent.values[2] = (float) 0.0001;
                        }
                    }
                }
            });
        }
    }
}
