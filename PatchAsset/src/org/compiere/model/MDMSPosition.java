package org.compiere.model;

import java.sql.ResultSet;
import java.util.Properties;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class MDMSPosition extends X_DM_DMSPosition {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MDMSPosition(Properties ctx, int DM_DMSPosition_ID, String trxName) {
		super(ctx, DM_DMSPosition_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDMSPosition(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		
		int cnt = DB.getSQLValue(get_TrxName(),
				"SELECT COUNT(*) FROM DM_DMSPosition  WHERE Name IS NOT NULL AND lower(Name)=?   AND ISActive='Y' AND AD_Client_ID=? AND DM_DMSDepartment_ID=? AND DM_DMSPosition_ID !=?"
				+ " ",
				getName().toLowerCase(), getAD_Client_ID(), getDM_DMSDepartment_ID(),get_ID());
		if (cnt > 0) {
			Object[] args = new Object[] {"Position","Name ", getName()};
			log.saveError("SaveError", Msg.getMsg(getCtx(), "SAVE_ERROR_NOT_UNIQUE_MSG",args) +" - "+ "Name"+"("+getName()+")");
			return false;
		}
		if(!newRecord){
			if(is_ValueChanged("Name")){
				String WhereClause="lower(Name)='"+getName().toLowerCase()+"' "
						+ "  AND C_JobCategory_ID="+getJobCategory()+"  AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
				int[] jobcatID =  X_C_Job.getAllIDs(X_C_Job.Table_Name, WhereClause, get_TrxName());
				if(jobcatID.length>0){
					X_C_Job jcat=new X_C_Job(getCtx(), jobcatID[0], get_TrxName());
					jcat.setName(getName());
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
		if(getJobCategory()>0){
			X_C_Job job = null;
			String WhereClause="lower(Name)='"+getName().toLowerCase()+"' "
					+ "  AND C_JobCategory_ID="+getJobCategory()+"  AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
			int[] jobcatID =  X_C_Job.getAllIDs(X_C_Job.Table_Name, WhereClause, get_TrxName());
			if(jobcatID.length>0){
				job=new X_C_Job(getCtx(), jobcatID[0], get_TrxName());
			}else{
				job=new X_C_Job(getCtx(), 0, get_TrxName());
				job.setName(getName());
				job.setC_JobCategory_ID(getJobCategory());
				job.setIsEmployee(true);
				job.save();
			}
		}
		return success;
	}
	public int getJobCategory(){
		int jobcat=0;
		String WhereClause="lower(Name)='"+getDM_DMSDepartment().getName().toLowerCase()+"'  AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
		int[] jobcatID =  MJobCategory.getAllIDs(MJobCategory.Table_Name, WhereClause, get_TrxName());
		if(jobcatID.length>0){
			MJobCategory jcat=new MJobCategory(getCtx(), jobcatID[0], get_TrxName());
			jobcat=jcat.get_ID();
		}
		return jobcat;
	}
}
