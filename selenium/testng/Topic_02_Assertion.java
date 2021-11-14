package testng;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assertion {
	
	@Test
	public void TC_01_Assert() {
		
		boolean status = 3 > 5;
		
		Assert.assertTrue(status);
//		Assert.assertTrue(status, "3 less than 5");
		
		Assert.assertFalse(status);

	}

}
