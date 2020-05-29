/**
 * The main class for the preloading screen.
 * <h2> Course Info: </h2>
 * ICS4UO with V. Krasteva
 *
 * @author Kiran K Basra and Marina Semenova
 * @version 1.3 June 10, 2019
 * Modifications: N/A
 */

//import statements
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PreLoading extends Preloader {
    // instance variables
    /**
     * The preloader's stage.
     */
    private Stage preloaderStage;

    /**
     * The preloader's scene.
     */
    private Scene scene;

    /**
     * The class constructor. Empty.
     */
    public PreLoading(){ }

    /**
     * Method to initialize the application. Loads the scene and the parent.
     * Overridden from Application initialization method.
     *
     * @throws Exception an errortrapping exception.
     */
    public void init() throws Exception{
        Parent preloaderRoot = FXMLLoader.load(getClass().getResource("fxml/PreLoading.fxml"));
        scene = new Scene (preloaderRoot, 300, 300);
    }

    /**
     * The main entry point for all JavaFX applications.
     * Sets the scene, initializes the style, shows the preloader.
     *
     * @param primaryStage the primary stage of the preloader
     * @throws Exception for errortrapping
     */
    public void start(Stage primaryStage) throws Exception{
        Font.loadFont(BusRiders.class.getResourceAsStream("resources/fonts/Spaceport_2006.otf"), 10);
        Image icon = new Image(getClass().getResourceAsStream("/resources/img/bus icon.png"));
        this.preloaderStage = primaryStage;
        preloaderStage.setScene(scene);
        preloaderStage.initStyle(StageStyle.UNDECORATED);
        preloaderStage.getIcons().add(icon);
        preloaderStage.show();
    }

    /**
     *To communicate with the main class. Shows the progress of loading on the preloader.
     *
     *@param info contains the information passed by BusRiders.java
     */
    public void handleApplicationNotification(PreloaderNotification info){
        if (info instanceof ProgressNotification){
            PreLoadingController.label.setText("Loading " + ((ProgressNotification) info).getProgress() + "%");
            PreLoadingController.progressBarTemp.setProgress(((ProgressNotification) info).getProgress()/100);
        }
    }

    /**
     * To communicate with the main class about the state change of if the game is loaded.
     *
     * @param info contains the information passed by BusRiders.java
     */
    public void handleStateChangeNotification (Preloader.StateChangeNotification info){
        StateChangeNotification.Type type = info.getType();
        switch(type){
            case BEFORE_START:
                preloaderStage.hide();
                break;
        }
    }
}
