import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    private final String filePath;

    public ReviewRepository(String filePath) {
        this.filePath = filePath;
    }

    // reads all reviews from the CSV file.
    public ArrayList<Review> loadReviews() {
        ArrayList<Review> reviewList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] sections = line.split(",", -1);
                String name = sections[0];
                float rating = Float.parseFloat(sections[1]);
                String tags = sections[2];
                String dateCreated = sections[3];
                String desc = sections.length > 4 ? sections[4] : "";

                Review currentReview = new Review(name, dateCreated, rating, desc, tags); 
                reviewList.add(currentReview);
            }
        } catch (Exception e) {
            System.out.println("Could not read CSV or file is empty.");
        }

        return reviewList;
    }

    // CSV overwriting function
    public void saveAllReviews(List<Review> reviewList) {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for (Review r : reviewList) {
                String rawTags = String.join("", r.getTags().stream().map(t -> "#" + t).toArray(String[]::new));
                writer.write(r.getName() + "," + r.getStarRating() + "," + rawTags + "," + r.getStringDateCreated()
                        + "," + r.getDescription() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Could not update file: " + e.getMessage());
        }
    }
}