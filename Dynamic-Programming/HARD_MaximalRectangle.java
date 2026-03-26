class Solution {
    /*Sketchy case:
    00000001
    00000001
    00000001
    00000001
    00000011
    11111111
    00000011 
    
    */
    
    
    public int maximalRectangle(char[][] a) {
        if (a.length == 0 || a[0].length == 0) {
            return 0;
        }
        
        int N = a.length;
        int M = a[0].length;
        int ans = 0;
        
        int[] dp = new int[M + 1];
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (a[i - 1][j - 1] == '1') {
                    dp[j] += 1;
                }
                
                else {
                    dp[j] = 0;
                }
                
            }
            
            for (int j = 1; j <= M; j++) {
                if (a[i - 1][j - 1] == '1') {
                    int h = Integer.MAX_VALUE;
                    for (int k = j; k >= 1; k--) {
                        h = min(h, dp[k]);
                        
                        ans = max(ans, h * (j - k + 1));
                    }
                }
            }
        }
        
        return ans;
    }
                                          
    public static int max(int a, int b) {
        return a > b? a : b;
    }
    
    public static int min(int a, int b) {
        return a < b? a : b;
    }
}
