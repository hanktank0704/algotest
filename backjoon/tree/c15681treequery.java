import java.io.*;
import java.util.*;
public class c15681treequery {
    static int n, r, q;
    static ArrayList<ArrayList<Integer>> a = new ArrayList<>();
    static int[] visited;
    static int[] parent;
    static int[] size;
    static Stack<Integer> s = new Stack<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        q = Integer.parseInt(st.nextToken());

        a = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            a.add(new ArrayList<>());
        }
        visited = new int[n+1];
        parent = new int[n+1];
        size = new int[n+1];

        for (int i = 0; i < n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            a.get(x).add(y);
            a.get(y).add(x);
        }
        dfs(r,0);

//        for (int val : size) {
//            System.out.print(val + " ");
//        }
//        System.out.println();

        for (int i = 0; i < q; i++) {
            int x = Integer.parseInt(br.readLine());
            System.out.println(size[x]);
        }
    }
    public static void dfs(int cur, int par){
        size[cur] = 1;
        for (int val : a.get(cur)) {
            if(val == par)
                continue;
            dfs(val, cur);
            size[cur] += size[val];
        }
    }
}
