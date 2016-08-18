package com.juhezi.bookshelf.content;

import android.content.Intent;
import android.util.Log;

import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.data.BooksRepository;
import com.juhezi.bookshelf.dataModule.BookInfo;
import com.juhezi.bookshelf.dataModule.BookService;
import com.juhezi.bookshelf.dataModule.BookSimInfo;
import com.juhezi.bookshelf.other.Config;
import com.juhezi.bookshelf.shelf.structure.ShelfFragment;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by qiaoyunrui on 16-8-18.
 */
public class ContentPresenter implements ContentContract.Presenter {

    private static final String TAG = "ContentPresenter";
    private ContentContract.View mContentView;
    private BooksRepository mRepository;
    private BookService mBookService;
    private Retrofit mRetrofit;

    @Inject
    public ContentPresenter(ContentContract.View view, BooksRepository repository) {
        mContentView = view;
        mRepository = repository;
        mContentView.setPresenter(this);
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Config.DOUBAN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mBookService = mRetrofit.create(BookService.class);
    }

    @Override
    public void openOnDouban() {

    }

    @Override
    public void getData(String isbn) {
        Call<BookInfo> call = mBookService.getBookInfo(isbn);
        call.enqueue(new Callback<BookInfo>() {
            @Override
            public void onResponse(Call<BookInfo> call, Response<BookInfo> response) {
                if (response.code() == 200) {
                    mContentView.showContent(response.body());
                } else {
                    mContentView.showErrorToast();
                }
                mContentView.hideProgressBar();
            }

            @Override
            public void onFailure(Call<BookInfo> call, Throwable t) {
                mContentView.showErrorToast();
                mContentView.hideProgressBar();
            }
        });
    }


    @Override
    public void start() {
        mContentView.showProgressBar();
    }
}
