package ac.otago.infoscience.mcws2.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ac.otago.infoscience.mcws2.Table3;
import ac.otago.infoscience.mcws2.Util;

public class UtilTest {

	@Test
	public void testLessThan51A() {
		for (int i=-10;i<=50;i++) {
			//assertEquals(i + ": ",Util.lessThan51(i),Util.AGE_50_OR_LESS);
		}
		
	}
	
	@Test
	public void testLessThan51B() {
		for (int i=51;i<150;i++) {
			//assertEquals(i + ": ",Util.lessThan51(i),Util.AGE_51_OR_MORE);
		}
		
	}
	
	@Test
	public void testFemaleMolesGroups() {
		assertEquals(0 + ": ",Util.F_MOLES_0,Util.femaleMolesGroups(0));
		
		assertEquals(1 + ": ",Util.F_MOLES_1,Util.femaleMolesGroups(1));
		assertEquals(2 + ": ",Util.F_MOLES_2,Util.femaleMolesGroups(2));
		
		
		
		
	}
	
	@Test
	public void testToAgeGroup() {
		for (int i=20;i<=24;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),0);
		}
		for (int i=25;i<=29;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),1);
		}
		for (int i=30;i<=34;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),2);
		}
		for (int i=35;i<=39;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),3);
		}
		for (int i=40;i<=44;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),4);
		}
		for (int i=45;i<=49;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),5);
		}
		for (int i=50;i<=54;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),6);
		}
		for (int i=55;i<=59;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),7);
		}
		for (int i=60;i<=64;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),8);
		}
		for (int i=65;i<=69;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),9);
		}
		for (int i=70;i<=74;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),10);
		}
		for (int i=75;i<=79;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),11);
		}
		for (int i=80;i<=84;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),12);
		}
		for (int i=85;i<=200;i++) {
			assertEquals(i + ": ",Util.toAgeGroup(i),13);
		}
		
		
	}
	
	
	@Test
	public void testriskToXML() {
	
		String expected1 = 
				"<?xml version=\"1.0\"?>" + "<MR><RISK>" + "0.0000" 
				+ "</RISK>" + "<DESCRIPTION>" + Util.LOW_RISK_TEXT
				+ "</DESCRIPTION>" + "</MR>";
		assertEquals("1:",expected1, Util.riskToXML(0.0000f));
		
		String expected2 = 
				"<?xml version=\"1.0\"?>" + "<MR><RISK>" + "0.0001" 
				+ "</RISK>" + "<DESCRIPTION>" + Util.LOW_RISK_TEXT
				+ "</DESCRIPTION>" + "</MR>";
		assertEquals("2:",expected2, Util.riskToXML(0.0001f));
		
		expected2 = 
				"<?xml version=\"1.0\"?>" + "<MR><RISK>" + "0.0027" 
				+ "</RISK>" + "<DESCRIPTION>" + Util.LOW_RISK_TEXT
				+ "</DESCRIPTION>" + "</MR>";
		assertEquals("2b:",expected2, Util.riskToXML(0.0027f));
		
		
		String expected3 = 	
				"<?xml version=\"1.0\"?>" + "<MR><RISK>" + "0.0027" 
				+ "</RISK>" + "<DESCRIPTION>" + Util.MODERATE_RISK_TEXT
				+ "</DESCRIPTION>" + "</MR>";
		assertEquals("3:",expected3, Util.riskToXML(0.002701f));
		
		expected3 = 	
				"<?xml version=\"1.0\"?>" + "<MR><RISK>" + "0.0100" 
				+ "</RISK>" + "<DESCRIPTION>" + Util.MODERATE_RISK_TEXT
				+ "</DESCRIPTION>" + "</MR>";
		assertEquals("3b:",expected3, Util.riskToXML(0.0100f));
		
		expected3 = 	
				"<?xml version=\"1.0\"?>" + "<MR><RISK>" + "0.0101" 
				+ "</RISK>" + "<DESCRIPTION>" + Util.HIGH_RISK_TEXT
				+ "</DESCRIPTION>" + "</MR>";
		assertEquals("3b:",expected3, Util.riskToXML(0.0101f));
		
		expected3 = 	
				"<?xml version=\"1.0\"?>" + "<MR><RISK>" + "0.0170" 
				+ "</RISK>" + "<DESCRIPTION>" + Util.HIGH_RISK_TEXT
				+ "</DESCRIPTION>" + "</MR>";
		assertEquals("3b:",expected3, Util.riskToXML(0.0170f));
		
		expected3 = 	
				"<?xml version=\"1.0\"?>" + "<MR><RISK>" + "0.0171" 
				+ "</RISK>" + "<DESCRIPTION>" + Util.VERY_HIGH_RISK_TEXT
				+ "</DESCRIPTION>" + "</MR>";
		assertEquals("3b:",expected3, Util.riskToXML(0.0171f));
		
		expected3 = 	
				"<?xml version=\"1.0\"?>" + "<MR><RISK>" + "0.0500" 
				+ "</RISK>" + "<DESCRIPTION>" + Util.VERY_HIGH_RISK_TEXT
				+ "</DESCRIPTION>" + "</MR>";
		assertEquals("3b:",expected3, Util.riskToXML(0.0500f));
		
		
	}
	
	

	
}
