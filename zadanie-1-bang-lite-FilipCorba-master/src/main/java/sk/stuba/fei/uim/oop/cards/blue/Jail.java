package sk.stuba.fei.uim.oop.cards.blue;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.players.Player;
import sk.stuba.fei.uim.oop.playingArea.PlayingArea;

public class Jail extends Card {
    private static final String CARD_NAME = "Jail";
    public Jail(PlayingArea playingArea){
        super(CARD_NAME, playingArea);

    }

    @Override
    public boolean canPlay(int index) {
        return false;
    }

    @Override
    public void playCard(Player player, Player [] players, int cardNumber) {
        super.playCard(player, players, cardNumber);


    }
}
