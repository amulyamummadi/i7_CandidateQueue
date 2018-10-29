package com.citrix.elearning.candidatemerge.tests;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.citrix.elearning.candidatemerge.pages.BasePage;
import com.citrix.elearning.candidatemerge.pages.candidatematching.CandidateMatchingPage;
import com.citrix.elearning.candidatemerge.pages.candidatequeue.CandidateQueuePage;
import com.citrix.elearning.candidatemerge.pages.candidatereconciliation.CandidateReconciliationPage;
import com.citrix.elearning.candidatemerge.pages.findcandidate.FindCandidatePage;
import com.citrix.elearning.candidatemerge.pages.login.LoginPage;
import com.citrix.elearning.candidatemerge.utils.CandidatePojo;
import com.citrix.elearning.candidatemerge.utils.PropertyUtil;
import com.citrix.elearning.candidatemerge.utils.WriteDataToExcelFile;

/**
 * This Test class contains all actions
 *
 * @author amulya.mummadi
 *
 */
public class BaseTest {
	public static Logger logger = Logger.getLogger(BasePage.class.getName());
	protected WebDriver driver;
	FindCandidatePage findCandidatePage;
	CandidateQueuePage candidateQueuePage;
	CandidateMatchingPage candidateMatchingPage;
	CandidateReconciliationPage candidateReconciliationPage;

	List<CandidatePojo> candidateList = new ArrayList<CandidatePojo>();

	final int numberParsed = Integer.parseInt(PropertyUtil.getProperty("number"));

	private WebDriver getWebDriver(String browserName) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\test\\resource\\binaries\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.setProperty("wedriver.gecko.driver", "src\\test\\resource\\binaries\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		return driver;
	}

	/**
	 * Method to setUp url and login details
	 */
	@BeforeTest
	public void setUp() {
		final String browser = PropertyUtil.getProperty("driver");
		driver = getWebDriver(browser);
		driver.get(PropertyUtil.getProperty("url"));
		final LoginPage loginPage = new LoginPage(driver);
		findCandidatePage = loginPage.login(PropertyUtil.getProperty("username"), PropertyUtil.getProperty("password"));
		candidateQueuePage = findCandidatePage.clickCandidateQueue();
	}

	/**
	 * Method to exit from the site
	 */
	@AfterTest
	public void tearDown() {
		WriteDataToExcelFile.excelFile(candidateList);
		if (driver != null) {
			driver.quit();
		}
	}

}
