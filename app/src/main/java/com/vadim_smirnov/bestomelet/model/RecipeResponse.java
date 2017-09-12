package com.vadim_smirnov.bestomelet.model;

import java.util.List;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public class RecipeResponse {

    private List<Recipe> results;

    public List<Recipe> getResults() {
        return results;
    }

    public void setResults(List<Recipe> results) {
        this.results = results;
    }
}
