/* *****************************************
 * CSCI 205 - Software Engineering and Design
 * Spring 2024
 * Instructor: Prof. Lily Romano / Prof. Joshua Stough
 *
 * Name: Maddy Kalaigian, John Klopcic, Kevin Duong, Clea Ramos
 * Section: 02
 * Date: 4/16/24
 * Time: 10:58 PM
 *
 * Project: csci205_final_project
 * Package: org.FinalProject
 * Class: gameViewController
 *
 * Description: Connects the view and controller and handles the events
 *
 * ****************************************
 */


package org.FinalProject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.FinalProject.ModelClasses.Card;
import org.FinalProject.ModelClasses.GameModel;

public class gameViewController {

    // The model being used for our game
    public GameModel theModel;

    // THe list of card boxes that holds the player's hand
    private ArrayList<VBox> cardBoxes;

    //Green color of the cards
    private Color greenColor = Color.web("rgb(141,255,130)");// blue as an rgb web value, implicit alpha


    // The FXML objects
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text deck;

    @FXML
    private Text discard;

    @FXML
    private Text endButton;

    @FXML
    private Text energyNum;

    @FXML
    private ImageView imageCard0;

    @FXML
    private ImageView imageCard1;

    @FXML
    private ImageView imageCard2;

    @FXML
    private ImageView imageCard3;

    @FXML
    private ImageView imageCard4;

    @FXML
    private Text monsterDefense;

    @FXML
    private ProgressBar monsterHealth;

    @FXML
    private ImageView monsterImage;

    @FXML
    private ImageView monsterReaction;

    @FXML
    private Text playerDefense;

    @FXML
    private ImageView shieldIcon;

    @FXML
    private ImageView shieldIconMonster;

    @FXML
    private Text monsterHealthText;

    @FXML
    private ProgressBar playerHealth;

    @FXML
    private ImageView playerImage;

    @FXML
    private ImageView playerEffect;

    @FXML
    private Text playerHealthText;

    @FXML
    private Text intentText;

    @FXML
    private Text textAction0;

    @FXML
    private Text textAction1;

    @FXML
    private Text textAction2;

    @FXML
    private Text textAction3;

    @FXML
    private Text textAction4;

    @FXML
    private Text textActionName0;

    @FXML
    private Text textActionName1;

    @FXML
    private Text textActionName2;

    @FXML
    private Text textActionName3;

    @FXML
    private Text textActionName4;

    @FXML
    private Text textDesc0;

    @FXML
    private Text textDesc1;

    @FXML
    private Text textDesc2;

    @FXML
    private Text textDesc3;

    @FXML
    private Text textDesc4;

    @FXML
    private Text textEnergy0;

    @FXML
    private Text textEnergy1;

    @FXML
    private Text textEnergy2;

    @FXML
    private Text textEnergy3;

    @FXML
    private Text textEnergy4;

    @FXML
    private VBox vBoxCard0;

    @FXML
    private VBox vBoxCard1;

    @FXML
    private VBox vBoxCard2;

    @FXML
    private VBox vBoxCard3;

    @FXML
    private VBox vBoxCard4;

    @FXML
    private Text intentText2;

    @FXML
    private ImageView intentAttack;

    @FXML
    private ImageView intentDefend;

    @FXML
    private StackPane mainScreen;

    @FXML
    private StackPane endScreen;

    @FXML
    private Text endText;

    @FXML
    private Text scoreText;

    @FXML
    private Button playButton;

    @FXML
    private Button resetButton;

    /**
     * Initialize all of our Scene Build ID's
     */
    @FXML
    void initialize() {
        initializeUI();
        initializePlayer();
        initializeMonster();
        initializeCards();
        initializeEndScene();
    }

    /**
     * Initialize the UI ID's
     */
    private void initializeUI() {
        assert deck != null : "fx:id=\"deck\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert discard != null : "fx:id=\"discard\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert endButton != null : "fx:id=\"endButton\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert energyNum != null : "fx:id=\"energyNum\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
    }

    /**
     * Initalize the Endscreen
     */
    private void initializeEndScene() {
        assert endButton != null : "fx:id=\"endButton\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert scoreText != null : "fx:id=\"scoreText\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert endText != null : "fx:id=\"endText\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert mainScreen != null : "fx:id=\"mainScreen\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert endScreen != null : "fx:id=\"endScreen\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
    }

