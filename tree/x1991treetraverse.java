import java.io.*;
import java.util.*;
public class x1991treetraverse {
    static int n;
    static ArrayList<ArrayList<Integer>> adj;
    static int[] parent;
    static Queue<Integer> q;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        adj = new ArrayList<>();
        q = new LinkedList<>();
        parent = new int[27];
        for (int i = 0; i < 27; i++) {
            adj.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int ptemp, rtemp, ltemp;
            ptemp = s.charAt(0) - 'A';
            s = st.nextToken();
            if(s.charAt(0) != '.'){
                rtemp = s.charAt(0) - 'A';
                adj.get(ptemp).add(rtemp);
                parent[rtemp] = ptemp;
            }
            s = st.nextToken();
            if(s.charAt(0) != '.'){
                ltemp = s.charAt(0) - 'A';
                adj.get(ptemp).add(ltemp);
                parent[ltemp] = ptemp;
            }
        }

        q = new LinkedList<>();
        q.add('A' - 'A');
        preorder();
        System.out.println();

        q = new LinkedList<>();
        q.add('A' - 'A');
        inorder();
        System.out.println();

        q = new LinkedList<>();
        q.add('A' - 'A');
        postorder();
    }
    public static void preorder(){
        while(!q.isEmpty()){
            int cur = q.poll();
            System.out.print((char)(cur + 'A'));
            for (int val : adj.get(cur)) {
                q.add(val);
                preorder();
            }
        }
    }
    public static void inorder(){
        while(!q.isEmpty()){
            int cur = q.poll();
//            for (int val : adj.get(cur)) {
//                q.add(val);
//                inorder();
//                System.out.print((char)(cur + 'A'));
//            }
            q.add(adj.get(cur).get(0));
            inorder();
            System.out.print((char)(cur + 'A'));
            q.add(adj.get(cur).get(1));
            inorder();

        }
    }
    public static void postorder(){
//        for (int val : q) {
//            System.out.print(val + " ");
//        }
//        System.out.println();
        int cur = q.poll();
        for (int val : adj.get(cur)) {
            q.add(val);
            postorder();
        }
        System.out.print((char)(cur + 'A'));
    }
}