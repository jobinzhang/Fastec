package com.example.latte_core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

public final class Latte {

    public static Configurator init(Context context) {
        getConfigurations().put(ConfigKeys.APPLICATION_CONTEXT, context);
        return Configurator.getInstance();
    }

    private static HashMap<Object, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static <T> T getConfiguration(Object key) {
        return (T) getConfigurations().get(key);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }

    public static Context getApplicationContext() {
        return (Context) getConfigurations().get(ConfigKeys.APPLICATION_CONTEXT);
    }
}
