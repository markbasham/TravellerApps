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
import traveller.web.TradeBuilder;
import traveller.world.World;

public class JumpTrade extends HttpServlet {

	private static final long serialVersionUID = 5193847341663858587L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Date thisDate = new Date();
		log("JumpTrade initialized at " + thisDate);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse responce)
	throws ServletException, IOException {

		Date thisDate = new Date();
		log("JumpTrade service called at " + thisDate);

		// set up the world
		World world = ServletUtils.extractWorldInformation(request,this);

		responce.setContentType("text/html");
		PrintWriter out = responce.getWriter();
		out.println("<html><head><title>"+world.getName()+"</title></head><body>");

		int jumpDistance = -1;
		try {
			jumpDistance = ServletUtils.extractJumpDistance(request,this);
			Subsector jump = ServletUtils.extractJumpList(jumpDistance,request,this);
			out.println(TradeBuilder.buildSaleComparisonTable(world, jump));
		} catch (Exception e) {
			out.println("No Jump "+jumpDistance+" Destinations<br>");
			e.printStackTrace(out);
		}

		out.println("</body></html>");
		out.close();
	}

	@Override
	public void destroy() {
		Date thisDate = new Date();
		log("JumpTrade destroyed at " + thisDate);
		super.destroy();
	}

}
