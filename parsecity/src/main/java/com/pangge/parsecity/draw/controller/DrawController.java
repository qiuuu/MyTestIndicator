package com.pangge.parsecity.draw.controller;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.pangge.parsecity.animation.data.Value;
import com.pangge.parsecity.draw.data.Indicator;
import com.pangge.parsecity.draw.drawer.Drawer;
import com.pangge.parsecity.utils.CoordinatesUtils;

/**
 * Created by iuuu on 17/6/6.
 */

public class DrawController {

    private Value value;
    private Drawer drawer;
    private Indicator indicator;

    public DrawController(@NonNull Indicator indicator) {
        this.indicator = indicator;
        this.drawer = new Drawer(indicator);
    }

    public void updateValue(@Nullable Value value) {
        this.value = value;
    }

    public void draw(@NonNull Canvas canvas) {
        int count = indicator.getCount();

        for (int position = 0; position < count; position++) {
            int coordinateX = CoordinatesUtils.getXCoordinate(indicator, position);
            int coordinateY = CoordinatesUtils.getYCoordinate(indicator, position);
            drawIndicator(canvas, position, coordinateX, coordinateY);
        }
    }

    private void drawIndicator(
            @NonNull Canvas canvas,
            int position,
            int coordinateX,
            int coordinateY) {

       // boolean interactiveAnimation = indicator.isInteractiveAnimation();
        boolean interactiveAnimation = true;
        int selectedPosition = indicator.getSelectedPosition();
        int selectingPosition = indicator.getSelectingPosition();
        int lastSelectedPosition = indicator.getLastSelectedPosition();

        boolean selectedItem = !interactiveAnimation && (position == selectedPosition || position == lastSelectedPosition);
        boolean selectingItem = interactiveAnimation && (position == selectedPosition || position == selectingPosition);
        boolean isSelectedItem = selectedItem | selectingItem;
        drawer.setup(position, coordinateX, coordinateY);

        if (value != null && isSelectedItem) {
            drawWithAnimation(canvas);
        } else {
            drawer.drawBasic(canvas, isSelectedItem);
        }
    }

    private void drawWithAnimation(@NonNull Canvas canvas) {
        drawer.drawBasic(canvas, true);
    }
}
