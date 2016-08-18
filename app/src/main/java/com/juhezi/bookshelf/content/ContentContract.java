package com.juhezi.bookshelf.content;

import android.content.Intent;

import com.juhezi.bookshelf.BasePresenter;
import com.juhezi.bookshelf.BaseView;
import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.dataModule.BookInfo;

/**
 * Created by qiaoyunrui on 16-8-18.
 */
public interface ContentContract {

    interface Presenter extends BasePresenter {

        void openOnDouban();    //在豆瓣网中查看

        void getData(String isbn);

    }

    interface View extends BaseView<Presenter> {

        void showContent(BookInfo bookInfo);    //显示图书信息

        void showProgressBar();

        void hideProgressBar();

        void showErrorToast();
    }

}
