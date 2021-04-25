/*
This is the code for the recipe activity.
The recipe activity is what comes up when you click on one of the card views.

It displays information that you can't see in the main activity such as the calories,
recipe on how to make the food, and (if applicable) a link on where the information came from.
 */

package com.example.android.materialme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class DessertRecipes extends AppCompatActivity {

    private TextView currTitleText;
    private TextView currRecipe;
    private TextView currDescription;
    private TextView currCalories;
    private ImageView currImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dessert_recipes);
        currTitleText = (TextView)findViewById(R.id.recipeTitle);
        currImage = (ImageView)findViewById(R.id.currImage);
        currDescription = (TextView)findViewById(R.id.currDescription);
        currCalories = (TextView)findViewById(R.id.currCalories);
        currRecipe = (TextView)findViewById(R.id.currRecipe);

        //Getting everything from the main activity to display the recipe
        currTitleText.setText(getIntent().getStringExtra("title")); //getting and displaying the title
        currRecipe.setText(getIntent().getStringExtra("recipe")); //getting and displaying the recipe
        currDescription.setText(getIntent().getStringExtra("description")); //getting and displaying description
        currCalories.setText(getIntent().getStringExtra("calories"));
        Glide.with(this).load(getIntent().getIntExtra("image",0)).into(currImage); //getting and displaying the image

    }

    //Code for the back button at the end of the recipe activity
    public void backButtonFunction(View view){
        Intent backButtonIntent = new Intent(this, MainActivity.class);
        startActivity(backButtonIntent);
    }
}