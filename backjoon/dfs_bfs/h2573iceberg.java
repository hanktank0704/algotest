import java.io.*;
import java.util.*;
public class h2573iceberg {
    static int n,m=0;
    static int[][] arr;
    static int[][] visited;
    static int[][] dissolve;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
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

        int cnt=0;
        int year=0;

        int k=0;
//        for(int ll=0; ll<3; ll++){
        while(true){
            visited = new int[n][m];
            dissolve = new int[n][m];
            cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if(arr[i][j] > 0 && visited[i][j] != 1){
                        q.add(new int[] {i,j});
                        visited[i][j] = 1;
                        bfs();
                        cnt++;
                    }
                }
            }

            if(cnt >= 2){
                System.out.println(k);
                return;
            }

            dissolveArr(1);
            k++;
//            System.out.println("outcome");
//            System.out.println(cnt);
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < m; j++) {
//                    System.out.print(arr[i][j] + " ");
//                }
//                System.out.println();
//            }

            if(checkarr() == 1)
                break;
        }
        System.out.println(0);
//        System.out.println(cnt);



    }
    public static void bfs() {
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int a = cur[0];
            int b = cur[1];
            for (int i = 0; i < 4; i++) {
                int x = a + dx[i];
                int y = b + dy[i];

                if(x>=0 && x<n && y>=0 && y<m){
                    if(arr[x][y] == 0){
                        dissolve[a][b] += 1;
                    }
                    if(arr[x][y] > 0 && visited[x][y] != 1){
                        q.add(new int[] {x,y});
                        visited[x][y] = 1;
                    }
                }
            }
        }
    }
    public static void dissolveArr(int height){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j] -= dissolve[i][j];
                if(arr[i][j] < 0)
                    arr[i][j] = 0;
            }
        }
    }
    public static int checkarr(){
        int ans = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(arr[i][j] > 0){
                    return 0;
                }
            }
        }
        return ans;
    }
}
//            System.out.println("dissolve");
//            for (int i = 0; i < n; i++) {
//        for (int j = 0; j < m; j++) {
//        System.out.print(dissolve[i][j] + " ");
//                }
//                        System.out.println();
//            }
