/*
This is the activity that is called when the user wants to add their own
recipe to the list. At this point, they can only customize the title,
recipe, calorie count, and adding a short description.
 */

package com.example.android.materialme;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class AddItemActivity extends AppCompatActivity {
    private EditText name;
    private EditText description;
    private EditText recipe;
    private EditText calories;
    private ImageView image;
    private EditText link;
    private static final int IMAGECHOSEN = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        name = (EditText)findViewById(R.id.inputName);
        description = (EditText)findViewById(R.id.inputDescription);
        recipe = (EditText)findViewById(R.id.inputRecipe);
        calories = (EditText)findViewById(R.id.inputCalories);
    }

    //Submit button code
    public void submitButtonCode(View view){
        //Sending the information the user inputted to the main activity so it can be created into a new recipe

        Intent goToMain = new Intent(this, MainActivity.class);
        goToMain.putExtra("newName",name.getText().toString());
        goToMain.putExtra("newInfo",description.getText().toString());
        goToMain.putExtra("newRecipe",recipe.getText().toString());
        goToMain.putExtra("newCalories", calories.getText().toString() + " calories");

        setResult(RESULT_OK, goToMain);
        finish();
    }

    //Cancel button code
    public void cancelButtonCode(View view){
        //The cancel button brings the user back to the main intent without changing anything
        //Basically this is if they've changed their mind about making a new item,
        //as if to say "forget it"

        final Intent backToMain = new Intent(this, MainActivity.class);
        new AlertDialog.Builder(this)
                .setTitle("Cancel Action")
                .setMessage("Are you sure you want to cancel the creation of this new recipe?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { //This is what happens if the user says yes to deleting the item
                        startActivity(backToMain);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() { //If the user says no, the dialog box just deletes itself
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).create().show();
    }
}