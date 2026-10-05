
import java.util.ArrayList;

public class Tags {

    ArrayList<String> tagArray = new ArrayList<>();

    public Tags(String tags) {
        StringBuilder sb = new StringBuilder();
        if (tags != null) {

            for (int i = 0; i < tags.length(); i++) {
                char currentChar = tags.charAt(i);

                if (currentChar == '#') {
                    if (sb.length() > 0) {
                        tagArray.add(sb.toString().trim());
                        sb.setLength(0);
                    }
                } else {
                    if (i > tags.indexOf("#")) {
                        sb.append(currentChar);
                    }
                }
            }

            if (sb.length() > 0) {
                tagArray.add(sb.toString().trim());
            }
        }

    }

    public String getTag(int idx) {
        if (tagArray.isEmpty()) {
            return "";
        } else if (idx > tagArray.size() - 1 || idx < 0) {
            return "";
        }
        return tagArray.get(idx);
    }

    public ArrayList<String> removeCommas(ArrayList<String> a) {
        ArrayList<String> tagArray = new ArrayList<>();
        for (String tag : a) {
            if (tag != null) {
                tagArray.add(tag.replace(",", ""));
            } else {
                tagArray.add("");
            }
        }
        return tagArray;
    }

    @Override
    public String toString() {
        if (tagArray == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.setLength(0);
        int count = 0;

        for (String item : removeCommas(tagArray)) {

            if (count >= 1) {
                sb.append(" ");
            }
            sb.append("[" + item + "]");
            count++;
        }
        return sb.toString();
    }
}
