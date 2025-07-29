import java.io.*;
import java.util.*;

public class a1260dfsbfs {
    static int n, m, v;
    static int[][] arr;
    static int[] visited;
    static Queue<Integer> q = new LinkedList<>();
    static Stack<Integer> s = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(a[0]);
        m = Integer.parseInt(a[1]);
        v = Integer.parseInt(a[2]);

        arr = new int[n+1][n+1];
        for (int i = 0; i < m; i++) {
            String[] b = br.readLine().split(" ");
            int x = Integer.parseInt(b[0]);
            int y = Integer.parseInt(b[1]);

            arr[x][y] = 1;
            arr[y][x] = 1;
        }
        //TODO sorting the input thingy
        //dfs
        s.add(v);
        visited = new int[n+1];
//        visited[v] = 1;
//        System.out.print(v + " ");

        while(!s.isEmpty()){
//            System.out.print("stack: ");
//            for(int val: s){
//                System.out.print(val + " ");
//            }
//            System.out.println();

            int cur = s.pop();
            if(visited[cur]!=1){
                System.out.print(cur + " ");
            }
            visited[cur] = 1;
            for (int i = n; i >= 1; i--) {
                if(arr[cur][i] == 1 && visited[i] != 1){
                    s.add(i);
                }
            }
        }
        System.out.println();

        //bfs
        q.add(v);
        visited = new int[n+1];
//        visited[v] = 1;
//        System.out.print(v + " ");
        while(!q.isEmpty()){
            int cur = q.poll();
            if(visited[cur]!=1){
                System.out.print(cur + " ");
            }
            visited[cur] = 1;
            for (int i = 0; i < n+1; i++) {
                if(arr[cur][i] == 1 && visited[i] != 1){
                    q.add(i);
//                    visited[i] = 1;
                }
            }
        }

    }
}
