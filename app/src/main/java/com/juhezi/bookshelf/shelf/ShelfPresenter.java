package com.juhezi.bookshelf.shelf;

import android.content.Context;
import android.util.Log;

import com.juhezi.bookshelf.data.BooksRepository;

import javax.inject.Inject;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public class ShelfPresenter implements ShelfContract.Presenter{

    private static final String TAG = "ShelfPresenter";

    private final ShelfContract.View mShelfView;
    private final BooksRepository mBooksRepository;

    @Inject
    public ShelfPresenter(ShelfContract.View shelfView, BooksRepository booksRepository) {
        this.mShelfView = shelfView;
        mShelfView.setPresenter(this);
        this.mBooksRepository = booksRepository;
    }

    @Override
    public void start() {
    }
}
