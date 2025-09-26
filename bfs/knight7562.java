import java.io.*;
import java.util.*;

public class knight7562 {
    static int t,l;
    static int[][] visited;
    static int[] dx = {-2, 2, 1, -1, -2, 2, 1, -1};
    static int[] dy = {1, 1, 2, 2, -1, -1, -2, -2};
    static Queue<int[]> q = new LinkedList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            l = Integer.parseInt(br.readLine());
            visited = new int[l][l];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startx, starty;
            startx = Integer.parseInt(st.nextToken());
            starty = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int destinx, destiny;
            destinx = Integer.parseInt(st.nextToken());
            destiny = Integer.parseInt(st.nextToken());
            q = new LinkedList<>();
            q.add(new int[] {startx,starty});
            visited[startx][starty] = 1;
            bfs();
            System.out.println((visited[destinx][destiny] - 1));
        }
    }
    public static void bfs(){
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            for (int i = 0; i < 8; i++) {
                int x = curx + dx[i];
                int y = cury + dy[i];

                if(x>=0 && x<l && y>=0 && y<l){
                    if(visited[x][y] == 0){
                        visited[x][y] = visited[curx][cury] + 1;
                        q.add(new int[] {x,y});
                    }
                }
            }
        }
    }
}
