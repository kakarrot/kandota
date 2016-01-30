package com.xulee.kandota.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by LX on 2016/1/27.
 */
public class Movie implements Parcelable {

    /**
     * uid : UNjUzMTI1NjQ=
     * img : http://r2.ykimg.com/0541040856A81FB26A0A4B045C1DA1A3
     * title : 【DOTA2】ProDota BU vs PRIES Bo3 #1 领秀解说
     * created_at : 2016-01-27 08:06:12
     * author : DOTA2领秀
     * href : http://v.youku.com/v_show/id_XMTQ1NzgzNDA2MA==.html?from=y1.7-1.2
     * avtar : http://g1.ykimg.com/0130391F4555B330F88ACF00F925CD9BCA7C12-1B6B-99F5-CD35-492EEC0111E6
     * id : 1c5e3841c8f94c7606b10c6fc421d482
     * channel : youku
     */
    private String uid;
    private String img;
    private String title;
    private String created_at;
    private String author;
    private String href;
    private String avtar;
    private String id;
    private String channel;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setAvtar(String avtar) {
        this.avtar = avtar;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getUid() {
        return uid;
    }

    public String getImg() {
        return img;
    }

    public String getTitle() {
        return title;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getAuthor() {
        return author;
    }

    public String getHref() {
        return href;
    }

    public String getAvtar() {
        return avtar;
    }

    public String getId() {
        return id;
    }

    public String getChannel() {
        return channel;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.img);
        dest.writeString(this.title);
        dest.writeString(this.created_at);
        dest.writeString(this.author);
        dest.writeString(this.href);
        dest.writeString(this.avtar);
        dest.writeString(this.id);
        dest.writeString(this.channel);
    }

    public Movie() {
    }

    protected Movie(Parcel in) {
        this.uid = in.readString();
        this.img = in.readString();
        this.title = in.readString();
        this.created_at = in.readString();
        this.author = in.readString();
        this.href = in.readString();
        this.avtar = in.readString();
        this.id = in.readString();
        this.channel = in.readString();
    }

    public static final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
