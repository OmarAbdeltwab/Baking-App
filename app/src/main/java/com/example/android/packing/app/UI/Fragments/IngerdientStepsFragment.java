package com.example.android.packing.app.UI.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.packing.app.Data.Utils;
import com.example.android.packing.app.MainActivity;
import com.example.android.packing.app.UI.Adapters.IngredientAdapter;
import com.example.android.packing.app.UI.Adapters.StepAdapter;
import com.example.android.packing.app.R;

import static com.example.android.packing.app.MainActivity.Curr_fragment_state;


/**
 * Created by Omar on 21-May-18.
 */

public class IngerdientStepsFragment extends Fragment implements StepAdapter.StepOnclickListner {
    // --Commented out by Inspection (29-May-18 4:00 AM):Recipes recipes;
    private RecyclerView  IngerdientsRV;
    private RecyclerView StepsRV;
    public IngerdientStepsFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );
        if (((MainActivity)context).getSupportActionBar() != null)
            ((MainActivity)context).getSupportActionBar().setDisplayHomeAsUpEnabled( true );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {




        container.removeAllViews();

        View rootView = inflater.inflate( R.layout.recipe_details_fragment, container, false);

        IngerdientsRV =rootView.findViewById( R.id.ingredients_rv );
        StepsRV=rootView.findViewById( R.id.steps_rv );

        RecyclerView.LayoutManager mLayoutManger= new LinearLayoutManager(container.getContext());

        IngerdientsRV.setLayoutManager( mLayoutManger );
        IngredientAdapter ingredientAdapter = new IngredientAdapter( MainActivity.AllRecipes.getResults()[MainActivity.CurrentRecipePos].getIngredients() );
        IngerdientsRV.setAdapter( ingredientAdapter );

        RecyclerView.LayoutManager SLayoutManger= new LinearLayoutManager(container.getContext());

        StepsRV.setLayoutManager( SLayoutManger );
        StepAdapter stepAdapter = new StepAdapter(MainActivity. AllRecipes.getResults()[MainActivity.CurrentRecipePos].getSteps(),this );
        StepsRV.setAdapter( stepAdapter );



        return rootView;
    }

    @Override
    public void StepOnClick(int pos) {
MainActivity.CurrentStepPos=pos;
        StepFragment stepFragment= new StepFragment();

        FragmentManager fragmentManager =getFragmentManager();
        if (MainActivity.IsTablet){
        fragmentManager.beginTransaction()
                .replace(R.id.tablet_StepDetails_fragment,stepFragment ).addToBackStack( Utils.fragment_state.RecipeDetails.toString() )
                .commit();

        }
       else{
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,stepFragment ).addToBackStack( Utils.fragment_state.RecipeDetails.toString() )
                .commit();
        Curr_fragment_state= Utils.fragment_state.StepDetails;}
    }

    // Setter methods for k
}
