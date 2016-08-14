package com.juhezi.bookshelf.shelf.structure;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.MenuItem;
import android.view.View;

import com.juhezi.bookshelf.R;
import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.data.BooksRepository;
import com.juhezi.bookshelf.dataModule.BookInfo;
import com.juhezi.bookshelf.dataModule.BookService;
import com.juhezi.bookshelf.dataModule.BookSimInfo;
import com.juhezi.bookshelf.other.Config;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        this.mContext = context;
        this.mShelfView = shelfView;
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
        load();
    }

    @Override
    public void load() {
        refresh();
    }

    @Override
    public void refresh() {
        mShelfView.showProgressbar();
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
                        //Recycler动画
                        mShelfView.recyclerViewAdd(bookSimInfo);
                        mShelfView.recyclerViewScrollTop();
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
    public void handleData(String isbn) {
        Call<BookInfo> call = mBookService.getBookInfo(isbn);
        final BookSimInfo[] bookSimInfo = {null};
        call.enqueue(new Callback<BookInfo>() {
            @Override
            public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
                if (response.code() == 200) {
                    bookSimInfo[0] = response.body().toBookSimInfo();
                    if (mBooksRepository.isRepeat(bookSimInfo[0].getIsbn())) {
                        mShelfView.showSnackbar(ShelfFragment.REPEAT);
                        mShelfView.hideProgressbar();
                    } else {
                        saveData(bookSimInfo[0]);
                        mShelfView.showSnackbar(ShelfFragment.SUCCESS);
                        mShelfView.hideProgressbar();
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
    public void deleteData(String id) {
        mBooksRepository.deleteBook(id);
    }

    @Override
    public void changeState(String id, int state) {
        mBooksRepository.changeState(id, state);
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
