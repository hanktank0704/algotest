import java.io.*;
import java.util.*;
public class n1240nodedistance {
    static int n,m;
    static ArrayList<ArrayList<edge>> nlist;
    static Queue<Integer> q;
    static int[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nlist = new ArrayList<>();
        visited = new int[n+1];
        q= new LinkedList<>();

        for (int i = 0; i < n+1; i++) {
            nlist.add(new ArrayList<>());
        }

        int a,b,d;
        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            nlist.get(a).add(new edge(b,d));
            nlist.get(b).add(new edge(a,d));
        }
        int x,y;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            visited = new int[n+1];
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            q.add(x);
            visited[x] = 1;
            bfs(x,y,0);
        }
    }
    public static void bfs(int start, int end, int dis){
        while(!q.isEmpty()){
            int temp = q.poll();
            if(temp == end)
                System.out.println(dis);
            for (edge val : nlist.get(temp)) {
                if(visited[val.to] == 0){
                    q.add(val.to);
                    visited[val.to] = 1;
                    bfs(temp, end, dis + val.dis);
                }
            }
        }
    }
    static class edge{
        int to;
        int dis;
        public edge(int to, int dis){
            this.to = to;
            this.dis = dis;
        }
    }
}