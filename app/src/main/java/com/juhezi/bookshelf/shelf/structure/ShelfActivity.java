package com.juhezi.bookshelf.shelf.structure;

import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.juhezi.bookshelf.R;
import com.juhezi.bookshelf.data.BooksRepository;
import com.juhezi.bookshelf.data.local.BooksLocalDataSource;
import com.juhezi.bookshelf.data.remote.BooksRemoteDataSource;

public class ShelfActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ShelfPresenter mPresenter;
    private ShelfFragment mShelfFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelf_act);

        initToolBar();
        
//        initNavDrawer();

        initFragment();

        initPresenter();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_shelf);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
//        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setLogo(R.drawable.ic_temp1);
    }

    /*private void initNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }*/

    private void initFragment() {
        mShelfFragment = (ShelfFragment) getSupportFragmentManager()
                .findFragmentById(R.id.rl_frag);
        if (mShelfFragment == null) {
            mShelfFragment = new ShelfFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.rl_frag,mShelfFragment)
                    .commit();
        }
    }

    private void initPresenter() {
        BooksRepository booksRepository = BooksRepository.newInstance(
                BooksLocalDataSource.newInstance(this),
                BooksRemoteDataSource.newInstance(this));
        mPresenter = new ShelfPresenter(mShelfFragment,booksRepository);
    }

    private void setupDrawerContent(NavigationView navigationView) {
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }*/
}
