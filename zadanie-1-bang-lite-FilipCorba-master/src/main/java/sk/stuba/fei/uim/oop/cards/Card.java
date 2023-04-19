package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.cards.blue.Barrel;
import sk.stuba.fei.uim.oop.cards.brown.Bang;
import sk.stuba.fei.uim.oop.cards.brown.Miss;
import sk.stuba.fei.uim.oop.players.Player;
import sk.stuba.fei.uim.oop.playingArea.PlayingArea;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.Objects;
import java.util.Random;

public abstract class Card {
    Random rand = new Random();
    private Player[] players;
    private Player player;
    protected String name;
    protected PlayingArea playingArea;
    protected Card card;

    public Card(String name, PlayingArea playingArea) {
        this.name = name;
        this.playingArea = playingArea;

    }

    public abstract boolean canPlay(int index);

    public String getName() {
        return name;
    }

    public void playCard(Player player, Player[] players, int cardNumber) {
        System.out.println("--- " + player.getName().toUpperCase() + " choose " + this.name + " card to play. ---");
    }

    public void removeCard(Player player) {

    }

    public void checkLives(Player player) {
        System.out.println(player.getName());


        int outcome = checkBarrel(player);


        if (outcome != 1) {


            checkMiss(player);
        }

    }

    private int checkBarrel(Player player) {
        int cardFound = 0;

        for (int i = 0; i < player.getDeskCards().size(); i++) {
            if (player.getDeskCards().get(i) instanceof Barrel) {
                int barrelHideout = 0;
                while (barrelHideout < 1 || barrelHideout > 4) {
                    barrelHideout = ZKlavesnice.readInt("*** Enter index of barrel:***");
                    if (barrelHideout < 1 || barrelHideout > 4) {
                        System.out.println(" !!! You enter wrong number  Try Again! !!!");
                    }
                }
                int randomValue = rand.nextInt(4);

                if (barrelHideout == randomValue + 1) {
                    System.out.println("--- They found your barrel ---");

                    cardFound = 2;
                    return cardFound;
                }
                cardFound = 1;
                System.out.println("--- They didn't found you ---");

                return cardFound;
            }

        }
        return cardFound;
    }

    private void checkMiss(Player player) {
        int bullet = 0;
        for (int i = 0; i < player.getCardsOnHand().size(); i++) {
            if (player.getCardsOnHand().get(i) instanceof Miss) {

                System.out.println("--- " + player.getName().toUpperCase() + " ate bullet. ---");
                this.playingArea.removeplayedCardsOnHand(player, i);
                bullet = 1;
                i = i + player.getCardsOnHand().size();
            }


        }
        if (bullet != 1) {
            System.out.println("--- " + player.getName().toUpperCase() + " got hit. ---");
            player.removeLife();
        }
    }

    protected void checkAmountOfCards(Player player) {
        int chooseDeck = 0;
        if (player.getCardsOnHand().size() == 0 && player.getDeskCards().size() == 0) {
            System.out.println("--- You can't play this card on" + player.getName() + "right now. ---");
        } else {
            if (player.getCardsOnHand().size() != 0 && player.getDeskCards().size() == 0) {
                player.getCardsOnHand().remove(rand.nextInt(player.getCardsOnHand().size()));
            } else if (player.getCardsOnHand().size() == 0) {
                player.getDeskCards().remove(rand.nextInt(player.getDeskCards().size()));
            } else {
                while (true) {

                    chooseDeck = ZKlavesnice.readInt("*** If you want to remove card from Hand push 1 If you want to remove card from Desk push 2 ***");
                    if (chooseDeck > 2 || chooseDeck < 1) {
                        System.out.println(" !!! You enter wrong number! !!!");
                    } else {
                        if (chooseDeck == 1) {
                            this.playingArea.removeplayedCardsOnHand(player, rand.nextInt(player.getCardsOnHand().size()));
                            break;
                        } else {
                            this.playingArea.removeplayedCardsInDesck(player, rand.nextInt(player.getDeskCards().size()));
                            break;
                        }
                    }
                    break;


                }
            }
        }
    }

