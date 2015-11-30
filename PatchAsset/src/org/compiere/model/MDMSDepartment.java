package org.compiere.model;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;
import org.compiere.util.Util;

public class MDMSDepartment extends X_DM_DMSDepartment {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDMSDepartment(Properties ctx, int DM_DMSDepartment_ID,
			String trxName) {
		super(ctx, DM_DMSDepartment_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDMSDepartment(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		
		int cnt = DB.getSQLValue(get_TrxName(),
				"SELECT COUNT(*) FROM DM_DMSDepartment  WHERE Name IS NOT NULL AND lower(Name)=?   AND ISActive='Y' AND AD_Client_ID=? AND DM_DMSDepartment_ID!=? "
				+ " ",
				getName().toLowerCase(), getAD_Client_ID(), get_ID());
		if (cnt > 0) {
			Object[] args = new Object[] {"Department","Name ", getName()};
			log.saveError("SaveError", Msg.getMsg(getCtx(), "SAVE_ERROR_NOT_UNIQUE_MSG",args) +" - "+ "Name"+"("+getName()+")");
			return false;
		}
		if(!newRecord){
			if(is_ValueChanged("Name")){
				String WhereClause="lower(Name)='"+getName().toLowerCase()+"'  AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
				int[] jobcatID =  MJobCategory.getAllIDs(MJobCategory.Table_Name, WhereClause, get_TrxName());
				if(jobcatID.length>0){
					MJobCategory jcat=new MJobCategory(getCtx(), jobcatID[0], get_TrxName());
					jcat.setName(getName());
					jcat.setAD_Org_ID(0);
					jcat.save();
				}
			}
		}
		return true;
	}
	protected boolean afterSave (boolean newRecord, boolean success)
	{
		if (!success)
			return success;
		MJobCategory jcat = null;
		String WhereClause="lower(Name)='"+getName().toLowerCase()+"'  AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
		int[] jobcatID =  MJobCategory.getAllIDs(MJobCategory.Table_Name, WhereClause, get_TrxName());
		if(jobcatID.length<=0){
			jcat=new MJobCategory(getCtx(), 0, get_TrxName());
			jcat.setName(getName());
			jcat.setAD_Org_ID(0);
			if(getDescription()!=null){
				jcat.setDescription(getDescription());
			}
			jcat.save();
		}
		return success;
	}
	@Override
	protected boolean beforeDelete ()
	{

		for (MDMSPosition line : getlinescad()) {
			line.deleteEx(true);
		}
		return true;
	}
	private MDMSPosition[] m_cad=null;
	public MDMSPosition[] getlinescad (String whereClause, String orderClause)
	{
		//red1 - using new Query class from Teo / Victor's MDDOrder.java implementation
		StringBuffer whereClauseFinal = new StringBuffer(MDMSPosition.COLUMNNAME_DM_DMSDepartment_ID+"=? ");
		if (!Util.isEmpty(whereClause, true))
			whereClauseFinal.append(whereClause);
		if (orderClause.length() == 0)
			orderClause = MDMSPosition.COLUMNNAME_DM_DMSPosition_ID;
		//
		List<MDMSPosition> list = new Query(getCtx(), MDMSPosition.Table_Name, whereClauseFinal.toString(), get_TrxName())
										.setParameters(get_ID())
										.setOrderBy(orderClause)
										.list();

		return list.toArray(new MDMSPosition[list.size()]);		
	}	//	getLines

	/**
	 * 	Get Lines of Order
	 * 	@param requery requery
	 * 	@param orderBy optional order by column
	 * 	@return lines
	 */
	public MDMSPosition[] getlinescad (boolean requery, String orderBy)
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
			orderClause += "DM_DMSPosition_ID";
		m_cad = getlinescad(null, orderClause);
		return m_cad;
	}	//	getLines

	/**
	 * 	Get Lines of Order.
	 * 	(used by web store)
	 * 	@return lines
	 */
	public MDMSPosition[] getlinescad()
	{
		return getlinescad(false, null);
	}	//	getLines
}
