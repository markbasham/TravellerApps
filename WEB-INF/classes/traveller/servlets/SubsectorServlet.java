package traveller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import traveller.subsector.Subsector;
import traveller.web.SubsectorBuilder;

public class SubsectorServlet extends HttpServlet {

	private static final long serialVersionUID = -1062764098318453725L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Date thisDate = new Date();
		log("SubsectorServlet initialized at " + thisDate);
	}
	
	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse responce) throws ServletException, IOException {

		PrintWriter out = responce.getWriter();
		out.println("<html><head><title>A Test</title></head><body>");

		Subsector subsector = ServletUtils.extractSubsector(request, this);
		
		try {
			out.println("<h1>"+subsector.getName()+"</h1>");
			out.println(SubsectorBuilder.buildArray(subsector,null));
			out.println(SubsectorBuilder.buildList(subsector));
		} catch (Exception e) {
			out.println("<h1>No Subsector information available</h1>");
		}
		
		out.println("</body>");

	}

	
	@Override
	public void destroy() {
		Date thisDate = new Date();
		log("SubsectorServlet destroyed at " + thisDate);
		super.destroy();
	}

}
