import java.io.*;
import java.util.*;
public class b11724linkednum {
    static int n,m;
    static int[][] arr;
    static int[] visited;
    static Queue<Integer> q = new LinkedList<>();
    static List<Integer>[] l;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
//        String[] a = br.readLine().split(" ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

//        arr = new int[n+1][n+1];

        visited = new int[n+1];
        l = new ArrayList[n+1];
        for (int i = 0; i < n+1; i++) {
            l[i] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
//            String[] b = br.readLine().split(" ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
//            arr[x][y] = 1;
//            arr[y][x] = 1;
//            l[x] = new ArrayList<>();
            l[x].add(y);
            l[y].add(x);
        }

        int count = 0;
        for (int i = 1; i < n+1; i++) {
            if(visited[i] != 1){
                count++;
                q.add(i);
//                bfs();
                bfs_iterative();
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
    public static void bfs_list(){
        if(!q.isEmpty()){
            int cur = q.poll();
//            if(visited[cur] != 1){
//                System.out.print(cur + " ");
//            }
            visited[cur] = 1;
            for(int val : l[cur]){
               if(visited[val] != 1){
                   q.add(val);
               }
            }
            bfs_list();
        }
        else{
            return;
        }

    }
    public static void bfs_iterative(){
        while(!q.isEmpty()){
            int cur = q.poll();
            for (int val : l[cur]) {
                if(visited[val] != 1){
                    visited[val] = 1;
                    q.add(val);
                }
            }
        }

    }
}
