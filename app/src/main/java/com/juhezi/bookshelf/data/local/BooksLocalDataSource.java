package com.juhezi.bookshelf.data.local;

import android.content.Context;

import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.dataModule.BookSimInfo;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;
import io.realm.RealmResults;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
public class BooksLocalDataSource implements BooksDataSource{

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
    public void getBookSimInfos(LoadSimBooksCallback callback) {
        if (query == null) {
            query = mRealm.where(BookSimInfo.class);
        }
        RealmResults<BookSimInfo> result = query.findAll(); //可以优化为分页查询
        List<BookSimInfo> list = result.subList(0,result.size() - 1);
        callback.onSimBooksLoaded(list);
    }

    @Override
    public void refreshSimInfos(LoadSimBooksCallback callback) {

    }

    @Override
    public void saveBookInfo(BookSimInfo bookSimInfo, SaveSimBookCallback callback) {
        mRealm.beginTransaction();
        mRealm.copyToRealmOrUpdate(bookSimInfo);
        mRealm.commitTransaction();
        callback.onSaved();
    }
}
