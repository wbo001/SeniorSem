package com.takeahike.takeahike;

/**
 * Created by wesle on 4/15/2017.
 */

import android.support.annotation.Keep;

@Keep
public class TrailInfo {

    public static String name;
    public static String diff;
    public static String descrip;

    public TrailInfo(){

    }

    public TrailInfo(String Description, String Difficulty, String Name) {
        this.name = Name;
        this.diff = Difficulty;
        this.descrip = Description;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getDifficulty() {
        return diff;
    }

    public void setDifficulty(String diff) {
        this.diff = diff;
    }

    public static String getDescription() {
        return descrip;
    }

    public void setDescription(String descrip) {
        this.descrip = descrip;
    }
}
