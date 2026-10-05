import java.util.ArrayList;

public class Tags {

    private final ArrayList<String> tagArray = new ArrayList<>();

    // constructor by default formats the tags from the data raw file
    // then inserts each tag in the above arraylist
    public Tags(String tags) {
        if (tags != null) {
            //check for # in first tag
            int firstHashIndex = tags.indexOf("#");
            if (firstHashIndex != -1) {
                String validTagsString = tags.substring(firstHashIndex);
                String[] parts = validTagsString.split("#");
                for (String part : parts) {
                    String cleaned = part.replace(" ", "").trim();
                    if (!cleaned.isEmpty()) {
                        tagArray.add(cleaned);
                    }
                }
            }
        }
    }

    //returns the tags in an arraylist of String
    public ArrayList<String> getTagArray() {
        return tagArray;
    }

    // Get a tag at index
    public String getTag(int idx) {
        if (idx < 0 || idx >= tagArray.size()) {
            return "";
        }
        return tagArray.get(idx);
    }

    //prints tags in this format [Tag 1] [Tag 2]
    @Override
    public String toString() {
        if (tagArray.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tagArray.size(); i++) {
            if (i > 0) {
                sb.append(" ");
            }
            sb.append("[").append(tagArray.get(i)).append("]");
        }
        return sb.toString();
    }
}