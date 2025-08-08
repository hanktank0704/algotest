import java.io.*;
import java.util.*;
public class Xfail14503robotclean {
    static int n,m;
    static int startx,starty, direction;
    static int[][] arr;
    static int[][] cleaned;
    // 0 : north, 1 : east, 2: south, 3: west
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans=0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        cleaned = new int[n][m];
        st = new StringTokenizer(br.readLine());
        startx = Integer.parseInt(st.nextToken());
        starty = Integer.parseInt(st.nextToken());
        direction = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        printarr(arr);

        roomba();
    }
    public static void roomba(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {startx, starty});
        cleaned[startx][starty] = 1;
        ans++;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];
            int x,y;
            printarr(arr, cx, cy);
            for (int i = 0; i < 4; i++) {
                // we might have to change way we search next place to go
                //90 degrees counterclock
                // 0 : north, 1 : east, 2: south, 3: west
//                static int[] dx = {1, 0, -1, 0};
//                static int[] dy = {0, 1, 0, -1};
                directionChanger();
                x = cx + dx[direction];
                y = cy + dy[direction];
                if(x>=0 && x<n && y>=0 && y<m){
                    if(arr[x][y] == 0){
                        q.add(new int[] {x,y});
                        cleaned[x][y] = 1;
                    }
                }
            }
            // if all 4 dir change fails
            // go backward
            x = cx + dx[(direction+2)%4];
            y = cy + dy[(direction+2)%4];
            if(arr[x][y] == 1){
                System.out.println(ans);
                break;
            }
            q.add(new int[] {x,y});
            cleaned[x][y] = 1;
        }


    }
    public static void directionChanger(){
        // 0 : north, 1 : east, 2: south, 3: west
        if(direction == 0){
            direction = 3;
        }
        else{
            direction--;
        }
    }
    public static void printarr(int[][] a, int x, int y){
        System.out.println();
        System.out.println(ans);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(i==x && j==y){
                    if(direction == 0)
                        System.out.print("^ ");
                    if(direction == 1)
                        System.out.print("> ");
                    if(direction == 2)
                        System.out.print("v ");
                    if(direction == 3)
                        System.out.print("< ");
                }
                else{
                    System.out.print(arr[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
//5 5
//3 3 0
//1 1 1 1 1
//1 0 0 0 1
//1 0 0 0 1
//1 0 0 0 1
//1 1 1 1 1
