package com.example.huqiang.course.mvp.book.suspense;

import com.example.huqiang.course.data.book.Subject;;
import com.example.huqiang.course.mvp.BasePresenter;
import com.example.huqiang.course.mvp.BaseView;

import java.util.List;

/**
 * Created by 49988 on 2018/6/7.
 */

public interface BookSuspenseContract {
    interface Presenter extends BasePresenter {
        void getSuspenseBook();
    }

    interface View extends BaseView<BookSuspenseContract.Presenter> {
        void showError();

        void setLoadingIndicator(boolean active);

        void showSuspenseBook(List<Subject> mSubjects);
    }
}
