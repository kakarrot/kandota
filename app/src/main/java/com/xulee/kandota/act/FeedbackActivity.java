package com.xulee.kandota.act;

import android.widget.EditText;

import com.liuguangqiang.android.mvp.Presenter;
import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseActivity;
import com.xulee.kandota.mvp.presenter.FeedbackPresenter;
import com.xulee.kandota.mvp.ui.FeedbackUi;
import com.xulee.kandota.mvp.ui.FeedbackUiCallback;
import com.xulee.kandota.utils.DialogUtils;

import butterknife.Bind;

public class FeedbackActivity extends BaseActivity implements FeedbackUi {

    private static final int ACTION_SEND = 0;

    @Bind(R.id.et_content)
    EditText etContent;

    private FeedbackUiCallback mCallback;

    @Override
    public void setUiCallback(FeedbackUiCallback feedbackUiCallback) {
        mCallback = feedbackUiCallback;
    }

    @Override
    public Presenter setPresenter() {
        return new FeedbackPresenter(getApplicationContext(), this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_feedback;
    }

    @Override
    public void showLoading() {
        DialogUtils.showLoading(FeedbackActivity.this);
    }

    @Override
    public void hideLoading() {
        DialogUtils.hideLoading();
    }

    @Override
    public void onSuccess() {
        finish();
    }

    private void postFeedback() {
        String content = etContent.getText().toString().trim();
        if (content.length() > 0) {
            mCallback.feedback(content);
        }
    }

}
