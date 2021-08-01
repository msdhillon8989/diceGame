package com.stayconnected.dicegame.entity;

public class Player {
    private final String name;
    private int score;

    private boolean finished;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    //Compare only account numbers
    @Override
    public boolean equals(Object obj) {
        Player other = (Player) obj;
        return name.equals(other.name);
    }



}
