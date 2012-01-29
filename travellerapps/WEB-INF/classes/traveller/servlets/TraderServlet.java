package traveller.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import traveller.trade.generators.Trade;
import traveller.web.TradeBuilder;

public class TraderServlet extends HttpServlet {

	private static final long serialVersionUID = -2443714827709887609L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		Date thisDate = new Date();
		log("TraderServlet initialized at " + thisDate);
	}
	
	@Override
	public void service(HttpServletRequest request, HttpServletResponse responce)
			throws ServletException, IOException {
		
		Date thisDate = new Date();
		log("TraderServlet service called at " + thisDate);
		
		// get the trader information
		Trade trade = ServletUtils.extractTradeInformation(request,this);
		
		responce.setContentType("text/html");
		PrintWriter out = responce.getWriter();
		out.println("<html><head><title>Trade for "+trade.getCurrent().getName()+"</title></head><body>");

		try {
			out.println(TradeBuilder.build(trade));
		} catch (Exception err) {
			out.println("An Error has been detected.<br>");
		}
		
		out.println("</body></html>");
		out.close();

	}
	
	public void destroy() {
		Date thisDate = new Date();
		log("TraderServlet destroyed at " + thisDate);
		super.destroy();
	}

}
