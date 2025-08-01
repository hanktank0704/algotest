import java.io.*;
import java.util.*;

public class g11403routefind {
    static int n;
    static Queue<Integer> q = new LinkedList<>();
    static int[][] visited;
    static int[][] arr;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
//            visited = new int[n][n];
            for (int j = 0; j < n; j++) {
                bfs(i,j);
            }
        }

        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(visited[i][j] + " ");
            }
            System.out.println();
        }

    }
    public static void bfs(int a, int b){
        int cnt=0;
        q.add(a);
        while(!q.isEmpty()){
            int cur = q.poll();
            for (int i = 0; i < n; i++) {
                if(arr[cur][i] == 1 && visited[cur][i] != 1){
                    q.add(i);
                    visited[cur][i] = 1;
                }
            }
        }
    }
}

// 0-1, 1-2, 2-0
//3
//        0 1 0
//        0 0 1
//        1 0 0