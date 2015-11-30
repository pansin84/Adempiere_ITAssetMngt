package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MJobCategory extends X_C_JobCategory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MJobCategory(Properties ctx, int C_JobCategory_ID, String trxName) {
		super(ctx, C_JobCategory_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MJobCategory(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	protected boolean beforeSave (boolean newRecord)
	{
		return true;
	}
}
