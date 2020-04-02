package com.example.latte_ec.sign;

import android.app.Activity;
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

public class SignUpDelegate extends BaseDelegate {

    @BindView(R2.id.sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.sign_up_mail)
    TextInputEditText mEmail = null;
    @BindView(R2.id.sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.sign_up_repassword)
    TextInputEditText mRePassword = null;
    private ISignListener iSignListener;

    @OnClick(R2.id.btn_sign_up)
    public void onClickSignUp() {
        boolean isCheckOK = checkForm();
        if (isCheckOK) {
            // TODO 注册逻辑

        }
    }

    @OnClick(R2.id.link_sign_in)
    public void onClickLinkSignIn() {
        start(new SignInDelegate());
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            iSignListener = (ISignListener) activity;
        }
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("密码验证错误");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
