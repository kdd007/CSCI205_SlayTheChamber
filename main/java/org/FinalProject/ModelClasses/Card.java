/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: John Klopcic
 * Section: 2PM Lecture / 10AM Lab
 * Date: 4/10/24
 * Time: 7:37 PM
 *
 * Project: csci205_final_project
 * Package: org.FinalProject.ModelClasses
 * Class: Card
 *
 * Description:
 * A class that represents a card in our game
 * ****************************************
 */
package org.FinalProject.ModelClasses;


/**
 *  A class that represents a card for our game
 *  It has no unique methods and instead relies on its attributes
 */
public class Card {

    // The amount of energy the card costs to play
    private int energy;

    // The type of the card
    private String cardType;

    // The name of the card to be displayed
    private String cardName;

    // The damage the card deals when played
    private int cardDamage;

    // The defense the card adds when played
    private int cardDefense;


    // The description of what the card does to be displayed
    private String cardDescription;

    // The special modifier of the card
    private String modifier;

    // The name of the image for the card to display
    private String imgPath;

    /**
     * Basic constructor for the card class
     *
     * @param energy cost of card to play
     * @param cardType the type of the card
     * @param cardName the name of the card
     * @param defense the amount of defense the card adds
     * @param damage the amount of damage the card does
     * @param description the description of the card
     */
    public Card(int energy, String cardType, String cardName, int defense,
                int damage, String description, String modifier, String imgPath) {
        this.energy = energy;
        this.cardType = cardType;
        this.cardName = cardName;
        this.cardDefense = defense;
        this.cardDamage = damage;
        this.cardDescription = description;
        this.modifier = modifier;
        this.imgPath = imgPath;

    }

    /**
     * basic getter for card's energy
     * @return the int of the card's energy cost
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * getter for card type
     * @return a string of the card's type
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * getter for card's name
     * @return a string of the card's name
     */
    public String getCardName() {
        return cardName;
    }

    /**
     * getter for card's damage
     * @return an int of the card's damage
     */
    public int getCardDamage() {
        return cardDamage;
    }

    /**
     * getter for card's defense
     * @return an int of the card's defense
     */
    public int getCardDefense() {
        return cardDefense;
    }

    /**
     * getter for card's description
     * @return a string of the card's description
     */
    public String getCardDescription() {
        return cardDescription;
    }

    /**
     * getter for card's modifier
     * @return a string of the card's modifier
     */
    public String getCardModifier() {
        return this.modifier;
    }


    /**
     * getter for card's modifier
     * @return a string of the card's modifier
     */
    public String getImgPath() {
        return this.imgPath;
    }

    /**
     *  Creates a string representation of the card
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return "Name: "+this.getCardName()+"\nEnergy: "+this.getEnergy()
                +"\n Damage: "+this.getCardDamage()+"\nDefense: "
                +this.getCardDefense()+"\nDescription: "+this.getCardDescription();
    }
}