package willy.individual.com.minilinkedinapp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by zhenglu on 2/18/17.
 */

public class DateUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy", Locale.getDefault());

    public static String dateToString (Date date) {
        return sdf.format(date);
    }

    public static Date stringToDate (String date) {
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date(0);
        }
    }
}
