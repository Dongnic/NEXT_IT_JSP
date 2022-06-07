package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Sum extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print("<html><body>");
		
		out.print("<ul>");
		int sum=0;
		for(int i = 1; i <=10; i++){
				sum+=i;
				out.print("<li>1부터 "+ i +"까지 합 : "+sum+"</li>");
		}
		out.print("</ul>");
		out.print("</body></html>");
	}
}
