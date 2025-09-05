import java.io.*;
import java.util.*;
public class l2178mazesearch {
    static int n,m;
    static int[] dx ={1, 0, -1, 0};
    static int[] dy ={0, 1, 0, -1};
    static int[][] maze;
    static int[][] visited;
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        maze = new int[n+1][m+1];
        visited = new int[n+1][m+1];

        for (int i = 1; i < n+1; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                maze[i][j+1] = (s.charAt(j) - '0');
            }
        }

        q.add(new int[] {1,1});
        visited[1][1] = 1;
        bfs(0);


//        System.out.println();
//        for (int i = 0; i < n+1; i++) {
//            for (int j = 0; j < m+1; j++) {
//                System.out.print(visited[i][j] + " ");
//            }
//            System.out.println();
//        }
        System.out.println(visited[n][m]);
    }
    public static void bfs(int cnt){
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];

            for (int i = 0; i < 4; i++) {
                int x = curx + dx[i];
                int y = cury + dy[i];

                if(x>=1 && x<=n && y>=1 && y<=m){
                    if(visited[x][y] == 0 && maze[x][y] == 1){
                        q.add(new int[] {x,y});
                        visited[x][y] = visited[curx][cury] + 1;
                    }
                }
            }
        }
    }
}
//        System.out.println();
//        for (int i = 0; i < n+1; i++) {
//            for (int j = 0; j < m+1; j++) {
//                System.out.print(maze[i][j] + " ");
//            }
//            System.out.println();
//        }
