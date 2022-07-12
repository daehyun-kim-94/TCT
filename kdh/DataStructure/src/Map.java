import java.io.*;
import java.util.HashMap;

public class Map {
    public static void main(String[] args) {

        System.out.println("Hello world!");

        HashMap<String, Effort> map = new HashMap<>();

        try {

            BufferedReader br = new BufferedReader(new FileReader(".\\DS_Sample2.csv"));

            String line;
            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                String id = arr[1];

                if(!map.containsKey(id)){
                    Effort effort = new Effort(arr[2]);
                    map.put(arr[1], effort);
                }

                map.get(id).pjt_a += Double.parseDouble(arr[3]);
                map.get(id).pjt_b += Double.parseDouble(arr[4]);
                map.get(id).pjt_c += Double.parseDouble(arr[5]);

                line = br.readLine();
            }
            br.close();

            for (String id: map.keySet())
            {
                Double sum = map.get(id).pjt_a + map.get(id).pjt_b +map.get(id).pjt_c;
                System.out.println(String.format("%s	%s	%.1f	%.1f	%.1f	->	%.1f", id, map.get(id).name, map.get(id).pjt_a, map.get(id).pjt_b, map.get(id).pjt_c, sum));
            }

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}

class Effort {
    public String name;
    public Double pjt_a;
    public Double pjt_b;
    public Double pjt_c;

    public Effort(String name) {
        this.name = name;
        this.pjt_a = 0.0;
        this.pjt_b = 0.0;
        this.pjt_c = 0.0;
    }
    public Effort(String name, Double pjt_a, Double pjt_b, Double pjt_c) {
        this.name = name;
        this.pjt_a = pjt_a;
        this.pjt_b = pjt_b;
        this.pjt_c = pjt_c;
    }
}