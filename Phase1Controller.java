/**
 * The controller for Phase1.fxml. It deals with all the actions that take place in Phase One of the game.
 * <h2> Course Info: </h2>
 *  ICS4UO with V. Krasteva
 *
 * @author Kiran K Basra and Marina Semenova
 * @version 1.3 June 10, 2019
 * Modifications: 2 new levels and bug fixes.
 */

//import statements
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import validation.AgeValidation;
import validation.NameValidation;
import java.net.URL;
import java.util.ResourceBundle;

public class Phase1Controller implements Initializable, ControlledScreen {
    // instance variables

    /**
     * The text label for Big Sister, updated when the text changes.
     */
    @FXML
    private Label bigSisterText;

    /**
     * The image of Big Sister, changed when the image is different.
     */
    @FXML
    private ImageView bigSister;

    /**
     * The bus driver's speech, updated using this variable.
     */
    @FXML
    private Label busDriverText;

    /**
     * The Bus Driver's text bubble - the variable can be used to hide or show the element.
     */
    @FXML
    private ImageView flippedSpeech;

    /**
     * The information button - the variable can be used to hide or show the element.
     */
    @FXML
    private Button info;

    /**
     * Big Sister's text bubble - the variable can be used to hide or show the element.
     */
    @FXML
    private ImageView bigSisterSpeech;

    /**
     * The button for 'next text' beside the Big Sister speech.
     * This variable is also used to hide/show it and disable/enable it.
     */
    @FXML
    private Button bigSisterButton;

    /**
     * The button for 'next text' beside the Bus Driver speech.
     * This variable is also used to hide/show it and disable/enable it.
     */
    @FXML
    private Button busDriverButton;

    /**
     * The button for to ask the bus driver to speak.
     * This variable is also used to hide/show it and disable/enable it.
     */
    @FXML
    private Button askDriver;

    /**
     * The arrow to point at things.
     * This variable is also used to hide/show it and disable/enable it.
     */
    @FXML
    private ImageView arrow;

    /**
     * The label to show the destination. Can be hidden or shown.
     */
    @FXML
    private Label destination;

    /**
     * The label to show the instructions. Can be hidden or shown.
     */
    @FXML
    private Label instr;

    /**
     * Image of the Presto card. Can be hidden, shown, and dragged.
     */
    @FXML
    private ImageView presto;

    /**
     * Button for tapping the Presto. Can be hidden, shown, disabled, or enabled.
     */
    @FXML
    private Button tap;

    /**
     * To show progress through the phase, can be updated.
     */
    @FXML
    private ProgressIndicator progress;

    /**
     * This button is used to continue without pay
     */
    @FXML
    private Button cont;

    /**
     * The ScreensController to make everything work.
     */
    private ScreensController myController;

    /**
     * The current level, to determine what is displayed.
     */
    private static int currentLevel;

    /**
     * To access and modify screen elements.
     */
    private Phase1Controller c;

    /**
     * Determines which screen is currently being displayed.
     */
    private int screenCount;

    /**
     * The Presto card's X coordinate.
     */
    private Double imgX = 120.0;

    /**
     * The Presto card's Y coordinate.
     */
    private Double imgY  = 52.0;

    /**
     * The controller for the next screen.
     */
    private Phase2Controller nextController;

    /**
     * This boolean is used to determine whether or not driver was asked.
     */
    private boolean askedDriver;

    /**
     * Since this class implements Initialiable, it needs this method.
     *
     * @param location an unused parameter.
     * @param resources an unused parameter.
     */
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Sets the the screen parent myController to a parameter ScreensController.
     *
     * @param screenParent what we are setting myController to.
     */
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    /**
     * This returns the controller for a screen. The controller depends on the value of index.
     * We may go to the next screen if next is true.
     * Used in Start.fxml, GetName.fxml, and LevelSelect.fxml.
     *
     * @param index accesses a screen in the array in ScreensController
     * @param next whether or not we are going to next screen
     */
    private void setController(int index, boolean next){
        if(!next) {
            c = (Phase1Controller) ScreensController.getController(index);
        }
        else{
            nextController = (Phase2Controller) ScreensController.getController(index);
        }
    }

