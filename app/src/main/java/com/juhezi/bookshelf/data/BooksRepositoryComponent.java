package com.juhezi.bookshelf.data;

import com.juhezi.bookshelf.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by qiaoyunrui on 16-8-16.
 */
@Singleton
@Component(modules = {BooksRepositoryModule.class, ApplicationModule.class})
public interface BooksRepositoryComponent {
    BooksRepository getBooksRepository();
}
