package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Msg;
import org.compiere.util.Util;

public class MAssetModelNo extends X_DM_AssetModelNo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MAssetModelNo(Properties ctx, int DM_AssetModelNo_ID, String trxName) {
		super(ctx, DM_AssetModelNo_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MAssetModelNo(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		
		int cnt = DB.getSQLValue(get_TrxName(),
				"SELECT COUNT(*) FROM DM_AssetModelNo  WHERE M_Product_Category_ID=? AND AssetManufacturer_ID=? AND ISActive='Y' AND AD_Client_ID=? AND DM_AssetModelNo_ID!=? "
				+ " ",
				getM_Product_Category_ID(),getAssetManufacturer_ID(), getAD_Client_ID(), get_ID());
		if (cnt > 0) {
			Object[] args = new Object[] {"Manufacturer : "+getAssetManufacturer().getName(),"Item Type ", getM_Product_Category().getName()};
			log.saveError("SaveError", Msg.getMsg(getCtx(), "SAVE_ERROR_NOT_UNIQUE_MSG",args) +" - "+ "Item Type"+"("+getM_Product_Category().getName()+")");
			return false;
		}
		
		return true;
	}
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		
		return success;
	}
	
	protected boolean beforeDelete ()
	{

		for (MAssetModelDetails line : getlinescad()) {
			line.deleteEx(true);
		}
		return true;
	}
	private MAssetModelDetails[] m_cad=null;
	public MAssetModelDetails[] getlinescad (String whereClause, String orderClause)
	{
		//red1 - using new Query class from Teo / Victor's MDDOrder.java implementation
		StringBuffer whereClauseFinal = new StringBuffer(MAssetModelDetails.COLUMNNAME_DM_AssetModelNo_ID+"=? ");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(whereClause);
		if (orderClause.length() == 0)
			orderClause = MAssetModelDetails.COLUMNNAME_DM_AssetModelDetails_ID;
		//
		List<MAssetModelDetails> list = new Query(getCtx(), MAssetModelDetails.Table_Name, whereClauseFinal.toString(), get_TrxName())
										.setParameters(get_ID())
										.setOrderBy(orderClause)
										.list();

		return list.toArray(new MAssetModelDetails[list.size()]);		
	}	//	getLines

	/**
	 * 	Get Lines of Order
	 * 	@param requery requery
	 * 	@param orderBy optional order by column
	 * 	@return lines
	 */
	public MAssetModelDetails[] getlinescad (boolean requery, String orderBy)
	{
		if (m_cad != null && !requery) {
			set_TrxName(m_cad, get_TrxName());
			return m_cad;
		}
		//
		String orderClause = "";
		if (orderBy != null && orderBy.length() > 0)
			orderClause += orderBy;
		else
			orderClause += "DM_AssetModelDetails_ID";
		m_cad = getlinescad(null, orderClause);
		return m_cad;
	}	//	getLines

	/**
	 * 	Get Lines of Order.
	 * 	(used by web store)
	 * 	@return lines
	 */
	public MAssetModelDetails[] getlinescad()
	{
		return getlinescad(false, null);
	}	//	getLines
}
