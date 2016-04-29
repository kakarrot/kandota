package com.xulee.kandota.entity.base;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LX on 2016/4/14.
 */
public class BaseResponse implements Parcelable {
    public String status;
    public String message;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.status);
        dest.writeString(this.message);
    }

    public BaseResponse() {
    }

    protected BaseResponse(Parcel in) {
        this.status = in.readString();
        this.message = in.readString();
    }

    public static final Parcelable.Creator<BaseResponse> CREATOR = new Parcelable.Creator<BaseResponse>() {
        @Override
        public BaseResponse createFromParcel(Parcel source) {
            return new BaseResponse(source);
        }

        @Override
        public BaseResponse[] newArray(int size) {
            return new BaseResponse[size];
        }
    };
}
