package com.vadim_smirnov.bestomelet.model.data_source;

import android.util.Log;

import com.google.gson.Gson;
import com.vadim_smirnov.bestomelet.api.ApiManager;
import com.vadim_smirnov.bestomelet.baseElements.BaseApplication;
import com.vadim_smirnov.bestomelet.model.Recipe;
import com.vadim_smirnov.bestomelet.model.RecipeResponse;
import com.vadim_smirnov.bestomelet.utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.vadim_smirnov.bestomelet.baseElements.BaseApplication.getBaseApplication;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public class RecipeDataSource implements RecipeDataSourceInterface {

    private Realm mRealmDatabase;

    public RecipeDataSource() {
        this.mRealmDatabase = Realm.getDefaultInstance();
    }

    @Override
    public void fetchRecipes(final ModelCallback<List<Recipe>> callback) {
        RealmResults<Recipe> result = mRealmDatabase.where(Recipe.class).findAll();
        if (mRealmDatabase.copyFromRealm(result).size() != 0) {
            callback.completion(mRealmDatabase.copyFromRealm(result));
        }

        if (!NetworkUtils.isNetworkAvailable(getBaseApplication())) {
            return;
        }

        ApiManager.getInstance().getService().fetchRecipes().enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful()) {
                        callback.completion(response.body().getResults());
                        saveDataToDatabase(response.body().getResults());

                } else {
                    callback.error(response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                callback.error(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void searchRecipe(String name, final ModelCallback<List<Recipe>> callback) {
        if (!NetworkUtils.isNetworkAvailable(getBaseApplication())) {
            callback.error("No Internet connection!");
            return;
        }
        ApiManager.getInstance().getService().searchRecipe(name).enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful()) {
                    callback.completion(response.body().getResults());
                    saveDataToDatabase(response.body().getResults());

                } else {
                    callback.error(response.message());
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                callback.error(t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void saveDataToDatabase(final List<Recipe> recipes) {
        mRealmDatabase.beginTransaction();
        RealmList<Recipe> realmList = new RealmList<>();
        realmList.addAll(recipes);
        mRealmDatabase.copyToRealmOrUpdate(realmList);
        mRealmDatabase.commitTransaction();
    }

}
