package util;

import java.util.Date;

/**
 * Created by Fiser on 22/12/16.
 */
public class Util {
    public static int daysBetween(Date startDate, Date endDate) {
        long startTime = startDate.getTime();
        long endTime = endDate.getTime();
        long diffTime = endTime - startTime;
        return (int) (diffTime / (1000 * 60 * 60 * 24));
    }

}
