package com.stayconnected.dicegame.service;


import com.stayconnected.dicegame.entity.Player;

import java.util.*;

public class ManageTurnService {

    private final Map<Player, Integer> lastScoreMap = new HashMap<>();

    private final Set<Player> skipTurn = new HashSet<>();
    private final List<Player> playingPlayers;
    private int currentTurn = -1;

    public ManageTurnService(List<Player> playingPlayers) {
        this.playingPlayers = playingPlayers;
        Collections.shuffle(this.playingPlayers);
    }

    public Player getNextPlayerForTurn() {

        while (true) {
            if (playingPlayers.size() <= 1) return null;
            currentTurn++;
            if (currentTurn >= playingPlayers.size()) {
                currentTurn = 0;
            }

            Player player = playingPlayers.get(currentTurn);

            if (skipTurn.contains(player)) {
                skipTurn.remove(player);
                System.out.println(player.getName() + "'s turn is skipped");
            } else {
                return player;
            }
        }
    }


    public void validateLastDiceValue(int diceValue, Player player) {

        if (diceValue % 6 == 1) {
            Integer lastScore = lastScoreMap.get(player);
            if (lastScore != null && lastScore == 1) {
                skipTurn.add(player);
                lastScoreMap.put(player, 0);
                System.out.println(player.getName() + " got 1 twice. Next turn will be skipped");
                return;
            }
        }
        lastScoreMap.put(player, diceValue %= 6);

        if (player.isFinished()) {
            removeFromPlaying();
        }
    }


    private void removeFromPlaying() {
        playingPlayers.remove(currentTurn);
        currentTurn--;
    }


}
