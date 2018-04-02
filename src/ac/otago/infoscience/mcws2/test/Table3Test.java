package ac.otago.infoscience.mcws2.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ac.otago.infoscience.mcws2.Table3;
import ac.otago.infoscience.mcws2.Util;

public class Table3Test {

	float delta = 0.5f;
	
	@SuppressWarnings("deprecation")
	@Test
	public void testInit() {
		Table3.init();
		/*
		assertEquals("female: olive",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.SKINCOLOUR_OLIVE),1,delta);
		assertEquals("female: medium",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.SKINCOLOUR_MEDIUM),2.94,delta);
		assertEquals("female: fair",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.SKINCOLOUR_FAIR),4.50,delta);
		assertEquals("female: fam-dontknow",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.FAMHXMOLES_DONTKNOW),1,delta);
		assertEquals("female: fam-no",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.FAMHXMOLES_NO),1.09,delta);
		assertEquals("female: fam-yes",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.FAMHXMOLES_YES),2.62,delta);
		assertEquals("female: moles0",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.MOLES_RARM_0),1,delta);
		assertEquals("female: moles1",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.MOLES_RARM_1),1.34,delta);
		assertEquals("female: moles2",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.MOLES_RARM_2),2.59,delta);
		assertEquals("female: moles3+",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.MOLES_RARM_3PLUS),4.28,delta);
		assertEquals("female: nmsc no",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.NMSC_NO_F),1,delta);
		assertEquals("female: nmsc yes",Table3.getRelativeRiskByFactor(Util.GENDER_FEMALE, Util.NMSC_YES_F),3.75,delta);
		assertEquals("male: moles0 ",Table3.getRelativeRiskByFactor(Util.GENDER_MALE,Util.M_MOLES_0),1.15,delta);
		assertEquals("male: nmsc  ",Table3.getRelativeRiskByFactor(Util.GENDER_MALE,Util.NMSC_NO_M),1,delta);
		assertEquals("male: nmsc  ",Table3.getRelativeRiskByFactor(Util.GENDER_MALE,Util.NMSC_YES_M),3.1,delta);
		//assertEquals("male: age  ",Table3.getRelativeRiskByFactor(Util.GENDER_MALE,Util.AGE_50_OR_LESS),1,delta);
		//assertEquals("male: age  ",Table3.getRelativeRiskByFactor(Util.GENDER_MALE,Util.AGE_51_OR_MORE),2.59,delta);
		assertEquals("male: occ in  ",Table3.getRelativeRiskByFactor(Util.GENDER_MALE,Util.OCCUPATION_IN),1,delta);
		assertEquals("male: occ in out  ",Table3.getRelativeRiskByFactor(Util.GENDER_MALE,Util.OCCUPATION_INOUT),1.55,delta);
		assertEquals("male: occ out  ",Table3.getRelativeRiskByFactor(Util.GENDER_MALE,Util.OCCUPATION_OUT),1.94,delta);
		assertEquals("male: birth not nz  ",Table3.getRelativeRiskByFactor(Util.GENDER_MALE,Util.BIRTH_NOTNZ),1,delta);
		assertEquals("male: birth nz  ",Table3.getRelativeRiskByFactor(Util.GENDER_MALE,Util.BIRTH_NZ),2.21,delta);
		
		int[] r1 = {Util.SKINCOLOUR_OLIVE,Util.FAMHXMOLES_DONTKNOW,Util.MOLES_RARM_0,Util.NMSC_NO_F};
		assertEquals("rr1 ",Table3.getRelativeRiskWomen(r1),1,delta);
		
		r1[0] = Util.SKINCOLOUR_MEDIUM;
		assertEquals("rr1 ",Table3.getRelativeRiskWomen(r1),2.94,delta);
		
		r1[1] = Util.FAMHXMOLES_YES;
		assertEquals("rr1 ",Table3.getRelativeRiskWomen(r1),2.94*2.62,delta);
		
		r1[2] = Util.MOLES_RARM_3PLUS;
		assertEquals("rr1 ",Table3.getRelativeRiskWomen(r1),2.94*2.62*4.28,delta);
		
		r1[3] = Util.NMSC_YES_F;
		assertEquals("rr1 ",Table3.getRelativeRiskWomen(r1),2.94*2.62*4.28*3.75,delta);
		
		//int[] r2 = {0,Util.NMSC_NO_M,Util.AGE_50_OR_LESS,Util.OCCUPATION_IN,Util.BIRTH_NOTNZ};
		//assertEquals("rr1 ",Table3.getRelativeRiskMen(r2),1,delta);
		
		r2[0] = 1;
		assertEquals("rr1 ",Table3.getRelativeRiskMen(r2),1.15,delta);
		
		r2[0] = 2;
		assertEquals("rr1 ",Table3.getRelativeRiskMen(r2),1.15*1.15,delta);
		
		r2[0] = 3;
		assertEquals("rr1 ",Table3.getRelativeRiskMen(r2),1.15*1.15*1.15,delta);
		
		r2[1] = Util.NMSC_YES_M;
		assertEquals("rr1 ",Table3.getRelativeRiskMen(r2),1.15*1.15*1.15*3.10,delta);
		
		r2[2] = Util.AGE_51_OR_MORE;
		assertEquals("rr1 ",Table3.getRelativeRiskMen(r2),1.15*1.15*1.15*3.10*2.59,delta);
		
		r2[3] = Util.OCCUPATION_INOUT;
		assertEquals("rr1 ",Table3.getRelativeRiskMen(r2),1.15*1.15*1.15*3.10*2.59*1.55,delta);
		
		r2[3] = Util.OCCUPATION_OUT;
		assertEquals("rr1 ",Table3.getRelativeRiskMen(r2),1.15*1.15*1.15*3.10*2.59*1.94,delta);
		
		r2[4] = Util.BIRTH_NZ;
		assertEquals("rr1 ",Table3.getRelativeRiskMen(r2),1.15*1.15*1.15*3.10*2.59*1.94*2.21,delta);
		
		*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	/*
	@Test
	public void testGetRelativeRiskWomen() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRelativeRiskMen() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetRelativeRiskByFactor() {
		fail("Not yet implemented");
	}
*/
}
