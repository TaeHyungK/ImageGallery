package com.enter.taehyung.imagegallery.util;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * 애니메이션 처리를 위한 ValueAnimator 반환 Util
 */
public class AnimatorUtil {
    private AnimatorUtil() {

    }

    public static AnimatorUtil getInstance() {
        return LazyHolder.INSTANCE;
    }

    private static class LazyHolder {
        public static final AnimatorUtil INSTANCE = new AnimatorUtil();
    }

    public ValueAnimator makeRotationAnimator(final View targetView, long duration, int startDegree, int endDegree) {
        ValueAnimator animator = ValueAnimator.ofInt(startDegree, endDegree);
        animator.setDuration(duration);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int degree = (int) animation.getAnimatedValue();

                if (targetView != null) {
                    targetView.setRotation(degree);
                }
            }
        });
        return animator;
    }
}
