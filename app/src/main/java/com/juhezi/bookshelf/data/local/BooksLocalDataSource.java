package com.juhezi.bookshelf.data.local;

import android.content.Context;
import android.util.Log;

import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.dataModule.BookSimInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
@Singleton
public class BooksLocalDataSource implements BooksDataSource {

    private static final String TAG = "BooksLocalDataSource";

    private Context mContext;

    private Realm mRealm;

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

    /*@Override
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


    @Override
    public void refreshSimInfos(LoadSimBooksCallback callback) {
        if (query == null) {
            query = mRealm.where(BookSimInfo.class);
        }
        RealmResults<BookSimInfo> result = query.findAllSorted("time", Sort.DESCENDING); //可以优化为分页查询
        List<BookSimInfo> list = new ArrayList<>(result.subList(0, result.size()));
        callback.onSimBooksLoaded(list);
    }
    */


    @Override
    public Observable<List<BookSimInfo>> getBooks() {
        return mRealm.where(BookSimInfo.class)
                .findAllSorted("time", Sort.DESCENDING)
                .asObservable()
                .map(new Func1<RealmResults<BookSimInfo>, List<BookSimInfo>>() {
                    @Override
                    public List<BookSimInfo> call(RealmResults<BookSimInfo> bookSimInfos) {
                        return new ArrayList<BookSimInfo>(bookSimInfos.subList(0, bookSimInfos.size()));

                    }
                });
    }

    @Override
    public Observable<BookSimInfo> getBookByIsbn(String isbn) {
        return mRealm.where(BookSimInfo.class)
                .equalTo("isbn", isbn)
                .findAll()
                .asObservable()
                .map(new Func1<RealmResults<BookSimInfo>, BookSimInfo>() {
                    @Override
                    public BookSimInfo call(RealmResults<BookSimInfo> bookSimInfos) {
                        if (bookSimInfos.size() == 0) {
                            return null;
                        } else {
                            return bookSimInfos.get(0);
                        }
                    }
                });
    }

    @Override
    public void saveBook(BookSimInfo bookSimInfo, OperateCallback<BookSimInfo> callback) {
        if (bookSimInfo != null) {
            mRealm.beginTransaction();
            mRealm.copyToRealmOrUpdate(bookSimInfo);
            mRealm.commitTransaction();
            callback.complete(bookSimInfo);
        } else {
            callback.error();
        }
    }

    @Override
    public void deleteBook(String id, OperateCallback<BookSimInfo> callback) {
        RealmQuery<BookSimInfo> repeatQuery = mRealm.where(BookSimInfo.class);
        repeatQuery.equalTo("id", id);
        RealmResults<BookSimInfo> result = repeatQuery.findAll();
        mRealm.beginTransaction();
        if (result.size() != 0) {
            result.get(0).deleteFromRealm();
        } else {
            callback.error();
        }
        mRealm.commitTransaction();
        callback.complete(result.get(0));
    }

    @Override
    public void deleteAllBooks(OperateCallback<BookSimInfo> callback) {
        RealmResults<BookSimInfo> result = mRealm.where(BookSimInfo.class)
                .findAll();
        mRealm.beginTransaction();
        result.deleteAllFromRealm();
        mRealm.commitTransaction();
        callback.complete(new BookSimInfo());
    }

    @Override
    public void changeBookState(String id, int state, OperateCallback<BookSimInfo> callback) {
        RealmQuery<BookSimInfo> query = mRealm.where(BookSimInfo.class);
        query.equalTo("id", id);
        RealmResults<BookSimInfo> result = query.findAll();
        mRealm.beginTransaction();
        if (result.size() != 0) {    //结果非空的时候执行操作
            result.get(0).setiState(state);
        } else {
            callback.error();
        }
        mRealm.commitTransaction();
        callback.complete(result.get(0));
    }

    @Override
    public boolean isRepeat(String isbn) {
        RealmQuery<BookSimInfo> repeatQuery = mRealm.where(BookSimInfo.class);
        repeatQuery.equalTo("isbn", isbn);
        RealmResults<BookSimInfo> result = repeatQuery.findAll();
        return result.size() > 0;
    }


}
