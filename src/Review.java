import java.util.ArrayList;

public class Review {
    private float starRating;
    private String description;
    private String name;
    private String date;
    private String displayTag;
    private String tags;

    private Tags tagList;

    //default constructor takes all inputs required for a review
    public Review(String name, String date, float rating, String description, String tags) {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("Rating must be between 0 and 10.");
        }
        this.starRating = rating;
        this.description = description;
        this.name = name;
        this.date = date;
        this.tags = tags;

        this.tagList = new Tags(tags);

        this.displayTag = tagList.toString();
    }

    public float getStarRating() {
        return starRating;
    }

    public String getStringDateCreated() {
        return date;
    }

    public static String displayStarRating(float stars) {
        char[] chars = new char[9];
        stars = stars / 2;
        int fullStars = (int) stars;
        float fraction = stars - fullStars;

        for (int i = 0; i < 5; i++) {
            int arrayIndex = i * 2;

            if (i < fullStars) {
                chars[arrayIndex] = '★';
            } else if (i == fullStars && fraction > 0) {

                if (fraction >= 0.75f) {
                    chars[arrayIndex] = '¾';
                } else if (fraction >= 0.5f) {
                    chars[arrayIndex] = '½';
                } else if (fraction >= 0.25f) {
                    chars[arrayIndex] = '¼';
                } else {
                    chars[arrayIndex] = '☆';
                }
            } else {
                chars[arrayIndex] = '☆';
            }

            if (arrayIndex + 1 < chars.length) {
                chars[arrayIndex + 1] = ' ';
            }
        }

        return new String(chars);
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getTags() {
        Tags tagObj = new Tags(tags); // fulfills scope requirement 1
        return tagObj.getTagArray();
    }

    @Override
    public String toString() {
        return ("\n" + name + " | " + date + "\n" + displayStarRating(starRating) + "\n" + displayTag + "\n"
                + description);
    }
}
