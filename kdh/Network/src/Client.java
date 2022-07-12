import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.*;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9090);
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());

        byte[] buffer = new byte[4096];
        int length;

        File directory = new File("./ClientsFiles");

        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {

                os.writeUTF(file.getName());
                os.writeInt((int) file.length());
                FileInputStream is = null;

                try {
                    is = new FileInputStream(file.getPath());

                    while ((length = is.read(buffer)) != -1) {
                        os.write(buffer, 0, length);
                    }
                } finally {
                    if (is != null) {
                        is.close();
                    }
                }
            }
        }
    }
}
