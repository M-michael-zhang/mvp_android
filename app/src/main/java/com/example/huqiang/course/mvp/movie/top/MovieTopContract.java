package com.example.huqiang.course.mvp.movie.top;

import com.example.huqiang.course.data.movie.Subject;
import com.example.huqiang.course.mvp.BasePresenter;
import com.example.huqiang.course.mvp.BaseView;

import java.util.List;

public interface MovieTopContract {
    interface Presenter extends BasePresenter {
        void getTopMovie();
    }

    interface View extends BaseView<Presenter> {
        void showError();

        void setLoadingIndicator(boolean active);

        void showTopMovie(List<Subject> subjects);
    }
}
