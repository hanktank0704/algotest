import java.io.*;
import java.util.*;
public class k7569tomato {
    static int m,n,h;
    static int[][][] tom;
    static int[][][] visited;
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    static int ans=0;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        tom = new int[m][n][h];
        visited = new int[m][n][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < m; k++) {
                    tom[k][j][i] = Integer.parseInt(st.nextToken());
                }
            }
        }


    }
    // check if everything is 1 or -1
    // 1. initialize visited
    // 2. add 0 into quue
    // 3. check x,y,z if it has 1 near by
    //    3-1. if there is change it to 1
    // 4. repeat cnt ++
    public static void bfs(){
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if(tom[k][j][i] == 0)
                        q.add(new int[] {k,j,i});
                }
            }
        }

        ans++;
    }
    public static int checkforzero(){
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < m; k++) {
                    if(tom[k][j][i] == 0)
                        return 0;
                }
            }
        }
        return 1;
    }
}
//        System.out.println();
//        for (int i = 0; i < h; i++) {
//            for (int j = 0; j < n; j++) {
//                for (int k = 0; k < m; k++) {
//                    System.out.print(tom[k][j][i] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//        }
