package com.juhezi.bookshelf.dataModule;

/**
 * Created by qiaoyunrui on 16-8-4.
 */
public class BookSimInfo {

    private static final String TAG = "BookSimInfo";

    private String isbn;
    private String title;
    private String author;
    private String imageUrl;
    private BookState state;    //阅读进度
    private String desc;

    public BookSimInfo(String title, BookState state, String isbn, String imageUrl, String desc, String author) {
        this.title = title;
        this.state = state;
        this.isbn = isbn;
        this.imageUrl = imageUrl;
        this.desc = desc;
        this.author = author;
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
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
