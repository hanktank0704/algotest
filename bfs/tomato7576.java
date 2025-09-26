import java.io.*;
import java.util.*;
public class tomato7576 {
    static int n,m;
    static int[][] arr;
    static int[][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j] == 1){
                    visited[i][j] = 1;
                    q.add(new int[] {i,j});
                }
            }
        }

        bfs();
        System.out.println(check());

//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(visited[i][j] + " ");
//            }
//            System.out.println();
//        }

    }
    public static void bfs(){
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            for (int i = 0; i < 4; i++) {
                int x = curx + dx[i];
                int y = cury + dy[i];

                if(x>=0 && x<n && y>=0 && y<m){
                    if(visited[x][y] == 0 && arr[x][y] != -1){
                        visited[x][y] = visited[curx][cury] + 1;
                        q.add(new int[] {x,y});
                    }
                }
            }
        }
    }
    public static int check(){
        int max=-10;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(visited[i][j] == 0 && arr[i][j] == 0){
//                    System.out.println();
//                    System.out.println(i + " " + j);
                    return -1;
                }

                if(max < visited[i][j]){
                    max = visited[i][j];
                }


            }
        }
        return max-1;
    }
}
