package com.rolnik.remik.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.annimon.stream.Optional;
import com.rolnik.remik.R;
import com.rolnik.remik.databinding.AddPlayerPointsDialogBinding;
import com.rolnik.remik.model.CurrentGame;
import com.rolnik.remik.model.PlayerWithPoints;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPlayerPointsDialog extends AnimatedDialog {
    @BindView(R.id.playerPoints)
    EditText playerPoints;
    @BindView(R.id.win)
    CheckBox win;

    private Queue<PlayerWithPoints> playersWithPoints;
    private AddPlayerPointsDialogBinding binding;

    public AddPlayerPointsDialog(@NonNull Context context, List<PlayerWithPoints> playersWithPoints) {
        super(context);
        this.playersWithPoints = new ArrayDeque<>(playersWithPoints);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.add_player_points_dialog, null, false);
        setContentView(binding.getRoot());
        ButterKnife.bind(this);

        Optional.ofNullable(playersWithPoints.poll())
                .ifPresentOrElse(this::bind, this::dismiss);
    }

    @OnClick(R.id.continueButton)
    public void addPlayerPointsAndClose(){
        int point = win.isChecked() ? CurrentGame.WIN_POINTS : getPlayerPoints();

        PlayerWithPoints playerWithPoints = binding.getPlayersWithPoints();

        playerWithPoints.addPoint(point);

        Optional.ofNullable(playersWithPoints.poll())
                .ifPresentOrElse(this::bind, this::dismiss);
    }

    private void bind(PlayerWithPoints playerWithPoints){
        binding.setPlayersWithPoints(playerWithPoints);
        binding.win.setChecked(false);
        binding.playerPoints.setText("");
    }

    private int getPlayerPoints() {
        String numberText = playerPoints.getText().toString();

        if(numberText.isEmpty()){
            return 0;
        }

        return Integer.parseInt(numberText);
    }
}
