import java.io.*;
import java.util.*;
public class m10026redgreenblind {
    static int n;
    static char[][] arr;
    static int[][] visited;
    static Queue<int[]> q;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new char[n][n];
        visited = new int[n][n];
        q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        int area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j] == 0){
                    area++;
                    q.add(new int[] {i,j});
                    visited[i][j] = area;
                    bfs(area, arr[i][j]);
                }
            }
        }
        System.out.print(area + " ");

        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == 'G')
                    arr[i][j] = 'R';
            }
        }

        area = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(visited[i][j] == 0){
                    area++;
                    q.add(new int[] {i,j});
                    visited[i][j] = area;
                    bfs(area, arr[i][j]);
                }
            }
        }
        System.out.println(area);


    }
    public static void bfs(int area, char color){
        while(!q.isEmpty()){
            int[] cur;
            cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];

            for (int i = 0; i < 4; i++) {
                int x = curx + dx[i];
                int y = cury + dy[i];

                if(x>=0 && x<n && y>=0 && y<n){
                    if(visited[x][y] == 0 && arr[x][y] == color){
                        q.add(new int[] {x,y});
                        visited[x][y] = area;
                    }
                }
            }
        }
    }
}
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(visited[i][j] + " ");
//            }
//            System.out.println();
//        }
