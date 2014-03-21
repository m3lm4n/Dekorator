package com.lastowski.dekorator.item;

import android.os.Parcel;
import android.os.Parcelable;

import com.lastowski.dekorator.Utils;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class SpecificItem implements Parcelable {
    int id;
    String name;
    String color;
    String size;
    String image;
    boolean away;

    public SpecificItem(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImage() {
        return Utils.Const.API_MEDIA_ADDRESS +"/"+ image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isAway() {
        return away;
    }

    public void setAway(boolean away) {
        this.away = away;
    }

    public SpecificItem(Parcel in){
        id = in.readInt();
        name = in.readString();
        color = in.readString();
        size = in.readString();
        image = in.readString();
        away = in.readInt() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(color);
        dest.writeString(size);
        dest.writeString(image);
        dest.writeInt(away ? 1 : 0);
    }

    public static final Parcelable.Creator<SpecificItem> CREATOR
            = new Parcelable.Creator<SpecificItem>() {
        public SpecificItem createFromParcel(Parcel in) {
            return new SpecificItem(in);
        }

        public SpecificItem[] newArray(int size) {
            return new SpecificItem[size];
        }
    };


}
