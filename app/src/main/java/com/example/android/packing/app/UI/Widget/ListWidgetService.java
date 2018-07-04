package com.example.android.packing.app.UI.Widget;

//the code from the example with some edits

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.example.android.packing.app.MainActivity;


public class ListWidgetService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        ListRemoteViewsFactory.SelectedRec=intent.getIntExtra( MainActivity.RecipeID,0 );
        return new ListRemoteViewsFactory(this.getApplicationContext());
    }


}

