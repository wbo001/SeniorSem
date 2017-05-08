package com.takeahike.takeahike;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by wesle on 3/19/2017.
 */

public class TrailsPage extends Fragment{

    private static final String TAG = "**MILEAGE**";
    FirebaseDatabase database;
    DatabaseReference trailRef;
    ListView trailList;
    List<Trail> trails;
    View v;
    boolean connected = false;
    TrailList adapter;

    @Nullable
    @Override
    /*public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Hiking Trails");



        //pullData();


    }*/

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.trails, container, false);
        getActivity().setTitle("Hiking Trails");

        connected = false;
        trailList = (ListView) v.findViewById(R.id.trailPageListView);

        trails = new ArrayList<>();
        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        connected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

            database = FirebaseDatabase.getInstance();

            trailRef = database.getReference("Trails");

            trailRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    trails.clear();
                    if(connected == true) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {

                        Trail t = ds.getValue(Trail.class);

                        trails.add(t);
                        LocalDB.openDB(getContext());
                        LocalDB.addTrail(t);
                        LocalDB.closeDB();


                        /*tName.add(t.getName());
                        tDescript.add(t.getDescription());
                        tDiff.add(t.getDifficulty());
                        tMile.add(t.getMileage());*/
                    }

                        adapter = new TrailList(getActivity(), trails);
                        trailList.setAdapter(adapter);
                    }

                    else if(connected == false)
                    {
                        LocalDB.openDB(getContext());
                        trails = LocalDB.getAllTrails(getContext());
                        LocalDB.closeDB();

                        adapter = new TrailList(getActivity(), trails);
                        trailList.setAdapter(adapter);
                        adapter.notifyDataSetChanged();

                    }
                    /*editor.putStringSet("Name", tName);
                    editor.putStringSet("Description", tDescript);
                    editor.putStringSet("Difficulty", tDiff);
                    editor.putStringSet("Mileage", tMile);
                    editor.commit();*/


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });




        return v;
    }

    public void onResume()
    {
        super.onResume();
        trailList.setAdapter(adapter);
    }
}
