import java.io.*;
import java.util.*;
public class l14500tetromino {
    static int n,m;
    static int[][] arr;
    static int[][] visited;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int ans = 0;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        System.out.println();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = 1;
                dfs(i,j,0,arr[i][j]);
                visited[i][j] = 0;
            }
        }

        System.out.println(ans);
    }
    public static void dfs(int curx, int cury, int stage, int sum){
        if(stage == 3){
//            System.out.println("sum " + sum);
//            System.out.println(curx + " " + cury);
            ans = Math.max(ans, sum);
        }
        else{
            for (int i = 0; i < 4; i++) {
                int x = curx + dx[i];
                int y = cury + dy[i];
//                System.out.print(arr[curx][cury] + " ");
                if(x>=0 && x<n && y>=0 && y<m){
                    if(visited[x][y] == 0){
                        if(stage == 1){
                            visited[x][y] = 1;
                            dfs(curx,cury, stage+1, sum + arr[x][y]);
                            visited[x][y] = 0;
                        }
                        visited[x][y] = 1;
                        dfs(x,y, stage+1, sum + arr[x][y]);
                        visited[x][y] = 0;
                    }
                }
            }
        }

    }
}
