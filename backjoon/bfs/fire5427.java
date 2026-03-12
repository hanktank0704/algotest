import java.io.*;
import java.util.*;
public class fire5427 {
    static int t;
    static int w,h;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static char[][] arr;
    static char[][] visited;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        t = Integer.parseInt(st.nextToken());

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            arr = new char[h][w];
            visited = new char[h][w];
            for (int j = 0; j < h; j++) {
                String s = br.readLine();
                for (int k = 0; k < w; k++) {
                    arr[j][k] = s.charAt(k);
                    if(arr[j][k] == '*'){
                        q.add(new int[] {j,k});

                    }
                    if(arr[j][k] == '@'){
                        q.add(new int[] {j,k});

                    }
                }
            }
            bfs();

        }
    }
    public static void bfs(){
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            if(arr[curx][cury] == '*'){
                for (int i = 0; i < 4; i++) {
                    int x = curx + dx[i];
                    int y = cury + dy[i];
                    if(x>=0 && x<h && y>=0 && y<w){
                        if(visited[x][y] == '\u0000' && arr[x][y] != '#'){

                        }
                    }
                }

            }
            if(arr[curx][cury] == '@'){
                for (int i = 0; i < 4; i++) {
                    int x = curx + dx[i];
                    int y = cury + dy[i];
                    if(x>=0 && x<h && y>=0 && y<w){

                    }
                }

            }
        }
    }
}

