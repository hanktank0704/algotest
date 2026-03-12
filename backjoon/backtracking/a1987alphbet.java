import java.io.*;
import java.util.*;
public class a1987alphbet {
    static int r,c;
    static char[][] arr;
    static int[][] visited;
    static int[] alphabet;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int ans=0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr = new char[r][c];
        visited = new int[r][c];
        alphabet = new int[26];

        for (int i = 0; i < r; i++) {
            String sss = br.readLine();
            for (int j = 0; j < c; j++) {
                arr[i][j] = sss.charAt(j);
            }
        }
//        printarr();

        visited[0][0] = 1;
        alphabet[arr[0][0] - 'A'] = 1;
        backtrack(0,0, 1);
        System.out.println(ans);

    }
    public static void backtrack(int cx, int cy, int cnt){
//        visited[cx][cy] = 1;
//        alphabet[arr[cx][cy] - 'A'] = 1;
        if(cnt > ans)
            ans = cnt;

        for (int i = 0; i < 4; i++) {
            int x = cx + dx[i];
            int y = cy + dy[i];

            if(x>=0 && x<r && y>=0 && y<c){
                if(alphabet[arr[x][y] - 'A'] != 1 && visited[x][y] != 1) {
                    alphabet[arr[x][y] - 'A'] = 1;
                    visited[x][y] = 1;
//                    System.out.println(x + " " + y);
//                    System.out.println(cnt);
                    backtrack(x,y, cnt+1);
                    alphabet[arr[x][y] - 'A'] = 0;
                    visited[x][y] = 0;

                }
            }
        }
    }
    public static void printarr(){
        System.out.println();
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
