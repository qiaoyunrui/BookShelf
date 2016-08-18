package com.juhezi.bookshelf.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.juhezi.bookshelf.R;

/**
 * Created by qiaoyunrui on 16-8-18.
 */
public class ContentActivity extends AppCompatActivity {

    private static final String TAG = "ContentActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_act);
    }

}
