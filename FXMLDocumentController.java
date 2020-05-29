/**
 * This class controls almost all .fxml files.
 * <h2> Course Info: </h2>
 *  ICS4UO with V. Krasteva
 *
 * @author Kiran K Basra and Marina Semenova.
 * @version 1.3 June 10, 2019.
 * Modifications: bug fixes.
 **/

//import statements
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import validation.AgeValidation;
import validation.NameValidation;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable, ControlledScreen {
    // instance variables
    /** From Level Selection, this button leads to Level 2. It needs to be disabled and enabled.*/
    @FXML
    private Button level2;

    /** From Level Selection, this button leads to Level 3. It needs to be disabled and enabled.*/
    @FXML
    private Button level3;

    /** The greeting message label. This needs to be updated from the controller, to be personalized with the user's name.*/
    @FXML
    private Label name;

    /** The anchor pane centers the greeting message on screen. */
    @FXML
    private AnchorPane anchorPane;

    /**The information label: it sets the pre-level information according to which level has been selected.*/
    @FXML
    private Label info;

    /** The label for high scoring user's names in Level 1.*/
    @FXML
    private Label usersLevel1;

    /** The label for high scoring user's names in Level 2. */
    @FXML
    private Label usersLevel2;

    /** The label for high scoring user's names in Level 3. */
    @FXML
    private Label usersLevel3;

    /** The label for high scoring user's scores in Level 1.*/
    @FXML
    private Label scoresLevel1;

    /** The label for high scoring user's scores in Level 2.*/
    @FXML
    private Label scoresLevel2;

    /**
     * The label for high scoring user's scores in Level 3.
     */
    @FXML
    private Label scoresLevel3;

    /**
     * The status label for if the level has been lost or won.
     */
    @FXML
    private Label status;

    /**
     * Tasks completed, for the point breakdown.
     */
    @FXML
    private Label listTasks;

    /**
     * Points gained, for the point breakdown.
     */
    @FXML
    private Label listPoints;

    /**
     * Rank on the high score chart, for the point breakdown.
     */
    @FXML
    private Label highScoreRank;

    /**
     * The button that takes the user to point breakdown.
     */
    @FXML
    private Button pointBreakdownButton;

    /**
     * User name, for the point breakdown.
     */
    @FXML
    private Label nameLabel;

    /**
     * The screen controller for the game.
     */
    private ScreensController myController;

    /**
     * Int for which levels have been unlocked by the user.
     **/
    private static int levelUnlocked;

    /**
     * The level currently being played, which affects what is displayed.
     */
    private static int currentLevel;

    /**
     * The controller of the screen, used to access and modify items.
     */
    private FXMLDocumentController c;

    /**
     * The controller of the next screen, for accessing and modifying items.
     */
    private Phase1Controller nextController;

    /**
     * The controller of the escape screen, for accessing and modifying items.
     */
    private EscapeController escapeController;

    /**
     * The rank of the current user.
     */
    private static int rank;

    /**
     * Since this class implements Initialiable, it needs this method.
     * @param location an unused parameter.
     * @param resources an unused parameter.
     */
    public void initialize(URL location, ResourceBundle resources) {
    }

    /**
     * Sets the the screen parent myController to a parameter ScreensController.
     * @param screenParent what we are setting myController to.
     */
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }

    /**
     * This method sends the user to the start/quit screen.
     * Used in Help, HighScores, LevelSelect, NameDisplay, and Rules.
     * @param event an event.
     */
    @FXML
    private void goToStart(ActionEvent event){
        myController.setScreen("start");
    }

    /**
     * This method sets the name for the name label in NameDisplay.fxml.
     * It sets the levelUnlocked to 1 (only the first level can be played).
     * It loads high scores.
     * It sets the formatting for the welcome message.
     * A helper method for the GetName class.
     * @param name the name retrieved from NameValidation.
     */
    public void setName(String name){
        levelUnlocked = 1;
        new HighScores();
        this.name.setText("Hello, " + name + "!");
        this.name.setMaxWidth(Double.MAX_VALUE);
        anchorPane.setLeftAnchor(this.name, 0.0);
        anchorPane.setRightAnchor(this.name, 0.0);
        this.name.setAlignment(Pos.CENTER);
    }

    /**
     * The method to go to the quit screen.
     * Used in Start.fxml.
     * @param event an event.
     */
    @FXML
    private void goToQuit(ActionEvent event){
        myController.setScreen("quit");
    }

    /**
     * The method used to go to the help screen.
     * Used in Start.fxml.
     * @param event an event.
     */
    @FXML
    private void goToHelp(ActionEvent event){
        myController.setScreen("help");
    }

    /**
     * The method used to go to the rules screen.
     * Used in Start.fxml.
     * @param event an event.
     */
    @FXML
    private void goToRules(ActionEvent event) {
        myController.setScreen("rules");
    }

    /**
     * The method used to go to the level select screen.
     * It sets the controller to controller number 9 and sets the screen to level select.
     * It also sees how many levels have been unlocked.
     * Used in Start.fxml.
     * @param event an event.
     */
    @FXML
    private void goToLevelSelect(ActionEvent event) {
        setController(9, false);
        c.computeUnlock();
        myController.setScreen("level select");
    }

    /**
     * The method used to go to the high scores screen. It also calls the setHighScoreLabels method.
     * Used in Start.fxml.
     * @param event an event.
     */
    @FXML
    private void goToHighScores(ActionEvent event) {
        setHighScoreLabels();
        myController.setScreen("high scores");
    }

    /**
     * This method calls the setController method with parameters of 8 and false.
     * It sets the text of the users and scores from Level 1 through 3 to the information from the HighScores class.
     */
    private void setHighScoreLabels(){
        setController(8, false);
        c.usersLevel1.setText(HighScores.getUsers(1));
        c.usersLevel2.setText(HighScores.getUsers(2));
        c.usersLevel3.setText(HighScores.getUsers(3));
        c.scoresLevel1.setText(HighScores.getScores(1));
        c.scoresLevel2.setText(HighScores.getScores(2));
        c.scoresLevel3.setText(HighScores.getScores(3));
    }

    /**
     * This returns the controller for a screen. The controller depends on the value of index.
     * We may go to the next screen if next is true.
     * Used in Start.fxml, GetName.fxml, and LevelSelect.fxml.
     * @param index accesses a screen in the array in ScreensController
     * @param next whether or not we are going to next screen
     */
    private void setController(int index, boolean next){
        if(!next) {
            c = (FXMLDocumentController) ScreensController.getController(index);
        }
        else{
            if (currentLevel == 3 && Points.getPassFail()){
                escapeController = (EscapeController) ScreensController.getController(index);
            }
            else {
                nextController = (Phase1Controller) ScreensController.getController(index);
            }
        }
    }

    /**
     * This sees how many levels have been unlocked by checking the levelUnlocked variable.
     */
    public void computeUnlock(){
        if (levelUnlocked == 2){
            level2.setDisable(false);
        }
        else if (levelUnlocked == 3){
            level3.setDisable(false);
            level2.setDisable(false);
        }
    }

    /**
     * This opens up an information popup.
     * Used in LevelSelect.fxml.
     * @param event an event.
     */
    @FXML
    private void openPopup(ActionEvent event)
    {
        myController.setScreen("info popup");
    }

    /**
     * This opens up a popup with information about the conpany.
     * Used in Start.fxml.
     */
    @FXML
    private void openPopup2()
    {
        myController.setScreen("company popup");
    }

    /**
     * This goes to Level One.
     * It is used in LevelSelect.
     * @param event an event.
     */
    @FXML
    private void goToLevel1(ActionEvent event){
        currentLevel = 1;
        setLevelInfo();
        myController.setScreen("level info");
    }

    /**
     * This goes to the Level Two.
     * It is used in LevelSelect.
     * @param event an event.
     */
    @FXML
    private void goToLevel2(ActionEvent event){
        currentLevel = 2;
        setLevelInfo();
        myController.setScreen("level info");

    }

    /**
     * This goes to the Level Three.
     * It is used in LevelSelect.
     * @param event an event.
     */
    @FXML
    private void goToLevel3(ActionEvent event){
        currentLevel = 3;
        setLevelInfo();
        myController.setScreen("level info");
    }

    /**
     * This changes the screen to Name Display.
     * It is used in GetName.
     * @param event an event.
     */
    @FXML
    private void goToNameDisplay(ActionEvent event){
        setController(1, false);
        c.setName(NameValidation.getName());
        myController.setScreen("name display");
    }

    /**
     * This sets the text for the level information.
     * It changes the screen to the appropriate information and shows a message.
     */
    private void setLevelInfo(){
        setController(10, false);
        if (currentLevel == 1){
            c.info.setText("We’re going to ride the bus with Big Sister, your guide! She’s going to show you around and tell you what to do and when to do it. Remember what she tells you - it will be helpful later on. Don’t worry - this will be easy!");
        }
        else if (currentLevel == 2){
            c.info.setText("This time, you’re riding the bus without Big Sister as your guide. Don’t worry though - nothing unexpected will happen this time. Just remember what she told you and pay attention to your surroundings.");
        }
        else if (currentLevel== 3){
            c.info.setText("This level, you’re going to run up against some unexpected situations and you’ll have to figure out how to deal with them. Don’t worry, and use your common sense. As long as you’re smart and you think, everything will be fine!");
        }
    }

    /**
     * Sends the screen  and screen controller to the screen for Phase One and sets up the next controller.
     * Used in LevelSelection.fxml.
     */
    @FXML
    private void goToPhase1(){
        new Points();
        setController(11, true);
        nextController.setup();
        myController.setScreen("phase 1");
    }

    /**
     * Goes to the GetName screen as long as the age given was aappropriate.
     * Used in GetAge.
     * @param event an Event.
     */
    @FXML
    private void goToGetName(ActionEvent event){
        if(AgeValidation.getAge() != 0){
            myController.setScreen("get name");
        }
    }


    /**
     * Formats the status of whether the user won or lost.
     * @param status if the user won or lost.
     */
    public void setStatus(String status){
        this.status.setText(status);
        this.status.setMaxWidth(Double.MAX_VALUE);
        anchorPane.setLeftAnchor(this.status, 0.0);
        anchorPane.setRightAnchor(this.status, 0.0);
        this.status.setAlignment(Pos.CENTER);
    }

    /**
     * Goes to the pointsBreakdown method. Sets the controller, the points, and the screen.
     * @param event
     */
    @FXML
    private void goToPointBreakdown(ActionEvent event){
        setController(16, false);
        c.pointBreakdownButton.setDisable(false);
        setController(17, false);
        c.setPoints();
        myController.setScreen("point breakdown");
    }

    /**
     * Sets the names, points, and the tasks for the high scores method.
     * Computes the rank by calling the computeScore method.
     */
    private void setPoints(){
        this.listTasks.setText(Points.getTasksRecord());
        this.listPoints.setText(Points.getPointsRecord());
        String name = NameValidation.getName();
        if (name.equals("user")){
            name = "-";
        }
        this.nameLabel.setText("Name: " + name);
        rank = HighScores.computeScore(currentLevel, Points.getPoints(), NameValidation.getName());
        if (rank == 0){
            this.highScoreRank.setText("High Score Rank: -");
        }
        else {
            this.highScoreRank.setText("High Score Rank: " + rank);
        }
    }

    /**
     * The final button, to go to level select or the name prompt.
     * @param event an event.
     */
    @FXML
    private void finalButton(ActionEvent event){
        setController(9, false);
        c.computeUnlock();
        if (NameValidation.getName().equals("user") && rank != 0){
            myController.setScreen("name prompt");
        }
        else if (currentLevel == 3 && Points.getPassFail()){
            c.goToEscape();
        }
        else{
            myController.setScreen("level select");
        }
    }

    /**
     * Goes to Escape screen if the game has been completed.
     */
    private void goToEscape(){
        setController(19, true);
        escapeController.setup();
        myController.setScreen("escape");
    }

    /**
     * Goes to Level Select after a level has been completed.
     * It computes the score of the previous level.
     * @param event
     */
    @FXML
    private void goToLevelSelect2(ActionEvent event){
        if (!NameValidation.getName().equals("user")){
            HighScores.computeScore(currentLevel, Points.getPoints(), NameValidation.getName());
        }
        if (currentLevel == 3 && Points.getPassFail()){
            setController(18, false);
            c.goToEscape();
        }
        else{
            setController(9, false);
            c.computeUnlock();
            myController.setScreen("level select");
        }
    }

    /**
     * Get current level - the level the game is on right now.
     * @return current level
     */
    public static int getCurrentLevel(){
        return currentLevel;
    }

    /**
     * Get how many levels have been unlocked.
     * @return level unlocked
     */
    public static int getLevelUnlocked(){
        return levelUnlocked;
    }

    /**
     * To increment the number of levels unlocked.
     */
    public static void incrementLevelUnlocked(){
        levelUnlocked++;
    }
}// end of class