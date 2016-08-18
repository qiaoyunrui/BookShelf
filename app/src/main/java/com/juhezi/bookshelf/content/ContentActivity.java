package com.juhezi.bookshelf.content;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.juhezi.bookshelf.BookShelfApplication;
import com.juhezi.bookshelf.R;
import com.juhezi.bookshelf.data.BooksDataSource;
import com.juhezi.bookshelf.dataModule.BookInfo;
import com.juhezi.bookshelf.other.Config;
import com.juhezi.juprogressbar.View.JuProgressbar;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by qiaoyunrui on 16-8-18.
 */
public class ContentActivity extends AppCompatActivity implements ContentContract.View {

    private static final String TAG = "ContentActivity";

    private CollapsingToolbarLayout mCTLayout;
    private Toolbar mTbTitle;
    private ImageView mImgCover;
    private RatingBar mRbRating;
    private TextView mTvRating;
    private TextView mTvSummary;
    private TextView mTvAuthor;
    private TextView mTvTranslator;
    private TextView mTvPages;
    private TextView mTvPublisher;
    private TextView mTvPubDate;
    private TextView mTvTags;
    private TextView mTvCatalog;
    private RelativeLayout mRlDoubanAlt;
    private JuProgressbar mJpProgress;
    private ActionBar mActionBar;

    private String doubanAltPath;

    @Inject
    ContentPresenter contentPresenter;
    private ContentContract.Presenter mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_act);
        initView();
        initEvent();
        initToolbar();
        DaggerContentComponent.builder()
                .booksRepositoryComponent(((BookShelfApplication) getApplication())
                        .getmBooksRepositoryComponent())
                .contentPresenterMoudle(new ContentPresenterMoudle(this))
                .build()
                .inject(this);
    }

    private void initView() {
        mCTLayout = (CollapsingToolbarLayout) findViewById(R.id.ctl_layout);
        mTbTitle = (Toolbar) findViewById(R.id.tb_title);
        mImgCover = (ImageView) findViewById(R.id.img_content_cover);
        mRbRating = (RatingBar) findViewById(R.id.rb_douban_rating);
        mTvRating = (TextView) findViewById(R.id.tv_douban_rating_text);
        mTvSummary = (TextView) findViewById(R.id.tv_content_summary);
        mTvAuthor = (TextView) findViewById(R.id.tv_content_author);
        mTvTranslator = (TextView) findViewById(R.id.tv_content_translator);
        mTvPages = (TextView) findViewById(R.id.tv_content_pages);
        mTvPublisher = (TextView) findViewById(R.id.tv_content_publisher);
        mTvPubDate = (TextView) findViewById(R.id.tv_content_pubdate);
        mTvTags = (TextView) findViewById(R.id.tv_content_tag);
        mTvCatalog = (TextView) findViewById(R.id.tv_content_catalog);
        mRlDoubanAlt = (RelativeLayout) findViewById(R.id.rl_douban_alt);
        mJpProgress = (JuProgressbar) findViewById(R.id.jp_progress);
    }

    @Override
    public void showContent(BookInfo bookInfo) {
        mCTLayout.setTitle(bookInfo.getTitle());
        Glide.with(this)
                .load(bookInfo.getImages().getLarge())
                .error(R.drawable.book_temp_icon)
                .crossFade()
                .into(mImgCover);
        mRbRating.setRating(Float.parseFloat(bookInfo.getRating().getAverage()) / 2);
        mTvRating.setText(bookInfo.getRating().getAverage());
        mTvSummary.setText(bookInfo.getSummary());
        mTvAuthor.setText(getSumString(bookInfo.getAuthor()));
        mTvTranslator.setText(getSumString(bookInfo.getTranslator()));
        mTvPages.setText(bookInfo.getPages());
        mTvPublisher.setText(bookInfo.getPublisher());
        mTvPubDate.setText(bookInfo.getPubdate());
        String sumTag = "";
        for (int i = 0; i < bookInfo.getTags().size(); i++) {
            sumTag += (bookInfo.getTags().get(i).getName() + " ");
        }
        mTvTags.setText(sumTag);
        mTvCatalog.setText(bookInfo.getCatalog());
        doubanAltPath = bookInfo.getAlt();
    }

    private String getSumString(List<String> list) {
        String str = "";
        for (int i = 0; i < list.size(); i++) {
            str += (list.get(i) + " ");
        }
        return str;
    }

    private void initToolbar() {
        setSupportActionBar(mTbTitle);
        mActionBar = getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initEvent() {
        mRlDoubanAlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                Uri uri = Uri.parse(doubanAltPath);
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mPresenter != null) {
            mPresenter.start();
            if (getIntent() != null) {
                String isbn = getIntent().getStringExtra(Config.ISBN);
                mPresenter.getData(isbn);
            }
        }
    }

    @Override
    public void showProgressBar() {
        mJpProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        mJpProgress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorToast() {
        Toast.makeText(ContentActivity.this, "数据加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPresenter(ContentContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
