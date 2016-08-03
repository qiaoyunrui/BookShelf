package com.juhezi.bookshelf;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
@Module
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        this.mContext = context;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

}
