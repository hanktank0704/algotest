import java.io.*;
import java.util.*;
public class a11725treeparent {
    static int n;
    static ArrayList<ArrayList<Integer>> adj;
    static Queue<Integer> q;
    static int[] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int x,y;
        adj = new ArrayList<>();
        q = new LinkedList<>();
        parent = new int[n+1];
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            adj.get(x).add(y);
            adj.get(y).add(x);
        }
        q.add(1);
        bfs();
        for (int i = 2; i < n+1; i++) {
            System.out.println(parent[i]);
        }
    }
    public static void bfs(){
        while(!q.isEmpty()){
            int cur = q.poll();
//            System.out.println("visit: " + cur);
            for (int val : adj.get(cur)) {
                if(parent[cur] == val)
                    continue;
                q.add(val);
                parent[val] = cur;
            }
        }
    }
}
