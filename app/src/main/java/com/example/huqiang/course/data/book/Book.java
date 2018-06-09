package com.example.huqiang.course.data.book;

import java.util.List;

/**
 * Created by 49988 on 2018/6/7.
 */

public class Book {


    private int count;
    private int start;
    private int total;
    private List<Subject> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Subject> getBooks() {
        return books;
    }

    public void setBooks(List<Subject> books) {
        this.books = books;
    }
}
