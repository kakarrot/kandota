package com.xulee.kandota.fragment;

import android.os.Handler;
import android.support.v4.app.Fragment;

import com.culiu.mhvp.core.InnerScrollerContainer;
import com.culiu.mhvp.core.OuterScroller;
import com.culiu.mhvp.integrated.ptr.pulltorefresh.PullToRefreshBase;

/**
 * @author Xavier-S
 *         <p/>
 *         Extracted codes for demo. In you practice, there's no need to
 *         create an AbsFragment to extend it. Just let your fragment
 *         implement InnerScrollerContainer. It's ok and good enough.
 * @date 2015.11.12 11:20
 */
public abstract class AbsListFragment extends Fragment implements InnerScrollerContainer, PullToRefreshBase.OnRefreshListener2 {

    /*************
     * InnerScrollerContainer interface
     **************/

    protected OuterScroller mOuterScroller;
    protected int mIndex;

    @Override
    public void setOuterScroller(OuterScroller outerScroller, int myPosition) {
        if (outerScroller == mOuterScroller && myPosition == mIndex) {
            return;
        }
        mOuterScroller = outerScroller;
        mIndex = myPosition;

        if (getInnerScroller() != null) {
            getInnerScroller().register2Outer(mOuterScroller, mIndex);
        }
    }

    /************* InnerScrollerContainer interface End **************/


    /****************************** Test code ******************************/
    /**
     * Emulate http asynchronous loading
     */
    protected void requestData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onResponse();
            }
        }, 200);
    }

    public abstract void onResponse();

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        // Update the whole state, and forbidden touch
        getInnerScroller().onRefresh(true);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Return to normal state
                getInnerScroller().onRefresh(false);
                // Test code
                // do something after refresh
                if (getInnerScroller().getReceiveView() instanceof PullToRefreshBase) {
                    ((PullToRefreshBase) getInnerScroller().getReceiveView()).onRefreshComplete();
                }
            }
        }, 200);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
    }
    /****************************** Test code End ******************************/
}
