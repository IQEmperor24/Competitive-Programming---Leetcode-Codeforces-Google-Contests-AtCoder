class Solution {
    public boolean canCross(int[] a) {
        int N = a.length;
        TreeSet<Integer>[] dp = new TreeSet[N + 1];
        
        for (int i = 0; i <= N; i++) {
            dp[i] = new TreeSet<Integer>();
        }
        
        if (a[1] == 1) {
            dp[1].add(1); // We have a 1 unit jump as the initial condition
        }
        
        for (int i = 1; i < N - 1; i++) {
            while (!dp[i].isEmpty()) {
                int jump = dp[i].pollFirst();
                
                for (int j = i + 1; j < N; j++) {
                    
                    if (a[j] == a[i] + jump - 1) {
                        dp[j].add(jump - 1);
                    }
                    
                    else if (a[j] == a[i] + jump + 1) {
                        dp[j].add(jump + 1);
                    }
                    
                    else if (a[j] == a[i] + jump) {
                        dp[j].add(jump);
                    } 
                }
            }
        }
        
        return dp[N - 1].size() > 0;
    }
}
