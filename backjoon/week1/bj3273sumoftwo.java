import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class bj3273sumoftwo {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String line = br.readLine();
        String[] tokens = line.split(" ");
        int[] numbers = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            numbers[i] = Integer.parseInt(tokens[i]);
        }

        int sum = Integer.parseInt(br.readLine());

//        System.out.println("n: " + n);
//        System.out.println("arr: " + Arrays.toString(numbers));
//        System.out.println("sum: " + sum);
        // 1 2 3 4 5 6 7 8 9
        Arrays.sort(numbers);
        int i=0, j=numbers.length-1;
        int answer = 0;
        while (i<j){
            if(numbers[i] + numbers[j] < sum){
                i++;
            }
            else if(numbers[i] + numbers[j] > sum){
                j--;
            }
            else{
                i++;
                j--;
                answer++;
            }
        }
        System.out.println(answer);
    }
}