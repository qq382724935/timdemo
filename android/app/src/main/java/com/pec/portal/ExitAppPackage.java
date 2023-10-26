/*
 * @Author: 刘利军
 * @Date: 2020-12-23 16:30:16
 * @LastEditors: 刘利军
 * @LastEditTime: 2020-12-23 16:58:13
 * @Description: 
 * @PageName: 
 */
package com.pec.portal;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.bridge.JavaScriptModule;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExitAppPackage implements ReactPackage {

  @Override
  public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }

  //@Override
  public List<Class<? extends JavaScriptModule>> createJSModules() {
      return Collections.emptyList();
  }

  @Override
  public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    List<NativeModule> modules = new ArrayList<>();
    modules.add(new ExitAppModule(reactContext));
    return modules;
  }

}