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

<<<<<<< HEAD
    public void setMileage(double Mileage) {
        Mileage = Mileage;
=======
    public void setMileage(long distance) {
        Distance = distance;
>>>>>>> 02595325ad4b871f3d061914322bdc130bc8e42f
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
