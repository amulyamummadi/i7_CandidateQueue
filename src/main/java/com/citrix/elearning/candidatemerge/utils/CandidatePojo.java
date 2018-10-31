package com.citrix.elearning.candidatemerge.utils;

/**
 * This util class contains CandidatePojo.
 *
 * @author amulya.mummadi
 *
 */
public class CandidatePojo {
	String candidateID;
	String firstName;
	String lastName;
	String result;
	String failureReason;

	/**
	 * Method to get Candidate id.
	 *
	 * @return CandidateId
	 */

	public String getCandidateId() {
		return candidateID;
	}

	/**
	 * Method to get failure reason.
	 *
	 * @return failure reason
	 */
	public String getFailureReason() {
		return failureReason;
	}

	/**
	 * Method to get firstname.
	 *
	 * @return firstname
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method to get lastname.
	 *
	 * @return lastname
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method to get result.
	 *
	 * @return result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Method to set candidate id.
	 *
	 * @param candidate
	 *            id
	 */
	public void setCandidateId(String candidateID) {
		this.candidateID = candidateID;
	}

	/**
	 * Method to set failure Reason.
	 *
	 * @param failure
	 *            Reason
	 */
	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	/**
	 * ' Method to set firstname.
	 *
	 * @param firstname
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Method to set lastname.
	 *
	 * @param lastname
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Method to set result.
	 *
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Overriding toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

}
