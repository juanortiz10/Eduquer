package com.example.juan.eduquer;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;
import adapter.DrawerAdapter;
import fragments.Add;
import fragments.Home;
import fragments.Look;
import fragments.Remove;
import models.Item;

public class MainActivity extends FragmentActivity {

    private ListView drawerList;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private String actitle;
    private String[] tagTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerList = (ListView)findViewById(R.id.nav);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        actitle = getTitle().toString();

        tagTitles= getResources().getStringArray(R.array.tags);
        addDrawerItems();
        setupDrawer();

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
    }

    private void addDrawerItems() {
        ArrayList<Item> items = new ArrayList<>();
        items.add(new Item(tagTitles[0], R.drawable.home));
        items.add(new Item(tagTitles[1], R.drawable.add));
        items.add(new Item(tagTitles[2], R.drawable.remove));
        items.add(new Item(tagTitles[3], R.drawable.look));

        drawerList.setAdapter(new DrawerAdapter(this, items));
        ShowFragment(0);
        drawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ShowFragment(i);
            }
        });
    }

    private void ShowFragment(int position){
        android.support.v4.app.Fragment fragment= null;

        switch (position){
            case 0:
                fragment= new Home();
                break;
            case 1:
                fragment= new Add();
                break;
            case 2:
                fragment= new Remove();
                break;
            case 3:
                fragment= new Look();
                break;

            default:
                Toast.makeText(getApplicationContext(), "No disponible",Toast.LENGTH_SHORT).show();
                fragment = new Home();
                position =1;
                break;
        }

        if (fragment != null) {
            android.support.v4.app.FragmentTransaction t= getSupportFragmentManager().beginTransaction();
            t.replace(R.id.content_frame,fragment);
            t.commit();

            drawerList.setItemChecked(position, true);
            drawerList.setSelection(position);
            setTitle(tagTitles[position]);
            drawerLayout.closeDrawer(drawerList);
        }else{
            Log.e("Error  ", "MostrarFragment" + position);
        }
    }

    private void setupDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.drawer_open, R.string.drawer_close) {

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

            }
        };

        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerLayout.setDrawerListener(drawerToggle);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        // Activate the navigation drawer toggle
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}