package org.novelerp.sapexception;

public class ExecuteCommitException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExecuteCommitException() {
		super("Execution of commit failed");
	}
}