package com.example.latte_ec.launcher;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.latte_core.delegates.BaseDelegate;
import com.example.latte_core.ui.launcher.ScrollLauncherTag;
import com.example.latte_core.util.storage.LattePreference;
import com.example.latte_core.util.timer.BaseTimerTask;
import com.example.latte_core.util.timer.ITimerListener;
import com.example.latte_ec.R;
import com.example.latte_ec.R2;

import java.util.Timer;

import butterknife.BindView;
import butterknife.OnClick;

public class LauncherDelegate extends BaseDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    TextView tvTimerView;
    private Timer timer;
    // 倒计时初始时间
    private int count = 5;

    @OnClick(R2.id.tv_launcher_timer)
    public void onClickTimeView() {
        if (timer != null) {
            timer.cancel();
            tvTimerView.setVisibility(View.INVISIBLE);
        }
        checkIsShowScroll();
    }

    private void initTimer() {
        timer = new Timer();
        BaseTimerTask baseTimerTask = new BaseTimerTask(this);
        timer.schedule(baseTimerTask, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initTimer();
    }

    private void checkIsShowScroll() {
        // 首次启动，展示轮播图
        boolean hasLaunchedApp = LattePreference.getAppFlag(ScrollLauncherTag.HAS_LAUNCHERED_APP.name());
        Log.i("hasLaunchedApp", String.valueOf(hasLaunchedApp));
        if (!hasLaunchedApp) {
            getSupportDelegate().start(new LauncherScrollDelegate(), SINGLETASK);
        }
    }

    /**
     * 倒计时计时器
     */
    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tvTimerView != null) {
                    tvTimerView.setText("跳过 \n" + count);
                    count --;
                    if (count < 0 ) {
                        tvTimerView.setVisibility(View.INVISIBLE);
                        timer.cancel();
                        checkIsShowScroll();
                    }
                }
            }
        });
    }
}