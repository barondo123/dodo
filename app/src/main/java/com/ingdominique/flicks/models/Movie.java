package com.ingdominique.flicks.models;

import org.json.JSONObject;

/**
 * Created by INGDOMINIQUE on 10/10/2017.
 */

public class Movie {
    // valeur pour Api
    private  String title;
    private String overview;
    private  String posterPath;
    private String backdropPath;

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Movie(JSONObject object) throws Exception{
        title=object.getString("title");
        overview=object.getString("overview");
        posterPath=object.getString("poster_path");
        backdropPath=object.getString("backdrop_path");
    }

}
