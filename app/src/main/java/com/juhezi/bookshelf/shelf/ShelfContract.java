package com.juhezi.bookshelf.shelf;

import com.juhezi.bookshelf.BasePresenter;
import com.juhezi.bookshelf.BaseView;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public interface ShelfContract {

    interface Presenter extends BasePresenter {

    }


    interface View extends BaseView<Presenter> {

    }

}
