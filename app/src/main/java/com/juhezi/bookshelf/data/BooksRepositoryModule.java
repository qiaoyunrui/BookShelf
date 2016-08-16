package com.juhezi.bookshelf.data;

import android.content.Context;

import com.juhezi.bookshelf.data.local.BooksLocalDataSource;
import com.juhezi.bookshelf.data.remote.BooksRemoteDataSource;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by qiaoyunrui on 16-8-16.
 */
@Module
public class BooksRepositoryModule {

    @Singleton
    @Provides
    @Local
    BooksDataSource provideBooksLocalDataSource(Context context) {
        return new BooksLocalDataSource(context);
    }

    @Singleton
    @Provides
    @Remote
    BooksDataSource provideBooksRemoteDataSource(Context context) {
        return new BooksRemoteDataSource(context);
    }

}
