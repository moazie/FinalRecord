import java.util.Scanner;

public class TUI {

    private final Scanner scan = new Scanner(System.in);

    public TUI() {
    }

    public String Text(String text) {
        while (true) {
            Client.clearConsole();
            System.out.print(text);
            String input = scan.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
        }
    }

    public String TextDesc(String text) {
        while (true) {
            Client.clearConsole();
            System.out.print(text);
            String input = scan.nextLine().trim();
            return input;
        }
    }

    public Boolean Bool(String text, Boolean def) {
        while (true) {
            Client.clearConsole();
            System.out.print(text);
            String input = scan.nextLine().trim();
            if (input.isEmpty()) {
                return def;
            } else if (input.matches("y") || input.matches("Y")) {
                return true;
            } else if (input.matches("n") || input.matches("N")) {
                return false;
            }
        }
    }

    public int Integer(String text, int lowerBound, int upperBound) {
        while (true) {
            Client.clearConsole();
            System.out.print(text);
            String input = scan.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= lowerBound && value <= upperBound) {
                    return value;
                }
            } catch (NumberFormatException e) {
            }
        }
    }

    public float Float(String text, float lowerBound, float upperBound) {
        while (true) {
            Client.clearConsole();
            System.out.print(text);
            String input = scan.nextLine().trim();
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
