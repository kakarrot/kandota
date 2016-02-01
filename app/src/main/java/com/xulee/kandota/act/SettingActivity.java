package com.xulee.kandota.act;

import android.os.Bundle;

import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseActivity;
import com.xulee.kandota.fragment.SettingFragment;

/**
 * 设置。
 * <p/>
 * Created by Eric on 2014-7-9
 */
public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.set_title);
        replaceFragment(new SettingFragment());
    }
}
