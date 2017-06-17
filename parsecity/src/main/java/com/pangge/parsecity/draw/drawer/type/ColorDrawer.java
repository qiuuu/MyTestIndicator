package com.pangge.parsecity.draw.drawer.type;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.NonNull;

import com.pangge.parsecity.animation.data.Value;
import com.pangge.parsecity.animation.data.type.ColorAnimationValue;
import com.pangge.parsecity.draw.data.Indicator;

/**
 * Created by iuuu on 17/6/6.
 */

public class ColorDrawer extends BaseDrawer {

    public ColorDrawer(@NonNull Paint paint, @NonNull Indicator indicator) {
        super(paint, indicator);
    }

    public void draw(@NonNull Canvas canvas,
                     @NonNull Value value,
                     int position,
                     int coordinateX,
                     int coordinateY) {

        /*if (!(value instanceof ColorAnimationValue)) {
            return;
        }*/

        ColorAnimationValue v = (ColorAnimationValue) value;
        float radius = indicator.getRadius();
        int color = indicator.getSelectedColor();

        int selectedPosition = indicator.getSelectedPosition();
        int selectingPosition = indicator.getSelectingPosition();
        int lastSelectedPosition = indicator.getLastSelectedPosition();



        paint.setColor(color);
        canvas.drawCircle(coordinateX, coordinateY, radius, paint);
    }
}
