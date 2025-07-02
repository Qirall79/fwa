package fr.fortytwo.cinema.models;

import java.sql.Timestamp;

public class Authentication {
    private long id;
    private Timestamp timestamp;
    private String ip;
    private long userId;

    public Authentication(long id, Timestamp timestamp, String ip, long userId) {
        this.id = id;
        this.timestamp = timestamp;
        this.ip = ip;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }
    
    public long getUserId() {
        return userId;
    }

    public String getIp() {
        return ip;
    }

    public String getTime() {
        return timestamp.getHours() + ":" + timestamp.getMinutes() ;
    }
    
    public String getDate() {
        return timestamp.getMonth() + ":" + timestamp.getDay() + ":" + timestamp.getYear();
    }

    @Override
    public String toString() {
        return id + " " + getDate() + " " + getTime() + " " + ip;
    }

    
}
