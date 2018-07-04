package com.example.android.packing.app;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.android.packing.app.Data.Recipe;
import com.example.android.packing.app.Data.Recipes;
import com.example.android.packing.app.Data.Utils;
import com.example.android.packing.app.UI.Fragments.IngerdientStepsFragment;
import com.example.android.packing.app.UI.Fragments.RecipesFragment;
import com.example.android.packing.app.UI.Fragments.StepFragment;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>   {
  public static Recipes AllRecipes;
  public static Boolean IsTablet= false;

  public static Utils.fragment_state Curr_fragment_state;
  public static int CurrentRecipePos,CurrentStepPos;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState( outState );
        outState.putSerializable( Utils.AllRecipesKey,AllRecipes );
        outState.putString( Utils.CurStateKey, Curr_fragment_state.toString() );
        outState.putInt( Utils.CurRecKey,CurrentRecipePos );
        outState.putInt( Utils.CurStepKey,CurrentStepPos );


    }


    public static final String RecipeID="RecipePos";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        IsTablet= CheckPhoneOrTablet();


        AllRecipes = new Recipes();
        if (savedInstanceState!=null) {

            AllRecipes = (Recipes) savedInstanceState.getSerializable( Utils.AllRecipesKey );
            Curr_fragment_state = Utils.fragment_state.valueOf( savedInstanceState.getString( Utils.CurStateKey ) );
            CurrentRecipePos = savedInstanceState.getInt( Utils.CurRecKey );
            CurrentStepPos = savedInstanceState.getInt( Utils.CurStepKey );
            FragmentManager fragmentManager = getSupportFragmentManager();
            if (getSupportActionBar() != null) getSupportActionBar().show();

            switch (Curr_fragment_state) {
                    //Put the Correct Fragment
                    case Recipes:
                        SetRecipesFragment();

                        break;
                    case RecipeDetails: {
                        IngerdientStepsFragment ingerdientStepsFragment = new IngerdientStepsFragment();

                        fragmentManager.beginTransaction()
                                .replace( R.id.fragment_container, ingerdientStepsFragment )//.addToBackStack( null )
                                .commit();
                        break;
                    }
                    case StepDetails: {
                        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE && getSupportActionBar() != null)
                        {
                            getSupportActionBar().hide();
                        }

                        StepFragment stepFragment = new StepFragment();
                        fragmentManager.beginTransaction()
                                .replace( R.id.fragment_container, stepFragment )//.addToBackStack( null )
                                .commit();
                        break;
                    }
                    case RecipesLS:
                        LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams( 0, ViewGroup.LayoutParams.MATCH_PARENT );
                        LP.weight = 1;
                        findViewById( R.id.fragment_container ).setLayoutParams( LP );


                        IngerdientStepsFragment ingerdientStepsFragment = new IngerdientStepsFragment();

                        fragmentManager.beginTransaction()
                                .replace( R.id.fragment_container, ingerdientStepsFragment )
                                .commit();

                        StepFragment stepFragment = new StepFragment();

                        fragmentManager.beginTransaction()
                                .replace( R.id.tablet_StepDetails_fragment, stepFragment )
                                .commit();
                        break;
                }

            } else {

                Curr_fragment_state = Utils.fragment_state.Recipes;
                //Load Recipes
                getSupportLoaderManager().initLoader( 2, new Bundle(), this );
            }
        }


    private Boolean CheckPhoneOrTablet()
    {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        float yInches= metrics.heightPixels/metrics.ydpi;
        float xInches= metrics.widthPixels/metrics.xdpi;
        double diagonalInches = Math.sqrt(xInches*xInches + yInches*yInches);
        return diagonalInches >= 6.5;

    }

    private void SetRecipesFragment(){
        RecipesFragment recipesFragment= new RecipesFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,recipesFragment )
                .commit();}
    @Override
    public  AsyncTaskLoader<String> onCreateLoader(int id,final Bundle args) {
        return new AsyncTaskLoader<String>(this ) {
            @Override
            protected void onStartLoading() {
                if (args==null)
                    return;
                forceLoad();
            }

            @Override
            public String loadInBackground() {

                String Url=getString( R.string.RecipesUrl);

                Gson gson = new Gson();

                Reader reader = new InputStreamReader( Utils.getResponseStream( Url ));

                AllRecipes.setResults( gson.fromJson(reader,Recipe[].class));

                return null;

            }
        }    ;
    }


    @Override
    public void onLoadFinished(android.support.v4.content.Loader<String> loader, String data) {
       SetRecipesFragment();

    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<String> loader) {


    }

    @Override
    public void onBackPressed() {
        if (getSupportActionBar() != null) getSupportActionBar().show();
        int BackStackTop=getSupportFragmentManager().getBackStackEntryCount()-1;

        if(BackStackTop>=0)
            Curr_fragment_state=Utils.fragment_state.valueOf(   getSupportFragmentManager().getBackStackEntryAt( BackStackTop).getName());
        if(IsTablet && Curr_fragment_state== Utils.fragment_state.Recipes) {
            LinearLayout.LayoutParams LP = new LinearLayout.LayoutParams( ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT );
            findViewById( R.id.fragment_container ).setLayoutParams( LP );
        }
        if(Curr_fragment_state== Utils.fragment_state.Recipes)
        { if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled( false );}
        super.onBackPressed();

}
@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
               onBackPressed();
                break;
        }
        return true;
    }
}

