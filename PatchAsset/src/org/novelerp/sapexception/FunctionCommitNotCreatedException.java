package org.novelerp.sapexception;
public class FunctionCommitNotCreatedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6842219079037612786L;

	public FunctionCommitNotCreatedException() {
		super("Function COMMIT could not be created");
	}
}
