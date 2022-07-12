package Test3;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class MyServlet extends HttpServlet {

    /** Queue **/
    HashMap<String, MyQueue> map = new HashMap<>();

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Request : "+ req.getRequestURL());

        /////////////////////////////////////////////////
        Gson gson = new Gson();
        JsonObject resJson = new JsonObject();

        String [] words = req.getPathInfo().toString().split("/");
        String command = words[1];
        String queueName = words[2];
        String messageId = null;
        String message = null;
        String result;

        switch(command) {
            case "RECEIVE":{
                if(!map.containsKey(queueName)){
                    result = "No Message";
                }else{
                    if(map.get(queueName).queue.isEmpty()){
                        result = "No Message";
                    }else{
                        message = map.get(queueName).peek().getMessage();
                        messageId = map.get(queueName).peek().getMessageId();
                        result = "Ok";
                    }
                }
                resJson.addProperty("Result", result);
                resJson.addProperty("Message Id", messageId);
                resJson.addProperty("Message", message);
                break;
            }
        }
        res.setStatus(200);
        res.setContentType("application/json");
        res.getWriter().print(resJson.toString());
        res.getWriter().flush();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        System.out.println("Request : "+ req.getRequestURL());
        ////////////////////////////////////////////////
        /** Req Input Json 변환 **/
        Gson gson = new Gson();
        JsonObject resJson = new JsonObject();

        BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()));

        String buffer;
        StringBuilder sb = new StringBuilder();

        while ((buffer = input.readLine()) != null) {
            sb.append(buffer + "\n");
        }

        String strBody = sb.toString();
        input.close();
        JsonObject jsonBody = gson.fromJson(strBody, JsonObject.class);
        int queueSize;
        String message;

        /*****************************************/
        /////////////////////////////////////////////////

        String [] words = req.getPathInfo().toString().split("/");
        String command = words[1];
        String queueName = words[2];
        String result;

        switch(command) {
            case "CREATE":{
                if(map.containsKey(queueName)){
                    result = "Queue Exist";
                }else{
                    queueSize = Integer.parseInt(jsonBody.get("QueueSize").getAsString());
                    MyQueue queue = new MyQueue(queueName, queueSize);
                    map.put(queueName, queue);
                    result = "Ok";
                }
                resJson.addProperty("Result", result);
                break;
            }
            case "SEND":{
                if(map.containsKey(queueName)){
                    if(map.get(queueName).isFull()){
                        result = "Queue Full";
                    }else{
                        message = jsonBody.get("Message").getAsString();
                        map.get(queueName).add(message);
                        result = "Ok";
                    }
                    resJson.addProperty("Result", result);
                    break;
                }
            }
            case "ACK":{
                if(map.containsKey(queueName)){

                    map.get(queueName).poll();
                    result = "Ok";
                    resJson.addProperty("Result", result);
                    break;
                }
            }
            case "FAIL":{

            }
        }

        res.setStatus(200);
        res.setContentType("application/json");
        res.getWriter().print(resJson.toString());
        res.getWriter().flush();
    }
}
