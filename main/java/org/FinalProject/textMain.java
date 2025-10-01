/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: John Klopcic
 * Section: 2PM Lecture / 10AM Lab
 * Date: 4/14/24
 * Time: 3:40 PM
 *
 * Project: csci205_final_project
 * Package: org.FinalProject
 * Class: textMain
 *
 * Description:
 * Simulate a game of our project only in text, for testing purposes
 * ****************************************
 */
package org.FinalProject;

import org.FinalProject.ModelClasses.Card;
import org.FinalProject.ModelClasses.Monster;
import org.FinalProject.ModelClasses.Player;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A main class that implements a text based
 * version of our game
 */
public class textMain {

    // The player to use for the game
    private static Player player;

    // The monster to use for the game
    private static ArrayList<Monster> monster;


    /**
     * Runs one game of our project
     * It is completely text-based
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Setup player and monster
        player = new Player(50, "TextDeck.csv");
        Monster newMonster = new Monster(50);
        monster = new ArrayList<>();
        monster.add(newMonster);

        while(player.getHealth() > 0 && monster.get(0).getHealth() > 0){
            System.out.println();
            player.setEnergy(3);
            System.out.println("NEW TURN: ");
            System.out.println(monster.get(0));
            System.out.println("Monster health is: "+monster.get(0).getHealth());
            takePlayerTurn();
            if(monster.get(0).getHealth() < 1){
                break;
            }
            System.out.println();
            System.out.println("Monster takes it's turn: ");
            System.out.println(monster.get(0));
            int beforeHealth = player.getHealth();
            player = monster.get(0).playCard(player);
            System.out.println("You took " +(beforeHealth - player.getHealth())+" damage!");
            System.out.println("The monster now has " + monster.get(0).getDefense() + " block");
            System.out.println("You now have "+player.getDefense()+" block");
            System.out.println("You now have "+player.getHealth()+" health");
            player.resetDefense();
            monster.get(0).resetDefense();
            player.discardHand();
            System.out.println("Your hand has been discarded and your block reset to 0");
        }
        if(player.getHealth() < 0){
            System.out.println("You lose!");
        }
        else {
            System.out.println("You win!");
        }
    }

    /**
     * Takes a turn for the player
     */
    public static void takePlayerTurn(){
        Scanner scan = new Scanner(System.in);
        player.drawHand();
        int cardSelect = -1;
        int oldHandSize = 5;
        while(cardSelect <= oldHandSize) {
            System.out.println();
            int counter = 0;
            System.out.println("Energy remaining is: "+ player.getEnergy()+"\nYour Hand:");
            for (Card c : player.getPlayerHand()) {
                counter++;
                System.out.print(counter + ". ");
                System.out.println(c.getCardName() + ": " + c.getCardDescription() + ", Cost: " + c.getEnergy());
            }
            counter ++;
            System.out.println(counter + ": End Turn");
            System.out.println("Enter which card you want to play:");
            cardSelect = scan.nextInt();
            oldHandSize = player.getPlayerHandSize();
            if(cardSelect <= player.getPlayerHandSize()) {
                Card selectedCard = player.getPlayerHand().get(cardSelect - 1);
                if (selectedCard.getEnergy() > player.getEnergy()) {
                    System.out.println("Not enough energy for that move");
                } else {
                    monster = player.playCard(0, monster, selectedCard);
                    System.out.println("The monster now has " + monster.get(0).getHealth() + " health");
                    System.out.println("The monster now has " + monster.get(0).getDefense() + " block");
                    System.out.println("You now have " + player.getDefense() + " block");
                }
            }
            if(monster.get(0).getHealth() < 1){
                break;
            }
        }
        System.out.println("Your turn is over");
    }
}