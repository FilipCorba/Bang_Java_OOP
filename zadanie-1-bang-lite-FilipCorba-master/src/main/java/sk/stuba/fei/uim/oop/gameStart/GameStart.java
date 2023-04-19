package sk.stuba.fei.uim.oop.gameStart;


import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.players.Player;
import sk.stuba.fei.uim.oop.playingArea.PlayingArea;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;

public class GameStart {
    private final Player[] players;
    private PlayingArea playingArea;
    private Player player;
    private int currentPlayer;
    private boolean continuePlay = true;

    public GameStart() {

        System.out.println("--- Welcome to FEI BANG  ---");
        int numberPlayers = 0;
        while (numberPlayers < 2 || numberPlayers > 4) {
            numberPlayers = ZKlavesnice.readInt("*** Enter number of players (2-4): ***");
            if (numberPlayers < 2 || numberPlayers > 4) {
                System.out.println(" !!! You enter wrong number of players. Try Again! !!!");
            }
        }
        this.players = new Player[numberPlayers];
        for (int i = 0; i < numberPlayers; i++) {
            this.players[i] = new Player(ZKlavesnice.readString("*** Enter name for PLAYER " + (i + 1) + " : ***"));
        }

        this.playingArea = new PlayingArea(this.players);
        this.startingGame(players);

    }

    private void startingGame(Player[] players) {
        System.out.println("--- GAME STARTED ---");
        while (this.getNumberOfActivePlayers() > 1) {
            this.playingArea.printBoardPlayersBeforeNextRound(players);
            for(Player p : players){
                Player activePlayer = this.players[this.currentPlayer];


                if (!activePlayer.isActive()) {
                    continuePlay = true;
                    this.incrementCounter();
                }
                else{
                    System.out.println("--- PLAYER " + activePlayer.getName().toUpperCase() + " STARTS TURN ---");
                    System.out.println("--- " + activePlayer.getName().toUpperCase() + "'s lives: " + activePlayer.getLives() + " ---");

                    this.playingArea.getTwoCards(activePlayer);
                    while (continuePlay) {
                        this.makeTurn(activePlayer);
                    }
                    continuePlay = true;
                    this.incrementCounter();
                }


            }
        }
            System.out.println("--- GAME FINISHED ---");





    }

    public boolean isContinuePlay() {
        return continuePlay;
    }

    private void makeTurn(Player activePlayer) {



        this.playingArea.checkDeck(activePlayer, this.getPlayers());

        ArrayList<Card> playableCards = activePlayer.getPlayableCardsOnHand();


        if (playableCards.size() != 0 && activePlayer.getLives() >0 && !activePlayer.isJail()) {

            this.playCard(playableCards, activePlayer);
            if (ZKlavesnice.readInt("*** Do u wish to continue with your play? if yes push 1 if no push 2 ***") == 2) {
                continuePlay = false;

                while (activePlayer.getCardsOnHand().size() > activePlayer.getLives()) {

                    this.playingArea.printBoardCards(activePlayer.getCardsOnHand());
                    for (int i = 0; i < activePlayer.getCardsOnHand().size() - activePlayer.getLives(); i++) {
                        while (true) {
                            int numberCard = ZKlavesnice.readInt("*** Choose card to send away ***") - 1;
                            if (numberCard < 0 || numberCard > activePlayer.getCardsOnHand().size() -1) {
                                System.out.println(" !!! You enter wrong number of card. Try Again! !!! ");
                            } else {
                                activePlayer.getCardsOnHand().remove(numberCard);
                                break;
                            }
                        }



                    }
                }
            }


        }
        else {
            activePlayer.setJail(false);

        }



    }



    private int getNumberOfActivePlayers() {
        int count = 0;
        for (Player player : this.players) {
            if (player.isActive()) {
                count++;
            }
        }
        return count;
    }

    private void playCard(ArrayList<Card> playableCards, Player activePlayer) {
        System.out.println("--- Playable cards on hand ---");
        int numberCard = choosingCard(playableCards, "PLAY");
        playableCards.get(numberCard).playCard(activePlayer, this.getPlayers(), numberCard);

    }

    private int choosingCard(ArrayList<Card> cards, String verb) {
        this.playingArea.printBoardCards(cards);
        int numberCard = 0;
        while (true) {
            numberCard = ZKlavesnice.readInt("*** Enter number of card you want to " + verb + ": ***") - 1;
            if (numberCard < 0 || numberCard > cards.size() - 1) {
                System.out.println(" !!! You enter wrong number of card. Try Again! !!! ");
            } else {
                break;
            }
        }
        return numberCard;
    }

    private void incrementCounter() {
        this.currentPlayer++;
        this.currentPlayer %= this.players.length;
    }

    public Player[] getPlayers() {
        return players;
    }
}
