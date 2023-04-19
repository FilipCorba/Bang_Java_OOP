package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.players.Player;
import sk.stuba.fei.uim.oop.playingArea.PlayingArea;

public class Omnibus extends Card {
    private static final String CARD_NAME = "Omnibus";
    public Omnibus(PlayingArea playingArea){
        super(CARD_NAME, playingArea);

    }

    @Override
    public boolean canPlay(int index) {
        return false;
    }
    @Override
    public void playCard(Player player, Player [] players, int cardNumber) {
        super.playCard(player, players, cardNumber);
        this.playingArea.getTwoCards(player);
        this.playingArea.removeplayedCardsOnHand(player, cardNumber);


    }
}
