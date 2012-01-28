package traveller.servlets.frames;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import traveller.servlets.ServletUtils;
import traveller.subsector.Subsector;
import traveller.web.SubsectorBuilder;

public class FramedSubsectorServlet extends HttpServlet {

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

		try {
			ServletUtils.resetSubsectorBeanData(request, this);
		} catch (Exception e) {
			// Do nothing, as its probably starting off.
		}
			
		PrintWriter out = responce.getWriter();

		Subsector subsector = ServletUtils.extractSubsector(request, this);
		
		out.println("<html><head><title>"+subsector.getName()+"</title></head><body>");
		
		try {
			out.println(SubsectorBuilder.buildSectorArray(subsector,"world"));
		} catch (Exception e) {
			out.println("<h1>No Subsector information available</h1>");
			e.printStackTrace(out);
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
