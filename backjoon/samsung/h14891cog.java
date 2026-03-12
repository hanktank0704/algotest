import java.io.*;
import java.util.*;
public class h14891cog {
    static int[] first;
    static int[] second;
    static int[] third;
    static int[] fourth;
    static int which, spindir;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        first = new int[8];
        second = new int[8];
        third = new int[8];
        fourth = new int[8];

        for (int i = 0; i < s.length(); i++) {
            first[i] = s.charAt(i) - '0';
        }
        s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            second[i] = s.charAt(i) - '0';
        }
        s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            third[i] = s.charAt(i) - '0';
        }
        s = br.readLine();
        for (int i = 0; i < s.length(); i++) {
            fourth[i] = s.charAt(i) - '0';
        }
        int k;
        k = Integer.parseInt(br.readLine());

//        printcog();
        for (int i = 0; i < k; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            which = Integer.parseInt(st.nextToken());
            spindir = Integer.parseInt(st.nextToken());

            // spinning coordination
            if(which == 1){
                if(first[2] != second[6]){
                    if(second[2] != third[6]){
                        if(third[2] != fourth[6]){
                            spin(fourth, -1 * spindir);
                        }
                        spin(third, spindir);
                    }
                    spin(second, -1 * spindir);
                }
                spin(first, spindir);
            }
            else if(which == 2){
                if(first[2] != second[6]){
                    spin(first, -1 * spindir);
                }
                if(second[2] != third[6]){
                    if(third[2] != fourth[6]){
                        spin(fourth, spindir);
                    }
                    spin(third, -1 * spindir);
                }
                spin(second, spindir);
            }
            else if(which == 3){
                if(third[2] != fourth[6]){
                    spin(fourth, -1 * spindir);
                }
                if(second[2] != third[6]){
                    if(first[2] != second[6]){
                        spin(first, spindir);
                    }
                    spin(second, -1 * spindir);
                }
                spin(third, spindir);
            }
            else{
                if(third[2] != fourth[6]){
                    if(second[2] != third[6]){
                        if(first[2] != second[6]){
                            spin(first, -1 * spindir);
                        }
                        spin(second, spindir);
                    }
                    spin(third, -1 * spindir);
                }
                spin(fourth, spindir);
            }
//            printcog();
        }
        // 1 2 : 2번쨰 톱니, 6번 톱니

        int ans = first[0] + second[0] * 2 + third[0] * 4 + fourth[0] *8;
        System.out.println(ans);
    }
    public static void spin(int[] whichone, int whatdirection){
        if(whatdirection == 1){     //clock direction
            int temp = whichone[7];
            for (int i = 6; i >= 0; i--) {
                whichone[i+1] = whichone[i];
            }
            whichone[0] = temp;
        }
        else if(whatdirection == -1){   //counter clock direction
            int temp = whichone[0];
            for (int i = 0; i < 7; i++) {
                whichone[i] = whichone[i+1];
            }
            whichone[7] = temp;
        }
    }
    public static void printcog(){
        System.out.println();
        for (int val : first) {
            System.out.print(val + " ");
        }
        System.out.println();
        for (int val : second) {
            System.out.print(val + " ");
        }
        System.out.println();
        for (int val : third) {
            System.out.print(val + " ");
        }
        System.out.println();
        for (int val : fourth) {
            System.out.print(val + " ");
        }
        System.out.println();
    }
}
