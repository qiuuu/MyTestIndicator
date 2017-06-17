package com.pangge.parsecity.animation.controller;

import android.support.annotation.NonNull;
import android.util.Log;

import com.pangge.parsecity.animation.type.BaseAnimation;
import com.pangge.parsecity.draw.data.Indicator;


public class AnimationController {

    private ValueController valueController;
    private ValueController.UpdateListener listener;

    private BaseAnimation runningAnimation;
    private Indicator indicator;

   // private float progress;
    private boolean isInteractive;

    public AnimationController(@NonNull Indicator indicator, @NonNull ValueController.UpdateListener listener) {
        this.valueController = new ValueController(listener);
        this.listener = listener;
        this.indicator = indicator;
    }

    //public void interactive(float progress) {
    public void interactive() {
        this.isInteractive = true;

        animate();
    }

    public void basic() {
        this.isInteractive = false;
       // this.progress = 0;
        animate();
    }

    public void end() {
        if (runningAnimation != null) {
            runningAnimation.end();
        }
    }

    private void animate() {
        /** no animation choose
         *
         */
        listener.onValueUpdated(null);


    }

}

