
import java.io.*;
import java.time.LocalDate;

public class App {

    static int optionMain;
    static String filePath = "data/data.csv";
    static String item = "";
    static String tags = "";

    static String nameSearch = null;
    static String tagSearch = null;
    static String descSearch = null;

    static float score = 0;
    static boolean ifTag = false;

    static int sort = 0;

    public static void main(String[] args) throws Exception {
        //tbi
        clearConsole();
        userInterface();
        fileWrite(item);

    }

    public static void userInterface() {
        TUI tui = new TUI();

        String menu = """
    Choose an option:
    1. Write a review
    2. Search/Edit a review(s)

    Option: """;

        String menuReview = "Write a name for what would you like to review: ";
        String menuReviewScore = "Write a Score for the review (0 - 10 (including fractional values)): ";
        String menuReviewTags = "Would you like to write any tags? (y/N) ";

        String menuSearch = """
    Choose an option:
    1. Search by Tag
    2. Search by Name
    3. Search by Description
    4. List All

    Option: """;

        String menuEnterText = "Enter text: ";
        String menuEnterTags = "Enter tags followed by hashtags with no spaces (e.g. #Books#Movies#Shows): ";

        String menuSearchList = """
    Choose an option:
    1. ★ High -> Low
    2. ★ Low -> High
    3. Date newest first
    4. Date oldest first
    5. Tag groups
    Option: """;

        optionMain = tui.Integer(menu, 1, 2);

        if (optionMain == 1) {
            item = tui.Text(menuReview);
            score = tui.Float(menuReviewScore, 0.0f, 10.0f);
            ifTag = tui.Bool(menuReviewTags, false);
            if (ifTag) {
                tags = tui.Text(menuEnterTags);
            }

        } else {
            item = null;
            optionMain = tui.Integer(menuSearch, 1, 4);

            switch (optionMain) {
                case 1 ->
                    tagSearch = tui.Text(menuEnterText);
                case 2 ->
                    nameSearch = tui.Text(menuEnterText);
                case 3 ->
                    descSearch = tui.Text(menuEnterText);
                default ->
                    sort = tui.Integer(menuSearchList, 1, 5);
            }
        }
    }

    public static void fileWrite(String input) {
        if (input == null) {
            return;
        }

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(input + "," + score + "," + tags + ","+ LocalDate.now() + "\n");
            System.out.println("Data written");

        } catch (IOException e) {
            System.err.println("Could not save data");
        }

    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Check the \'clear\' command.");
        }
    }
}
