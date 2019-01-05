package com.example.taehyungkim.week2;

public class list_item {

    private int profile_image;
    private String name;
    private String phonenum;
    private String email;
    private String job;
    private String country;
    private String gender;
    private String bloodgroup;
    private String educaion;
    private String birthdate;

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public String getEducaion() {
        return educaion;
    }

    public void setEducaion(String educaion) {
        this.educaion = educaion;
    }

    public int getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(int profile_image) {
        this.profile_image = profile_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public list_item(int profile_image, String name, String phonenum, String email, String job, String country, String gender, String bloodgroup, String educaion, String birthdate) {
        this.profile_image = profile_image;
        this.name = name;
        this.phonenum = phonenum;
        this.email = email;
        this.job = job;
        this.country = country;
        this.gender = gender;
        this.bloodgroup = bloodgroup;
        this.educaion = educaion;
        this.birthdate = birthdate;
    }

}
