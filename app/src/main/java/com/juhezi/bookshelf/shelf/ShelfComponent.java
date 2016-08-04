package com.juhezi.bookshelf.shelf;

import com.juhezi.bookshelf.ApplicationModule;

import dagger.Component;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
@Component(modules = ShelfPresenterMoudle.class)
public interface ShelfComponent {

    void inject(ShelfActivity activity);

}
