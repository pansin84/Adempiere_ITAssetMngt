package org.novelerp.sapexception;
public class RepositoryNotCreatedException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepositoryNotCreatedException() {
		super("Repository object could not be created");
	}
}