package ac.otago.infoscience.mcws2;


import java.text.DecimalFormat;

import org.apache.log4j.Logger;

/**
 * 
 * @author michel
 *
 *
 * This class is responsible for calculating absolute risk. There are separate methods for 
 * female and for male patients.
 * 
 */
public class Calculator {
	

	private static Logger logger = Logger
			.getLogger("ac.otago.infoscience.mcws.Calculator");

	
	/**
	 * 
	 * Calculate absolute risk for a female person.
	 * 
	 * @param parms int[] = { occ, hair,freckles, moles, famHXMoles,nmsc,age, area } The
	 * order of the elements does not matter.
	 * @return calculated absolute risk for the given risk profile for women.
	 * 
	 * Replaced by method calculateFemaleFears
	 * 
	 */

	public static double calculateFemale(int[] parms) {

		

		for (int i=0;i<parms.length;i++) {
			logger.debug("calculateFemale, parms" + i + ": " + parms[i]);
		}
 
		// Table2 holds baseline incidence and mortality
		Table2 table2 = Table2.newInstance();
		
		// get baseline melanoma incidence for women, the age group and the area.
		float baseline = table2.getMelanomaIncidence(Util.GENDER_FEMALE,
				Util.toAgeGroup(parms[Util.AGE_INDEX_F]), parms[Util.AREA_INDEX_F]);
		logger.debug("baseline incidence " + baseline);
		
		// get baseline mortality for women of this age group.
		float h2 = table2.getMortality(Util.GENDER_FEMALE,
				Util.toAgeGroup(parms[Util.AGE_INDEX_F]));
		logger.debug("baseline mortality (h2)" + h2);
		
		h2 = h2/100000;
		logger.debug("h2 * 1E-5 = " + h2);
		
		
		int[] riskFactors = {parms[0], parms[1],parms[2],parms[3],parms[4],parms[5]};
		
		// calculate relative risk for the given risk profile.
		float rr = Table3.getRelativeRiskWomen(riskFactors);
		logger.debug("relative risk is " + rr);

		// now do some magic to calculate absolute risk
		float h1 = baseline * Util.ADJ_FEMALE / 100000;
		logger.debug("baseline * (" + Util.ADJ_FEMALE +") is h1 = " + h1);
		
		float numerator = h1 * rr;
		logger.debug("h1 * rr = " + numerator);
		
		float denominator = h1 * rr + h2;
		logger.debug("h1*rr + h2 = " + denominator);

		// first factor of P(a,r)
		double f1 = numerator / denominator;
		logger.debug("first factor= " + f1);
		// second factor of P(a,r)
		double f2 = (1 - Math.exp(-5 * denominator));
		logger.debug("1- exp(-5 * denominator) " + f2);
		
		// P(a,r)
		double result = f1 * f2;
		logger.debug("P(a,r) = " + result);
		
		logger.debug("result is " + result);
		
		return (result);

	}
	


	
	/**
	 * 
	 * Calculate absolute risk for a female person, usering Fear's formula
	 * 
	 * @param parms int[] = { occ, hair,freckles, moles, famHXMoles,nmsc,age, area } The
	 * order of the elements does not matter.
	 * @return calculated absolute risk for the given risk profile for women.
	 * 
	 */

