package com.example.latte_ec.sign;

public interface ISignListener {

    void onSignInSuccess();

    void onSignInError();

    void onSignUpSuccess();

    void onSignUpFail(String message);
}
