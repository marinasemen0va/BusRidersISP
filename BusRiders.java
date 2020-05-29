/**
 * The driver class for the Bus Riders! game.
 * <h2> Course Info: </h2>
 * ICS4UO with V. Krasteva
 *
 * @author Kiran Basra and Marina Semenova
 * @version 1.3 June 10, 2019
 * Modifications: none
 * Time spent: finalizing everything about the game. This included adding 2 levels and fixing bugs
 */

// importing classes
import com.sun.javafx.application.LauncherImpl;
import javafx.application.Application;
import javafx.application.Preloader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BusRiders extends Application {
    // instance variables
    /** Used for the preloader process, limits how long the preloader lasts.*/
    private static final int COUNT_LIMIT = 100000; // for loading process

    /**
     * The main entry point for all JavaFX applications.
     * It loads resources.fonts and creates a screen controller.
     * It loads all the screens of the program, and sets the screen to the first screen.
     * It creates a new group and a new scene and sets the primary stage to the new scene.
     *
     * @param primaryStage the primary stage of the JavaFX application.
     * @throws Exception An exception for errortrapping.
     */
    public void start(Stage primaryStage) throws Exception{
        Image icon = new Image(getClass().getResourceAsStream("/resources/img/bus icon.png"));
        Font.loadFont(BusRiders.class.getResourceAsStream("resources/fonts/Shaka Pow.ttf"), 10);
        Font.loadFont(BusRiders.class.getResourceAsStream("resources/fonts/Spaceport_2006.otf"), 10);
        ScreensController mainContainer = new ScreensController();
        mainContainer.loadScreen("get name", "fxml/GetName.fxml");
        mainContainer.loadScreen("name display", "fxml/NameDisplay.fxml");
        mainContainer.loadScreen("company popup", "fxml/CompanyPopup.fxml");
        mainContainer.loadScreen("info popup", "fxml/InfoPopup.fxml");
        mainContainer.loadScreen("start", "fxml/Start.fxml");
        mainContainer.loadScreen("quit", "fxml/Quit.fxml");
        mainContainer.loadScreen("help", "fxml/Help.fxml");
        mainContainer.loadScreen("rules", "fxml/Rules.fxml");
        mainContainer.loadScreen("high scores", "fxml/HighScores.fxml");
        mainContainer.loadScreen("level select", "fxml/LevelSelect.fxml");
        mainContainer.loadScreen("level info", "fxml/LevelInfo.fxml");
        mainContainer.loadScreen("phase 1", "fxml/Phase1.fxml");
        mainContainer.loadScreen("phase 2", "fxml/Phase2.fxml");
        mainContainer.loadScreen("get age", "fxml/GetAge.fxml");
        mainContainer.loadScreen("phase 3", "fxml/Phase3.fxml");
        mainContainer.loadScreen("phase 4", "fxml/Phase4.fxml");
        mainContainer.loadScreen("win lose", "fxml/WinLose.fxml");
        mainContainer.loadScreen("point breakdown", "fxml/PointBreakdown.fxml");
        mainContainer.loadScreen("name prompt", "fxml/NamePrompt.fxml");
        mainContainer.loadScreen("escape", "fxml/Escape.fxml");
        mainContainer.setScreen("get age");
        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root, 720, 480, Color.rgb(7, 44, 104));
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Bus Riders!");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Method to initialize the application.
     * Was overridden from the initial init, which did nothing.
     * Notifies the preloader of the progress.
     *
     * @throws Exception An errortrapping exception.
     */
    public void init() throws Exception{
        for (int x = 0; x < COUNT_LIMIT; x++){
            double progress = (100 * x) / COUNT_LIMIT;
            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(progress));
        }
    }

    /**
     * Main method of the project.
     * Launches the application.
     *
     * @param args The arguments for the class.
     */
    public static void main(String[] args) {
        LauncherImpl.launchApplication(BusRiders.class, PreLoading.class, args);
    }
}