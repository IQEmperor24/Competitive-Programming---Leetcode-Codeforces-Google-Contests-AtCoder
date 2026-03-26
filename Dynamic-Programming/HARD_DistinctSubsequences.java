class Solution {
    
    /*dp[i][j] -> Number of subsequences of t.substring(0, j) in string s up to and including
    index i. Thus, t.substring(0, 0) -> empty string -> always has one way (pick nothing)*/
    public int numDistinct(String s, String t) {
        int N = s.length();
        int M = t.length();
        
        int[][] dp = new int[N + 1][M + 1];
        
        for (int i = 0; i <= N; i++) {
            dp[i][0] = 1;
        }
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                dp[i][j] = dp[i - 1][j];
                
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
            }
        }
        
        return dp[N][M];
    }
}
