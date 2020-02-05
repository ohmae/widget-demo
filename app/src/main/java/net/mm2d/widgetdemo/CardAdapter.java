/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.widgetdemo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author <a href="mailto:ryo@mm2d.net">大前良介 (OHMAE Ryosuke)</a>
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    private LayoutInflater mLayoutInflater;

    public CardAdapter(final Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.applyItem(position);
    }

    @Override
    public int getItemCount() {
        return 36;
    }

    public interface OnItemClickListener {
        void onClick(int color);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener l) {
        mOnItemClickListener = l;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener {
        private ImageView mImageView;
        private int mColor;

        ViewHolder(View view) {
            super(view);
            mImageView = (ImageView) view.findViewById(R.id.icon);
            view.setOnClickListener(this);
        }

        void applyItem(int position) {
            float[] hsv = new float[]{position * 10f, 1.0f, 1.0f};
            mColor = Color.HSVToColor(hsv);
            mImageView.setColorFilter(mColor);
        }

        @Override
        public void onClick(final View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(mColor);
            }
        }
    }
}
