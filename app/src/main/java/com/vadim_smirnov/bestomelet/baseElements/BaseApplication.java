package com.vadim_smirnov.bestomelet.baseElements;

import android.app.Application;

import com.vadim_smirnov.bestomelet.Constants;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by vadimsmirnov on 11.09.17.
 */

public class BaseApplication extends Application {

    private static BaseApplication baseApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        setBaseApplication(this);

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name(Constants.DATABASE_NAME).build();
        Realm.setDefaultConfiguration(config);
    }

    public static BaseApplication getBaseApplication() {
        return baseApplication;
    }

    private static void setBaseApplication(BaseApplication baseApplication) {
        BaseApplication.baseApplication = baseApplication;
    }
}
