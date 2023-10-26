package com.pec.portal.lanz;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.sinoweb.lenzpecframe.LenzPecFrameSDK;

/**
 * Description：Java模块
 * Created by Shelby on 2023/3/23 11:45
 */
public class LenzPecFrameModule extends ReactContextBaseJavaModule {
    private final int REQUEST_CODE = 10001;
    private Promise mPromise;

    public LenzPecFrameModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);

        //注册ActivityResult监听，用于接收SDK返回结果
        reactApplicationContext.addActivityEventListener(new ActivityEventListener() {
            @Override
            public void onActivityResult(Activity activity, int i, int i1, @Nullable Intent intent) {
                //处理SDK返回数据，通过Promise.resolve将结果回调给RN
                if (i1 == Activity.RESULT_OK && i == REQUEST_CODE) {
                    if (null != mPromise) {
                        Object result = intent.getStringExtra("result");
                        mPromise.resolve(result);
                    }

                    mPromise = null;
                }
            }

            @Override
            public void onNewIntent(Intent intent) {

            }
        });
    }

    @NonNull
    @Override
    public String getName() {
        //返回Java模块名
        return "lenz_pec_frame";
    }

    @ReactMethod
    public void PEC_CAMERA(ReadableMap readableMap, Promise promise) {
        //用于监听相机返回结果
        mPromise = promise;

        //启动相机SDK
        LenzPecFrameSDK.PEC_CAMERA(getCurrentActivity(), readableMap.toHashMap(), REQUEST_CODE);
    }
}
