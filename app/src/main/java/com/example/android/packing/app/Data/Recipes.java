package com.example.android.packing.app.Data;
import java.io.Serializable;

public class Recipes  implements Serializable {



    private Recipe[] results = null;

    public  Recipe[] getResults() {return results;    }

    public   void    setResults(Recipe[] results) {
        this.results = results;
    }



}