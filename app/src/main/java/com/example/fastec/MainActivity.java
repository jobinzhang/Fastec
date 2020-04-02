package com.example.fastec;

import android.widget.Toast;

import com.example.latte_core.activities.ProxyActivity;
import com.example.latte_core.delegates.BaseDelegate;
import com.example.latte_ec.sign.ISignListener;
import com.example.latte_ec.sign.SignUpDelegate;

public class MainActivity extends ProxyActivity implements ISignListener {


    @Override
    public BaseDelegate setRootDelegate() {
        return new SignUpDelegate();
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
    }
}
