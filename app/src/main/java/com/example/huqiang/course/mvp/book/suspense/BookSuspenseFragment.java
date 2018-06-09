package com.example.huqiang.course.mvp.book.suspense;

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
import com.example.huqiang.course.data.book.Subject;
import com.example.huqiang.course.data.source.remote.BookRemoteDataSource;
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
public class BookSuspenseFragment extends Fragment implements BookSuspenseContract.View, OnRecyclerViewItemClickListener {
    @BindView(R.id.rv_sus_book)
    RecyclerView mRvSuspenseBook;
    @BindView(R.id.sw_sus_book)
    SwipeRefreshLayout mSwSuspenseBook;


    private BookSuspenseContract.Presenter presenter;
    Unbinder unbinder;

    public static BookSuspenseFragment newInstance() {
        return new BookSuspenseFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_suspense, container, false);
        new BookSuspensePresenter(this, BookRemoteDataSource.getInstance());
        initView(view);
        return view;
    }

    private void initRecyclerView() {
        final GridLayoutManager manager = new GridLayoutManager(getContext(), 3);
        mRvSuspenseBook.setLayoutManager(manager);

        final SpacesItemDecorate decorate = new SpacesItemDecorate(ScreenUtils.dipToPx(10), ScreenUtils.dipToPx(10),
                ScreenUtils.dipToPx(10), 0);
        mRvSuspenseBook.addItemDecoration(decorate);
    }

    private void initRefreshLayout() {
        mSwSuspenseBook.setColorSchemeColors(MyApplication.getContext().getResources()
                .getColor(R.color.colorPrimary));
        mSwSuspenseBook.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getSuspenseBook();
            }
        });
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRefreshLayout();
        initRecyclerView();
        mSwSuspenseBook.setRefreshing(true);
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
        mSwSuspenseBook.post(new Runnable() {
            @Override
            public void run() {
                mSwSuspenseBook.setRefreshing(active);
            }
        });
    }

    @Override
    public void showSuspenseBook(List<Subject> mSubjects) {
        final BookSuspenseAdapter adapter = new BookSuspenseAdapter(getContext(), mSubjects);
        mRvSuspenseBook.setAdapter(adapter);
        adapter.setListener(this);
    }



    @Override
    public void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
    }

    @Override
    public void setPresenter(BookSuspenseContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onItemClick(View view, int position) {
        Log.d("--------", String.valueOf(position));
    }
}
