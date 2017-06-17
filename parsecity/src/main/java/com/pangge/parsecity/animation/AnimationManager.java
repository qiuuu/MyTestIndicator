package com.pangge.parsecity.animation;

import android.support.annotation.NonNull;

import com.pangge.parsecity.animation.controller.AnimationController;
import com.pangge.parsecity.animation.controller.ValueController;
import com.pangge.parsecity.draw.data.Indicator;

/**
 * Created by iuuu on 17/6/6.
 */

public class AnimationManager {
    private AnimationController animationController;

    public AnimationManager(@NonNull Indicator indicator, @NonNull ValueController.UpdateListener listener) {
        this.animationController = new AnimationController(indicator, listener);
    }

    public void basic() {
        if (animationController != null) {
            animationController.end();
            animationController.basic();
        }
    }

   /* public void interactive(float progress) {
        if (animationController != null) {
            animationController.interactive(progress);
        }
    }*/
   public void interactive() {
       if (animationController != null) {
           animationController.interactive();
       }
   }

    public void end() {
        if (animationController != null) {
            animationController.end();
        }
    }
}
