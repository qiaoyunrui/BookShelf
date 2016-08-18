package com.juhezi.bookshelf;

import android.app.Application;

import com.juhezi.bookshelf.data.BooksRepositoryComponent;
import com.juhezi.bookshelf.data.BooksRepositoryModule;
import com.juhezi.bookshelf.data.DaggerBooksRepositoryComponent;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public class BookShelfApplication extends Application {

    private static final String TAG = "BookShelfApplication";

    private BooksRepositoryComponent mBooksRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration config = new RealmConfiguration
                .Builder(this).deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(config);
        mBooksRepositoryComponent = DaggerBooksRepositoryComponent.builder()
                .applicationModule(new ApplicationModule(getApplicationContext()))
                .booksRepositoryModule(new BooksRepositoryModule())
                .build();
    }

    public BooksRepositoryComponent getmBooksRepositoryComponent() {
        return mBooksRepositoryComponent;
    }


}
