package validation;

/**
 * This is the age validation class.
 * <h2> Course Info: </h2>
 * ICS4UO with V. Krasteva
 *
 * @author Kiran Basra and Marina Semenova.
 * @version 1.3 June 10, 2019.
 * Modifications: n/a because new class.
 */

import javafx.scene.control.TextField;

public class AgeValidation extends TextField {
    // instance variables
    /** The age of the user.*/
    private static int age;

    /**
     * The constructor of the class.
     * Sets to prompt text to ...
     */
    public AgeValidation(){
        this.setPromptText("...");
    }

    /**
     * Method to replace the text. Inherited from the super.
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
     * Replace selection method. Inherited from the super.
     *
     * @param text text
     */
    public void replaceSelection(String text) {
        super.replaceSelection(text);
        verify();
    }

    /**
     * Verify method.
     * Verifies that the string entered is acceptable, otherwise sets to "".
     * The user's age must be under 100 and over 0.
     */
    private void verify() {
        int userAge = 0;
        try{
            userAge = Integer.parseInt(getText());
        }
        catch(Exception e){
            setText("");
            return;
        }
        if (userAge >= 100 || userAge <= 0){
            setText("");
            return;
        }
        age = userAge;
    }

    /**
     * Returns an errortrapped age to the main BusRiders class.
     * @return age
     */
    public static int getAge(){
        return age;
    }

}
