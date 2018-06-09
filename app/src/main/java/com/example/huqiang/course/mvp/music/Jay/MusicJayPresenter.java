package com.example.huqiang.course.mvp.music.Jay;

import com.example.huqiang.course.data.music.Music;
import com.example.huqiang.course.data.source.remote.MusicRemoteDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 49988 on 2018/6/7.
 */

public class MusicJayPresenter implements MusicJayContract.Presenter {

    private final MusicJayContract.View mView;
    private final MusicRemoteDataSource mDataSource;
    private final CompositeDisposable disposable;

    public MusicJayPresenter(MusicJayContract.View mView, MusicRemoteDataSource mDataSource) {
        this.mView = mView;
        this.mDataSource = mDataSource;
        this.mView.setPresenter(this);
        disposable = new CompositeDisposable();
    }


    @Override
    public void getJayMusic() {
        final Disposable d = mDataSource.getJayMusic()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Music>() {
                    @Override
                    public void onNext(Music value) {
                        mView.showJayMusic(value.getMusics());
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
        getJayMusic();
    }

    @Override
    public void unSubscribe() {
        disposable.clear();
    }

}