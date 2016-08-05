package com.juhezi.bookshelf.data.remote;

import android.content.Context;

import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.dataModule.BookSimInfo;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
public class BooksRemoteDataSource implements BooksDataSource {

    private static final String TAG = "BooksRemoteDataSource";

    private Context mContext;

    private static BooksRemoteDataSource sDataSource;

    private BooksRemoteDataSource(Context context) {
        this.mContext = context;
    }

    public static BooksRemoteDataSource newInstance(Context context) {
        if (sDataSource == null) {
            sDataSource = new BooksRemoteDataSource(context);
        }
        return sDataSource;
    }

    @Override
    public boolean isRepeat(String isbn) {
        return false;
    }

    @Override
    public void getBookSimInfos(LoadSimBooksCallback callback) {

    }

    @Override
    public void refreshSimInfos(LoadSimBooksCallback callback) {

    }

    @Override
    public void saveBookInfo(BookSimInfo bookSimInfo, OperateCallback callback) {

    }

    @Override
    public void deleteBook(String id) {

    }

}
