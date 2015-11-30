package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Msg;

public class MDMSEmployeeType extends X_DM_DMSEmployeeType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDMSEmployeeType(Properties ctx, int DM_DMSEmployeeType_ID,
			String trxName) {
		super(ctx, DM_DMSEmployeeType_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDMSEmployeeType(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		
		int cnt = DB.getSQLValue(get_TrxName(),
				"SELECT COUNT(*) FROM DM_DMSEmployeeType  WHERE Name IS NOT NULL AND lower(Name)=?   AND ISActive='Y' AND AD_Client_ID=? AND DM_DMSEmployeeType_ID!=? "
				+ " ",
				getName().toLowerCase(), getAD_Client_ID(), get_ID());
		if (cnt > 0) {
			Object[] args = new Object[] {"Employee type","Name ", getName()};
			log.saveError("SaveError", Msg.getMsg(getCtx(), "SAVE_ERROR_NOT_UNIQUE_MSG",args) +" - "+ "Name"+"("+getName()+")");
			return false;
		}
		return true;
	}
}
