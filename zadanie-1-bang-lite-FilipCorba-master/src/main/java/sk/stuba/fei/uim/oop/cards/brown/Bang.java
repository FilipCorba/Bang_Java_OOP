package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.players.Player;
import sk.stuba.fei.uim.oop.playingArea.PlayingArea;

public class Bang extends Card {
    private static final String CARD_NAME = "Bang";
    public Bang(PlayingArea playingArea){
        super(CARD_NAME, playingArea);

    }

    @Override
    public boolean canPlay(int index) {
        return false;
    }

    @Override
    public void playCard(Player player, Player [] players, int cardNumber) {
        super.playCard(player, players, cardNumber);
        int oposingPlayer = this.choosingOposingPlayer(players, player);
        this.checkLives(players[oposingPlayer]);

        System.out.println(players[oposingPlayer].getName());
        this.playingArea.removeplayedCardsOnHand(player, cardNumber);

    }
}
