import org.junit.Before;
import org.junit.Test;

public class FileIOTest {
@Before
    public void setUp() {
        Client.item = "Valid Item";
        Client.score = 8.5f;
        Client.tags = "#ValidTag";
        Client.desc = "Valid description";
    }

    @Test
    public void testValidateInput_ValidText_DoesNotThrow() {
        Client.validateInput("Good Review Text #123!");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateInput_ContainsComma_ThrowsException() {
        Client.validateInput("Invalid, Text With Comma");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFileWrite_ItemContainsComma_ThrowsException() {
        Client.item = "Movie, The Sequel";
        Client.fileWrite();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFileWrite_DescriptionContainsComma_ThrowsException() {
        Client.desc = "Great movie, highly recommended!";
        Client.fileWrite();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateInput_ContainsNewline_ThrowsException() {
        Client.validateInput("Line 1\nLine 2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidateInput_ContainsCarriageReturn_ThrowsException() {
        Client.validateInput("Line 1\rLine 2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFileWrite_DescriptionContainsNewline_ThrowsException() {
        Client.desc = "First line of review.\nSecond line of review.";
        Client.fileWrite();
    }
    
}
