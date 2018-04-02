package ac.otago.infoscience.mcws2;

import java.text.DecimalFormat;

import javax.tools.ToolProvider;

import org.apache.log4j.Logger;

public class Util {

	public static final int TABLE2_NUM_ROWS = 14;
	public static final int TABLE2_NUM_COLS = 10;
	
	public static final int NUMBER_OF_RISK_FACTORS_M = 16;
	
	
	public static final int NUMBER_OF_RISK_FACTORS_F = 16;
	

	
	// which of the parameters contains the age. This is 
	// the index in the parms array
	public static final int AGE_INDEX_M = 6;
	public static final int AREA_INDEX_M = 7;
	
	public static final int AGE_INDEX_F = 6;
	public static final int AREA_INDEX_F = 7;

	
	public static final int MAX_DIGITS = 4;

	public static int GENDER_FEMALE = 0;
	public static int GENDER_MALE = 1;

	public static int AREA_NORTH = 0;
	public static int AREA_MIDLAND = 1;
	public static int AREA_CENTRAL = 2;
	public static int AREA_SOUTH = 3;

	public static String AREA_NORTH_STRING = "NORTH";
	public static String AREA_MIDLAND_STRING = "MIDLAND";
	public static String AREA_CENTRAL_STRING = "CENTRAL";
	public static String AREA_SOUTH_STRING = "SOUTH";

	public static String FRECKLES_NONE_STR = "NONE";
	public static String FRECKLES_FEW_STR = "FEW";
	public static String FRECKLES_MANY_STR = "MANY";
	
	
	// variables for women. These ints match the index on 
	// table3_women.csv
	public static int F_OCC_IN_OUT = 0;
	public static int F_OCC_OUT = 1;
	public static int F_OCC_IN = 2;
	public static int F_HAIR_BLACK = 3;
	public static int F_HAIR_FAIR = 4;
	public static int F_FRECKLES_NONE = 5;
	public static int F_FRECKLES_FEW = 6;
	public static int F_FRECKLES_MANY = 7;
	public static int F_MOLES_0 = 8;
	public static int F_MOLES_1 = 9;
	public static int F_MOLES_2 = 10;
	public static int F_FAMHXMOLES_NO = 11;
	public static int F_FAMHXMOLES_YES = 12;
	public static int F_FAMHXMOLES_DONTKNOW = 13;
	public static int F_NMSC_NO = 14;
	public static int F_NMSC_YES = 15;
	
	 
	
	public static String SKINCOLOUR_OLIVE_STR = "OLIVE";
	public static String SKINCOLOUR_MEDIUM_STR = "MEDIUM";
	public static String SKINCOLOUR_FAIR_STR = "FAIR";
	
	 
	
	// variables for men
	public static int M_MOLES_0 = 0;
	public static int M_MOLES_1 = 1;
	public static int M_MOLES_2 = 2;
	public static int M_NMSC_NO = 3;
	public static int M_NMSC_YES = 4;
	public static int M_OCCUPATION_INOUT = 5;
	public static int M_OCCUPATION_OUT = 6;
	public static int M_OCCUPATION_IN = 7;
	
	public static int M_BIRTH_NOTNZ = 8;
	public static int M_BIRTH_NZ = 9;
	public static int M_EYECOLOUR_BROWN = 10;
	public static int M_EYECOLOUR_GREEN = 11;
	public static int M_EYECOLOUR_GREY = 12;
	public static int M_SKINCOLOUR_OLIVE = 13;
	public static int M_SKINCOLOUR_MEDIUM = 14;
	public static int M_SKINCOLOUR_FAIR = 15;
	
	
	public static String OCCUPATION_IN_STR = "IN";
	public static String OCCUPATION_INOUT_STR = "INOUT";
	public static String OCCUPATION_OUT_STR = "OUT";
	public static String EYE_BROWN_STR = "BROWN";
	public static String EYE_GREEN_STR = "GREEN";
	public static String EYE_GREY_STR = "GREY";
	public static String BIRTH_NOTNZ_STR = "NOT_NZ";
	public static String BIRTH_NZ_STR = "NZ";
	public static String HAIR_BLACK_STR = "BLACK";
	public static String HAIR_FAIR_STR = "FAIR";
	
	
	
	
	// these are 2 adjustment factors,
	public static float ADJ_FEMALE = 1- 0.93416752f;
	public static float ADJ_MALE = 1 - 0.944828426f;

	private static Logger logger = Logger.getLogger("ac.otago.infoscience.mcws.Util");

