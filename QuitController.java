/**
 * The controller for the Quit screen and Quit.fxml
 * <h2> Course Info: </h2>
 * ICS4UO with V. Krasteva
 *
 * @author Kiran K Basra and Marina Semenova
 * @version 1.3 June 10, 2019
 * Modifications: N/A
 */

//imported classes
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class QuitController implements Initializable, ControlledScreen {
    // instance variables
    /**
     * ScreensController instance for this controller
     */
    private ScreensController myController;

    /**
     * The stage for this screen
     */
    private Stage stage;

    /**
     * A method to handle button action.
     * When a the button is clicked, the stage is closed.
     *
     * @param event an event
     */
    @FXML
    private void handleButtonAction (ActionEvent event){
        stage = (Stage) ((Button)event.getSource()).getScene().getWindow();
        stage.close();
    }

    /**
     * Since this class implements Initializable, it needs this method.
     *
     * @param location An unused parameter.
     * @param resources An unused parameter.
     */
    public void initialize(URL location, ResourceBundle resources) { }

    /**
     * Sets the screenParent controller to the parameter screenParent.
     *
     * @param screenParent  the new screenParent controller.
     */
    public void setScreenParent(ScreensController screenParent) {
        myController = screenParent;
    }
}
