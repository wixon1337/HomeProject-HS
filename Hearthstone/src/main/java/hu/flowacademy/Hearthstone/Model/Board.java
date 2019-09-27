package hu.flowacademy.Hearthstone.Model;

import hu.flowacademy.Hearthstone.Model.Cards.Card;
import hu.flowacademy.Hearthstone.Model.Cards.Minion;

import java.util.ArrayList;
import java.util.LinkedList;

public class Board {
    private ArrayList<Card> player1Hand;
    private ArrayList<Card> player2Hand;
    private LinkedList<Minion> player1Boardside;
    private LinkedList<Minion> player2Boardside;
    private ArrayList<Card> player1Deck;
    private ArrayList<Card> player2Deck;

    public Board() {
        this.player1Hand = new ArrayList<>();
        this.player2Hand = new ArrayList<>();
        this.player1Boardside = new LinkedList<Minion>();
        this.player2Boardside = new LinkedList<Minion>();
        this.player1Deck = new ArrayList<>();
        this.player2Deck = new ArrayList<>();
    }

    public void drawCardByPlayer1() {
        player1Hand.add(this.player1Deck.remove(player1Deck.size()-1));
    }

    public void drawCardByPlayer2() {
        player2Hand.add(this.player2Deck.remove(player2Deck.size()-1));
    }


    public ArrayList<Card> getPlayer1Hand() {
        return player1Hand;
    }


    public ArrayList<Card> getPlayer2Hand() {
        return player2Hand;
    }


    public LinkedList<Minion> getPlayer1Boardside() {
        return player1Boardside;
    }

    public LinkedList<Minion> getPlayer2Boardside() {
        return player2Boardside;
    }


    public ArrayList<Card> getPlayer1Deck() {
        return player1Deck;
    }


    public ArrayList<Card> getPlayer2Deck() {
        return player2Deck;
    }

}
