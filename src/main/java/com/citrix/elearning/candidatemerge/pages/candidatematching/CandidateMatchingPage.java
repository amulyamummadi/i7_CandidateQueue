package com.citrix.elearning.candidatemerge.pages.candidatematching;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.pages.BasePage;
import com.citrix.elearning.candidatemerge.pages.candidatereconciliation.CandidateReconciliationPage;

/**
 * This page class contains functionality of clicking on Lastname of selected
 * record and getting candidate id.
 *
 * @author amulya.mummadi
 *
 */
public class CandidateMatchingPage extends BasePage {
	/**
	 * Web Element for candidate id.
	 */
	@FindBy(xpath = "//tr[4]/td[9]")
	WebElement candidateID;
	/**
	 * Web Element for lastname link.
	 */
	@FindBy(xpath = "//tr[4]/td[5]/a")
	WebElement lastNameLink;

	/**
	 * Constructor Initialization.
	 *
	 * @param driver
	 *            {@link WebDriver}
	 *
	 */
	public CandidateMatchingPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	/**
	 * Method to click on the lastname of master_record.
	 *
	 * @return {@link CandidateReconciliationPage}
	 */
	public CandidateReconciliationPage clickLastName() {
		lastNameLink.click();
		logger.info("Navigating to CandidateReconciliationPage");
		return new CandidateReconciliationPage(driver);
	}

	/**
	 * Method to get candidate id.
	 *
	 * @return candidate id.
	 */
	public String getCandidateId() {
		return candidateID.getText();
	}

}
