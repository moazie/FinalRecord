import java.util.ArrayList;
import java.util.Collections;

public class ReviewReader {
    private final ArrayList<Review> reviewList;
    private final ReviewRepository repository;
    private final TUI tui = new TUI();
    private int tabs;

    public ReviewReader() {
        if (Client.optionMain == 1) {
            this.reviewList = new ArrayList<>();
            this.repository = null;
            return;
        }

        // Initialize repository and load reviews
        this.repository = new ReviewRepository(Client.filePath);
        this.reviewList = repository.loadReviews();

        if (reviewList.isEmpty()) {
            System.out.println("There are no reviews.");
            return;
        }

        this.tabs = (reviewList.size() + 4) / 5;

        // Check if deletion mode was chosen
        if (Client.isDeleteMode) {
            deleteReviewWorkflow();
        } else {
            showTabs();
        }
    }

    private void deleteReviewWorkflow() {
        StringBuilder prompt = new StringBuilder("========== Delete a Review ==========\n\n");
        
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

        Review removed = reviewList.remove(choice - 1);
        repository.saveAllReviews(reviewList);
        System.out.println("Successfully deleted review: " + removed.getName());
    }

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

    private ArrayList<Review> getFilteredReviews() {
        ArrayList<Review> filtered = new ArrayList<>();
        for (Review review : reviewList) {
            boolean matches = true;

            if (Client.nameSearch != null && !review.getName().toLowerCase().contains(Client.nameSearch.toLowerCase())) {
                matches = false;
            }
            if (Client.descSearch != null && !review.getDescription().toLowerCase().contains(Client.descSearch.toLowerCase())) {
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

    private void sortReviews(ArrayList<Review> list) {
        switch (Client.sort) {
            case 1: // high to low
                Collections.sort(list, (r1, r2) -> Float.compare(r2.getStarRating(), r1.getStarRating()));
                break;
            case 2: // low to high
                Collections.sort(list, (r1, r2) -> Float.compare(r1.getStarRating(), r2.getStarRating()));
                break;
            case 3: // new to old
                Collections.reverse(list);
                break;
            case 4: // old to new
            default:
                break;
        }
    }

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