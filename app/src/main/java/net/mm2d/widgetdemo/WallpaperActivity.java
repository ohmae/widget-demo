/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.widgetdemo;

import android.app.WallpaperManager;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.view.View;

public class WallpaperActivity extends AppCompatActivity {
    private static final int SYSTEM_UI_VISIBLE = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
        getWindow().getDecorView().setSystemUiVisibility(SYSTEM_UI_VISIBLE);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final CardAdapter adapter = new CardAdapter(this);
        recyclerView.setAdapter(adapter);

        final WallpaperManager manager = WallpaperManager.getInstance(this);
        recyclerView.setOnScrollListener(new OnScrollListener() {
            private int mOffset;
            @Override
            public void onScrolled(final RecyclerView recyclerView, final int dx, final int dy) {
                mOffset += dy;
                if (recyclerView.getChildCount() == 0) {
                    return;
                }
                final int height = recyclerView.getChildAt(0).getHeight() * adapter.getItemCount();
                if (height == 0) {
                    return;
                }
                final float offset = mOffset / (float)height;
                manager.setWallpaperOffsets(recyclerView.getWindowToken(), offset, offset);
            }
        });

        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            adapter.setOnItemClickListener(color -> {
                getWindow().setStatusBarColor(color);
                getWindow().setNavigationBarColor(color);
            });
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, 0);
    }
}
