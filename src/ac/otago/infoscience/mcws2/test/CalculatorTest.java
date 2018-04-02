package ac.otago.infoscience.mcws2.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import ac.otago.infoscience.mcws2.Calculator;
import ac.otago.infoscience.mcws2.Table3;
import ac.otago.infoscience.mcws2.Util;

/**
 * Uses Table 4 in the paper.
 * 
 * @author michel
 * 
 */
public class CalculatorTest {

	float delta = 0.0001f;
	//float percent = 0.01f;

	float[][] resultsWomenA = new float[12][4];
	float[][] resultsWomenB = new float[12][4];
	float[][] resultsWomenC = new float[12][4];
	
	float[][] resultsMenA = new float[12][4];
	float[][] resultsMenB = new float[12][4];
	float[][] resultsMenC = new float[12][4];

	
	void initResultsWomen() {
		resultsWomenA[0][0] = 0.0058f;
		resultsWomenA[0][1] = 0.0519f;
		resultsWomenA[0][2] = 0.1060f;
		resultsWomenA[0][3] = 0.1578f;

		resultsWomenA[1][0] = 0.0062f;
		resultsWomenA[1][1] = 0.0539f;
		resultsWomenA[1][2] = 0.1261f;
		resultsWomenA[1][3] = 0.1706f;

		resultsWomenA[2][0] = 0.0090f;
		resultsWomenA[2][1] = 0.0435f;
		resultsWomenA[2][2] = 0.0966f;
		resultsWomenA[2][3] = 0.1356f;

		resultsWomenA[3][0] = 0.0140f;
		resultsWomenA[3][1] = 0.0533f;
		resultsWomenA[3][2] = 0.0932f;
		resultsWomenA[3][3] = 0.1426f;

		resultsWomenB[0][0] = 0.0004f;
		resultsWomenB[0][1] = 0.0036f;
		resultsWomenB[0][2] = 0.0075f;
		resultsWomenB[0][3] = 0.0115f;

		resultsWomenB[1][0] = 0.0004f;
		resultsWomenB[1][1] = 0.0037f;
		resultsWomenB[1][2] = 0.0090f;
		resultsWomenB[1][3] = 0.0125f;

		resultsWomenB[2][0] = 0.0006f;
		resultsWomenB[2][1] = 0.0030f;
		resultsWomenB[2][2] = 0.0068f;
		resultsWomenB[2][3] = 0.0098f;

		resultsWomenB[3][0] = 0.0009f;
		resultsWomenB[3][1] = 0.0037f;
		resultsWomenB[3][2] = 0.0065f;
		resultsWomenB[3][3] = 0.0103f;

		resultsWomenC[0][0] = 0.0000f;
		resultsWomenC[0][1] = 0.0003f;
		resultsWomenC[0][2] = 0.0006f;
		resultsWomenC[0][3] = 0.0009f;

		resultsWomenC[1][0] = 0.000f;
		resultsWomenC[1][1] = 0.0003f;
		resultsWomenC[1][2] = 0.0007f;
		resultsWomenC[1][3] = 0.0010f;

		resultsWomenC[2][0] = 0.0000f;
		resultsWomenC[2][1] = 0.0003f;
		resultsWomenC[2][2] = 0.0005f;
		resultsWomenC[2][3] = 0.0008f;

		resultsWomenC[3][0] = 0.0001f;
		resultsWomenC[3][1] = 0.0003f;
		resultsWomenC[3][2] = 0.0005f;
		resultsWomenC[3][3] = 0.0008f;

	}

