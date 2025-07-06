import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class bj1120string {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        String a = input[0];
        String b = input[1];

        int alen = a.length();
        int blen = b.length();

        int mindiff=10000;
        for (int i = 0; i < blen - alen+1; i++) {
            int diff=0;
            for (int j = 0; j < alen; j++) {
                if(a.charAt(j) != b.charAt(i+j))
                    diff++;
            }
            if(mindiff > diff)
                mindiff = diff;
        }

        System.out.println(mindiff);
    }
//    public static int checkdiff(String a, String b){
//        int diff = 0;
//        for(int i=0; i<a.length(); i++){
//
//        }
//
//        return diff;
//    }
}
