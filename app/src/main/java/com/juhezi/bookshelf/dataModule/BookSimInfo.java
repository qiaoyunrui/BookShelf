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
public class BookSimInfo extends RealmObject {

    private static final String TAG = "BookSimInfo";

    public static final int START = 0;
    public static final int MEDIUM = 1;
    public static final int END = 2;

    @PrimaryKey
    private String id;
    @Index
    private String isbn;
    @Index
    private String title;
    private String author;
    private String imageUrl;
    private int iState;
    private String desc;

    public BookSimInfo() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
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

    public int getiState() {
        return iState;
    }

    public void setiState(int iState) {
        this.iState = iState;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
