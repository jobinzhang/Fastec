package com.example.fastec;

import android.widget.Toast;

import com.example.latte_core.activities.ProxyActivity;
import com.example.latte_core.delegates.BaseDelegate;
import com.example.latte_ec.launcher.LauncherDelegate;
import com.example.latte_ec.sign.ISignListener;
import com.example.latte_ec.sign.SignInDelegate;
import com.example.latte_ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity implements ISignListener {


    @Override
    public BaseDelegate setRootDelegate() {
        return new LauncherDelegate();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "onSignInSuccess", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignInError() {
        Toast.makeText(this, "onSignError", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "onSignUpSuccess", Toast.LENGTH_SHORT).show();
        // 注册成功，跳转登录页面
        start(new SignInDelegate());
    }

    @Override
    public void onSignUpFail(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
