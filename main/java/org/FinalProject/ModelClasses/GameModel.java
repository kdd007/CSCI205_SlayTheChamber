/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: John Klopcic
 * Section: 2PM Lecture / 10AM Lab
 * Date: 4/17/24
 * Time: 2:11 PM
 *
 * Project: csci205_final_project
 * Package: org.FinalProject.ModelClasses
 * Class: GameModel
 *
 * Description:
 * The total Model for our Game
 * ****************************************
 */
package org.FinalProject.ModelClasses;

import java.util.ArrayList;

/**
 *  A class that encapsulates the entire model for an instance of the game
 */
public class GameModel {
    private Player player;
    private ArrayList<Monster> monster;

    private int turnNum;

    /**
     * Basic getter for is the game is done
     * @return true if game is done false otherwise
     */
    public boolean isGameDone() {
        return gameDone;
    }

    // The boolean on whether the game is over
    private boolean gameDone;

    /**
     * Basic constructor for game instance
     */
    public GameModel(){
        this.player = new Player(50, "SuperDeck.csv");
        this.monster = new ArrayList<>();
        this.monster.add(new Monster(100));
        this.gameDone = false;
        this.player.drawHand();
        this.turnNum = 1;
    }

    /**
     * Basic getter for the model
     * @return the player the model is using
     */
    public Player getPlayer() {
        return this.player;
    }


    /**
     * Basic setter for the model's player
     * @param updatedPlayer the player to set the model's player to
     */
    public void setPlayer(Player updatedPlayer) {
        this.player=updatedPlayer;
    }


    /**
     * Basic getter for teh list of monsters
     * @return the list of monster's the game is using
     */
    public ArrayList<Monster> getMonster(){
        return this.monster;
    }


    /**
     * Basic setter for the list of monsters
     * @param updatedMonster the monster list to be updated to
     */
    public void setMonster(ArrayList<Monster> updatedMonster){
        this.monster=updatedMonster;
    }

    /**
     *  Take the enemies turn and reset various values
     */
    public void EnemyTurnAndReset(){
        this.getMonster().get(0).resetDefense();
        this.getPlayer().discardHand();
        this.setPlayer(this.getMonster().get(0).playCard(this.getPlayer()));
        this.CheckGameDone();
        this.getPlayer().resetDefense();
        this.getPlayer().resetEnergy();
        this.getPlayer().drawHand();
    }

    /**
     *  Check if the game is done
     */
    public void CheckGameDone(){
        if(this.getMonster().get(0).getHealth() <= 0){
            this.gameDone = true;
            System.out.println("Game Over");
        }
        else if(this.getPlayer().getHealth() <= 0){
            this.gameDone = true;
            System.out.println("Game Over");
        }
    }

    /**
     * Generates and returns the players score
     * @return the score from the game
     */
    public int getScore(){
        return this.player.getHealth() - this.turnNum - this.monster.get(0).getHealth();
    }

    /**
     * Check if the player has lost
     * @return boolean true of the player has lost
     */
    public boolean isPlayerDead(){return this.player.getHealth() <= 0;}

    /**
     * Check if the player has won
     * @return boolean true if the player has won
     */
    public boolean isMonsterDead(){return this.monster.get(0).getHealth() <= 0;}

    /**
     * Increment the turn number
     */
    public void incTurn(){this.turnNum ++;}

    /**
     * returns the turn number
     * @return integer of the turn number
     */
    public int getTurnNum(){return this.turnNum;}

}