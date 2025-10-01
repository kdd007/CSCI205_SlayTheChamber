package org.FinalProject.ModelClasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    Monster monster;
    Player player;
    ArrayList<Monster> monsters;
    Card basicAttack;
    Card basicDefense;
    Card basicDraw;
    Card basicRecovery;

    @BeforeEach
    void setUp() {
        this.basicAttack=new Card(0, "Attack", "Strike", 0,6,"","Single Target","");
        this.basicDefense=new Card(0, "Defense", "Defend", 12,0,"","Self","");
        this.basicDraw=new Card(0, "Skill", "Draw", 0,1,"","Draw","");
        this.basicRecovery=new Card(0, "Skill", "Draw", 0,6,"","Recovery","");
        this.player = new Player(80,"testDeck.csv");
        this.monsters = new ArrayList<>();
        this.monster= new Monster(100);
        this.monsters.add(monster);
    }

    /**
     * Test that we can take damage properly and it subtracts the right amount
     */
    @Test
    void testTakeDamage() {
        assertEquals(80,this.player.getHealth());
        this.player.takeDamage(this.basicAttack);
        assertEquals(74,this.player.getHealth());
    }

    /**
     * Test that we can add to our defense
     */
    @Test
    void testAddDefense() {
        assertEquals(0,this.player.getDefense());
        this.player.addDefense(this.basicDefense);
        assertEquals(12,this.player.getDefense());
    }

    /**
     * Test that we can draw a hand of 5
     */
    @Test
    void testDrawHand() {
        assertEquals(0, this.player.getPlayerHandSize());
        this.player.drawHand();
        assertEquals(5, this.player.getPlayerHandSize());
    }

    /**
     * Test that we can remove every card in our hand regardless of the number of cards in the hand
     */
    @Test
    void testDiscardHand() {
        assertEquals(0, this.player.getPlayerHandSize());
        this.player.drawHand();
        assertEquals(5, this.player.getPlayerHandSize());
        this.player.discardHand();
        assertEquals(0, this.player.getPlayerHandSize());
        this.player.addCard(this.basicDraw);
        this.monsters= player.playCard(0,this.monsters, this.basicDraw);
        assertEquals(1, this.player.getPlayerHandSize());
        this.player.discardHand();
        assertEquals(0, this.player.getPlayerHandSize());
    }

    /**
     * Test that we can add a specific card to the hand.
     */
    @Test
    void testAddCard(){
        assertEquals(0,this.player.getPlayerHandSize());
        this.player.addCard(this.basicDefense);
        assertEquals(1,this.player.getPlayerHandSize());
        this.player.addCard(this.basicDraw);
        assertEquals(2,this.player.getPlayerHandSize());
    }

    /**
     * Test that we can play ANY type of card.
     */
    @Test
    void testPlayCard() {
        this.player.addCard(this.basicAttack);
        this.player.addCard(this.basicDefense);
        this.player.addCard(this.basicDraw);
        this.player.addCard(this.basicRecovery);
        this.monsters= player.playCard(0,this.monsters, this.basicAttack);
        assertEquals(94,this.monsters.get(0).getHealth());

        this.monsters= player.playCard(0,this.monsters, this.basicDefense);
        assertEquals(12,this.player.getDefense());

        this.monsters= player.playCard(0,this.monsters, this.basicDraw);
        assertEquals(2,this.player.getPlayerHandSize());

        assertEquals(80,this.player.getHealth());
        this.player.takeDamage(this.basicAttack); // 6 block
        this.player.takeDamage(this.basicAttack); // 0 block
        this.player.takeDamage(this.basicAttack); // -6 HP
        assertEquals(74,this.player.getHealth());

        this.monsters= player.playCard(0,this.monsters, this.basicRecovery);
        assertEquals(80,this.player.getHealth());
    }

    /**
     * Test that we can adjust the value of the HP both adding and subtracting
     */
    @Test
    void testChangeHP() {
        assertEquals(80,this.player.getHealth());
        this.player.changeHP(-10);
        assertEquals(70,this.player.getHealth());
        this.player.changeHP(5);
        assertEquals(75,this.player.getHealth());
        this.player.changeHP(5);
        assertEquals(80,this.player.getHealth());
    }
}