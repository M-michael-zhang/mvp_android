package com.example.huqiang.course.mvp.music;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huqiang.course.R;
import com.example.huqiang.course.mvp.music.Jay.MusicJayFragment;
import com.example.huqiang.course.mvp.music.Young.MusicYoungFragment;
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
public class MusicFragment extends Fragment {


    @BindView(R.id.tab_music)
    TabLayout mTabMusic;
    @BindView(R.id.vw_music)
    ViewPager mVwMusic;
    Unbinder unbinder;

    public static MusicFragment newInstance() {
        return new MusicFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_music, container, false);
        unbinder = ButterKnife.bind(this, view);
        initViewPager();
        initTabLayout();
        return view;
    }

    private void initTabLayout() {
        mTabMusic.setupWithViewPager(mVwMusic);
    }

    private void initViewPager() {
        final List<Fragment> fragments = new ArrayList<>();
        fragments.add(MusicYoungFragment.newInstance());
        fragments.add(MusicJayFragment.newInstance());

        final String[] titles = {"青春", "周杰伦"};

        final MusicViewPagerAdapter adapter = new MusicViewPagerAdapter(getChildFragmentManager(), fragments, titles);
        mVwMusic.setAdapter(adapter);
        mVwMusic.setOffscreenPageLimit(2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
