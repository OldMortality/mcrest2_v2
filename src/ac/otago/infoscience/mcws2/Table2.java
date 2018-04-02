package ac.otago.infoscience.mcws2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

public class Table2 {

	private static Logger logger = Logger
			.getLogger("ac.otago.infoscience.mcws.Table2");

	// 2-D array. One row is for each age group, as in Table 2 of the paper.

	Float[][] theTable = null;

	static Table2 theInstance = null;

	private Table2() {
		super();
	}

	public static Table2 newInstance() {

		logger.info("newInstance");

		if (theInstance == null) {

			logger.info("creating a new instance of Table2");

			theInstance = new Table2();

			theInstance.theTable = new Float[Util.TABLE2_NUM_ROWS][Util.TABLE2_NUM_COLS];

			BufferedReader br = null;

			String path = "table2-v2.csv";
			
			logger.info("reading from " + path);
			

			try {

				InputStream in = theInstance.getClass().getResourceAsStream(
						path);

				in = theInstance.getClass().getClassLoader()
						.getResourceAsStream(path);

				InputStreamReader is = new InputStreamReader(in);

				br = new BufferedReader(is);

				String read = br.readLine();

				logger.info("reading from table2:");

				int rowCount = 0;

				while (read != null) {

					// remove quotes
					read = read.replaceAll("\"", "");

					logger.info("from table2: " + read);

					String[] numbers = read.split(",");

					// skip column 0, the age groups, so women/north becomes
					// column [0]
					for (int i = 1; i < numbers.length; i++) {

						// System.out.println("number " + numbers[i] + "---- " +
						// new Float(numbers[i]));

						theInstance.theTable[rowCount][i - 1] = new Float(
								numbers[i]);

					}

					rowCount++;

					read = br.readLine();

				}
				logger.info("finished reading from table2");
			} catch (IOException e) {
				logger.error("Error while reading from table2", e);

				theInstance = null;

			} finally {
				try {
					if (br != null)
						br.close();
				} catch (IOException ex) {
					logger.info("setting theInstance to NULL again");
					theInstance = null;
					ex.printStackTrace();
				}
			}

		}

		return (theInstance);

	}

	/**
	 * 
	 * @param gender
	 *            male is Util.GENDER_MALE = 1
	 * @param ageGroup
	 *            0 is 20-24, 1 is 25-29, up to 13 85+
	 * @param area
	 *            0 North, 1 Mid 2 Central 3 South
	 *            
	 * so the first row of the csv file would be for age group 0
	 * and columns: 0-4:
	 *   Female North, Female mid, Female central Female south, non melanoma mortality
	 *     columns: 5-9:
	 *   Male   North, Male   mid, Male   central,Male   south, non melanoma mortality
	 *   
	 * @return
	 */
	public float getMelanomaIncidence(int gender, int ageGroup, int area) {

		logger.debug("Getting incidence for gender, agegroup, area " + gender + ", " + ageGroup
				+ ", " + area );
		
		float result = theInstance.theTable[ageGroup][gender * 5 + area]; 
		logger.debug("gender, agegroup, area " + gender + ", " + ageGroup
				+ ", " + area + ". Melanoma incidence is " + result);
		
		return (result);

	}

	/**
	 * Non melanoma mortality rates
	 * 
	 * @param gender
	 * @param ageGroup
	 * @param area
	 * @return
	 */
	public float getMortality(int gender, int ageGroup) {

		float result = theInstance.theTable[ageGroup][4 + gender * 5];
		logger.debug("mortality for gender " + gender + " agegroup " + ageGroup  + " is: " + result );
		return (result);

	}

	/**
	 * test only
	 * @param args
	 */
	public static void main(String[] args) {

		Table2 t = Table2.newInstance();

		int gender = Util.GENDER_FEMALE;
		int ageGroup = 5;
		int area = Util.AREA_SOUTH;

		// System.out.println("[0,1] " + Table2.theInstance.theTable[0][1]);
		float inc = t.getMelanomaIncidence(gender, ageGroup, area);
		System.out.println("incidence: " + inc);

		float m = t.getMortality(gender, ageGroup);
		System.out.println("mortality: " + m);

	}

}
