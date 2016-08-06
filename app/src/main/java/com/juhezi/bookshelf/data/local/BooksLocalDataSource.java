package com.juhezi.bookshelf.data.local;

import android.content.Context;
import android.util.Log;

import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.dataModule.BookSimInfo;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
public class BooksLocalDataSource implements BooksDataSource {

    private static final String TAG = "BooksLocalDataSource";

    private Context mContext;

    private Realm mRealm;
    private RealmQuery<BookSimInfo> query;

    private static BooksLocalDataSource sDataSource;

    public BooksLocalDataSource(Context context) {
        this.mContext = context;
        mRealm = Realm.getDefaultInstance();
    }

    public static BooksLocalDataSource newInstance(Context context) {
        if (sDataSource == null) {
            sDataSource = new BooksLocalDataSource(context);
        }
        return sDataSource;
    }

    @Override
    public boolean isRepeat(String isbn) {
        RealmQuery<BookSimInfo> repeatQuery = mRealm.where(BookSimInfo.class);
        repeatQuery.equalTo("isbn", isbn);
        RealmResults<BookSimInfo> result = repeatQuery.findAll();
        return result.size() > 0;
    }

    @Override
    public void getBookSimInfos(LoadSimBooksCallback callback) {
        refreshSimInfos(callback);
    }

    @Override
    public void refreshSimInfos(LoadSimBooksCallback callback) {
        if (query == null) {
            query = mRealm.where(BookSimInfo.class);
        }
        RealmResults<BookSimInfo> result = query.findAll(); //可以优化为分页查询
        List<BookSimInfo> list = new ArrayList<>(result.subList(0, result.size()));
        callback.onSimBooksLoaded(list);
    }

    @Override
    public void saveBookInfo(BookSimInfo bookSimInfo, OperateCallback callback) {
        if (bookSimInfo != null) {
            mRealm.beginTransaction();
            mRealm.copyToRealmOrUpdate(bookSimInfo);
            mRealm.commitTransaction();
            callback.complete();
        } else {
            callback.error();
        }

    }

    @Override
    public void deleteBook(String id) {
        RealmQuery<BookSimInfo> repeatQuery = mRealm.where(BookSimInfo.class);
        repeatQuery.equalTo("id", id);
        RealmResults<BookSimInfo> result = repeatQuery.findAll();
        mRealm.beginTransaction();
        result.get(0).deleteFromRealm();
        mRealm.commitTransaction();
    }

    @Override
    public void changeState(String id, int state) {
        RealmQuery<BookSimInfo> repeatQuery = mRealm.where(BookSimInfo.class);
        repeatQuery.equalTo("id", id);
        RealmResults<BookSimInfo> result = repeatQuery.findAll();
        mRealm.beginTransaction();
        result.get(0).setiState(state);
        mRealm.commitTransaction();
    }
}
