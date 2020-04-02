package com.example.latte_ec.sign;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

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
            signListener.onSignInSuccess();
        }
    }

    public static void onSignUp(String response, ISignListener signListener) {

    }
}
