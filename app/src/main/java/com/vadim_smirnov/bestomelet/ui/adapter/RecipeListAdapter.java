package com.vadim_smirnov.bestomelet.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vadim_smirnov.bestomelet.R;
import com.vadim_smirnov.bestomelet.model.Recipe;
import com.vadim_smirnov.bestomelet.utils.PicassoCircleTransform;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeListViewHolder> {

    private List<Recipe> mRecipeList;
    private Context mContext;

    public RecipeListAdapter(List<Recipe> recipeList) {
        mRecipeList = recipeList;
    }

    @Override
    public RecipeListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_recipe_row, parent, false);
        mContext = parent.getContext();
        return new RecipeListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecipeListViewHolder holder, int position) {
       holder.mRecipe = mRecipeList.get(position);

        holder.mTextViewTitle.setText(holder.mRecipe.getTitle());
        holder.mTextViewIngredients.setText(holder.mRecipe.getIngredients());

        if (!holder.mRecipe.getThumbnail().isEmpty()) {
            Picasso.with(mContext)
                    .load(holder.mRecipe.getThumbnail())
                    .resize(200,200)
                    .error(R.mipmap.ic_launcher_round)
                    .transform(new PicassoCircleTransform())
                    .into(holder.mImageViewThumbnail);
        } else {
            holder.mImageViewThumbnail.setImageResource(R.mipmap.ic_launcher_round);
        }

    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }

    class RecipeListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private View mView;

        Recipe mRecipe;

        @BindView(R.id.text_view_title)
        TextView mTextViewTitle;

        @BindView(R.id.text_view_ingredients)
        TextView mTextViewIngredients;

        @BindView(R.id.image_view_thumbnail)
        ImageView mImageViewThumbnail;

        RecipeListViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            mView = itemView;
            mView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mRecipe.getHref()));
            mContext.startActivity(browserIntent);
        }
    }

}
