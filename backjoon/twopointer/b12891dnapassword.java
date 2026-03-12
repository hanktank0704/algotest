import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class b12891dnapassword {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] a = br.readLine().split(" ");
        int s = Integer.parseInt(a[0]);
        int p = Integer.parseInt(a[1]);

        String dna = br.readLine();

        String[] b = br.readLine().split(" ");
        int A = Integer.parseInt(b[0]);
        int C = Integer.parseInt(b[1]);
        int G = Integer.parseInt(b[2]);
        int T = Integer.parseInt(b[3]);

        int anum = 0;
        int cnum = 0;
        int gnum = 0;
        int tnum = 0;
        int LEN = 1000000;


//        System.out.println(Arrays.toString(dna));
//        System.out.println(dna);

        int ans=0;
        for (int i = 0; i < dna.length(); i++) {
            if(dna.charAt(i) == 'A')
                anum++;
            if(dna.charAt(i) == 'C')
                cnum++;
            if(dna.charAt(i) == 'G')
                gnum++;
            if(dna.charAt(i) == 'T')
                tnum++;
            if(i >= p){     //TODO
                if(dna.charAt(i-p) == 'A')
                    anum--;
                if(dna.charAt(i-p) == 'C')
                    cnum--;
                if(dna.charAt(i-p) == 'G')
                    gnum--;
                if(dna.charAt(i-p) == 'T')
                    tnum--;
//                for (int j = i-p+1; j <= i; j++) {
//                    System.out.print(dna.charAt(j));
//                }
//                System.out.println();

//                Move this if statement to outside and added i>=p-1
//                if(anum == A && cnum == C && gnum == G && tnum == T)
//                    ans++;
            }
            //to here
            if(anum >= A && cnum >= C && gnum >= G && tnum >= T && i>=p-1)
                ans++;
        }
        System.out.println(ans);
    }
}

//11 2
//CCTGGATTGCA
//1 1 0 0

//11 2
//AAAAAAAAAAA
//2 0 0 0
