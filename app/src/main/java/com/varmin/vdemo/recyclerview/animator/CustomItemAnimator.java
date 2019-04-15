package com.varmin.vdemo.recyclerview.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Created by HuangYang
 * on 2019/4/8  12:33.
 * 文件描述：
 */
public class CustomItemAnimator extends BaseDefaultItemAnimator {
    private static final String TAG = "CustomItemAnimator";
    @Override
    protected void animateAddImpl(final RecyclerView.ViewHolder holder) {
        //super.animateAddImpl(holder);
        final View view = holder.itemView;
        ObjectAnimator translationX = ObjectAnimator
                .ofFloat(view, "translationX", 0, 20, -20, 0, 20, -20, 0, 20, -20, 0)
                .setDuration(getAddDuration());
        ObjectAnimator scaleX = ObjectAnimator
                .ofFloat(view, "scaleX", 1, 0.95f, 1.05f, 1, 0.95f, 1.05f, 1, 0.95f, 1.05f,1)
                .setDuration(getAddDuration());
        AnimatorSet set = new AnimatorSet();
        set.playTogether(scaleX,translationX);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationCancel(Animator animation) {
                view.setAlpha(1);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
                dispatchAddFinished(holder);
                mAddAnimations.remove(holder);
                dispatchFinishedWhenDone();
            }
            @Override
            public void onAnimationStart(Animator animation) {
                view.setAlpha(1);
                dispatchAddStarting(holder);
            }
        });
        set.start();
    }

    @Override
    protected void animateRemoveImpl(final RecyclerView.ViewHolder holder) {
        //super.animateRemoveImpl(holder);
        final View view = holder.itemView;
        final int right = view.getRight();
        final ViewPropertyAnimator animation = view.animate();
        mRemoveAnimations.add(holder);
        //不要轻易改变默认动画时长
        animation.setDuration(getRemoveDuration())
                .alpha(0)
                .translationX(-right)
                .setListener(
                new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        dispatchRemoveStarting(holder);
                    }
                    @Override
                    public void onAnimationEnd(Animator animator) {
                        animation.setListener(null);
                        view.setAlpha(1);
                        view.setTranslationX(0);
                        dispatchRemoveFinished(holder);
                        mRemoveAnimations.remove(holder);
                        dispatchFinishedWhenDone();
                    }
                }).start();
    }
}
