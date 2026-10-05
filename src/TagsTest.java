import static org.junit.Assert.*;
import org.junit.Test;

public class TagsTest {

    @Test
    public void testGetTag_OutOfBoundsAndInvalidIndex() {
        Tags tags = new Tags("#Books#Movies#Shows");

        assertEquals("Books", tags.getTag(0));
        assertEquals("Movies", tags.getTag(1));
        assertEquals("Shows", tags.getTag(2));
        
        // Out of bounds
        assertEquals("", tags.getTag(-1));
        assertEquals("", tags.getTag(3));
        assertEquals("", tags.getTag(1000));
    }

    @Test
    public void testToString_StandardTags() {
        Tags tags = new Tags("#Books#Movies#Shows");
        assertEquals("[Books] [Movies] [Shows]", tags.toString());
    }

    @Test
    public void testParsing_IgnoreTextBeforeFirstHash() {
        Tags tags = new Tags("IgnoredText#ValidTag#AnotherTag");
        assertEquals("[ValidTag] [AnotherTag]", tags.toString());
    }

    @Test
    public void testParsing_ConsecutiveHashesAndTrailingHashes() {
        Tags tags = new Tags("###Books###");
        assertEquals("[Books]", tags.toString());
        assertEquals("Books", tags.getTag(0));
        assertEquals("", tags.getTag(1)); // Ensures empty tags aren't indexed
    }

    @Test
    public void testParsing_PunctuationAndWhitespace() {
        Tags tags = new Tags("#  Books  #Movies ");
        // Ensure leading/trailing spaces handle predictably
        assertEquals("Books", tags.getTag(0));
    }

    @Test
    public void testNullAndEmptyInputs() {
        Tags nullTags = new Tags(null);
        assertEquals("", nullTags.getTag(0));
        assertEquals("", nullTags.toString());

        Tags emptyTags = new Tags("");
        assertEquals("", emptyTags.getTag(0));
        assertEquals("", emptyTags.toString());

        Tags hashOnly = new Tags("#");
        assertEquals("", hashOnly.getTag(0));
        assertEquals("", hashOnly.toString());
    }
}