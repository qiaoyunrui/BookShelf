package com.juhezi.bookshelf.shelf;

import android.content.Context;
import android.util.Log;

import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.data.BooksRepository;
import com.juhezi.bookshelf.dataModule.BookSimInfo;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public class ShelfPresenter implements ShelfContract.Presenter{

    private static final String TAG = "ShelfPresenter";

    private final ShelfContract.View mShelfView;
    private final BooksRepository mBooksRepository;

    @Inject
    public ShelfPresenter(ShelfContract.View shelfView, BooksRepository booksRepository) {
        this.mShelfView = shelfView;
        mShelfView.setPresenter(this);
        this.mBooksRepository = booksRepository;
    }

    @Override
    public void start() {
        load();
    }

    @Override
    public void load() {
        refresh();
    }

    @Override
    public void refresh() {
        mShelfView.showDialog();
        mBooksRepository.refreshSimInfos(new BooksDataSource.LoadSimBooksCallback() {
            @Override
            public void onSimBooksLoaded(final List<BookSimInfo> dataList) {
                mShelfView.post(new Runnable() {
                    @Override
                    public void run() {
                        mShelfView.updateRecycler(dataList);
                        mShelfView.hideProgressbar();
                    }
                });

            }

            @Override
            public void onDataNotAvailable() {
                mShelfView.post(new Runnable() {
                    @Override
                    public void run() {
                        mShelfView.hideProgressbar();
                        mShelfView.showErrorToast();
                    }
                });
            }
        });
    }

    @Override
    public void saveData(final BookSimInfo bookSimInfo) {
        mShelfView.showProgressbar();
        mBooksRepository.saveBookInfo(bookSimInfo, new BooksDataSource.OperateCallback() {
            @Override
            public void complete() {
                mShelfView.post(new Runnable() {
                    @Override
                    public void run() {
                        //RecyclerView动画
                        mShelfView.hideProgressbar();
                    }
                });
            }

            @Override
            public void error() {
                mShelfView.post(new Runnable() {
                    @Override
                    public void run() {
                        mShelfView.showErrorToast();
                        mShelfView.hideProgressbar();
                    }
                });
            }
        });
    }

    @Override
    public void handleData(String string) {
        Log.i(TAG, "handleData: " + string);
    }
}
