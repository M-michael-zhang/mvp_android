package com.example.huqiang.course.mvp.book.suspense;

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
import com.example.huqiang.course.data.book.Subject;
import com.example.huqiang.course.interfaze.OnRecyclerViewItemClickListener;
import com.example.huqiang.course.utils.ScreenUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 49988 on 2018/6/7.
 */

public class BookSuspenseAdapter extends RecyclerView.Adapter<BookSuspenseAdapter.ViewHolder> {
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

    public BookSuspenseAdapter(Context mContext, List<Subject> mSubjects) {
        this.mContext = mContext;
        this.mSubjects = mSubjects;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BookSuspenseAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = mInflater.inflate(R.layout.item_book_suspense, parent, false);
        return new BookSuspenseAdapter.ViewHolder(view, listener);
    }


    @Override
    public void onBindViewHolder(BookSuspenseAdapter.ViewHolder holder, int position) {
        final ViewGroup.LayoutParams params = holder.mIvSuspenseBook.getLayoutParams();
        final int screenWidth = ScreenUtils.getScreenWidth();
        final int imageWidth = (screenWidth - ScreenUtils.dipToPx(80)) / 3;
        params.width = imageWidth;
        double imageHeight = imageWidth * 1.4;
        params.height = (int) imageHeight;

        Glide.with(mContext)
                .load(mSubjects.get(position).getImage())
                .apply(OPTIONS)
                .into(holder.mIvSuspenseBook);

        holder.mTvSuspenseBookName.setText(mSubjects.get(position).getTitle());

        holder.mTvSuspenseBookGrade.setText(String.format(mContext.getString(R.string.book_grade),
                mSubjects.get(position).getRating().getAverage()));
    }

    @Override
    public int getItemCount() {
        return mSubjects.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.iv_sus_book)
        AppCompatImageView mIvSuspenseBook;
        @BindView(R.id.tv_sus_book_name)
        AppCompatTextView mTvSuspenseBookName;
        @BindView(R.id.tv_sus_book_grade)
        AppCompatTextView mTvSuspenseBookGrade;

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

