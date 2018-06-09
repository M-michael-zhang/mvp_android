package com.example.huqiang.course.mvp.book.life;

import com.example.huqiang.course.data.book.Subject;;
import com.example.huqiang.course.mvp.BasePresenter;
import com.example.huqiang.course.mvp.BaseView;

import java.util.List;

/**
 * Created by 49988 on 2018/6/7.
 */

public interface BookLifeContract {
    interface Presenter extends BasePresenter {
        void getLifeBook();
    }

    interface View extends BaseView<BookLifeContract.Presenter> {
        void showError();

        void setLoadingIndicator(boolean active);

        void showLifeBook(List<Subject> mSubjects);
    }
}