    protected int choosingOposingPlayer(Player[] players, Player player) {
        this.playingArea.printBoardPlayersInRound(players, player);
        int numberCard = 0;
        while (true) {
            numberCard = ZKlavesnice.readInt("*** choose who u want to attack ***") - 1;
            if (numberCard > players.length || player == players[numberCard]) {
                System.out.println(" !!! You enter wrong number! !!!");
            } else {
                break;
            }
        }
        return numberCard;
    }


    protected void isBarrelOntheDesk(Player player, int cardNumber) {
        int inside = 0;
        if (player.getDeskCards().size() == 0) {
            Card cardToAdd = player.getCardsOnHand().get(cardNumber);
            player.getDeskCards().add(cardToAdd);
            player.getCardsOnHand().remove(cardNumber);
            System.out.println("--- YOU PUT BARREL INTO THE DECK ON YOUR DESK IN FRONT OF YOU. ---");
        } else {
            for (int i = 0; i < player.getDeskCards().size(); i++) {
                if (player.getDeskCards().get(i) instanceof Barrel) {
                    System.out.println(" !!! YOU ALREADY HAVE BARREL INFRONT OF YOU! !!!");
                    inside++;
                }

            }
            if (inside == 0) {
                Card cardToAdd = player.getCardsOnHand().get(cardNumber);
                player.getDeskCards().add(cardToAdd);
                player.getCardsOnHand().remove(cardNumber);
                System.out.println("--- YOU PUT BARREL INTO THE DECK ON YOUR DESK IN FRONT OF YOU. ---");
            }
        }
    }

    protected void Dynamite(Player[] players, Player player, int cardNumber) {
        int randomValue = rand.nextInt(8) + 1;
        while (true) {
            int chanceNumber = ZKlavesnice.readInt("*** Choose your number from 1 to 8. ***");
            if (chanceNumber > 8 || chanceNumber < 1) {
                System.out.println(" !!! You enter wrong number! !!!");
            } else {
                if (randomValue == chanceNumber) {
                    System.out.println("--- You have been hit with Dynamite. ---");
                    for (int i = 0; i < 3; i++) {
                        player.removeLife();
                    }
                    break;
                } else {
                    System.out.println("--- You have God on your side Dynamite has been moved to the previous player.  ---");
                    for (int i = 0; i < players.length; i++) {
                        if (player == players[i]) {
                            if (i != 0) {
                                Card cardToAdd = player.getCardsOnHand().get(cardNumber);
                                players[i - 1].getDeskCards().add(cardToAdd);
                                player.getCardsOnHand().remove(cardNumber);
                                break;
                            } else {
                                Card cardToAdd = player.getCardsOnHand().get(cardNumber);
                                players[players.length - 1].getDeskCards().add(cardToAdd);
                                player.getCardsOnHand().remove(cardNumber);
                                break;
                            }

                        }
                    }
                    break;
                }
            }

        }


    }

    protected void Jail(Player[] players, Player player, int cardNumber) {
        int randomValue = rand.nextInt(4) + 1;
        while (true) {
            int chanceNumber = ZKlavesnice.readInt("*** Choose your number from 1 to 4. ***");
            if (chanceNumber > 4 || chanceNumber < 1) {
                System.out.println(" !!! You enter wrong number! !!!");
            } else {
                if (randomValue == chanceNumber) {

                        player.setJail(false);
                        break;

                } else {
                    for (int i = 0; i < players.length; i++) {
                        if (player == players[i]) {
                            if (i != 0) {
                                Card cardToAdd = player.getCardsOnHand().get(cardNumber);
                                players[i - 1].getDeskCards().add(cardToAdd);
                                player.getCardsOnHand().remove(cardNumber);
                                break;
                            } else {
                                Card cardToAdd = player.getCardsOnHand().get(cardNumber);
                                players[players.length - 1].getDeskCards().add(cardToAdd);
                                player.getCardsOnHand().remove(cardNumber);
                                break;
                            }

                        }
                    }
                    break;
                }
            }
        }


    }


}
