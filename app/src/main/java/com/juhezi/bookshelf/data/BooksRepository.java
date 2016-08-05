package com.juhezi.bookshelf.data;

import android.support.annotation.NonNull;
import android.util.Log;

import com.juhezi.bookshelf.dataModule.BookSimInfo;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
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

    private BooksRepository(@NonNull BooksDataSource booksLocalDataSource,
                            @NonNull BooksDataSource booksRemoteDataSource) {
        this.mBooksLocalDataSource = booksLocalDataSource;
        this.mBooksRemoteDataSource = booksRemoteDataSource;
    }

    @Override
    public void getBookSimInfos(final LoadSimBooksCallback callback) {
        mBooksLocalDataSource.getBookSimInfos(callback);

    }

    @Override
    public void refreshSimInfos(LoadSimBooksCallback callback) {
        mBooksLocalDataSource.refreshSimInfos(callback);
    }

    @Override
    public void saveBookInfo(BookSimInfo bookSimInfo, OperateCallback callback) {
        mBooksLocalDataSource.saveBookInfo(bookSimInfo, callback);
    }

    @Override
    public void deleteBook(String id) {
        mBooksLocalDataSource.deleteBook(id);
    }

    /**
     * 根据isbn检查数据库中是否存在相同书籍
     *
     * @param isbn
     * @return
     */
    @Override
    public boolean isRepeat(String isbn) {
        return mBooksLocalDataSource.isRepeat(isbn);
    }

}
