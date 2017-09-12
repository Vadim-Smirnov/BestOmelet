package com.vadim_smirnov.bestomelet.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vadim_smirnov.bestomelet.R;
import com.vadim_smirnov.bestomelet.contract.RecipeListContract;
import com.vadim_smirnov.bestomelet.model.Recipe;
import com.vadim_smirnov.bestomelet.ui.adapter.RecipeListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public class RecipeListFragment extends Fragment implements RecipeListContract.View, SearchView.OnQueryTextListener {

    @BindView(R.id.progress)
    protected ProgressBar mProgress;

    @BindView(R.id.recycler_view_recipe)
    protected RecyclerView mRecyclerViewRecipes;

    @BindView(R.id.content)
    View mContent;

    @BindView(R.id.search_view)
    SearchView mSearchView;

    @BindView(R.id.toolbar_actionbar)
    Toolbar mToolbar;

    private RecipeListContract.Presenter mPresenter;


    public static RecipeListFragment newInstance() {
        return new RecipeListFragment();
    }

    @Override
    public void setPresenter(RecipeListContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.start();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        ButterKnife.bind(this, view);

        mSearchView.setOnQueryTextListener(this);
        mToolbar.setTitle(R.string.app_name);
        mPresenter.fetchRecipes();

        return view;
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        mRecyclerViewRecipes.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerViewRecipes.setHasFixedSize(true);
        mRecyclerViewRecipes.setAdapter(new RecipeListAdapter(recipes));
    }

    @Override
    public void showLoading(boolean isShow) {
        mProgress.setVisibility(isShow ? View.VISIBLE : View.GONE);
        mContent.setVisibility(isShow ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            mPresenter.fetchRecipes();
        } else if (newText.length() > 0) {
            mPresenter.searchRecipe(newText);
        }
        return false;
    }
}
