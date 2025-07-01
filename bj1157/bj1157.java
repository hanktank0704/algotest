import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.util.Arrays;
import java.util.Locale;

public class bj1157 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String uppers = s.toUpperCase();
        int[] alphabetCount = new int[26];
        for(int i=0; i<uppers.length(); i++){
            alphabetCount[uppers.charAt(i) - 'A']++;
        }
//        System.out.println(Arrays.toString(alphabetCount));
        int max=0;
        int where=0;
        int repeat = 0;
        for(int i=0; i<alphabetCount.length; i++){
            if(max < alphabetCount[i]){
                where = i;
                max = alphabetCount[i];
                repeat = 0;
            }
            else if(max == alphabetCount[i])
                repeat = 1;
        }

        if(repeat != 0)
            System.out.println("?");
        else{
            System.out.println((char)(where + 'A'));
        }
    }
}