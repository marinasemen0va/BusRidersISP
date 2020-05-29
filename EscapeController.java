/**
 * The escape class for the safety quiz after level 3.
 * <h2> Course Info: </h2>
 *  ICS4UO with V. Krasteva
 *
 * @author Kiran K Basra and Marina Semenova.
 * @version 1.3, June 10 2019.
 * Modifications: N/A - new file.
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
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EscapeController implements Initializable, ControlledScreen
{
    //instance variables

    /** The text that appears on the screen.*/
    @FXML
    private Label text;

    /**The very last button.*/
    @FXML
    private Button end;

    /** The button to go to the next question. */
    @FXML
    private Button cont;

    /**The TRUE button for users to select.*/
    @FXML
    private Button trueButton;

    /** The FALSE button users can select.*/
    @FXML
    private Button falseButton;

    /**To show progress through the phase, can be updated.*/
    @FXML
    private ProgressIndicator progress;

    /** Image that displays whether right or wrong answer. */
    @FXML
    private ImageView display;

    /** The controller.*/
    private ScreensController myController;

    /**Determines which screen is currently being displayed.*/
    private int screenCount;

    /** Controls the screen, accesses and modifies elements.*/
    private EscapeController c;

    /** ArrayList of questions. */
    private ArrayList<String> questions;

    /** ArrayList of explanations. */
    private ArrayList<String> explanations;

    /**ArrayList of answers.*/
    private ArrayList<Boolean> answers;

    /**This integer keeps track of which question is on screen. */
    private int index;

    /**This variables keeps track of the users answer;*/
    private boolean answer;

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
     * This sets the controller {@link #c} to a specified controller..
     * The controller depends on the value of {@link #index}.
     *
     * @param index accesses a screen in the array in ScreensController
     */
    private void setController(int index){
        c = (EscapeController) ScreensController.getController(index);
    }

    /**
     * This method randomizes the quiz questions and pairs them with the explanations and the answers.
     */
    private void randomize(){
        questions = new ArrayList<>();
        ArrayList<String> questionsTemp = new ArrayList<String>();
        questionsTemp.add("The best place to sit on the bus is in front, near the driver.");
        questionsTemp.add("If someone who makes you nervous sits beside you, stay so that you don’t offend them.");
        questionsTemp.add("You need to pay to ride the bus when you are 13 or over.");
        questionsTemp.add("If you press the stop button at the wrong time, you do not have to get off the bus.");
        questionsTemp.add("If you are a stop late getting off the bus, you should ride all the way to the station.");
        questionsTemp.add("There is a way to tell the number of minutes until the next TTC bus comes.");
        questionsTemp.add("On the bus, you should carry your backpack on your back so it doesn’t get stolen.");
        explanations = new ArrayList<String>();
        ArrayList<String> explanationsTemp = new ArrayList<String>();
        explanationsTemp.add("FALSE: the best place is close to the back, out of the way of traffic.");
        explanationsTemp.add("FALSE: calmly go sit somewhere else. If you can, try to sit beside a woman with kids or someone else who looks friendly and trustworthy.");
        explanationsTemp.add("TRUE: student fare is $2.10 with Presto and $2.15 without. For adults (20+) the fare is $3.10.");
        explanationsTemp.add("TRUE: if you press the wrong stop, don’t worry! Just stay on.");
        explanationsTemp.add("FALSE: if you are only a few stops late, you can just get out and walk.");
        explanationsTemp.add("TRUE: each bus stop has a 3 or 4-digit number written on the pole. If you text that number to 898-882, it will automatically text back the time until the next bus reaches the stop.");
        explanationsTemp.add("FALSE: when you are standing, put your backpack on the floor to rest your back and keep it out of other peoples’ ways. It is easier to keep track of when you put it between your feet. When you are sitting, hold your backpack in your lap.");
        answers = new ArrayList<Boolean>();
        ArrayList<Boolean> answersTemp = new ArrayList<Boolean>();
        answersTemp.add(false);
        answersTemp.add(false);
        answersTemp.add(true);
        answersTemp.add(true);
        answersTemp.add(false);
        answersTemp.add(true);
        answersTemp.add(false);
        for (int x = 0; x < 7; x++){
            int random = (int) (Math.random() * answersTemp.size());
            questions.add(questionsTemp.get(random));
            explanations.add(explanationsTemp.get(random));
            answers.add(answersTemp.get(random));
            questionsTemp.remove(random);
            explanationsTemp.remove(random);
            answersTemp.remove(random);
        }
    }

    /**
     * The setup method. It sets the controller c and sets the level.
     * It sets several things about the style and the introductory message.
     */
    @FXML
    public void setup(){
        setController(19);
        c.randomize();
        c.screenCount = 0;
        c.index = 0;
        c.progress.setStyle(" -fx-progress-color: #072c68;-fx-font-family: Spaceport;-fx-color:#ffffff; -fx-font-size: 18em;");
        c.text.setText("As you've mastered how to ride the bus, here's a little quiz to test your skills! Don't worry you'll do great! If you don't get something it will be explained!");
        c.cont.setVisible(true);
        c.progress.setProgress(0);
        c.screenCount = 0;
        c.end.setVisible(false);
        c.cont.setVisible(true);
    }

    /**
     * Displays the current question on c.
     */
    private void displayQuestion(){
        c.progress.setProgress((10/150.0) * (screenCount));
        c.text.setText(questions.get(index));
        c.cont.setVisible(false);
        c.falseButton.setVisible(true);
        c.trueButton.setVisible(true);
        c.display.setVisible(false);
    }

    /**
     * Displays the current explanation on c.
     */
    private void displayExplanation(){
        c.progress.setProgress((10/150.0) * (screenCount));
        c.text.setText(explanations.get(index));
        c.cont.setVisible(true);
        c.falseButton.setVisible(false);
        c.trueButton.setVisible(false);
        if (answer){
            c.display.setImage(new Image("/resources/img/right.png"));
        }
        else{
            c.display.setImage(new Image("/resources/img/wrong.png"));
        }
        c.display.setVisible(true);
        c.index++;
    }

    /**
     * This method goes to the next screen.
     * It checks the screenCount to display the right information on the screen.
     *
     * @param e an event.
     */
    @FXML
    private void nextScreen (ActionEvent e){
        screenCount++;
        if (screenCount == 1){
            c.displayQuestion();
        }
        else if (screenCount == 2){
            c.displayExplanation();
        }
        else if (screenCount == 3){
            c.displayQuestion();
        }
        else if (screenCount == 4){
            c.displayExplanation();
        }
        else if (screenCount == 5){
            c.displayQuestion();
        }
        else if (screenCount == 6){
            c.displayExplanation();
        }
        else if (screenCount == 7){
            c.displayQuestion();
        }
        else if (screenCount == 8){
            c.displayExplanation();
        }
        else if (screenCount == 9){
            c.displayQuestion();
        }
        else if (screenCount == 10){
            c.displayExplanation();
        }
        else if (screenCount == 11){
            c.displayQuestion();
        }
        else if (screenCount == 12){
            c.displayExplanation();
        }
        else if (screenCount == 13){
            c.displayQuestion();
        }
        else if (screenCount == 14){
            c.displayExplanation();
        }
        else{
            c.display.setVisible(false);
            c.progress.setProgress((10/150.0) * (screenCount));
            c.text.setText("You finished the quiz! I’m sure now you know more about bus safety. You should be so proud that you can ride the bus so well!");
            c.end.setVisible(true);
            c.cont.setVisible(false);
        }
    }

    /**
     * The method used to go to the start screen.
     *
     * @param event an event.
     */
    @FXML
    private void goToStart(ActionEvent event) {
        myController.setScreen("start");
    }

    /**
     * This method determines if the answer was correct or incorrect based on the index.
     *
     * @param event an event.
     */
    @FXML
    private void truePressed(ActionEvent event){
        if (answers.get(index)){
            answer = true;
        }
        else{
            answer = false;
        }
        nextScreen(event);
    }

    /**
     * This method determines if the answer was correct or incorrect.
     *
     * @param event an event.
     */
    @FXML
    private void falsePressed(ActionEvent event){
        if (!answers.get(index)){
            answer = true;
        }
        else{
            answer = false;
        }
        nextScreen(event);
    }
}