package com.juhezi.bookshelf.shelf;

import com.juhezi.bookshelf.BasePresenter;
import com.juhezi.bookshelf.BaseView;
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

        void handleData(String string);

    }


    interface View extends BaseView<Presenter> {

        void showProgressbar();

        void hideProgressbar();

        void turn2ZXingAct();

        void turn2ContentAct();

        void showDialog();  //显示对话框

        void updateRecycler(List<BookSimInfo> dataList);

        void requestPrimission();

        void showErrorToast();

        void post(Runnable r);
    }

}
