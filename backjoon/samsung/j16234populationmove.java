import java.io.*;
import java.util.*;
public class j16234populationmove {
    static int[][] a;
    static int[][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    static int group = 0;
    static int ans=0;

    static int n, l, r;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        a = new int[n][n];
        visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int change;
        for (int aa = 0; aa < 2000; aa++) {
            int[][] tempv = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    tempv[i][j] = visited[i][j];
                }
            }
            visited = new int[n][n];
            group = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(visited[i][j] == 0){
                        group++;
                        visited[i][j] = group;
                        q.add(new int[] {i,j});
                        change = bfs();
                    }
                }
            }
            if(Arrays.deepEquals(tempv, visited)){
                break;
            }
            else{
                ans++;
            }
        }

//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(a[i][j] + " ");
//            }
//            System.out.println();
//        }

        System.out.println(ans-1);

    }
    static public int bfs(){
        int sum=0;
        int cnt=0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            sum += a[curx][cury];
            cnt++;
            for (int i = 0; i < 4; i++) {
                int x = curx + dx[i];
                int y = cury + dy[i];

                if(x>=0 && x<n && y>=0 && y<n){
                    int diff = Math.abs(a[x][y] - a[curx][cury]);
                    if(visited[x][y] == 0 && diff >= l && diff <= r){
                        q.add(new int[] {x,y});
                        visited[x][y] = group;
                    }
                }
            }
        }

        int temp = sum/cnt;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j] == group){
                    a[i][j] = temp;
                }
            }
        }
        return sum/cnt;
    }
}
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(a[i][j] + " ");
//            }
//            System.out.println();
//        }

//static public void bfs(){
//    while(!q.isEmpty()){
//        int[] cur = q.poll();
//        int curx = cur[0];
//        int cury = cur[1];
//        for (int i = 0; i < 4; i++) {
//            int x = curx + dx[i];
//            int y = cury + dy[i];
//
//            if(x>=0 && x<n && y>=0 && y<n){
//                int diff = Math.abs(a[x][y] - a[curx][cury]);
//                if(visited[x][y] == 0 && diff >= l && diff <= r){
//                    q.add(new int[] {x,y});
//                    visited[x][y] = group;
//                }
//            }
//        }
//    }
//}
