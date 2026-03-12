import java.io.*;
import java.util.*;
public class bitonic11054 {
    static int n;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // dp[i][j] : i에서 시작, j에서 끝나느 수열중 최대

    }
}
