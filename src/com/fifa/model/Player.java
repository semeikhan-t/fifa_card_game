package com.fifa.model;

public class Player extends Entity {
    private String name;
    private int countryId;
    private String position;
    private int overall;
    private boolean isStarter;
    private String photoPath;

    public Player() {}

    public Player(String name, int countryId, String position, int overall, boolean isStarter, String photoPath) {
        this.name = name;
        this.countryId = countryId;
        this.position = position;
        this.overall = overall;
        this.isStarter = isStarter;
        this.photoPath = photoPath;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCountryId() { return countryId; }
    public void setCountryId(int countryId) { this.countryId = countryId; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public int getOverall() { return overall; }
    public void setOverall(int overall) { this.overall = overall; }

    public boolean isStarter() { return isStarter; }
    public void setStarter(boolean starter) { isStarter = starter; }

    public String getPhotoPath() { return photoPath; }
    public void setPhotoPath(String photoPath) { this.photoPath = photoPath; }

    @Override
    public String toString() {
        return name + " (" + position + ") - " + overall;
    }
}
