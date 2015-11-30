package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MAssetTransaction extends X_DM_AssetTransaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MAssetTransaction(Properties ctx, int DM_AssetTransaction_ID, String trxName) {
		super(ctx, DM_AssetTransaction_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MAssetTransaction(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
