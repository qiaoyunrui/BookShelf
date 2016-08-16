package com.juhezi.bookshelf.shelf.structure;

import com.juhezi.bookshelf.data.BooksRepositoryComponent;
import com.juhezi.bookshelf.data.BooksRepositoryModule;
import com.juhezi.bookshelf.other.FragmentScoped;

import dagger.Component;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
@FragmentScoped
@Component(dependencies = BooksRepositoryComponent.class, modules = ShelfPresenterMoudle.class)
public interface ShelfComponent {

    void inject(ShelfActivity activity);

}
