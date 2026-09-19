
import java.util.ArrayList;

public class Tags {

    ArrayList<String> tagArray = new ArrayList<>();

    public Tags(String tags) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < tags.length(); i++) {
            char currentChar = tags.charAt(i);

            if (currentChar == '#') {
                if (sb.length() > 0) {
                    tagArray.add(sb.toString().trim());
                    sb.setLength(0);
                }
            } else {
                sb.append(currentChar);
            }
        }

        if (sb.length() > 0) {
            tagArray.add(sb.toString().trim());
        }
    }

    public String getTag(int idx) {
        return tagArray.get(idx);
    }

    public String toDisplay(String tags) {
        return "#";

    }

    @Override
    public String toString() {
        return "#";
    }
}
