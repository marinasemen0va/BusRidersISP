/**
 * The interface made for switching screens.
 * <h2> Course Info: </h2>
 *  ICS4UO with V. Krasteva
 *
 * @author Kiran K Basra and Marina Semenova.
 * @version 1.3 June 10, 2019.
 * Modifications: none.
 */

public interface ControlledScreen {

    /**
     * Sets the screen parent.
     *
     * @param screenPage the ScreenController responsible for that particular phase.
     */
    void setScreenParent(ScreensController screenPage);
}
