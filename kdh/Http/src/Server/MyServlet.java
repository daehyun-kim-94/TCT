package Server;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.time.LocalTime;
import java.util.Date;

public class MyServlet extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Request : "+ req.getRequestURL());
		
		res.setStatus(200);
		res.getWriter().write(new Date().toString());
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("Request : "+ req.getRequestURL());
		////////////////////////////////////////////////
		File destFolder = new File("./OUTPUT");
		if(!destFolder.exists()) {
		    destFolder.mkdirs(); 
		}
		
		LocalTime currentTime = LocalTime.now();
		String fname = String.format("./OUTPUT/%02d%02d%02d.json", currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
	    PrintWriter printWriter = new PrintWriter(new FileWriter(new File(fname)));
	    
        BufferedReader input = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String buffer;
        while ((buffer = input.readLine()) != null) {
        	printWriter.print(buffer);
        }        
		input.close();		
		printWriter.close();
		/////////////////////////////////////////////////
		
		res.setStatus(200);
		res.getWriter().write(fname + " saved!");
	}
}
