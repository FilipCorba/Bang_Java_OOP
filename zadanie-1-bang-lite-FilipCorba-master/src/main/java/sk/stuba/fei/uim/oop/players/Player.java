package sk.stuba.fei.uim.oop.players;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.gameStart.GameStart;
import sk.stuba.fei.uim.oop.playingArea.PlayingArea;


import java.util.ArrayList;
import java.util.Collections;

public class Player {
    private PlayingArea playingArea;
    private GameStart gameStart;
    private final String name;
    private int lives;
    private ArrayList<Card> cardsOnHand;
    private ArrayList<Card> deskCards;
    private boolean jail;

    public boolean isJail() {
        return jail;
    }

    public void setJail(boolean jail) {
        this.jail = jail;
    }

    public Player(String name) {
        this.cardsOnHand = new ArrayList<Card>();
        this.deskCards = new ArrayList<Card>();
        this.name = name;
        this.lives = 1;
        this.jail = false;


    }

    public ArrayList<Card> getCardsOnHand() {


        return cardsOnHand;
    }

    public ArrayList<Card> getDeskCards() {
        return deskCards;
    }

    public String getName() {
        return name;
    }

    public int getLives() {
        return lives;
    }


    public ArrayList<Card> removeCardsFromHand() {
        ArrayList<Card> removedCards = new ArrayList<>(this.cardsOnHand);
        this.cardsOnHand.clear();
        return removedCards;
    }

    public void addCardToHand(Card card, PlayingArea playingArea) {
        if(playingArea.getPlayingCards().size() == 0){
            playingArea.getPlayedCards().addAll(playingArea.getPlayedCards());
            cardsOnHand.add(card);
        }
        else {
            cardsOnHand.add(card);
        }





    }

    public ArrayList<Card> getPlayableCardsOnHand() {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (Card card : this.cardsOnHand) {
            cards.add(card);
        }
        return cards;
    }

    public ArrayList<Card> getPlayableCardsOnDesk() {
        ArrayList<Card> cards = new ArrayList<Card>();
        for (Card card : this.deskCards) {
            cards.add(card);
        }
        return cards;
    }

    public ArrayList<Card> getAllCards() {
        return this.cardsOnHand;
    }

    public boolean isActive() {
        return this.lives > 0;
    }

    public void removeLife() {
        if (this.lives > 0) {
            this.lives--;
        }


    }

    public void addLife() {
        this.lives++;
    }


}