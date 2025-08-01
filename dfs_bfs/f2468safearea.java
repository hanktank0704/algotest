import java.io.*;
import java.util.*;
public class f2468safearea {
    static int n;
    static int[][] arr;
    static int[][] visited;
    static Queue<int[]> q = new LinkedList<>();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int cnt=0;
    static int height = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max=0;
        for (int aa = 0; aa < 100; aa++) {
            visited = new int[n][n];
            height = aa;
//            System.out.println("height: " + height);
            cnt = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if(arr[i][j] > height){
                        if(visited[i][j] == 1)
                            continue;
                        q.add(new int[] {i,j});
                        visited[i][j] = 1;
                        bfs();
                        cnt++;
                    }
                }
            }
            if(max < cnt)
                max = cnt;
//            System.out.println("cnt: " + max);

        }
        System.out.println(max);

//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                int temp = arr[i][j] - height;
//                if(temp <0)
//                    temp = 0;
//                System.out.print(temp + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(visited[i][j] + " ");
//            }
//            System.out.println();
//        }


    }
    public static void bfs(){
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int a = x + dx[i];
                int b = y + dy[i];

                if(a>=0 && a<n && b>=0 && b<n){
                    if(visited[a][b] == 0 && arr[a][b] > height){
                        q.add(new int[] {a,b});
                        visited[a][b] = 1;
                    }
                }

            }

        }
    }
}
