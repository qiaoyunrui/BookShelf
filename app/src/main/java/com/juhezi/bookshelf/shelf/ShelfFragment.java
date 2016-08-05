package com.juhezi.bookshelf.shelf;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.juhezi.bookshelf.R;
import com.juhezi.bookshelf.dataModule.BookSimInfo;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiaoyunrui on 16-8-3.
 */
public class ShelfFragment extends Fragment implements ShelfContract.View {

    private static final String TAG = "ShelfFragment";

    public static final int ZXING_CODE = 123;
    private ShelfContract.Presenter mPresenter;
    private View rootView;
    private RecyclerView mRvList;
    private FloatingActionButton mFabShow;
    private SwipeRefreshLayout mSrl_refresh;
    private BookAdapter mAdapter;
    private AlertDialog.Builder mBuilder;

    private List<BookSimInfo> dataList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.shelf_frag, container, false);
        mRvList = (RecyclerView) rootView.findViewById(R.id.rv_list);
        mFabShow = (FloatingActionButton) rootView.findViewById(R.id.fab_add);
        mSrl_refresh = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_refresh);
        initEvent();
        initRecycle();
        initDialog();
        return rootView;
    }

    private void initDialog() {
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
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(layoutManager);
        mRvList.setHasFixedSize(true);
        mAdapter = new BookAdapter(dataList);
        mRvList.setAdapter(mAdapter);
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
        startActivityForResult(intent,ZXING_CODE);
    }

    @Override
    public void turn2ContentAct() {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void updateRecycler(List<BookSimInfo> list) {
        mAdapter.setDataList(list);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void requestPrimission() {

    }

    @Override
    public void showErrorToast() {
        Toast.makeText(getContext(), "数据记载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void post(Runnable r) {
        getActivity().runOnUiThread(r);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == ZXING_CODE) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                if(bundle == null) {
                    return;
                }
                if(bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    mPresenter.handleData(result);
                }
            }
        }
    }


}
