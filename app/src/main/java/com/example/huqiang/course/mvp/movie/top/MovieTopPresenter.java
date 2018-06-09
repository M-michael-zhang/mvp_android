package com.example.huqiang.course.mvp.movie.top;

import com.example.huqiang.course.data.movie.Movie;
import com.example.huqiang.course.data.source.remote.MovieRemoteDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieTopPresenter implements MovieTopContract.Presenter {

    private final MovieTopContract.View mView;
    private final MovieRemoteDataSource mDataSource;
    private final CompositeDisposable disposable;

    public MovieTopPresenter(MovieTopContract.View mView, MovieRemoteDataSource mDataSource) {
        this.mView = mView;
        this.mDataSource = mDataSource;
        this.mView.setPresenter(this);
        disposable = new CompositeDisposable();
    }


    @Override
    public void getTopMovie() {
        final Disposable d = mDataSource.getTopMovie()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Movie>() {
                    @Override
                    public void onNext(Movie value) {
                        mView.showTopMovie(value.getSubjects());
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
        getTopMovie();
    }

    @Override
    public void unSubscribe() {
        disposable.clear();
    }
}
