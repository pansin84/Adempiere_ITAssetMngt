package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

public class MAssetLicence extends X_DM_Asset_Licence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MAssetLicence(Properties ctx, int DM_Asset_Licence_ID, String trxName) {
		super(ctx, DM_Asset_Licence_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MAssetLicence(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

}
