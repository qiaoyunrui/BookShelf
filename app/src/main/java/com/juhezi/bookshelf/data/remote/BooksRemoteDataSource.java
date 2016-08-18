package com.juhezi.bookshelf.data.remote;

import android.content.Context;

import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.dataModule.BookSimInfo;

import java.util.List;

import io.realm.RealmResults;
import rx.Observable;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
public class BooksRemoteDataSource implements BooksDataSource {

    private static final String TAG = "BooksRemoteDataSource";

    private Context mContext;

    private static BooksRemoteDataSource sDataSource;

    public BooksRemoteDataSource(Context context) {
        this.mContext = context;
    }

    public static BooksRemoteDataSource newInstance(Context context) {
        if (sDataSource == null) {
            sDataSource = new BooksRemoteDataSource(context);
        }
        return sDataSource;
    }


    @Override
    public Observable<List<BookSimInfo>> getBooks() {
        return null;
    }

    @Override
    public Observable<BookSimInfo> getBookByIsbn(String isbn) {
        return null;
    }

    @Override
    public void saveBook(BookSimInfo bookSimInfo, OperateCallback<BookSimInfo> callback) {

    }

    @Override
    public void deleteBook(String id, OperateCallback<BookSimInfo> callback) {

    }

    @Override
    public void deleteAllBooks(OperateCallback<BookSimInfo> callback) {

    }

    @Override
    public void changeBookState(String id, int state, OperateCallback<BookSimInfo> callback) {

    }

    @Override
    public boolean isRepeat(String isbn) {
        return false;
    }
}
