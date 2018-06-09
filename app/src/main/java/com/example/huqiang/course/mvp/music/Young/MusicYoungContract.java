package com.example.huqiang.course.mvp.music.Young;

import com.example.huqiang.course.data.music.Subject;;
import com.example.huqiang.course.mvp.BasePresenter;
import com.example.huqiang.course.mvp.BaseView;

import java.util.List;

/**
 * Created by 49988 on 2018/6/7.
 */

public interface MusicYoungContract {
    interface Presenter extends BasePresenter {
        void getYoungMusic();
    }

    interface View extends BaseView<MusicYoungContract.Presenter> {
        void showError();

        void setLoadingIndicator(boolean active);

        void showYoungMusic(List<Subject> mSubjects);
    }
}
