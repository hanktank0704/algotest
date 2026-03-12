import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class c15656nandm7 {
    static int n,m;
    static int[] arr, visited, ans;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(a[0]);
        m = Integer.parseInt(a[1]);
        String[] b = br.readLine().split(" ");
        arr = new int[n];
        ans = new int[m];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(b[i]);
        }

        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
        recur(0);
        System.out.print(sb);
    }
    public static void recur(int stage){
        if(stage == m){
            for (int val : ans) {
                sb.append(val);
                sb.append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = 0; i < n; i++) {
            ans[stage] = arr[i];
//            sb.append(arr[i]);
//            sb.append(' ');
            recur(stage+1);
        }

    }
}
