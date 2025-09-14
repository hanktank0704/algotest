import java.io.*;
import java.util.*;
public class mintchoco {
    static int n,t;
    static int[][] f;
    static int[][] b;

    static Queue<int[]> q = new LinkedList<>();

    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int[][] visited = new int[n][n];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        f = new int[n][n];
        b = new int[n][n];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                char temp = s.charAt(j);
                if(temp == 'T'){
                    f[i][j] = 1;
                }
                if(temp == 'C'){
                    f[i][j] = 2;
                }
                if(temp == 'M'){
                    f[i][j] = 3;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                b[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < t; i++) {
            System.out.println(t + "th line");
            // morning
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    b[j][k] += 1;
                }
            }

            // lunch
            // bfs로 같은 그룹 돌아가면서 최대 신앙치를 찾으면 좌표를 대표자 어레이리스트에 추가한다.
            ArrayList<int[]> daepyo = new ArrayList<>();
            ArrayList<int[]> samefood = new ArrayList<>();
            visited = new int[n][n];

            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    if(visited[j][k] == 0){

                        samefood = new ArrayList<>();
                        q.add(new int[] {j,k});
                        // bfs();
                        bfs(samefood);
//                        System.out.println("food: " + f[j][k]);
//                        for (int[] val : samefood) {
//                            System.out.println(val[0] + " " + val[1]);
//                        }
//                        System.out.println();
                    }
                }
            }

            //samefood 에서 얻은 같은 그룹에서 대표자를 선정하면된다.

            int[] leader = samefood.get(0);
            ArrayList<int[]> leaders = new ArrayList<>();

            for (int[] val : samefood) {
                int[] cur = val;
                int curx = cur[0];
                int cury = cur[1];

                if(b[leader[0]][leader[1]] < b[curx][cury]){
                    leader[0] = curx;
                    leader[1] = cury;
                }
                leaders.add(leader);
            }

            for (int[] val : leaders) {
                System.out.println(val[0] + " " + val[1]);
            }


            // night


        }
    }
//    public static void bfs(ArrayList al){
    public static void bfs(ArrayList<int[]> al){
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            for (int i = 0; i < 4; i++) {
                int x = curx + dx[i];
                int y = cury + dy[i];

                if(x>=0 && x<n && y>=0 && y<n){
                    if(visited[x][y] == 0 && f[curx][cury] == f[x][y]){
                        q.add(new int[] {x,y});
                        visited[x][y] = 1;
                        al.add(new int[] {x,y});
                    }
                }
            }
        }
    }
}
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(f[i][j] + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < n; j++) {
//                System.out.print(b[i][j] + " ");
//            }
//            System.out.println();
//        }
