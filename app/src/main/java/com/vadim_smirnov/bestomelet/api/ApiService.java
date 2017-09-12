package com.vadim_smirnov.bestomelet.api;

import com.vadim_smirnov.bestomelet.model.RecipeResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public interface ApiService {

    @GET(ApiConstants.GET_RECIPES_URL)
    Call<RecipeResponse> fetchRecipes();

    @GET(".")
    Call<RecipeResponse> searchRecipe(@Query("q") String name);

}
