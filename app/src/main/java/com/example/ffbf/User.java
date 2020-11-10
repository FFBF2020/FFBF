package com.example.ffbf;

public class User {

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
}
