package com.rolnik.remik.utils;

import com.annimon.stream.Optional;
import com.annimon.stream.Stream;
import com.rolnik.remik.model.Player;
import com.rolnik.remik.model.PlayerWithPoints;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

@AllArgsConstructor

public class PlayerPointsSpeechService {
    private final List<PlayerWithPoints> playerWithPoints;


    public void addPlayersPoints(final List<String> speech){
        HashMap<PlayerWithPoints, String> playersText = divideSpeechBetweenPlayers(speech);

        for(Map.Entry<PlayerWithPoints,String> entry : playersText.entrySet()){
            int points = castToNumber(entry.getValue());

            entry.getKey().addPoint(points);
        }
    }

    private HashMap<PlayerWithPoints, String> divideSpeechBetweenPlayers(List<String> speech) {
        HashMap<PlayerWithPoints, String> playersText = new HashMap<>();

        for(int i = 0; i < speech.size(); i++){
            String string = speech.get(i);

            Optional<PlayerWithPoints> playerOptional = Optional.ofNullable(getPlayerByNicknameOrName(string));

            if(playerOptional.isPresent()){
                Optional<String> stringOptional = Stream.of(speech).skip(i + 1).findFirst();

                stringOptional.ifPresent( s -> playersText.put(playerOptional.get(), s));
            }
        }

        return playersText;
    }

    private int castToNumber(String s) {
        try {
            return Integer.valueOf(s);
        } catch (NumberFormatException e){
            return 0;
        }
    }

    private PlayerWithPoints getPlayerByNicknameOrName(String string) {
        for(PlayerWithPoints playerWithPoints : playerWithPoints){
            Player player = playerWithPoints.getPlayer();

            if(player.getFirstName().toLowerCase().equals(string.toLowerCase()) ||
                player.getNickname().toLowerCase().equals(string.toLowerCase())){
                return playerWithPoints;
            }
        }

        return null;
    }
}
