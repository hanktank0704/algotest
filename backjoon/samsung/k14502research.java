import java.io.*;
import java.util.*;
public class k14502research {
    static int n,m;
    static int[][] a;
    static int[][] copya;
    static int[][] visited;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static Queue<int[]> q = new LinkedList<>();

    static int leftover = 0;
    static int realleftover = 0;
    static int ans= 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new int[n][m];
        copya = new int[n][m];
        visited = new int[n][m];

        leftover = n*m;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
                if(a[i][j] == 1)
                    leftover--;
            }
        }
        realleftover = leftover;

        //backtrack
        backtrack(0,0);

        //bfs

        System.out.println(ans);


    }
    public static void backtrack(int stage, int wherewall){ // create walls
        if(stage == 3){
            leftover = realleftover;
//            System.out.println("realleftover" + leftover);
            visited = new int[n][m];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    copya[i][j] = a[i][j];
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(copya[i][j] == 2 && visited[i][j] == 0){
                        visited[i][j] = 1;
                        q.add(new int[] {i,j});
                        bfs();
                    }
                }
            }
//            printarr(copya);
            int cnt=0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(copya[i][j] == 0)
                        cnt++;
                }
            }
//            System.out.println("left : " + cnt);
//            System.out.println("leftover " + leftover);
            if(ans < cnt)
                ans = cnt;
        }
        else{
            for (int i = wherewall; i < n*m; i++) {
                if(a[i/m][i%m] == 0){
                    a[i/m][i%m] = 1;
                    backtrack(stage+1, i);
                    a[i/m][i%m] = 0;
                }
            }
        }

    }
    public static void bfs(){ // spread germs to find safe space
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            for (int i = 0; i < 4; i++) {
                int x = curx + dx[i];
                int y = cury + dy[i];
                if(x>=0 && x<n && y>=0 && y<m){
                    if(visited[x][y] == 0 && copya[x][y] == 0){
                        visited[x][y] = 1;
                        q.add(new int[] {x,y});
                        copya[x][y] = 2;
                        leftover--;
                    }
                }
            }
        }
    }
    public static void printarr(int[][] arr){
        System.out.println("print");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.print(a[i][j] + " ");
//            }
//            System.out.println();
//        }