	void initResultsMen() {
		resultsMenA[0][0] = 0.0006f;
		resultsMenA[0][1] = 0.0056f;
		
		
		resultsMenA[0][2] = 0.0800f;
		
		resultsMenA[0][3] = 0.1493f;

		resultsMenA[1][0] = 0.0015f;
		resultsMenA[1][1] = 0.0107f;
		
		resultsMenA[1][2] = 0.0774f;
		
		resultsMenA[1][3] = 0.1605f;

		resultsMenA[2][0] = 0.0005f;
		resultsMenA[2][1] = 0.0069f;
		resultsMenA[2][2] = 0.0650f;
		resultsMenA[2][3] = 0.1292f;

		resultsMenA[3][0] = 0.0016f;
		resultsMenA[3][1] = 0.0068f;
		resultsMenA[3][2] = 0.0585f;
		resultsMenA[3][3] = 0.1283f;
		
		resultsMenB[0][0] = 0.0003f;
		resultsMenB[0][1] = 0.0025f;
		resultsMenB[0][2] = 0.0369f;
		resultsMenB[0][3] = 0.0706f;

		resultsMenB[1][0] = 0.0007f;
		resultsMenB[1][1] = 0.0049f;
		resultsMenB[1][2] = 0.0357f;
		resultsMenB[1][3] = 0.0762f;

		resultsMenB[2][0] = 0.0002f;
		resultsMenB[2][1] = 0.0031f;
		resultsMenB[2][2] = 0.0298f;
		resultsMenB[2][3] = 0.0607f;

		resultsMenB[3][0] = 0.0007f;
		resultsMenB[3][1] = 0.0031f;
		resultsMenB[3][2] = 0.0268f;
		resultsMenB[3][3] = 0.0603f;

		resultsMenC[0][0] = 0.0000f;
		resultsMenC[0][1] = 0.0002f;
		resultsMenC[0][2] = 0.0031f;
		resultsMenC[0][3] = 0.006f;

		resultsMenC[1][0] = 0.0001f;
		resultsMenC[1][1] = 0.0004f;
		resultsMenC[1][2] = 0.0030f;
		resultsMenC[1][3] = 0.0065f;

		resultsMenC[2][0] = 0.0000f;
		resultsMenC[2][1] = 0.0003f;
		resultsMenC[2][2] = 0.0025f;
		resultsMenC[2][3] = 0.00510f;

		resultsMenC[3][0] = 0.0001f;
		resultsMenC[3][1] = 0.0002f;
		resultsMenC[3][2] = 0.0022f;
		resultsMenC[3][3] = 0.0051f;

	}

	
	void runTestsFemale(int[] A, float[][] results) {

		assertEquals("female: 00 ", results[0][0] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 40;
		assertEquals("female: 01 ", results[0][1] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 60;
		assertEquals("female: 02 ", results[0][2] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 80;
		assertEquals("female: 03 ", results[0][3] ,
				Calculator.calculateFemale(A), delta);

		// area midland

		A[Util.AGE_INDEX_F] = 20;
		A[Util.AREA_INDEX_F] = Util.AREA_MIDLAND;
		assertEquals("female: 10 ", results[1][0] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 40;
		assertEquals("female: 11 ", results[1][1] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 60;
		assertEquals("female: 12 ", results[1][2] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 80;
		assertEquals("female: 13 ", results[1][3] ,
				Calculator.calculateFemale(A), delta);

		// area central

		A[Util.AGE_INDEX_F] = 20;
		A[Util.AREA_INDEX_F] = Util.AREA_CENTRAL;
		assertEquals("female: 20 ", results[2][0] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 40;
		assertEquals("female: 21 ", results[2][1] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 60;
		assertEquals("female: 22 ", results[2][2] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 80;
		assertEquals("female: 23 ", results[2][3] ,
				Calculator.calculateFemale(A), delta);

		// area south

		A[Util.AGE_INDEX_F] = 20;
		A[Util.AREA_INDEX_F] = Util.AREA_SOUTH;
		assertEquals("female: 30 ", results[3][0] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 40;
		assertEquals("female: 31 ", results[3][1] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 60;
		assertEquals("female: 32 ", results[3][2] ,
				Calculator.calculateFemale(A), delta);

		A[Util.AGE_INDEX_F] = 80;
		assertEquals("female: 33 ", results[3][3] ,
			Calculator.calculateFemale(A), delta);

	}

	
	void runTestsMaleYoung(int[] A, float[][] results) {

		// area north

		A[Util.AREA_INDEX_M] = Util.AREA_NORTH;
		
		assertEquals("male: 00 ", results[0][0] ,
				Calculator.calculateMale(A), delta);

		A[Util.AGE_INDEX_M] = 40;
		assertEquals("male: 01 ", results[0][1] ,
				Calculator.calculateMale(A), delta);

		// area midland

		A[Util.AGE_INDEX_M] = 20;
		A[Util.AREA_INDEX_M] = Util.AREA_MIDLAND;
		assertEquals("male: 10 ", results[1][0] ,
				Calculator.calculateMale(A), delta);

		A[Util.AGE_INDEX_M] = 40;
		assertEquals("male: 11 ", results[1][1] ,
				Calculator.calculateMale(A), delta);

		// area central

		A[Util.AGE_INDEX_M] = 20;
		A[Util.AREA_INDEX_M] = Util.AREA_CENTRAL;
		assertEquals("male: 20 ", results[2][0] ,
				Calculator.calculateMale(A), delta);

		A[Util.AGE_INDEX_M] = 40;
		assertEquals("male: 21 ", results[2][1] ,
				Calculator.calculateMale(A), delta);

		// area south

		A[Util.AGE_INDEX_M] = 20;
		A[Util.AREA_INDEX_M] = Util.AREA_SOUTH;
		assertEquals("male: 30 ", results[3][0] ,
				Calculator.calculateMale(A), delta);

		A[Util.AGE_INDEX_M] = 40;
		assertEquals("male: 31 ", results[3][1] ,
				Calculator.calculateMale(A), delta);


	}


	void runTestsMaleOld(int[] A, float[][] results) {

		// area north

		A[Util.AREA_INDEX_M] = Util.AREA_NORTH;
	
		A[Util.AGE_INDEX_M] = 60;
		assertEquals("male: 02 ", results[0][2] ,
				Calculator.calculateMale(A), delta);

		A[Util.AGE_INDEX_M] = 80;
		assertEquals("male: 03 ", results[0][3] ,
				Calculator.calculateMale(A), delta);

		// area midland

		A[Util.AREA_INDEX_M] = Util.AREA_MIDLAND;
		
		A[Util.AGE_INDEX_M] = 60;
		assertEquals("male: 12 ", results[1][2] ,
				Calculator.calculateMale(A), delta);

		A[Util.AGE_INDEX_M] = 80;
		assertEquals("male: 13 ", results[1][3] ,
				Calculator.calculateMale(A), delta);

		// area central

		A[Util.AREA_INDEX_M] = Util.AREA_CENTRAL;
		
		A[Util.AGE_INDEX_M] = 60;
		assertEquals("male: 22 ", results[2][2] ,
				Calculator.calculateMale(A), delta);

		A[Util.AGE_INDEX_M] = 80;
		assertEquals("male: 23 ", results[2][3] ,
				Calculator.calculateMale(A), delta);

		// area south

		A[Util.AREA_INDEX_M] = Util.AREA_SOUTH;
		
		A[Util.AGE_INDEX_M] = 60;
		assertEquals("male: 32 ", results[3][2] ,
				Calculator.calculateMale(A), delta);

		A[Util.AGE_INDEX_M] = 80;
		assertEquals("male: 33 ", results[3][3] ,
			Calculator.calculateMale(A), delta);

	}


	
	@Test
	public void testCalculateFemale() {

		// initialise expected results
		initResultsWomen();

		// HIGH RISK
		// { skinColourInt,faxMHXMolesInt,moles,nmscInt,ageInt, areaInt }
		
		/*
		int[] A = { Util.F_OCC_IN_OUT Util.F_FAMHXMOLES_DONTKNOW,
				Util.F_MOLES_2, Util.F_NMSC_YES, 20, Util.AREA_NORTH };
		System.out.println("running testCalculateFemale A");
		runTestsFemale(A, resultsWomenA);

		// MEDIUM RISK
		int[] B = { Util.SKINCOLOUR_FAIR, Util.F_FAMHXMOLES_NO,
				Util.F_MOLES_2, Util.F_NMSC_NO, 20, Util.AREA_NORTH };
		System.out.println("running testCalculateFemale B");
		runTestsFemale(B, resultsWomenB);
		*/

		// LOW RISK
		/*
		int[] C = { Util.M_SKINCOLOUR_OLIVE, Util.FAMHXMOLES_DONTKNOW,
				Util.MOLES_RARM_0, Util.NMSC_NO_F, 20, Util.AREA_NORTH };
		System.out.println("running testCalculateFemale c");
		runTestsFemale(C, resultsWomenC);
		*/
	}

	@Test
	public void testCalculateMale() {

		// initialise expected results
		initResultsMen();

		// HIGH RISK
		// int[] = { moles, nmsc, occupation,birth,age_at_diagnosis, current age,area }
		int[] A = { 5, Util.M_NMSC_YES, Util.M_OCCUPATION_OUT, Util.M_BIRTH_NZ, 0, 20, Util.AREA_NORTH };
		System.out.println("running testCalculateMale A");
		runTestsMaleYoung(A, resultsMenA);

		int[] A2 = {5, Util.M_NMSC_YES, Util.M_OCCUPATION_OUT, Util.M_BIRTH_NZ, 0, 60, Util.AREA_NORTH};
		System.out.println("running testCalculateMale A2");
		runTestsMaleOld(A2, resultsMenA);

		// MEDIUM RISK
		//int[] B = { 1, Util.NMSC_YES_M, Util.M_OCCUPATION_INOUT, Util.BIRTH_NZ,Util.AGE_50_OR_LESS,  20, Util.AREA_NORTH };
		System.out.println("running testCalculateMale B");
		//runTestsMaleYoung(B, resultsMenB);
		
		//int[] B2 = { 1, Util.NMSC_YES_M, Util.OCCUPATION_INOUT, Util.BIRTH_NZ,Util.AGE_51_OR_MORE,  60, Util.AREA_NORTH };
		System.out.println("running testCalculateMale B2");
		//runTestsMaleOld(B2, resultsMenB);
		
		
		// LOW RISK
		/*
		int[] C = { 0, Util.NMSC_NO_M, Util.OCCUPATION_IN, Util.BIRTH_NOTNZ ,Util.AGE_50_OR_LESS,  20, Util.AREA_NORTH };
		System.out.println("running testCalculateMale c");
		runTestsMaleYoung(C, resultsMenC);

		int[] C2 = { 0, Util.NMSC_NO_M, Util.OCCUPATION_IN, Util.BIRTH_NOTNZ ,Util.AGE_51_OR_MORE,  20, Util.AREA_NORTH };
		System.out.println("running testCalculateMale C2");
		runTestsMaleOld(C2, resultsMenC);
	*/
		
	}
	
	@Test
	public void testcalculateRiskCategory() {
		
		assertEquals("1: ",Util.LOW_RISK ,Util.calculateRiskCategory(0.00145f));
		assertEquals("2: ",Util.LOW_RISK ,Util.calculateRiskCategory(0.00345f));
		assertEquals("3: ",Util.MODERATE_RISK ,Util.calculateRiskCategory(0.00351f));
		assertEquals("4: ",Util.MODERATE_RISK ,Util.calculateRiskCategory(0.00829f));
		assertEquals("5: ",Util.HIGH_RISK ,Util.calculateRiskCategory(0.0084f));
		assertEquals("6: ",Util.HIGH_RISK ,Util.calculateRiskCategory(0.0179f));
		assertEquals("7: ",Util.VERY_HIGH_RISK ,Util.calculateRiskCategory(0.0181f));
		assertEquals("8: ",Util.VERY_HIGH_RISK ,Util.calculateRiskCategory(0.02f));
			
		
	}
	
	@Test
	public void testgetRiskDescription() {
		String THE_RISK_IS = "The risk is 0.00% in 5 years. ";
		String ONE_IN_MANY = THE_RISK_IS + 
				"\n 1 in 100,000,000 people of the same age and gender, and with the same characteristics " +
		"as this person would be expected to develop melanoma in the next 5 years.";
		assertEquals("1: ",Util.LOW_RISK_TEXT + ONE_IN_MANY,Util.getRiskDescription(Util.LOW_RISK,0.0f));
		assertEquals("2: ",Util.MODERATE_RISK_TEXT + ONE_IN_MANY ,Util.getRiskDescription(Util.MODERATE_RISK,0f));
		assertEquals("3: ",Util.HIGH_RISK_TEXT + ONE_IN_MANY,Util.getRiskDescription(Util.HIGH_RISK,0f));
		assertEquals("4: ",Util.VERY_HIGH_RISK_TEXT + ONE_IN_MANY,Util.getRiskDescription(Util.VERY_HIGH_RISK,0f));
				
		
	}
	
	
}
