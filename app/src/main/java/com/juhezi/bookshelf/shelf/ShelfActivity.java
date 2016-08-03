package com.juhezi.bookshelf.shelf;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.juhezi.bookshelf.R;

import javax.inject.Inject;

public class ShelfActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
//    @Inject ShelfPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shelf_act);

        initToolBar();
        
        initNavDrawer();

        initFragment();

        initPresenter();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_shelf);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    private void initNavDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.dl_layout);
        mDrawerLayout.setStatusBarBackground(R.color.colorPrimaryDark);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }

    private void initFragment() {
        ShelfFragment shelfFragment = (ShelfFragment) getSupportFragmentManager()
                .findFragmentById(R.id.rl_frag);
        if (shelfFragment == null) {
            shelfFragment = new ShelfFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.rl_frag,shelfFragment)
                    .commit();
        }
    }

    private void initPresenter() {

    }

    private void setupDrawerContent(NavigationView navigationView) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
