package com.juhezi.bookshelf.shelf.structure;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.juhezi.bookshelf.R;
import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.dataModule.BookSimInfo;
import com.juhezi.bookshelf.other.Config;
import com.juhezi.bookshelf.shelf.BookAdapter;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public class ShelfFragment extends Fragment implements ShelfContract.View {

    private static final String TAG = "ShelfFragment";

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int REPEAT = 2;

    public static final int ZXING_CODE = 123;
    private ShelfContract.Presenter mPresenter;
    private View rootView;
    private RecyclerView mRvList;
    private FloatingActionButton mFabShow;
    private SwipeRefreshLayout mSrl_refresh;
    private RelativeLayout mEmptyView;
    private BookAdapter mAdapter;
    private AlertDialog.Builder mBuilder;
    private String id;
    private int pos;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    private LinearLayoutManager linearLayoutManager;

    private List<BookSimInfo> dataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.shelf_frag, container, false);
        mRvList = (RecyclerView) rootView.findViewById(R.id.rv_list);
        mFabShow = (FloatingActionButton) rootView.findViewById(R.id.fab_add);
        mSrl_refresh = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_refresh);
        mSrl_refresh.setColorSchemeColors(getResources().getColor(R.color.yellow),
                getResources().getColor(R.color.blue),
                getResources().getColor(R.color.red));
        mEmptyView = (RelativeLayout) rootView.findViewById(R.id.rl_empty);
        initEvent();
        initRecycle();
        initDialog();
        return rootView;
    }

    private void initDialog() {
        mBuilder = new AlertDialog.Builder(getContext())
                .setTitle("是否删除?")
                .setMessage("删除之后无法恢复")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mPresenter.deleteData(id, new BooksDataSource.OperateCallback<BookSimInfo>() {
                            @Override
                            public void complete(BookSimInfo bookSimInfo) {
                                mAdapter.delete(pos, new BookAdapter.Callback() {
                                    @Override
                                    public void onCompleteTask() {
                                        showEmptyView();
                                    }
                                });
                            }

                            @Override
                            public void error() {
                                showErrorToast();
                            }
                        });

                    }
                })
                .setNegativeButton("取消", null);
    }

    private void initEvent() {
        mFabShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                turn2ZXingAct();
            }
        });
        mSrl_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.refresh();
            }
        });
    }

    private void initRecycle() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        int spanCount = 2;  //列数为2列
        staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        if (mPresenter.getLayoutState()) {
            mRvList.setLayoutManager(linearLayoutManager);
        } else {
            mRvList.setLayoutManager(staggeredGridLayoutManager);
        }
        mAdapter = new BookAdapter(dataList);
        mAdapter.setItemListener(new BookAdapter.BookItemListener() {
            @Override
            public void onItemClick(BookSimInfo bookSimInfo) {
                turn2ContentAct(bookSimInfo);
            }

            @Override
            public void onItemDeleteListener(BookSimInfo bookSimInfo, int position) {
                id = bookSimInfo.getId();
                pos = position;
                showDialog();
            }

            @Override
            public void onItemChangeStateListener(BookSimInfo bookSimInfo, int state) {
                mPresenter.changeState(bookSimInfo.getId(), state, new BooksDataSource.OperateCallback<BookSimInfo>() {
                    @Override
                    public void complete(BookSimInfo bookSimInfo) {

                    }

                    @Override
                    public void error() {

                    }
                });
            }

        });
        mRvList.setAdapter(mAdapter);
//        mRvList.setHasFixedSize(true);
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

    @Override
    public void showProgressbar() {
        mSrl_refresh.setRefreshing(true);
    }

    @Override
    public void hideProgressbar() {
        mSrl_refresh.setRefreshing(false);
    }

    @Override
    public void turn2ZXingAct() {
        Intent intent = new Intent(getContext(), CaptureActivity.class);
        startActivityForResult(intent, ZXING_CODE);
    }

    @Override
    public void turn2ContentAct(BookSimInfo bookSimInfo) {

    }

    @Override
    public void showDialog() {
        mBuilder.create().show();
    }

    @Override
    public void updateRecycler(List<BookSimInfo> list) {
        mAdapter.setDataList(list);
        mAdapter.notifyDataSetChanged();
        if (mAdapter.getItemCount() == 0) {
            showEmptyView();
        } else {
            hideEmptyView();
        }
    }

    @Override
    public void requestPrimission() {
        if (!EasyPermissions.hasPermissions(getContext(), Manifest.permission.CAMERA)) {
            EasyPermissions.requestPermissions(getContext(),
                    Config.CAMERA_PRIMISSION_rationale,
                    0x123,
                    Manifest.permission.CAMERA);
        }

    }

    @Override
    public void showErrorToast() {
        Toast.makeText(getContext(), "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSnackbar(int type) {
        switch (type) {
            case SUCCESS:
                Snackbar.make(getView(), "添加书籍成功", Snackbar.LENGTH_SHORT)
                        .setAction("查看", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                mRvList.scrollToPosition(0);
                            }
                        })
                        .show();
                break;
            case FAIL:
                Snackbar.make(getView(), "添加书籍失败", Snackbar.LENGTH_SHORT).show();
                break;
            case REPEAT:
                Snackbar.make(getView(), "书籍已经存在", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void recyclerViewAdd(BookSimInfo bookSimInfo) {
        mAdapter.add(bookSimInfo, new BookAdapter.Callback() {
            @Override
            public void onCompleteTask() {
                hideEmptyView();
            }
        });
    }

    @Override
    public void recyclerViewScrollTop() {
        mRvList.scrollToPosition(0);
    }

    @Override
    public void change2Linear(MenuItem view) {
        view.setIcon(R.drawable.ic_linear);
        mRvList.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void change2Stagger(MenuItem view) {
        view.setIcon(R.drawable.ic_stagger);
        mRvList.setLayoutManager(staggeredGridLayoutManager);
    }

    @Override
    public void showEmptyView() {
        mEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        mEmptyView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ZXING_CODE) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    mPresenter.handleData(result);
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

}
