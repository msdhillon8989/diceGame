package com.stayconnected.dicegame.entity;

public class Rank {

    public Rank next;
    public Rank pre;

    private Player players;

    public Rank() {

    }

    public Rank(Player players) {
        this.players = players;
    }

    public Player getPlayer() {
        return players;
    }

}
