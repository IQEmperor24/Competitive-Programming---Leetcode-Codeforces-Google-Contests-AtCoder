class Solution {
    /*dp[i][j] -> If substring(0, j) of string s can be represented by
    substring(0, i) of wildcard string t. Thus, dp[0][0] is always true since 
    empty string of s can be represented by empty string of t*/
    
    public boolean isMatch(String s, String p) {
        int M = s.length();
        int N = p.length();
        
        boolean[][] dp = new boolean[N + 1][M + 1];
        
        dp[0][0] = true;
        
        for (int i = 1; i <= N; i++) {
            char op = p.charAt(i - 1);
            
            if (op == '*') {
                for (int j = 0; j <= M; j++) {
                    if (dp[i - 1][j]) {
                        for (int k = j; k <= M; k++) {
                            dp[i][k] = true;
                        }
                        break;
                    }
                }
            }
            
            else {
                for (int j = 1; j <= M; j++) {
                    if (op == '?') {
                        dp[i][j] |= dp[i - 1][j - 1];
                    }
                
                    else if (op == s.charAt(j - 1)) {
                        dp[i][j] |= dp[i - 1][j - 1];
                    }       
                }
            }
            
        }
        
        return dp[N][M];
    }
}
