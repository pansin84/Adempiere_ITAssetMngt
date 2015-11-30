package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MIssuedToEmployee extends X_DM_IssuedToEmployee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MIssuedToEmployee(Properties ctx, int DM_IssuedToEmployee_ID, String trxName) {
		super(ctx, DM_IssuedToEmployee_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MIssuedToEmployee(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
