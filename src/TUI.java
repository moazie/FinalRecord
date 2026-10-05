import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class TUI {

    private final Scanner SCAN = new Scanner(System.in);

    public TUI() {
    }

    // default method (clears screen)
    public int readInteger(String text, int lowerBound, int upperBound) {
        return readInteger(text, lowerBound, upperBound, true);
    }

    // overloaded method allowing caller to control screen clearing
    public int readInteger(String text, int lowerBound, int upperBound, boolean clearScreen) {
        while (true) {
            if (clearScreen) {
                Client.clearConsole();
            }
            System.out.print(text);
            String input = SCAN.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= lowerBound && value <= upperBound) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
        }
    }

    // default text reader (clears screen)
    public String readText(String text) {
        return readText(text, true);
    }

    // overloaded text reader
    public String readText(String text, boolean clearScreen) {
        while (true) {
            if (clearScreen) {
                Client.clearConsole();
            }
            System.out.print(text);
            String input = SCAN.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
        }
    }

    //only for read text description (doesnt check empty)
    public String readTextDesc(String text) {
        System.out.print(text);
        return SCAN.nextLine().trim();
    }

    //read for yes (y) or no (n)
    public Boolean readBool(String text, Boolean def) {
        while (true) {
            Client.clearConsole();
            System.out.print(text);
            String input = SCAN.nextLine().trim();
            if (input.isEmpty()) {
                return def;
            } else if (input.equalsIgnoreCase("y")) {
                return true;
            } else if (input.equalsIgnoreCase("n")) {
                return false;
            }
        }
    }

    //read for float
    public float readFloat(String text, float lowerBound, float upperBound) {
        while (true) {
            Client.clearConsole();
            System.out.print(text);
            String input = SCAN.nextLine().trim();
            try {
                float value = Float.parseFloat(input);
                if (value >= lowerBound && value <= upperBound) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
        }
    }

    //read for date 
    public LocalDate readDate(String text) {
        LocalDate defaultDate = LocalDate.now();
        while (true) {
            Client.clearConsole();
            System.out.print(text + " [Default: " + defaultDate + "]: ");
            String input = SCAN.nextLine().trim();

            if (input.isEmpty()) {
                return defaultDate;
            }

            if (input.startsWith("0000")) {
            System.out.println("Year 0000 is invalid in AD calendar. Please use year 0001 or later.");
            System.out.print("Press Enter to try again...");
            SCAN.nextLine();
            continue;
            }

            try {
                return LocalDate.parse(input); // Validates YYYY-MM-DD format
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format! Please enter a valid date as YYYY-MM-DD.");
                System.out.print("Press Enter to try again...");
                SCAN.nextLine();
            }
        }
    }
}