	/**
	 * validate parameters for a woman. All parameters are case insensitive.
	 * 
	 * @param occupation
	 *            "in_out","in","out"
	 * @param hair
	 * 	          "black","fair"           
	 * @param freckles
	 *            "none","few","many"
	 * @param moles
	 *            0,1,2
	 * @param famHXMoles           
	 *            "no","yes","dont_know"  
	 * @param nmsc
	 *            "no","yes"
	 * @param age
	 *            an integer greater than 19
	 * @param area
	 *            "north","midland","central","south"
	 * 
	 * @return int[] representing the parameters, or null if the input is
	 *         invalid. These integers follow table 3_women.csv, starting
	 *         at zero.
	 * 
	 *         Hence, a woman with occupation=in, hair=black, freckles=none.
	 *         moles=2, e5=1, nmsc=yes"
	 *         would return: {2,3,5,10,12,15,Util.AREA_SOUTH=3}
	 * 
	 */
	static int[] validateFemale(String occ, String hair, String freckles, String moles, 
			String famHXMoles, String nmsc, String age,
			String area) {

		logger.debug("validate Female " + occ + "-" + hair + "-" + freckles + "-" +
		moles +"-"+famHXMoles + "-"+nmsc + "-" + age + "-" + area);
		boolean validInput = true;
		
		int[] result = null;

		if (occ == null) {
			logger.debug("missing parameter occ");
			return (result);
		}
		if (hair == null) {
			logger.debug("missing parameter hair");
			return (result);
		}
		if (freckles == null) {
			logger.debug("missing parameter freckles");
			return (result);
		}
		if (moles == null) {
			logger.debug("missing parameter moles");
			return (result);
		}
		if (famHXMoles == null) {
			logger.debug("missing parameter famHXMoles");
			return (result);
		}
		if (nmsc == null) {
			logger.debug("missing parameter nmsc");
			return (result);
		}
		if (age == null) {
			logger.debug("missing parameter age");
			return (result);
		}
		if (area == null) {
			logger.debug("missing parameter area");
			return (result);
		}

		int occInt = -1;
		if (validInput) {
			if (occ.toUpperCase().equals(Util.OCCUPATION_IN_STR)) {
				occInt = Util.F_OCC_IN;
			} else {
				if (occ.toUpperCase().equals(Util.OCCUPATION_INOUT_STR)) {
					occInt = Util.F_OCC_IN_OUT;
				} else {
					if (occ.toUpperCase().equals(Util.OCCUPATION_OUT_STR)) {
						occInt = Util.F_OCC_OUT;
					} else {
						validInput = false;
						logger.debug("occ should be IN, OUT or INOUT. got " + occ);

					}
				}
			}
		}
		
		
		int hairInt = -1;
		if (hair.toUpperCase().equals(Util.HAIR_BLACK_STR)) {
			hairInt = Util.F_HAIR_BLACK;
		} else {
			if (hair.toUpperCase().equals(Util.HAIR_FAIR_STR)) {
				hairInt = Util.F_HAIR_FAIR;
			}  else {
					logger.debug("hair colour should be " + Util.HAIR_BLACK_STR + " or " +
							Util.HAIR_FAIR_STR );
					validInput = false;
				}
			}

		int frecklesInt = -1;
		if (validInput) {
			if (freckles.toUpperCase().equals(Util.FRECKLES_NONE_STR)) {
				frecklesInt = Util.F_FRECKLES_NONE;
			} else {
				if (freckles.toUpperCase().equals(Util.FRECKLES_FEW_STR)) {
					frecklesInt = Util.F_FRECKLES_FEW;
				} else {
					if (freckles.toUpperCase().equals(Util.FRECKLES_MANY_STR)) {
						frecklesInt = Util.F_FRECKLES_MANY;
					} else {
						validInput = false;
						logger.debug("freckles should be " + Util.FRECKLES_NONE_STR + " or " +
								Util.FRECKLES_FEW_STR + " or " +
								Util.FRECKLES_MANY_STR + " instead got " + freckles);

					}
				}
			}
		} else {
			validInput = false;
			logger.error("invalid parameter occupation " + occ);
		}

		int molesInt = -1;
		if (validInput) {
			try {

				molesInt = Integer.parseInt(moles);
				if (molesInt < 0) {

					validInput = false;
					logger.debug("error formatting #moles: negative number");
				}

			} catch (NumberFormatException e) {

				validInput = false;
				logger.debug("error formatting #moles");

			}

			// assign risk group
			molesInt = Util.femaleMolesGroups(molesInt);

		}
		

		int famHXMolesInt = -1;
		if (validInput) {
			if (famHXMoles.toUpperCase().equals("DONT_KNOW")) {
				famHXMolesInt = Util.F_FAMHXMOLES_DONTKNOW;
			} else {
				if (famHXMoles.toUpperCase().equals("YES")) {
					famHXMolesInt = Util.F_FAMHXMOLES_YES;
				} else {
					if (famHXMoles.toUpperCase().equals("NO")) {
						famHXMolesInt = Util.F_FAMHXMOLES_NO;
					} else {
						logger.debug("famHXMoles should be dont_know, yes or no");
						validInput = false;
					}
				}
			}

		}

		

		int nmscInt = -1;
		if (validInput) {

			if (nmsc.toUpperCase().equals("NO")) {

				nmscInt = Util.F_NMSC_NO;

			} else {

				if (nmsc.toUpperCase().equals("YES")) {

					nmscInt = Util.F_NMSC_YES;

				} else {

					logger.debug("nmsc should be YES or NO, got " + nmsc);
					validInput = false;
				}

			}
		}

		Integer ageInt = -1;
		if (validInput) {
			try {

				ageInt = Integer.parseInt(age);

				if (ageInt < 20) {

					validInput = false;
					logger.debug("error formatting age: less than 20");
				}

			} catch (NumberFormatException e) {

				validInput = false;
				logger.debug("error formatting age, got " + age);
			}

		}

		Integer areaInt = -1;
		if (validInput) {

			if (area.toUpperCase().equals(Util.AREA_CENTRAL_STRING)) {
				areaInt = Util.AREA_CENTRAL;
			} else if (area.toUpperCase().equals(Util.AREA_MIDLAND_STRING)) {
				areaInt = Util.AREA_MIDLAND;
			} else if (area.toUpperCase().equals(Util.AREA_NORTH_STRING)) {
				areaInt = Util.AREA_NORTH;
			} else if (area.toUpperCase().equals(Util.AREA_SOUTH_STRING)) {
				areaInt = Util.AREA_SOUTH;
			} else {
				logger.debug("area should be north, midland, central,south");
				validInput = false;
			}

		}

		int[] parms = { occInt, hairInt,frecklesInt, molesInt, famHXMolesInt, nmscInt, ageInt, areaInt };
		logger.debug("validated parms: ");
		logger.debug("occInt: " + parms[0]);
		logger.debug("hairInt: " + parms[1]);
		logger.debug("frecklesInt: " + parms[2]);
		logger.debug("molesInt: " + parms[3]);
		logger.debug("famHMMolesInt: " + parms[4]);
		logger.debug("nmscInt: " + parms[5]);
		logger.debug("ageInt: " + parms[6]);
		logger.debug("areaInt: " + parms[7]);
		
		
		
		
		
		
		
		if (!validInput) {
			parms = null;
		}

		return (parms);
	}

