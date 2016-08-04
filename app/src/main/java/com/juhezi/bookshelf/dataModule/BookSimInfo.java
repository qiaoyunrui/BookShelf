package com.juhezi.bookshelf.dataModule;

import java.lang.reflect.Type;
import java.util.Random;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.Index;
import io.realm.annotations.PrimaryKey;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
public class BookSimInfo extends RealmObject{

    private static final String TAG = "BookSimInfo";

    @PrimaryKey
    private String id;
    @Index
    private String isbn;
    @Index
    private String title;
    private String author;
    private String imageUrl;
    @Ignore
    private BookState state;    //阅读进度
    private int iState;
    private String desc;

    public BookSimInfo() {
        id = UUID.randomUUID().toString();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookState getState() {
        return state;
    }

    public void setState(BookState state) {
        this.state = state;
        iState = state2int(state);
    }

    private int state2int(BookState state) {
        int iState = 0;
        switch(state) {
            case START:
                iState = 0;
                break;
            case MEDIUM:
                iState = 1;
                break;
            case END:
                iState = 2;
                break;
        }
        return iState;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
