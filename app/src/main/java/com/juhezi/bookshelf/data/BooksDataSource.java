package com.juhezi.bookshelf.data;

import com.juhezi.bookshelf.dataModule.BookInfo;
import com.juhezi.bookshelf.dataModule.BookSimInfo;

import java.util.List;

import io.realm.RealmResults;
import rx.Observable;


/**
 * Created by qiaoyunrui on 16-8-4.
 */
public interface BooksDataSource {

    interface LoadSimBooksCallback {

        void onSimBooksLoaded(List<BookSimInfo> dataList);

        void onDataNotAvailable();

    }

    interface GetBookInfoCallback {

        void onBookInfoLoaded(BookInfo bookInfo);

        void onDataNotAvailable();

    }

    interface OperateCallback<T> {

        void complete(T t);

        void error();

    }

    Observable<List<BookSimInfo>> getBooks();


    Observable<BookSimInfo> getBookByIsbn(String isbn);

    void saveBook(BookSimInfo bookSimInfo, OperateCallback<BookSimInfo> callback);

    void deleteBook(String id, OperateCallback<BookSimInfo> callback);

    void deleteAllBooks(OperateCallback<BookSimInfo> callback);

    void changeBookState(String id, int state, OperateCallback<BookSimInfo> callback);

    boolean isRepeat(String isbn);
}
