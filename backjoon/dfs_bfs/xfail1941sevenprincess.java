import java.io.*;
import java.util.*;
public class xfail1941sevenprincess {
    // wrong count same people multiple times
    static char[][] arr;
    static int[][] visited;
    static int dasom;
    static int doyun;
    static int[] dx = {1, 0 , -1, 0};
    static int[] dy = {0, 1, -1, 0};
    static Stack<int[]> s = new Stack<>();
    static int ans=0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new char[5][5];
        visited = new int[5][5];
        for (int i = 0; i < 5; i++) {
            String s = br.readLine();
            for (int j = 0; j < 5; j++) {
                arr[i][j] = s.charAt(j);
            }
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                visited = new int[5][5];
                if(arr[i][j] == 'Y'){
                    s.add(new int[] {i,j});
                    visited[i][j] = 1;
                    dfs(1,0);
                }
                else{
                    s.add(new int[] {i,j});
                    visited[i][j] = 1;
                    dfs(1,1);
                }
            }
        }
        System.out.println(ans);

//        printarr();
    }
    public static void dfs(int stage, int dasom){
        if(stage == 7){
            System.out.println("stage 7");
            if(dasom >=4){
                ans++;
            }
        }
        else{
            int[] cur = s.pop();
//            int x = cur[0];
//            int y = cur[1];
            int curx = cur[0];
            int cury = cur[1];
//            System.out.println(x + " " + y);
            for (int i = 0; i < 4; i++) {
//                x += dx[i];
//                y += dy[i];
                int x = curx + dx[i];
                int y = cury + dy[i];
                if(x>=0 && x<5 && y>=0 && y<5){
                    if(visited[x][y] == 0){
                        s.add(new int[] {x,y});
//                        System.out.println("added");
                        visited[x][y] = 1;
                        if(arr[x][y] == 'Y')
                            dfs(stage+1, dasom+1);
                        else
                            dfs(stage+1, dasom);
                        //necessary?
                    }
                }
            }

        }
    }
    public static void printarr(){
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
