package com.citrix.elearning.candidatemerge.pages.candidatereconciliation;

import java.util.Date;

import javax.swing.text.Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.pages.BasePage;

/**
 * This page class contains functionality of comparing emails and dates of
 * Master and Inbound records .
 *
 * @author amulya.mummadi
 *
 */
public class CandidateReconciliationPage extends BasePage {
	final String dateFormat = "MM/dd/yyyy hh:mm:ss";
	/**
	 * Web Element for inbound email.
	 */
	@FindBy(xpath = "//table[@id=\"emailTableA\"]/tbody/tr/td/table/tbody/tr[2]/td[2]/span")
	WebElement inboundEmail;
	/**
	 * Web Element for master email.
	 */
	@FindBy(xpath = "//table[@id='emailTableB']//span[contains(text(),'Primary Email Address')]/../following-sibling::td")
	WebElement masterEmail;
	/**
	 * Web {@link Element} for alternarive email.
	 */
	@FindBy(xpath = "//table[@id='emailTableB']//span[contains(text(),'Alternative Email Address')]/../following-sibling::td")
	WebElement alternativeEmail;
	/**
	 * Web ELement for inbound date.
	 */
	@FindBy(xpath = "//form[@name=\"candidateMergeDetailForm\"]/table/tbody/tr[5]/td[1]/table/tbody/tr/td[2]")
	WebElement stringInboundDate;
	/**
	 * Web Element for master date.
	 */
	@FindBy(xpath = "//form[@name=\"candidateMergeDetailForm\"]/table/tbody/tr[5]/td[5]/table/tbody/tr/td[2]")
	WebElement stringMasterDate;
	/**
	 * Web Element for don't apply link.
	 */
	@FindBy(linkText = "Don't Apply Inbound Record to Master Record")
	WebElement dontapply;
	/**
	 * Web Element for creating new record.
	 */
	@FindBy(linkText = "Create a new Master Record")
	WebElement createNew;
	/**
	 * Web Element for apply inbound record.
	 */
	@FindBy(linkText = "Apply Inbound Record to Master Record")
	WebElement apply;

	/**
	 * Constructor Initialization.
	 *
	 * @param driver
	 *            {@link WebDriver}
	 */
	public CandidateReconciliationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	/**
	 * Method to click on apply link.
	 *
	 * @return true/false
	 */
	public boolean clickApply() {
		explicitWait(apply);
		apply.click();
		return true;
	}

	/**
	 * Method to click on create new link.
	 */
	public void clickCreateNew() {
		explicitWait(createNew);
		createNew.click();
	}

	/**
	 * Method to click on don't apply link.
	 *
	 * @return true/false.
	 */
	public boolean clickDontApply() {
		explicitWait(dontapply);
		dontapply.click();
		return true;
	}

	/**
	 * Method to get alternative email text.
	 *
	 * @return email text.
	 */
	public String getAlternativeEmailText() {
		return getTextOfElement(alternativeEmail);
	}

	/**
	 * Method to get apply link text.
	 *
	 * @return link text.
	 */
	public String getApplyText() {
		explicitWait(apply);
		return apply.getText();
	}

	/**
	 * Method to get create new link text.
	 *
	 * @return link text.
	 */
	public String getCreateNewText() {
		explicitWait(createNew);
		return createNew.getText();
	}

	/**
	 * Method to get don't apply link text.
	 *
	 * @return link text.
	 */
	public String getDontApplyText() {
		explicitWait(dontapply);
		return dontapply.getText();
	}

	/**
	 * Method to get inbound date.
	 *
	 * @return {@link Date}
	 */
	public Date getInboundDate() {
		explicitWait(stringInboundDate);
		logger.info("InboundDate in String format " + getTextOfElement(stringInboundDate));
		final String inboundDate = getTextOfElement(stringInboundDate);
		return stringToDateConversion(inboundDate, dateFormat);

	}

	/**
	 * Method to get inbound email text.
	 *
	 * @return email text.
	 */
	public String getInboundEmailText() {
		return getTextOfElement(inboundEmail);
	}

	/**
	 * Method to get master date.
	 *
	 * @return {@link date}
	 */
	public Date getMasterDate() {
		explicitWait(stringMasterDate);
		logger.info("MasterDate in String format " + getTextOfElement(stringInboundDate));
		return stringToDateConversion(stringMasterDate.getText(), dateFormat);
	}

	/**
	 * Method to get master email text.
	 *
	 * @return email text.
	 */
	public String getMasterEmailText() {
		return getTextOfElement(masterEmail);

	}

	/**
	 * Method to verify Apply link alert and it's text
	 */
	public void verifyApplyLinkAlert() {
		final boolean result = isAlertPresent();
		if (result) {
			final String applyLinkText = alertText();
			if (applyLinkText.contains(
					"This inbound record has an older revision date than the Master Record.  The demographic information in the inbound record will not be applied to the Master Record.")) {
				closeAlert();
			} else {
				closeAlert();
			}
		}
	}

	/**
	 * Method to verify Create new link alert and it's text
	 */
	public void verifyCreateNewLinkAlert() {
		final boolean result = isAlertPresent();
		if (result) {
			final String createNewLinkText = alertText();
			if (createNewLinkText
					.contains("Please verify that you wish to create a new candidate record with this data.")) {
				closeAlert();
			} else {
				closeAlert();
			}
		}
	}

	/**
	 * Method to verify Don't apply link alert and it's text
	 */
	public void verifydontApplyLinkAlert() {
		final boolean result = isAlertPresent();
		if (result) {
			final String dontApplyLinkText = alertText();
			if (dontApplyLinkText
					.contains("Please verify that you wish to update only the VUE record with the new data.")) {
				closeAlert();
			} else {
				closeAlert();
			}
		}
	}
}
