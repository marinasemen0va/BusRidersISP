/**
 * Controls the preloader screen and Preloading.fxml.
 * <h2> Course Info: </h2>
 * ICS4UO with V. Krasteva
 *
 * @author Kiran K Basra and Marina Semenova
 * @version 1.3 June 10, 2019
 * Modifications: N/A
 */

//imported classes
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import java.net.URL;
import java.util.ResourceBundle;

public class PreLoadingController implements Initializable{
    // instance variables
    /**
     * Used to show the progress of loading.
     */
    @FXML
    private ProgressBar progressBar;

    /**
     * Progress label: shows percent of game loaded.
     */
    @FXML
    private Label progress;

    /**
     * Label used to update progress.
     */
    public static Label label;

    /**
     * A temporary ProgressBar used to update progressBAr.
     */
    public static ProgressBar progressBarTemp;

    /**
     * The initialize method to initialize the preloader.
     *
     * @param url an unused parameter from the superclass.
     * @param rb an unused parameter from the superclass.
     */
    public void initialize (URL url, ResourceBundle rb) {
        label = progress;
        progressBarTemp = progressBar;
        progressBar.setStyle("-fx-accent:#072c68;-fx-control-inner-background: #fff;-fx-font-family: Spaceport;-fx-color:#ffffff; -fx-font-size: 18em;");
    }

}
