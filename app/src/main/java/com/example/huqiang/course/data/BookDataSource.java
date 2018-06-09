package com.example.huqiang.course.data;

import io.reactivex.Observable;

import com.example.huqiang.course.data.book.Book;

/**
 * Created by 49988 on 2018/6/7.
 */

public interface BookDataSource {
    Observable<Book> getSuspenseBook();
    Observable<Book> getLifeBook();

}
