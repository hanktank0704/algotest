import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj10431lining {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split(" ");
            int testcase = Integer.parseInt(tokens[0]);
            int[] values = new int[tokens.length - 1];
            for (int j = 1; j < tokens.length; j++) {
                values[j-1] = Integer.parseInt(tokens[j]);
            }

            int answer = 0;
            for (int j = 0; j < values.length; j++) {
                for (int k = 0; k < j; k++) {
                    if(values[k] > values[j]){
                        answer+= j-k;
                        int temp = values[j];
                        for (int l = j-1; l >= k; l--) {
                            values[l+1] = values[l];
                        }
                        values[k] = temp;
                        break;
                    }
                }
            }
            System.out.println((i+1) + " " + answer);
//            System.out.println(Arrays.toString(values));
        }
    }
}
