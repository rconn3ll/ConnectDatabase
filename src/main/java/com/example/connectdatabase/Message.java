package com.example.connectdatabase;

public class Message {

    private String message;
    private int id;
    private String time;

    //setters and getters
    public void setMessage(String message) {
        this.message = message;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return this.message;
    }

    public int getId() {
        return this.id;
    }

    public String getTime() {
        return this.time;
    }

    public String toString() {
        return "id: "+this.id+", message: "+this.message+", time: "+this.time;
    }
}
