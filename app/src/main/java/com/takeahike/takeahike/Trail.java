package com.takeahike.takeahike;


/**
 * Created by wesle on 4/17/2017.
 */

public class Trail {

    private String Mileage;
    private String Name;
    private String Description;
    private String Difficulty;

    public Trail(){

    }



    public Trail(String name, String description, String difficulty, String mileage) {
        this.Name = name;
        this.Description = description;
        this.Mileage = mileage;
        this.Difficulty = difficulty;

    }

    public String getMileage() {
        return Mileage;
    }

    public void setMileage(String mileage){
        this.Mileage = mileage;
    }

    public void setName(String name) {
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
