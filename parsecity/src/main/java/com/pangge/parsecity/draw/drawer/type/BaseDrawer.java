package com.pangge.parsecity.draw.drawer.type;

import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.pangge.parsecity.draw.data.Indicator;

/**
 * Created by iuuu on 17/6/6.
 */

public class BaseDrawer {
    Paint paint;
    Indicator indicator;

    BaseDrawer(@NonNull Paint paint, @NonNull Indicator indicator) {
        this.paint = paint;
        this.indicator = indicator;
    }
}
