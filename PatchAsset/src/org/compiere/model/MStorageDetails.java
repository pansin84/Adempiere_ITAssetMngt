package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MStorageDetails extends X_DM_StorageDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MStorageDetails(Properties ctx, int DM_StorageDetails_ID, String trxName) {
		super(ctx, DM_StorageDetails_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MStorageDetails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
