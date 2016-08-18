package com.juhezi.bookshelf.content;

import com.juhezi.bookshelf.BasePresenter;
import com.juhezi.bookshelf.BaseView;

/**
 * Created by qiaoyunrui on 16-8-18.
 */
public interface ContentContract {

    interface Presenter extends BasePresenter {

    }

    interface View extends BaseView<Presenter> {

    }

}
