/**
 * The controller for Phase3.fxml
 * <h2> Course Info: </h2>
 *  ICS4UO with V. Krasteva
 *
 * @author Kiran K Basra and Marina Semenova
 * @version 1.3 June 10, 2019
 * Modifications: 2 new levels and bug fixes
 */

//import statements
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import validation.NameValidation;
import java.net.URL;
import java.util.ResourceBundle;

public class Phase3Controller implements Initializable, ControlledScreen {
    // instance variables

    /**
     * The button used to shout to driver to stop.
     */
    @FXML
    private Button shoutButton;

    /**
     * The label to show the destination. Can be hidden or shown.
     */
    @FXML
    private Label destination;

    /**
     * The information button - the variable can be used to hide or show the element.
     */
    @FXML
    private Button info;

    /**
     * The Bus Driver's text bubble - the variable can be used to hide or show the element.
     */
    @FXML
    private ImageView flippedSpeech;

    /**
     * The Stop button. Can be hidden or shown.
     */
    @FXML
    private Button stop;

    /**
     * The Big Sister text label, to update and change the text.
     */
    @FXML
    private Label bigSisterText;

    /**
     * The image of Big Sister - can be changed.
     */
    @FXML
    private ImageView bigSister;

    /**
     * The button for next speech in the Big Sister speech.
     * Can be hidden/shown, disabled/enabled.
     */
    @FXML
    private Button bigSisterButton;

    /**
     * The arrow to point at objects, can be disabled or enabled.
     */
    @FXML
    private ImageView arrow;

    /**
     * Used to show the progress through the phase, can be updated.
     */
    @FXML
    private ProgressIndicator progress;

    /**
     * The label for the next bus stop coming up, can be updated.
     */
    @FXML
    private Label nextStopLabel;

    /**
     * The next stop, can be updated.
     */
    @FXML
    private Label nextStop;

    /**
     * The controller.
     */
    private ScreensController myController;

    /**
     * The current level - determines what is displayed.
     */
    private static int currentLevel;

    /**
     * The controller of the screen - updates and modifies elements.
     */
    private Phase3Controller c;

    /**
     * The index of which screen is being displayed.
     */
    private int screenCount;

    /**
     * The distance between the stop the user chooses and the destination.
     */
    private int stoppedAt;

    /**
     * If the stop button has been pressed, true. Otherwise, false.
     */
    private boolean pressed;

