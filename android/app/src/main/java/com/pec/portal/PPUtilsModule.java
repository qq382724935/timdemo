/*
 * @Author: 刘利军
 * @Date: 2020-12-23 16:21:46
 * @LastEditors: 刘利军
 * @LastEditTime: 2023-03-17 13:21:54
 * @Description: 
 * @PageName: 
 */
package com.pec.portal;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import android.content.Context;
import android.util.Log;

/** 友盟开始 */
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.pec.portal.umeng.DplusReactPackage;
import com.umeng.analytics.MobclickAgent;
import com.umeng.socialize.UMShareAPI;
import com.pec.portal.umeng.ShareModule;
/** 友盟结束 */



public class PPUtilsModule extends ReactContextBaseJavaModule {
  private static Context appContext;
  private static ReactApplicationContext context;


  public PPUtilsModule(ReactApplicationContext reactContext) {
    super(reactContext);
    context = reactContext;
  }

    @Override
    public String getName() {
        return "PPUtils";
    }

    private static final String TAG = MainApplication.class.getName();

    @ReactMethod
    public void umInit() {
      UMConfigure.init(context, BuildConfig.UM_APP_KEY_ANDROID, "Umeng", UMConfigure.DEVICE_TYPE_PHONE, BuildConfig.UM_MESSAGE_SECRET_ANDROID);
      MobclickAgent.setSessionContinueMillis(1000);
      ShareModule.initSocialSDK(getCurrentActivity());
      String FileProvider = "com.pec.portal.fileprovider";
      PlatformConfig.setWeixin(BuildConfig.WX_KEY, BuildConfig.WX_SECRET);
      PlatformConfig.setWXFileProvider(FileProvider);
      PlatformConfig.setDing(BuildConfig.DDING_KEY);
    }
    
}

