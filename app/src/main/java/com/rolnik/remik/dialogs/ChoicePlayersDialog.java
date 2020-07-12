package com.rolnik.remik.dialogs;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.annimon.stream.Stream;
import com.rolnik.remik.R;
import com.rolnik.remik.adapters.PlayerChooseRecyclerViewAdapter;
import com.rolnik.remik.model.Player;
import com.rolnik.remik.model.PlayerWithChoice;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChoicePlayersDialog extends AnimatedDialog {
    @BindView(R.id.okButton)
    Button okButton;
    @BindView(R.id.playersToChoose)
    RecyclerView playersToChoose;

    private List<Player> players;
    private final View.OnClickListener onClickListener;
    private PlayerChooseRecyclerViewAdapter adapter;

    public ChoicePlayersDialog(@NonNull Context context, List<Player> players, View.OnClickListener onClickListener) {
        super(context);
        this.players = players;
        this.onClickListener = onClickListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.choose_players_dialog);
        ButterKnife.bind(this);

        initPlayersRecyclerView();
        initOkButton();
    }

    private void initPlayersRecyclerView() {
        adapter = new PlayerChooseRecyclerViewAdapter(getContext(), mapPlayers(players));

        playersToChoose.setAdapter(adapter);
    }

    private List<PlayerWithChoice> mapPlayers(List<Player> players){
        return Stream.of(players)
                .map(p -> new PlayerWithChoice(p, false))
                .toList();
    }

    private void initOkButton() {
        okButton.setOnClickListener(onClickListener);
    }

    public void setPlayers(List<Player> players){
        this.players = players;
        if (adapter != null) {
            adapter.setPlayers(mapPlayers(players));
        }
    }

    public List<Player> getChosenPlayers() {
        return Stream.of(adapter.getPlayers())
                .filter(PlayerWithChoice::isChecked)
                .map(PlayerWithChoice::getPlayer)
                .toList();
    }

    @OnClick(R.id.cancelButton)
    public void dismiss() {
        super.dismiss();
    }
}
