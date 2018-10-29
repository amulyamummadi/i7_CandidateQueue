package com.citrix.elearning.candidatemerge.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author amulya.mummadi
 *
 */
public class BasePage {

	public static Logger logger = Logger.getLogger(BasePage.class.getName());

	/**
	 * Instance variable for webdriver.
	 */
	protected WebDriver driver;

	/**
	 * Constructor to initialize the webdriver.
	 *
	 * @param driver
	 *            {@link WebDriver}
	 */
	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Method to clear and type the text.
	 *
	 * @param element
	 *            {@link WebElement}
	 * @param text
	 *            the element text to set.
	 */
	public void clearAndType(WebElement element, String text) {
		try {
			element.clear();
		} catch (final Exception e) {
			logger.error(e.getMessage());
		}
		element.sendKeys(text);
	}

	/**
	 * Method to close the alerts
	 */
	public void closePopup() {
		try {
			new WebDriverWait(driver, 30).until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert().dismiss();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to explicitWait i.e, till the visibility of element
	 *
	 * @param element
	 *            the element to get the path
	 */
	public void explicitWait(WebElement element) {
		new WebDriverWait(driver, 50).until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * Method to get the text from elements loacted
	 *
	 * @param element
	 *            {@link WebElement}
	 * @return {@value text}
	 */
	public String getTextOfElement(WebElement element) {
		String text = null;
		try {
			text = element.getText();
		} catch (final Exception e) {
		}
		return text;
	}

	/**
	 * Method to Convert String format to Date format
	 *
	 * @param stringDate
	 *            the element string format date to send
	 * @return {@value date}
	 */
	public Date stringToDateConversion(String stringDate, String dateFormat) {
		final SimpleDateFormat simpledate = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = simpledate.parse(stringDate);

		} catch (final ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
