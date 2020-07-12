package com.rolnik.remik.viewholders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rolnik.remik.R;
import com.rolnik.remik.model.Player;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameHistoryViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.playerName)
    TextView playerName;
    @BindView(R.id.playerPoints)
    TextView playerPoints;

    public GameHistoryViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView.getRootView());
    }

    public void bind(Player player, Integer points) {
        playerName.setText(player.getFirstName());
        playerPoints.setText(points + " pkt.");
    }
}
