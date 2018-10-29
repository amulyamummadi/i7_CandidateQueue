package com.citrix.elearning.candidatemerge.pages.candidatereconciliation;

import java.util.Date;

import javax.swing.text.Element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.citrix.elearning.candidatemerge.pages.BasePage;

/**
 * This page class is for comparing mails and dates of Master and Inbound
 * records
 *
 * @author amulya.mummadi
 *
 */
public class CandidateReconciliationPage extends BasePage {
	/**
	 * Web Element for InboundEmail
	 */
	@FindBy(xpath = "//table[@id=\"emailTableA\"]/tbody/tr/td/table/tbody/tr[2]/td[2]/span")
	WebElement inboundEmail;
	/**
	 * Web Element for MasterEmail
	 */
	@FindBy(xpath = "//table[@id='emailTableB']//span[contains(text(),'Primary Email Address')]/../following-sibling::td")
	WebElement masterEmail;
	/**
	 * Web {@link Element} for AlternariveEmail
	 */
	@FindBy(xpath = "//table[@id='emailTableB']//span[contains(text(),'Alternative Email Address')]/../following-sibling::td")
	WebElement alternativeEmail;
	/**
	 * Web ELement for InboundDate
	 */
	@FindBy(xpath = "//form[@name=\"candidateMergeDetailForm\"]/table/tbody/tr[5]/td[1]/table/tbody/tr/td[2]")
	WebElement stringInboundDate;
	/**
	 * Web Element for MasterDate;
	 */
	@FindBy(xpath = "//form[@name=\"candidateMergeDetailForm\"]/table/tbody/tr[5]/td[5]/table/tbody/tr/td[2]")
	WebElement stringMasterDate;
	/**
	 * Web Element for Don't apply link
	 */
	@FindBy(linkText = "Don't Apply Inbound Record to Master Record")
	WebElement dontapply;
	/**
	 * Web Element for creating new record
	 */
	@FindBy(linkText = "Create a new Master Record")
	WebElement createNew;
	/**
	 * Web Element for apply Inbound record
	 */
	@FindBy(linkText = "Apply Inbound Record to Master Record")
	WebElement apply;

	/**
	 * Constructor Initialization
	 *
	 * @param driver
	 *            {@link WebDriver}
	 */
	public CandidateReconciliationPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);

	}

	/**
	 * Method to click on Apply link
	 *
	 * @return true/false
	 */
	public boolean clickApply() {
		explicitWait(apply);
		apply.click();
		return true;
	}

	/**
	 * Method to click on Create New link
	 */
	public void clickCreateNew() {
		explicitWait(createNew);
		createNew.click();
	}

	/**
	 * Method to click on Don't Apply link
	 *
	 * @return true/false
	 */
	public boolean clickDontApply() {
		explicitWait(dontapply);
		dontapply.click();
		return true;
	}

	/**
	 * method to close the Alert
	 */
	public void closeAlert() {
		closePopup();
	}

	/**
	 * Method to get AlternativeEmailText
	 *
	 * @return email text
	 */
	public String getAlternativeEmailText() {
		return getTextOfElement(alternativeEmail);
	}

	/**
	 * Method to get Apply link text
	 *
	 * @return link text
	 */
	public String getApplyText() {
		explicitWait(apply);
		return apply.getText();
	}

	/**
	 * Method to get Create New link text
	 *
	 * @return link text
	 */
	public String getCreateNewText() {
		explicitWait(createNew);
		return createNew.getText();
	}

	/**
	 * Method to get Don't Apply link text
	 *
	 * @return link text
	 */
	public String getDontApplyText() {
		explicitWait(dontapply);
		return dontapply.getText();
	}

	/**
	 * Method to get InboundDate
	 *
	 * @return Date
	 */
	public Date getInboundDate() {
		explicitWait(stringInboundDate);
		System.out.println(getTextOfElement(stringInboundDate));
		final String inboundDate = getTextOfElement(stringInboundDate);
		return stringToDateConversion(inboundDate, "MM/dd/yyyy hh:mm:ss");

	}

	/**
	 * Method to get InboundEmailText
	 *
	 * @return email text
	 */
	public String getInboundEmailText() {
		return getTextOfElement(inboundEmail);
	}

	/**
	 * Method to get MasterDate
	 *
	 * @return date
	 */
	public Date getMasterDate() {
		explicitWait(stringMasterDate);

		return stringToDateConversion(stringMasterDate.getText(), "MM/dd/yyyy hh:mm:ss");
	}

	/**
	 * Method to get MasterEmailText
	 *
	 * @return email text
	 */
	public String getMasterEmailText() {
		return getTextOfElement(masterEmail);

	}

}
