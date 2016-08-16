package com.juhezi.bookshelf.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.juhezi.bookshelf.dataModule.BookSimInfo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
@Singleton
public class BooksRepository implements BooksDataSource {

    private static final String TAG = "BooksRepository";

    private static BooksRepository sInstance = null;

    private BooksDataSource mBooksLocalDataSource;
    private BooksDataSource mBooksRemoteDataSource;

    public static BooksRepository newInstance(@NonNull BooksDataSource booksLocalDataSource,
                                              @NonNull BooksDataSource booksRemoteDataSource) {
        if (sInstance == null) {
            sInstance = new BooksRepository(booksLocalDataSource,
                    booksRemoteDataSource);
        }
        return sInstance;
    }

    @Inject
    public BooksRepository(@Local BooksDataSource booksLocalDataSource,
                           @Remote BooksDataSource booksRemoteDataSource) {
        this.mBooksLocalDataSource = booksLocalDataSource;
        this.mBooksRemoteDataSource = booksRemoteDataSource;
    }

    @Override
    public Observable<List<BookSimInfo>> getBooks() {
        return mBooksLocalDataSource.getBooks();
    }

    @Override
    public Observable<BookSimInfo> getBookByIsbn(String isbn) {
        return mBooksLocalDataSource.getBookByIsbn(isbn);
    }

    @Override
    public void saveBook(BookSimInfo bookSimInfo, OperateCallback<BookSimInfo> callback) {
        mBooksLocalDataSource.saveBook(bookSimInfo, callback);
    }

    @Override
    public void deleteBook(String id, OperateCallback<BookSimInfo> callback) {
        mBooksLocalDataSource.deleteBook(id, callback);
    }

    @Override
    public void deleteAllBooks(OperateCallback<BookSimInfo> callback) {
        mBooksLocalDataSource.deleteAllBooks(callback);
    }

    @Override
    public void changeBookState(String id, int state, OperateCallback<BookSimInfo> callback) {
        mBooksLocalDataSource.changeBookState(id, state, callback);
    }

    @Override
    public boolean isRepeat(String isbn) {
        return mBooksLocalDataSource.isRepeat(isbn);
    }
}
