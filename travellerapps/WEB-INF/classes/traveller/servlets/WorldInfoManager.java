package traveller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import traveller.web.LinkUtils;
import traveller.world.World;

public class WorldInfoManager extends HttpServlet {

	private static final long serialVersionUID = 5193847341663858587L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Date thisDate = new Date();
		log("WorldInfoManager initialized at " + thisDate);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse responce)
			throws ServletException, IOException {
		
		Date thisDate = new Date();
		log("WorldInfoManager service called at " + thisDate);
		
		// set up the world
		World world = ServletUtils.extractWorldInformation(request,this);	
		
		responce.setContentType("text/html");
		PrintWriter out = responce.getWriter();
		out.println("<html><head><title>"+world.getName()+"</title></head><body>");
		
		// add in a link back to the main subsector page
		out.println("<b>"+world.getName()+"</b><br>");
		
		// simple link for trader information
		out.println(LinkUtils.generateWorldUPPInfoLink(world, "Info", "world"));
		
		// simple link for trader information
		out.println(LinkUtils.generateJumpTradeLink(world, "J1 Trade", "world", 1));
		
		// simple link for trader information
		out.println(LinkUtils.generateJumpTradeLink(world, "J2 Trade", "world", 2));
		
		// simple link for trader information
		out.println(LinkUtils.generateJumpTradeLink(world, "J3 Trade", "world", 3));
		
		// simple link for trader information
		out.println(LinkUtils.generateJumpTradeLink(world, "J4 Trade", "world", 4));
		
		// simple link for trader information
		out.println(LinkUtils.generateJumpTradeLink(world, "J5 Trade", "world", 5));
		
		// simple link for trader information
		out.println(LinkUtils.generateJumpTradeLink(world, "J6 Trade", "world", 6));
		
		// simple link for trader information
		out.println(LinkUtils.generateTraderLink(world, 1, "Trader 1", "world"));
		
		// simple link for trader information
		out.println(LinkUtils.generateTraderLink(world, 2, "Trader 2", "world"));
		
		// simple link for trader information
		out.println(LinkUtils.generateTraderLink(world, 3, "Trader 3", "world"));
		
		
		
		out.println("</body></html>");
		out.close();
	}
	
	@Override
	public void destroy() {
		Date thisDate = new Date();
		log("WorldInfoManager destroyed at " + thisDate);
		super.destroy();
	}
	
}
