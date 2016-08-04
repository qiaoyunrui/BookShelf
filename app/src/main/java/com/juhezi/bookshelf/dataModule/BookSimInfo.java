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

    public BookSimInfo(String author, String imageUrl, String isbn, BookState state, String title) {
        this.author = author;
        this.imageUrl = imageUrl;
        this.isbn = isbn;
        this.state = state;
        this.title = title;
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