	/**
	 * Validate input parameters for a man.
	 * 
	 * @param molesRarm
	 *            0,1,2
	 * @param nmsc
	 *            "yes","no"
	 * @param occ
	 *            "in","out","inout"
	 * @param birthplace
	 *            "nz","not_nz"
	 * @param eye
	 *            "brown","green","grey"
	 * @param skin
	 *            "olive","medium","fair"
	 * @param age
	 *            20,21,22,..
	 * @param area
	 *            "north","midland","central","south"
	 * 
	 * @return int[] representing the parameters, or null if the input is
	 *         invalid. See the method for females for how this array is built
	 *         up.
	 * 
	 */

	static int[] validateMale(String molesRarm, String nmsc, String occ, String birthplace, String eye, String skin,
			String age, String area) {

		logger.debug("molesRarm: " + molesRarm);
		logger.debug("nmsc: " + nmsc);
		logger.debug("occ: " + occ);
		logger.debug("eye: " + eye);
		logger.debug("skin: " + skin);
		logger.debug("birthplace: " + birthplace);
		logger.debug("age: " + age);
		logger.debug("area: " + area);

		boolean validInput = true;
		int[] result = null;

		if (molesRarm == null) {
			logger.debug("missing parameter molesRarm");
			return (result);

		}
		if (nmsc == null) {
			logger.debug("missing parameter nmsc");
			return (result);

		}
		if (occ == null) {
			logger.debug("missing parameter occ");
			return (result);

		}
		if (birthplace == null) {

			logger.debug("missing parameter birthplace");
			return (result);

		}
		if (eye == null) {

			logger.debug("missing parameter eye");
			return (result);

		}

		if (skin == null) {

			logger.debug("missing parameter skin");
			return (result);

		}
		if (age == null) {
			logger.debug("missing parameter age");
			return (result);

		}
		if (area == null) {
			logger.debug("missing parameter area");
			return (result);

		}

		int moles = -1;
		if (validInput) {
			try {

				moles = Integer.parseInt(molesRarm);
				if (moles < 0) {

					validInput = false;
					logger.debug("error formatting #moles: negative number");
				}
				if (moles > 2) {

					validInput = false;
					logger.debug("error formatting #moles: should be 0,1 or 2");
				}

			} catch (NumberFormatException e) {

				validInput = false;
				logger.error("invalid parameter molesRamr " + molesRarm, e);
			}
		}

		int nmscInt = -1;
		if (validInput) {

			if (nmsc.toUpperCase().equals("NO")) {

				nmscInt = Util.M_NMSC_NO;

			} else {

				if (nmsc.toUpperCase().equals("YES")) {

					nmscInt = Util.M_NMSC_YES;

				} else {

					logger.debug("nmsc should be YES or NO, got " + nmsc);
					validInput = false;
				}

			}
		}

		int occInt = -1;
		if (validInput) {
			if (occ.toUpperCase().equals(Util.OCCUPATION_IN_STR)) {
				occInt = Util.M_OCCUPATION_IN;
			} else {
				if (occ.toUpperCase().equals(Util.OCCUPATION_INOUT_STR)) {
					occInt = Util.M_OCCUPATION_INOUT;
				} else {
					if (occ.toUpperCase().equals(Util.OCCUPATION_OUT_STR)) {
						occInt = Util.M_OCCUPATION_OUT;
					} else {
						validInput = false;
						logger.debug("occ should be IN, OUT or INOUT. got " + occ);

					}
				}
			}
		}
		

		int birthInt = -1;
		if (validInput) {
			if (birthplace.toUpperCase().equals(Util.BIRTH_NOTNZ_STR)) {
				birthInt = Util.M_BIRTH_NOTNZ;
			} else {
				if (birthplace.toUpperCase().equals(Util.BIRTH_NZ_STR)) {
					birthInt = Util.M_BIRTH_NZ;
				} else {
					validInput = false;
					logger.debug("birthplace should be " + Util.BIRTH_NOTNZ_STR + " or " + Util.BIRTH_NZ_STR
							+ "instead got " + birthplace);

				}
			}

		}
		
		int eyeInt = -1;
		if (validInput) {
			if (eye.toUpperCase().equals(Util.EYE_BROWN_STR)) {
				eyeInt = Util.M_EYECOLOUR_BROWN;
			} else {
				if (eye.toUpperCase().equals(Util.EYE_GREEN_STR)) {
					eyeInt = Util.M_EYECOLOUR_GREEN;
				} else {
					if (eye.toUpperCase().equals(Util.EYE_GREY_STR)) {
						eyeInt = Util.M_EYECOLOUR_GREY;
					} else {
					validInput = false;
					logger.debug("eye should be " + Util.EYE_BROWN_STR+ " or " + Util.EYE_GREEN_STR + " or " +
					        Util.EYE_GREY_STR + "instead got " + birthplace);
					}
				}
			}

		}

		int skinInt = -1;
		if (validInput) {
			if (skin.toUpperCase().equals(Util.SKINCOLOUR_OLIVE_STR)) {
				skinInt = Util.M_SKINCOLOUR_OLIVE;
			} else {
				if (skin.toUpperCase().equals(Util.SKINCOLOUR_MEDIUM_STR)) {
					skinInt = Util.M_SKINCOLOUR_MEDIUM;
				} else {
					if (skin.toUpperCase().equals(Util.SKINCOLOUR_FAIR_STR)) {
						skinInt = Util.M_SKINCOLOUR_FAIR;
					} else {
					validInput = false;
					logger.debug("skin should be " + Util.SKINCOLOUR_OLIVE_STR + " or " + Util.SKINCOLOUR_MEDIUM_STR + " or " +
					        Util.SKINCOLOUR_FAIR_STR + " instead got " + skin);
					}
				}
			}

		}

		
		Integer ageInt = -1;
		if (validInput) {
			try {

				ageInt = Integer.parseInt(age);
				if (ageInt < 20) {

					validInput = false;
					logger.debug("error formatting age: less than 20");
				}

			} catch (NumberFormatException e) {

				validInput = false;
				logger.debug("error formatting age, got " + age);
			}

		}

		Integer areaInt = -1;
		if (validInput) {

			if (area.toUpperCase().equals(Util.AREA_CENTRAL_STRING)) {
				areaInt = Util.AREA_CENTRAL;
			} else if (area.toUpperCase().equals(Util.AREA_MIDLAND_STRING)) {
				areaInt = Util.AREA_MIDLAND;
			} else if (area.toUpperCase().equals(Util.AREA_NORTH_STRING)) {
				areaInt = Util.AREA_NORTH;
			} else if (area.toUpperCase().equals(Util.AREA_SOUTH_STRING)) {
				areaInt = Util.AREA_SOUTH;
			} else {
				validInput = false;
				logger.debug("invalid area parameter:" + area);
			}

		}

		
		int[] parms = { moles, nmscInt, occInt, birthInt, eyeInt, skinInt, ageInt, areaInt };

		if (!validInput) {
			parms = null;
		}

		return (parms);

	}

