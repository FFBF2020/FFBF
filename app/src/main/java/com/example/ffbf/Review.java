package com.example.ffbf;

import android.os.Parcel;
import android.os.Parcelable;

public class Review implements Parcelable {

    private String placeName;
   private String userMail;
    private String review;

      //constructor for writing data
    public Review(String placeName, String userMail, String review) {
        this.placeName = placeName;
        this.userMail = userMail;
        this.review = review;
    }
     // empty constructor for reading data
    public Review() {
    }

    protected Review(Parcel in) {
        placeName = in.readString();
        userMail = in.readString();
        review = in.readString();
    }

    public static final Creator<Review> CREATOR = new Creator<Review>() {
        @Override
        public Review createFromParcel(Parcel in) {
            return new Review(in);
        }

        @Override
        public Review[] newArray(int size) {
            return new Review[size];
        }
    };

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(placeName);
        dest.writeString(userMail);
        dest.writeString(review);
    }
}
