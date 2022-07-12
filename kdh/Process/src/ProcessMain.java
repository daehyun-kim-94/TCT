import com.sun.source.tree.WhileLoopTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class ProcessMain {

    public static String getProcessOutput (List<String> cmdList ) throws IOException, InterruptedException
    {
        ProcessBuilder builder = new ProcessBuilder(cmdList);
        Process process = builder.start();
        InputStream psout = process.getInputStream();
        byte[] buffer = new byte[1024];
        int len = psout.read(buffer);
        return (new String(buffer, 0, len));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new FileReader(".\\NUM.TXT"));

        String line;
        while((line = br.readLine()) != null){

            int first = Integer.parseInt(line.split(" ")[0]);
            int second = Integer.parseInt(line.split(" ")[1]);
            Thread thread1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    String output = null;
                    try {

                        output = getProcessOutput(Arrays.asList ("add_2sec.exe",Integer.toString(first),Integer.toString(second)));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(output);
                }
            });
            thread1.start();

        }


    }
}
