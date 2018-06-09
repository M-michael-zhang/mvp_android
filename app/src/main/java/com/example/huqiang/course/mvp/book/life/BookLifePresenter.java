package com.example.huqiang.course.mvp.book.life;

import com.example.huqiang.course.data.book.Book;
import com.example.huqiang.course.data.source.remote.BookRemoteDataSource;
import com.example.huqiang.course.mvp.book.life.BookLifeContract;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 49988 on 2018/6/7.
 */

public class BookLifePresenter implements BookLifeContract.Presenter {

    private final BookLifeContract.View mView;
    private final BookRemoteDataSource mDataSource;
    private final CompositeDisposable disposable;

    public BookLifePresenter(BookLifeContract.View mView, BookRemoteDataSource mDataSource) {
        this.mView = mView;
        this.mDataSource = mDataSource;
        this.mView.setPresenter(this);
        disposable = new CompositeDisposable();
    }


    @Override
    public void getLifeBook() {
        final Disposable d = mDataSource.getLifeBook()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Book>() {
                    @Override
                    public void onNext(Book value) {
                        mView.showLifeBook(value.getBooks());
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
        getLifeBook();
    }

    @Override
    public void unSubscribe() {
        disposable.clear();
    }

}