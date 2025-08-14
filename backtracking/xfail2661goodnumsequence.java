import java.io.*;

public class xfail2661goodnumsequence {
    static int n;
    static int[][] dynamic;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dynamic = new int[n+1][n];
        dynamic[1][0] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i-1; j++) {
                dynamic[i][j] = dynamic[i-1][j];
            }
//            dynamic[i][i] = 1, 2, 3;
            for (int j = 1; j <= 3; j++) {
                if(dynamic[i][i-2] != j){
                    dynamic[i][i-1] = j;
                    int aa = checknum(dynamic[i]);
                    if(aa == 1){
                        break;
                    }
                }
            }
        }
//        System.out.println(Arrays.toString(dynamic[n]));
        StringBuilder st = new StringBuilder();
        for (int val : dynamic[n]) {
            st.append(val);
        }
        System.out.println(st);
//        System.out.println(checknum(new int[] {1,2,1,3}));
//        System.out.println(checknum(new int[] {1,2,1,3,1}));
//        System.out.println(checknum(new int[] {1,2,1,3,1,2}));
    }
    public static int checknum(int[] num){
        String s;
        StringBuilder sb = new StringBuilder();
        for (int val : num) {
            if(val == 0)
                break;
            sb.append(val);
        }
        s = sb.toString();

        int[] split = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            split[i] = s.charAt(i) - '0';
        }
//        System.out.println(Arrays.toString(split));

        int check = 1;
        for (int i = 1; i <= s.length()/2; i++) {
            int len = s.length();
            String a = s.substring(len - 2*i, len -i );
            String b = s.substring(len - i, len);

            if(a.equals(b)){
                return 0;
            }

        }
        return 1;
    }
}
