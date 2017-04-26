package com.takeahike.takeahike;


/**
 * Created by wesle on 4/17/2017.
 */

public class Trail {

    private long Distance;
    private String Name;
    private String Description;
    private String Difficulty;

    public Trail(){

    }



    public Trail(String name, String description, String difficulty, long distance) {
        this.Name = name;
        this.Description = description;
        this.Distance = distance;
        this.Difficulty = difficulty;

    }

    public long getDistance() {
        return Distance;
    }

    public void setDistance(long distance) {
        Distance = distance;
    }

    public void SetName(String name) {
        this.Name = name;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public void setDifficulty(String difficulty) {
        this.Difficulty = difficulty;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public String getDifficulty() {
        return Difficulty;
    }
}
