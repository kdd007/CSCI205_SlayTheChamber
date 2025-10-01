/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: John Klopcic
 * Section: 2PM Lecture / 10AM Lab
 * Date: 4/11/24
 * Time: 10:05 AM
 *
 * Project: csci205_final_project
 * Package: org.FinalProject.ModelClasses
 * Class: Deck
 *
 * Description:
 * The class that represents a deck of cards
 * ****************************************
 */
package org.FinalProject.ModelClasses;

import java.io.*;
import java.util.*;

/**
 *  The class that represents our deck of cards
 */
public class Deck {
    // The size of a player's hand
    public final static int HAND_SIZE = 5;

    // The list of all the cards that can be in the deck
    private ArrayList<Card> totalCards;

    // The current draw pile of the deck
    private Deque<Card> drawPile;

    // The current discard pile of the deck
    private ArrayList<Card> discardPile;

    /**
     * basic getter for all cards in deck
     * @return a list of all cards in deck
     */
    public ArrayList<Card> getTotalCards() {
        return totalCards;
    }

    /**
     * basic getter for draw pile
     * @return the queue of the drawpile
     */
    public Deque<Card> getDrawPile() {
        return drawPile;
    }

    /**
     * basic getter for discard pile
     * @return the discard pile list
     */
    public ArrayList<Card> getDiscardPile() {
        return discardPile;
    }

    /**
     *  Constructor for the deck
     *  @param string the CSV file that the deck uses to build itself
     */
    public Deck(String fileName){
        totalCards = new ArrayList<>();
        drawPile = new LinkedList<>();
        discardPile = new ArrayList<>();
        this.generateSet(fileName);
    }

    /**
     *  Shuffle the discard pile into the draw pile
     */
    public void shuffleCards(){
        Collections.shuffle(this.discardPile);
        for (Card card:discardPile){
            this.drawPile.addLast(card);
        }
        this.discardPile.clear();
    }

    /**
     * Takes in a CSV of the deck we want and generates the cards
     * that the deck keeps track of.
     * @param fileName the csv file name to read the data from
     */
    public void generateSet(String fileName){
        InputStream inStream = Card.class.getClassLoader().getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));
        try{
            // Read a line and set variables to the values they should be
            String temp = reader.readLine();
            while((temp = reader.readLine()) != null){
                //temp = reader.readLine();
                String[] data = temp.split(",");
                String cardName = data[0];
                String cardType = data[1];
                int energy = Integer.parseInt(data[2]);
                int cardDefense = Integer.parseInt(data[3]);
                int cardAttack = Integer.parseInt(data[4]);
                String cardDescription = data[5];
                String modifier = data[6];
                String imgPath = data[7];
                // Add the new card to our discard pile
                this.discardPile.add(new Card(energy, cardType,
                        cardName, cardDefense, cardAttack,
                        cardDescription, modifier, imgPath));
            }
        // If we smart this shouldn't happen
        } catch(IOException e){
            System.out.println("Error mishandled file: " + e);
        }
        // Put the new cards into the draw pile
        this.shuffleCards();
    }

    /**
     * The method to add a card to the discard pile
     * used when we want to discard or play any cards
     * @param card the card to discard
     */
    public void addToDiscard(Card card){
        this.discardPile.add(card);
    }

    /**
     * Gives the player a list of cards drawn for the hand
     * @return a list of Cards
     */
    public ArrayList<Card> getHand(){
        ArrayList<Card> retList = new ArrayList<>();
        for (int i = 0; i < HAND_SIZE; i++) {
            if(drawPile.isEmpty()){
                this.shuffleCards();
            }
            retList.add(this.drawPile.removeFirst());
        }
        return retList;
    }


    /**
     * Draws one card from the deck and returns it
     * @return the card drawn
     */
    public Card drawCard(){
        if(drawPile.isEmpty()){
            this.shuffleCards();
        }
        return this.drawPile.removeFirst();
    }

    /**
     * Returns the cards remaining in the deck
     */
    public Deque<Card> getDeck(){
        return drawPile;
    }
    /**
     * Returns the size of the cards remaining in the deck
     */
    public int getDeckSize(){
        return drawPile.size();
    }
    /**
     * Returns the cards in the discard pile
     */
    public ArrayList<Card> getDiscard(){
        return discardPile;
    }
    /**
     * Returns the size of the discard pile
     */
    public int getDiscardSize(){
        return discardPile.size();
    }
}