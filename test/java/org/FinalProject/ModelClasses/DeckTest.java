package org.FinalProject.ModelClasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {

    // The deck to be used for each test
    Deck deck;


    // Set up a deck to be used for each test filled with duplicated cards
    @BeforeEach
    void setUp() {
        deck = new Deck("duplicatedCards.csv");
    }


    /**
     *  Test the deck's ability to shuffle cards form the discard pile into the draw pile
     */
    @Test
    void shuffleCards() {
        deck.shuffleCards();
        assertEquals(6, deck.getDrawPile().size());
        assertEquals(0, deck.getDiscardPile().size());
        deck.addToDiscard(deck.getDrawPile().getFirst());
        deck.shuffleCards();
        assertEquals(7, deck.getDrawPile().size());
        for (int i = 0; i < 200; i++) {
            deck.addToDiscard(deck.getDrawPile().getFirst());
        }
        deck.shuffleCards();
        assertEquals(207, deck.getDrawPile().size());

    }

    /**
     *  Test the deck's ability to generate a set of cards with a given CSV file0
     */
    @Test
    void generateSet() {
        assertEquals(6, deck.getDrawPile().size());
        assertEquals(0, deck.getDiscardPile().size());
        assertEquals("Name: Slash\nEnergy: 1\n Damage: 6\nDefense: 0\nDescription: Hurty hurt the monster"
                , deck.getDrawPile().getFirst().toString());
    }

    /**
     * Test the deck's ability to add more cards into its discard pile
     */
    @Test
    void addToDiscard() {
        Card card = deck.getDrawPile().getFirst();
        deck.addToDiscard(card);
        assertEquals(1, deck.getDiscardPile().size());
        assertEquals(card, deck.getDiscardPile().get(0));
        deck.addToDiscard(card);
        assertEquals(2, deck.getDiscardPile().size());
    }

    /**
     *  Get a hand from the deck that the player can use
     */
    @Test
    void getHand() {
        ArrayList<Card> cardList = deck.getHand();
        assertEquals(1, deck.getDrawPile().size());
        assertEquals(0, deck.getDiscardPile().size());
        assertEquals(5, cardList.size());
        assertEquals("Name: Slash\nEnergy: 1\n Damage: 6\nDefense: 0\nDescription: Hurty hurt the monster",
                deck.getDrawPile().getFirst().toString());
        assertEquals("Name: Slash\nEnergy: 1\n Damage: 6\nDefense: 0\nDescription: Hurty hurt the monster",
                cardList.get(0).toString());
        for (Card c : cardList){
            deck.addToDiscard(c);
        }
        deck.addToDiscard(cardList.get(0));
        cardList = deck.getHand();
        assertEquals(2, deck.getDrawPile().size());
        assertEquals(0, deck.getDiscardPile().size());
        assertEquals(5, cardList.size());
    }

    /**
     *  Test drawing a single card from the deck
     */
    @Test
    void drawCard(){
        Card card = deck.drawCard();
        assertEquals(5, deck.getDrawPile().size());
        assertEquals("Name: Slash\nEnergy: 1\n Damage: 6\nDefense: 0\nDescription: Hurty hurt the monster",
                card.toString());
        card = deck.drawCard();
        card = deck.drawCard();
        assertEquals(3, deck.getDrawPile().size());
        assertEquals("Name: Slash\nEnergy: 1\n Damage: 6\nDefense: 0\nDescription: Hurty hurt the monster",
                card.toString());
        card = deck.drawCard();
        card = deck.drawCard();
        card = deck.drawCard();
        assertEquals(0, deck.getDrawPile().size());
        assertEquals("Name: Slash\nEnergy: 1\n Damage: 6\nDefense: 0\nDescription: Hurty hurt the monster",
                card.toString());
        for (int i = 0; i < 10; i++) {
            deck.addToDiscard(card);
        }
        card = deck.drawCard();
        assertEquals(9, deck.getDrawPile().size());
        assertEquals("Name: Slash\nEnergy: 1\n Damage: 6\nDefense: 0\nDescription: Hurty hurt the monster",
                card.toString());
    }
}