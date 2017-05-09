package com.takeahike.takeahike;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

public class Hike_Started extends AppCompatActivity {

    TextView startedHikeTimer;
    String message, url, messageID, phoneNumber, name, trailSelected, time;
    Button stopHike;
    int time1;
    //Button checkURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike__started);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle("Hike Started");

        //checkURL = (Button) findViewById(R.id.checkURLButton);
        stopHike = (Button) findViewById(R.id.stopHikeButton);

        startedHikeTimer = (TextView) findViewById(R.id.textView4);

        Intent intent = getIntent();

        messageID = intent.getStringExtra("MESSAGEID");
        name = intent.getStringExtra("NAME");
        phoneNumber = intent.getStringExtra("PHONE");
        trailSelected = intent.getStringExtra("TRAIL");
        time = intent.getStringExtra("TIME");
        time1 = Integer.valueOf(time);


        /*checkURL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                url = createURL(url);
                Toast.makeText(getApplication(), url, Toast.LENGTH_LONG).show();
            }
        });*/

        new CountDownTimer(time1 * 3600000, 1000) {

            public void onTick(long millisUntilFinished) {

                long hour = (millisUntilFinished / 3600000);
                long minute = (millisUntilFinished / 60000) - (60 * hour);
                long second = ((millisUntilFinished / 1000) - (3600 * hour)) - (60 * minute);

                startedHikeTimer.setText(" " + hour + ":" + minute + ":" + second);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                startedHikeTimer.setText("End Hike!");
            }

        }.start();

        stopHike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Code for sending message to server

               /* RequestQueue queue = Volley.newRequestQueue(getApplication().getApplicationContext());
                url = createURL(url);

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                //mTextView.setText("Response is: "+ response);
                                messageID = response;
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("That didn't work! :" + error);
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);*/

                endHike();
            }
        });
    }
    public void onBackPressed(){

    }
    public String createURL(String url){

        url = "https://api.smsapi.com/sms.do?username=bigwilly&password=56caf899950018b65c8b42daaaf95e75&from=TakeAHike&to=" + phoneNumber + "&message=" + name + "has returned safely from" + trailSelected;


        return url;
    }
    public void endHike(){

        Intent endHike = new Intent(getApplicationContext(), MainActivity.class);
        endHike.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(endHike);
        finish();

    }

}
