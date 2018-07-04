package com.example.android.packing.app.UI.Widget;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.example.android.packing.app.Data.Ingredient;
import com.example.android.packing.app.Data.Recipe;
import com.example.android.packing.app.Data.Recipes;
import com.example.android.packing.app.Data.Utils;
import com.example.android.packing.app.MainActivity;
import com.example.android.packing.app.R;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Created by Omar on 28-May-18.
 */
class ListRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context mContext;
    private Recipes AllRecipes;
    public static int SelectedRec=0;

    public ListRemoteViewsFactory(Context applicationContext) {
        mContext = applicationContext;

    }

    @Override
    public void onCreate() {
        AllRecipes=new Recipes();
        String Url= mContext.getString( R.string.RecipesUrl);




    }

    @Override
    public void onDataSetChanged() {
        // Get all plant info ordered by creation time
        String Url= mContext.getString( R.string.RecipesUrl);


        Gson gson = new Gson();

        Reader reader = new InputStreamReader( Utils.getResponseStream( Url ));

        AllRecipes.setResults( gson.fromJson(reader,Recipe[].class));


    }

    @Override
    public void onDestroy() {
        //
    }

    @Override
    public int getCount() {

        if (AllRecipes==null) return 0;
        return AllRecipes.getResults().length;
    }

    @Override
    public RemoteViews getViewAt(int position) {
if (AllRecipes==null) return null;

        RemoteViews views = new RemoteViews(mContext.getPackageName(), R.layout.ingerdient_widget_list_item);
        //get selected recipe ingerdientts
        Ingredient temping= AllRecipes.getResults()[SelectedRec].getIngredients().get( position );
        String Ingerdient =String.valueOf( position +1)+": "+ temping.getQuantity()+" "+temping.getMeasure()+" "+temping.getIngredient();
        views.setTextViewText( R.id.ingerdient_widget_tv,Ingerdient );




        Bundle extras = new Bundle();
        extras.putLong( MainActivity.RecipeID, position);

        Intent fillInIntent = new Intent();
        fillInIntent.putExtras(extras);
        views.setOnClickFillInIntent(R.id.widget_item_container, fillInIntent);

        return views;


    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1; // Treat all items in the ListView the same
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
