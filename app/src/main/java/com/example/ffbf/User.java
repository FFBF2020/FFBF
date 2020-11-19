package com.example.ffbf;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {

 private String fn, sn, mail, password, type;

   // Constructor for writing data from database
    public User(String fn, String sn, String mail, String password, String type) {
        this.fn = fn;
        this.sn = sn;
        this.mail = mail;
        this.password = password;
        this.type = type;
    }

    //Empty constructor for reading data from database
    public User() {
    }

    protected User(Parcel in) {
        fn = in.readString();
        sn = in.readString();
        mail = in.readString();
        password = in.readString();
        type = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {return type;}

    public void setType(String type){this.type = type;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fn);
        dest.writeString(sn);
        dest.writeString(mail);
        dest.writeString(password);
        dest.writeString(type);
    }
}
