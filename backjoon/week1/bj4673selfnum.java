import java.io.IOException;
import java.util.Arrays;

public class bj4673selfnum {
    public static void findnonselfnum(int a, int[] num){
//        int selfnum = 1;
        int sum=0;
        sum += a;
        while(a>0){
            sum += (a%10);
            a = a/10;
        }
        if(sum < 10000)
            num[sum] = 1;
    }
    public static void main(String[] args) throws IOException {
        int[] num = new int[10001];
        num[1] = 0;
        for (int i = 1; i <= 10000; i++) {
            findnonselfnum(i, num);
        }

        for (int i = 1; i < 10000; i++) {
            if(num[i] == 0){
                System.out.println(i);
            }
        }
//        System.out.println(Arrays.toString(num));
    }
}
