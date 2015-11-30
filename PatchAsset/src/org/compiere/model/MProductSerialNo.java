package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MProductSerialNo extends X_DM_ProductSerialNo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MProductSerialNo(Properties ctx, int DM_ProductSerialNo_ID, String trxName) {
		super(ctx, DM_ProductSerialNo_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MProductSerialNo(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
