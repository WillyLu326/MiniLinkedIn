package willy.individual.com.minilinkedinapp.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenglu on 2/21/17.
 */

public class ToolUtils {

    public static List<String> convertStringToList(String courses) {
        String[] strs = courses.split("\\n");
        List<String> result = new ArrayList<>();
        for (String str : strs) {
            result.add(str);
        }
        return result;
    }

    public static String convertListToString(List<String> courses) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < courses.size(); ++i) {
            if (i == courses.size() - 1) {
                sb.append(courses.get(i));
            } else {
                sb.append(courses.get(i) + "\n");
            }
        }
        return sb.toString();
    }
}
