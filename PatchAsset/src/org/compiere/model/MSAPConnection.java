package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MSAPConnection extends X_DM_SAPConnection {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MSAPConnection(Properties ctx, int DM_SAPConnection_ID,
			String trxName) {
		super(ctx, DM_SAPConnection_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MSAPConnection(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
