package com.example.ffbf;

public class Stalls {

    String name, address1, address2, description, vegType, url;

 // Constructor for writing
    public Stalls(String name, String address1, String address2, String description, String vegType,String url) {
        this.name = name;
        this.address1 = address1;
        this.address2 = address2;
        this.description = description;
        this.vegType = vegType;
        this.url = url;

    }
    //Empty Constructor for reading
    public Stalls() {
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

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
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
}
