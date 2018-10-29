package com.citrix.elearning.candidatemerge.utils;

/**
 * This util class contains CandidatePojo
 *
 * @author amulya.mummadi
 *
 */
public class CandidatePojo {
	String candidateID;
	String firstName;
	String lastName;
	String result;
	String failureResult;

	/**
	 * Method to get CandidateId
	 *
	 * @return CandidateId
	 */

	public String getCandidateId() {
		return candidateID;
	}

	/**
	 * Method to get FailureResult
	 *
	 * @return FailureResult
	 */
	public String getFailureResult() {
		return failureResult;
	}

	/**
	 * Method to get FirstName
	 *
	 * @return FirstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method to get LastName
	 *
	 * @return LastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method to get Result
	 *
	 * @return Result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Method to setCandidateId
	 *
	 * @param candidateID
	 */
	public void setCandidateId(String candidateID) {
		this.candidateID = candidateID;
	}

	/**
	 * Method to set FailureResult
	 *
	 * @param failureResult
	 */
	public void setFailureResult(String failureResult) {
		this.failureResult = failureResult;
	}

	/**
	 * ' Method to set FirstName
	 *
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Method to set LastName
	 *
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Method to set Result
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
