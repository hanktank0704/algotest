import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class a15654nandm5 {
    static int n,m;
    static int[] arr;
    static int[] visited;
    static int[] ans;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(a[0]);
        m = Integer.parseInt(a[1]);

        arr = new int[n];
        visited = new int[n];
        ans = new int[m];

        String[] b = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(b[i]);
        }

//        System.out.println(Arrays.toString(arr));
        Arrays.sort(arr);
        recur(arr, visited, m);


    }
    public static void recur(int[] arr, int[] visited, int stage){
        // 1 7 8 9
        if(stage == 0){
            for (int i = 0; i < ans.length; i++) {
                System.out.print(ans[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < arr.length; i++) {
            if(visited[i] == 1){
                continue;
            }
            ans[m-stage] = arr[i];
//            System.out.print(arr[i]);
            visited[i] = 1;
            recur(arr, visited, stage-1);
            visited[i] = 0;
//            System.out.println();
        }
    }
}
// 2 4 5
// 4 5 -> 5 ->

// 2 4 5
//
