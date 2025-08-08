import java.io.*;
import java.util.*;
public class e14503robotclean {
    static int n,m;
    static int[][] arr;
    static int startx, starty;
    // 0 : north, 1 : east, 2: south, 3: west
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans=1;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        startx = Integer.parseInt(st.nextToken());
        starty = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        arr[startx][starty] = -1;
        ans = 1;
        dfs(direction, startx, starty);
    }

    public static void dfs(int direction, int cx, int cy){
        // 0 : north, 1 : east, 2: south, 3: west
        int x,y,nextdir;
        for (int i = 0; i < 4; i++) {
            direction = (direction+3)%4;
            //nextdir = (direction+3)%4;
            x = cx + dx[direction];
            y = cy + dy[direction];
            if(x>=0 && x<n && y>=0 &&y<m){
                if(arr[x][y] == 0){
                    arr[x][y] = -1;
                    ans++;
                    dfs(direction, x, y);
                    return;
                }
            }
        }
        direction = (direction+2)%4;
        x = cx + dx[direction];
        y = cy + dy[direction];
        direction = (direction+2)%4;
        if(arr[x][y] == 1){
            System.out.println(ans);
            return;
        }
        else{
            dfs(direction, x, y);
        }
    }
}
