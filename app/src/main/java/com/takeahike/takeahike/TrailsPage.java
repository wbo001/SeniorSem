package com.takeahike.takeahike;

import android.content.Context;
import android.content.SharedPreferences;
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


    @Nullable
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.v = view;
        getActivity().setTitle("Hiking Trails");

        trailList = (ListView) v.findViewById(R.id.trailPageListView);

        trails = new ArrayList<>();

        //pullData();


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.trails, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();

        final SharedPreferences.Editor editor = getContext().getSharedPreferences("trailsInfo", Context.MODE_PRIVATE).edit();
        final Set<String> tName = new HashSet<String>();
        final Set<String> tDescript = new HashSet<String>();
        final Set<String> tDiff = new HashSet<String>();
        final Set<String> tMile = new HashSet<>();

        database = FirebaseDatabase.getInstance();
        trailRef = database.getReference("Trails");

        trailRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                trails.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    Trail t = ds.getValue(Trail.class);

                    trails.add(t);


                    tName.add(t.getName());
                    tDescript.add(t.getDescription());
                    tDiff.add(t.getDifficulty());
                    tMile.add(t.getMileage());
                }

                TrailList adapter = new TrailList(getActivity(), trails);
                trailList.setAdapter(adapter);
                editor.putStringSet("Name", tName);
                editor.putStringSet("Description", tDescript);
                editor.putStringSet("Difficulty", tDiff);
                editor.putStringSet("Mileage", tMile);
                editor.commit();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /*private void pullData(){

        database = FirebaseDatabase.getInstance();
        trailRef = database.getReference("Trails");

        trailRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds : dataSnapshot.getChildren()){

                    Trail t = ds.getValue(Trail.class);

                    System.out.println("**************" + t.getName());
                    System.out.println("**************" + t.getDescription());
                    System.out.println("**************" + t.getDifficulty());
                    trails.add(t);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }*/

}
