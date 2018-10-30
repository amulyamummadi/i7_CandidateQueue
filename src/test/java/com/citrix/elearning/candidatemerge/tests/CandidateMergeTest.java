package com.citrix.elearning.candidatemerge.tests;

import java.util.Date;

import org.testng.annotations.Test;

import com.citrix.elearning.candidatemerge.utils.CandidatePojo;

public class CandidateMergeTest extends BaseTest {
	/**
	 * Method to perform the actions after login and before logout
	 */
	@Test
	public void recordClearing() {
		final CandidatePojo candidatePojo = new CandidatePojo();
		try {
			logger.info("In candidateQueue page, getting name of the 1st record");
			final String fullName = candidateQueuePage.getName(numberParsed);
			final String[] str = fullName.split(",");
			final String firstName = str[1];
			logger.info("FirstName is=" + firstName);
			final String lastName = str[0];
			logger.info("LastName is=" + lastName);
			candidatePojo.setLastName(lastName);
			candidatePojo.setFirstName(firstName);

			logger.info("Clicking on candidateQueue record");
			candidateMatchingPage = candidateQueuePage.clickQueueRecord(numberParsed);

			logger.info("In CandidateMatching page, getting candidateId of the record ");
			final String candidateId = candidateMatchingPage.getCandidateId();
			candidatePojo.setCandidateId(candidateId);

			logger.info("Clicking on last name of the master record");
			candidateReconciliationPage = candidateMatchingPage.clickLastName();

			logger.info("Getting emails of inbound and master records");
			final String inboundEmailText = candidateReconciliationPage.getInboundEmailText();
			logger.info("InboundEmailText=" + inboundEmailText);
			final String masterEmailText = candidateReconciliationPage.getMasterEmailText();
			logger.info("MasterEmailText=" + masterEmailText);
			final String alternativeEmailText = candidateReconciliationPage.getAlternativeEmailText();
			logger.info("AlternativeEmailText=" + alternativeEmailText);

			logger.info("Getting dates of inbound and master records");
			final Date inboundDate = candidateReconciliationPage.getInboundDate();
			logger.info("InboundDate in Date format=" + inboundDate);
			final Date masterDate = candidateReconciliationPage.getMasterDate();
			logger.info("MasterDate in Date format=" + masterDate);

			final boolean result = candidateReconciliationPage.isAlertPresent();
			logger.info("Comparing emails and dates");
			if (inboundEmailText.equals(masterEmailText) || inboundEmailText.equals(alternativeEmailText)) {
				if (inboundDate.compareTo(masterDate) < 0 || inboundDate.compareTo(masterDate) == 0) {

					final String dontApplyText = candidateReconciliationPage.getDontApplyText();
					candidatePojo.setResult(dontApplyText);

					logger.info("Clicking on Don't apply link");
					candidateReconciliationPage.clickDontApply();
					if (result) {
						final String applyLinkText = candidateReconciliationPage.alertText();
						if (applyLinkText.equals(
								"Please verify that you wish to update only the VUE record with the new data.")) {
							candidateReconciliationPage.closeAlert();
						} else {
							candidateReconciliationPage.closeAlert();
						}
					}

				} else if (inboundDate.compareTo(masterDate) > 0) {

					final String applyText = candidateReconciliationPage.getApplyText();
					candidatePojo.setResult(applyText);

					logger.info("Clicking on apply link");
					candidateReconciliationPage.clickApply();
					if (result) {
						final String applyLinkText = candidateReconciliationPage.alertText();
						if (applyLinkText.equals(
								"This inbound record has an older revision date than the Master Record.  The demographic information in the inbound record will not be applied to the Master Record.")) {
							candidateReconciliationPage.closeAlert();
						} else {
							candidateReconciliationPage.closeAlert();
						}
					}
				}
			} else {
				final String createNewText = candidateReconciliationPage.getCreateNewText();
				candidatePojo.setResult(createNewText);

				logger.info("Clicking on CreateNew link");
				candidateReconciliationPage.clickCreateNew();
				if (result) {
					final String createNewLinkText = candidateReconciliationPage.alertText();
					if (createNewLinkText
							.equals("Please verify that you wish to create a new candidate record with this data.")) {
						candidateReconciliationPage.closeAlert();
					} else {
						candidateReconciliationPage.closeAlert();
					}
				}
			}
			logger.info("Going back to CandidateQueue by clicking CandidateQueue tab");
			findCandidatePage.clickCandidateQueue();

			logger.info("Searching by Name");
			candidateQueuePage.searchTextboxAndSendName(numberParsed);

			logger.info("By using combination of QueueDate and name,verifing whether record is there or not");
			candidateQueuePage.isDisplay();

		} catch (final Exception e) {
			logger.error("Error occured here");
			candidatePojo.setFailureResult(e.getMessage());
		}
		logger.info("Adding CandidatePojo to list");
		candidateList.add(candidatePojo);
	}

}
