package hu.flowacademy.Hearthstone.Model;

import hu.flowacademy.Hearthstone.Model.Cards.Card;
import hu.flowacademy.Hearthstone.Model.Cards.Minion;
import hu.flowacademy.Hearthstone.Model.Heroes.Hero;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class Board {
    private ArrayList<Card> player1Hand;
    private ArrayList<Card> player2Hand;
    private Minion[] player1Boardside;
    private Minion[] player2Boardside;
    private ArrayList<Card> player1Deck;
    private ArrayList<Card> player2Deck;
    private boolean p1Turn;
    private Hero player1Hero;
    private Hero player2Hero;

    public Board() {
        this.player1Hand = new ArrayList<>();
        this.player2Hand = new ArrayList<>();
        this.player1Boardside = new Minion[5];
        this.player2Boardside = new Minion[5];
        this.player1Deck = new ArrayList<>();
        this.player2Deck = new ArrayList<>();
        this.p1Turn = true;
    }

    public ArrayList<Card> getPlayer1Hand() {
        return player1Hand;
    }


    public ArrayList<Card> getPlayer2Hand() {
        return player2Hand;
    }


    public Minion[] getPlayer1Boardside() {
        return player1Boardside;
    }

    public Minion[] getPlayer2Boardside() {
        return player2Boardside;
    }


    public ArrayList<Card> getPlayer1Deck() {
        return player1Deck;
    }


    public ArrayList<Card> getPlayer2Deck() {
        return player2Deck;
    }

    public boolean isP1Turn() {
        return p1Turn;
    }

    public void setP1Turn(boolean p1Turn) {
        this.p1Turn = p1Turn;
    }

    public Hero getPlayer1Hero() {
        return player1Hero;
    }

    public void setPlayer1Hero(Hero player1Hero) {
        this.player1Hero = player1Hero;
    }

    public Hero getPlayer2Hero() {
        return player2Hero;
    }

    public void setPlayer2Hero(Hero player2Hero) {
        this.player2Hero = player2Hero;
    }

    public void drawCardByPlayer1() {
        player1Hand.add(this.player1Deck.remove(new Random().nextInt(player1Deck.size())));
    }

    public void drawCardByPlayer2() {
        player2Hand.add(this.player2Deck.remove(new Random().nextInt(player2Deck.size())));
    }

    public Minion findMinionInPlayer1HandById(Integer cardId) {
        for (Card c : this.getPlayer1Hand()) {
            if (c.getId().equals(cardId)) {
                return (Minion) c;
            }
        }
        return null;
    }

    public Minion findMinionInPlayer2HandById(Integer cardId) {
        for (Card c : this.getPlayer2Hand()) {
            if (c.getId().equals(cardId)) {
                return (Minion) c;
            }
        }
        return null;
    }

    public void summonMinionByPlayer1(Minion minion, int index) {
        this.player1Boardside[index] = minion;
    }

    public void summonMinionByPlayer2(Minion minion, int index) {
        this.player2Boardside[index] = minion;
    }
}
