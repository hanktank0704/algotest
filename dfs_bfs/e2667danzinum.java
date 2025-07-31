import java.io.*;
import java.util.*;
public class e2667danzinum {
    static int n;
    static int[][] arr;
    static int[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int cnt = 1;
    static int howmany=0;

    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            String a = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = a.charAt(j) - '0';
            }
        }
        List<Integer> L = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == 1 && visited[i][j] == 0){
                    howmany = 0;
                    q.add(new int[] {i,j});
                    visited[i][j] = cnt;            //TODO : this is the part i got wrong
                    howmany++;
//                    System.out.println("added: " + i + " " + j);
                    bfs();
                    cnt++;
//                    System.out.println(howmany);
                    L.add(howmany);
                }

            }

        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(visited[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(cnt-1);
        Collections.sort(L);
        for(int val : L){
            System.out.println(val);
        }

    }

    public static void bfs(){
        if(!q.isEmpty()){
//            howmany++; why not here??? gpt question
            int[] cur = q.poll();
            int a = cur[0];
            int b = cur[1];
            for (int i = 0; i < 4; i++) {
                int x = a + dx[i];
                int y = b + dy[i];
                if(x>=0 && x<=n-1 && y>=0 && y<=n-1){
                    if(visited[x][y] != 0)
                        continue;
                    if(arr[x][y] == 1){
                        q.add(new int[] {x,y});
                        visited[x][y] = cnt;
                        howmany++;
                        bfs();
                    }
                }
            }
        }
    }
}
