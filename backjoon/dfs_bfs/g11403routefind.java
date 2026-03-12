import java.io.*;
import java.util.*;

public class g11403routefind {
    static int n;
    static Queue<Integer> q = new LinkedList<>();
    static int[][] adj;
    static int[][] reach;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj= new int[n][n];
        reach= new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                adj[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < n; i++) {
            int[] visited = new int[n];
            Queue<Integer> q = new LinkedList<>();

            for (int j = 0; j < n; j++) {
                if(adj[i][j] == 1){
                    q.add(j);
                    reach[i][j] = 1;
                    visited[j] = 1;
                }
            }

            while(!q.isEmpty()){
                int u = q.poll();
                for (int j = 0; j < n; j++) {
                    if(adj[u][j] == 1 && visited[j] != 1){
                        visited[j] =1;
                        reach[i][j] = 1;
                        q.add(j);
                    }
                }
            }

        }
        printarr(reach);


    }
    public static void bfs(int a){
    }
    public static void printarr(int[][] arr){
//        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
// 0-1, 1-2, 2-0
//3
//        0 1 0
//        0 0 1
//        1 0 0