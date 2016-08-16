package com.juhezi.bookshelf.shelf.structure;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.juhezi.bookshelf.R;
import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.data.BooksRepository;
import com.juhezi.bookshelf.dataModule.BookInfo;
import com.juhezi.bookshelf.dataModule.BookService;
import com.juhezi.bookshelf.dataModule.BookSimInfo;
import com.juhezi.bookshelf.other.Config;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public class ShelfPresenter implements ShelfContract.Presenter {

    private static final String TAG = "ShelfPresenter";
    private final ShelfContract.View mShelfView;
    private final BooksRepository mBooksRepository;
    private Retrofit mRetrofit;
    private BookService mBookService;
    private SharedPreferences mSharedPreferences;
    private Context mContext;

    @Inject
    public ShelfPresenter(ShelfContract.View shelfView, BooksRepository booksRepository, Context context) {
        this.mShelfView = shelfView;
        this.mContext = context;
        mShelfView.setPresenter(this);
        this.mBooksRepository = booksRepository;
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Config.DOUBAN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBookService = mRetrofit.create(BookService.class);
    }

    @Override
    public void start() {
        mShelfView.requestPrimission(); //请求权限
        mShelfView.showProgressbar();
        load();
    }

    @Override
    public void load() {
        refresh();
    }

    @Override
    public void refresh() {
        mBooksRepository.getBooks()
                .subscribe(new Observer<List<BookSimInfo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mShelfView.showErrorToast();
                    }

                    @Override
                    public void onNext(List<BookSimInfo> list) {
                        mShelfView.hideProgressbar();
                        mShelfView.updateRecycler(list);
                    }
                });
    }

    @Override
    public void saveData(final BookSimInfo bookSimInfo) {
        mBooksRepository.saveBook(bookSimInfo, new BooksDataSource.OperateCallback<BookSimInfo>() {
            @Override
            public void complete(BookSimInfo bookSimInfo) {
                mShelfView.showSnackbar(ShelfFragment.SUCCESS);
                mShelfView.recyclerViewAdd(bookSimInfo);
                mShelfView.recyclerViewScrollTop();
                mShelfView.hideProgressbar();
            }

            @Override
            public void error() {
                mShelfView.showErrorToast();
                mShelfView.hideProgressbar();
            }
        });
    }

    @Override
    public void handleData(String isbn) {
//        mShelfView.showProgressbar();
        Call<BookInfo> call = mBookService.getBookInfo(isbn);
        final BookSimInfo[] bookSimInfo = {null};
        call.enqueue(new Callback<BookInfo>() {
            @Override
            public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
                if (response.code() == 200) {
                    bookSimInfo[0] = response.body().toBookSimInfo();
                    if (mBooksRepository.isRepeat(bookSimInfo[0].getIsbn())) {  //书籍重复
                        mShelfView.showSnackbar(ShelfFragment.REPEAT);
                        mShelfView.hideProgressbar();
                    } else {
                        saveData(bookSimInfo[0]);
                    }
                } else {
                    mShelfView.showSnackbar(ShelfFragment.FAIL);
                    mShelfView.hideProgressbar();
                }
            }

            @Override
            public void onFailure(Call<BookInfo> call, Throwable t) {
                mShelfView.showSnackbar(ShelfFragment.FAIL);
                mShelfView.hideProgressbar();
            }
        });
    }

    @Override
    public void deleteData(String id, BooksDataSource.OperateCallback<BookSimInfo> callback) {
        mBooksRepository.deleteBook(id, callback);
    }

    @Override
    public void changeState(String id, int state, BooksDataSource.OperateCallback<BookSimInfo> callback) {
        mBooksRepository.changeBookState(id, state, callback);
    }

    @Override
    public void changeLayout(MenuItem view) {
        if (mSharedPreferences == null) {
            mSharedPreferences = mContext.getSharedPreferences(Config.SHARE_PREFERENCE, Context.MODE_PRIVATE);
        }
        boolean isLinear = mSharedPreferences.getBoolean(Config.LAYOUT_STATE, true);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        if (isLinear) {
            mShelfView.change2Stagger(view);
            editor.putBoolean(Config.LAYOUT_STATE, false);
        } else {
            mShelfView.change2Linear(view);
            editor.putBoolean(Config.LAYOUT_STATE, true);
        }
        editor.commit();
    }

    @Override
    public boolean getLayoutState() {
        if (mSharedPreferences == null) {
            mSharedPreferences = mContext.getSharedPreferences(Config.SHARE_PREFERENCE, Context.MODE_PRIVATE);
        }
        return mSharedPreferences.getBoolean(Config.LAYOUT_STATE, true);
    }
}
