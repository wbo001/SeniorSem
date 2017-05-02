package com.takeahike.takeahike;

import android.provider.BaseColumns;

/**
 * Created by Brady on 5/2/2017.
 */

public class Trails_T implements BaseColumns {
    private Trails_T(){}

    public static final String TABLE_NAME = "trails";

    //Columns

    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String DIFFICULTY = "difficulty";
    public static final String MILEAGE = "mileage";


    /* SQL Statements */
    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ("
                    + _ID + " INTEGER PRIMARY KEY, "
                    + NAME + " TEXT NOT NULL UNIQUE, "
                    + DESCRIPTION + " TEXT NOT NULL, "
                    + DIFFICULTY + " TEXT NOT NULL,"
                    + MILEAGE + " TEXT NOT NULL)";

    public static final String DELETE_TABLE =
            "DELETE TABLE IF EXISTS " + TABLE_NAME;

    public static final String GET_ALL_TRAILS=
            "SELECT *"
                    + " FROM " + TABLE_NAME;
}
