package com.example.fastec;


import android.os.Bundle;
import android.view.View;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.latte_core.delegates.LatteDelegate;


public class ExampleDelegate extends LatteDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegata_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

}
