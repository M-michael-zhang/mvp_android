package com.example.huqiang.course.mvp.music.Young;

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
public class MusicYoungFragment extends Fragment implements MusicYoungContract.View, OnRecyclerViewItemClickListener {
    @BindView(R.id.rv_young_music)
    RecyclerView mRvYoungMusic;
    @BindView(R.id.sw_young_music)
    SwipeRefreshLayout mSwYoungMusic;


    private MusicYoungContract.Presenter presenter;

    Unbinder unbinder;

    public static MusicYoungFragment newInstance() {
        return new MusicYoungFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music_young, container, false);
        new MusicYoungPresenter (this, MusicRemoteDataSource.getInstance());
        initView(view);
        return view;
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        mRvYoungMusic.setLayoutManager(manager);

        final SpacesItemDecorate decorate = new SpacesItemDecorate(ScreenUtils.dipToPx(10), ScreenUtils.dipToPx(10),
                ScreenUtils.dipToPx(10), 0);
        mRvYoungMusic.addItemDecoration(decorate);
    }

    private void initRefreshLayout() {
        mSwYoungMusic.setColorSchemeColors(MyApplication.getContext().getResources()
                .getColor(R.color.colorPrimary));
        mSwYoungMusic.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getYoungMusic();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mSwYoungMusic.setRefreshing(true);
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
        mSwYoungMusic.post(new Runnable() {
            @Override
            public void run() {
                mSwYoungMusic.setRefreshing(active);
            }
        });
    }

    @Override
    public void showYoungMusic(List<Subject> mSubjects) {
        final MusicYoungAdapter adapter = new MusicYoungAdapter(getContext(), mSubjects);
        mRvYoungMusic.setAdapter(adapter);
        adapter.setListener(this);
    }



    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void setPresenter(MusicYoungContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("--------", String.valueOf(position));
    }
}