    /**
     * Initialize the Player ID's
     */
    private void initializePlayer() {
        assert playerDefense != null : "fx:id=\"playerDefense\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert shieldIcon != null : "fx:id=\"shieldIcon\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert playerHealth != null : "fx:id=\"playerHealth\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert playerImage != null : "fx:id=\"playerImage\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert playerEffect != null : "fx:id=\"playerEffect\" was not injected: check your FXML file 'gameViewfxml" +
                ".fxml'.";
        assert playerHealthText != null : "fx:id=\"playerEffect\" was not injected: check your FXML file 'gameViewfxml" +
                ".fxml'.";
    }

    /**
     * Initialize the Monster ID's
     */
    private void initializeMonster() {
        assert monsterDefense != null : "fx:id=\"monsterDefense\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert monsterHealth != null : "fx:id=\"monsterHealth\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert monsterImage != null : "fx:id=\"monsterImage\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert monsterReaction != null : "fx:id=\"monsterReaction\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert monsterHealthText != null : "fx:id=\"playerEffect\" was not injected: check your FXML file " +
                "'gameViewfxml" +
                ".fxml'.";
        assert shieldIconMonster != null : "fx:id=\"shieldIconMonster\" was not injected: check your FXML file " +
                "'gameViewfxml" +
                ".fxml'.";
        assert intentText != null : "fx:id=\"intentText\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert intentText2 != null : "fx:id=\"intentText2\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
    }

    /**
     * Initialize the Card ID's
     */
    private void initializeCards() {
        assert imageCard0 != null : "fx:id=\"imageCard0\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert imageCard1 != null : "fx:id=\"imageCard1\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert imageCard2 != null : "fx:id=\"imageCard2\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert imageCard3 != null : "fx:id=\"imageCard3\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert imageCard4 != null : "fx:id=\"imageCard4\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textAction0 != null : "fx:id=\"textAction0\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textAction1 != null : "fx:id=\"textAction1\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textAction2 != null : "fx:id=\"textAction2\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textAction3 != null : "fx:id=\"textAction3\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textAction4 != null : "fx:id=\"textAction4\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textActionName0 != null : "fx:id=\"textActionName0\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textActionName1 != null : "fx:id=\"textActionName1\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textActionName2 != null : "fx:id=\"textActionName2\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textActionName3 != null : "fx:id=\"textActionName3\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textActionName4 != null : "fx:id=\"textActionName4\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textDesc0 != null : "fx:id=\"textDesc0\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textDesc1 != null : "fx:id=\"textDesc1\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textDesc2 != null : "fx:id=\"textDesc2\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textDesc3 != null : "fx:id=\"textDesc3\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textDesc4 != null : "fx:id=\"textDesc4\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textEnergy0 != null : "fx:id=\"textEnergy0\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textEnergy1 != null : "fx:id=\"textEnergy1\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textEnergy2 != null : "fx:id=\"textEnergy2\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textEnergy3 != null : "fx:id=\"textEnergy3\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert textEnergy4 != null : "fx:id=\"textEnergy4\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert vBoxCard0 != null : "fx:id=\"vBoxCard0\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert vBoxCard1 != null : "fx:id=\"vBoxCard1\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert vBoxCard2 != null : "fx:id=\"vBoxCard2\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert vBoxCard3 != null : "fx:id=\"vBoxCard3\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert vBoxCard4 != null : "fx:id=\"vBoxCard4\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
        assert resetButton != null : "fx:id=\"resetButton\" was not injected: check your FXML file 'gameViewfxml.fxml'.";
    }

    /**Set the model for this controller
     * @param theModel the {@link GameModel} connected to this controller
     */
    public void setModel(GameModel theModel){
        this.theModel = theModel;
        initBindings();
        initEventHandler();
    }


    /**
     * Set up bindings in our game from model to view
     */
    private void initBindings(){
        //System.out.println((theModel.getPlayer().getEnergy()));

        //BASIC ICONS:
        //set the value in the energy icon
        energyNum.textProperty().setValue(Integer.toString(theModel.getPlayer().getEnergy()));

        //set the value for the deck
        deck.textProperty().setValue(Integer.toString(theModel.getPlayer().getDeckSize()));

        //set the value for the discard pile
        discard.textProperty().setValue(Integer.toString(theModel.getPlayer().getDiscardSize()));

        // Intent Icons
        System.out.println("Attack intent URL: " + getClass().getResource("/images/Files/monster/attackIntent.gif"));
        System.out.println("Defend intent URL: " + getClass().getResource("/images/Files/monster/defendIntent.gif"));

        String imagePath = "/images/Files/monster/attackIntent.gif";
        InputStream imageStream = getClass().getResourceAsStream(imagePath);
        Image image = new Image(imageStream);
        intentAttack.setImage(image);


        intentAttack.setImage(new Image(getClass().getResourceAsStream("/images/Files/monster/attackIntent.gif")));
        intentDefend.setImage(new Image(getClass().getResourceAsStream("/images/Files/monster/defendIntent.gif")));


        //Update the defense icons for the player and for the enemy
        playerDefense.textProperty().setValue(Integer.toString(theModel.getPlayer().getDefense()));
        monsterDefense.textProperty().setValue(Integer.toString(theModel.getMonster().get(0).getDefense()));

        // Set the monster's intent
        updateIntent();

        cardBoxes= new ArrayList<>(Arrays.asList(vBoxCard0, vBoxCard1, vBoxCard2, vBoxCard3, vBoxCard4));
        //CARD
    }

