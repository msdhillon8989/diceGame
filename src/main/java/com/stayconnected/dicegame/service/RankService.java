package com.stayconnected.dicegame.service;


import com.stayconnected.dicegame.entity.Player;
import com.stayconnected.dicegame.entity.Rank;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RankService {

    final Rank head;
    final Rank tail;

    Map<Player, Rank> playerRank = new HashMap<>();

    RankService(List<Player> players) {
        head = new Rank();
        tail = new Rank();

        head.next = tail;
        tail.pre = head;

        for (Player p : players) {
            Rank rank = new Rank(p);
            playerRank.put(p, rank);
            add(rank);
        }
    }

    private void remove(Rank rank) {
        Rank next = rank.next;
        Rank pre = rank.pre;

        pre.next = next;
        next.pre = pre;

    }

    private void add(Rank rank) {
        add(head, rank);
    }

    private void add(Rank addTo, Rank rank) {
        Rank nextRank = addTo.next;

        addTo.next = rank;
        rank.pre = addTo;

        rank.next = nextRank;
        nextRank.pre = rank;
    }


    public void rearrangePlayer(Player p) {
        Rank rank = playerRank.get(p);

        Rank previousRank = rank.pre;
        boolean rankChanged = false;
        while (previousRank != head && !previousRank.getPlayer().isFinished() && previousRank.getPlayer().getScore() < p.getScore()) {
            previousRank = previousRank.pre;
            rankChanged = true;
        }
        if (rankChanged) {
            remove(rank);
            add(previousRank, rank);
        }
    }


    public void printRankTable(){
        Rank temp = head.next;

        int lastScore=-1;
        int rank = 1;
        System.out.println();
        System.out.println("=======================");
        System.out.println("Rank | Player   | Score");
        System.out.println("=======================");

        while(temp.next!=null){
            if((temp!=head.next && temp.getPlayer().isFinished()) || temp.getPlayer().getScore()<lastScore){
                rank++;
            }
            System.out.println(rank+"    | "+temp.getPlayer().getName()+" | "+temp.getPlayer().getScore() );
            lastScore=temp.getPlayer().getScore();
            temp=temp.next;
        }
        System.out.println("=======================");
        System.out.println();
    }
}
