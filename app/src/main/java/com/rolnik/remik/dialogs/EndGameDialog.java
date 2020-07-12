package com.rolnik.remik.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.annimon.stream.Stream;
import com.rolnik.remik.R;
import com.rolnik.remik.adapters.GameResultRecyclerViewAdapter;
import com.rolnik.remik.model.PlayerWithPoints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EndGameDialog extends Dialog {
    @BindView(R.id.results)
    RecyclerView results;

    private final List<PlayerWithPoints> playerWithPointsList;

    public EndGameDialog(@NonNull Context context, List<PlayerWithPoints> playerWithPointsList) {
        super(context);
        this.playerWithPointsList = new ArrayList<>(playerWithPointsList);
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.end_game);
        ButterKnife.bind(this);

        setUpResults();
    }

    @OnClick(R.id.okButton)
    public void close() {
        super.dismiss();
    }

    private void setUpResults() {
        Collections.sort(playerWithPointsList, (o1, o2) -> Integer.compare(Stream.of(o1.getCurrentPoints()).mapToInt(i -> i).sum()
                , Stream.of(o2.getCurrentPoints()).mapToInt(i -> i).sum()));

        GameResultRecyclerViewAdapter adapter = new GameResultRecyclerViewAdapter(getContext(), playerWithPointsList);

        results.setAdapter(adapter);
    }
}
