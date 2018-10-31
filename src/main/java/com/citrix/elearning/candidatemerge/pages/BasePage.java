package com.citrix.elearning.candidatemerge.pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class contains generic methods for all classes.
 *
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
	 * Method to accept the alert
	 */
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	/**
	 * Method to get alert text
	 *
	 * @return link text
	 */
	public String alertText() {
		final Alert alert = driver.switchTo().alert();
		return alert.getText();
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
	 * Method to close the alert
	 */
	public void closeAlert() {
		driver.switchTo().alert().dismiss();
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
	 * Method to get the text from elements located
	 *
	 * @param element
	 *            {@link WebElement}
	 * @return text of web element.
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
	 * Method to verify alert is present or not.
	 *
	 * @return true/false
	 */
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (final NoAlertPresentException Ex) {
			return false;
		}
	}

	/**
	 * Method to convert string format to date format.
	 *
	 * @param date
	 *            string to send.
	 * @return {@link Date}
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
