package com.juhezi.bookshelf;

import com.facebook.stetho.Stetho;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public class DebugApplication extends BookShelfApplication {

    private static final String TAG = "DebugApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
