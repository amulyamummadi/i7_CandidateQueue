package com.citrix.elearning.candidatemerge.tests;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.BasicConfigurator;
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
 * This Test class contains all actions.
 *
 * @author amulya.mummadi
 *
 */
public class BaseTest {
	private static Logger logger = Logger.getLogger(BasePage.class);
	protected WebDriver driver;
	FindCandidatePage findCandidatePage;
	CandidateQueuePage candidateQueuePage;
	CandidateMatchingPage candidateMatchingPage;
	CandidateReconciliationPage candidateReconciliationPage;

	List<CandidatePojo> candidateList = new ArrayList<CandidatePojo>();

	final int numberParsed = Integer.parseInt(PropertyUtil.getProperty("number"));

	private WebDriver getWebDriver(String browserName) {
		if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "src\\test\\resources\\binaries\\chromedriver.exe");
			driver = new ChromeDriver();
		} else {
			System.setProperty("wedriver.gecko.driver", "src\\test\\resources\\binaries\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		return driver;
	}

	/**
	 * Method to set up url and login details.
	 */
	@BeforeTest
	public void setUp() {
		BasicConfigurator.configure();
		final String browser = PropertyUtil.getProperty("driver");
		driver = getWebDriver(browser);
		final String url = PropertyUtil.getProperty("url");
		logger.info("Loading url: " + url);
		driver.get(url);
		final LoginPage loginPage = new LoginPage(driver);
		logger.info("Entering login credentials");
		final String username = PropertyUtil.getProperty("username");
		final String password = PropertyUtil.getProperty("password");
		findCandidatePage = loginPage.login(username, password);
		logger.info("Clicking CandidateQueue Tab");
		candidateQueuePage = findCandidatePage.clickCandidateQueue();
	}

	/**
	 * Method to write excel data and close the browser.
	 */
	@AfterTest
	public void tearDown() {
		logger.info("Writing data to excel file.");
		WriteDataToExcelFile.excelFile(candidateList);
		if (driver != null) {
			driver.quit();
		}
	}

}