	public static double calculateFemaleFears(int[] parms) {

		

		for (int i=0;i<parms.length;i++) {
			logger.debug("calculateFemaleFears, parms" + i + ": " + parms[i]);
		}
 
		// Table2 holds baseline incidence and mortality
		Table2 table2 = Table2.newInstance();
		
		// get baseline melanoma incidence for women, the age group and the area.
		int age = parms[Util.AGE_INDEX_F];
		int ageGroup = Util.toAgeGroup(age);
		int nextAgeGroup = Util.toAgeGroup(age + 5);
		logger.debug("age is:" + age);
		logger.debug("age group is:" + ageGroup);
		logger.debug("nextAge group is:" + nextAgeGroup);
		
		
		float baseline = table2.getMelanomaIncidence(Util.GENDER_FEMALE,
				ageGroup, parms[Util.AREA_INDEX_F]);
		
		logger.debug("baseline incidence " + baseline);
		
		// get baseline mortality for women of this age group.
		float h2 = table2.getMortality(Util.GENDER_FEMALE,ageGroup);
				
		logger.debug("baseline mortality (h2)" + h2);
		
		h2 = h2/100000;
		logger.debug("h2 * 1E-5 = " + h2);
		
		
		int[] riskFactors = {parms[0], parms[1],parms[2],parms[3],parms[4],parms[5]};
		
		// calculate relative risk for the given risk profile.
		float rr = Table3.getRelativeRiskWomen(riskFactors);
		logger.debug("relative risk is " + rr);

		// now do some magic to calculate absolute risk
		float h1 = baseline * Util.ADJ_FEMALE / 100000;
		logger.debug("baseline * " + Util.ADJ_FEMALE +") is h1 = " + h1);
		
		float numerator = h1 * rr;
		logger.debug("h1 * rr = " + numerator);
		
		float denominator = h1 * rr + h2;
		float saveDenominator = denominator;
		logger.debug("h1*rr + h2 = " + denominator);

		// first factor of P(a,r)
		double f1 = numerator / denominator;
		logger.debug("first factor= " + f1);
		// second factor of P(a,r)
		
		// number of years to go in the current age group
		
		double yearsInThisAgeGroup = 5 - (age % 5);
		logger.debug("years to go in this age group:" + yearsInThisAgeGroup);
		
		double yearsInNextAgeGroup = 5 - yearsInThisAgeGroup;
		logger.debug("years to go in next age group:" + yearsInNextAgeGroup);
		
		
		double f2 = (1 - Math.exp(-yearsInThisAgeGroup * denominator));
		logger.debug("1- exp(-5 * denominator) " + f2);
		
		// the bit for the years remaining within the current age group
		double result1 = f1 * f2;
		
		// now the bit for the years in the next age group
		baseline = table2.getMelanomaIncidence(Util.GENDER_FEMALE,
				nextAgeGroup, parms[Util.AREA_INDEX_F]);
		
		logger.debug("baseline incidence for next age group " + baseline);
		
		// get baseline mortality for women of this age group.
		h2 = table2.getMortality(Util.GENDER_FEMALE,
				nextAgeGroup);
		logger.debug("baseline mortality (h2) for next age group" + h2);
		
		h2 = h2/100000;
		logger.debug("h2 * 1E-5 = " + h2);
		
		
		// now do some magic to calculate absolute risk
		h1 = baseline * Util.ADJ_FEMALE / 100000;
		logger.debug("baseline * (1-" + Util.ADJ_FEMALE +") is h1 = " + h1);
		
		numerator = h1 * rr;
		logger.debug("h1 * rr = " + numerator);
		
		denominator = h1 * rr + h2;
		logger.debug("h1*rr + h2 = " + denominator);

		// first factor of P(a,r)
		f1 = numerator / denominator;
		logger.debug("first factor= " + f1);
		
		
		f2 = (1 - Math.exp(-yearsInNextAgeGroup * denominator));
		logger.debug("1- exp(-yearsInNextAgeGroup * denominator) " + f2);
		
		// the bit for the years remaining within the next age group
		double result3 = f1 * f2;
		
		double result2 = Math.exp(-yearsInThisAgeGroup * saveDenominator);

		logger.debug("result1 is: " + result1);
		logger.debug("result2 is: " + result2);
		logger.debug("result3 is: " + result3);
			
		double result = result1 + result2 * result3;
		logger.debug("final result is result1 + result2 * result3: " + result);
		
		return (result);

	}
	

	
	/**
	 * 
	 * Calculate absolute risk for a male person.
	 *         
	 * @param parms int[]  { moles, nmsc, occupation,birth,age,area } 
	 * @return absolute risk
	 * 
	 * Replaced by method calculateMaleFears
	 * 
	 * 
	 */
	public static double calculateMale(int[] parms) {

		for (int i=0;i<parms.length;i++) {
			logger.debug("calculateMale, parms" + i + ": " + parms[i]);
		}
		
		// Table2 holds baseline incidence and mortality
		Table2 table2 = Table2.newInstance();
		
		// get baseline melanoma incidence for men, the age group and the area.
		float baseline = table2.getMelanomaIncidence(Util.GENDER_MALE,
				Util.toAgeGroup(parms[Util.AGE_INDEX_M]), parms[Util.AREA_INDEX_M]);
		logger.debug("baseline incidence " + baseline);
		
		// get baseline mortality for men of this age group.
		float h2 = table2.getMortality(Util.GENDER_MALE,
				Util.toAgeGroup(parms[Util.AGE_INDEX_M]));
		h2 = h2/100000;
		logger.debug("h2 * 1E-5 = " + h2);
		
		logger.debug("baseline mortality (h2) " + h2);
		 
		
		int[] riskFactors = {parms[0], parms[1], parms[2],parms[3], parms[4],parms[5]};
		
		// calculate relative risk for this man.
		float rr = Table3.getRelativeRiskMen(riskFactors);
		logger.debug("relative risk is: " + rr);

		// calculate absolute risk.
		float h1 = baseline * Util.ADJ_MALE / 100000;
		logger.debug("baseline * " +  Util.ADJ_MALE /100000  + " is h1 = " + h1);
		
		float numerator = h1 * rr;
		logger.debug("h1 * rr = " + numerator);
		
		float denominator = h1 * rr + h2;
		logger.debug("h1*rr + h2 = " + denominator);

		// first factor of P(a,r)
		double f1 = numerator / denominator;
		logger.debug("first factor= " + f1);

		// second factor of P(a,r)
		double f2 = (1 - Math.exp(-5 * denominator));
		logger.debug("1- exp(-5 * denominator) " + f2);

		// P(a,r)
		double result = f1 * f2;
		logger.debug("P(a,r) = " + result);
		
	 
		logger.debug("result is " + result);
		
		// Each mole increases risk, so we cap risk at 1
		result = Math.min(result, 1.00);
		
		return (result);

		 

	}


