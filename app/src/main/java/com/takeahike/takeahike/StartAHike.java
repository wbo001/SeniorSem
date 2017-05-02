package com.takeahike.takeahike;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by wesle on 3/19/2017.
 */

public class StartAHike extends Fragment{

    Button startHike;
    TextView mTextView;

    @Nullable
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        startHike = (Button) view.findViewById(R.id.StartAHikeButton);
        mTextView = (TextView) view.findViewById(R.id.startHikeTextView);

        getActivity().setTitle("Start A Hike");


        startHike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                RequestQueue queue = Volley.newRequestQueue(getActivity().getApplicationContext());
                String url ="https://api.smsapi.com/sms.do?username=bigwilly&password=56caf899950018b65c8b42daaaf95e75&to=15402724723&message=TestMessage";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // Display the first 500 characters of the response string.
                                mTextView.setText("Response is: "+ response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mTextView.setText("That didn't work! :" + error);
                    }
                });
                // Add the request to the RequestQueue.
                queue.add(stringRequest);
            }
        });

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.startahike, container, false);
    }
}
