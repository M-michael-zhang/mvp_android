package com.example.huqiang.course.mvp.book.suspense;

import com.example.huqiang.course.data.book.Book;
import com.example.huqiang.course.data.source.remote.BookRemoteDataSource;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 49988 on 2018/6/7.
 */

public class BookSuspensePresenter implements BookSuspenseContract.Presenter {

    private final BookSuspenseContract.View mView;
    private final BookRemoteDataSource mDataSource;
    private final CompositeDisposable disposable;

    public BookSuspensePresenter(BookSuspenseContract.View mView, BookRemoteDataSource mDataSource) {
        this.mView = mView;
        this.mDataSource = mDataSource;
        this.mView.setPresenter(this);
        disposable = new CompositeDisposable();
    }


    @Override
    public void getSuspenseBook() {
        final Disposable d = mDataSource.getSuspenseBook()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<Book>() {
                    @Override
                    public void onNext(Book value) {
                        mView.showSuspenseBook(value.getBooks());
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
        getSuspenseBook();
    }

    @Override
    public void unSubscribe() {
        disposable.clear();
    }

}