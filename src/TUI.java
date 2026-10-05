import java.util.Scanner;

public class TUI {

    private final Scanner SCAN = new Scanner(System.in);

    public TUI() {
    }

    // Default method (clears screen) for backward compatibility with Client
    public int readInteger(String text, int lowerBound, int upperBound) {
        return readInteger(text, lowerBound, upperBound, true);
    }

    // Overloaded method allowing caller to control screen clearing
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

    // Default text reader (clears screen)
    public String readText(String text) {
        return readText(text, true);
    }

    // Overloaded text reader
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

    public String readTextDesc(String text) {
        System.out.print(text);
        return SCAN.nextLine().trim();
    }

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
}