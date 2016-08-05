package com.juhezi.bookshelf.dataModule;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by qiaoyunrui on 16-8-5.
 */
public interface BookService {

    @GET("v2/book/isbn/{isbn}")
    Call<BookInfo> getBookInfo(@Path("isbn") String isbn);

}
