import java.io.*;
import java.util.*;

public class c2644choncal {
    static int n, m;
    static int x, y;
    static Queue<Integer> q = new LinkedList<>();
    static int[][] arr;
    static int[] visited;
//    static List<Integer>[] l;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
//        l = new ArrayList[n+1];
//        for (int i = 0; i < n+1; i++) {
//            l[i] = new ArrayList<>();
//        }
        arr = new int[n+1][n+1];
        visited = new int[n+1];
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a][b] = 1;
            arr[b][a] = 1;
//            l[a].add(b);
//            l[b].add(a);
        }
//        for (int i = 0; i < n+1; i++) {
//            for(int val : l[i]){
//                System.out.print(val + " ");
//            }
//            System.out.println();
//        }
        int cnt = 1;
        for (int i = 0; i < visited.length; i++) {
            visited[i] = -1;
        }

        q.add(x);
        visited[x] = 0;
        while(!q.isEmpty()){
            int cur = q.poll();

            for (int i = 1; i < n+1; i++) {
                if(arr[cur][i] == 1 && visited[i] == -1){
                    q.add(i);
//                    System.out.println("added " + i);
                    visited[i] = visited[cur] + 1;
                }
            }
//            cnt++;
        }
        System.out.println(visited[y]);
//        for(int val: visited){
//            System.out.print(val + " ");
//        }

    }
}
