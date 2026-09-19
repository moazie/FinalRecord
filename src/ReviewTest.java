import static org.junit.Assert.*;
import org.junit.Test;

public class ReviewTest {

    @Test

    public void returnToString() {
        Review review = new Review("jojo", "2026-09-28", 5.5f, "null", "#test#cool");
        assertEquals("\njojo | 2026-09-28\n★ ★ ¾ ☆ ☆\n[test] [cool]\nnull", review.toString());
    }
}
