package com.example.android.packing.app.Data;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by Omar on 20-May-18.
 */

public class Utils {
    public enum fragment_state {Recipes,RecipeDetails, StepDetails,RecipesLS}

    public static final String AllRecipesKey="ALLRECIPES" ,CurStateKey="CURSTATE" ,CurRecKey="CurRecPos",CurStepKey="CurStepPos";
    public static InputStream getResponseStream (String Url)

    { InputStream ret=null;
        try {
            ret = new URL(Url).openConnection().getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }


}
