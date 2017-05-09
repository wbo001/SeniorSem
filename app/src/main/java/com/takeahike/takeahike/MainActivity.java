package com.takeahike.takeahike;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    SharedPreferences appInPro;
    SharedPreferences.Editor edit;
    String appInProgress;
    String messageID, phoneNumber, name, trailSelected, timeSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appInPro = this.getSharedPreferences(getString(R.string.shared_pref_key), this.MODE_PRIVATE);
        appInProgress = appInPro.getString("Is_hike_in_pro", "False");

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, new HomePage()).commit();
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        checkForHike();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment frag = null;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        switch(id){
            case R.id.nav_home:
                frag = new HomePage();
                ft.replace(R.id.content_main, frag);
                ft.commit();
                break;
            case R.id.nav_info:
                frag = new InfoPage();
                ft.replace(R.id.content_main, frag);
                ft.commit();
                break;
            case R.id.nav_trials:
                frag = new TrailsPage();
                ft.replace(R.id.content_main, frag);
                ft.commit();
                break;
            case R.id.nav_start:
                frag = new StartAHike();
                ft.replace(R.id.content_main, frag);
                ft.commit();
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void checkForHike(){

        if(appInProgress.equalsIgnoreCase("True")){

            messageID = appInPro.getString("MESSAGEID", "000000000");
            phoneNumber = appInPro.getString("PHONE", "12345678901");
            trailSelected = appInPro.getString("TRAIL", "Trail1");
            timeSelected = appInPro.getString("TIME", "0");

            Intent hikeStarted = new Intent(this, Hike_Started.class);
            hikeStarted.putExtra("TRAIL", trailSelected);
            hikeStarted.putExtra("MESSAGEID", messageID);
            hikeStarted.putExtra("PHONE", phoneNumber);
            hikeStarted.putExtra("TIME", timeSelected);
            hikeStarted.putExtra("NAME", name);
            startActivity(hikeStarted);
        }

    }
}
