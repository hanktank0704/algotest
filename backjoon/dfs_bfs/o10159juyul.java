import java.io.*;
import java.util.*;
public class o10159juyul {
    static int n,m;
    static ArrayList<ArrayList<Integer>> a = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> b = new ArrayList<>();
    static Queue<Integer> q = new LinkedList<>();
    static int[] answer;
    static int[] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        answer = new int[n+1];
        visited = new int[n+1];
        for(int i=0; i<n+1; i++){
            answer[i] = n+100;
        }
        for(int i=0; i<=n; i++){
            a.add(new ArrayList<>());
        }
        for(int i=0; i<=n; i++){
            b.add(new ArrayList<>());
        }

        for(int i=0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            a.get(x).add(y);
            b.get(y).add(x);
        }

        for(int i=1; i<=n; i++){
            visited = new int[n+1];
            bfs(i);
        }
    }
    public static void bfs(int start){
        int cnt=0;
        q.add(start);
        visited[start] = 1;
        ArrayList<Integer> visit = new ArrayList<>();
        while(!q.isEmpty()){
            int cur = q.poll();
            // visit.add(cur);
            cnt++;
            for(int val : a.get(cur)){
                if(visited[val] == 0){
                    q.add(val);
                    visited[val] = 1;
                }
            }
        }
        q.add(start);
        while(!q.isEmpty()){
            int cur = q.poll();
            // visit.add(cur);
            cnt++;
            for(int val : b.get(cur)){
                if(visited[val] == 0){
                    q.add(val);
                    visited[val] = 1;
                }
            }
        }

        System.out.println(n - cnt + 1);

    }
}
