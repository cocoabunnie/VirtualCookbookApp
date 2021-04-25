/*
This is the MealItem object. Each meal item is represented in each card in the recycler view.
In other words, each MealItem object is represented by a card in the recycler view
 */
package com.example.android.materialme;

import android.os.Parcel;
import android.os.Parcelable;


class MealItem implements Parcelable{

    // Member variables representing the title and information about the sport.
    private String title; //title of each dessert item
    private String info; //info for each dessert item
    private String recipe;
    private int IMAGE; //image for each dessert item
    private String calories; //calories

    MealItem(String title, String info, int IMAGE, String recipe, String calories) {
        this.title = title;
        this.info = info;
        this.IMAGE = IMAGE;
        this.recipe = recipe;
        this.calories = calories;
    }

    private MealItem(Parcel in) {
        title = in.readString();
        info = in.readString();
        IMAGE = in.readInt();
        recipe = in.readString();
        calories = in.readString();
    }

    //GETTERS
    String getTitle() {
        return title;
    }

    String getInfo() {
        return info;
    }

    public int getImage(){
        return IMAGE;
    }

    public String getRecipe(){
        return recipe;
    }

    public String getCalories(){ return calories;}

    //SETTERS
    public void setTitle(String n){
        this.title = n;
    }

    public void setInfo(String n){
        this.info = n;
    }

    public void setRecipe(String n){
        this.recipe = n;
    }

    public void setImage(int n){
        this.IMAGE = n;
    }

    public void setIMAGE(String n){ this.calories = n;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(IMAGE);
        parcel.writeString("title");
        parcel.writeString("info");
        parcel.writeString("recipe");
        parcel.writeString("calories");
    }

    public static final Parcelable.Creator<MealItem> CREATOR = new Parcelable.Creator<MealItem>() {
        public MealItem createFromParcel(Parcel in) {
            return new MealItem(in);
        }

        public MealItem[] newArray(int size) {
            return new MealItem[size];
        }
    };
}