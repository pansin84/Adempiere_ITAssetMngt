package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MEmployeeContact extends X_DM_EmployeeContact {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MEmployeeContact(Properties ctx, int DM_EmployeeContact_ID,
			String trxName) {
		super(ctx, DM_EmployeeContact_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MEmployeeContact(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
