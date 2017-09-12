package com.vadim_smirnov.bestomelet.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public class Recipe extends RealmObject {

    private String title;

    @PrimaryKey
    private String href;

    private String ingredients;

    private String thumbnail;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
