package com.juhezi.bookshelf.shelf;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juhezi.bookshelf.R;
import com.juhezi.bookshelf.dataModule.BookSimInfo;
import com.juhezi.bookshelf.dataModule.BookState;

import java.util.ArrayList;
import java.util.List;

import static com.juhezi.bookshelf.dataModule.BookState.END;
import static com.juhezi.bookshelf.dataModule.BookState.MEDIUM;
import static com.juhezi.bookshelf.dataModule.BookState.START;

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
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shelf_item, parent, false);
        BookViewHodler viewHolder = new BookViewHodler(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BookViewHodler holder, int position) {
        if (dataList != null) {
            switch (dataList.get(position).getState()) {
                case START:
                    holder.mIvState.setImageResource(R.drawable.ic_state_undone);
                    break;
                case MEDIUM:
                    holder.mIvState.setImageResource(R.drawable.ic_state_medium);
                    break;
                case END:
                    holder.mIvState.setImageResource(R.drawable.ic_state_done);
                    break;
            }
            holder.mTvTitle.setText(dataList.get(position).getTitle());
            holder.mTvAuthor.setText(dataList.get(position).getAuthor());
            holder.mTvDesc.setText(dataList.get(position).getDesc());
            Glide.with(holder.mImgCover.getContext())
                    .load(dataList
                            .get(position)
                            .getImageUrl())
                    .into(holder.mImgCover);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public List<BookSimInfo> getDataList() {
        return dataList;
    }

    public void setDataList(List<BookSimInfo> dataList) {
        this.dataList = dataList;
    }

    public class BookViewHodler extends RecyclerView.ViewHolder {

        public ImageView mImgCover;
        public TextView mTvTitle;
        public TextView mTvAuthor;
        public TextView mTvDesc;
        public ImageButton mIbDelete;
        public ImageView mIvState;

        public BookViewHodler(View itemView) {
            super(itemView);
            mImgCover = (ImageView) itemView.findViewById(R.id.img_cover);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            mTvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
            mIbDelete = (ImageButton) itemView.findViewById(R.id.ib_delete);
            mIvState = (ImageView) itemView.findViewById(R.id.img_state);
        }

    }

}
