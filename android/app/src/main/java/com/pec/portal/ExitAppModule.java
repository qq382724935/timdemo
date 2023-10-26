/*
 * @Author: 刘利军
 * @Date: 2020-12-23 16:21:46
 * @LastEditors: 刘利军
 * @LastEditTime: 2020-12-23 17:17:44
 * @Description: 
 * @PageName: 
 */
package com.pec.portal;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.Map;
import java.util.HashMap;

public class ExitAppModule extends ReactContextBaseJavaModule {
  private static ReactApplicationContext context;

  public ExitAppModule(ReactApplicationContext reactContext) {
    super(reactContext);
    context = reactContext;
  }

    @Override
    public String getName() {
        return "ExitApp";
    }

    @ReactMethod
    public void exitApp() {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}