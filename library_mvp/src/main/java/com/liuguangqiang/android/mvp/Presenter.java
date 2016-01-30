package com.liuguangqiang.android.mvp;

/**
 * Created by Eric on 15/5/8.
 */
public abstract class Presenter<U extends BaseUi<UC>, UC> {

    private boolean isAttachedUi = false;

    public Presenter() {
    }

    public Presenter(U ui) {
        this.mUi = ui;
    }

    private OnUiAttachedListener onUiAttachedListener;

    private U mUi;

    public U getUi() {
        return mUi;
    }

    public void setOnUiAttachedListener(OnUiAttachedListener listener) {
        onUiAttachedListener = listener;
    }

    public boolean isAttachedUi() {
        return isAttachedUi;
    }

    public void attach() {
        attach(mUi);
    }

    public synchronized final void attach(U ui) {
        if (!isAttachedUi) {
            checkArgument(ui);
            ui.setUiCallback(createUiCallback(ui));
            populateUi(ui);
            this.mUi = ui;
            isAttachedUi = true;
            onAttachedUi();
        }
    }

    public void detach() {
        detach(mUi);
    }

    public synchronized final void detach(U ui) {
        if (isAttachedUi) {
            checkArgument(ui);
            ui.setUiCallback(null);
            this.mUi = null;
            isAttachedUi = false;
        }
    }

    protected abstract void populateUi(U ui);

    protected abstract UC createUiCallback(U ui);

    private void checkArgument(U ui) {
        if (ui == null)
            throw new IllegalArgumentException("Presenter can not attach or detach any null object!");
    }

    protected void onAttachedUi() {
        if (onUiAttachedListener != null) onUiAttachedListener.onAttachedUi();
    }

    public interface OnUiAttachedListener {

        void onAttachedUi();

    }

}
