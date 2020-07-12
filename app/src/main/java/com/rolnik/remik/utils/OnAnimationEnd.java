package com.rolnik.remik.utils;

import android.animation.Animator;

public abstract class OnAnimationEnd implements Animator.AnimatorListener {
    @Override
    public void onAnimationStart(Animator animator) {

    }

    @Override
    public void onAnimationCancel(Animator animator) {

    }

    @Override
    public void onAnimationRepeat(Animator animator) {

    }

    public abstract void onAnimationEnd(Animator animator);
}
