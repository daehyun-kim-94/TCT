package Test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Test1 {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        Queue<String> queue = new LinkedList<>();

        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);

        while(true){
            String input = br.readLine();
            String[] arr = input.split(" ");

            if(arr[0].equals("SEND")){
                if(arr.length > 1){
                    queue.add(arr[1]);
                }
            }else if(arr[0].equals("RECEIVE")) {
                System.out.println(queue.poll());
            }
        }
    }


}