	/**
	 * 
	 * Calculate absolute risk for a female person, usering Fear's formula
	 * 
	 * @param parms int[] = { occ, hair,freckles, moles, famHXMoles,nmsc,age, area } The
	 * order of the elements does not matter.
	 * @return calculated absolute risk for the given risk profile for women.
	 * 
	 */

	public static double calculateMaleFears(int[] parms) {

		

		for (int i=0;i<parms.length;i++) {
			logger.debug("calculateMaleFears, parms" + i + ": " + parms[i]);
		}
 
		// Table2 holds baseline incidence and mortality
		Table2 table2 = Table2.newInstance();
		
		// get baseline melanoma incidence for women, the age group and the area.
		int age = parms[Util.AGE_INDEX_M];
		int ageGroup = Util.toAgeGroup(age);
		int nextAgeGroup = Util.toAgeGroup(age + 5);
		logger.debug("age is:" + age);
		logger.debug("age group is:" + ageGroup);
		logger.debug("nextAge group is:" + nextAgeGroup);
		
		
		float baseline = table2.getMelanomaIncidence(Util.GENDER_MALE,
				ageGroup, parms[Util.AREA_INDEX_M]);
		
		logger.debug("baseline incidence " + baseline);
		
		// get baseline mortality for women of this age group.
		float h2 = table2.getMortality(Util.GENDER_MALE,
				Util.toAgeGroup(parms[Util.AGE_INDEX_M]));
		logger.debug("baseline mortality (h2)" + h2);
		
		h2 = h2/100000;
		logger.debug("h2 * 1E-5 = " + h2);
		
		
		int[] riskFactors = {parms[0], parms[1],parms[2],parms[3],parms[4],parms[5]};
		
		// calculate relative risk for the given risk profile.
		float rr = Table3.getRelativeRiskMen(riskFactors);
		logger.debug("relative risk is " + rr);

		// now do some magic to calculate absolute risk
		float h1 = baseline * Util.ADJ_MALE / 100000;
		logger.debug("baseline * (1-" + Util.ADJ_MALE +") is h1 = " + h1);
		
		float numerator = h1 * rr;
		logger.debug("h1 * rr = " + numerator);
		
		float denominator = h1 * rr + h2;
		float saveDenominator = denominator;
		logger.debug("h1*rr + h2 = " + denominator);

		// first factor of P(a,r)
		double f1 = numerator / denominator;
		logger.debug("first factor= " + f1);
		// second factor of P(a,r)
		
		// number of years to go in the current age group
		
		double yearsInThisAgeGroup = 5 - (age % 5);
		logger.debug("years to go in this age group:" + yearsInThisAgeGroup);
		
		double yearsInNextAgeGroup = 5 - yearsInThisAgeGroup;
		logger.debug("years to go in next age group:" + yearsInNextAgeGroup);
		
		
		double f2 = (1 - Math.exp(-yearsInThisAgeGroup * denominator));
		logger.debug("1- exp(-5 * denominator) " + f2);
		
		// the bit for the years remaining within the current age group
		double result1 = f1 * f2;
		
		// now the bit for the years in the next age group
		baseline = table2.getMelanomaIncidence(Util.GENDER_MALE,
				nextAgeGroup, parms[Util.AREA_INDEX_M]);
		
		logger.debug("baseline incidence for next age group " + baseline);
		
		// get baseline mortality for women of this age group.
		h2 = table2.getMortality(Util.GENDER_MALE,
				nextAgeGroup);
		logger.debug("baseline mortality (h2) for next age group" + h2);
		
		h2 = h2/100000;
		logger.debug("h2 * 1E-5 = " + h2);
		
		
		// now do some magic to calculate absolute risk
		h1 = baseline * Util.ADJ_MALE / 100000;
		logger.debug("baseline * " + Util.ADJ_MALE +") is h1 = " + h1);
		
		numerator = h1 * rr;
		logger.debug("h1 * rr = " + numerator);
		
		denominator = h1 * rr + h2;
		logger.debug("h1*rr + h2 = " + denominator);

		// first factor of P(a,r)
		f1 = numerator / denominator;
		logger.debug("first factor= " + f1);
		// second factor of P(a,r)
		
		// number of years to go in the next age group
		
		
		f2 = (1 - Math.exp(-yearsInNextAgeGroup * denominator));
		logger.debug("1- exp(-5 * denominator) " + f2);
		
		// the bit for the years remaining within the next age group
		double result3 = f1 * f2;
		
		logger.debug("calculating result2:");
		logger.debug("yearsInThisAgeGroup: "+yearsInThisAgeGroup);
		logger.debug("saveDenominator: "+saveDenominator);
		logger.debug("yearsInThisAgeGroup * saveDenominator: "+yearsInThisAgeGroup * saveDenominator);
		logger.debug("exp{-yearsInThisAgeGroup * saveDenominator}: "+ Math.exp(-yearsInThisAgeGroup * saveDenominator));		
		double result2 = Math.exp(-yearsInThisAgeGroup * saveDenominator);

		logger.debug("result1 is: " + result1);
		logger.debug("result2 is: " + result2);
		logger.debug("result3 is: " + result3);
			
		double result = result1 + result2 * result3;
		logger.debug("final result is result1 + result2 * result3: " + result);
		
		return (result);

	}
	


	/**
	 * test only
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		// female: parms is { skinColourInt,faxMHXMolesInt,moles,nmscInt,ageInt, areaInt }
		/*int[] parms = {Util.F_FRECKLES_MANY,
				Util.F_FAMHXMOLES_DONTKNOW,
				Util.F_MOLES_2,
				Util.F_NMSC_YES,
				40,
				Util.AREA_SOUTH}; 
		String s = String.valueOf(calculateFemale(parms));
		System.out.println("result is: " + s);

		// male: parms is { moles, nmsc, occupation,birth,age,area }
		//int[] parms2 = {5,Util.NMSC_YES_M,Util.OCCUPATION_OUT,Util.BIRTH_NZ,60,Util.AREA_MIDLAND};
		//String s2 = String.valueOf(calculateMale(parms2));
		//System.out.println("result is: " + s2);
		
		
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(4);
		df.setMinimumFractionDigits(4);
		
		logger.debug("result is " + df.format(0.001f));
		
		*/
		
	}

}
