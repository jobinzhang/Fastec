package com.example.latte_ec.sign;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.latte_core.app.AccountManager;

public class SignHandler {

    public static void onSignIn(String response, ISignListener signListener) {
        // 解析登录信息
        Log.i("onSignIn:", response);
        JSONObject jsonObject = JSON.parseObject(response);
        JSONObject user = jsonObject.getJSONObject("data");
        // 登录失败
        if (user == null) {
            signListener.onSignInError();
        } else {
            AccountManager.setSignState(true);
            signListener.onSignInSuccess();
        }
    }

    public static void onSignUp(String response, ISignListener signListener) {
        Log.i("onSignUp", response);
        JSONObject jsonObject = JSON.parseObject(response);
        if (jsonObject.getIntValue("state") != 0) {
            signListener.onSignUpFail(jsonObject.getString("message"));
        } else {
            // 注册成功
            signListener.onSignUpSuccess();
        }
    }
}
