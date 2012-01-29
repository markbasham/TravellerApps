package traveller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import traveller.web.WorldBuilder;
import traveller.world.World;

public class WorldBasicInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 5193847341663858587L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Date thisDate = new Date();
		log("WorldBasicInfoServlet initialized at " + thisDate);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse responce)
			throws ServletException, IOException {
		
		Date thisDate = new Date();
		log("WorldBasicInfoServlet service called at " + thisDate);
		
		// set up the world
		World world = ServletUtils.extractWorldInformation(request,this);
		
		responce.setContentType("text/html");
		PrintWriter out = responce.getWriter();
		out.println("<html><head><title>"+world.getName()+"</title></head><body>");
		
		try {
			out.println(WorldBuilder.build(world));
		} catch (Exception err) {
			out.println("An Error has been detected.<br>");
		}
		
		out.println("</body></html>");
		out.close();
	}
	
	@Override
	public void destroy() {
		Date thisDate = new Date();
		log("WorldBasicInfoServlet destroyed at " + thisDate);
		super.destroy();
	}
	
}
