package com.citrix.elearning.candidatemerge.pages.candidatequeue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.pages.BasePage;
import com.citrix.elearning.candidatemerge.pages.candidatematching.CandidateMatchingPage;
import com.citrix.elearning.candidatemerge.utils.PropertyUtil;

/**
 * This page class contains functionality of finding a record from
 * candidateQueue, getting full name and queued date of the respected record.
 *
 *
 * @author amulya.mummadi
 *
 */

public class CandidateQueuePage extends BasePage {
	final int numberParsed = Integer.parseInt(PropertyUtil.getProperty("number"));

	@FindBy(xpath = "//tr[@id=\"candidateQueueDataTableForm:candQueueTable:ch\"]/th[6]/input")
	WebElement searchTextboxxPath;

	/**
	 * Constructor Initialization.
	 *
	 * @param driver
	 *            {@link WebDriver}
	 */
	public CandidateQueuePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	/**
	 * Method to select the record of CandidateQueuePage.
	 *
	 * @param string
	 *            the element number to be set.
	 * @return {@link CandidateMatchingPage}
	 */
	public CandidateMatchingPage clickQueueRecord(int num) {
		final WebElement RecordElement = getNamexPath(num);
		RecordElement.click();
		logger.info("Navigating to CandidateMatchingPage");
		return new CandidateMatchingPage(driver);
	}

	/**
	 * Method to get the name of the person.
	 *
	 * @param num
	 *            the number to set.
	 * @return name.
	 */
	public String getName(int num) {
		return getNamexPath(num).getText();
	}

	/**
	 * Method to get the xPath of the name location.
	 *
	 * @param number
	 *            the number to set.
	 * @return web element of name.
	 */
	public WebElement getNamexPath(int number) {
		final String Recordxpath = "(//tr//td[6])[" + number + "]/a";
		return driver.findElement(By.xpath(Recordxpath));
	}

	/**
	 * Method to get Queued date.
	 *
	 * @param number
	 *            the number to set.
	 * @return {@link QueuedDate }
	 */
	public String getQueuedDate(int number) {
		final String queuedDatexPath = "(//*[@id=\"candidateQueueDataTableForm:candQueueTable\"]/tbody/tr/td)[" + number
				+ "]";
		final WebElement stringQueued = driver.findElement(By.xpath(queuedDatexPath));
		final String stringQueuedDate = stringQueued.getText();
		logger.info("Queued date from candidate queue page" + stringQueuedDate);
		return stringQueuedDate;
	}

	/**
	 * Method to get combination of Queued date and Name.
	 *
	 * @param number
	 *            the number to set.
	 * @return Web Element of Name And Queued date.
	 */
	public WebElement getQueuedDateAndName(int number) {
		final String name = getName(numberParsed);
		final String queuedDate = getQueuedDate(numberParsed);
		final String dateAndName = "//td[text()='" + queuedDate + "']/following-sibling::td/a[text()='" + name + "']";
		final WebElement nameAndQueuedDate = driver.findElement(By.xpath(dateAndName));
		return nameAndQueuedDate;

	}

	/**
	 * Method to say whether Queued date and name is visible or not.
	 */
	public boolean isQueuedDateIsPresent() {
		return getQueuedDateAndName(numberParsed).isDisplayed();
	}

	/**
	 * Method to set name in SearchBox.
	 *
	 * @param num
	 *            the number to set.
	 */
	public void searchTextboxAndSendName(int num) {
		final String name = getNamexPath(num).getText();
		logger.info("Name entered in textBox is=" + name);
		searchTextboxxPath.sendKeys(name);
	}
}