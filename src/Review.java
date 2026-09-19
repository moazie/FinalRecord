


public class Review {
    private float starRating;
    private String description;
    private String name;
    private String date;
    private String tags;

    Tags tagList = new Tags(tags);

    public Review(String name, String date, int starRating, String description, String tags) {
        if (starRating < 1 || starRating > 10) {
            throw new IllegalArgumentException("Rating must be between 1 and 10.");
        }
        this.starRating = starRating;
        this.description = description;
        this.name = name;
        this.date = date;
        this.tags = tags;
    }

    public float getStarRating() {
        return starRating;
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

    @Override
    public String toString() {
        return (name + " | " + date + "\n" + displayStarRating(starRating) + "\n\n" + tagList.toString() + "\n\n" + description);
    }
}
