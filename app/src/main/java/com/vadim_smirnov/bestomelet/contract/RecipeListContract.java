package com.vadim_smirnov.bestomelet.contract;

import com.vadim_smirnov.bestomelet.baseElements.BaseContract;
import com.vadim_smirnov.bestomelet.model.Recipe;

import java.util.List;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public class RecipeListContract {

    public interface View extends BaseContract.View<Presenter> {

        void showRecipes(List<Recipe> recipes);

        void showLoading(boolean isShow);

        void showError(String message);

    }

    public interface Presenter extends BaseContract.Presenter {

        void fetchRecipes();

        void searchRecipe(String name);

    }

}
