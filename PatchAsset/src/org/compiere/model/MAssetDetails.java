package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

public class MAssetDetails extends X_DM_AssetDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MAssetDetails(Properties ctx, int DM_AssetDetails_ID, String trxName) {
		super(ctx, DM_AssetDetails_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MAssetDetails(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		
		int cnt = DB.getSQLValue(get_TrxName(),
				"SELECT COUNT(*) FROM DM_AssetDetails  WHERE DM_AssetModelDetails_ID=? AND LOWER(DeviceSerialNo)=? AND ISActive='Y' AND AD_Client_ID=? AND DM_AssetDetails_ID!=? "
				+ " ",
				getDM_AssetModelDetails_ID(),getDeviceSerialNo().toLowerCase(), getAD_Client_ID(), get_ID());
		if (cnt > 0) {
			Object[] args = new Object[] {"ModelNo : "+getModelNo(),"Serial No ", getDeviceSerialNo()};
			log.saveError("SaveError", Msg.getMsg(getCtx(), "SAVE_ERROR_NOT_UNIQUE_MSG",args) +" - "+ "Serial No"+"("+getDeviceSerialNo()+")");
			return false;
		}
		if(!newRecord){
			
			if(is_ValueChanged("DeviceSerialNo")){
				String WhereClause="lower(DeviceSerialNo)='"+get_ValueOld("DeviceSerialNo").toString().toLowerCase()+"' "
						+ "  AND M_Product_ID="+getDM_AssetModelDetails().getM_Product_ID()+"  AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
				int[] productID =  MProductSerialNo.getAllIDs(MProductSerialNo.Table_Name, WhereClause, get_TrxName());
				if(productID.length>0){
					MProductSerialNo product=new MProductSerialNo(getCtx(), productID[0], get_TrxName());
					product.setDeviceSerialNo(getDeviceSerialNo());
					product.save();
				}
			}
		}
		
		return true;
	}
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		String WhereClause="lower(DeviceSerialNo)='"+getDeviceSerialNo().toLowerCase()+"' "
				+ "  AND M_Product_ID="+getDM_AssetModelDetails().getM_Product_ID()+"  AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
		int[] productID =  MProductSerialNo.getAllIDs(MProductSerialNo.Table_Name, WhereClause, get_TrxName());
		MProductSerialNo product=null;
		if(productID.length>0){
			product=new MProductSerialNo(getCtx(), productID[0], get_TrxName());
		}else{
			product=new MProductSerialNo(getCtx(), 0, get_TrxName());
			product.setDeviceSerialNo(getDeviceSerialNo());
			product.setM_Product_ID(getDM_AssetModelDetails().getM_Product_ID());
			if(getOsSerialNo()!=null)
				product.setA_License_No(getOsSerialNo());
			if(product.save()){
			
			}
		}
		return success;
	}
	protected boolean beforeDelete ()
	{
		
		for (MAssetLicence line : getlinescad()) {
			line.deleteEx(true);
		}
		return true;
	}
	protected boolean afterDelete (boolean success)
	{
		String WhereClause="lower(DeviceSerialNo)='"+getDeviceSerialNo().toLowerCase()+"' "
				+ "  AND M_Product_ID="+getDM_AssetModelDetails().getM_Product_ID()+"  AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
		int[] productID =  MProductSerialNo.getAllIDs(MProductSerialNo.Table_Name, WhereClause, get_TrxName());
		if(productID.length>0){
			MProductSerialNo product=new MProductSerialNo(getCtx(), productID[0], get_TrxName());
			product.deleteEx(true);
		}
		return success;
	}	//	afterDelete
	private MAssetLicence[] m_cad=null;
	public MAssetLicence[] getlinescad (String whereClause, String orderClause)
	{
		//red1 - using new Query class from Teo / Victor's MDDOrder.java implementation
		StringBuffer whereClauseFinal = new StringBuffer(MAssetLicence.COLUMNNAME_DM_AssetDetails_ID+"=? ");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(whereClause);
		if (orderClause.length() == 0)
			orderClause = MAssetLicence.COLUMNNAME_DM_Asset_Licence_ID;
		//
		List<MAssetLicence> list = new Query(getCtx(), MAssetLicence.Table_Name, whereClauseFinal.toString(), get_TrxName())
										.setParameters(get_ID())
										.setOrderBy(orderClause)
										.list();

		return list.toArray(new MAssetLicence[list.size()]);		
	}	//	getLines

	/**
	 * 	Get Lines of Order
	 * 	@param requery requery
	 * 	@param orderBy optional order by column
	 * 	@return lines
	 */
	public MAssetLicence[] getlinescad (boolean requery, String orderBy)
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
			orderClause += "DM_Asset_Licence_ID";
		m_cad = getlinescad(null, orderClause);
		return m_cad;
	}	//	getLines

	/**
	 * 	Get Lines of Order.
	 * 	(used by web store)
	 * 	@return lines
	 */
	public MAssetLicence[] getlinescad()
	{
		return getlinescad(false, null);
	}	//	getLines

}
