/*
 * Copyright (c) 2017 大前良介 (OHMAE Ryosuke)
 *
 * This software is released under the MIT License.
 * http://opensource.org/licenses/MIT
 */

package net.mm2d.widgetdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.icon1).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TransparentActivity.class),
                    makeScaleUpAnimationBundle(v));
            finish();
        });
        findViewById(R.id.icon2).setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, WallpaperActivity.class),
                    makeScaleUpAnimationBundle(v));
            finish();
        });
    }

    private static Bundle makeScaleUpAnimationBundle(View v) {
        return ActivityOptionsCompat.makeScaleUpAnimation(v, 0, 0, v.getWidth(), v.getHeight())
                .toBundle();
    }
}
