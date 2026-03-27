class Solution {
    static int[] dp;
    static boolean[] vis;
    static int[] in;
    static ArrayList<Integer>[] g;
    public int longestIncreasingPath(int[][] a) {
        g = new ArrayList[40001];
        dp = new int[40001];
        
        vis = new boolean[40001];
        in = new int[40001];
        
        int N = a.length;
        int M = a[0].length;
        
        for (int i = 0; i <= 40000; i++) {
            g[i] = new ArrayList<Integer>();
        }
        
        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    if (i + dy[k] < N && i + dy[k] >= 0 
                        && j + dx[k] >= 0 && j + dx[k] < M) {
                        if (a[i + dy[k]][j + dx[k]] > a[i][j]) {
                            g[i * M + j].add((i + dy[k]) * M + j + dx[k]);
                            in[(i + dy[k]) * M + j + dx[k]]++;
                        }
                    }   
                }
            }
        }
        
        Arrays.fill(dp, 1);
        for (int v = 0; v <= 40000; v++) {
            if (in[v] == 0 && !vis[v]) {
                vis[v] = true;
                DFS(v);
            }
        }
        
        int ans = 0;
        for (int v = 0; v <= 40000; v++) {
            ans = Math.max(ans, dp[v]);
        }
        
        return ans;
    }
    
    public void DFS(int v) {
        //vis[v] = true;
        
        for (int i = 0; i < g[v].size(); i++) {
            int nbr = g[v].get(i);
            
            if (!vis[nbr]) {
                in[nbr]--;
                dp[nbr] = Math.max(dp[nbr], dp[v] + 1);
                
                if (in[nbr] == 0) {
                    vis[nbr] = true;
                    DFS(nbr);
                }
            }
        }
    }
}
