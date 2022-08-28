package leetcode;

public class IpAddressValidate {
    private static boolean parse(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        s = s + ".";
        int dotCount = 0;
        boolean spanStart = true;
        StringBuffer spanValue = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                spanValue.append(s.charAt(i));
            } else {
                if (s.charAt(i) == '.') {
                    if (spanStart) {
                        return false;
                    } else {
                        dotCount++;
                        spanStart = true;
                        if ((spanValue.length() == 3 && Integer.parseInt(spanValue.toString()) > 255) || spanValue.length() > 3) {
                            return false;
                        }
                        spanValue.setLength(0);
                        continue;
                    }
                } else {
                    return false;
                }
            }

            spanStart = false;
        }

        if (dotCount == 4) {
            return  true;
        } else {
            return false;
        }
    }
    public static void test() {
//        String ip = "192.168.0.1";
        String ip = "311.0.0.1";
        System.out.println("ip :" + ip + " result:" + parse(ip));
    }
}
