package com.citrix.elearning.candidatemerge.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.pages.BasePage;
import com.citrix.elearning.candidatemerge.pages.findcandidate.FindCandidatePage;

/**
 * This page class contains login functionalities.
 *
 * @author amulya.mummadi
 *
 */
public class LoginPage extends BasePage {

	/**
	 * Web element for username.
	 */
	@FindBy(name = "username")
	WebElement userName;

	/**
	 * Web element for password.
	 */
	@FindBy(name = "password")
	WebElement password;

	/**
	 * Web element for submit button.
	 */
	@FindBy(name = "submit")
	WebElement submitButton;

	/**
	 * Constructor initialization.
	 *
	 * @param driver
	 *            {@link WebDriver}
	 */
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to click on submit button.
	 */
	public void clickSubmit() {
		submitButton.click();
	}

	/**
	 * Method to login the application.
	 *
	 * @param userName
	 *            the userName to set.
	 * @param password
	 *            the password to set.
	 * @return {@link FindCandidatePage}
	 */
	public FindCandidatePage login(String userName, String password) {
		setUsername(userName);
		setPassword(password);
		clickSubmit();
		logger.info("Navigating to FindCandidatePage");
		return new FindCandidatePage(driver);
	}

	/**
	 * Method to set password.
	 *
	 * @param password
	 *            the password to set.
	 */
	public void setPassword(String password) {
		clearAndType(this.password, password);
	}

	/**
	 * Method to set username.
	 *
	 * @param userName
	 *            the username to set.
	 */
	public void setUsername(String userName) {
		clearAndType(this.userName, userName);
	}
}
