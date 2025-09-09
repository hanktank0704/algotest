import java.io.*;
import java.util.*;
public class x15686chickendelivery {
    static int ans = Integer.MAX_VALUE;
    static int n,m;
    static int[][] arr;
    static int[][] visited;
    static Queue<int[]> q;
    static int[] dx = {1,0,-1,0};
    static int[] dy = {0,1,0,-1};
    static int totalDis = 0;

    static int[] deletethis;
    static int totalchicken;
    static int[] usedchicken;
    static ArrayList<int[]> lis;

    public static void main(String[] args) throws IOException{
        //roudnlab, 천주혁kj
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        visited = new int[n][n];
        q = new LinkedList<>();
        lis = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2)
                    lis.add(new int[] {i,j});
            }
        }

        // find way to pick m chickenshop;
        // backtrack
        totalchicken = lis.size();
        deletethis = new int[totalchicken - m];
        usedchicken = new int[totalchicken];
        backtrack(0,0);

//        System.out.println();
        System.out.println(ans);

//        System.out.println();
//        System.out.println(findDisSum());

    }
    // choose total - M number of chickenshop to delete
//    static int[] deletethis;
//    static int totalchicken;
//    static int[] usedchicken;
    public static void backtrack(int stage, int start){
        if(stage == totalchicken - m){
//            System.out.println("deletethis");
//            for (int val : deletethis) {
//                System.out.print(val + " ");
//            }
//            System.out.println();
            for (int val : deletethis) {
                int[] cor = lis.get(val);
                int x = cor[0];
                int y = cor[1];

                arr[x][y] = 0;

            }
//            System.out.println("arr: ");
//            for (int i = 0; i < n; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.print(arr[i][j] + " ");
//                }
//                System.out.println();
//            }
//            System.out.println("sum: ");
            int temp = findDisSum();
//            System.out.println(temp);
            if(ans > temp)
                ans = temp;

//            System.out.println();
            for (int val : deletethis) {
                int[] cor = lis.get(val);
                int x = cor[0];
                int y = cor[1];

                arr[x][y] = 2;
            }

//            for (int val : deletethis) {
//                System.out.print(val + " ");
//            }
//            System.out.println();
            return;
        }
        else{
            for (int i = start; i < totalchicken; i++) {
                deletethis[stage] = i;
                backtrack(stage+1, i+1);
            }
        }

    }
    public static int findDisSum(){
        totalDis = 0;
//        visited = new int[n][n];
        q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == 1){
                    visited = new int[n][n];
                    q = new LinkedList<>();
                    q.add(new int[] {i,j});

                    visited[i][j] = 1;
                    int distemp = bfsDis();
                    totalDis += distemp;
                }
            }
        }
        return totalDis;
    }
    public static int bfsDis(){
//        visited = new int[n][n];
        int dis = 0;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            for (int i = 0; i < 4; i++) {
                int x = curx + dx[i];
                int y = cury + dy[i];

                if(x>=0 && x<n && y>=0 && y<n){
                    if(visited[x][y] == 0){
                        q.add(new int[] {x,y});
                        visited[x][y] = visited[curx][cury] + 1;
                        if(arr[x][y] == 2){
//                            System.out.println();
//                            System.out.println("visited: ");
//                            for (int a = 0; a < n; a++) {
//                                for (int j = 0; j < n; j++) {
//                                    System.out.print(visited[a][j] + " ");
//                                }
//                                System.out.println();
//                            }
                            return visited[x][y] - 1;
                        }
                    }
                }
            }
        }
        return -1;
    }
}
