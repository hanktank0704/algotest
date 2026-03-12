import java.io.*;
import java.util.*;
public class normalbag12865 {
    static int n,k;
    static int[] weight;
    static int[] value;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        weight = new int[n+1];
        value = new int[n+1];
        dp = new int[n+1][k+1];
        //what mulgun, weight


        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            weight[i] = Integer.parseInt(st.nextToken());
            value[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j] = dp[i-1][j];
                if(j >= weight[i]){
                    // value of i th mulgun, j weight
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j - weight[i]] + value[i]);
                }
            }
        }
//        for (int i = 0; i <= n; i++) {
//            for (int j = 0; j <= k; j++) {
//                System.out.print(dp[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(dp[n][k]);

    }
}
