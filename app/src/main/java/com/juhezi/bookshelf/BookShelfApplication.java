package com.juhezi.bookshelf;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.inspector.database.ContentProviderSchema;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.internal.Table;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public class BookShelfApplication extends Application{

    private static final String TAG = "BookShelfApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration
                .Builder(this).deleteRealmIfMigrationNeeded()
        .build();
        Realm.setDefaultConfiguration(config);
    }
}
