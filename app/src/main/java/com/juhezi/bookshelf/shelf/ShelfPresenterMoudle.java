package com.juhezi.bookshelf.shelf;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
@Module
public class ShelfPresenterMoudle {

    private static final String TAG = "ShelfPresenterMoudle";

    private final ShelfContract.View mView;

    public ShelfPresenterMoudle(ShelfContract.View view) {
        this.mView = view;
    }

    @Provides
    ShelfContract.View provideContractView() {
        return mView;
    }

}
