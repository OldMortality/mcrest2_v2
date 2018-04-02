package ac.otago.infoscience.mcws2;


import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.Properties;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 * 
 * @author michel
 *
 * This is the web service for male patients.
 * 
 *
 */

 

//Sets the path to base URL + /getmelcalc
@Path("/getmelcalcm")
public class GetMelCalcM{

	private static Logger logger = Logger
			.getLogger("ac.otago.infoscience.mcws.GetMelCalcM");

	// @Context javax.servlet.http.HttpServletRequest request
	
	@GET
	@Produces(MediaType.TEXT_XML)
	public Response getXMLHistory(
			@Context javax.servlet.http.HttpServletRequest request, 
			@QueryParam("moles") String molesRarm, 
			@QueryParam("nmsc") String nmsc,
			@QueryParam("occ") String occ,
			@QueryParam("birthplace") String birthplace,
			@QueryParam("eye") String eye,
			@QueryParam("skin") String skin,
			@QueryParam("age") String age,
			@QueryParam("area") String area
			
			) {
		
		logger.info("GetMelCalcM");

		String ipAddress = request.getRemoteHost();
		
		logger.log(Level.INFO, "IP address is: " + ipAddress);
		
		Properties prop = new Properties();
		
		// add this string as a Access-Control-Allow-Origin header
		
		String path = "config.properties";
		
		String allowHeader = "";
		
		try {

			InputStream is =
					  this.getClass().getClassLoader().getResourceAsStream(path);
			
			prop.load(is);
			
			allowHeader = prop.getProperty("allow_header");
			
			
			
		}
			
		catch( Exception e) {
			
			logger.error("Error loading the properties file " + path,e);
		
		}
		

		// validate input, and convert the input parameters (Strings) to int[] 
		int[] parms = Util.validateMale(molesRarm,nmsc,occ,birthplace, eye, skin, age,area);
		
		
		String xml;	
		if (parms == null) {
			// invalid input
			
			xml = "<?xml version=\"1.0\"?><MR><RISK>-1</RISK><DESCRIPTION>Invalid input</DESCRIPTION></MR>";
			
			
		} else {
			
			// payload
			
			
			float value = (float) Calculator.calculateMaleFears(parms);
			 
			
			xml = Util.riskToXML(value);
		
		}

		try {

			
			// Response headers, so e-referral system can call this from the
			// browser.
			ResponseBuilder builder = Response.ok(xml);
			
			if (allowHeader != null && allowHeader.length() > 0 ) {
				
				builder.language("en").header("Access-Control-Allow-Origin",
					allowHeader);
		
			}
			
			return builder.build();

		} catch (Exception e) {
			
			ResponseBuilder builder = Response.serverError();

			if (allowHeader != null && allowHeader.length() > 0 ) {
				
				builder.language("en").header("Access-Control-Allow-Origin",
					allowHeader);
		
			} 
			
			builder.language("en").header("Access-Control-Allow-Origin",allowHeader);
			
			return builder.build();

		}

	}
	
 

}
