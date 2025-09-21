import java.io.*;
import java.util.*;
public class e14267companyculture {
    static int n,m;
    static int[] parent;
    static int[] praise;
    static int i,w;
    static ArrayList<ArrayList<Integer>> a = new ArrayList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        parent = new int[n+1];
        praise = new int[n+1];
        for (int j = 0; j < n+1; j++) {
            a.add(new ArrayList<>());
        }

        st = new StringTokenizer(br.readLine());
        for (int j = 1; j < n+1; j++) {
            parent[j] = Integer.parseInt(st.nextToken());
            if(parent[j] != -1)
                a.get(parent[j]).add(j);
        }

        for (int j = 0; j < m; j++) {
            st = new StringTokenizer(br.readLine());
            i = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            praise[i] += w;
        }
        dfs(1,-1);
        for (int j = 1; j < praise.length; j++) {
            System.out.print(praise[j] + " ");
        }
    }
    public static void dfs(int cur, int par){
//        System.out.println("dfs");
        if(cur == 1){
            praise[cur] = 0;
        }
        else{
            praise[cur] += praise[par];
        }
        for (int val : a.get(cur)) {
            if(val == par)
                continue;
//            System.out.print(val + " ");
            dfs(val, cur);
        }
    }
}
