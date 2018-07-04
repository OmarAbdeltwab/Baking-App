package com.example.android.packing.app.UI.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.packing.app.Data.Ingredient;
import com.example.android.packing.app.R;

import java.util.List;




/**
 * Created by Omar on 28-Mar-18.
 */

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private final List<Ingredient> Ingredients;
    public IngredientAdapter(List<Ingredient> Ingredients) {
        this.Ingredients = Ingredients;
    }

    @Override
    public IngredientViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new IngredientViewHolder( LayoutInflater.from( parent.getContext()).inflate( R.layout.ingredient,parent,false ) );
    }


    @Override
    public void onBindViewHolder(IngredientViewHolder holder, int position) {

        Ingredient cur_ingredient = Ingredients.get(position);
        holder.IngredientName.setText( String.valueOf( position +1)+": "+cur_ingredient.getQuantity()+" "+cur_ingredient.getMeasure()+"(s) "+ cur_ingredient.getIngredient() );

    }

    @Override
    public int getItemCount() {
        if (Ingredients !=null)
            return Ingredients.size();
        else return 0;
    }

    class IngredientViewHolder
            extends RecyclerView.ViewHolder
    {

        final TextView IngredientName;
        public IngredientViewHolder(View itemView) {
            super( itemView );

            IngredientName = itemView.findViewById( R.id.tv_ingredient );

        }



    }
}
