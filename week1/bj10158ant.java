import java.io.*;
import java.util.StringTokenizer;

class bj10158ant {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int xlength = Integer.parseInt(st.nextToken());
        int ylength = Integer.parseInt(st.nextToken());
        // Second line: p and q
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        // Third line: t
        int time = Integer.parseInt(br.readLine());
        int xtime, ytime =0;
        xtime = time % (2*xlength);
        ytime = time % (2*ylength);
        
        //x
        int xanswer=x, yanswer = y;
        int dx =1, dy = 1;
        for(int i=0; i<xtime; i++){
            if(xanswer < xlength && xanswer > 0){
                xanswer += dx;
            } else if (xanswer == xlength) {
                dx = -1;
                xanswer += dx;
            }
            else if (xanswer == 0){
                dx = 1;
                xanswer += dx;
            }
        }

        for(int i=0; i<ytime; i++){
            if(yanswer < ylength && yanswer > 0){
                yanswer += dy;
            } else if (yanswer == ylength) {
                dy = -1;
                yanswer += dy;
            }
            else if (yanswer == 0){
                dy = 1;
                yanswer += dy;
            }
        }

        System.out.println(xanswer + " " + yanswer);
    }
}