    /**
     *  Update the UI for the player's hand
     *  Including setting the visual attributes of all the cards
     */
    private void updateHandUI() {
        ArrayList<Text> cardActionNameList = new ArrayList<>(Arrays.asList(textActionName0,textActionName1,textActionName2,textActionName3,textActionName4));
        ArrayList<Text> cardActionList = new ArrayList<>(Arrays.asList(textAction0,textAction1,textAction2,textAction3,textAction4));
        ArrayList<Text> cardDescList = new ArrayList<>(Arrays.asList(textDesc0,textDesc1,textDesc2,textDesc3,textDesc4));
        ArrayList<Text> cardEnergyList = new ArrayList<>(Arrays.asList(textEnergy0,textEnergy1,textEnergy2,textEnergy3,textEnergy4));
        ArrayList<ImageView> cardImageList = new ArrayList<>(Arrays.asList(imageCard0, imageCard1, imageCard2, imageCard3, imageCard4));

        for (int i =0; i<theModel.getPlayer().getPlayerHand().size(); i++){
            Card currentCard = theModel.getPlayer().getPlayerHand().get(i);

            cardActionNameList.get(i).textProperty().set(currentCard.getCardName());
            cardActionList.get(i).textProperty().set(currentCard.getCardType());
            cardDescList.get(i).textProperty().set(currentCard.getCardDescription());
            cardEnergyList.get(i).textProperty().set(Integer.toString(currentCard.getEnergy()));
            cardImageList.get(i).setImage(new Image(getClass().getResourceAsStream("/images/Files/cards/"+currentCard.getImgPath())));

        }
    }

    /**
     *  Initialize all the event handlers that are being used for the game
     */
    public void initEventHandler(){
        updateHandUI();
        updateHealthBars();
        shieldIcon.setImage(new Image(getClass().getResourceAsStream("/images/Files/GUI/Shield.png")));
        shieldIconMonster.setImage(new Image(getClass().getResourceAsStream("/images/Files/GUI/Shield.png")));


        // Bind the cards to being able to play the cards.
        vBoxCard0.onMouseClickedProperty().setValue(event -> {
            vBoxCard0.setDisable(true);

            Card playedCard = theModel.getPlayer().getPlayerHand().get(0);
            handleCard(playedCard, vBoxCard0);
        });
        vBoxCard1.onMouseClickedProperty().setValue(event -> {
            vBoxCard1.setDisable(true);

            Card playedCard = theModel.getPlayer().getPlayerHand().get(1);
            handleCard(playedCard, vBoxCard1);
        });
        vBoxCard2.onMouseClickedProperty().setValue(event -> {
            vBoxCard2.setDisable(true);

            Card playedCard = theModel.getPlayer().getPlayerHand().get(2);
            handleCard(playedCard, vBoxCard2);

        });
        vBoxCard3.onMouseClickedProperty().setValue(event -> {
            vBoxCard3.setDisable(true);

            Card playedCard = theModel.getPlayer().getPlayerHand().get(3);
            handleCard(playedCard, vBoxCard3);

        });
        vBoxCard4.onMouseClickedProperty().setValue(event -> {
            vBoxCard4.setDisable(true);

            Card playedCard = theModel.getPlayer().getPlayerHand().get(4);
            handleCard(playedCard, vBoxCard4);
        });

        // Enable the player to end turn
        endButton.onMouseClickedProperty().setValue(event -> {
            enemyTurnAndReset();
            theModel.incTurn();
        });

        playButton.onMouseClickedProperty().setValue(event -> {
            gotoMainScreen();
        });
        // Resets the state of the game back to the beginning
        resetButton.onMouseClickedProperty().setValue(event -> {
            gotoMainScreen();
        });
    }

