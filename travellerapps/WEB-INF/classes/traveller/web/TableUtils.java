package traveller.web;

import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.mla.html.table.Cell;

public class TableUtils {

	public static final String TITLE = "title";
	public static final String HEADING = "heading";
	public static final String DESCRIPTOR = "desc";
	public static final String VALUE = "value";
	
	// For sector setup
	public static final String EMPTY = "empty";
	public static final String TOP = "top";
	public static final String BOTTOM = "bottom";
	public static final String BASIC = "basic";
	public static final String GASGIANT = "gasgiant";

	static VelocityEngine ve = null;
	static Template tableTemplate = null;
	static Template sectorTableTemplate = null;

	public static Cell cellGen(String content, String type) {
		return cellGen(content, type, 1, 1);
	}

	public static Cell cellGen(String content, String type, int rowSpan, int colSpan) {
		Cell cell = new Cell(content, rowSpan, colSpan);
		cell.setType(type);
		return cell;
	}

	public static Template getTableTemplate() throws Exception {
		if (tableTemplate == null) {
			// get the velocity engine
			if (ve == null) {
				ve = new VelocityEngine();
				ve.setProperty("file.resource.loader.class", ClasspathResourceLoader.class.getName());
				ve.init();
			}

			// grab the right template
			tableTemplate = ve.getTemplate( "traveller/templates/table.vm" );
		}
		return tableTemplate;
	}
	
	public static Template getSectorTableTemplate() throws Exception {
		if (sectorTableTemplate == null) {
			// get the velocity engine
			if (ve == null) {
				ve = new VelocityEngine();
				ve.setProperty("file.resource.loader.class", ClasspathResourceLoader.class.getName());
				ve.init();
			}

			// grab the right template
			sectorTableTemplate = ve.getTemplate( "traveller/templates/sectortable.vm" );
		}
		return sectorTableTemplate;
	}



}
