package com.rolnik.remik.adapters;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.annimon.stream.Stream;
import com.rolnik.remik.R;
import com.rolnik.remik.databinding.GameBinding;
import com.rolnik.remik.model.Game;
import com.rolnik.remik.model.GameHistory;
import com.rolnik.remik.model.Player;
import com.rolnik.remik.model.PlayerWithGameHistory;
import com.rolnik.remik.utils.OnGameDeleteButtonClicked;
import com.rolnik.remik.viewholders.GameViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class GameRecyclerViewAdapter extends RecyclerView.Adapter<GameViewHolder> {
    private final Context context;
    private final List<Game> games;
    private final List<PlayerWithGameHistory> players;
    private final OnGameDeleteButtonClicked buttonClicked;
    private final List<Integer> clickedPositions = new ArrayList<>();

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        GameBinding gameBinding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.game, parent, false);

        return new GameViewHolder(gameBinding, context, parent, buttonClicked);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        Game game = games.get(position);

        holder.bind(game, filterGameHistory(game));

        if (clickedPositions.contains(position)) {
            holder.gameHistory.setVisibility(View.VISIBLE);
        } else {
            holder.gameHistory.setVisibility(View.GONE);
        }

        holder.date.setOnClickListener(v -> {
            int visibility = holder.gameHistory.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE;

            if (visibility == View.VISIBLE) {
                clickedPositions.add(position);
            } else {
                clickedPositions.remove(Integer.valueOf(position));
            }
            changeHolderGameHistoryVisibility(holder, visibility);
        });
    }

    @Override
    public int getItemCount() {
        return games.size();
    }

    public void changeData(final List<Game> games, final List<PlayerWithGameHistory> players) {
        this.games.clear();
        this.players.clear();

        this.games.addAll(games);
        this.players.addAll(players);

        notifyItemRangeInserted(0, games.size());
    }

    public Game get(int position) {
        return games.get(position);
    }

    public void remove(int position) {
        games.remove(position);

        notifyDataSetChanged();
    }

    private HashMap<Player, Integer> filterGameHistory(Game game) {
        HashMap<Player, Integer> playerWithPoints = new HashMap<>();

        for (PlayerWithGameHistory playerWithGameHistory : players) {
            List<GameHistory> playerGameHistory =
                    Stream.of(playerWithGameHistory.getGameHistories())
                    .filter(g -> g.getGameId() == game.getId())
                    .toList();

            if (playerGameHistory.size() > 0 ) {
                playerWithPoints.put(playerWithGameHistory.getPlayer(),
                        Stream.of(playerGameHistory)
                                .mapToInt(GameHistory::getPoints)
                                .sum());
            }

        }

        return playerWithPoints;
    }

    private void changeHolderGameHistoryVisibility(GameViewHolder holder, int visibility) {
        TransitionManager.beginDelayedTransition(holder.root);

        holder.gameHistory.setVisibility(visibility);
    }
}
