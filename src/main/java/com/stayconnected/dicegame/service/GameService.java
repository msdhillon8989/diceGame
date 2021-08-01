package com.stayconnected.dicegame.service;


import com.stayconnected.dicegame.entity.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class GameService {

    private final int minScoreToWin;

    private final RankService rankService;
    private final ManageTurnService turnService;

    public GameService(int totalPlayers, int minScoreToWin) {
        final List<Player> playingPlayers = new ArrayList<>();
        this.minScoreToWin = minScoreToWin;

        for (int i = 1; i <= totalPlayers; i++) {
            playingPlayers.add(new Player("Player-"+i));
        }

        rankService = new RankService(playingPlayers);
        turnService = new ManageTurnService(playingPlayers);
    }





    private int rollDice() {


        double random = (Math.random() * 6);


        return (int) Math.ceil(random);

    }

    public void startGame() throws IOException {
        while (true) {

            Player player = turnService.getNextPlayerForTurn();
            if (player == null) {
                break;
            }

            System.out.println(player.getName() + "'s turn. Press Enter to roll the dice");

            int diceValue = getDiceValue(player);

            addScore(player, diceValue);

            turnService.validateLastDiceValue(diceValue, player);

            rankService.rearrangePlayer(player);

            rankService.printRankTable();

        }
    }








    private void addScore(Player player, int diceValue) {
        player.setScore(player.getScore() + diceValue);
        if (player.getScore() >= minScoreToWin) {
            player.setFinished(true);
        }
    }




    private int getDiceValue(Player player) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        int totalDiceValue = rollDice();

        System.out.println(player.getName() + " got " + totalDiceValue + " in diceRoll");

        if (totalDiceValue == 6) {
            System.out.println(player.getName() + " got another chance, Press Enter to roll dice again");
            br.readLine();
            int currentDiceValue = rollDice();
            totalDiceValue += currentDiceValue;
            System.out.println(player.getName() + " got " + currentDiceValue + " : total dice value = " + totalDiceValue);
        }
        return totalDiceValue;
    }

}
