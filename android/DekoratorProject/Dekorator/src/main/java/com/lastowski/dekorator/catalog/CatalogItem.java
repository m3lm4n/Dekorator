package com.lastowski.dekorator.catalog;

import android.os.Parcel;
import android.os.Parcelable;

import com.lastowski.dekorator.Utils;

import java.util.Calendar;

/**
 * Created by adamastowski on 29.12.2013.
 */
public class CatalogItem implements Parcelable {
    int id;
    String name;
    String image;

    public CatalogItem(){

    }

    public CatalogItem(int id, String name, String image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }

    public CatalogItem(Parcel in){
        id = in.readInt();
        name = in.readString();
        image = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(image);

    }

    public static final Parcelable.Creator<CatalogItem> CREATOR
            = new Parcelable.Creator<CatalogItem>() {
        public CatalogItem createFromParcel(Parcel in) {
            return new CatalogItem(in);
        }

        public CatalogItem[] newArray(int size) {
            return new CatalogItem[size];
        }
    };

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

    public String getImage() {
        return Utils.Const.API_MEDIA_ADDRESS +"/"+ image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
