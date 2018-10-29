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

			final String fullName = candidateQueuePage.getName(numberParsed);
			final String[] str = fullName.split(",");
			final String firstName = str[1];
			logger.info(firstName);
			final String lastName = str[0];
			logger.info(lastName);
			candidatePojo.setLastName(lastName);
			candidatePojo.setFirstName(firstName);

			candidateMatchingPage = candidateQueuePage.clickQueueRecord(numberParsed);

			final String candidateId = candidateMatchingPage.getCandidateId();
			candidatePojo.setCandidateId(candidateId);

			candidateReconciliationPage = candidateMatchingPage.clickLastName();

			final String inboundEmailText = candidateReconciliationPage.getInboundEmailText();
			logger.info("inboundEmailText=" + inboundEmailText);
			final String masterEmailText = candidateReconciliationPage.getMasterEmailText();
			logger.info("masterEmailText=" + masterEmailText);
			final String alternativeEmailText = candidateReconciliationPage.getAlternativeEmailText();
			logger.info("alternativeEmailText=" + alternativeEmailText);

			final Date inboundDate = candidateReconciliationPage.getInboundDate();
			System.out.println(inboundDate);
			final Date masterDate = candidateReconciliationPage.getMasterDate();
			System.out.println(masterDate);
			if (inboundEmailText.equals(masterEmailText) || inboundEmailText.equals(alternativeEmailText)) {
				if (inboundDate.compareTo(masterDate) < 0 || inboundDate.compareTo(masterDate) == 0) {

					final String dontApplyText = candidateReconciliationPage.getDontApplyText();
					candidatePojo.setResult(dontApplyText);

					candidateReconciliationPage.clickDontApply();
					logger.info("Clicked on Don't apply");

					candidateReconciliationPage.closeAlert();
				} else if (inboundDate.compareTo(masterDate) > 0) {
					final String applyText = candidateReconciliationPage.getApplyText();
					candidatePojo.setResult(applyText);
					candidateReconciliationPage.clickApply();
					logger.info("Clicked on apply");
					candidateReconciliationPage.closeAlert();
					System.out.println("popup closed");
				}
			} else {
				final String createNewText = candidateReconciliationPage.getCreateNewText();
				candidatePojo.setResult(createNewText);
				candidateReconciliationPage.clickCreateNew();
				logger.info("Clicked on CreateNew");
				candidateReconciliationPage.closeAlert();
			}

			findCandidatePage.clickCandidateQueue();
			candidateQueuePage.searchTextboxAndSendName(numberParsed);
			candidateQueuePage.isDisplay();

		} catch (final Exception e) {
			candidatePojo.setFailureResult(e.getMessage());
		}
		candidateList.add(candidatePojo);
	}

}
