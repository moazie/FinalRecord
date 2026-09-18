
public class Review {
    private float starRating;
    private String description;

    public Review(int starRating, String description) {
        if (starRating < 1 || starRating > 10) {
            throw new IllegalArgumentException("Rating must be between 1 and 10.");
        }
        this.starRating = starRating;
        this.description = description;
    }

    public float getStarRating() {
        return starRating;
    }

    public static String displayStarRating(float stars) {
        char[] chars = new char[9]; // 5 stars + 4 spaces
        stars = stars / 2;
        int fullStars = (int) stars;
        float fraction = stars - fullStars;

        for (int i = 0; i < 5; i++) {
            int arrayIndex = i * 2; // Positions 0, 2, 4, 6, 8

            if (i < fullStars) {
                chars[arrayIndex] = '★'; // Full star
            } else if (i == fullStars && fraction > 0) {
                // Assign partial star based on quarter boundaries
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
                chars[arrayIndex] = '☆'; // Empty star
            }

            // Add trailing space (except after index 8)
            if (arrayIndex + 1 < chars.length) {
                chars[arrayIndex + 1] = ' ';
            }
        }

        return new String(chars);
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return displayStarRating(starRating) + description;
    }
}
