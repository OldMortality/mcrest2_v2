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
 */

/**
 * 
 * @author michel
 * 
 *         This is the web service for female patients.
 * 
 * 
 * 
 */
// Sets the path to base URL + /getmelcalc
@Path("/getmelcalcf")
public class GetMelCalcF {

	private static Logger logger = Logger
			.getLogger("ac.otago.infoscience.mcws.GetMelCalcF");

	/*
	 * This is the one for females
	 */
	@GET
	@Produces(MediaType.TEXT_XML)
	public Response getXMLHistory(
			@Context javax.servlet.http.HttpServletRequest request,
			@QueryParam("occ") String occ,
			@QueryParam("hair") String hair,
			@QueryParam("freckles") String freckles,
			@QueryParam("moles") String moles,
			@QueryParam("famhxmoles") String famMHXMoles,
			@QueryParam("nmsc") String nmsc, 
			@QueryParam("age") String age,
			@QueryParam("area") String area

	) {

		logger.info("getMelCalcf");

		String ipAddress = request.getRemoteHost();

		logger.log(Level.INFO, "IP address is: " + ipAddress);

		Properties prop = new Properties();

		String path = "config.properties";

		String allowHeader = "";

		try {

			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(path);

			prop.load(is);

			allowHeader = prop.getProperty("allow_header");

		}

		catch (Exception e) {

			logger.error("Error loading the properties file " + path, e);

		}

		// validate input, and convert the input parameters (Strings) to int[]
		int[] parms = Util.validateFemale(occ,hair,freckles,moles,famMHXMoles, nmsc, age, area);

		String xml;
		if (parms == null) {
			// invalid input

			xml = "<?xml version=\"1.0\"?><MR><RISK>-1</RISK><DESCRIPTION>Invalid input</DESCRIPTION></MR>";

		} else {

			// payload

			float value = (float) Calculator.calculateFemaleFears(parms);

			xml = Util.riskToXML(value);

		}

		try {

			// Response headers, so e-referral system can call this from the
			// browser.
			ResponseBuilder builder = Response.ok(xml);

			if (allowHeader != null && allowHeader.length() > 0) {

				builder.language("en").header("Access-Control-Allow-Origin",
						allowHeader);

			}

			return builder.build();

		} catch (Exception e) {

			ResponseBuilder builder = Response.serverError();

			if (allowHeader != null && allowHeader.length() > 0) {

				builder.language("en").header("Access-Control-Allow-Origin",
						allowHeader);

			}

			builder.language("en").header("Access-Control-Allow-Origin",
					allowHeader);

			return builder.build();

		}

	}
}
