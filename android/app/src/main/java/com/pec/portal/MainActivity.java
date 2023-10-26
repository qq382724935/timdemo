/*
 * @Author: 刘利军
 * @Date: 2020-06-22 11:12:02
 * @LastEditors: 刘利军
 * @LastEditTime: 2023-03-17 13:21:48
 * @Description: 
 */ 
package com.pec.portal;

import com.facebook.react.ReactActivity;
import org.devio.rn.splashscreen.SplashScreen;
import android.os.Bundle;
import android.util.Log;
import android.content.Intent;


public class MainActivity extends ReactActivity {

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */
  @Override
  protected String getMainComponentName() {
    return "pecportal";
  }
  // 启动屏重写onCreate
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    SplashScreen.show(this,true);  // here 
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onResume() {
      super.onResume();
      Log.e("xxxxxx","onResume=");
  }
  @Override
  protected void onPause() {
      super.onPause();
      Log.e("xxxxxx","onPause=");
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
  }
}
