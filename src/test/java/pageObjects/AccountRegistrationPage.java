package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) 
	{
		super(driver);
	}
	
	
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="(//input[@id='input-lastname'])[1]")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail ;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement textTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtpassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement checkedPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname) {
		txtFirstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txtLastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void setTelephone(String telphone) {
		textTelephone.sendKeys(telphone);
	}
	
	public void setPassword(String pwd) {
		txtpassword.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String cnfpwd) {
		txtConfirmPassword.sendKeys(cnfpwd);
	}
	
	public void setPrivacyPolicy() {
		checkedPolicy.click();
	}
	
	public void clickConfirm() {
		btnContinue.click();
	}
	
	public String getConfirmationmsg() {
		try {
			return msgConfirmation.getText();
		}catch(Exception e) {
			return e.getMessage();
		}
	}

}
