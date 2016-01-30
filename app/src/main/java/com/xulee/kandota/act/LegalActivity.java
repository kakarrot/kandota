package com.xulee.kandota.act;

import android.os.Bundle;

import com.xulee.kandota.base.BaseActivity;
import com.xulee.kandota.fragment.LegalFragment;


public class LegalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        replaceFragment(new LegalFragment());
    }

}
