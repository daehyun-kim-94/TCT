import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Que {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        HashMap<String, SizeQue> map = new HashMap<>();

        Scanner sc = new Scanner(System.in);

        while(true) {
            String line = sc.nextLine();
            String cmd = line.split(" ")[0];
            String name = line.split(" ")[1];

            switch (cmd) {
                case "CREATE" :
                    int size = Integer.parseInt(line.split(" ")[2]);
                    if(map.containsKey(name)){
                        System.out.println("Queue Exist");
                        break;
                    }

                    SizeQue q = new SizeQue(size);
                    map.put(name, q);
                    System.out.println("Queue Created");

                    break;
                case "ENQUEUE" :
                    String msg = line.split(" ")[2];
                    if(map.get(name).max_size == map.get(name).queue.size()){
                        System.out.println("Queue Full");
                        break;
                    }
                    map.get(name).queue.add(msg);
                    System.out.println("Enqueued");

                case "DEQUEUE" :
                    if(map.get(name).queue.size() == 0){
                        System.out.println("Queue Empty");
                    }


                    break;
                case "GET" :
                    break;
                case "SET" :
                    break;
                case "DEL" :
                    break;
            }
        }

    }
}

class SizeQue {
    public Queue<String> queue = new LinkedList<>();
    public int max_size = 0;
    public int id;
    public static int st = 0;

    public SizeQue(int size) {
        this.max_size = size;
    }
}
