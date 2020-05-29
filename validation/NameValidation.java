/**
 * This is the name validation class.
 * <h2> Course Info: </h2>
 * ICS4UO with V. Krasteva
 *
 * @author Kiran Basra and Marina Semenova.
 * @version 1.3 June 10, 2019.
 * Modifications: none.
 */

package validation;

import javafx.scene.control.TextField;

public class NameValidation extends TextField {
    // instance variables
    /** The name of the user.*/
    private static String name;

    /**
     * The constructor. Sets the prompt text to '...'.
     */
    public NameValidation(){
        this.setPromptText("...");
    }

    /**
     * Replace text method. Inherited from super.
     *
     * @param start start
     * @param end end
     * @param text text
     */
    public void replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        verify();
    }

    /**
     * Replace selection method. Inherited from super.
     *
     * @param text the text being replaced.
     */
    public void replaceSelection(String text) {
        super.replaceSelection(text);
        verify();
    }

    /**
     * Verify method.
     * Verifies that the string entered is under 8 characters, otherwise sets the length.
     */
    private void verify() {
        if (getText().length() > 8) {
            setText(getText().substring(0, 8));
        }
        name = getText();
    }

    /**
     * Returns an errortrapped name.
     * If name is null returns user.
     * @return name or "user"
     */
    public static String getName(){
        if (name == null || name.trim().equals("")){
            name = "user";
        }
        return name.replaceAll("\\s","");
    }

}
