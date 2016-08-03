package com.juhezi.bookshelf;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public class BookShelfApplication extends Application{

    private static final String TAG = "BookShelfApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
