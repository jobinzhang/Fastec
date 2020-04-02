package com.example.latte_ec.sign;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.delegates.BaseDelegate;
import com.example.latte_core.net.RestClient;
import com.example.latte_core.net.callback.ISuccess;
import com.example.latte_ec.R;
import com.example.latte_ec.R2;
import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.OnClick;


public class SignInDelegate extends BaseDelegate {

    @BindView(R2.id.sign_in_name)
    TextInputEditText mname = null;
    @BindView(R2.id.sign_in_password)
    TextInputEditText mPassword = null;
    private String username;
    private String password;
    private ISignListener iSignListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            iSignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_in)
    public void onclickSignIn() {
        boolean isCheckOk = checkForm();
        if (isCheckOk) {
            JSONObject body = new JSONObject();
            body.put("username",username);
            body.put("password",password);
            RestClient.builder().url("/fastec/user/login").
                   raw(body.toString()).
                    success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            SignHandler.onSignIn(response, iSignListener);
                        }
            }).build().post();
        }
    }

    @OnClick(R2.id.link_sign_up)
    public void onClickLinkSignUp() {
        start(new SignUpDelegate());
    }

    private boolean checkForm() {
        username = mname.getText().toString();
        password = mPassword.getText().toString();
        boolean isPass = true;
        if (username.isEmpty()) {
            mname.setError("用户名为空");
            isPass = false;
        } else {
            mname.setError(null);
        }

        if (password.isEmpty()) {
            mPassword.setError("密码为空");
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