    /**
     * The setup method. It sets the controller, creates a new Points object, and sets the level.
     * It sets several things about the style and the introductory message (which changes per level).
     */
    public void setup(){
        setController(11, false);
        c.bigSisterButton.setText(">");
        c.screenCount = 0;
        currentLevel = FXMLDocumentController.getCurrentLevel();
        c.progress.setStyle(" -fx-progress-color: #072c68;-fx-font-family: Spaceport;-fx-color:#ffffff; -fx-font-size: 18em;");
        c.arrow.setLayoutX(479);
        c.arrow.setLayoutY(14);
        askedDriver = false;
        c.arrow.setVisible(false);
        c.tap.setVisible(false);
        askedDriver = false;
        c.presto.setVisible(false);
        c.askDriver.setVisible(false);
        c.instr.setVisible(false);
        c.progress.setProgress(0);
        c.busDriverButton.setVisible(false);
        c.busDriverText.setVisible(false);
        c.flippedSpeech.setVisible(false);
        c.bigSister.setVisible(true);
        c.bigSisterButton.setVisible(true);
        c.bigSisterText.setVisible(true);
        c.bigSisterSpeech.setVisible(true);
        c.bigSisterText.setLayoutX(421);
        c.bigSisterText.setLayoutY(75);
        c.bigSisterSpeech.setLayoutX(386);
        c.bigSisterSpeech.setLayoutY(48);
        c.bigSisterButton.setLayoutX(372);
        c.bigSisterButton.setLayoutY(184);
        c.askDriver.setDisable(false);
        c.cont.setDisable(false);
        c.info.setVisible(false);
        c.cont.setVisible(false);
        if (currentLevel == 1){
            c.bigSisterText.setText("Hi, " + NameValidation.getName() + "! Are you ready for the bus?");
            c.destination.setText("Destination: Fun Road");
        }
        else if (currentLevel == 2){
            c.bigSisterText.setText("Hey, " + NameValidation.getName() + "! I’m not going with you this time.");
            c.destination.setText("Destination: Happy Avenue");
        }
        else if (currentLevel== 3){
            c.bigSisterText.setText("Wow, " + NameValidation.getName() + ", you’re a bus-riding expert now!");
            c.destination.setText("Destination: School Street");
        }
    }

