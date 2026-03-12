import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj11068 {
    public static int checksymmetry(String s){
        int i=0, j=s.length()-1;
        int answer = 1;
        while(i<j){
            if(s.charAt(i) != s.charAt(j)){
                answer = 0;
                break;
            }
            i++;
            j--;
        }
        return answer;
    }
    public static String toBase(int num, int base){
        StringBuilder s = new StringBuilder();
        while(num>0){
            int remainder = num % base;
            if(remainder < 10){
                s.append(remainder);
            }
            else{
                s.append((char)('A' + remainder - 10));
            }
            num /= base;
        }
        return s.reverse().toString();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
            int ans = 0;
            for(int j=2; j<=64; j++){
                if(checksymmetry(toBase(nums[i], j)) == 1){
                    ans=1;
                    break;
                }
            }
            System.out.println(ans);
        }
    }
}
