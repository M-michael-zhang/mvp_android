package com.example.huqiang.course.mvp.movie.top;


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
import com.example.huqiang.course.data.movie.Subject;
import com.example.huqiang.course.data.source.remote.MovieRemoteDataSource;
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
public class MovieTopFragment extends Fragment implements MovieTopContract.View, OnRecyclerViewItemClickListener {

    @BindView(R.id.rv_top_movie)
    RecyclerView mRvTopMovie;
    @BindView(R.id.sw_top_movie)
    SwipeRefreshLayout mSwTopMovie;

    private MovieTopContract.Presenter presenter;
    Unbinder unbinder;

    public static MovieTopFragment newInstance() {
        return new MovieTopFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_top, container, false);
        new MovieTopPresenter(this, MovieRemoteDataSource.getInstance());
        initView(view);
        return view;
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        mRvTopMovie.setLayoutManager(manager);

        final SpacesItemDecorate decorate = new SpacesItemDecorate(ScreenUtils.dipToPx(10), ScreenUtils.dipToPx(10),
                ScreenUtils.dipToPx(10), 0);
        mRvTopMovie.addItemDecoration(decorate);
    }

    private void initRefreshLayout() {
        mSwTopMovie.setColorSchemeColors(MyApplication.getContext().getResources()
                .getColor(R.color.colorPrimary));
        mSwTopMovie.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getTopMovie();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mSwTopMovie.setRefreshing(true);
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
        mSwTopMovie.post(new Runnable() {
            @Override
            public void run() {
                mSwTopMovie.setRefreshing(active);
            }
        });
    }

    @Override
    public void showTopMovie(List<Subject> subjects) {
        final MovieTopAdapter adapter = new MovieTopAdapter(getContext(), subjects);
        mRvTopMovie.setAdapter(adapter);
        adapter.setListener(this);
    }

    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void setPresenter(MovieTopContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("--------", String.valueOf(position));
    }
}
