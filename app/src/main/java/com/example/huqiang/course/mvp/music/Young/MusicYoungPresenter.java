package com.example.huqiang.course.mvp.music.Young;

import com.example.huqiang.course.data.music.Music;
import com.example.huqiang.course.data.source.remote.MusicRemoteDataSource;
import com.example.huqiang.course.mvp.music.Young.MusicYoungContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 49988 on 2018/6/7.
 */

public class MusicYoungPresenter implements MusicYoungContract.Presenter {

    private final MusicYoungContract.View mView;
    private final MusicRemoteDataSource mDataSource;
    private final CompositeDisposable disposable;

    public MusicYoungPresenter(MusicYoungContract.View mView, MusicRemoteDataSource mDataSource) {
        this.mView = mView;
        this.mDataSource = mDataSource;
        this.mView.setPresenter(this);
        disposable = new CompositeDisposable();
    }


    @Override
    public void getYoungMusic() {
        final Disposable d = mDataSource.getYoungMusic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Music>() {
                    @Override
                    public void onNext(Music value) {
                        mView.showYoungMusic(value.getMusics());
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError();
                        mView.setLoadingIndicator(false);
                    }

                    @Override
                    public void onComplete() {
                        mView.setLoadingIndicator(false);
                    }
                });
        disposable.add(d);
    }

    @Override
    public void onSubscribe() {
        getYoungMusic();
    }

    @Override
    public void unSubscribe() {
        disposable.clear();
    }

}