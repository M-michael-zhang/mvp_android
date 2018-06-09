package com.example.huqiang.course.mvp.music.Jay;

import com.example.huqiang.course.data.music.Subject;;
import com.example.huqiang.course.mvp.BasePresenter;
import com.example.huqiang.course.mvp.BaseView;

import java.util.List;

/**
 * Created by 49988 on 2018/6/7.
 */

public interface MusicJayContract {
    interface Presenter extends BasePresenter {
        void getJayMusic();
    }

    interface View extends BaseView<MusicJayContract.Presenter> {
        void showError();

        void setLoadingIndicator(boolean active);

        void showJayMusic(List<Subject> mSubjects);
    }
}
