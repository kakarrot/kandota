package com.xulee.kandota.entity.base;

import android.os.Parcel;
import android.os.Parcelable;

public class Id implements Parcelable {

    private String $id;

    public String get$id() {
        return $id;
    }

    public void set$id(String $id) {
        this.$id = $id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.$id);
    }

    public Id() {
    }

    private Id(Parcel in) {
        this.$id = in.readString();
    }

    public static final Creator<Id> CREATOR = new Creator<Id>() {
        public Id createFromParcel(Parcel source) {
            return new Id(source);
        }

        public Id[] newArray(int size) {
            return new Id[size];
        }
    };
}
