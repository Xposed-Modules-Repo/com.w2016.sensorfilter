package com.w2016.noacceleration;

import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookMain implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        /*
        XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorEventListener.class, Sensor.class, int.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                if (param.args[0] != null) {
                    param.args[2] =  replace_delayUs((Integer) param.args[2]);
                    new SensorHelper((SensorEventListener) param.args[0]);
                    XposedBridge.log("应用创建传感器监听"+lpparam.appInfo.name + "创建--linstener：" + param.args[0].toString() + "创建--type：" + param.args[1].toString());
                }
            }
        });

         */

        XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorEventListener.class, Sensor.class, int.class, android.os.Handler.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                super.beforeHookedMethod(param);
                if (param.args[0] != null) {
                    param.args[2] =  replace_delayUs((Integer) param.args[2]);
                    new SensorHelper((SensorEventListener) param.args[0]);
                    XposedBridge.log("应用创建传感器监听"+lpparam.appInfo.name + "创建--linstener：" + param.args[0].toString() + "创建--type：" + param.args[1].toString());
                }
            }
        });

/*
        try {
            XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorListener.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null) {
                        new SensorHelper_API3((SensorListener) param.args[0]);
                        XposedBridge.log("应用创建传感器监听" + lpparam.appInfo.name + "创建--linstener：" + param.args[0].toString() + "创建--type：" + param.args[1].toString());
                    }
                }
            });
        }catch (Exception ignore){

        }

 */

        try {
            XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorListener.class, int.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null) {
                        new SensorHelper_API3((SensorListener) param.args[0]);
                        param.args[2] =  replace_delayUs((Integer) param.args[2]);
                        XposedBridge.log("应用创建传感器监听" + lpparam.appInfo.name + "创建--linstener：" + param.args[0].toString() + "创建--type：" + param.args[1].toString());
                    }
                }
            });
        }catch (Exception ignore) {
        }

        try {
            XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorEventListener.class, Sensor.class, int.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null) {
                        param.args[2] =  replace_delayUs((Integer) param.args[2]);
                        new SensorHelper_API3((SensorListener) param.args[0]);
                        XposedBridge.log("应用创建传感器监听" + lpparam.appInfo.name + "创建--linstener：" + param.args[0].toString() + "创建--type：" + param.args[1].toString());
                    }
                }
            });
        }catch (Exception ignore) {

        }

        try {
            XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "registerListener", SensorEventListener.class, Sensor.class, int.class, int.class, android.os.Handler.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null) {
                        param.args[2] =  replace_delayUs((Integer) param.args[2]);
                        new SensorHelper((SensorEventListener) param.args[0]);
                        XposedBridge.log("应用创建传感器监听" + lpparam.appInfo.name + "创建--linstener：" + param.args[0].toString() + "创建--type：" + param.args[1].toString());

                    }
                }
            });
        }catch (Exception ignore) {

        }

        /*以下为注销*/
/*
            XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "unregisterListener", SensorEventListener.class , new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null) {
                        XposedBridge.log("应用注销传感器监听"+lpparam.appInfo.name + "注销--linstener：" + param.args[0].toString());
                    }
                }
            });

 */

            XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "unregisterListener", SensorEventListener.class,Sensor.class , new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    if (param.args[0] != null) {
                        XposedBridge.log("应用注销传感器监听"+lpparam.appInfo.name +"注销--linstener：" + param.args[0].toString() + "注销--type：" + param.args[1].toString());
                    }
                }
            });


            try {

                XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "unregisterListener", SensorEventListener.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        if (param.args[0] != null) {
                            XposedBridge.log("应用注销传感器监听" + lpparam.appInfo.name + "注销--linstener：" + param.args[0].toString() );
                        }
                    }
                });
            }catch (Exception ignore){

            }



            try {

                XposedHelpers.findAndHookMethod("android.hardware.SensorManager", lpparam.classLoader, "unregisterListener", SensorListener.class, int.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        if (param.args[0] != null) {
                            XposedBridge.log("应用注销传感器监听" + lpparam.appInfo.name + "注销--linstener：" + param.args[0].toString() + "注销--type：" + param.args[1].toString());
                        }
                    }
                });
            }catch (Exception ignore){

            }
    }


    private int replace_delayUs(int originalDelayUs){
        if ((originalDelayUs > SensorManager.SENSOR_DELAY_FASTEST && originalDelayUs <= SensorManager.SENSOR_DELAY_GAME) || originalDelayUs > 50*1000){
            return originalDelayUs;
        }
        return SensorManager.SENSOR_DELAY_GAME;
    }

}
