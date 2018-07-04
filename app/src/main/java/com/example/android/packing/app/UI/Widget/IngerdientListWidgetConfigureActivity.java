package com.example.android.packing.app.UI.Widget;

import android.app.Activity;
import android.app.LoaderManager;
import android.appwidget.AppWidgetManager;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.android.packing.app.Data.Recipe;
import com.example.android.packing.app.Data.Recipes;
import com.example.android.packing.app.Data.Utils;
import com.example.android.packing.app.R;
import com.google.gson.Gson;

import java.io.InputStreamReader;
import java.io.Reader;

/**
 * The configuration screen for the {@link IngerdientListWidget IngerdientListWidget} AppWidget.
 */
public class IngerdientListWidgetConfigureActivity extends Activity implements LoaderManager.LoaderCallbacks<String>  {

    private static final String PREFS_NAME = "com.example.android.packingapp.backingapp.IngerdientListWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";
    private static final String PREF_PREFIX_KEY_NAME =" appwidget_name_";
    private int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    private static Recipes AllRecipes;
    private RadioGroup radioGroup;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = IngerdientListWidgetConfigureActivity.this;

            // When the button is clicked, store the slected Recipe locally
            int SelectedRecipe = (radioGroup.getCheckedRadioButtonId())-1;
            SaveSelectedRecipeID( context, mAppWidgetId, SelectedRecipe );

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance( context );
            IngerdientListWidget.updateAppWidget( context, appWidgetManager, mAppWidgetId );

            Intent resultValue = new Intent();
            resultValue.putExtra( AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId );
            setResult( RESULT_OK, resultValue );
            finish();
        }
    };

    public IngerdientListWidgetConfigureActivity() {
        super();
    }

    // Write the prefix to the SharedPreferences object for this widget
    private static void SaveSelectedRecipeID(Context context, int appWidgetId, int value) {
        SharedPreferences.Editor prefs = context.getSharedPreferences( PREFS_NAME, 0 ).edit();
        prefs.putInt( PREF_PREFIX_KEY + appWidgetId, value );

        prefs.putString( PREF_PREFIX_KEY_NAME + appWidgetId, AllRecipes.getResults()[value].getName() );
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static int loadRecipePref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences( PREFS_NAME, 0 );
        int selected = prefs.getInt( PREF_PREFIX_KEY + appWidgetId, 0 );

            return selected;

    }

    static void deleteRecipePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences( PREFS_NAME, 0 ).edit();
        prefs.remove( PREF_PREFIX_KEY + appWidgetId );
        prefs.apply();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate( icicle );

        // Set the result to CANCELED.  This will cause the widget host to cancel
        // out of the widget placement if the user presses the back button.
        setResult( RESULT_CANCELED );
AllRecipes=new Recipes();
        setContentView( R.layout.ingerdient_list_widget_configure );
       radioGroup = (RadioGroup) findViewById( R.id.recipes_rg );

getLoaderManager().initLoader( 2,null, this );

findViewById( R.id.add_button ).setOnClickListener( mOnClickListener );

        // Find the widget id from the intent.
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID );
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

    }

    @NonNull
    @Override
    public AsyncTaskLoader<String> onCreateLoader(int id, @Nullable Bundle args) {
      return   new AsyncTaskLoader<String>( this ) {
            @Override
            protected void onStartLoading() {
                // if (args==null)
                //   return;
                forceLoad();
            }

            @Override
            public String loadInBackground() {

                String Url = getString( R.string.RecipesUrl );

                Gson gson = new Gson();

                Reader reader = new InputStreamReader( Utils.getResponseStream( Url ) );

                AllRecipes.setResults( gson.fromJson( reader, Recipe[].class ) );

                return null;

            }
        };
    }

    @Override
    public void onLoadFinished(android.content.Loader<String> loader, String data) {
        for (Recipe R :AllRecipes.getResults()) {
            RadioButton tempButton =new RadioButton( this );
            tempButton.setText( R.getName() );

            radioGroup.addView( tempButton);

        }
    }

    @Override
    public void onLoaderReset(android.content.Loader<String> loader) {

    }


    public static String loadRecipePrefNAme(Context context, int appWidgetId) {

        SharedPreferences prefs = context.getSharedPreferences( PREFS_NAME, 0 );
        String selected = prefs.getString( PREF_PREFIX_KEY_NAME + appWidgetId,"" );

        return selected;}

}

