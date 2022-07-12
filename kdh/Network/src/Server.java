import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Date;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hello world!");

        ServerThread th = new ServerThread();
        th.start();

        InputStream in = System.in;
        InputStreamReader reader = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(reader);
        String str;

        while (true)
        {
            str = br.readLine();

            if (str.equals("QUIT"))
            {
                th.listener.close();
                th.join();
                break;
            }
        }

    }
}

class ServerThread extends Thread implements Runnable{
    ServerSocket listener = null;

    @Override
    public void run() {

        final int BUF_SIZE = 4096;
        byte[] buffer = new byte[BUF_SIZE];

        try {
            File destFolder = new File("./ServerFiles");
            if (!destFolder.exists()) {
                destFolder.mkdir();
            }

            try {
                listener = new ServerSocket(9090);
                while (true) {
                    Socket socket = listener.accept();
                    DataInputStream is = new DataInputStream(socket.getInputStream());
                    try {
                        String fileName = null;
                        // 파일 이름 수신
                        while ((fileName = is.readUTF()) != null) {
                            // 파일 크기 수신
                            int fileSize = is.readInt();
                            int length;

                            FileOutputStream fw = new FileOutputStream("./ServerFiles/" + fileName);

                            while (fileSize > 0) {
                                // 파일 내용 수신
                                length = is.read(buffer, 0, Math.min(fileSize, buffer.length));
                                fileSize -= length;
                                fw.write(buffer, 0, length);
                            }

                            fw.close();
                            System.out.println(fileName + " is received.");
                        }
                    }
                    catch (EOFException e) {
                        System.out.println("Finish Recieve...");
                        socket.close();
                    }catch (Exception e) {
                        System.out.println(e.getMessage());
                        socket.close();
                    }

                }
            }
            finally {
                listener.close();
            }
        }catch (Exception e){

        }
    }
}