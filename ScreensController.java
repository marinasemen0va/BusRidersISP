/**
 * The ScreensController class from the Bus Riders! game.
 * <h2> Course Info: </h2>
 * ICS4UO with V. Krasteva
 *
 * @author Kiran K Basra and Marina Semenova
 * @version 1.3 June 10, 2019
 * Modifications: N/A
 */

//imported classes
import java.util.ArrayList;
import java.util.HashMap;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class ScreensController extends StackPane {
    // instance variables
    /**
     * HashMap variable
     */
    private HashMap<String, Node> screens = new HashMap<>();
    /**
     * ArrayList of controllers - one for each loader screen
     */
    private static ArrayList<ControlledScreen> controllers;

    /**
     * The constructor. Calls the super and initializes the controllers ArrayList.
     */
    public ScreensController() {
        super();
        controllers = new ArrayList<>();
    }

    /**
     * Adds a screen to the list of screens.
     *
     * @param name name of screen
     * @param screen the screen being added
     */
    public void addScreen(String name, Node screen) {
        screens.put(name, screen);
    }

    /**
     * Get one of the screens.
     *
     * @param name name of screen we are trying to get
     * @return the screen with the name we are lookng for
     */
    public Node getScreen(String name) {
        return screens.get(name);
    }

    /**
     * Prepare a screen.
     *
     * @param name name of screen we are showing.
     * @param resource the file of the screen.
     * @return true if it returns successfully, false otherwise.
     */
    public boolean loadScreen(String name, String resource) {
        try {
            FXMLLoader myLoader = new FXMLLoader(getClass().getResource(resource));
            Parent loadScreen = (Parent) myLoader.load();
            ControlledScreen myScreenControler = ((ControlledScreen) myLoader.getController());
            controllers.add(myScreenControler);
            myScreenControler.setScreenParent(this);
            addScreen(name, loadScreen);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Set the screen to a specified screen.
     *
     * @param name name of screen we are setting the the total to.
     * @return true if successful, false otherwise.
     */
    public boolean setScreen(final String name) {
        if (screens.get(name) != null) {   //screen loaded
            final DoubleProperty opacity = opacityProperty();
            if (!getChildren().isEmpty()) {    //if there is more than one screen
                Timeline fade = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 1.0)),
                        new KeyFrame(new Duration(250), new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent t) {
                                getChildren().remove(0);                    //remove the displayed screen
                                getChildren().add(0, screens.get(name));     //add the screen
                                Timeline fadeIn = new Timeline(
                                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                                        new KeyFrame(new Duration(800), new KeyValue(opacity, 1.0)));
                                fadeIn.play();
                            }
                        }, new KeyValue(opacity, 0.0)));
                fade.play();
            } else {
                setOpacity(0.0);
                getChildren().add(screens.get(name));       //no one else been displayed, then just show
                Timeline fadeIn = new Timeline(
                        new KeyFrame(Duration.ZERO, new KeyValue(opacity, 0.0)),
                        new KeyFrame(new Duration(750), new KeyValue(opacity, 1.0)));
                fadeIn.play();
            }
            return true;
        } else {
            System.out.println("Something went wrong. This screen has not been loaded.\n");
            return false;
        }
    }

    /**
     * Unload / remove screens method.
     *
     * @param name name of screen we want to remove.
     * @return true if succesful: otherwise false.
     */
    public boolean unloadScreen(String name) {
        if (screens.remove(name) == null) {
            System.out.println("Something went wrong. This screen doesn't exist.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method to return the screen controller.
     *
     * @param x the index of the screen controller we're looking for.
     * @return screen controller at index x
     */
    public static ControlledScreen getController(int x){
        return controllers.get(x);
    }
}