	/**
	 * convert age in years to age group. 20-24 years is group 0, 25-29 is group
	 * 1, etc.
	 * 
	 * @param age
	 *            , 20,21,...
	 * @return age group 0,1,2, or -1 if age<20
	 */
	public static int toAgeGroup(int age) {

		int result = -1;
		if (age >= 20) {
			result = age / 5 - 4;
		}
		if (age >= 85) {
			result = 13;
		}
		logger.debug("age " + age + " is in age group " + result);
		return (result);
	}

	/**
	 * 
	 * @param age
	 * @return Util.AGE_LESS50 if the given age <= 50, or Util.AGE_MORE50 if age
	 *         = 51,52,...
	 */
	/*
	public static int lessThan51(int age) {

		int result = AGE_51_OR_MORE;
		if (age <= 50) {
			result = Util.AGE_50_OR_LESS;
		}
		return (result);
	}
	*/
	public static int femaleMolesGroups(int moles) {

		int result = -1;
		if (moles == 0) {
			result = Util.F_MOLES_0;
		} else {
			if (moles == 1) {
				result = Util.F_MOLES_1;
			} else {
				if (moles >= 2) {
					result = Util.F_MOLES_2;
				} 
			}
		}
		return (result);

	}

	public static int maleMolesGroups(int moles) {

		int result = -1;
		if (moles == 0) {
			result = Util.M_MOLES_0;
		} else {
			if (moles == 1) {
				result = Util.M_MOLES_1;
			} else {
				if (moles == 2) {
					result = Util.M_MOLES_2;
				} else {
					logger.debug("invalid number of moles" + moles);
				}
			}
		}

		return (result);

	}

