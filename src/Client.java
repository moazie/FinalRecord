import java.io.*;
import java.time.LocalDate;

public class Client {

    static int optionMain;
    static String filePath = "data/data.csv";
    static String item = null;
    static String tags = "";
    static String desc = "";

    static String nameSearch = null;
    static String tagSearch = null;
    static String descSearch = null;

    static float score = 0;
    static boolean ifTag = false;

    public static int sort = 0;
    static boolean cancelSearch = false; // Flag to track back action
    static boolean isDeleteMode = false; // Flag to indicate deletion flow

    public static void main(String[] args) throws Exception {
        boolean running = true;
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        while (running) {
            clearConsole();
            userInterface();

            switch (optionMain) {
                case 1:
                    // Option 1: Write a Review
                    if (item != null) {
                        fileWrite();
                    }
                    pauseForUser(keyboard);
                    resetFields();
                    break;

                case 2:
                    // Option 2: Search / Edit / Delete Reviews
                    if (!cancelSearch) { // Only read/display if user didn't hit "Back"
                        clearConsole();
                        fileRead();
                        pauseForUser(keyboard);
                    }
                    resetFields();
                    break;

                case 3:
                    // Option 3: Exit Program
                    running = false;
                    System.out.println("See you later!");
                    break;

                default:
                    break;
            }
        }
    }

    public static void userInterface() {
        TUI tui = new TUI();

        String menu = """
                                                  
                 _____ _         _    _____ _____               _ 
                |   __|_|___ ___| |  | __  |   __|___ ___ ___ _| |
                |   __| |   | .'| |  |    -|   __|  _| . |  _| . |
                |__|  |_|_|_|__,|_|  |__|__|_____|___|___|_| |___|
                                                  

                Welcome to the Final Record!
                Choose an option:
                1. Write a review
                2. Search/Manage review(s)
                3. Exit

                Option: """;

        String menuReview = "Write a name for what would you like to review (Ctrl C to exit): ";
        String menuReviewScore = "Write a Score for the review (0 - 10 (including fractional values)): ";
        String menuReviewTags = "Would you like to write any tags? (y/N) ";

        String menuSearch = """
                Choose an option:
                1. Search by Tag
                2. Search by Name
                3. Search by Description
                4. List All
                5. Delete a Review
                6. Back to Main Menu

                Option: """;

        String menuEnterText = "Enter text: ";
        String menuEnterDesc = "Write a description (or leave blank): ";
        String menuEnterTags = "Enter tags followed by hashtags with no spaces (e.g. #Books#Movies#Shows): ";

        String menuSearchList = """
                Choose an option:
                1. ★ High -> Low
                2. ★ Low -> High
                3. Date newest first
                4. Date oldest first
                5. Tag groups
                Option: """;

        // Main Menu selection
        optionMain = tui.readInteger(menu, 1, 3);

        if (optionMain == 1) {
            item = tui.readText(menuReview);
            score = tui.readFloat(menuReviewScore, 0.0f, 10.0f);
            ifTag = tui.readBool(menuReviewTags, false);
            if (ifTag) {
                tags = tui.readText(menuEnterTags);
            }
            desc = tui.readTextDesc(menuEnterDesc);

        } else if (optionMain == 2) {
            item = null;
            // Range updated to 1..6 to accommodate Delete and Back options
            int searchOption = tui.readInteger(menuSearch, 1, 6);

            switch (searchOption) {
                case 1:
                    tagSearch = tui.readText(menuEnterText);
                    break;
                case 2:
                    nameSearch = tui.readText(menuEnterText);
                    break;
                case 3:
                    descSearch = tui.readText(menuEnterText);
                    break;
                case 4:
                    sort = tui.readInteger(menuSearchList, 1, 5);
                    break;
                case 5:
                    isDeleteMode = true; // Trigger deletion workflow in ReviewReader
                    break;
                case 6:
                    cancelSearch = true; // Flag back button
                    break;
                default:
                    break;
            }
        }
    }

    public static void validateInput(String field) {
        if (field == null) {
            return;
        }
        if (field.contains(",") || field.contains("\n") || field.contains("\r")) {
            throw new IllegalArgumentException(
                    "Input contains forbidden CSV characters (commas or newlines): " + field);
        }
    }

    public static void fileWrite() throws IllegalArgumentException {
        if (item == null) {
            return;
        }

        validateInput(item);
        validateInput(tags);
        validateInput(desc);

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.append(item + "," + score + "," + tags + "," + LocalDate.now() + "," + desc + "\n");
            System.out.println("Data successfully written!");
        } catch (IOException e) {
            System.err.println("Could not save data: " + e.getMessage());
        }
    }

    public static void fileRead() {
        ReviewReader reader = new ReviewReader();
        reader.toString();
    }

    public static void resetFields() {
        item = null;
        tags = "";
        desc = "";
        score = 0;
        ifTag = false;

        nameSearch = null;
        tagSearch = null;
        descSearch = null;

        sort = 0;
        cancelSearch = false;
        isDeleteMode = false;
    }

    public static void pauseForUser(BufferedReader reader) {
        System.out.println("\nPress Enter to return to the main menu...");
        try {
            reader.readLine();
        } catch (IOException e) {
            // Ignore error on enter press
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
            System.out.println("Check the 'clear' command.");
        }
    }
}