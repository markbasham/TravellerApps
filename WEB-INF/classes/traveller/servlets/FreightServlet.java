package traveller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import traveller.trade.generators.Freight;
import traveller.trade.generators.Passengers;
import traveller.web.FreightBuilder;
import traveller.web.PassengerBuilder;
import traveller.world.World;

public class FreightServlet extends HttpServlet {

	private static final long serialVersionUID = -769507735040144905L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Date thisDate = new Date();
		log("FreightServlet initialized at " + thisDate);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse responce)
			throws ServletException, IOException {
		
		Date thisDate = new Date();
		log("FreightServlet service called at " + thisDate);
		
		// set up the world
		World world = ServletUtils.extractWorldInformation(request,this);
		Passengers passengers = ServletUtils.extractPassengerInformation(request, this);
		Freight freight = ServletUtils.extractFreightInformation(request,this);
		
		responce.setContentType("text/html");
		PrintWriter out = responce.getWriter();
		out.println("<html><head><title>"+world.getName()+"</title></head><body>");
		
		try {
			out.println(PassengerBuilder.build(passengers));
			out.println("<br>");
			out.println(FreightBuilder.build(freight));
		} catch (Exception err) {
			out.println("An Error has been detected.<br>");
		}
		
		out.println("</body></html>");
		out.close();

	}

	@Override
	public void destroy() {
		Date thisDate = new Date();
		log("FreightServlet destroyed at " + thisDate);
		super.destroy();
	}
	
}
