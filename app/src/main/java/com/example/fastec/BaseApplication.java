package com.example.fastec;

import android.app.Application;
import android.util.Log;

import com.example.latte_core.app.Latte;
import com.joanzapata.iconify.fonts.FontAwesomeModule;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        Log.i("fast", "application init...");
        super.onCreate();
        Latte.init(this)
                .withIcons(new FontAwesomeModule())
                .withHost("http://open.play.cn")
                .config();
    }
}
