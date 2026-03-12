import java.io.*;
import java.util.*;
public class coin22294 {
    static int n,k;
    static int[] coin;
    static int[] ans;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        ans = new int[k+1];
        coin = new int[n];
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
        }
        for (int i = 0; i <= k; i++) {
            ans[i] = Integer.MAX_VALUE -1;
        }

        ans[0] = 0;
        for (int i = 0; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                if(i+coin[j] <= k && ans[i+coin[j]] > ans[i] + 1 ){
                    ans[i + coin[j]] = ans[i] + 1;
                }
            }
        }
//        for (int val : ans) {
//            System.out.print(val + " ");
//        }
//        System.out.println();
        if(ans[k] == Integer.MAX_VALUE - 1)
            System.out.println(-1);
        else
            System.out.println(ans[k]);
    }
}
