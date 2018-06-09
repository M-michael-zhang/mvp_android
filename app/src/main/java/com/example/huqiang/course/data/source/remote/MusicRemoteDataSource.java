package com.example.huqiang.course.data.source.remote;

import com.example.huqiang.course.data.MusicDataSource;
import com.example.huqiang.course.data.music.Music;
import com.example.huqiang.course.retrofit.ApiService;
import com.example.huqiang.course.retrofit.RetrofitClient;

import io.reactivex.Observable;

/**
 * Created by 49988 on 2018/6/7.
 */

public class MusicRemoteDataSource implements MusicDataSource {
    private static MusicRemoteDataSource mDataSource;

    public static MusicRemoteDataSource getInstance() {
        if (mDataSource == null) {
            mDataSource = new MusicRemoteDataSource();
        }
        return mDataSource;
    }

    @Override
    public Observable<Music> getYoungMusic() {
        return RetrofitClient.getInstance()
                .create(ApiService.class)
                .getYoungMusic();
    }

    public Observable<Music> getJayMusic() {
        return RetrofitClient.getInstance()
                .create(ApiService.class)
                .getJayMusic();
    }
}
