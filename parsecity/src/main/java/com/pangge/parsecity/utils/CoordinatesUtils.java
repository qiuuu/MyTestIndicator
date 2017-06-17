package com.pangge.parsecity.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Pair;

import com.pangge.parsecity.draw.data.Indicator;


public class CoordinatesUtils {

    @SuppressWarnings("UnnecessaryLocalVariable")
    public static int getCoordinate(@Nullable Indicator indicator, int position) {
        if (indicator == null) {
            return 0;
        }

        /**
         * always Horizontal
         */

        return getXCoordinate(indicator, position);
    }

    @SuppressWarnings("UnnecessaryLocalVariable")
    public static int getXCoordinate( Indicator indicator, int position) {
        if (indicator == null) {
            return 0;
        }

        int coordinate;

        coordinate = getHorizontalCoordinate(indicator, position);

        coordinate += indicator.getPaddingLeft();
        return coordinate;
    }

    public static int getYCoordinate(@Nullable Indicator indicator, int position) {
        if (indicator == null) {
            return 0;
        }

        int coordinate;

        coordinate = getVerticalCoordinate(indicator);

        coordinate += indicator.getPaddingTop();
        return coordinate;
    }

    private static int getHorizontalCoordinate(@NonNull Indicator indicator, int position) {
        int count = indicator.getCount();
        int radius = indicator.getRadius();
        int stroke = indicator.getStroke();
        int padding = indicator.getPadding();

        int coordinate = 0;
        for (int i = 0; i < count; i++) {
            coordinate += radius + (stroke / 2);

            if (position == i) {
                return coordinate;
            }

            coordinate += radius + padding + (stroke / 2);
        }
        return coordinate;
    }

    private static int getVerticalCoordinate(Indicator indicator) {
        int radius = indicator.getRadius();
        int coordinate;

       coordinate = radius;

        return coordinate;
    }


}
