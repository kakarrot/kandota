package com.xulee.kandota.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseFragment;
import com.xulee.kandota.mvp.presenter.LegalPresenter;
import com.xulee.kandota.mvp.ui.LegalUi;
import com.xulee.kandota.mvp.ui.LegalUiCallback;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LegalFragment extends BaseFragment implements LegalUi {

    @Bind(R.id.tv_legal)
    TextView tvLegal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_legal, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public Presenter setPresenter() {
        return new LegalPresenter(getActivity(), this);
    }

    @Override
    public void setUiCallback(LegalUiCallback legalUiCallback) {

    }

    public void initViews() {
        tvLegal = (TextView) getView().findViewById(R.id.tv_legal);
    }

    @Override
    public void setLegal(String legal) {
        tvLegal.setText(legal);
    }

}


