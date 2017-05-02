package com.takeahike.takeahike;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Hike_Started extends AppCompatActivity {

    TextView startedHikeTV;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike__started);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();

        startedHikeTV = (TextView) findViewById(R.id.startedHikeTextview);

        message = intent.getStringExtra("TRAIL") + "\n" + intent.getStringExtra("TIME") + "\n" +
                intent.getStringExtra("PHONE") + "\n" + intent.getStringExtra("MESSAGEID");

        startedHikeTV.setText(message);

    }

}
