import java.io.*;
import java.util.*;
public class hideseek1697 {
    static int n,k;
    static int[] visited;
    static Queue<Integer> q = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        visited = new int[100001];
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        q.add(n);
        visited[n]= 1;
        bfs();
//        for (int i = 0; i <= k; i++) {
//            System.out.print(visited[i] + " ");
//        }
//        System.out.println();
        System.out.println(visited[k] - 1);

    }
    public static void bfs(){
        while(!q.isEmpty()){
            int cur = q.poll();
            if(visited[k] != 0)
                return;

            // +1
            int x = cur+1;
            if(x<100001){
                if(visited[x] == 0){
                    visited[x] = visited[cur]+1;
                    q.add(x);
                }
            }
            // -1
            x = cur-1;
            if(x>=0){
                if(visited[x] == 0){
                    visited[x] = visited[cur]+1;
                    q.add(x);
                }

            }
            // *2
            x = cur*2;
            if(x < 100001){
                if(visited[x] == 0){
                    visited[x] = visited[cur]+1;
                    q.add(x);
                }
            }

        }
    }
}
