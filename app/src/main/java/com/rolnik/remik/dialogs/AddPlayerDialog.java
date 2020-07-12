package com.rolnik.remik.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.rolnik.remik.R;
import com.rolnik.remik.databinding.AddPlayerDialogBinding;
import com.rolnik.remik.model.Player;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddPlayerDialog extends AnimatedDialog {
    @BindView(R.id.addButton)
    Button addButton;
    @BindView(R.id.cancelButton)
    Button cancelButton;

    private AddPlayerDialogBinding addPlayerDialogBinding;
    private final View.OnClickListener onClickListener;

    public AddPlayerDialog(@NonNull Context context, View.OnClickListener onClickListener) {
        super(context);
        this.onClickListener = onClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        if(getWindow() != null){
            getWindow().getAttributes().windowAnimations = R.style.WindowAnimationTransition;
        }


        addPlayerDialogBinding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.add_player_dialog, null, false);

        setContentView(addPlayerDialogBinding.getRoot());
        ButterKnife.bind(this, addPlayerDialogBinding.getRoot());

        addPlayerDialogBinding.setPlayer(new Player());

        initButtons();
    }

    private void initButtons() {
        cancelButton.setOnClickListener(view -> dismiss());
        addButton.setOnClickListener(onClickListener);
    }

    public Player getPlayer(){
        return addPlayerDialogBinding.getPlayer();
    }
}
