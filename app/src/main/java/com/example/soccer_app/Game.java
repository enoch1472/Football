package com.example.soccer_app;

import java.io.Serializable;

public class Game implements Serializable {
    private String name;
    private String date;
    private String time;
    private String location;
    private String host;
    private int currentPlayers;
    private int maxPlayers;
    private String gameType;
    private double latitude;
    private double longitude;

    public Game(String name, String date, String time, String location, String host,
                int currentPlayers, int maxPlayers, String gameType, double latitude, double longitude) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.location = location;
        this.host = host;
        this.currentPlayers = currentPlayers;
        this.maxPlayers = maxPlayers;
        this.gameType = gameType;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getLocation() { return location; }
    public String getHost() { return host; }
    public int getCurrentPlayers() { return currentPlayers; }
    public int getMaxPlayers() { return maxPlayers; }
    public String getGameType() { return gameType; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
