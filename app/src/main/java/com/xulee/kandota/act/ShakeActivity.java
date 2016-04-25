package com.xulee.kandota.act;

import android.os.Bundle;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseActivity;
import com.xulee.kandota.mvp.presenter.ShakePresenter;
import com.xulee.kandota.mvp.ui.ShakeUi;
import com.xulee.kandota.mvp.ui.ShakeUiCallback;

/**
 * 摇积分/现场摇/摇奖品
 */
public class ShakeActivity extends BaseActivity implements ShakeUi {

    public static final int TYPE_SHAKE_SCORE = 0x001;
    public static final int TYPE_SHAKE_LIVE = 0x002;
    public static final int TYPE_SHAKE_PRIZE = 0x003;

    public static String TYPE_SHAKE = "type_shake";

    private ShakeUiCallback mCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttachedUi() {
        super.onAttachedUi();
        mCallback.showShake(getShakeType());
    }

    @Override
    public Presenter setPresenter() {
        return new ShakePresenter(this, this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_shake;
    }

    @Override
    public void setUiCallback(ShakeUiCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public void showShake(int shakeType) {
        switch (shakeType){
            case TYPE_SHAKE_SCORE:
                setTitle(R.string.str_shake_score);
                break;
            case TYPE_SHAKE_LIVE:
                setTitle(R.string.str_shake_live);
                break;
            case TYPE_SHAKE_PRIZE:
                setTitle(R.string.str_shake_prize);
                break;
        }
    }

    @Override
    public int getShakeType() {
        int type = getIntent().getIntExtra(TYPE_SHAKE, TYPE_SHAKE_SCORE);
        return type;
    }
}