    /**
     * This button is used to continue without pay.
     * If the user continues without pay 50 points are subtracted.
     *
     * @param event an event.
     */
    @FXML
    private void continueNoTap(ActionEvent event){
        Points.record("Didn't tap", -50);
        c.cont.setDisable(true);
        c.cont.setVisible(false);
        nextScreen(event);
    }
    /**
     * This goes to the next screen method.
     * It checks the screenCount to display the right information on the screen, depending on the level.
     *
     * @param event an event.
     */
    @FXML
    private void nextScreen(ActionEvent event) {
        screenCount++;
        if (currentLevel == 1) {
            if (screenCount == 1) {
                c.bigSisterText.setText("Don’t be scared, I’m right here with you.");
                c.progress.setProgress((10/90.0) * screenCount);
            } else if (screenCount == 2) {
                c.bigSisterText.setText("The bus is a fun and exciting place to be!");
                c.progress.setProgress((10/90.0) * screenCount);
            } else if (screenCount == 3) {
                c.bigSisterText.setText("The driver will tell you if the bus stops where you need if you ask.");
                c.progress.setProgress((10/90.0) * screenCount);
            } else if (screenCount == 4) {
                c.bigSisterText.setText("Check our stop right now - see it?");
                c.arrow.setVisible(true);
                c.destination.setVisible(true);
                c.progress.setProgress((10/90.0) * screenCount);
            } else if (screenCount == 5) {
                c.bigSister.setImage(new Image("resources/img/big sister 2.png"));
                c.bigSisterText.setText("Ask them right now, just to make sure!");
                c.arrow.setLayoutX(104);
                c.arrow.setLayoutY(354);
                c.bigSisterButton.setVisible(false);
                c.askDriver.setVisible(true);
                c.progress.setProgress((10/90.0) * screenCount);
            } else if (screenCount == 6) {
                c.bigSister.setImage(new Image("resources/img/big sister 3.png"));
                c.arrow.setVisible(false);
                c.askDriver.setVisible(false);
                c.flippedSpeech.setVisible(true);
                c.busDriverText.setVisible(true);
                c.busDriverButton.setVisible(true);
                c.busDriverText.setText("Yes, this bus stops at Fun Road.");
                c.bigSisterSpeech.setVisible(false);
                c.bigSisterText.setVisible(false);
                c.progress.setProgress((10/90.0) * screenCount);
            } else if (screenCount == 7) {
                c.bigSisterText.setText("We’re going to tap our Presto right now.");
                c.bigSister.setImage(new Image("resources/img/big sister 2.png"));
                c.flippedSpeech.setVisible(false);
                c.busDriverText.setVisible(false);
                c.busDriverButton.setVisible(false);
                c.bigSisterButton.setVisible(true);
                c.bigSisterSpeech.setVisible(true);
                c.bigSisterText.setVisible(true);
                c.progress.setProgress((10/90.0) * screenCount);
            } else if (screenCount == 8) {
                c.progress.setProgress((10/90.0) * screenCount);
                String text;
                if (AgeValidation.getAge() <= 12) {
                    text = "You’re under 13 so tapping your Presto is just a formality.";
                } else {
                    text = "You’re over 12 so you will have to get a Presto that uses money.";
                }
                c.bigSisterText.setText(text);
                c.instr.setVisible(true);
                c.arrow.setVisible(true);
                c.arrow.setLayoutX(134);
                c.arrow.setLayoutY(51);
                c.presto.setVisible(true);
                c.presto.setX(0);
                c.presto.setY(0);
                c.bigSisterButton.setVisible(false);
                c.tap.setVisible(true);
                c.tap.setDisable(true);
            }
            else if (screenCount == 9) {
                c.progress.setProgress((10/90.0) * screenCount);
                c.bigSister.setImage(new Image("resources/img/big sister.png"));
                c.bigSisterText.setText("Congratulations! You did it!");
                Points.record("Payed for ride", 10);
                c.instr.setVisible(false);
                c.presto.setVisible(false);
                c.arrow.setVisible(false);
                c.tap.setVisible(false);
                c.bigSisterButton.setVisible(true);
            }
            else{
                goToPhase2();
            }
        }
        else if (currentLevel == 2){
            if (screenCount == 1) {
                c.bigSisterText.setText("Don’t worry you’ll still be alright!");
                c.progress.setProgress((10/100.0) * screenCount);
            } else if (screenCount == 2) {
                c.bigSisterText.setText("Just remember what I taught you when we rode the bus together.");
                c.progress.setProgress((10/100.0) * screenCount);
            } else if (screenCount == 3) {
                c.bigSisterText.setText("I’m sure you’ll do great.");
                c.progress.setProgress((10/100.0) * screenCount);
            } else if (screenCount == 4) {
                c.bigSisterText.setText("Your stop is on the screen right now.");
                c.arrow.setVisible(true);
                c.arrow.setLayoutX(519);
                c.progress.setProgress((10/100.0) * screenCount);
                c.bigSister.setImage(new Image("resources/img/big sister 2.png"));
            } else if (screenCount == 5) {
                c.bigSisterText.setText("If you forget what to do there's an info button to help you.");
                c.progress.setProgress((10/100.0) * screenCount);
                c.arrow.setVisible(false);
                c.bigSister.setImage(new Image("resources/img/big sister.png"));
            } else if (screenCount == 6) {
                c.bigSisterText.setText("Alright, good luck!");
                c.progress.setProgress((10 /100.0) * screenCount);
            }
            else if (screenCount == 7){
                c.progress.setProgress((10 /100.0) * screenCount);
                c.bigSister.setVisible(false);
                c.bigSisterSpeech.setVisible(false);
                c.bigSisterButton.setVisible(false);
                c.bigSisterText.setVisible(false);
                c.info.setVisible(true);
                c.instr.setVisible(true);
                c.arrow.setLayoutX(134);
                c.arrow.setLayoutY(51);
                c.arrow.setVisible(true);
                c.presto.setVisible(true);
                c.presto.setX(0);
                c.presto.setY(0);
                c.cont.setVisible(true);
                c.askDriver.setVisible(true);
                c.tap.setVisible(true);
                c.tap.setDisable(true);
            }
            else{
                goToPhase2();
            }
        }
        else if (currentLevel== 3){
            if (screenCount == 1) {
                c.bigSisterText.setText("There are a few more things you need to know.");
                c.progress.setProgress((10/90.0) * screenCount);
            } else if (screenCount == 2) {
                c.bigSisterText.setText("You’ll have to experience some things no one has prepared you for.");
                c.progress.setProgress((10/90.0) * screenCount);
            } else if (screenCount == 3) {
                c.bigSisterText.setText("Don’t worry - I know you’ll be okay!");
                c.progress.setProgress((10/90.0) * screenCount);
            } else if (screenCount == 4) {
                c.bigSisterText.setText("After that, you’ll be ready to ride in the real world!");
                c.progress.setProgress((10/90.0) * screenCount);
            } else if (screenCount == 5) {
                c.bigSisterText.setText("Your new stop is on the screen again.");
                c.arrow.setVisible(true);
                c.arrow.setLayoutX(519);
                c.progress.setProgress((10/90.0) * screenCount);
                c.bigSister.setImage(new Image("resources/img/big sister 2.png"));
            } else if (screenCount == 6) {
                c.arrow.setVisible(false);
                c.bigSister.setImage(new Image("resources/img/big sister.png"));
                c.bigSisterText.setText("Best of luck!");
                c.progress.setProgress((10 / 90.0) * screenCount);
            }
            else if (screenCount == 7){
                c.progress.setProgress((10 / 90.0) * screenCount);
                c.bigSister.setVisible(false);
                c.bigSisterSpeech.setVisible(false);
                c.bigSisterButton.setVisible(false);
                c.bigSisterText.setVisible(false);
                c.info.setVisible(false);
                c.instr.setVisible(true);
                c.arrow.setLayoutX(134);
                c.arrow.setLayoutY(51);
                c.arrow.setVisible(true);
                c.presto.setVisible(true);
                c.presto.setX(0);
                c.presto.setY(0);
                c.cont.setVisible(true);
                c.askDriver.setVisible(true);
                c.tap.setVisible(true);
                c.tap.setDisable(true);
                c.progress.setProgress((10 / 90.0) * screenCount);
            }
            else{
                if(!askedDriver){
                    Points.record("Didn't confirm with driver", -5);
                }
                goToPhase2();
            }
        }

    }

