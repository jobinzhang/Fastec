package com.example.latte_ec.sign;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.latte_core.delegates.BaseDelegate;
import com.example.latte_ec.R;
import com.example.latte_ec.R2;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;


public class SignInDelegate extends BaseDelegate {

    @BindView(R2.id.sign_in_mail)
    TextInputEditText mEmail = null;
    @BindView(R2.id.sign_in_password)
    TextInputEditText mPassword = null;

    @OnClick(R2.id.btn_sign_in)
    public void onclickSignIn() {
        boolean isCheckOk = checkForm();
        if (isCheckOk) {
            //TODO 登录
        }
    }

    @OnClick(R2.id.link_sign_up)
    public void onClickLinkSignUp() {
        start(new SignUpDelegate());
    }

    private boolean checkForm() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();
        boolean isPass = true;
        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }
        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
