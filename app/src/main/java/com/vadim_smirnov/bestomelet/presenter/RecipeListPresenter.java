package com.vadim_smirnov.bestomelet.presenter;

import com.vadim_smirnov.bestomelet.contract.RecipeListContract;
import com.vadim_smirnov.bestomelet.model.Recipe;
import com.vadim_smirnov.bestomelet.model.data_source.ModelCallback;
import com.vadim_smirnov.bestomelet.model.data_source.RecipeDataSource;

import java.util.List;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public class RecipeListPresenter implements RecipeListContract.Presenter {

    private RecipeListContract.View mView;

    private RecipeDataSource mRecipeDataSource;

    public RecipeListPresenter(RecipeListContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mRecipeDataSource = new RecipeDataSource();
    }

    @Override
    public void fetchRecipes() {
        mView.showLoading(true);
        mRecipeDataSource.fetchRecipes(new ModelCallback<List<Recipe>>() {
            @Override
            public void completion(List<Recipe> response) {
                mView.showRecipes(response);
                mView.showLoading(false);
            }

            @Override
            public void error(String error) {
                mView.showError(error);
                mView.showLoading(false);
            }

        });
    }

    @Override
    public void searchRecipe(String name) {
        mRecipeDataSource.searchRecipe(name, new ModelCallback<List<Recipe>>() {
            @Override
            public void completion(List<Recipe> response) {
                mView.showRecipes(response);
            }

            @Override
            public void error(String error) {
                mView.showError(error);
            }

        });
    }
}