	public static String LOW_RISK = "LOW_RISK";
	public static String MODERATE_RISK = "MODERATE_RISK";
	public static String HIGH_RISK = "HIGH_RISK";
	public static String VERY_HIGH_RISK = "VERY_HIGH_RISK";

	// obsolete: added .000001 because I have a testcase exactly at the boundary.
	static float LOW_RISK_UPP = 0.003501f;
	static float MODERATE_RISK_UPP = 0.0083f;
	static float HIGH_RISK_UPP = 0.018f;

	/**
	 * work out risk category, based on risk;
	 * 
	 * @param risk
	 *            float
	 * @return risk category
	 */
	public static String calculateRiskCategory(Float risk) {

		String result = null;
		if (risk == null) {
			return result;
		}
		if (risk < 0f) {
			return result;
		}

		if (risk < LOW_RISK_UPP) {
			result = LOW_RISK;
		} else {
			if (risk < MODERATE_RISK_UPP) {
				result = MODERATE_RISK;
			} else if (risk < HIGH_RISK_UPP) {
				result = HIGH_RISK;
			} else {
				result = VERY_HIGH_RISK;
			}
		}

		return result;

	}

	 
	public static String LOW_RISK_TEXT = "This person is expected to have a low risk of developing melanoma in the next 5 years." ; 
	public static String MODERATE_RISK_TEXT = "This person is expected to have a moderate risk of developing melanoma in the next 5 years.";
	public static String HIGH_RISK_TEXT = "This person is expected to have a high risk of developing melanoma in the next 5 years.";
	public static String VERY_HIGH_RISK_TEXT = "This person is expected to have a very high risk of developing melanoma in the next 5 years.";

