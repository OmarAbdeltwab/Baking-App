package com.example.android.packing.app.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.packing.app.Data.Recipes;
import com.example.android.packing.app.R;
import com.squareup.picasso.Picasso;



/**
 * Created by Omar on 28-Mar-18.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private final Recipes recipes;
    public RecipeAdapter( Recipes recipes ,RecipeOnCickListner recipeOnCickListner) {

        this.recipes=recipes;
this.recipeOnCickListner=recipeOnCickListner;

    }
public interface RecipeOnCickListner {void RecipeOnClick(int pos);}
    private final RecipeOnCickListner recipeOnCickListner;
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecipeViewHolder( LayoutInflater.from( parent.getContext()).inflate( R.layout.recipe,parent,false ) );
    }


    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {

        holder.RecipeName_TV.setText( recipes.getResults()[ position ].getName() );
        String imagepath=recipes.getResults()[ position ].getImage();

      if (imagepath.length()!=0&&imagepath!=null)
       Picasso.with(holder.RecipeThumb_IV.getContext())
              .load(recipes.getResults()[ position ].getImage());

        else holder.RecipeThumb_IV.setImageResource( R.drawable.no_image_available );
    }

    @Override
    public int getItemCount() {
        if (recipes!=null)
            return recipes.getResults().length;
        else return 0;
    }

    class RecipeViewHolder
            extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        final TextView RecipeName_TV ; final ImageView RecipeThumb_IV;
        public RecipeViewHolder(View itemView) {
            super( itemView );
itemView.setOnClickListener( this );
            RecipeName_TV = itemView.findViewById( R.id.tv_recipe_name );
            RecipeThumb_IV =itemView.findViewById( R.id.iv_thumb );
        }


        @Override
        public void onClick(View v) {
            recipeOnCickListner.RecipeOnClick( getAdapterPosition() );
        }
    }
}
