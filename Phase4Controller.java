/**
 * The controller for Phase4.fxml
 * <h2> Course Info: </h2>
 * ICS4UO with V. Krasteva
 *
 * @author Kiran K Basra and Marina Semenova
 * @version 1.3 June 10, 2019
 * Modifications: 2 new levels and bug fixes
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
import validation.NameValidation;

import java.net.URL;
import java.util.ResourceBundle;

public class Phase4Controller implements Initializable, ControlledScreen {
    // instance variables
    /**
     * The finish button - takes the user to the win/lose screen.
     */
    @FXML
    private Button finishButton;

    /**
     * The text label for Big Sister, updated when the text changes.
     */
    @FXML
    private Label bigSisterText;

    /**
     * The button for 'next text' beside the Big Sister speech.
     * This variable is also used to hide/show it and disable/enable it.
     */
    @FXML
    private Button bigSisterButton;

    /**
     * To show progress through the phase, can be updated.
     */
    @FXML
    private ProgressIndicator progress;

    /**
     * The image of Big Sister - can be changed.
     */
    @FXML
    private ImageView bigSister;

    /**
     * The controller.
     */
    private ScreensController myController;

    /**
     * The cocntroller for the next screen.
     */
    private FXMLDocumentController nextController;

    /**
     * Which level the user is currently playing - changes what happens in several levels.
     */
    private static int currentLevel;

    /**
     * Controls the screen, accesses and modifies elements.
     */
    private Phase4Controller c;

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
            c = (Phase4Controller) ScreensController.getController(index);
        }
        else{
            nextController = (FXMLDocumentController) ScreensController.getController(index);
        }
    }

    /**
     * The setup method. Sets the controller and the current level, and sets up the screen based on what happened.
     *
     * @param stoppedAt the offset from the destination at which the user stopped.
     */
    @FXML
    public void setup(int stoppedAt){
        setController(15, false);
        currentLevel = FXMLDocumentController.getCurrentLevel();
        c.progress.setStyle(" -fx-progress-color: #072c68;-fx-font-family: Spaceport;-fx-color:#ffffff; -fx-font-size: 18em;");
        c.finishButton.setVisible(false);
        c.progress.setProgress((10/70.0) * (7));
        c.bigSisterButton.setVisible(true);
        if (stoppedAt == 0){
            c.bigSisterText.setText("Amazing! You stopped on time!");
        }
        else if (stoppedAt < 0){
            c.bigSisterText.setText("On no! You stopped too early!");
        }
        else{
            c.bigSisterText.setText("On no! You stopped too late!");
        }
    }

    /**
     * This method completes the final actions of the level, outputting a message that differs based on level.
     *
     * @param event An event.
     */
    @FXML
    private void nextScreen(ActionEvent event) {
        setController(15, false);
        if (currentLevel == 1){
            c.bigSisterText.setText("You're done! You did great!");
            c.bigSister.setImage(new Image("resources/img/big sister 2.png"));
        }
        else if (currentLevel == 2){
            c.bigSisterText.setText("Fantastic job! You did that so well!");
        }
        else if (currentLevel== 3){
            c.bigSisterText.setText("Congratulations, "+ NameValidation.getName() +"! That was spectacular!");
        }
        c.bigSisterButton.setVisible(false);
        c.finishButton.setVisible(true);
    }

    /**
     * Sets the controller to the controller for the win/lose method.
     * Checks if the user passed.
     * Unlocks a level if the user passes their hardest level.
     * Outputs a message.
     * Sets the screen to the win/lose method.
     *
     * @param event an event.
     */
    @FXML
    private void goToWinLose(ActionEvent event){
        setController(16, true);
        String status;
        if (Points.getPassFail()){
            status = "Congratulations! You passed!";
            int levelUnlocked = FXMLDocumentController.getLevelUnlocked();
            if (currentLevel == levelUnlocked){
                FXMLDocumentController.incrementLevelUnlocked();
            }
        }
        else{
            status = "Good effort but you didn't quite make it!";
        }
        nextController.setStatus(status);
        myController.setScreen("win lose");
    }
}//end of "Phase4Controller" class.