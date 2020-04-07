package com.example.latte_ec.launcher;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.example.latte_core.app.AccountManager;
import com.example.latte_core.app.IUserChecker;
import com.example.latte_core.delegates.BaseDelegate;
import com.example.latte_core.ui.launcher.LauncherHolderCreator;
import com.example.latte_core.ui.launcher.ScrollLauncherTag;
import com.example.latte_core.util.storage.LattePreference;
import com.example.latte_ec.R;
import com.example.latte_ec.main.EcBottomDelegate;
import com.example.latte_ec.sign.SignInDelegate;

import java.util.ArrayList;
import java.util.List;

public class LauncherScrollDelegate extends BaseDelegate implements OnItemClickListener {

    private ConvenientBanner<Integer> convenientBanner;
    private List<Integer> resourceIds = new ArrayList<>();

    private void initBanner() {
        resourceIds.add(R.mipmap.launcher_00);
        resourceIds.add(R.mipmap.launcher_01);
        resourceIds.add(R.mipmap.launcher_02);
        resourceIds.add(R.mipmap.launcher_03);
        resourceIds.add(R.mipmap.launcher_04);
        resourceIds.add(R.mipmap.launcher_05);
        convenientBanner.setPages(new LauncherHolderCreator(), resourceIds).
                setPageIndicator(new int[]{R.drawable.dot_focus, R.drawable.dot_normal}).
                setOnItemClickListener(this).
                setCanLoop(false);
    }

    @Override
    public Object setLayout() {
        convenientBanner = new ConvenientBanner<>(getContext());
        return convenientBanner;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        // 滑动到最后一个
        if (position == resourceIds.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_LAUNCHERED_APP.name(), true);
            // 判断用户是否登录
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    Toast.makeText(getActivity(), "on signin", Toast.LENGTH_LONG);
                    // 登录成功跳转主页面
                    getSupportDelegate().start(new EcBottomDelegate());
                }
                @Override
                public void onNotSignIn() {
                    // 没有登录跳转登录页面
                    getSupportDelegate().start(new SignInDelegate());
                }
            });
        }
    }
}
