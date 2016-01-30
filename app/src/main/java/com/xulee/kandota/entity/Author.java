package com.xulee.kandota.entity;

import android.os.Parcel;
import android.os.Parcelable;


/**
 * Created by LX on 2016/1/29.
 */
public class Author implements Parcelable{

    /**
     * id : UNDI1NTMxMjMy
     * name : Dota情书
     * avtar : http://g4.ykimg.com/0130391F484F82790BC834065745D82FDF80F2-9C8B-5C1D-8CD2-E6E677EF6F70
     */

    private String id;
    private String name;
    private String avtar;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvtar(String avtar) {
        this.avtar = avtar;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAvtar() {
        return avtar;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.avtar);
    }

    public Author() {
    }

    protected Author(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.avtar = in.readString();
    }

    public static final Creator<Author> CREATOR = new Creator<Author>() {
        public Author createFromParcel(Parcel source) {
            return new Author(source);
        }

        public Author[] newArray(int size) {
            return new Author[size];
        }
    };
}
