package com.example.latte_core.app;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;

import java.util.ArrayList;
import java.util.HashMap;

public class Configurator {
    private static final HashMap<String, Object> LATTE_CONFIGS = new HashMap<String, Object>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<IconFontDescriptor>();


    private Configurator() {
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), false);
    }

    public static Configurator getInstance() {
        return Hold.INSTANCE;
    }

    public final HashMap<String, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    private static class Hold {
        private static final Configurator INSTANCE = new Configurator();
    }

    /**
     * 相关配置
     */
    public void config() {
        initIcons();
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(), true);
    }

    public final Configurator withHost(String host) {
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(), host);
        return this;
    }

    public final Configurator withIcons(IconFontDescriptor iconFontDescriptor) {
        ICONS.add(iconFontDescriptor);
        return this;
    }

    public static void checkConfiguration() {
        boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady) {
            throw new RuntimeException("configuration is not ready, call configurator");
        }
    }

    public <T> T getConfiguration(Enum<ConfigType> key) {
        checkConfiguration();
        return (T) LATTE_CONFIGS.get(key.name());
    }

    public void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }
}
