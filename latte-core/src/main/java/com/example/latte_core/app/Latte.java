package com.example.latte_core.app;

import android.content.Context;

import java.util.HashMap;

public final class Latte {

    public static Configurator init(Context context) {

        getConfigurations().put(ConfigType.APPLICATION_CONTEXT.name(), context);
        return Configurator.getInstance();
    }

    private static HashMap<String, Object> getConfigurations() {
        return Configurator.getInstance().getLatteConfigs();
    }

    public static Context getApplicaton() {
        return (Context) getConfigurations().get(ConfigType.APPLICATION_CONTEXT.name());
    }
}
