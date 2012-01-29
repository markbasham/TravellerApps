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
import traveller.web.CurrentBuilder;
import traveller.web.TradeBuilder;
import traveller.world.World;

public class WorldServlet extends HttpServlet {

	private static final long serialVersionUID = 5193847341663858587L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Date thisDate = new Date();
		log("WorldServlet initialized at " + thisDate);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse responce)
			throws ServletException, IOException {
		
		Date thisDate = new Date();
		log("WorldServlet service called at " + thisDate);
		
		// set up the world
		World world = ServletUtils.extractWorldInformation(request,this);
		
		responce.setContentType("text/html");
		PrintWriter out = responce.getWriter();
		out.println("<html><head><title>"+world.getName()+"</title></head><body>");
		
		// add in a link back to the main subsector page
		out.println("<a href='SubsectorServlet'>back to subsector</a>");
		
		// simple link for trader information
		out.println(String.format("<a href='TraderServlet?worldLocation=%s'>trader information</a>",world.getLocation().toString()));
		
		try {
			out.println(CurrentBuilder.build(world,0));
		} catch (Exception err) {
			out.println("An Error has been detected.<br>");
		}
		
		for(int i = 1; i < 7; i++) {
			try {
				Subsector jump = ServletUtils.extractJumpList(i,request,this);
				out.println(TradeBuilder.buildSaleComparisonTable(world, jump));
			} catch (Exception e) {
				out.println("No Jump "+i+" Destinateions<br>");
				//e.printStackTrace(out);
			}
		}
		
		out.println("</body></html>");
		out.close();
	}
	
	@Override
	public void destroy() {
		Date thisDate = new Date();
		log("WorldServlet destroyed at " + thisDate);
		super.destroy();
	}
	
}
