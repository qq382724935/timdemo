/*
 * @Author: 刘利军
 * @Date: 2023-04-13 13:03:40
 * @LastEditors: 刘利军
 * @LastEditTime: 2023-04-13 13:04:06
 * @Description: 
 * @PageName: 
 */
package com.pec.portal.lanz;

import androidx.annotation.NonNull;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.uimanager.ViewManager;
import com.sinoweb.lenzpecframe.LenzPecFrameSDK;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description：原生模块包
 * Created by Shelby on 2023/3/23 11:48
 */
public class LenzPecFramePackage implements ReactPackage {
    @NonNull
    @Override
    public List<NativeModule> createNativeModules(@NonNull ReactApplicationContext reactApplicationContext) {
        List<NativeModule> modules = new ArrayList<>();
        modules.add(new LenzPecFrameModule(reactApplicationContext));        
        return modules;
    }

    @NonNull
    @Override
    public List<ViewManager> createViewManagers(@NonNull ReactApplicationContext reactApplicationContext) {
        return Collections.emptyList();
    }
}
