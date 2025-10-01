/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Kevin Duong
 * Section: 02 - 2PM
 * Lab Section: 01 - 10AM
 * Date: 4/11/2024
 * Time: 10:14 AM
 *
 * Project: csci205_final_project
 * Package: org.FinalProject.ModelClasses
 * Class: Monster
 *
 * Description: A class representing the monster that is being fought.
 *
 * ****************************************
 */
package org.FinalProject.ModelClasses;

import java.util.ArrayList;

public class Monster {
    // Represents the Health of a single monster
    private int health;
    // Total health of the monster
    private int maxHealth;
    // An array representing the different attacks that a monster can perform
    private ArrayList<Card> attackPattern;
    // A value to represent the monster's defense
    private int defense;
    // A value to represent the current action the monster is taking
    private int currAction;
    // A constructor for the monster itself holding the player being acted on, the health being changed and the defense.
    public Monster(int health) {
        this.maxHealth=health;
        this.health = health;
        this.defense = 0;
        this.currAction=-1;
        this.attackPattern=new ArrayList<>();
        setActionPattern();
    }
    // Basic getter for health
    public int getHealth() {return health;}
    // Basic getter for Max Health
    public int getMaxHealth() {return this.maxHealth;}
    // Basic getter for defense
    public int getDefense() {return defense;}

    // Iterates through the attack pattern and does the corresponding effect of the card.
    public Player playCard(Player player) {
        this.currAction=(this.currAction+1)%5;
        player.takeDamage(this.attackPattern.get(this.currAction));
        addDefense(this.attackPattern.get(this.currAction));
        return player;
    }
    // Sets the attack pattern of the monster
    public void setActionPattern() {
        this.attackPattern.add(new Card(0, "Attack", "Strike", 8,6,"","", ""));
        this.attackPattern.add(new Card(0, "Defend", "Block", 12,0,"","", ""));
        this.attackPattern.add(new Card(0, "Attack", "Cleave", 0,15,"","", ""));
        this.attackPattern.add(new Card(0, "Attack", "Parry", 10,3,"","", ""));
        this.attackPattern.add(new Card(0, "Attack", "Strike", 0,10,"","", ""));
    }

    /**
     * A method that takes a card and subtracts the health from the pool
     * @param card A card that has the damage
     */
    public void takeDamage(Card card) {
        int damage = card.getCardDamage();
        // Special Condition
        if(card.getCardModifier().equals("Remove")){
            this.defense = 0;
        }
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
     * Adds block to the defense variable
     * @param card A card that has the defense
     */

    public void addDefense(Card card) {this.defense=this.defense+card.getCardDefense();}

    /**
     * Resets the defense of the monster to 0
     */
    public void resetDefense() {this.defense=0;}

    /**
     * Get a string representation of the monster's intent
     * @return a string representations of the monster's intent
     */
    @Override
    public String toString() {
        String attack = ""+this.attackPattern.get((this.currAction + 1)%5).getCardDamage();
        String defense = ""+this.attackPattern.get((this.currAction + 1)%5).getCardDefense();
        return "Monster Attacking for: "+attack+"\nMonster Defending for: "+defense;
    }

    /**
     * Returns a list containing the values of the monster's intent
     * @return list with monster attack in [0] and monster defend in [1]
     */
    public ArrayList<Integer> getIntent(){
        ArrayList<Integer> retList = new ArrayList<>();
        retList.add(this.attackPattern.get((this.currAction + 1)%5).getCardDamage());
        retList.add(this.attackPattern.get((this.currAction + 1)%5).getCardDefense());
        return retList;
    }

    /**
     * Returns the percentage of the monster's health
     * @return the percentage of the monster's health as a double
     */
    public double healthPercent(){
        return (double) this.health /this.maxHealth;
    }
    /**
     * Returns the current action
     * @return the current action of the monster
     */
    public Card getAction(){
        return this.attackPattern.get(this.currAction);
    }
}
