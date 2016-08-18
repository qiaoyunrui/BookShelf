package com.juhezi.bookshelf.content;

import com.juhezi.bookshelf.data.BooksRepository;
import com.juhezi.bookshelf.data.BooksRepositoryComponent;
import com.juhezi.bookshelf.other.FragmentScoped;

import dagger.Component;

/**
 * Created by qiaoyunrui on 16-8-18.
 */
@FragmentScoped
@Component(dependencies = BooksRepositoryComponent.class, modules = ContentPresenterMoudle.class)
public interface ContentComponent {

    void inject(ContentActivity activity);

}
