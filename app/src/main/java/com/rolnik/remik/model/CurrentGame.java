package com.rolnik.remik.model;

import com.annimon.stream.Stream;

import java.util.ArrayList;
import java.util.List;



public class CurrentGame {
    public static final int WIN_POINTS = -50;

    private List<PlayerWithPoints> players;
    private long gameId;

    public CurrentGame() {
    }

    public CurrentGame(List<Player> players){
        this.players = Stream.of(players).map(PlayerWithPoints::new).toList();
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public List<GameHistory> finish(){
        List<GameHistory> gameHistories = new ArrayList<>();

        for (PlayerWithPoints playerWithPoints : this.players) {
            GameHistory gameHistory = createGameHistory(playerWithPoints);

            gameHistories.add(gameHistory);
        }

        players.clear();

        return gameHistories;
    }

    private GameHistory createGameHistory(final PlayerWithPoints playerWithPoints) {
        GameHistory gameHistory = new GameHistory();

        int pointsSum = Stream.of(playerWithPoints.getCurrentPoints()).mapToInt(integer -> integer).sum();

        gameHistory.setPoints(pointsSum);
        gameHistory.setPlayerId(playerWithPoints.getPlayer().getId());
        gameHistory.setGameId(gameId);

        return gameHistory;
    }

    public List<PlayerWithPoints> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerWithPoints> players) {
        this.players = players;
    }

    public long getGameId() {
        return gameId;
    }
}
