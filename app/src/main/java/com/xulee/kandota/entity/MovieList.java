package com.xulee.kandota.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LX on 2016/1/27.
 */
public class MovieList implements Parcelable {
    private List<Movie> data;

    public void setData(List<Movie> data) {
        this.data = data;
    }

    public List<Movie> getData() {
        return data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.data);
    }

    public MovieList() {
    }

    protected MovieList(Parcel in) {
        this.data = new ArrayList<Movie>();
        in.readList(this.data, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<MovieList> CREATOR = new Parcelable.Creator<MovieList>() {
        public MovieList createFromParcel(Parcel source) {
            return new MovieList(source);
        }

        public MovieList[] newArray(int size) {
            return new MovieList[size];
        }
    };
}
