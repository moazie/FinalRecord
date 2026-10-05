import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;

public class ReviewTest {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_NegativeRating_ThrowsException() {
        new Review("Alice", "2026-09-28", -0.1f, "Bad", "#tag");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_RatingExceedsTen_ThrowsException() {
        new Review("Alice", "2026-09-28", 10.1f, "Too good", "#tag");
    }

    @Test
    public void testConstructor_BoundaryRatings_Success() {
        Review minReview = new Review("Alice", "2026-09-28", 0.0f, "Min", "#tag");
        Review maxReview = new Review("Bob", "2026-09-28", 10.0f, "Max", "#tag");

        assertEquals(0.0f, minReview.getStarRating(), 0.001f);
        assertEquals(10.0f, maxReview.getStarRating(), 0.001f);
    }

    @Test
    public void testDisplayStarRating_FullStars() {

        assertEquals("★ ★ ★ ★ ★", Review.displayStarRating(10.0f));
        assertEquals("☆ ☆ ☆ ☆ ☆", Review.displayStarRating(0.0f));
    }

    @Test
    public void testDisplayStarRating_Fractions() {
        assertEquals("★ ★ ¾ ☆ ☆", Review.displayStarRating(5.5f));
        assertEquals("★ ★ ½ ☆ ☆", Review.displayStarRating(5.0f));
        assertEquals("★ ¼ ☆ ☆ ☆", Review.displayStarRating(2.5f));
        assertEquals("☆ ☆ ☆ ☆ ☆", Review.displayStarRating(0.4f));
    }

    @Test
    public void testGettersAndTags() {
        Review review = new Review("Jojo", "2026-09-28", 8.0f, "Loved it", "#Action#Anime");

        assertEquals("Jojo", review.getName());
        assertEquals("Loved it", review.getDescription());

        ArrayList<String> tags = review.getTags();
        assertNotNull(tags);
        assertTrue(tags.contains("Action"));
        assertTrue(tags.contains("Anime"));
    }

    @Test
    public void testToString_Formatting() {
        Review review = new Review("jojo", "2026-09-28", 5.5f, "Great show", "#test#cool");
        String expected = "\njojo | 2026-09-28\n★ ★ ¾ ☆ ☆\n[test] [cool]\nGreat show";

        assertEquals(expected, review.toString());
    }
}