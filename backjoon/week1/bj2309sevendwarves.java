import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.Arrays;

public class bj2309sevendwarves {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] nums = new int[9];
        for (int i = 0; i < 9; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }
//        System.out.println(Arrays.toString(nums));
        Arrays.sort(nums);
        int sum=0;
        for(int num : nums)
            sum+= num;

        for (int i = 0; i < 9; i++) {
            for (int j = i+1; j < 9; j++) {
                if(sum - nums[i] - nums[j] == 100){
                    for (int k = 0; k < 9; k++) {
                        if(k==i || k==j)
                            continue;
                        System.out.println(nums[k]);
                    }
                    return;
                }
            }
        }
    }
}
