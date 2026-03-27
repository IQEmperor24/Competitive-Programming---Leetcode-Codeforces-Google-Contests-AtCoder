class Solution {
    /*dp[i][j] -> minimum largest sum among j subarrays split using the first i elements of array a
    
    
    */
    
    public int splitArray(int[] a, int M) {
        int N = a.length;
        
        int[][] dp = new int[N + 1][M + 1];
        
        for (int i = 1; i <= N; i++) {
            dp[i][1] = dp[i - 1][1] + a[i - 1];
        }
        
        for (int m = 2; m <= M; m++) {
            for (int i = m; i <= N; i++) {
                dp[i][m] = Integer.MAX_VALUE;
                
                
                for (int j = m - 1; j < i; j++) {
                    dp[i][m] = min(dp[i][m], max(dp[j][m - 1], dp[i][1] - dp[j][1]));
                }
            }
        }
        
        return dp[N][M];
    }
    
    public static int min(int a, int b) {
        return a < b? a : b;
    }
    
    public static int max(int a, int b) {
        return a > b? a : b;
    }
}
