import java.io.*;
import java.util.*;
public class d1068tree {
    static int n;
    static int root;
    static int delete;
    static int[] parent;
    static ArrayList<ArrayList<Integer>> a = new ArrayList<>();
    static int ans=0;

    static Stack<Integer> s = new Stack<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            a.add(new ArrayList<>());
        }
        StringTokenizer st = new StringTokenizer(br.readLine());
        root=-1;
        for (int i = 0; i < n; i++) {
            parent[i] = Integer.parseInt(st.nextToken());
            if(parent[i] != -1) {
                a.get(parent[i]).add(i);
                a.get(i).add(parent[i]);
            }
            else{
                root = i;
            }
        }
        delete = Integer.parseInt(br.readLine());

        if(delete == root)
            System.out.println(0);
        else{
            dfs(root, -1);
            System.out.println(ans);
        }
    }
    public static void dfs(int cur, int parent){
        int child = 0;
        for (int val : a.get(cur)) {
            if(val == delete)
                continue;
            if(val == parent)
                continue;
            child++;
            dfs(val, cur);
        }
        if(child == 0 )
            ans++;
    }
}
