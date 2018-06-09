package com.example.huqiang.course.data;

import com.example.huqiang.course.data.music.Music;

import io.reactivex.Observable;

public interface MusicDataSource {
    Observable<Music> getYoungMusic();
    Observable<Music> getJayMusic();
}
