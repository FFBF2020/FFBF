package com.example.ffbf;

import android.os.Parcel;
import android.os.Parcelable;

public class RestAndStrFood  implements Parcelable {

    String name, address1, address2, description, vegType, url, placeType;



    // Constructor for writing
    public RestAndStrFood(String name, String address1, String description, String vegType, String url, String placeType) {
        this.name = name;
        this.address1 = address1;
        this.description = description;
        this.vegType = vegType;
        this.url = url;
        this.placeType = placeType;

    }
    //Empty Constructor for reading
    public RestAndStrFood() {
    }


    protected RestAndStrFood(Parcel in) {
        name = in.readString();
        address1 = in.readString();
        address2 = in.readString();
        description = in.readString();
        vegType = in.readString();
        url = in.readString();
        placeType = in.readString();
    }

    public static final Creator<RestAndStrFood> CREATOR = new Creator<RestAndStrFood>() {
        @Override
        public RestAndStrFood createFromParcel(Parcel in) {
            return new RestAndStrFood(in);
        }

        @Override
        public RestAndStrFood[] newArray(int size) {
            return new RestAndStrFood[size];
        }
    };

    public String getPlaceType() {
        return placeType;
    }

    public void setPlaceType(String placeType) {
        this.placeType = placeType;
    }
    public String getUrl(){
        return url;
    }

    public void setUrl() {
        this.url = url;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVegTypeType() {
        return vegType;
    }

    public void setVegType(String type) {
        this.vegType = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(address1);
        dest.writeString(address2);
        dest.writeString(description);
        dest.writeString(vegType);
        dest.writeString(url);
        dest.writeString(placeType);
    }
}
