package testCases;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() {
		
		logger.info("*** Starting TC001_AccountRegistrationTest ***");
		try {
		HomePage hp =new HomePage(driver);
		hp.clickMyAccount();
		logger.info("*** clicked on Myaccount ***");
		hp.clickRegister();
		logger.info("*** click on Register ***");
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("*** providing customer details ***");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString()+"@gamil.com");
		regpage.setTelephone(randomeNumber()+"123");
		
		String password = randomeAlphaNumeric();
		
		regpage.setPassword(password);
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickConfirm();
		
		String confmsg = regpage.getConfirmationmsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		}
		catch(Exception e) {
			logger.error("Test Failed");
			logger.debug("Debug logs...");
			Assert.fail();
		}
		
		logger.info("*** Finished TC001_AccountRegistrationTest ***");
	}  
	
	public String randomeString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(6);
		return generatedstring;
	}
	
	public String randomeNumber() {
		String generatednumber = RandomStringUtils.randomNumeric(5);
		return generatednumber;
	}
	
	public String randomeAlphaNumeric() {
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring+generatednumber);
	}

}
