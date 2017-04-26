package com.takeahike.takeahike;


/**
 * Created by wesle on 4/17/2017.
 */

public class Trail {

    private String Distance;
    private String Name;
    private String Description;
    private String Difficulty;

    public Trail(){

    }



    public Trail(String name, String description, String difficulty, String milage) {
        this.Name = name;
        this.Description = description;
        this.Distance = milage;
        this.Difficulty = difficulty;

    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
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
