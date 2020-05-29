/**
 * The controller for Phase2.fxml
 * <h2> Course Info: </h2>
 *  ICS4UO with V. Krasteva
 *
 * @author Kiran Basra and Marina Semenova
 * @version 1.3 June 10, 2019
 * Modifications: 2 new levels and bug fixes.
 */

//import statements
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import validation.NameValidation;
import java.net.URL;
import java.util.ResourceBundle;

public class Phase2Controller implements Initializable, ControlledScreen {
    // instance variables

    /**
     * The background.
     */
    @FXML
    private ImageView bg;

    /**
     * Image of Big Sister's text.
     */
    @FXML
    private Label bigSisterText;

    /**
     * Big Sister's text bubble - the variable can be used to hide or show the element.
     */
    @FXML
    private ImageView bigSisterSpeech;

    /**
     * The information button - the variable can be used to hide or show the element.
     */
    @FXML
    private Button info;

    /**
     * Image of Big Sister, can be changed.
     */
    @FXML
    private ImageView bigSister;

    /**
     * The next button beside the big sister speech.
     * Can be hidden/shown, disabled/enabled.
     */
    @FXML
    private Button bigSisterButton;

    /**
     * To show progress, updated as phase continues.
     */
    @FXML
    private ProgressIndicator progress;

    /**
     * Updates time for the choose seat stage.
     */
    @FXML
    private ProgressBar time;

    /**
     * Sit button number one. Can be hidden or shown.
     */
    @FXML
    private Button sit1;

    /**
     * Sit button number two. Can be hidden or shown.
     */
    @FXML
    private Button sit2;

    /**
     * Sit button number three. Can be hidden or shown.
     */
    @FXML
    private Button sit3;

    /**
     * Sit button number four. Can be hidden or shown.
     */
    @FXML
    private Button sit4;

    /**
     * Stand button number one. Can be hidden or shown.
     */
    @FXML
    private Button stand1;

    /**
     * Stand button number two. Can be hidden or shown.
     */
    @FXML
    private Button stand2;

    /**
     * The controller of the screen.
     */
    private ScreensController myController;

    /**
     * Which level the game is on - determines what is shown.
     */
    private static int currentLevel;

    /**
     * Controller - accesses and modifies objects.
     */
    private Phase2Controller c;

    /**
     * Index for screens array. Determines what is shown on the screen.
     */
    private int screenCount;

    /**
     * Used to change the time progress bar.
     */
    private Task copyWorker;

    /**
     * Determines if a user sits (true) or stands (false).
     */
    private boolean sit;

