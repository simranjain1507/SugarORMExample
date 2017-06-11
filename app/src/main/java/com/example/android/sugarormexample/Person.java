package com.example.android.sugarormexample;

import com.orm.SugarRecord;

/**
 * Created by simranjain1507 on 11/06/17.
 */

public class Person extends SugarRecord {

    String name, email,gender,area;
    String phone;
    long id;

    public Person(){

    }

    public Person(String name, String emial, String phone, String area){
        this.name=name;
        this.email=emial;
        this.gender=gender;
        this.phone=phone;
        this.area=area;
    }
    public Person(int id,String name, String emial, String phone, String area){
        this.name=name;
        this.email=emial;
        this.id=id;
        this.gender=gender;
        this.phone=phone;
        this.area=area;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}

