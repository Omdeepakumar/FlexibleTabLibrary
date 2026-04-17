package com.flexibletab.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.flexibletab.theme.TabTheme;

/**
 * Handles tab animations including indicator movement, color transitions, and scale effects.
 */
public class TabAnimator {
    
    private ValueAnimator indicatorAnimator;
    private ValueAnimator colorAnimator;
    private ValueAnimator scaleAnimator;
    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    
    /**
     * Animate indicator position change
     */
    public void animateIndicator(final View indicator, float startX, float endX, 
                                  float startWidth, float endWidth, TabTheme theme) {
        if (indicatorAnimator != null && indicatorAnimator.isRunning()) {
            indicatorAnimator.cancel();
        }
        
        indicatorAnimator = ValueAnimator.ofFloat(0f, 1f);
        indicatorAnimator.setDuration(theme.isAnimationEnabled() ? theme.getAnimationDuration() : 0);
        indicatorAnimator.setInterpolator(new DecelerateInterpolator());
        indicatorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float fraction = animation.getAnimatedFraction();
                float currentX = startX + (endX - startX) * fraction;
                float currentWidth = startWidth + (endWidth - startWidth) * fraction;
                
                indicator.setX(currentX);
                indicator.getLayoutParams().width = (int) currentWidth;
                indicator.requestLayout();
            }
        });
        indicatorAnimator.start();
    }
    
    /**
     * Animate color change for a view
     */
    public void animateColorChange(final View view, final int startColor, final int endColor, 
                                   TabTheme theme) {
        if (colorAnimator != null && colorAnimator.isRunning()) {
            colorAnimator.cancel();
        }
        
        colorAnimator = ValueAnimator.ofObject(argbEvaluator, startColor, endColor);
        colorAnimator.setDuration(theme.isAnimationEnabled() ? theme.getAnimationDuration() : 0);
        colorAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                if (view instanceof TextView) {
                    ((TextView) view).setTextColor(color);
                } else {
                    view.setBackgroundColor(color);
                }
            }
        });
        colorAnimator.start();
    }
    
    /**
     * Animate text color change
     */
    public void animateTextColor(final TextView textView, int startColor, int endColor, 
                                  TabTheme theme) {
        ValueAnimator animator = ValueAnimator.ofObject(argbEvaluator, startColor, endColor);
        animator.setDuration(theme.isAnimationEnabled() ? theme.getAnimationDuration() : 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setTextColor((int) animation.getAnimatedValue());
            }
        });
        animator.start();
    }
    
    /**
     * Animate scale effect for tab selection
     */
    public void animateScale(final View view, boolean selected, TabTheme theme) {
        if (scaleAnimator != null && scaleAnimator.isRunning()) {
            scaleAnimator.cancel();
        }
        
        float startScale = selected ? 0.9f : 1.05f;
        float endScale = selected ? 1.05f : 0.9f;
        
        scaleAnimator = ValueAnimator.ofFloat(startScale, endScale, 1.0f);
        scaleAnimator.setDuration(theme.isAnimationEnabled() ? theme.getAnimationDuration() : 0);
        scaleAnimator.setInterpolator(new OvershootInterpolator());
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float scale = (float) animation.getAnimatedValue();
                view.setScaleX(scale);
                view.setScaleY(scale);
            }
        });
        scaleAnimator.start();
    }
    
    /**
     * Animate fade transition
     */
    public void animateFade(final View view, boolean fadeIn, TabTheme theme) {
        float startAlpha = fadeIn ? 0.3f : 1.0f;
        float endAlpha = fadeIn ? 1.0f : 0.3f;
        
        ValueAnimator animator = ValueAnimator.ofFloat(startAlpha, endAlpha);
        animator.setDuration(theme.isAnimationEnabled() ? theme.getAnimationDuration() : 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setAlpha((float) animation.getAnimatedValue());
            }
        });
        animator.start();
    }
    
    /**
     * Animate background color change with corner radius
     */
    public void animateBackgroundColor(final View view, int startColor, int endColor, 
                                        final float cornerRadius, TabTheme theme) {
        ValueAnimator animator = ValueAnimator.ofObject(argbEvaluator, startColor, endColor);
        animator.setDuration(theme.isAnimationEnabled() ? theme.getAnimationDuration() : 0);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int color = (int) animation.getAnimatedValue();
                // Background with corner radius will be handled by drawable
                view.setBackgroundColor(color);
            }
        });
        animator.start();
    }
    
    /**
     * Cancel all running animations
     */
    public void cancelAll() {
        if (indicatorAnimator != null) indicatorAnimator.cancel();
        if (colorAnimator != null) colorAnimator.cancel();
        if (scaleAnimator != null) scaleAnimator.cancel();
    }
}
