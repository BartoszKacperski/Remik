package com.rolnik.remik.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.rolnik.remik.R;

public abstract class AnimatedDialog extends Dialog {

    public AnimatedDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getWindow() != null){
            getWindow().getAttributes().windowAnimations = R.style.WindowAnimationTransition;
        }

    }

}
