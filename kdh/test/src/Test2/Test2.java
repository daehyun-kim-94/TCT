package Test2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class Test2 {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        HashMap<String, MyQueue> map = new HashMap<>();

        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);

        while(true){
            String input = br.readLine();
            String[] arr = input.split(" ");

            if(arr[0].equals("CREATE")) {
                if(arr.length > 2){
                    String name = arr[1];
                    int size = Integer.parseInt(arr[2]);

                    if(map.containsKey(name)){
                        System.out.println("Queue Exist");
                        continue;
                    }

                    MyQueue queue = new MyQueue(name, size);
                    map.put(name, queue);
                }
            }
            else if(arr[0].equals("SEND")){
                if(arr.length > 2){
                    String name = arr[1];
                    String val = arr[2];

                    if(!map.containsKey(name)){
                        System.out.println("Queue Not Exist");
                        continue;
                    }
                    map.get(name).add(val);
                }
            }else if(arr[0].equals("RECEIVE")) {
                if(arr.length > 1){
                    String name = arr[1];

                    if(!map.containsKey(name)){
                        System.out.println("Queue Not Exist");
                        continue;
                    }

                    System.out.println(map.get(name).poll());
                }
            }
        }
    }
}

class MyQueue {
    String name;
    int size;
    Queue<String> queue = new LinkedList<>();

    public MyQueue (String name, int size){
        this.name = name;
        this.size = size;
    }

    public void add(String str){
        if(queue.size() == size){
            System.out.println("Queue Full");
        }else{
            queue.add(str);
        }
    }

    public String poll(){
        if(queue.isEmpty()){
            return "";
        }else{
            return queue.poll();
        }
    }
}