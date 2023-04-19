package sk.stuba.fei.uim.oop.cards.brown;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.players.Player;
import sk.stuba.fei.uim.oop.playingArea.PlayingArea;

import java.util.Objects;

public class Indians extends Card {
    private static final String CARD_NAME = "Indians";

    public Indians(PlayingArea playingArea) {
        super(CARD_NAME, playingArea);

    }

    @Override
    public boolean canPlay(int index) {
        return false;
    }

    public void playCard(Player player, Player[] players, int cardNumber) {
        super.playCard(player, players, cardNumber);
        for (Player value : players) {
            int indians = 0;
            if (value != player) {
                for (int j = 0; j < value.getCardsOnHand().size(); j++) {

                    if (value.getCardsOnHand().get(j) instanceof Bang) {
                        this.playingArea.removeplayedCardsOnHand(value, j);
                        j = j + value.getCardsOnHand().size();
                        indians++;
                        System.out.println("Player " + value.getName().toUpperCase() + " has lost card bang.");
                    }

                }
                if (indians == 0 && player.getLives()>0) {
                    value.removeLife();
                    System.out.println("Player " + value.getName().toUpperCase() + " has lost 1 live");
                }
            }
        }


        this.playingArea.removeplayedCardsOnHand(player, cardNumber);

    }
}
