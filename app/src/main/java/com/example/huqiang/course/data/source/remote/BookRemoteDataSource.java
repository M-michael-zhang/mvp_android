package com.example.huqiang.course.data.source.remote;

import com.example.huqiang.course.data.BookDataSource;
import com.example.huqiang.course.data.book.Book;
import com.example.huqiang.course.retrofit.ApiService;
import com.example.huqiang.course.retrofit.RetrofitClient;

import io.reactivex.Observable;

/**
 * Created by 49988 on 2018/6/7.
 */

public class BookRemoteDataSource implements BookDataSource {
    private static BookRemoteDataSource mDataSource;

    public static BookRemoteDataSource getInstance() {
        if (mDataSource == null) {
            mDataSource = new BookRemoteDataSource();
        }
        return mDataSource;
    }

    @Override
    public Observable<Book> getSuspenseBook() {
        return RetrofitClient.getInstance()
                .create(ApiService.class)
                .getSuspenseBook();
    }

    public Observable<Book> getLifeBook() {
        return RetrofitClient.getInstance()
                .create(ApiService.class)
                .getLifeBook();
    }
}
