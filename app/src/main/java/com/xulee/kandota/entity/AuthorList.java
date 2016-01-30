package com.xulee.kandota.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by LX on 2016/1/29.
 */
public class AuthorList implements Parcelable {

    private List<Author> data;

    public void setData(List<Author> data) {
        this.data = data;
    }

    public List<Author> getData() {
        return data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(data);
    }

    public AuthorList() {
    }

    protected AuthorList(Parcel in) {
        this.data = in.createTypedArrayList(Author.CREATOR);
    }

    public static final Parcelable.Creator<AuthorList> CREATOR = new Parcelable.Creator<AuthorList>() {
        public AuthorList createFromParcel(Parcel source) {
            return new AuthorList(source);
        }

        public AuthorList[] newArray(int size) {
            return new AuthorList[size];
        }
    };
}
