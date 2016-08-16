package com.juhezi.bookshelf.shelf.structure;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
@Module
public class ShelfPresenterMoudle {

    private final ShelfContract.View mView;

    private final Context mContext;

    public ShelfPresenterMoudle(ShelfContract.View view, Context context) {
        this.mView = view;
        this.mContext = context;
    }

    @Provides
    ShelfContract.View provideContractView() {
        return mView;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }
}
