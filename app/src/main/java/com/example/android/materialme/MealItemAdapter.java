/*
This is the adapter. The adapter is what binds the information to the viewholder and sends
the data to each viewHolder
*/

package com.example.android.materialme;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

/***
 * The adapter class for the RecyclerView, contains the dessert data.
 */
class MealItemAdapter extends RecyclerView.Adapter<MealItemAdapter.ViewHolder>  {

    // Member variables.
    private ArrayList<MealItem> dessertData;
    private Context mContext;


    /**
     * Constructor that passes in the data and the context.
     *
     * dessertData - ArrayList containing the dessert data.
     * context - Context of the application.
     */
    MealItemAdapter(Context context, ArrayList<MealItem> dessertData) {
        this.dessertData = dessertData;
        this.mContext = context;
    }


    /**
     * Required method for creating the viewholder objects.
     *
     * parent - The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * viewType - The view type of the new View.
     * returns the new created ViewHolder
     */
    @Override
    public MealItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * holder - The viewholder into which the data should be put.
     * position - The adapter position.
     */
    @Override
    public void onBindViewHolder(final MealItemAdapter.ViewHolder holder, int position) {
        // Get current sport.
        final MealItem currentDessert = dessertData.get(position);


        // Populate the TextViews with data.
        holder.bindTo(currentDessert);

        //Long click listener code
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) { //Long pressing one of the dessert items
                new AlertDialog.Builder(mContext)
                        .setTitle("Removing Item...")
                        .setMessage("Are you sure you want to remove this item?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) { //This is what happens if the user says yes to deleting the item
                                MealItemAdapter mAdapter = new MealItemAdapter(mContext, dessertData);

                                dessertData.remove(holder.getAdapterPosition());
                                mAdapter.notifyItemRemoved(holder.getAdapterPosition());
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() { //If the user says no, the dialog box just deletes itself
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();

                return true;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //information being sent to DessertRecipes.class
                Intent recipeIntent = new Intent(mContext, DessertRecipes.class);

                String currDessertTitle = currentDessert.getTitle();
                String currDessertRecipe = currentDessert.getRecipe();
                String currDessertDescription = currentDessert.getInfo();
                String currentDessertCalories = currentDessert.getCalories();
                int currDessertImage = currentDessert.getImage();

                recipeIntent.putExtra("title", currDessertTitle);
                recipeIntent.putExtra("image", currDessertImage);
                recipeIntent.putExtra("description", currDessertDescription);
                recipeIntent.putExtra("recipe", currDessertRecipe);
                recipeIntent.putExtra("calories", currentDessertCalories);

                mContext.startActivity(recipeIntent);

            }
        });
    }

    //Gets the size of the data
    @Override
    public int getItemCount() {

        return dessertData.size();
    }


    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView dessertImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            dessertImage = itemView.findViewById(R.id.dessertImage);
        }

        void bindTo(MealItem currentDessert){
            // Populate the textviews with data.
            mTitleText.setText(currentDessert.getTitle());
            mInfoText.setText(currentDessert.getInfo());
            Glide.with(mContext).load(currentDessert.getImage()).into(dessertImage);
        }
    }
}
