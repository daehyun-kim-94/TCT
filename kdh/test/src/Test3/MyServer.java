package Test3;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletHandler;

import java.io.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServer {
    public static void main(String[] args) throws Exception {
        // Http Server Start
        QueueHttpServer queueHttpServer = new QueueHttpServer();
        Thread threadHttp = new Thread(queueHttpServer);
        threadHttp.start();

        Scanner scanner = new Scanner(System.in);
        String line;
        while ((line = scanner.nextLine()) != null) {
            if (line.equals("QUIT")) {
                queueHttpServer.quit();
                break;
            } else {
                System.out.println("If you want to quit, input \"QUIT\"");
            }
        }
    }


}
class QueueHttpServer extends Thread {

    Server server;

    public void run() {
        server = new Server();
        ServerConnector http = new ServerConnector(server);
        http.setHost("127.0.0.1");
        http.setPort(8080);
        server.addConnector(http);

        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(MyServlet.class, "/*");
        server.setHandler(servletHandler);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void quit()
    {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class MyQueue {
    String name;
    ReentrantLock lock = new ReentrantLock();
    int max;
    int size;
    int index;

    Queue<MyMessage> queue = new LinkedList<>();
    ArrayList<MyMessage> newQueue = new ArrayList<>();

    public MyQueue (String name, int max){
        this.name = name;
        this.max = max;
        this.index = 0;
    }

    public boolean isFull(){
        if((newQueue.size() - index) == max){
            return true;
        }else{
            return false;
        }
    }

    public boolean isEmpty(){
        if((newQueue.size() - (index + 1)) == 0){
            return true;
        }else{
            return false;
        }
    }
    public void add(String str){
        if((newQueue.size() - index) == max){
            //System.out.println("Queue Full");
        }else{
            newQueue.add(new MyMessage(str));
            index++;
        }
    }
    public MyMessage peek(){
        if(isEmpty()){
            return null;
        }else{
            lock.lock();
            MyMessage msg = newQueue.get(index);
            //index++;
            return msg;
        }
    }
    public String poll(){
        if(isEmpty()){
            return "No Message";
        }else{
            String msg = newQueue.get(index).getMessage();
            index++;
            lock.unlock();
            return msg;
        }
    }
}

class MyMessage{

    //public ReentrantLock lock = new ReentrantLock();
    public String messageId = UUID.randomUUID().toString();
    public String message;

    public MyMessage (String message){
        this.message = message;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        //lock.lock();
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}