package com.example.android.packing.app.UI.Widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.android.packing.app.MainActivity;
import com.example.android.packing.app.R;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link IngerdientListWidgetConfigureActivity IngerdientListWidgetConfigureActivity}
 */
public class IngerdientListWidget extends AppWidgetProvider {
    private static int SelectedRecipe;
    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        SelectedRecipe = IngerdientListWidgetConfigureActivity.loadRecipePref( context, appWidgetId );
        String SelectedRecipeName = IngerdientListWidgetConfigureActivity.loadRecipePrefNAme( context, appWidgetId );
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews( context.getPackageName(), R.layout.ingerdient_list_widget );

        views.setTextViewText( R.id.appwidget_text, SelectedRecipeName );

        Intent intent = new Intent(context, ListWidgetService.class);
        intent.putExtra( MainActivity.RecipeID ,SelectedRecipe );
        views.setRemoteAdapter(R.id.ingerdient_widget_lv, intent);
        appWidgetManager.updateAppWidget( appWidgetId, views );

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {


            updateAppWidget( context, appWidgetManager, appWidgetId );
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        // When the user deletes the widget, delete the preference associated with it.
        for (int appWidgetId : appWidgetIds) {
            IngerdientListWidgetConfigureActivity.deleteRecipePref( context, appWidgetId );
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

