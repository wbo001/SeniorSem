package com.takeahike.takeahike;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by wesle on 3/19/2017.
 */

public class StartAHike extends Fragment{

    Button startHike;
    TextView mTextView;
    String messageID, name, trailSelected, phoneNumber, timeSelected, hikeInPro;
    Spinner trailDropdown, timeDropdown;
    EditText phoneNumberEdit, hikerNameEdit;
    long timeHikeStarted;
    boolean isConnected;
    SharedPreferences appInPro;
    SharedPreferences.Editor edit;


    @Nullable
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        phoneNumberEdit = (EditText) view.findViewById(R.id.phoneNumber);
        hikerNameEdit = (EditText) view.findViewById(R.id.hikerName);
        phoneNumberEdit.setHint("Phone Number");
        hikerNameEdit.setHint("Your Name");

        startHike = (Button) view.findViewById(R.id.StartAHikeButton);

        appInPro = getContext().getSharedPreferences(getString(R.string.shared_pref_key), getContext().MODE_PRIVATE);

        hikeInPro = appInPro.getString("Is_hike_in_pro", "False");

        LocalDB.openDB(getActivity());
        String[] trailNames = LocalDB.getAllTrailsName(getActivity());
        LocalDB.closeDB();

        ArrayAdapter<String> trailAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, trailNames);
        trailDropdown = (Spinner)view.findViewById(R.id.trailSpinner);

        trailDropdown.setAdapter(trailAdapter);

        timeDropdown = (Spinner)view.findViewById(R.id.trailTime);
        String[] times = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, times);
        timeDropdown.setAdapter(timeAdapter);

        getActivity().setTitle("Start A Hike");
        createOnClick();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            return inflater.inflate(R.layout.startahike, container, false);

    }
    public void createOnClick(){
        //Trail dropdown listener
        trailDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                trailSelected = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        timeDropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                timeSelected = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //onclick for start hike button
        startHike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

                String url = null;
                name = hikerNameEdit.getText().toString().trim();
                phoneNumber = phoneNumberEdit.getText().toString().trim();

                if(hikeInPro.equalsIgnoreCase("False")) {

                    ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
                    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                    isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                    if ((name.length() != 0) && (phoneNumber.length() == 11)) {
                        if (isConnected) {
                            //Code for sending message to server
                        RequestQueue queue = Volley.newRequestQueue(getContext());
                        url = "https://api.smsapi.com/sms.do?username=bigblackafrothunder@gmail.com&password=1871a3c1da602bf471d3d76cc60cdb9b&to=" + phoneNumber + "&message=" + name + ".";


                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        // Display the first 500 characters of the response string.
                                        messageID = response;
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                //mTextView.setText("That didn't work! :" + error);
                            }
                        });
                        // Add the request to the RequestQueue.
                        queue.add(stringRequest);

                            //Starting the hike started activity
                            Intent hikeStarted = new Intent(getActivity(), Hike_Started.class);
                            timeHikeStarted = System.currentTimeMillis();
                            hikeStarted.putExtra("TIME_HIKE_STARTED", timeHikeStarted);
                            hikeStarted.putExtra("TRAIL", trailSelected);
                            hikeStarted.putExtra("MESSAGEID", messageID);
                            hikeStarted.putExtra("PHONE", phoneNumber);
                            hikeStarted.putExtra("TIME", timeSelected);
                            hikeStarted.putExtra("NAME", name);
                            startActivity(hikeStarted);
                        } else {
                            Toast.makeText(getContext(), "No Connection", Toast.LENGTH_LONG).show();
                        }
                    } else {

                        Toast.makeText(getContext(), "Phone number must be 11 digits and Name is required", Toast.LENGTH_LONG).show();

                    }
                }
                else{
                    Toast.makeText(getContext(), "Hike already in progress", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}
