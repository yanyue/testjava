package leetcode;

public class FloatValueGet {
    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    public static double atof(String s) {
        int i = 0;
        double res = 0;
        double rate = 1;
        while (s.charAt(i) == ' ') {
            ++i;
        }

        int sign = 1;
        if (s.charAt(i) == '+') {
            ++i;
        } else if (s.charAt(i) == '-') {
            sign = -1;
            ++i;
        }

        boolean isDot = false;
        for (; i < s.length(); ++i) {
            if (s.charAt(i) == '.') {
                isDot = true;
                continue;
            }

            if (!isDot) {
                res = res * 10 + (s.charAt(i) - '0');
            } else {
                res = res + rate / 10 * (s.charAt(i) - '0');
                rate /= 10;
            }
        }

        return  res * sign;
    }
}