    /**
     *  Take the enemy's turn then
     *  reset the player's hand and energy
     */
    private void enemyTurnAndReset() {
        theModel.EnemyTurnAndReset();
        if (Objects.equals(theModel.getMonster().get(0).getAction().getCardType(), "Attack")) {
            // Monster attacks
            monsterImage.setImage(new Image(getClass().getResourceAsStream("/images/Files/monster/monsterAttack.gif")));
            // Monster swipes animation plays for 1.75 second on the player, then change Monster back to idle
            Timeline timeline0 = new Timeline(new KeyFrame(Duration.seconds(1.75), event -> {
                playerEffect.setImage(new Image(getClass().getResourceAsStream("/images/Files/monster/monsterSwipes.gif")));
                monsterImage.setImage(new Image(getClass().getResourceAsStream("/images/Files/monster/monsterIdle.gif")));
                updateHealthBars();
            }));
            // Play the Player's hurt animation for 1.25 seconds
            Timeline timeline1 = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
                playerImage.setImage(new Image(getClass().getResourceAsStream("/images/Files/player/The Silent Ouch.gif")));
                playerEffect.setImage(null);
                updateHealthBars();
            }));

            // Change the image back after 1 second
            Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
                playerImage.setImage(new Image(getClass().getResourceAsStream("/images/Files/player/The Silent Idle.gif")));
                updateHealthBars();
            }));
            timeline0.play();
            timeline1.play();
            timeline2.play();
            // Checks if the game is done to play the endscreen
            if(theModel.isGameDone()){
                gotoEndScreen();
            }
        }
        else{
            // if the monster plays a defense card, then put the shield up effect on the monster
            monsterReaction.setImage(new Image(getClass().getResourceAsStream("/images/Files/player/ShieldUp.gif")));
            Timeline timeline3 = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                monsterReaction.setImage(null);
                updateHealthBars();
            }));
            timeline3.play();
        }
        updateHealthBars();
        updateUI();
        updateHandUI();
        // Fade the new cards back in when the turn ends
        for (VBox card: cardBoxes) {
            fadeIn(card);
        }
        updateIntent();
    }

    /**
     * Updates the intent of the monster when called
     */
    private void updateIntent() {
        intentText.textProperty().setValue(""+theModel.getMonster().get(0).getIntent().get(0));
        intentText2.textProperty().setValue(""+ theModel.getMonster().get(0).getIntent().get(1));
    }

    /**
     *  Handle a card that has been clicked on and attempted to play
     *
     * @param playedCard the card being played
     * @param vBoxCard the card that was clicked on
     */
    private void handleCard(Card playedCard, VBox vBoxCard) {
        // If the player has enough energy then play the card
        if (theModel.getPlayer().getEnergy() - playedCard.getEnergy() >= 0) {
            playCard(playedCard);
            fadeOut(vBoxCard);
            updateUI();
        } else {
            // Card is invalid
            vBoxCard.setStyle("-fx-background-color:   radial-gradient(focus-distance 0% , center 50% 50% , radius 100% , #dad7be,  #898573) ; -fx-background-radius: 10");
            //turn color of card to Red
            applyColorShadow(vBoxCard, Color.FIREBRICK);


        }
    }

    /**
     * Changing the color of the background shadow on the card
     * @param vBoxCard - the card to change the background
     * @param color
     */
    private void applyColorShadow(VBox vBoxCard, Color color) {
        DropShadow cardShadow = new DropShadow();
        cardShadow.setColor(color);
        cardShadow.setWidth(29.32);
        cardShadow.setHeight(29.32);
        cardShadow.setRadius(14.6);
        cardShadow.setSpread(0.8);
        vBoxCard.setEffect(cardShadow);
    }


    /**
     * Fade out a player's card from their hand
     * @param vBoxCard the card to fade out
     */
    private void fadeOut(VBox vBoxCard) {
        FadeTransition fade = new FadeTransition(Duration.seconds(0.5), vBoxCard);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
    }

    /**
     *  Fade in a card in the player's hand
     * @param vBoxCard the card to fade in
     */
    private void fadeIn(VBox vBoxCard) {
        FadeTransition fade = new FadeTransition(Duration.seconds(1), vBoxCard);
        fade.setFromValue(0);
        fade.setToValue(1);
        fade.play();
        //vBoxCard.setStyle("-fx-border-color: blue; -fx-background-color: pink");
        vBoxCard.setStyle("-fx-background-color:   radial-gradient(focus-distance 0% , center 50% 50% , radius 100% , #dad7be,  #898573) ; -fx-background-radius: 10");
        applyColorShadow(vBoxCard, greenColor);
        vBoxCard.setDisable(false);
    }

    /**
     *  Update the UI including
     *  all the player's stats and the enemies
     */
    private void updateUI() {
        energyNum.textProperty().setValue(Integer.toString(theModel.getPlayer().getEnergy()));
        discard.textProperty().setValue(Integer.toString(theModel.getPlayer().getDiscardSize()));
        playerDefense.textProperty().setValue(Integer.toString(theModel.getPlayer().getDefense()));
        monsterDefense.textProperty().setValue(Integer.toString(theModel.getMonster().get(0).getDefense()));
        deck.textProperty().setValue(Integer.toString(theModel.getPlayer().getDeckSize()));
    }


    /**
     *  Visually play a card
     * @param playedCard the card that is being played
     */
    private void playCard(Card playedCard) {
        // Perform the calculations and updating the monster and player's values
        theModel.setMonster(theModel.getPlayer().playCard(0, theModel.getMonster(), playedCard));
        theModel.CheckGameDone();
        // if the card was an attack then play the attack animation
        if (Objects.equals(playedCard.getCardType(), "Attack")) {
            playerImage.setImage(new Image(getClass().getResourceAsStream("/images/Files/player/The Silent Attack.gif")));
            // Change the image back after 1 second
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                playerImage.setImage(new Image(getClass().getResourceAsStream("/images/Files/player/The Silent Idle.gif")));
                updateHealthBars();
            }));
            timeline.play();


            //Overlay the attack/defend action on the opponent
            Timeline timeline2 = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                monsterReaction.setImage(new Image(getClass().getResourceAsStream("/images/Files/player/slashy.gif")));
                updateHealthBars();
            }));
            timeline2.play();
        }
        // if it was a defense card, then play the shield animation on the user
        else if (Objects.equals(playedCard.getCardType(), "Defense")) {
            playerEffect.setImage(new Image(getClass().getResourceAsStream("/images/Files/player/ShieldUp.gif")));
            // Change the image back after 1 second
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                playerEffect.setImage(null);
                updateHealthBars();
            }));
            timeline.play();
        }
        // if the card was gamble, then refresh the hand
        else if(playedCard.getCardModifier().equals("Gamble")){
            updateHandUI();
            updateUI();
            // Update every card when Gamble is played
            for (VBox card: cardBoxes) {
                fadeIn(card);
            }
        }
        else {
            updateHealthBars();
        }
        if(theModel.isGameDone()){
            gotoEndScreen();
        }
    }

    /**
     *  Update all the health bars on screen
     */
    private void updateHealthBars() {
        double playerHealthPercent = theModel.getPlayer().healthPercent();
        double monsterHealthPercent = theModel.getMonster().get(0).healthPercent();
        playerHealth.setProgress(playerHealthPercent);
        monsterHealth.setProgress(monsterHealthPercent);
        playerHealthText.textProperty().setValue(" "+theModel.getPlayer().getHealth()+"/"+theModel.getPlayer().getMaxHealth());
        monsterHealthText.textProperty().setValue(theModel.getMonster().get(0).getHealth()+"/"+theModel.getMonster().get(0).getMaxHealth());
    }


    /**
     *  Sets the current screen to the end screen
     *  and reports game results
     */
    private void gotoEndScreen(){
        mainScreen.setVisible(false);
        mainScreen.disableProperty().set(true);
        endScreen.setVisible(true);
        endScreen.disableProperty().set(false);
        scoreText.textProperty().setValue("Score: " + theModel.getScore());
        // Depending on the score they receive at the end of the game, display a specific message
        if(theModel.getScore() > 53){
            endText.textProperty().set("You've beaten the highest dev score!");
        }
        else if(theModel.getPlayer().getHealth() == 50){
            endText.textProperty().set("Flawless Victory!");
        }
        else if(theModel.getScore() == -113){
            endText.textProperty().set("Bro at least try a little!");
        }
        else if(theModel.getTurnNum() > 50){
            endText.textProperty().set("You might want to try attacking!");
        }
        else if(theModel.getPlayer().getHealth() > 0){
            endText.textProperty().set("You won!");
        }
        else{
            endText.textProperty().set("You lost!");
        }
    }

    /**
     *  Sets the current screen to the main screen
     *  and create a new game
     */
    private void gotoMainScreen(){
        setModel(new GameModel());
        mainScreen.setVisible(true);
        mainScreen.disableProperty().set(false);
        endScreen.setVisible(false);
        endScreen.disableProperty().set(true);
        updateUI();
        updateIntent();
        updateHealthBars();
        updateHandUI();
        for (VBox card: cardBoxes) {
            fadeIn(card);
        }
    }
}

