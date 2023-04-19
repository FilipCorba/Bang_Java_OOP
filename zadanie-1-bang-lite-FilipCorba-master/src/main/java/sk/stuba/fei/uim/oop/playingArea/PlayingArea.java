package sk.stuba.fei.uim.oop.playingArea;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.blue.Barrel;

import sk.stuba.fei.uim.oop.cards.blue.Dynamite;
import sk.stuba.fei.uim.oop.cards.blue.Jail;
import sk.stuba.fei.uim.oop.cards.brown.*;

import sk.stuba.fei.uim.oop.gameStart.GameStart;
import sk.stuba.fei.uim.oop.players.Player;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


public class PlayingArea {
    protected Card card;
    protected GameStart gameStart;


    private ArrayList<Card> playingCards;
    private ArrayList<Card> playedCards;


    public PlayingArea(Player[] players) {
        this.playedCards = new ArrayList<>();
        this.playingCards = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            playingCards.add(new Barrel(this));
        }
        for (int i = 0; i < 1; i++) {
            playingCards.add(new Dynamite(this));
        }
        for (int i = 0; i < 3; i++) {
            playingCards.add(new Jail(this));
        }


        for (int i = 0; i < 30; i++) {
            playingCards.add(new Bang(this));
        }
        for (int i = 0; i < 15; i++) {
            playingCards.add(new Miss(this));
        }
        for (int i = 0; i < 8; i++) {
            playingCards.add(new Beer(this));
        }
        for (int i = 0; i < 6; i++) {
            playingCards.add(new CatBalou(this));
        }
        for (int i = 0; i < 2; i++) {
            playingCards.add(new Indians(this));
        }
        for (int i = 0; i < 4; i++) {
            playingCards.add(new Omnibus(this));
        }

        Collections.shuffle(playingCards);


        for (Player player : players) {
            for (int i = 0; i < 4; i++) {
                Card cardToAdd = getPlayingCards().get(0);
                player.addCardToHand(cardToAdd, this);
                playingCards.remove(0);
            }

        }


    }


    public void printBoardPlayersInRound(Player[] players, Player player) {

        System.out.println("----- Players Alive-----");


        for (int i = 0; i < players.length; i++) {
            if (Objects.equals(players[i].getName(), player.getName())) {
                continue;
            } else {
                if (players[i].getLives() > 0) {
                    System.out.println((i + 1) + "." + players[i].getName().toUpperCase() + " with " + players[i].getLives() + " lives.");

                }

            }


        }
        System.out.println("----- Players Dead-----");


        for (int i = 0; i < players.length; i++) {
            if (Objects.equals(players[i].getName(), player.getName())) {
                break;
            } else {
                if (players[i].getLives() == 0) {
                    System.out.println((i + 1) + "." + players[i].getName());

                }

            }


        }
        System.out.println("----- start -----");
    }

    public void printBoardPlayersBeforeNextRound(Player[] players) {

        System.out.println("----- Players Alive-----");


        for (int i = 0; i < players.length; i++) {


            if (players[i].getLives() > 0) {
                System.out.println((i + 1) + "." + players[i].getName().toUpperCase() + " with " + players[i].getLives() + " lives.");

            }


        }
        System.out.println("----- Players Dead-----");


        for (int i = 0; i < players.length; i++) {


            if (players[i].getLives() == 0) {
                System.out.println((i + 1) + "." + players[i].getName());

            }


        }
        System.out.println("----- start -----");
    }


    public void printBoardCards(ArrayList<Card> cards) {

        for (int i = 0; i < cards.size(); i++) {
            System.out.println("Card " + (i + 1) + ":" + cards.get(i).getName());
        }
    }


    public ArrayList<Card> getPlayingCards() {
        return playingCards;
    }

    public void removeplayedCardsOnHand(Player player, int cardNumber) {
        Card cardToAdd = player.getCardsOnHand().get(cardNumber);
        playedCards.add(cardToAdd);
        player.getCardsOnHand().remove(cardNumber);
    }

    public void removeplayedCardsInDesck(Player player, int cardNumber) {
        Card cardToAdd = player.getDeskCards().get(cardNumber);
        playedCards.add(cardToAdd);
        player.getDeskCards().remove(cardNumber);
    }

    public ArrayList<Card> getPlayedCards() {
        return playedCards;
    }


    public void checkDeck(Player player, Player[] players) {
        if (player.getDeskCards().size() >= 1) {
            for (int i = 0; i < player.getDeskCards().size(); i++) {
                if (Objects.equals(player.getDeskCards().get(i).getName(), "Dynamit")) {
                    System.out.println("----- In your deck in front of u you have" + player.getDeskCards().get(i).getName() + " -----");
                    ArrayList<Card> playableCards = player.getPlayableCardsOnDesk();
                    playableCards.get(i).playCard(player, players, i);
                }
            }
            for (int i = 0; i < player.getDeskCards().size(); i++) {
                if (Objects.equals(player.getDeskCards().get(i).getName(), "Jail")) {
                    System.out.println("----- In your deck in front of u you have" + player.getDeskCards().get(i).getName() + " -----");
                    this.card.playCard(player, players, i);
                    ArrayList<Card> playableCards = player.getPlayableCardsOnDesk();
                    playableCards.get(i).playCard(player, players, i);

                } else {
                    System.out.println("----- In your deck in front of u you have" + player.getDeskCards().get(i).getName() + " -----");
                }
            }
        } else {
            System.out.println("----- Your deck in front of u you is empty -----");
        }


    }


    public void getTwoCards(Player activePlayer) {
        if (playingCards.size() < 2) {
            getPlayedCards().addAll(getPlayedCards());
            for (int i = 0; i < 2; i++) {
                activePlayer.addCardToHand(this.getPlayingCards().get(0), this);
                this.getPlayingCards().remove(0);
            }
        } else {
            for (int i = 0; i < 2; i++) {
                activePlayer.addCardToHand(this.getPlayingCards().get(0), this);
                this.getPlayingCards().remove(0);
            }
        }

    }


}
