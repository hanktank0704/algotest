import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class e2417numroot {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
//        System.out.println("n: " + n);

        long l=0;
        long r=n;
        long mid = 0;
        while(l<=r){
            mid = (l+r)/2;
            if(Math.pow(mid,2)==n){
                System.out.println(mid);
                return;
            }
            if(Math.pow(mid,2) <= n){
                l = mid+1;
            }
            else{
                r = mid-1;
            }
//            System.out.println(l+" "+r);
        }
        System.out.println(l);
    }
}
