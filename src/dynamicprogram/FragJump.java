package dynamicprogram;

/**
 * dp[n] = dp[n-1] + dp[n-2]
 *
 * dp[0] = 0;
 * dp[1] = 1;
 * dp[2] = 2;
 */
public class FragJump {
    public static int resolve(int targetCount) {
        int dp[] = new int[targetCount + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= targetCount; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[targetCount];
    }
}
