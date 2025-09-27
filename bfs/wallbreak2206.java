import java.io.*;
import java.util.*;
public class wallbreak2206 {
    static int n,m;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static Queue<int[]> q = new LinkedList<>();
    static int[][] arr;
    static int[][][] visited;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new int[n][m][2];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = (s.charAt(j) - '0');
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j][0] = -1;
                visited[i][j][1] = -1;
            }
        }

        q.add(new int[] {0,0,0});
        visited[0][0][0] = 1;
        bfs();

        int temp1 = visited[n-1][m-1][0];
        int temp2 = visited[n-1][m-1][1];

        if(temp1!=-1 && temp2!=-1)
            System.out.println(Math.min(temp1, temp2));

        if(temp1==-1 || temp2==-1)
            System.out.println(Math.max(temp1, temp2));

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(visited[i][j][0] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(visited[i][j][1] + " ");
//            }
//            System.out.println();
//        }

    }
    public static void bfs(){
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            int broken = cur[2];
            for (int i = 0; i < 4; i++) {
                int x = curx + dx[i];
                int y = cury + dy[i];

                if(x>=0 && x<n && y>=0 && y<m){
                    if(broken == 0 && arr[x][y] == 0 && visited[x][y][0] == -1){
                        visited[x][y][0] = visited[curx][cury][0] + 1;
                        q.add(new int[] {x,y,0});
                    }
                    else if(broken == 1 && arr[x][y] == 0 && visited[x][y][1] == -1){
                        visited[x][y][1] = visited[curx][cury][1] + 1;
                        q.add(new int[] {x,y,1});
                    }
                    else if(broken == 0 && arr[x][y] == 1 && visited[x][y][1] == -1){
                        visited[x][y][1] = visited[curx][cury][0] + 1;
                        q.add(new int[] {x,y,1});
                    }
                }
            }
        }
    }
}
