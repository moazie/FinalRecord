import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ReviewReader {
    ArrayList<Review> reviewList = new ArrayList<>();
    ArrayList<String> tagList = new ArrayList<>();

    private int count;
    private int tabs;
    private String line;

    private final Scanner scan = new Scanner(System.in);

    public ReviewReader() {

        if (Client.optionMain == 1) {
            return;
        }

        // Count how many reviews are in the file
        try (BufferedReader reader = new BufferedReader(new FileReader(Client.filePath))) {

            while ((line = reader.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    count++;
                }
            }

        } catch (Exception e) {
            System.out.println("Could not count reviews");
            return;
        }

        // 5 reviews maximum per tab
        tabs = (count + 4) / 5;

        // No reviews
        if (tabs == 0) {
            System.out.println("There are no reviews.");
            return;
        }

        // Read reviews from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(Client.filePath))) {

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] sections = line.split(",", -1);

                String name = sections[0];
                float rating = Float.parseFloat(sections[1]);
                String tags = sections[2];
                String dateCreated = sections[3];
                String desc = "";
                if (sections.length > 4) {
                    desc = sections[4];
                }

                Tags tagObj = new Tags(tags);

                for (String tag : tagObj.tagArray) {
                    tagList.add(tag);
                }

                Review currentReview = new Review(name, dateCreated, rating, desc, tags);

                reviewList.add(currentReview);

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Could not read CSV");
            return;
        }

        showTabs();
    }

    private void showTabs() {

        // 1. DISPLAY TAB 1 BY DEFAULT BEFORE WAITING FOR USER INPUT
        displayPage(1);

        while (true) {

            System.out.println();
            System.out.print(
                    "Which tab would you like to go to "
                            + "(0 to exit review) (1-" + tabs + " tabs): ");

            String input = scan.nextLine().trim();

            int selected;

            try {
                selected = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                continue;
            }

            // 0 exits
            if (selected == 0) {
                return;
            }

            // Ignore invalid tab numbers
            if (selected < 1 || selected > tabs) {
                continue;
            }

            // 2. DISPLAY SELECTED TAB ON EACH USER INPUT
            displayPage(selected);
        }

    }

    /**
     * Renders a specific tab/page of reviews to the console.
     */
    private void displayPage(int selected) {
        Client.clearConsole();

        displayTabTop(selected);

        switch (Client.sort) {
            // high to low
            case 1:
                Collections.sort(reviewList, (r1, r2) -> {
                    return Float.compare(r1.getStarRating(), r2.getStarRating());
                });
                Collections.reverse(reviewList);
                oldToNew(selected);
                break;
            // low to high
            case 2:
                Collections.sort(reviewList, (r1, r2) -> {
                    return Float.compare(r1.getStarRating(), r2.getStarRating());
                });
                oldToNew(selected);
                break;

            // new to old
            case 3:
                newToOld(selected);
                break;

            // old to new
            case 4:
                oldToNew(selected);
                break;

            // tag sort
            case 5:
                Collections.sort(reviewList, (r1, r2) -> {
                    int matches = 0;
                    for (String tag : r1.getTags()) {
                        if (r2.getTags().contains(tag)) {
                            matches++;
                        }
                    }
                    if (matches > 0) {
                        return -matches;
                    }
                    return r1.getTags().toString().compareTo(r2.getTags().toString());
                });

                oldToNew(selected);
                break;
            default:
                break;
        }

        if (Client.nameSearch != null) {
            for (Review review : reviewList) {
                if (review.getName().toLowerCase().contains(Client.nameSearch.toLowerCase())) {
                    System.out.println(review.toString());
                }
            }
        }

        if (Client.descSearch != null) {
            for (Review review : reviewList) {
                if (review.getDescription().toLowerCase().contains(Client.descSearch.toLowerCase())) {
                    System.out.println(review.toString());
                }
            }
        }

        if (Client.tagSearch != null) {
            for (Review review : reviewList) {
                for (String tag : review.getTags()) {
                    if (tag.toLowerCase().contains(Client.tagSearch.toLowerCase())) {
                        System.out.println(review.toString());
                    }
                }
            }
        }

        displayTabBottom(selected);
    }

    private void displayTabTop(int tab) {

        System.out.println(
                "========== Tab " + tab + " of " + tabs + " ==========");

    }

    private void displayTabBottom(int tab) {
        int start = (tab - 1) * 5;
        int end = Math.min(start + 5, reviewList.size());
        System.out.println();
        System.out.println("================================");

        System.out.println(
                "Showing reviews " + (start + 1)
                        + "-" + end
                        + " of " + reviewList.size());
    }

    private void oldToNew(int tab) {
        int start = (tab - 1) * 5;
        int end = Math.min(start + 5, reviewList.size());
        for (int i = start; i < end; i++) {
            System.out.println();
            System.out.println(reviewList.get(i));
        }
    }

    private void newToOld(int tab) {
        int start = (tab - 1) * 5;
        int end = Math.min(start + 5, reviewList.size());

        for (int i = end - 1; i >= start; i--) {
            System.out.println();
            System.out.println(reviewList.get(i));
        }
    }

}