import java.io.*;
import java.util.*;
public class i15686chickendelivery {
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

    public static void main(String[] args) throws IOException {
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
//        ArrayList<int[]> templist = new ArrayList<>(lis);
        if(stage == totalchicken - m){
            for (int val : deletethis) {
                int[] cor = lis.get(val);
                int x = cor[0];
                int y = cor[1];

                arr[x][y] = 0;
            }
            findsum();
//            int temp =
//            if(ans > temp)
//                ans = temp;

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
    public static int findsum(){
        int dissum=0;
        // find the remaining chicken
        ArrayList<int[]> remain = new ArrayList<>(lis);
        for (int i = deletethis.length-1; i >= 0; i--) {
            int temp = deletethis[i];
            remain.remove(temp);
        }
//        System.out.println("remaining");
//        for (int[] val : remain) {
//            System.out.println(val[0] + " " + val[1]);
//        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(arr[i][j] == 1){
                    int dis=Integer.MAX_VALUE;
                    for(int[] val : remain){
                        int temp=0;
                        temp += Math.abs(val[0] - i);
                        temp += Math.abs(val[1] - j);
                        if(dis > temp)
                            dis = temp;
                    }
                    dissum += dis;
                }
            }
        }
//        System.out.println("dissum : " + dissum);
        if(ans > dissum)
            ans = dissum;

        return 0;
    }
}
