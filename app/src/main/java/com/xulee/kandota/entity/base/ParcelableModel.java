package com.xulee.kandota.entity.base;

import android.os.Parcel;
import android.os.Parcelable;

public class ParcelableModel implements Parcelable {

    public ParcelableModel() {
    }

    public ParcelableModel(Parcel source) {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

    }

    public static final Creator<ParcelableModel> CREATOR = new Creator<ParcelableModel>() {
        public ParcelableModel createFromParcel(Parcel data) {
            return new ParcelableModel(data);
        }

        public ParcelableModel[] newArray(int size) {
            return new ParcelableModel[size];
        }
    };

}
