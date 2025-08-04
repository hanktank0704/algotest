import java.io.*;
import java.util.*;
public class a13458testproctor {
    static int[] A;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        A = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i < n+1; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        long total = 0;
        for (int i = 1; i < n+1; i++) {
            total++;
            if(A[i] - b > 0){
                if((A[i] - b) % c == 0){
                    total += (A[i] - b)/c;
                }
                else{
                    total += (A[i] - b)/c + 1;
                }
            }
        }
        System.out.println(total);
    }
}
