package com.citrix.elearning.candidatemerge.pages.findcandidate;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.pages.BasePage;
import com.citrix.elearning.candidatemerge.pages.candidatequeue.CandidateQueuePage;

/**
 * This page class contains Selecting CandidateQueue tab functionality.
 *
 * @author amulya.mummadi
 *
 */
public class FindCandidatePage extends BasePage {

	/**
	 * Web element for candidateQueueLink.
	 */

	@FindBy(linkText = "Candidate Queue")
	WebElement candidateQueueLink;

	/**
	 * constructor initialization.
	 *
	 * @param driver
	 *            {@link WebDriver}
	 */
	public FindCandidatePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to select the CandidateQueueLink.
	 *
	 * @return {@link CandidateQueuePage}
	 */
	public CandidateQueuePage clickCandidateQueue() {
		candidateQueueLink.click();
		logger.info("Navigating to CandidateQueuePage");
		return new CandidateQueuePage(driver);
	}
}