	public static String getRiskDescription(String riskCategory, float risk) {
		logger.debug("risk category is: " + riskCategory);
		logger.debug("risk is: " + risk);
		String result = "ERROR";
		if (riskCategory == null) {
			return result;
		}

		if (riskCategory.equals(LOW_RISK)) {
			result = LOW_RISK_TEXT;
		} else {
			if (riskCategory.equals(MODERATE_RISK)) {
				result = MODERATE_RISK_TEXT;
			} else {
				if (riskCategory.equals(HIGH_RISK)) {
					result = HIGH_RISK_TEXT;
				} else {
					if (riskCategory.equals(VERY_HIGH_RISK)) {
						result = VERY_HIGH_RISK_TEXT;
					} else {
						logger.error("unexpected riskCategory " + riskCategory);
					}
				}
			}
		}
		// no division by 0
		float riskPerc = Math.max(risk * 100, 0.000001f);
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		String riskPercStr = df.format(riskPerc);
		
		
		float numIn100 = 100/riskPerc;
		DecimalFormat df2 = new DecimalFormat();
		df2.setMaximumFractionDigits(0);
		df2.setMinimumFractionDigits(0);
		String numIn100Str = df2.format(numIn100);
		
		
        result = result + "The risk is " + riskPercStr + "% in 5 years. \n ";
		result = result + "1 in " + numIn100Str + " people of the same age and gender, and with the same characteristics as this person " + 
				"would be expected to develop melanoma in the next 5 years.";
		return result;
	}
	
	public static String toPerc(float risk) {
		float riskPerc = Math.max(risk * 100, 0.000001f);
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(2);
		df.setMinimumFractionDigits(2);
		String riskPercStr = df.format(riskPerc);
		return riskPercStr;
	}
	
	/*
	 * used for testing only. NumIn100 is passed in as a parameter, so we can compare
	 * it to the actual value produced by the web service.
	 * 
	 */
	public static String getRiskDescriptionTest(String riskCategory, float risk, String numIn100Str) {
		logger.debug("risk category is: " + riskCategory);
		logger.debug("risk is: " + risk);
		String result = "ERROR";
		if (riskCategory == null) {
			return result;
		}

		if (riskCategory.equals(LOW_RISK)) {
			result = LOW_RISK_TEXT;
		} else {
			if (riskCategory.equals(MODERATE_RISK)) {
				result = MODERATE_RISK_TEXT;
			} else {
				if (riskCategory.equals(HIGH_RISK)) {
					result = HIGH_RISK_TEXT;
				} else {
					if (riskCategory.equals(VERY_HIGH_RISK)) {
						result = VERY_HIGH_RISK_TEXT;
					} else {
						logger.error("unexpected riskCategory " + riskCategory);
					}
				}
			}
		}
		// no division by 0
		//float riskPerc = Math.max(risk * 100, 0.000001f);
		
		//DecimalFormat df = new DecimalFormat();
		//df.setMaximumFractionDigits(2);
		///df.setMinimumFractionDigits(2);
		//String riskPercStr = df.format(riskPerc);
		
		String riskPercStr = toPerc(risk);

		result = result + "The risk is " + riskPercStr + "% in 5 years. \n ";
		result = result + "1 in " + numIn100Str + " people of the same age and gender, and with the same characteristics as this person " + 
				"would be expected to develop melanoma in the next 5 years.";
		return result;
	}
	
	

	public static String toElement(String riskCategory) {
		return ("<RISK_CATEGORY>" + riskCategory + "</RISK_CATEGORY>");
	}

	public static String riskToXML(float risk) {

		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(Util.MAX_DIGITS);
		df.setMinimumFractionDigits(Util.MAX_DIGITS);
		String result = df.format(risk);

		String riskCategory = calculateRiskCategory(risk);
		String riskDescription = getRiskDescription(riskCategory, risk);

		String xml = "<?xml version=\"1.0\"?>" + "<MR><RISK>" + result + "</RISK>" + toElement(riskCategory)
				+ "<DESCRIPTION>" + riskDescription + "</DESCRIPTION>" + "</MR>";
		return xml;

	}

	/**
	 * testing only
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		/*
		for (int i = 1; i < 90; i++) {
			System.out.println(i + "   " + toAgeGroup(i));
		}
		*/
		
		String desc = getRiskDescription(Util.MODERATE_RISK, 0.001f);
		System.out.println(desc);
		
		String desc2 = riskToXML(0.0165f);
		System.out.println(desc2);
		
		

	}
}
