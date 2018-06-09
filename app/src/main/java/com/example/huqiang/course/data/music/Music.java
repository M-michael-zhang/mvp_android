package com.example.huqiang.course.data.music;

import java.util.List;

/**
 * Created by 49988 on 2018/6/7.
 */

public class Music {


    private int count;
    private int start;
    private int total;
    private List<Subject> musics;

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

    public List<Subject> getMusics() {
        return musics;
    }

    public void setMusics(List<Subject> musics) {
        this.musics = musics;
    }
}
