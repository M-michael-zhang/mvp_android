package com.example.huqiang.course.mvp.book;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huqiang.course.R;
import com.example.huqiang.course.mvp.book.life.BookLifeFragment;
import com.example.huqiang.course.mvp.book.suspense.BookSuspenseFragment;
import com.example.huqiang.course.mvp.movie.hot.MovieHotFragment;
import com.example.huqiang.course.mvp.movie.top.MovieTopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookFragment extends Fragment {


    @BindView(R.id.tab_book)
    TabLayout mTabBook;
    @BindView(R.id.vw_book)
    ViewPager mVwBook;
    Unbinder unbinder;

    public static BookFragment newInstance() {
        return new BookFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViewPager();
        initTabLayout();
        return view;
    }

    private void initTabLayout() {
        mTabBook.setupWithViewPager(mVwBook);
    }

    private void initViewPager() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(BookSuspenseFragment.newInstance());
        fragments.add(BookLifeFragment.newInstance());

        final String[] titles = {"悬疑", "生活"};

        final BookViewPagerAdapter adapter = new BookViewPagerAdapter(getChildFragmentManager(), fragments, titles);
        mVwBook.setAdapter(adapter);
        mVwBook.setOffscreenPageLimit(2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
