package com.example.huqiang.course.mvp.music.Young;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.huqiang.course.R;
import com.example.huqiang.course.data.music.Subject;
import com.example.huqiang.course.interfaze.OnRecyclerViewItemClickListener;
import com.example.huqiang.course.utils.ScreenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 49988 on 2018/6/7.
 */

public class MusicYoungAdapter extends RecyclerView.Adapter<MusicYoungAdapter.ViewHolder> {
    private final Context mContext;
    private final LayoutInflater mInflater;
    private final List<Subject> mSubjects;
    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .dontAnimate();

    private OnRecyclerViewItemClickListener listener;

    public void setListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public MusicYoungAdapter(Context mContext, List<Subject> mSubjects) {
        this.mContext = mContext;
        this.mSubjects = mSubjects;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public MusicYoungAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mInflater.inflate(R.layout.item_music_young, parent, false);
        return new MusicYoungAdapter.ViewHolder(view, listener);
    }


    @Override
    public void onBindViewHolder(MusicYoungAdapter.ViewHolder holder, int position) {
        final ViewGroup.LayoutParams params = holder.mIvYoungMusic.getLayoutParams();
        final int screenWidth = ScreenUtils.getScreenWidth();
        final int imageWidth = (screenWidth - ScreenUtils.dipToPx(80)) / 3;
        params.width = imageWidth;
        double imageHeight = imageWidth * 1.4;
        params.height = (int) imageHeight;

        Glide.with(mContext)
                .load(mSubjects.get(position).getImage())
                .apply(OPTIONS)
                .into(holder.mIvYoungMusic);

        holder.mTvYoungMusicName.setText(mSubjects.get(position).getTitle());

        holder.mTvYoungMusicGrade.setText(String.format(mContext.getString(R.string.music_grade),
                mSubjects.get(position).getRating().getAverage()));
    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_young_music)
        AppCompatImageView mIvYoungMusic;
        @BindView(R.id.tv_young_music_name)
        AppCompatTextView mTvYoungMusicName;
        @BindView(R.id.tv_young_music_grade)
        AppCompatTextView mTvYoungMusicGrade;

        private OnRecyclerViewItemClickListener listener;

        public ViewHolder(View itemView, OnRecyclerViewItemClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.onItemClick(view, getLayoutPosition());
            }
        }
    }

}

