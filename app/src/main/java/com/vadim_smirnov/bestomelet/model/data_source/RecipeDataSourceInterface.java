package com.vadim_smirnov.bestomelet.model.data_source;

import com.vadim_smirnov.bestomelet.model.Recipe;

import java.util.List;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public interface RecipeDataSourceInterface {

    void fetchRecipes(ModelCallback<List<Recipe>> callback);

    void searchRecipe(String name, ModelCallback<List<Recipe>> callback);

}
