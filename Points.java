/**
 * The Points class, to record how many points each player gains
 * <h2> Course Info: </h2>
 *  ICS4UO with V. Krasteva
 *
 * @author Kiran Basra and Marina Semenova
 * @version 1.3 June 10, 2019
 * Modifications: none
 */

// importing statements
import java.util.ArrayList;

public class Points {
    // instance variables
    /**
     * A list of all the points the user has gained and lost.
     */
    private static ArrayList<Integer> pointsList;
    /**
     * The point total of a specific user.
     */
    private static int points;
    /**
     * How many times the points are increased or decreased.
     */
    private static String pointsRecord;
    /**
     * A record of all the tasks the user has completed.
     */
    private static String tasksRecord;

    /**
     * Class constructor. Sets points to 50 and inititalizes all other variables.
     */
    public Points(){
        points = 50;
        pointsRecord = "";
        tasksRecord = "";
        pointsList = new ArrayList<Integer>();
    }

    /**
     * Record an action that gains or loses the user points.
     *
     * @param task the name of the task
     * @param p points gained (if positive) or lost (if negative)
     */
    public static void record(String task, int p){
        if (p > 0){
            pointsRecord += "+";
        }
        pointsRecord += p + "\n";
        pointsList.add(p);
        tasksRecord += task + "\n";
        points += p;
    }

    /**
     * Get the total number of points.
     *
     * @return points (total)
     */
    public static int getPoints() {
        return points;
    }

    /**
     * Returns the record of all the points, plus the total at the end.
     *
     * @return points record and points (as a String)
     */
    public static String getPointsRecord(){
        return pointsRecord += points;
    }

    /**
     * Returns the tasks performed, with the word 'total' at the end.
     *
     * @return task names
     */
    public static String getTasksRecord(){
        return tasksRecord += "Total:";
    }

    /**
     * Returns if the user has won or lost.
     *
     * @return status (true if over 50, false otherwise).
     */
    public static boolean getPassFail(){
        return points > 50;
    }
}
