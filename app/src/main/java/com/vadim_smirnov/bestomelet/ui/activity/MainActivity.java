package com.vadim_smirnov.bestomelet.ui.activity;

import android.os.Bundle;

import com.vadim_smirnov.bestomelet.R;
import com.vadim_smirnov.bestomelet.baseElements.BaseActivity;
import com.vadim_smirnov.bestomelet.presenter.RecipeListPresenter;
import com.vadim_smirnov.bestomelet.ui.fragment.RecipeListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecipeListFragment fragment = RecipeListFragment.newInstance();
        new RecipeListPresenter(fragment);
        replaceFragment(fragment);
    }

}
