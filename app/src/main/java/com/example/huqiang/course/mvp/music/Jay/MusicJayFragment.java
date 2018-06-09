package com.example.huqiang.course.mvp.music.Jay;

/**
 * Created by 49988 on 2018/6/7.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.huqiang.course.MyApplication;
import com.example.huqiang.course.R;
import com.example.huqiang.course.data.music.Subject;
import com.example.huqiang.course.data.source.remote.MusicRemoteDataSource;
import com.example.huqiang.course.interfaze.OnRecyclerViewItemClickListener;
import com.example.huqiang.course.utils.ScreenUtils;
import com.example.huqiang.course.widgets.SpacesItemDecorate;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicJayFragment extends Fragment implements MusicJayContract.View, OnRecyclerViewItemClickListener {
    @BindView(R.id.rv_jay_music)
    RecyclerView mRvJayMusic;
    @BindView(R.id.sw_jay_music)
    SwipeRefreshLayout mSwJayMusic;


    private MusicJayContract.Presenter presenter;

    Unbinder unbinder;

    public static MusicJayFragment newInstance() {
        return new MusicJayFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_jay, container, false);
        new MusicJayPresenter (this, MusicRemoteDataSource.getInstance());
        initView(view);
        return view;
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        mRvJayMusic.setLayoutManager(manager);

        final SpacesItemDecorate decorate = new SpacesItemDecorate(ScreenUtils.dipToPx(10), ScreenUtils.dipToPx(10),
                ScreenUtils.dipToPx(10), 0);
        mRvJayMusic.addItemDecoration(decorate);
    }

    private void initRefreshLayout() {
        mSwJayMusic.setColorSchemeColors(MyApplication.getContext().getResources()
                .getColor(R.color.colorPrimary));
        mSwJayMusic.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getJayMusic();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mSwJayMusic.setRefreshing(true);
        presenter.onSubscribe();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.unSubscribe();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "网络出错了", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setLoadingIndicator(final boolean active) {
        mSwJayMusic.post(new Runnable() {
            @Override
            public void run() {
                mSwJayMusic.setRefreshing(active);
            }
        });
    }

    @Override
    public void showJayMusic(List<Subject> mSubjects) {
        final MusicJayAdapter adapter = new MusicJayAdapter(getContext(), mSubjects);
        mRvJayMusic.setAdapter(adapter);
        adapter.setListener(this);
    }



    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void setPresenter(MusicJayContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("--------", String.valueOf(position));
    }
}
