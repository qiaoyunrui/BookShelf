package com.juhezi.bookshelf.content;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qiaoyunrui on 16-8-18.
 */
@Module
public class ContentPresenterMoudle {
    private final ContentContract.View mView;

    public ContentPresenterMoudle(ContentContract.View view) {
        this.mView = view;
    }

    @Provides
    ContentContract.View provideContentView() {
        return mView;
    }

}
