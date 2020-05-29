/**
 * The High Scores class, to hold the user scores and rank them.
 * <h2> Course Info: </h2>
 * ICS4UO with V. Krasteva
 *
 * @author Kiran Basra and Marina Semenova
 * @version 1.3 June 10, 2019
 * Modifications: bug fixes.
 */

//import statements
import java.io.*;
import java.util.ArrayList;

public class HighScores {
    // instance variables
    /**
     * ArrayList for Level 1 users.
     */
    private static ArrayList<String> usersLevel1;
    /**
     * ArrayList for Level 2 users.
     */
    private static ArrayList<String> usersLevel2;
    /**
     * ArrayList for Level 3 users.
     */
    private static ArrayList<String> usersLevel3;
    /**
     * ArrayList for Level 1 scores.
     */
    private static ArrayList<Integer> scoresLevel1;
    /**
     * ArrayList for Level 2 scores.
     */
    private static ArrayList<Integer> scoresLevel2;
    /**
     * ArrayList for Level 3 scores.
     */
    private static ArrayList<Integer> scoresLevel3;

    /**
     * Class constructor. Calls getData to load files.
     */
    public HighScores(){
        getData();
    }

    /**
     * Checks if the inputted score belongs in the high scores.
     * If it does, it is added to the high scores.
     * @param level the level the score belongs to, so it is ranked among others of its level.
     * @param score the score of user, to rank the scores.
     * @param name the name of user, to differentiate between scores.
     * @return if the score makes it into the high scores, true. Otherwise, false.
     */
    public static int computeScore(int level, int score, String name) {
        int rank = 0;
        ArrayList<Integer> scores;
        ArrayList<String> users;
        if (level == 1) {
            scores = scoresLevel1;
            users = usersLevel1;
        } else if (level == 2) {
            scores = scoresLevel2;
            users = usersLevel2;
        } else {
            scores = scoresLevel3;
            users = usersLevel3;
        }
        if (scores != null && !scores.isEmpty()) {
            int length = scores.size();
            if (score > scores.get(length - 1)) {
                for (int x = 0; x < scores.size(); x++) { // sorting example
                    if (scores.get(x) < score) {
                        if (!name.equals("user")) {
                            scores.add(x, score);
                            users.add(x, name);
                            if (length + 1 > 10) {
                                scores.remove(length);
                                users.remove(length);
                            }
                        }
                        rank = x + 1;
                        break;
                    }
                }
            } else {
                if (length != 10) {
                    if (!name.equals("user")) {
                        scores.add(score);
                        users.add(name);
                    }
                    rank = scores.size();
                }
            }
        }
        else{
            rank = 1;
            if (!name.equals("user")) {
                scores = new ArrayList<Integer>();
                users = new ArrayList<String>();
                scores.add(score);
                users.add(name);
            }
        }
        if (level == 1) {
            scoresLevel1 = scores;
            usersLevel1 = users;
        } else if (level == 2) {
            scoresLevel2 = scores;
            usersLevel2= users;
        } else {
            scoresLevel3 = scores;
            usersLevel3 = users;
        }
        if (rank != 0 && !name.equals("user")) {
            write(level);
        }
        return rank;
    }

    /**
     * Update the file after a new level is added.
     * Changes the file name based on the level and prints the user name and score to the file.
     * @param level the level of the game, to differentiate between lists
     */
    private static void write(int level){
        PrintWriter w;
        String fileName = "HighScoresLevel";
        ArrayList<Integer> scores;
        ArrayList<String> users;
        if (level == 1) {
            scores = scoresLevel1;
            users = usersLevel1;
            fileName += "1";
        } else if (level == 2) {
            scores = scoresLevel2;
            users = usersLevel2;
            fileName += "2";
        } else {
            scores = scoresLevel3;
            users = usersLevel3;
            fileName += "3";
        }
        try {
            File file = new File(System.getProperty("user.home") + "\\" + fileName + ".txt");
            w = new PrintWriter (new BufferedWriter(new FileWriter (file.getCanonicalPath())));
            for (int x = 0; x < scores.size(); x++)
            {
                w.println(users.get(x));
                w.println(scores.get(x));
            }
            w.close ();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Method to get the current high scores.
     * Opens the file and reads how many lines it has, then closes it.
     * Creates an arraylist for scores and usernames, then updates the relevant list based on the contents of the file.
     */
    private void getData(){
        for (int i = 1; i <= 3; i++){
            String line = "";
            int numberOfLines = 0;
            BufferedReader r;
            File f;
            try
            {
                f = new File(System.getProperty("user.home") + "\\HighScoresLevel" + i + ".txt");
                f.createNewFile();
                r = new BufferedReader (new FileReader(f.getAbsolutePath()));
                line = r.readLine ();
                while (line != null)
                {
                    numberOfLines++;
                    line = r.readLine ();

                }
                r.close ();
            }
            catch (Exception e)
            {
                System.out.println (e);
            }
            ArrayList<String> users = new ArrayList<String>();
            ArrayList<Integer> scores = new ArrayList<Integer>();
            try
            {
                f = new File(System.getProperty("user.home") + "\\HighScoresLevel" + i + ".txt");
                r = new BufferedReader (new FileReader(f.getCanonicalPath()));
                for (int x = 0; x < numberOfLines/2; x++)
                {
                    users.add(r.readLine ());
                    scores.add(Integer.parseInt(r.readLine ()));
                }
                r.close ();
            }
            catch (Exception e)
            {
                System.out.println (e);
            }
            if (i == 1){
                usersLevel1 = users;
                scoresLevel1 = scores;
            }
            else if (i == 2){
                usersLevel2 = users;
                scoresLevel2 = scores;
            }
            else{
                usersLevel3 = users;
                scoresLevel3 = scores;
            }
        }
    }

    /**
     * Outputs the list of highest-scoring users for that particular level.
     * @param level The level of the game in question.
     * @return a String of usernames for that level, to be outputted on the high scores screen
     */
    public static String getUsers(int level){
        ArrayList<String> users;
        if (level == 1){
            users = usersLevel1;
        }
        else if (level == 2){
            users = usersLevel2;
        }
        else{
            users = usersLevel3;
        }
        String total = "";
        for (int x = 0; x < 10; x++){
            if (x < users.size()) {
                total += users.get(x) + "\n";
            }
            else {
                total += "-\n";
            }
        }
        return total;
    }

    /**
     * Outputs the 10 largest scores of a level in order.
     * @param level The relevant level of the game.
     * @return The top 10 scores in order, in String format.
     */
    public static String getScores(int level){
        ArrayList<Integer> scores;
        if (level == 1){
            scores = scoresLevel1;
        }
        else if (level == 2){
            scores = scoresLevel2;
        }
        else{
            scores = scoresLevel3;
        }
        String total = "";
        for (int x = 0; x < 10; x++){
            if (x < scores.size()) {
                total += "" + scores.get(x) + "\n";
            }
            else {
                total += "-\n";
            }

        }
        return total;
    }
}//end of "HighScores" class
