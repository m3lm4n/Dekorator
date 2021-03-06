package com.lastowski.dekorator.reservations;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.lastowski.dekorator.item.SpecificItem;

/**
 * Created by adamastowski on 01.01.2014.
 */
public class ReservationItem implements Parcelable{

    @Expose(serialize = false)
    int id;

    @Expose(serialize = true, deserialize = true)
    String name;

    @Expose(serialize = true, deserialize = true)
    String date;

    @Expose(serialize = false, deserialize = false)
    SpecificItem item;

    public SpecificItem getItem() {
        return item;
    }

    public void setItem(SpecificItem item) {
        this.item = item;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ReservationItem(){
    }

    public ReservationItem(int id, String name, String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public ReservationItem(Parcel in){
        id = in.readInt();
        name = in.readString();
        date = in.readString();
        item = in.readParcelable(SpecificItem.class.getClassLoader());
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(date);
        dest.writeParcelable(item, flags);
    }

    public static final Parcelable.Creator<ReservationItem> CREATOR
            = new Parcelable.Creator<ReservationItem>() {
        public ReservationItem createFromParcel(Parcel in) {
            return new ReservationItem(in);
        }

        public ReservationItem[] newArray(int size) {
            return new ReservationItem[size];
        }
    };
}
