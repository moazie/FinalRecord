import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

public class ReviewReader {
    // final does not hold primative value so camel casing used for styling
    private final ArrayList<Review> reviewList;
    private final ReviewRepository repository;

    // new terminal user interface object
    private final TUI tui = new TUI();

    private int tabs;

    /*
     * constructor by default loads reviews from the ReviewRepository helper class
     * and stores the reviews in an arraylist of reviews unless write a review
     * is selected
     */
    public ReviewReader() {
        // if write review is selected return
        if (Client.optionMain == 1) {
            this.reviewList = new ArrayList<>();
            this.repository = null;
            return;
        }

        // initialize repository and load reviews
        this.repository = new ReviewRepository(Client.filePath);
        this.reviewList = repository.loadReviews();

        if (reviewList.isEmpty()) {
            System.out.println("There are no reviews.");
            return;
        }

        this.tabs = (reviewList.size() + 4) / 5;

        // check if deletion mode was chosen
        if (Client.isDeleteMode) {
            deleteReviewWorkflow();
        } else {
            showTabs();
        }
    }

    // Method for when user selects delete a review
    private void deleteReviewWorkflow() {
        StringBuilder prompt = new StringBuilder("========== Delete a Review ==========\n\n");

        // Print reviews
        for (int i = 0; i < reviewList.size(); i++) {
            prompt.append("[").append(i + 1).append("] ")
                    .append(reviewList.get(i).getName())
                    .append(" (").append(reviewList.get(i).getStarRating()).append(" ★ )\n");
        }

        prompt.append("\nSelect the review number to delete (0 to cancel): ");

        int choice = tui.readInteger(prompt.toString(), 0, reviewList.size(), false);

        if (choice == 0) {
            System.out.println("Deletion cancelled.");
            return;
        }

        // review deletion successful
        Review removed = reviewList.remove(choice - 1);
        repository.saveAllReviews(reviewList);
        System.out.println("Successfully deleted review: " + removed.getName());
    }

    // Method showing the text after printing reviews allowing user to switch tabs
    private void showTabs() {
        int currentPage = 1;
        while (true) {
            displayPage(currentPage);

            String prompt = "\nWhich tab would you like to go to (0 to exit review) (1-" + tabs + " tabs): ";
            int selected = tui.readInteger(prompt, 0, tabs, false);

            if (selected == 0) {
                return;
            }

            currentPage = selected;
        }
    }

    /*
     * A Review reader method that combines other methods to print
     * a user interface to view the reviews
     */
    private void displayPage(int selected) {
        Client.clearConsole();

        ArrayList<Review> filteredList = getFilteredReviews();

        tabs = Math.max(1, (filteredList.size() + 4) / 5);
        if (selected > tabs) {
            selected = tabs;
        }

        displayTabTop(selected);

        sortReviews(filteredList);

        if (filteredList.isEmpty()) {
            System.out.println("\nNo reviews found matching your search.");
        } else {
            renderTabItems(filteredList, selected);
        }

        displayTabBottom(filteredList, selected);
    }

    // Filters reviews in the ReviewList based on user option selected
    private ArrayList<Review> getFilteredReviews() {
        ArrayList<Review> filtered = new ArrayList<>();
        for (Review review : reviewList) {
            boolean matches = true;

            if (Client.nameSearch != null
                    && !review.getName().toLowerCase().contains(Client.nameSearch.toLowerCase())) {
                matches = false;
            }
            if (Client.descSearch != null
                    && !review.getDescription().toLowerCase().contains(Client.descSearch.toLowerCase())) {
                matches = false;
            }
            if (Client.tagSearch != null) {
                boolean tagMatch = false;
                for (String tag : review.getTags()) {
                    if (tag.toLowerCase().contains(Client.tagSearch.toLowerCase())) {
                        tagMatch = true;
                        break;
                    }
                }
                if (!tagMatch) {
                    matches = false;
                }
            }

            if (matches) {
                filtered.add(review);
            }
        }
        return filtered;
    }

    // uses java Collections to sort ArrayList based on user selected option
    private void sortReviews(ArrayList<Review> list) {
        // Define the exact pattern matching your date strings
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        switch (Client.sort) {
            case 1: // high to low star rating
                Collections.sort(list, (r1, r2) -> Float.compare(r2.getStarRating(), r1.getStarRating()));
                break;

            case 2: // low to high star rating
                Collections.sort(list, (r1, r2) -> Float.compare(r1.getStarRating(), r2.getStarRating()));
                break;

            case 3: // new to old (descending date)
                Collections.sort(list, (r1, r2) -> {
                    LocalDate d1 = LocalDate.parse(r1.getStringDateCreated(), formatter);
                    LocalDate d2 = LocalDate.parse(r2.getStringDateCreated(), formatter);
                    return d2.compareTo(d1);
                });
                break;

            case 4: // old to new (ascending date)
                Collections.sort(list, (r1, r2) -> {
                    LocalDate d1 = LocalDate.parse(r1.getStringDateCreated(), formatter);
                    LocalDate d2 = LocalDate.parse(r2.getStringDateCreated(), formatter);
                    return d1.compareTo(d2);
                });
                break;

            default:
                break;
        }
    }

    /*
     * following methods are for displaying
     * decorations for top and bottom of tabs
     */

    private void renderTabItems(ArrayList<Review> list, int tab) {
        int start = (tab - 1) * 5;
        int end = Math.min(start + 5, list.size());
        for (int i = start; i < end; i++) {
            System.out.println("\n" + list.get(i));
        }
    }

    private void displayTabTop(int tab) {
        System.out.println("========== Tab " + tab + " of " + tabs + " ==========");
    }

    private void displayTabBottom(ArrayList<Review> list, int tab) {
        if (list.isEmpty()) {
            System.out.println("\n================================");
            System.out.println("Showing 0 reviews");
            return;
        }
        int start = (tab - 1) * 5;
        int end = Math.min(start + 5, list.size());
        System.out.println("\n================================");
        System.out.println("Showing reviews " + (start + 1) + "-" + end + " of " + list.size());
    }
}