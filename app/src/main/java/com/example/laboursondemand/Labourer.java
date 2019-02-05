package com.example.laboursondemand;

public class Labourer {
    private String uid;
    private String name;
    private float rating;
    private int wage;
    private float distance;
    private String profile;

    public Labourer() {
    }

    public Labourer(String uid, String name, float rating, int wage, float distance, String profile) {
        this.uid = uid;
        this.name = name;
        this.rating = rating;
        this.wage = wage;
        this.distance = distance;
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public int getWage() {
        return wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "Labourer{" +
                "name='" + name + '\'' +
                ", rating=" + rating +
                ", wage=" + wage +
                ", distance=" + distance +
                '}';
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
