import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static String secToTime(int totalsecond){
        int hour = totalsecond / 3600;
        int min = (totalsecond % 3600) / 60;
        int sec = totalsecond % 60;

        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        int hour, min, sec;
        int time1, time2;

        String[] parts = s1.split(":");
        hour = Integer.parseInt(parts[0]);
        min = Integer.parseInt(parts[1]);
        sec = Integer.parseInt(parts[2]);
        time1 = 3600 * hour + 60 * min + sec;

        parts = s2.split(":");
        hour = Integer.parseInt(parts[0]);
        min = Integer.parseInt(parts[1]);
        sec = Integer.parseInt(parts[2]);
        time2 = 3600 * hour + 60 * min + sec;

        int diff = time2 - time1;

        if(diff<=0)
            diff += 3600 * 24;


        System.out.println(secToTime(diff));


    }
}
