import java.io.*;
import java.util.*;
public class b11724linkednum {
    static int n,m;
    static int[][] arr;
    static int[] visited;
    static Queue<Integer> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(a[0]);
        m = Integer.parseInt(a[1]);

        arr = new int[n+1][n+1];
        visited = new int[n+1];
        for (int i = 0; i < m; i++) {
            String[] b = br.readLine().split(" ");
            int x = Integer.parseInt(b[0]);
            int y = Integer.parseInt(b[1]);
            arr[x][y] = 1;
            arr[y][x] = 1;
        }

        int count = 0;
        for (int i = 1; i < n+1; i++) {
            if(visited[i] != 1){
                count++;
                q.add(i);
                bfs();
//                System.out.println();
            }
        }
        System.out.println(count);
    }
    public static void bfs(){
        if(!q.isEmpty()){
            int cur = q.poll();
//            if(visited[cur] != 1){
//                System.out.print(cur + " ");
//            }
            visited[cur] = 1;
            for (int i = 1; i < n+1; i++) {         // not i<m, i<n+1
                if(arr[cur][i] == 1 && visited[i] != 1){
                    q.add(i);
                }
            }
            bfs();
        }
        else{
            return;
        }
    }
}
