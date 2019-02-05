package com.example.laboursondemand;

public class Labour {
    private String uid;
    private String feedback;
    private float rating;

    public Labour(String uid, String feedback, float rating) {
        this.uid = uid;
        this.feedback = feedback;
        this.rating = rating;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Labour{" +
                "uid='" + uid + '\'' +
                ", feedback='" + feedback + '\'' +
                ", rating=" + rating +
                '}';
    }
}
