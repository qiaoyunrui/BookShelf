package com.juhezi.bookshelf.shelf;

import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.juhezi.bookshelf.R;
import com.juhezi.bookshelf.dataModule.BookSimInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHodler> {

    private static final String TAG = "BookAdapter";
    private List<BookSimInfo> dataList = new ArrayList<>();
    private BookItemListener mListener;

    public BookAdapter(List<BookSimInfo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public BookViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shelf_item, parent, false);
        BookViewHodler viewHolder = new BookViewHodler(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final BookViewHodler holder, final int position) {
        if (dataList != null) {
            switch (dataList.get(position).getiState()) {
                case BookSimInfo.START:
                    holder.mIvState.setImageResource(R.drawable.ic_state_undone);
                    break;
                case BookSimInfo.MEDIUM:
                    holder.mIvState.setImageResource(R.drawable.ic_state_medium);
                    break;
                case BookSimInfo.END:
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
                    .error(R.drawable.book_temp_icon)
                    .crossFade()
                    .into(holder.mImgCover);
            if (mListener != null) {
                holder.mIbDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onItemDeleteListener(dataList.get(position), position);
                    }
                });
                holder.mSwipeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onItemClick(dataList.get(position));
                    }
                });
                holder.mRl_undone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onItemChangeStateListener(dataList.get(position), BookSimInfo.START);
                        holder.mIvState.setImageResource(R.drawable.ic_state_undone);
                        holder.mSwipeLayout.hideActionView();
                    }
                });
                holder.mRl_medium.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onItemChangeStateListener(dataList.get(position), BookSimInfo.MEDIUM);
                        holder.mIvState.setImageResource(R.drawable.ic_state_medium);
                        holder.mSwipeLayout.hideActionView();
                    }
                });
                holder.mRl_done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.onItemChangeStateListener(dataList.get(position), BookSimInfo.END);
                        holder.mIvState.setImageResource(R.drawable.ic_state_done);
                        holder.mSwipeLayout.hideActionView();
                    }
                });
            }
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
        this.dataList.clear();
        this.dataList.addAll(dataList);
    }

    public void setItemListener(BookItemListener listener) {
        this.mListener = listener;
    }

    public void delete(int position, Callback callback) {
        dataList.remove(position);
        notifyItemRemoved(position);
        if (dataList.size() == 0) { //列表里已经没有书籍
            callback.onCompleteTask();
        }
    }

    public void add(BookSimInfo bookSimInfo, Callback callback) {
        dataList.add(0, bookSimInfo);
        if (dataList.size() == 1) {
            callback.onCompleteTask();
        }
        notifyItemInserted(0);
    }

    public void clear() {
        dataList.clear();
        notifyDataSetChanged();
    }

    public class BookViewHodler extends RecyclerView.ViewHolder {

        public ImageView mImgCover;
        public TextView mTvTitle;
        public TextView mTvAuthor;
        public TextView mTvDesc;
        public ImageView mIbDelete;
        public ImageView mIvState;
        public RelativeLayout mRl_done;
        public RelativeLayout mRl_medium;
        public RelativeLayout mRl_undone;
        public SwipeLayout mSwipeLayout;

        public BookViewHodler(View itemView) {
            super(itemView);
            mImgCover = (ImageView) itemView.findViewById(R.id.img_cover);
            mTvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            mTvAuthor = (TextView) itemView.findViewById(R.id.tv_author);
            mTvDesc = (TextView) itemView.findViewById(R.id.tv_desc);
            mIbDelete = (ImageView) itemView.findViewById(R.id.iv_delete);
            mIvState = (ImageView) itemView.findViewById(R.id.img_state);
            mRl_undone = (RelativeLayout) itemView.findViewById(R.id.rl_undone);
            mRl_medium = (RelativeLayout) itemView.findViewById(R.id.rl_medium);
            mRl_done = (RelativeLayout) itemView.findViewById(R.id.rl_done);
            mSwipeLayout = (SwipeLayout) itemView.findViewById(R.id.sl_layout);
        }

    }

    public interface BookItemListener {
        void onItemClick(BookSimInfo bookSimInfo);

        void onItemDeleteListener(BookSimInfo bookSimInfo, int position);

        void onItemChangeStateListener(BookSimInfo bookSimInfo, int state);
    }

    public interface Callback {
        void onCompleteTask();
    }

}
