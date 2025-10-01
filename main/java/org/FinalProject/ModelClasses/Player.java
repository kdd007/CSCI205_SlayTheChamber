/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Kevin Duong
 * Section: 02 - 2PM
 * Lab Section: 01 - 10AM
 * Date: 4/11/2024
 * Time: 10:08 AM
 *
 * Project: csci205_final_project
 * Package: org.FinalProject.ModelClasses
 * Class: Player
 *
 * Description:
 *
 * ****************************************
 */
package org.FinalProject.ModelClasses;

import java.util.ArrayList;
import java.util.Deque;

public class Player {
    // The arrayList representing the list of cards in the hand
    private ArrayList<Card> playerHand;
    // The list of all the monsters we are facing at any given time.
    private ArrayList<Monster> monsterList;
    // The Deck object holding the cards we want to use.
    private Deck playerDeck;
    // Represents the Health of a single monster
    private int health;
    // Total health of the player
    private int maxHealth;
    // A value to represent the monster's defense
    private int defense;
    // A value to represent how much energy we have each turn
    private int energy;
    // A value to represent how much maximum energy we have each turn
    private int maxEnergy;

    /**
     * A constructor for the monster itself holding the player being acted on, the health being changed and the defense.
     * @param health Health of the Player
     */
    public Player(int health,String deckName) {
        this.maxHealth=health;
        this.health = health;
        this.defense = 0;
        this.playerDeck= new Deck(deckName);
        this.playerHand= new ArrayList<>();
        this.energy = 3;
        this.maxEnergy = 3;
    }

    /**
     * A method that takes a card and inflicts damage onto the player
     * @param card
     */
    public void takeDamage(Card card) {
        int damage = card.getCardDamage();
        for (int i = 0; i < damage; i++) {
            if(this.defense != 0){
                this.defense --;
            }
            else {
                this.health --;
            }
        }
    }
    /**
     * A method that changes the health without a Card.
     */
    public void changeHP(int value) {
        if (this.health+value < maxHealth) {
            this.health = this.health + value;
        }
        else{
            this.health=maxHealth;
        }
    }

    /**
     *  Adds block to the defense variable
     * @param card Takes in a card to get the value of how much we want to add
     */
    public void addDefense(Card card) {this.defense=this.defense+card.getCardDefense();}

    /**
     * Resets the defense of the monster to 0
     */
    public void resetDefense() {this.defense=0;}

    /**
     * A method which would call the method to get 5 cards from the deck
     */
    public void drawHand(){
        this.playerHand=this.playerDeck.getHand();
    }

    /**
     * A method which adds the cards in the remaining hand to the discard and removes it from the hand
     */
    public void discardHand(){
        for (Card card : playerHand) {
            if (card !=null) {
                this.playerDeck.addToDiscard(card);
            }
        }
        this.playerHand.clear();
    }

    /**
     * Plays the effect of any card, if it is a damage card, then it the index would tell us which monster we are
     * attacking
     * @param monsterIndex The index of the monster we are attacking
     * @param card The card that is being played
     */
    public ArrayList<Monster> playCard(int monsterIndex, ArrayList<Monster> monsterList, Card card) {
        if (this.energy-card.getEnergy()>=0) {
            // Handles AOE cards
            if (card.getCardModifier().equals("AOE")) {
                for (Monster monster : monsterList) {
                    monster.takeDamage(card);
                }
                addDefense(card);
            }
            else if(card.getCardModifier().equals("Gamble")){
                discardHand();
                drawHand();
                this.energy = this.energy - card.getEnergy();
                int cardIdx = playerHand.indexOf(card);
                // Find the index of the first occurrence of the element to replace
                int index = playerHand.indexOf(card);
                if (index != -1) { // If the element is found
                    playerHand.set(index, null); // Replace the element at that index
                }
                return monsterList;
            }
            // Handles Draw Cards
            else if (card.getCardModifier().equals("Draw")) {
                changeHP(card.getCardDefense()); // If there is an HP cost, subtract it
                for (int i = 0; i < card.getCardDamage(); i++) { // Draw cards equal to the damage value
                    this.playerHand.add(playerDeck.drawCard());
                }
            } else if (card.getCardModifier().equals("Recovery")) {
                changeHP(card.getCardDamage());
                addDefense(card);
            }
            // Handles Single Target cards
            else if (monsterIndex != -1) {
                monsterList.get(monsterIndex).takeDamage(card);
                addDefense(card);
            }
            this.energy = this.energy - card.getEnergy();
            playerDeck.addToDiscard(card);
            int cardIdx = playerHand.indexOf(card);
//        playerHand.remove(cardIdx);
            // Find the index of the first occurrence of the element to replace
            int index = playerHand.indexOf(card);
            if (index != -1) { // If the element is found
                playerHand.set(index, null); // Replace the element at that index
            }
        }

        return monsterList;
    }

    /**
     * Method that adds a card to the hand
     * @param card
     */
    public void addCard(Card card) {
        this.playerHand.add(card);
    }
//    /**
//     * Basic setter for the list of monster
//     * @param monsterList A list with different monsters.
//     */
//    public void setMonsterList(ArrayList<Monster> monsterList) {this.monsterList = monsterList;}

    // Basic getter for health
    public int getHealth() {return health;}
    // Basic getter for Max Health
    public int getMaxHealth() {return this.maxHealth;}
    // Basic getter for defense
    public int getDefense() {return defense;}
    // Basic getter for energy
    public int getEnergy() {return energy;}
    // Basic getter for getting the size of the hand.
    public int getPlayerHandSize() {
        int count = 0;
        for (Card card : playerHand) {
            if (card != null) {
                count++;
            }
        }
        return count;
    }
    // Basic setter for energy
    public void setEnergy(int energy) {this.maxEnergy = energy;}
    // Method which resets the energy
    public void resetEnergy() {this.energy=this.maxEnergy;}
    // Basic getter for player hand
    public ArrayList<Card> getPlayerHand(){return this.playerHand;}

    /**
     * Returns the cards remaining in the deck
     */
    public Deque<Card> getDeck() {
        return this.playerDeck.getDeck();
    }
    /**
     * Returns the size of the cards remaining in the deck
     */
    public int getDeckSize () {
        return this.playerDeck.getDeckSize();
    }
    /**
     * Returns the cards in the discard pile
     */
    public ArrayList<Card> getDiscard () {
        return this.playerDeck.getDiscard();
    }
    /**
     * Returns the size of the discard pile
     */
    public int getDiscardSize () {
        return this.playerDeck.getDiscardSize();
    }

    /**
     * Returns the percentage of the player's health
     * @return the percentage of the player's health as a double
     */
    public double healthPercent(){
        return (double) this.health /this.maxHealth;
    }

}
