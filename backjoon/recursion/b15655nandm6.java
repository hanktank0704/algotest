import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class b15655nandm6 {
    static int n,m;
    static int[] arr;
    static int[] visited;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(a[0]);
        m = Integer.parseInt(a[1]);
        String[] b = br.readLine().split(" ");
        arr = new int[n];
        visited = new int[n];
        ans = new int[m];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(b[i]);
        }
        Arrays.sort(arr);
//        System.out.println(Arrays.toString(arr));
        recur(0, 0);
    }

    public static void recur(int stage, int start){
        if(stage == m){
            for (int i = 0; i < m; i++) {
                System.out.print(ans[i] + " ");
            }
            System.out.println();
            return;
        }
        for (int i = start; i < n; i++) {
            if(visited[i] == 1)
                continue;

            ans[stage] = arr[i];
            visited[i]=1;
            recur(stage + 1, i+1);
            visited[i]=0;
        }
    }
}
