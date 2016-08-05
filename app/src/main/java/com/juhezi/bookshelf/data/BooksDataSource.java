package com.juhezi.bookshelf.data;

import com.juhezi.bookshelf.dataModule.BookInfo;
import com.juhezi.bookshelf.dataModule.BookSimInfo;

import java.util.List;


/**
 * Created by qiaoyunrui on 16-8-4.
 */
public interface BooksDataSource {

    boolean isRepeat(String isbn);

    interface LoadSimBooksCallback {
        void onSimBooksLoaded(List<BookSimInfo> dataList);

        void onDataNotAvailable();
    }

    interface GetBookInfoCallback {
        void onBookInfoLoaded(BookInfo bookInfo);

        void onDataNotAvailable();
    }

    interface OperateCallback {
        void complete();

        void error();
    }

    void getBookSimInfos(LoadSimBooksCallback callback);

    void refreshSimInfos(LoadSimBooksCallback callback);

    void saveBookInfo(BookSimInfo bookSimInfo,OperateCallback callback);
}
