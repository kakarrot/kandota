package com.xulee.kandota.act;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import com.liuguangqiang.framework.utils.CommonUtils;
import com.xulee.kandota.R;
import com.xulee.kandota.base.BaseActivity;
import com.xulee.kandota.fragment.SearchFragment;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Search books。
 * <p/>
 * Created by Eric on 2014-7-6
 */
public class SearchActivity extends BaseActivity {

    private SearchFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragment = new SearchFragment();
        replaceFragment(fragment);
        initActionBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null && actionBar.isShowing()) {
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);

            SearchViewHolder viewHolder = new SearchViewHolder();
            ActionBar.LayoutParams params =
                    new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(viewHolder.getView(), params);
        }
    }

    public class SearchViewHolder {

        private View view;

        @Bind(R.id.et_search)
        EditText etSearch;

        public View getView() {
            return view;
        }

        public SearchViewHolder() {
            view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.action_bar_search, null);
            ButterKnife.bind(this, view);

            etSearch.requestFocus();
            etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
            etSearch.setOnKeyListener(new View.OnKeyListener() {

                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if (KeyEvent.KEYCODE_ENTER == keyCode
                            && event.getAction() == KeyEvent.ACTION_DOWN) {
                        onSearch();
                        return true;
                    }
                    return false;
                }
            });
        }

        @OnClick(R.id.iv_search)
        public void onSearch() {
            if (etSearch.getText().toString().trim().length() == 0) return;
            CommonUtils.hideSoftKeyborad(SearchActivity.this);
            fragment.search(etSearch.getText().toString().trim());
        }
    }

}
