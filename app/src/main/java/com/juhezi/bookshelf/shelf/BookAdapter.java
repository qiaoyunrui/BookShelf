package com.juhezi.bookshelf.shelf;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.bookshelf.dataModule.BookSimInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHodler> {

    private static final String TAG = "BookAdapter";
    private List<BookSimInfo> dataList = new ArrayList<>();

    public BookAdapter(List<BookSimInfo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public BookViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BookViewHodler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BookViewHodler extends RecyclerView.ViewHolder {

        public BookViewHodler(View itemView) {
            super(itemView);
        }

    }

    public List<BookSimInfo> getDataList() {
        return dataList;
    }

    public void setDataList(List<BookSimInfo> dataList) {
        this.dataList = dataList;
    }

}
