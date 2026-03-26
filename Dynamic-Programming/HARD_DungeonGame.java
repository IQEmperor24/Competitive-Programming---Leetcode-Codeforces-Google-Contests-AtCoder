class Solution {
    static boolean[][] alive;
    static long[][] dp;
    static int[][] m;
    static int N;
    static int M;
    
    public int calculateMinimumHP(int[][] a) {
        N = a.length;
        M = a[0].length;
        
        m = new int[N + 1][M + 1];
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                m[i][j] = a[i - 1][j - 1];
            }
        }
        
        long lo = 1;
        long hi = (long) (400 * 10000);
        long ans = hi;
        
        while (lo <= hi) {
            long mid = (lo + hi) / 2;
            
            dp = new long[N + 1][M + 1];
            alive = new boolean[N + 1][M + 1];
            
            if (solve(mid)) {
                ans = min(ans, mid);
                hi = mid - 1;
            }
            
            else {
                lo = mid + 1;
            }
        }
        
        return (int) ans;
    }
    
    public static boolean solve(long mid) {
        dp[1][1] = mid + m[1][1];
        
        if (dp[1][1] > 0) {
            alive[1][1] = true;
        }
        
        for (int i = 2; i <= N; i++) {
            if (alive[i - 1][1]) {
                dp[i][1] = dp[i - 1][1] + m[i][1];
                alive[i][1] = (dp[i][1] > 0);
            }
        }
        
        for (int i = 2; i <= M; i++) {
            if (alive[1][i - 1]) {
                dp[1][i] = dp[1][i - 1] + m[1][i];
                alive[1][i] = dp[1][i] > 0;    
            }
        }
        
        for (int i = 2; i <= N; i++) {
            for (int j = 2; j <= M; j++) {
                long prev_health = 0;
                
                if (alive[i][j - 1]) {
                    prev_health = max(prev_health, dp[i][j - 1]);
                }
                
                if (alive[i - 1][j]) {
                    prev_health = max(prev_health, dp[i - 1][j]);
                }
                
                if (prev_health == 0) {
                    alive[i][j] = false;
                    continue;
                }
                
                prev_health += m[i][j];
                
                if (prev_health <= 0) {
                    alive[i][j] = false;
                    continue;
                }
                
                dp[i][j] = prev_health;
                alive[i][j] = true;
            }
        }
        
        return alive[N][M];
    }
    
    public static long min(long a, long b) {
        return a < b? a : b;
    }
    
    public static long max(long a, long b) {
        return a > b? a : b;
    }
    
}
