package com.xulee.kandota.view.banner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.banner.widget.Banner.BaseIndicatorBanner;
import com.xulee.kandota.R;
import com.xulee.kandota.utils.ViewFindUtils;

public class SimpleGuideBanner extends BaseIndicatorBanner<Integer, SimpleGuideBanner> {
    public SimpleGuideBanner(Context context) {
        this(context, null, 0);
    }

    public SimpleGuideBanner(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleGuideBanner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setBarShowWhenLast(false);
    }

    @Override
    public View onCreateItemView(int position) {
        View inflate = View.inflate(mContext, R.layout.adapter_simple_guide, null);
        ImageView iv = ViewFindUtils.find(inflate, R.id.iv);
        TextView tv_jump = ViewFindUtils.find(inflate, R.id.tv_jump);

        final Integer resId = mDatas.get(position);
        tv_jump.setVisibility(position == mDatas.size() - 1 ? VISIBLE : GONE);

        iv.setImageResource(resId);

        tv_jump.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onJumpClickL != null)
                    onJumpClickL.onJumpClick();
            }
        });

        return inflate;
    }

    private OnJumpClickL onJumpClickL;

    public interface OnJumpClickL {
        void onJumpClick();
    }

    public void setOnJumpClickL(OnJumpClickL onJumpClickL) {
        this.onJumpClickL = onJumpClickL;
    }
}
