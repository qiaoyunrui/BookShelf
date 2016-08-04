package com.juhezi.bookshelf.shelf;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.juhezi.bookshelf.R;
import com.juhezi.bookshelf.dataModule.BookSimInfo;
import com.juhezi.bookshelf.dataModule.BookState;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public class ShelfFragment extends Fragment implements ShelfContract.View {

    private static final String TAG = "ShelfFragment";

    private ShelfContract.Presenter mPresenter;
    private View rootView;
    private RecyclerView mRvList;
    private FloatingActionButton mFabShow;
    private SwipeRefreshLayout mSrl_refresh;

    private List<BookSimInfo> dataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.shelf_frag, container, false);
        mRvList = (RecyclerView) rootView.findViewById(R.id.rv_list);
        mFabShow = (FloatingActionButton) rootView.findViewById(R.id.fab_add);
        mSrl_refresh = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_refresh);
        initRecycle();
        return rootView;
    }

    private void initRecycle() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(layoutManager);
        mRvList.setHasFixedSize(true);
        BookSimInfo temp = new BookSimInfo("Android开发艺术探索",
                BookState.MEDIUM,
                "7121269392",
                "https://img3.doubanio.com/lpic/s28283341.jpg",
                "《Android开发艺术探索》是一本Android进阶类书籍，采用理论、源码和实践相结合的方式来阐述高水准的Android应用开发要点。《Android开发艺术探索》从三个方面来组织内容。第一，介绍Android开发者不容易掌握的一些知识点；第二，结合Android源代码和应用层开发过程，融会贯通，介绍一些比较深入的知识点；第三，介绍一些核心技术和Android的性能优化思想。\n" +
                        "《Android开发艺术探索》侧重于Android知识的体系化和系统工作机制的分析，通过《Android开发艺术探索》的学习可以极大地提高开发者的Android技术水平，从而更加高效地成为高级开发者。而对于高级开发者来说，仍然可以从《Android开发艺术探索》的知识体系中获益。",
                "任玉刚");
        for (int i = 0; i < 10; i++) {
            dataList.add(temp);
        }
        BookAdapter adapter = new BookAdapter(dataList);
        mRvList.setAdapter(adapter);
    }

    @Override
    public void setPresenter(ShelfContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.start();
        }
    }

    @Override
    public void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }
}
