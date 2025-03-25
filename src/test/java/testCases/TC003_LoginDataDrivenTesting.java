package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import PageObjects.LoginPage;
import PageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_LoginDataDrivenTesting extends BaseClass{
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups = "Datadriven") // getting data from other package
	public void verify_loginDDT(String email, String pwd, String exp) { // Here exp is expected result
		
		logger.info("***** Starting TC003_LoginDataDrivenTesting *****");
		
		try {
		// HomePage
				HomePage hp = new HomePage(driver);
				hp.clickMyAccount();
				hp.clickLogin();
				
				// LoginPage
				LoginPage lp = new LoginPage(driver);
				lp.setEmail(email);
				lp.setPassword(pwd);
				lp.clickLogin();
				
				// MyAccountPage
				MyAccountPage macc = new MyAccountPage(driver);
				boolean targetPage = macc.isMyAccountPageExists();
				
				
				/* Data is valid - login success - test pass - logout
				 * Data is valid - login failed - test fail*/
				 
				/* Data is invalid - login success - test pass - logout
				 * Data is invalid - login failed - test pass*/
				
				if(exp.equalsIgnoreCase("Valid")) { // positive testing
					if(targetPage==true) {
						macc.clickLogout();
						Assert.assertTrue(true);
					}
					else {
						Assert.assertTrue(false);
					}
				}
				
				if(exp.equalsIgnoreCase("Invalid")) { // negative testing
					if(targetPage==true) {
						macc.clickLogout();
						Assert.assertTrue(false);
					}
					else {
						Assert.assertTrue(true);
					}
				}
				
		}
		catch(Exception e) {
			Assert.fail();
		}
				
				logger.info("***** Finished TC003_LoginDataDrivenTesting *****");
	}
	
}