    /**
     * This method determines what happens when the driver button is pressed.
     *
     * @param event an event.
     */
    @FXML
    private void nextScreenDriver(ActionEvent event){
        if(currentLevel == 1){
            nextScreen(event);
        }
        else{
            c.busDriverText.setVisible(false);
            c.busDriverButton.setVisible(false);
            c.flippedSpeech.setVisible(false);
        }
    }

    /**
     * This method determines what happens when the ask driver button is pressed.
     *
     * @param event an event.
     */
    @FXML
    private void askDriverAction(ActionEvent event){
        askedDriver = true;
        c.askDriver.setDisable(true);
        if (currentLevel == 1){
            nextScreen(event);
        }
        else{
            if (currentLevel == 2) {
                c.busDriverText.setText("Yes, this bus stops at Happy Avenue.");
            }
            else
            {
                c.busDriverText.setText("Yes, this bus stops at School Street.");
            }
            c.busDriverText.setVisible(true);
            c.busDriverButton.setVisible(true);
            c.flippedSpeech.setVisible(true);
            c.busDriverButton.setText("<");
        }
        Points.record("Confirmed with driver", 10);
        c.askDriver.setVisible(false);
    }

    /**
     * This method opens the help information.
     *
     * @param event an event.
     */
    @FXML
    private void openPopup(ActionEvent event){
        c.bigSisterSpeech.setLayoutX(466);
        c.bigSisterSpeech.setLayoutY(228);
        c.bigSisterText.setLayoutX(501);
        c.bigSisterText.setLayoutY(255);
        c.bigSisterButton.setLayoutX(455);
        c.bigSisterButton.setLayoutY(360);
        c.bigSisterSpeech.setVisible(true);
        c.bigSisterText.setVisible(true);
        c.bigSisterButton.setVisible(true);
        c.bigSisterText.setText("Click on the Presto and drag it onto the reader then click \"tap\".");
        c.bigSisterButton.setText("<");
    }

    /**
     * This method determines what happens when the big sister button is pressed.
     *
     * @param event an event.
     */
    @FXML
    private void nextScreenSister(ActionEvent event){
        if(currentLevel == 1){
            nextScreen(event);
        }
        else{
            if (screenCount == 7) {
                c.bigSisterSpeech.setVisible(false);
                c.bigSisterText.setVisible(false);
                c.bigSisterButton.setVisible(false);
            }
            else{
                nextScreen(event);
            }
        }
    }

    /**
     * To show the Presto moving when it is dragged by the mouse.
     *
     * @param e when the mouse uses the Presto.
     */
    @FXML
    private void onMouseDragged (MouseEvent e) {
        setController(11, false);
        c.imgX = e.getX();
        c.imgY = e.getY();
        c.presto.setX(c.imgX - 40);
        c.presto.setY(c.imgY - 20);
    }

    /**
     * This checks if the Presto is within the proper range of the scanner.
     * If it is, the tap button will be enabled. Otherwise, you will need to try again. In some cases you may lose points.
     *
     * @param e an event
     */
    @FXML
    private void onMouseReleased(MouseEvent e){
        setController(11, false);
        c.imgX = e.getX();
        c.imgY = e.getY();
        if ((c.imgX >= 260 && c.imgX <= 397) && (c.imgY >= 270 && c.imgY <= 360)){
            c.tap.setDisable(false);
        }
        else if ((c.imgX >= 700 || c.imgX <= 0) || (c.imgY >= 440 || c.imgY <= 0)){
            screenCount--;
            nextScreen(new ActionEvent());
        }
        else{
            c.tap.setDisable(true);
        }
    }

    /**
     * This method is used to determine what occurs when the tap button is pressed.
     *
     * @param event an event.
     */
    @FXML
    private void tap (ActionEvent event){
        if (currentLevel == 1){
            nextScreen(event);
        }
        else{
            Points.record("Pay for ride", 10);
            c.tap.setDisable(true);
            c.tap.setVisible(false);
            nextScreen(event);
        }
    }

    /**
     * This method makes the game continue on to Phase Two.
     */
    @FXML
    private void goToPhase2(){
        setController(12, true);
        nextController.setup();
        myController.setScreen("phase 2");
    }
}//end of "Phase1Controller" class