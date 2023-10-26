/*
 * @Author: 刘利军
 * @Date: 2020-06-22 11:12:02
 * @LastEditors: 刘利军
 * @LastEditTime: 2023-08-17 13:47:30
 * @Description: 
 */ 
package com.pec.portal;

import android.app.Application;
import android.content.Context;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.soloader.SoLoader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import android.util.Log;

import com.microsoft.codepush.react.CodePush; // codepush

import android.webkit.WebView;

/** 友盟开始 */
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.pec.portal.umeng.DplusReactPackage;
/** 友盟结束 */

import com.pec.portal.ExitAppPackage;
import com.pec.portal.PPUtilsPackage;

// 朗境
import com.pec.portal.lanz.LenzPecFramePackage;

import android.os.Build;
import android.app.ActivityManager;


import android.annotation.TargetApi;
import android.text.TextUtils;
import android.webkit.WebView;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;


public class MainApplication extends Application implements ReactApplication {
  private static final String TAG = MainApplication.class.getName();

  private final ReactNativeHost mReactNativeHost =
      new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          // Packages that cannot be autolinked yet can be added manually here, for example:
          // packages.add(new MyReactNativePackage());
          packages.add(new DplusReactPackage());
          packages.add(new ExitAppPackage());
          packages.add(new PPUtilsPackage());
          packages.add(new LenzPecFramePackage());
          return packages;
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }

        @Override
        protected String getJSBundleFile() {
            return CodePush.getJSBundleFile(); // codepush
        }
      };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    SoLoader.init(this, /* native exopackage */ false);
    initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
    
    UMConfigure.preInit(this,BuildConfig.UM_APP_KEY_ANDROID,"Umeng");

    WebView.setWebContentsDebuggingEnabled(true);
    handleWebViewDir(this);
  }
  

  /**
   * Loads Flipper in React Native templates. Call this in the onCreate method with something like
   * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
   *
   * @param context
   * @param reactInstanceManager
   */
  private static void initializeFlipper(
      Context context, ReactInstanceManager reactInstanceManager) {
    if (BuildConfig.DEBUG) {
      try {
        /*
         We use reflection here to pick up the class that initializes Flipper,
        since Flipper library is not available in release mode
        */
        Class<?> aClass = Class.forName("com.pec.portal.ReactNativeFlipper");
        aClass
            .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
            .invoke(null, context, reactInstanceManager);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }


  private static void handleWebViewDir(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            return;
        }
        try {
            String suffix = "";
            String processName = getCurProcessName(context);
            if (!TextUtils.equals(context.getPackageName(), processName)) {//判断不等于默认进程名称
                suffix = TextUtils.isEmpty(processName) ? context.getPackageName() : processName;
                WebView.setDataDirectorySuffix(suffix);
                suffix = "_" + suffix;
            }
            tryLockOrRecreateFile(context,suffix);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(Build.VERSION_CODES.P)
    private static void tryLockOrRecreateFile(Context context, String suffix) {
        String sb = context.getDataDir().getAbsolutePath() +
                "/app_webview"+suffix+"/webview_data.lock";
        File file = new File(sb);
        if (file.exists()) {
            try {
                FileLock tryLock = new RandomAccessFile(file, "rw").getChannel().tryLock();
                if (tryLock != null) {
                    tryLock.close();
                } else {
                    createFile(file, file.delete());
                }
            } catch (Exception e) {
                e.printStackTrace();
                boolean deleted = false;
                if (file.exists()) {
                    deleted = file.delete();
                }
                createFile(file, deleted);
            }
        }
    }

    private static void createFile(File file, boolean deleted){
        try {
            if (deleted && !file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
                .getRunningAppProcesses();
        if (appProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess == null) {
                continue;
            }
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }


}