    /**
     * The controller for the next screen.
     */
    private Phase3Controller nextController;

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
            c = (Phase2Controller) ScreensController.getController(index);
        }
        else{
            nextController = (Phase3Controller) ScreensController.getController(index);
        }
    }

    /**
     * The setup method. It sets the controller and sets the level.
     * It sets several things about the style and the introductory message.
     */
    @FXML
    public void setup(){
        setController(12, false);
        currentLevel = FXMLDocumentController.getCurrentLevel();
        c.screenCount = 0;
        c.bigSisterButton.setText(">");
        c.bg.setImage(new Image("/resources/img/phase 2.jpg"));
        c.progress.setStyle(" -fx-progress-color: #072c68;-fx-font-family: Spaceport;-fx-color:#ffffff; -fx-font-size: 18em;");
        c.sit1.setDisable(false);
        c.sit2.setDisable(false);
        c.sit3.setDisable(false);
        c.sit4.setDisable(false);
        c.stand1.setDisable(false);
        c.stand2.setDisable(false);
        c.info.setVisible(false);
        if (currentLevel == 1){
            c.progress.setProgress((10/90.0) * (screenCount));
            c.bigSisterText.setFont(new Font("Spaceport", 15));
            c.bigSisterText.setText("Okay, " + NameValidation.getName() + "! Ready to sit down? Don’t worry too much about where.");
            c.bigSister.setVisible(true);
            c.bigSisterButton.setVisible(true);
            c.bigSisterText.setVisible(true);
            c.bigSisterSpeech.setVisible(true);
            c.bigSisterText.setLayoutX(417);
            c.bigSisterText.setLayoutY(71);
            c.bigSisterSpeech.setLayoutX(386);
            c.bigSisterSpeech.setLayoutY(48);
            c.bigSisterButton.setLayoutX(375);
            c.bigSisterButton.setLayoutY(180);
            time.setVisible(false);
            c.time.setVisible(false);
            c.sit1.setVisible(false);
            c.sit2.setVisible(false);
            c.sit3.setVisible(false);
            c.sit4.setVisible(false);
            c.stand1.setVisible(false);
            c.stand2.setVisible(false);
        }
        else{
            c.progress.setProgress((10 / 100.0) * 8);
            c.bigSister.setVisible(false);
            c.bigSisterSpeech.setVisible(false);
            c.bigSisterButton.setVisible(false);
            c.bigSisterText.setVisible(false);
            c.time.setVisible(true);
            c.sit1.setVisible(true);
            c.sit3.setVisible(true);
            c.sit4.setVisible(true);
            c.stand1.setVisible(true);
            c.stand2.setVisible(true);
            c.bigSisterSpeech.setLayoutX(466);
            c.bigSisterSpeech.setLayoutY(228);
            c.bigSisterText.setLayoutX(501);
            if (currentLevel== 3){
                c.info.setVisible(false);
                c.bg.setImage(new Image("/resources/img/phase 2 level 3.jpg"));
                c.sit2.setVisible(false);
            }
            else{
                c.info.setVisible(true);
                c.sit2.setVisible(true);

            }
            c.bigSisterText.setLayoutY(255);
            c.bigSisterButton.setLayoutX(455);
            c.bigSisterButton.setLayoutY(360);
            c.time.setStyle("-fx-accent:#072c68;-fx-control-inner-background: #fff;-fx-font-family: Spaceport;-fx-color:#ffffff; -fx-font-size: 18em;");
            copyWorker = createWorker();
            c.time.progressProperty().unbind();
            c.time.progressProperty().bind(copyWorker.progressProperty());
            copyWorker.messageProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue));
            new Thread(copyWorker).start();
        }
    }

    /**
     * This goes to the next screen method.
     * It checks the screenCount to display the right information on the screen.
     * In level 1, the first 7 screens present information and the eighth screen lets you choose a seat.
     * The ninth screen is sent to Phase Three.
     * Otherwise, you complete the action and go to Phase 3.
     */
    @FXML
    private void nextScreen(ActionEvent event) {
        screenCount++;
        if (currentLevel == 1) {
            if (screenCount == 1) {
                c.bigSisterText.setFont(new Font("Spaceport", 16));
                c.progress.setProgress((10/90.0) * (screenCount));
                c.bigSisterText.setText("There are lots of empty seats right now, but sometimes there won’t be.");
            }
            else if (screenCount == 2) {
                c.progress.setProgress((10/90.0) * (screenCount));
                c.bigSisterText.setText("If there are seats it is safer to sit but sometimes you'll have to stand.");
            }
            else if (screenCount == 3) {
                c.progress.setProgress((10/90.0) * (screenCount));
                c.bigSisterText.setText("When you have to, stand beside a pole to stay balanced.");
            }
            else if (screenCount == 4) {
                c.progress.setProgress((10/90.0) * (screenCount));
                c.bigSisterText.setText("Also, stand as far back as you can from the exits for less traffic.");
            }
            else if (screenCount == 5) {
                c.progress.setProgress((10/90.0) * (screenCount));
                c.bigSisterText.setText("If you choose to sit beside someone, be aware of them.");
            }
            else if (screenCount == 6) {
                c.progress.setProgress((10/90.0) * (screenCount));
                c.bigSisterText.setText("Unfriendly people can sometimes be bad to sit beside.");
            }
            else if (screenCount == 7) {
                c.progress.setProgress((10/90.0) * (screenCount));
                c.bigSisterText.setText("But you aren’t in danger on the bus, so don’t worry.");
            }
            else if (screenCount == 8) {
                c.progress.setProgress((10/90.0) * (screenCount));
                c.bigSister.setImage(new Image("resources/img/big sister 2.png"));
                c.bigSisterText.setText("Try and find a spot fast since the bus is going to start moving soon!");
                c.time.setVisible(true);
                c.sit1.setVisible(true);
                c.sit2.setVisible(true);
                c.sit3.setVisible(true);
                c.sit4.setVisible(true);
                c.stand1.setVisible(true);
                c.stand2.setVisible(true);
                c.bigSisterButton.setVisible(false);
                c.time.setStyle("-fx-accent:#072c68;-fx-control-inner-background: #fff;-fx-font-family: Spaceport;-fx-color:#ffffff; -fx-font-size: 18em;");
                copyWorker = createWorker();
                c.time.progressProperty().unbind();
                c.time.progressProperty().bind(copyWorker.progressProperty());
                copyWorker.messageProperty().addListener((observable, oldValue, newValue) -> System.out.println(newValue));
                new Thread(copyWorker).start();
            }
            else if (screenCount == 9) {
                c.bigSister.setImage(new Image("resources/img/big sister.png"));
                c.progress.setProgress((10/90.0) * (screenCount));
                if(sit) {
                    if (c.time.getProgress() == 1) {
                        c.bigSisterText.setText("Oh no you didn't make it in time! You'll do better next time!");
                    } else {
                        c.bigSisterText.setText("Great job! You sat down in time!");
                    }
                }
                else{
                    if (c.time.getProgress() == 1) {
                        c.bigSisterText.setText("Oh no you didn't make it in time! Also make sure you sit next time!");
                    } else {
                        c.bigSisterText.setText("You stood in time but make sure you sit if there are open seats.");
                    }
                }
                c.bigSisterButton.setVisible(true);
                c.time.setVisible(false);
            }
            else{
                goToPhase3();
            }
        }
        else{
            goToPhase3();
        }
    }

    /**
     * This method opens the help information.
     *
     * @param event an event.
     */
    @FXML
    private void openPopup(ActionEvent event){
        c.bigSisterSpeech.setVisible(true);
        c.bigSisterText.setVisible(true);
        c.bigSisterButton.setVisible(true);
        c.bigSisterText.setText("Pick a place to sit or stand in time.");
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
            c.bigSisterSpeech.setVisible(false);
            c.bigSisterText.setVisible(false);
            c.bigSisterButton.setVisible(false);
        }
    }

    /**
     * This creates a worker method and makes the computer delay for 1100 seconds.
     *
     * @return a Task
     */
    private Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    if (currentLevel == 3){
                        if (i == 0) {
                            Thread.sleep(2000);
                        } else {
                            Thread.sleep(500);
                        }
                    }
                    else {
                        if (i == 0) {
                            Thread.sleep(2000);
                        } else {
                            Thread.sleep(1000);
                        }
                    }
                    updateProgress(i + 1, 10);
                }
                return true;
            }
        };

    }

    /**
     * When one of the sit buttons are pressed, it turns sit to true and moves on.
     * It also calculates points based on the user's proximity to the bad guy.
     *
     * @param event an event passed to nextScreen.
     */
    @FXML
    private void sitHere(ActionEvent event){
        sit = true;
        if (currentLevel == 3 && event.getSource().toString().equals("Button[id=sit1, styleClass=button]'sit'")){
            Points.record("Sat down next to bad guy", -30);
        }
        else if (currentLevel == 3 && event.getSource().toString().equals("Button[id=sit4, styleClass=button]'sit'")){
            Points.record("Sat down across from bad guy", -25);
        }
        else{
            Points.record("Sat down", 10);
        }
        if (c.time.getProgress() == 1) {
            Points.record("Didn't sit in time", -5);
        } else {
            Points.record("Sat in time", 10);
        }
        c.sit1.setDisable(true);
        c.sit2.setDisable(true);
        c.sit3.setDisable(true);
        c.sit4.setDisable(true);
        c.stand1.setDisable(true);
        c.stand2.setDisable(true);
        c.sit1.setVisible(false);
        c.sit2.setVisible(false);
        c.sit3.setVisible(false);
        c.sit4.setVisible(false);
        c.stand1.setVisible(false);
        c.stand2.setVisible(false);
        nextScreen(event);
    }

    /**
     * When one of the stand buttons are pressed, it turns sit to false and moves on.
     * It also records the points gained based on the user's proximity to the bad guy.
     *
     * @param event an event passed to nextScreen.
     */
    @FXML
    private void standHere(ActionEvent event){
        sit = false;
        if (currentLevel == 3 && event.getSource().equals("Button[id=stand1, styleClass=button]'stand'")){
            Points.record("Stood next to bad guy", -40);
        }
        else{
            Points.record("Stood instead of sitting", -10);
        }
        if (c.time.getProgress() == 1) {
            Points.record("Didn't stand in time", -10);
        } else {
            Points.record("Stood in time", 5);
        }
        c.sit1.setDisable(true);
        c.sit2.setDisable(true);
        c.sit3.setDisable(true);
        c.sit4.setDisable(true);
        c.stand1.setDisable(true);
        c.stand2.setDisable(true);
        c.sit1.setVisible(false);
        c.sit2.setVisible(false);
        c.sit3.setVisible(false);
        c.sit4.setVisible(false);
        c.stand1.setVisible(false);
        c.stand2.setVisible(false);
        nextScreen(event);
    }

    /**
     * Sets the controller to the controller for Phase 3, sets up the next controller and the screen.
     */
    @FXML
    private void goToPhase3(){
        setController(14, true);
        nextController.setup();
        myController.setScreen("phase 3");
    }
}//end of "Phase2Controller" class