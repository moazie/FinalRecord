
import java.io.*;
import java.util.*;

public class App {

    static int optionMain;
    static String filePath = "data/data.txt";
    static String item = "";

    public static void main(String[] args) throws Exception {
        //To implement
        clearConsole();
        userInterface();
        fileWrite(item);

    }

    public static void userInterface() {
        try (Scanner scan = new Scanner(System.in)) {
            String menu = """
            Choose an option:
            1. Write a review
            2. Search for a review(s)
    
            Option: """;

            String menuOne = "Write a name for what would you like to review: ";
            String menuTwo = """
            Choose an option:
            1. Search by Tag
            2. Search by Name
            3. Search by Description
            4. List All
            5. List by Tag
    
            Option: """;

            String menuTwoFour = """
            Choose an option:
            1. ★ High -> Low
            2. ★ Low -> High
            3. Date newest first
            4. Date oldest first
            5. Tag groups
            Option: """;

            while (true) {
                clearConsole();
                System.out.print(menu);

                String input = scan.nextLine().trim();

                try {
                    optionMain = Integer.parseInt(input);
                    if (optionMain == 1 || optionMain == 2) {
                        break;
                    }
                } catch (NumberFormatException e) {
                }
            }
            if (optionMain == 1) {
                while (true) {
                    clearConsole();
                    System.out.print(menuOne);

                    String input = scan.nextLine().trim();

                    try {
                        item = input;
                        break;
                    } catch (NumberFormatException e) {
                    }
                }
            } else {
                item = null;
                while (true) {
                    clearConsole();
                    System.out.print(menuTwo);
                    String input = scan.nextLine().trim();

                    try {
                    optionMain = Integer.parseInt(input);
                    if (optionMain > 0 && optionMain < 6) {
                        break;
                    }
                    } catch (NumberFormatException e) {
                    }

                    //tbi
                }
            }

        }
    }

    public static void fileWrite(String input) {
        if (input == null) {
            return;
        }
        String cleanedInput = input.replace("[", "").replace("]", "");

        try (FileWriter writer = new FileWriter(filePath, true)) {
            writer.write("[" + cleanedInput + "]" + System.lineSeparator());
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
