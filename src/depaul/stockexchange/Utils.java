package depaul.stockexchange;

/**
 *
 * @author      Xin Guo
 * @author      Yuancheng Zhang
 * @author      Junmin Liu
 */

public class Utils {

    /**
     * Check if the string is null or empty
     * @param str
     *      The string passed in to check
     * @return 
     *      If string is null or empty, return true
     *      Otherwise return false
     */
    public static Boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}