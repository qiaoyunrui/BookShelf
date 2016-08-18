package com.juhezi.bookshelf.shelf.structure;

import android.content.Context;
import android.view.MenuItem;

import com.juhezi.bookshelf.BasePresenter;
import com.juhezi.bookshelf.BaseView;
import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.dataModule.BookSimInfo;

import java.util.List;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public interface ShelfContract {

    interface Presenter extends BasePresenter {

        void load();    //初次加载数据

        void refresh();

        void saveData(BookSimInfo bookSimInfo);

        void handleData(String isbn);

        void deleteData(String id, BooksDataSource.OperateCallback<BookSimInfo> callback);

        void changeState(String id, int state, BooksDataSource.OperateCallback<BookSimInfo> callback);

        void changeLayout(MenuItem view);

        boolean getLayoutState();

        void deleteAll(BooksDataSource.OperateCallback<BookSimInfo> callback);
    }


    interface View extends BaseView<Presenter> {

        void showProgressbar();

        void hideProgressbar();

        void turn2ZXingAct();

        void turn2ContentAct(BookSimInfo bookSimInfo);

        void showDialog();  //显示对话框

        void updateRecycler(List<BookSimInfo> dataList);

        void requestPrimission();

        void showErrorToast();

        void showSnackbar(int type);

        void recyclerViewAdd(BookSimInfo bookSimInfo);

        void recyclerViewScrollTop();

        void change2Linear(MenuItem view);   //切换为线性布局

        void change2Stagger(MenuItem view);  //切换为瀑布流布局

        void showEmptyView();

        void hideEmptyView();

        void showDialogDeleteAll();

    }

}