    /**
     * Controller for the next phase.
     */
    private Phase4Controller nextController;

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
            c = (Phase3Controller) ScreensController.getController(index);
        }
        else{
            nextController = (Phase4Controller) ScreensController.getController(index);
        }
    }

    /**
     * Sets up the screen for the phase. Sets the style, controller, and current level.
     * Writes the opening message.
     */
    @FXML
    public void setup(){
        setController(14, false);
        currentLevel = FXMLDocumentController.getCurrentLevel();
        c.bigSisterButton.setText(">");
        c.pressed = false;
        c.screenCount = 0;
        c.progress.setStyle(" -fx-progress-color: #072c68;-fx-font-family: Spaceport;-fx-color:#ffffff; -fx-font-size: 18em;");
        c.bigSisterButton.setVisible(true);
        c.arrow.setVisible(false);
        c.stop.setDisable(false);
        c.nextStop.setText("Safety Avenue");
        c.shoutButton.setDisable(false);
        if (currentLevel == 1){
            c.progress.setProgress((10/70.0) * (screenCount));
            c.bigSisterText.setText("We’re almost done, " + NameValidation.getName() + "! Now all we have to do is get off the bus.");
            c.bigSister.setVisible(true);
            c.bigSisterButton.setVisible(true);
            c.bigSisterText.setVisible(true);
            c.flippedSpeech.setVisible(true);
            c.bigSisterText.setLayoutX(166);
            c.bigSisterText.setLayoutY(119);
            c.flippedSpeech.setLayoutX(124);
            c.flippedSpeech.setLayoutY(93);
            c.bigSisterButton.setLayoutX(323);
            c.bigSisterButton.setLayoutY(223);
            c.info.setVisible(false);
            c.stop.setVisible(false);
        }
        else{
            c.progress.setProgress((10/100.0) * 9);
            c.bigSisterText.setVisible(false);
            c.bigSisterButton.setVisible(false);
            c.bigSister.setVisible(false);
            c.flippedSpeech.setVisible(false);
            c.destination.setText("Destination: Happy Avenue");
            c.stop.setVisible(true);
            if (currentLevel== 3){
                c.destination.setText("Destination: School Street");
                c.info.setVisible(false);
                c.stoppedAt = -5;
                c.sleep("Dundas Street");
            }
            else{
                c.info.setVisible(false);
                c.stoppedAt = -4;
                c.sleep("Allen Road");
            }
            c.flippedSpeech.setLayoutX(49);
            c.flippedSpeech.setLayoutY(228);
            c.bigSisterText.setLayoutX(90);
            c.bigSisterText.setLayoutY(256);
            c.bigSisterButton.setLayoutX(248);
            c.bigSisterButton.setLayoutY(360);
        }
    }

    /**
     * Helper method for gameplay, displays the name of the next street.
     *
     * @param street the name of the street that comes up next.
     */
    private void sleep(String street){
        if (!c.pressed) {
            Task<Void> sleeper = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                    }
                    return null;
                }
            };
            sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
                @Override
                public void handle(WorkerStateEvent event) {
                    c.nextStop.setText(street);
                    if(!c.pressed) {
                        c.stoppedAt++;
                        nextScreen(new ActionEvent());
                    }
                }
            });
            new Thread(sleeper).start();
        }
        else{
            nextScreen(new ActionEvent());
        }
    }

    /**
     * Sends to the next screen, displays the appropriate graphics.
     * In level 1, screens 1 to 6 are information from Big Sister.
     * Screens 7 through 11 are stops.
     * In levels 2 and 3, all screens are a list of stops.
     * In level 3, the last screen displays a button saying 'shout to stop'.
     */
    @FXML
    private void nextScreen(ActionEvent event) {
        screenCount++;
        if (currentLevel == 1) {
            if (screenCount == 1) {
                c.progress.setProgress((10/70.0) * (screenCount));
                c.bigSisterText.setText("You do this by pressing the stop button.");
                c.arrow.setVisible(true);
            }
            else if (screenCount == 2) {
                c.progress.setProgress((10/70.0) * (screenCount));
                c.arrow.setLayoutX(657);
                c.arrow.setLayoutY(435);
                c.bigSisterText.setText("You remember the stop, right? It’s on the screen right now!");
            }
            else if (screenCount == 3) {
                c.progress.setProgress((10/70.0) * (screenCount));
                c.arrow.setLayoutX(457);
                c.arrow.setLayoutY(55);
                c.bigSisterText.setText("Up here we have the current stop we’re at.");
                c.nextStopLabel.setVisible(true);
                c.nextStop.setVisible(true);
            }
            else if (screenCount == 4) {
                c.progress.setProgress((10/70.0) * (screenCount));
                c.bigSisterText.setText("This isn’t our stop yet so we’re going to wait a bit.");
                c.arrow.setVisible(false);
            }
            else if (screenCount == 5) {
                c.progress.setProgress((10/70.0) * (screenCount));
                c.bigSisterText.setText("Now, we’re going to pay attention to the name of the current stop.");
                c.arrow.setVisible(true);
            }
            else if (screenCount == 6) {
                c.stoppedAt = -3;
                c.bigSisterButton.setVisible(false);
                c.bigSister.setImage(new Image("resources/img/big sister 2 flipped.png"));
                c.progress.setProgress((10/70.0) * (screenCount));
                c.stop.setVisible(true);
                c.bigSisterText.setText("When our stop is next we want to press the button right away.");
                c.arrow.setLayoutX(674);
                c.arrow.setLayoutY(135);
                c.sleep("Yonge Street");
            }
            else if (screenCount == 7) {
                c.sleep("Bloor Street");
            }
            else if (screenCount == 8){
                c.sleep("Fun Road");
            }
            else if (screenCount == 9){
                c.sleep("Incredible Blvd");
            }
            else if (screenCount == 10){
                c.sleep("Eglinton Avenue");
            }
            else if (screenCount == 11){
                c.sleep("Kipling Avenue");
            }
            else {
                if (!c.pressed){
                    Points.record("Didn't stop", -25);
                }
                goToPhase4();
            }
        }
        else if (currentLevel == 2){
            if (screenCount == 1) {
                c.sleep("The Queensway");
            }
            else if (screenCount == 2){
                c.sleep("King Street");
            }
            else if (screenCount == 3){
                c.sleep("Happy Avenue");
            }
            else if (screenCount == 4){
                c.sleep("Bathurst Road");
            }
            else if (screenCount == 5){
                c.sleep("Dufferin Street");
            }
            else if (screenCount == 6){
                c.sleep("Weston Road");
            }
            else {
                if (!c.pressed){
                    Points.record("Didn't stop", -25);
                }
                goToPhase4();
            }
        }
        else {
            if (screenCount == 1) {
                c.sleep("Tillplain Road");
            }
            else if (screenCount == 2){
                c.sleep("Steeles Road");
            }
            else if (screenCount == 3){
                c.sleep("Bayview Avenue");
            }
            else if (screenCount == 4){
                c.sleep("School Street");
            }
            else if (screenCount == 5){
                c.sleep("Celeste Drive");
            }
            else if (screenCount == 6){
                c.sleep("Pine Street");
            }
            else if (screenCount == 7){
                c.sleep("Brampton Road");
            }
            else {
                if (!c.pressed){
                    Points.record("Didn't stop", -25);
                }
                goToPhase4();
            }
        }
    }

    /**
     * This method opens the help information.
     *
     * @param event an event.
     */
    @FXML
    private void openPopup(ActionEvent event){
        c.flippedSpeech.setVisible(true);
        c.bigSisterText.setVisible(true);
        c.bigSisterButton.setVisible(true);
        c.bigSisterText.setText("Stop at the destination.");
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
            c.flippedSpeech.setVisible(false);
            c.bigSisterText.setVisible(false);
            c.bigSisterButton.setVisible(false);
        }
    }

    /**
     * This method determines what happens when the shout button is pressed.
     * It calculates how many points have been gained based on when the user shouted.
     *
     * @param event an event
     */
    @FXML
    private void shout(ActionEvent event){
        c.pressed = true;
        String message = "";
        int pointsAdded = 0;
        if (c.stoppedAt == 0) {
            message = "Stopped at right stop";
            pointsAdded = 10;
        } else if (c.stoppedAt < 0) {
            if (c.stoppedAt == -1) {
                message = "Stopped " + Math.abs(c.stoppedAt) + " stop too early";
            }else{
                message = "Stopped " + Math.abs(c.stoppedAt) + " stops too early";
            }
            pointsAdded = c.stoppedAt * 5;
        } else {
            if (c.stoppedAt == 1) {
                message = "Stopped " + c.stoppedAt + " stop too late";
            }else{
                message = "Stopped " + c.stoppedAt + " stops too late";
            }
            pointsAdded = -c.stoppedAt * 5;
        }
        Points.record(message, pointsAdded);
        c.shoutButton.setDisable(true);
        c.shoutButton.setVisible(false);
        nextScreen(new ActionEvent());

    }
    /**
     * When the user presses stop, it stops the bus and calculates the points.
     * It outputs a message to Points and goes to the next screen.
     *
     * @param event an event
     */
    @FXML
    private void stop (ActionEvent event){
        if (currentLevel != 3) {
            c.pressed = true;
            String message = "";
            int pointsAdded = 0;
            if (c.stoppedAt == 0) {
                message = "Stopped at right stop";
                pointsAdded = 10;
            } else if (c.stoppedAt < 0) {
                message = "Stopped " + Math.abs(c.stoppedAt) + " stops too early";
                pointsAdded = c.stoppedAt * 10;
            } else {
                message = "Stopped " + c.stoppedAt + " stops too late";
                pointsAdded = -c.stoppedAt * 10;
            }
            Points.record(message, pointsAdded);
            nextScreen(new ActionEvent());
        }
        else{
            c.shoutButton.setVisible(true);
        }
        c.stop.setDisable(true);
        c.stop.setVisible(false);
    }

    /**
     * Goes to phase 4, sets the nextController up.
     */
    @FXML
    private void goToPhase4(){
        setController(15, true);
        nextController.setup(c.stoppedAt);
        myController.setScreen("phase 4");
    }
}//end of "Phase3Controller".