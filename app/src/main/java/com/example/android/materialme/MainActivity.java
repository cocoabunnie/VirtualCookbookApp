/*
This app is intended to enable the user to keep a list of recipes so they can easily go back and
reference them when they need to.

This is the first activity that the user sees. It shows their list of recipes in the form of cards on a
recycle view.
 */

package com.example.android.materialme;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView foodieRecyclerView;
    public ArrayList<MealItem> dessertData;
    public final int REQUEST_CODE = 1;
    private MealItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("LIFECYCLE", "create");

        // Initialize the RecyclerView.
        foodieRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        foodieRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Initialize the ArrayList that will contain the data.
        dessertData = new ArrayList<MealItem>();
        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new MealItemAdapter(this, dessertData);
        foodieRecyclerView.setAdapter(mAdapter);


        if(savedInstanceState != null){
            dessertData = savedInstanceState.getParcelableArrayList("dessertList"); //Initial information
            mAdapter = new MealItemAdapter(this, dessertData);
            foodieRecyclerView.setAdapter(mAdapter);
            Log.d("DESSERTDATA_ONCREATE", dessertData.toString());
        } else {
            Log.d("DESSERTDATAONCREATENULL", dessertData.toString());
            initializeData();

        }
    }


    //LIFECYCLE SUPPORT
    @Override
    protected void onSaveInstanceState(Bundle outState) { //ensuring the users recipe data is saved
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("dessertList", dessertData);
        Log.d("DESSERTDATA_ONSAVE", dessertData.toString());
    }


    //Initializing/loading the dessert data
    private void initializeData() {
        TypedArray dessertImageResources = getResources().obtainTypedArray(R.array.dessertImages);

        // Getting the resources from the XML file.
        String[] dessertList = getResources().getStringArray(R.array.dessertTitles);
        String[] dessertInfo = getResources().getStringArray(R.array.dessertDescription);
        String[] dessertRecipes = getResources().getStringArray(R.array.dessertRecipes);
        String[] dessertCalories = getResources().getStringArray(R.array.dessertCalories);

        // Clearing the existing data to avoid duplication
        dessertData.clear();

        //Load initial default items
        for(int i=0;i<dessertList.length;i++){
            dessertData.add(new MealItem(dessertList[i],dessertInfo[i],dessertImageResources.getResourceId(i,0),dessertRecipes[i],dessertCalories[i] + " calories"));
        }

        dessertImageResources.recycle();
        mAdapter.notifyDataSetChanged();
    }

    //Code for the FAB (the + button in the corner of the screen)
    public void addItemButton(View view){
        Intent addItemActivity = new Intent(this, AddItemActivity.class);
        startActivityForResult(addItemActivity, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        TypedArray defaultImage = getResources().obtainTypedArray(R.array.defaultImage);
        String name = "";
        String description = "";
        String recipe = "";
        String calories = "";

        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            name = data.getStringExtra("newName");
            description = data.getStringExtra("newInfo");
            recipe = data.getStringExtra("newRecipe");
            calories = data.getStringExtra("newCalories");
        }

        dessertData.add(new MealItem(name, description, defaultImage.getResourceId(0,0), recipe, calories));
        mAdapter.notifyDataSetChanged();
    }

    //Options menu across top
    //CODE FOR OPTIONS MENU ACROSS THE TOP (SHARE AND INFO)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) { //shows the menu so user can see the two icons
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.usermenu, menu);
        return true;
    }
}
