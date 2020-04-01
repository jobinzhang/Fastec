package com.example.fastec;

import com.example.latte_core.activities.ProxyActivity;
import com.example.latte_core.delegates.BaseDelegate;
import com.example.latte_ec.launcher.LauncherDelegate;
import com.example.latte_ec.launcher.LauncherScrollDelegate;
import com.example.latte_ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity {


    @Override
    public BaseDelegate setRootDelegate() {
        return new SignUpDelegate();
    }
}
