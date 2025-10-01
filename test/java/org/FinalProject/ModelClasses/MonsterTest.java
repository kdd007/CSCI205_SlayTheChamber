package org.FinalProject.ModelClasses;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MonsterTest {
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
     * Test that we can properly cycle through every action and do the correct action
     */
    @Test
    void testPlayCard() {
        assertEquals(80, this.player.getHealth());
        this.player= monster.playCard(this.player);
        assertEquals(74, this.player.getHealth());
        this.player= monster.playCard(this.player);
        assertEquals(20,this.monster.getDefense());
        this.player= monster.playCard(this.player);
        assertEquals(59,this.player.getHealth());
        this.player= monster.playCard(this.player);
        assertEquals(56,this.player.getHealth());
        this.player= monster.playCard(this.player);
        assertEquals(46,this.player.getHealth());
        this.player= monster.playCard(this.player);
        assertEquals(38,this.monster.getDefense());
    }

    /**
     * Test that we can properly subtract health
     */
    @Test
    void testTakeDamage() {
        assertEquals(100,this.monster.getHealth());
        this.monster.takeDamage(basicAttack);
        assertEquals(94,this.monster.getHealth());
    }

    /**
     * Test that we can properly add to our defense
     */
    @Test
    void testAddDefense() {
        assertEquals(0,this.monster.getDefense());
        this.monster.addDefense(basicDefense);
        assertEquals(12,this.monster.getDefense());

    }
}