package com.example.android.packing.app.UI.Fragments;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.android.packing.app.Data.Utils;
import com.example.android.packing.app.MainActivity;
import com.example.android.packing.app.UI.Adapters.RecipeAdapter;
import com.example.android.packing.app.R;

import static com.example.android.packing.app.MainActivity.Curr_fragment_state;

/**
 * Created by Omar on 21-May-18.
 */

public class RecipesFragment extends Fragment implements RecipeAdapter.RecipeOnCickListner {

    private ViewGroup container ;
public  RecipesFragment(){
    //context=getContext();
 }


    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (((MainActivity)context).getSupportActionBar() != null)
            ((MainActivity)context).getSupportActionBar().setDisplayHomeAsUpEnabled( false );
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        this.container=container;

        // Inflate the Android-Me fragment layout
        View rootView = inflater.inflate( R.layout.recipes_fragment, container, false);
        container.removeAllViews();

       RecyclerView RecipesRV= rootView.findViewById( R.id.recipes_rv );
       RecyclerView.LayoutManager mLayoutManger;
if (getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT || MainActivity.IsTablet)
    mLayoutManger=new GridLayoutManager(container.getContext(), 3 );
else
    mLayoutManger= new LinearLayoutManager(container.getContext());
        RecipesRV.setLayoutManager( mLayoutManger );
        RecipeAdapter mrecipeAdapter = new RecipeAdapter( MainActivity.AllRecipes,this);
        RecipesRV.setAdapter( mrecipeAdapter );



        return rootView;
    }
    @Override
    public void RecipeOnClick(int pos) {
        MainActivity.CurrentRecipePos=pos;
        if(MainActivity.IsTablet){
            // 6.5inch device or bigger
            //
            LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT );
            LP.weight=1;
            container.findViewById( R.id.fragment_container ).setLayoutParams(LP );

            IngerdientStepsFragment ingerdientStepsFragment= new IngerdientStepsFragment();
            FragmentManager fragmentManager =getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,ingerdientStepsFragment ).addToBackStack( Utils.fragment_state.Recipes.toString() )
                    .commit();
            Curr_fragment_state= Utils.fragment_state.RecipesLS;

            StepFragment stepFragment= new StepFragment();

            MainActivity.CurrentStepPos=0;
            fragmentManager.beginTransaction()
                    .replace(R.id.tablet_StepDetails_fragment,stepFragment )
                    .commit();
            Curr_fragment_state= Utils.fragment_state.RecipesLS;

        }else{
            // smaller device
            IngerdientStepsFragment ingerdientStepsFragment= new IngerdientStepsFragment();
            FragmentManager fragmentManager =getFragmentManager();

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container,ingerdientStepsFragment ).addToBackStack( Utils.fragment_state.Recipes.toString() )
                    .commit();
            Curr_fragment_state= Utils.fragment_state.RecipeDetails;
        }





    }
}
