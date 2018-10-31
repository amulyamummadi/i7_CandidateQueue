package com.citrix.elearning.candidatemerge.tests;

import java.util.Date;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.citrix.elearning.candidatemerge.pages.BasePage;
import com.citrix.elearning.candidatemerge.utils.CandidatePojo;

public class CandidateMergeTest extends BaseTest {
	/**
	 * Method to perform the actions after login and before logout.
	 */
	@Test
	public void recordClearing() {
		final Logger logger = Logger.getLogger(BasePage.class);
		final CandidatePojo candidatePojo = new CandidatePojo();
		try {
			logger.info("In candidateQueue page, getting name of the 1st record.");
			final String fullName = candidateQueuePage.getName(numberParsed);
			final String[] str = fullName.split(",");
			final String firstName = str[1];
			logger.info("FirstName is=" + firstName);
			final String lastName = str[0];
			logger.info("LastName is=" + lastName);
			candidatePojo.setLastName(lastName);
			candidatePojo.setFirstName(firstName);

			logger.info("Clicking on candidate queue record.");
			candidateMatchingPage = candidateQueuePage.clickQueueRecord(numberParsed);

			logger.info("Get candidate id from candidate matching page.");
			final String candidateId = candidateMatchingPage.getCandidateId();
			candidatePojo.setCandidateId(candidateId);

			logger.info("Clicking on last name of the master record.");
			candidateReconciliationPage = candidateMatchingPage.clickLastName();

			logger.info("Getting emails of inbound and master records.");
			final String inboundEmailText = candidateReconciliationPage.getInboundEmailText();
			logger.info("Inbound email text: " + inboundEmailText);
			final String masterEmailText = candidateReconciliationPage.getMasterEmailText();
			logger.info("Master email text: " + masterEmailText);
			final String alternativeEmailText = candidateReconciliationPage.getAlternativeEmailText();
			logger.info("Alternative email text: " + alternativeEmailText);

			logger.info("Getting dates of inbound and master records.");
			final Date inboundDate = candidateReconciliationPage.getInboundDate();
			logger.info("Inbound date in Date format: " + inboundDate);
			final Date masterDate = candidateReconciliationPage.getMasterDate();
			logger.info("Master date in Date format: " + masterDate);

			logger.info("Comparing inbound, master emails and last update dates.");
			if (inboundEmailText.equals(masterEmailText) || inboundEmailText.equals(alternativeEmailText)) {
				if (inboundDate.compareTo(masterDate) < 0 || inboundDate.compareTo(masterDate) == 0) {

					final String dontApplyText = candidateReconciliationPage.getDontApplyText();
					candidatePojo.setResult(dontApplyText);

					logger.info("Clicking on Don't apply link.");
					candidateReconciliationPage.clickDontApply();
					candidateReconciliationPage.verifydontApplyLinkAlert();

				} else if (inboundDate.compareTo(masterDate) > 0) {

					final String applyText = candidateReconciliationPage.getApplyText();
					candidatePojo.setResult(applyText);

					logger.info("Clicking on apply link.");
					candidateReconciliationPage.clickApply();
					candidateReconciliationPage.verifyApplyLinkAlert();
				}
			} else {
				final String createNewText = candidateReconciliationPage.getCreateNewText();
				candidatePojo.setResult(createNewText);

				logger.info("Clicking on create new link.");
				candidateReconciliationPage.clickCreateNew();
				candidateReconciliationPage.verifyCreateNewLinkAlert();
			}
			logger.info("Going back to candidate queue by clicking candidate queue tab.");
			findCandidatePage.clickCandidateQueue();

			logger.info("Searching by Name: " + candidateQueuePage.getName(numberParsed));
			candidateQueuePage.searchTextboxAndSendName(numberParsed);

			logger.info("Verify candidate record (candidate )is cleared from queue.");
			final boolean nameAndQueuedDate = candidateQueuePage.isQueuedDateIsPresent();
			Assert.assertTrue(nameAndQueuedDate);

			logger.info("Clicking on candidate queue tab");
			findCandidatePage.clickCandidateQueue();

		} catch (final Exception e) {
			logger.error("Error occured here.");
			candidatePojo.setFailureReason(e.getMessage());
		}
		logger.info("Adding candidate pojo to list.");
		candidateList.add(candidatePojo);
	}

}
