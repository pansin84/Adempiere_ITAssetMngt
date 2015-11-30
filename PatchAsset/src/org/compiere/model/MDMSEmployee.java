package org.compiere.model;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.TreeMap;

import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.Msg;

public class MDMSEmployee extends X_DM_DMSEmployee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static TreeMap<String, Integer> map = new TreeMap<String, Integer>();
	public MDMSEmployee(Properties ctx, int DM_DMSEmployee_ID, String trxName) {
		super(ctx, DM_DMSEmployee_ID, trxName);
		// TODO Auto-generated constructor stub
	}

	public MDMSEmployee(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
		// TODO Auto-generated constructor stub
	}
	@Override
	protected boolean beforeSave (boolean newRecord)
	{
		
//		int cnt = DB.getSQLValue(get_TrxName(),
//				"SELECT COUNT(*) FROM DM_DMSEmployee WHERE EmployeeCode IS NOT NULL AND lower(EmployeeCode)=?   AND ISActive='Y' AND AD_Client_ID=? AND DM_DMSEmployee_ID!=? "
//				+ " ",
//				getEmployeeCode().toLowerCase(), getAD_Client_ID(), get_ID());
//		if (cnt > 0) {
//			Object[] args = new Object[] {"Employee","Employee Code ", getEmployeeCode()};
//			log.saveError("SaveError", Msg.getMsg(getCtx(), "SAVE_ERROR_NOT_UNIQUE_MSG",args) +" - "+ "Employee Code"+"("+getEmployeeCode()+")");
//			return false;
//		}
//		
//		cnt = DB.getSQLValue(get_TrxName(),
//				"SELECT COUNT(*) FROM DM_DMSEmployee WHERE EMailUser IS NOT NULL AND lower(EMailUser)=?   AND ISActive='Y' AND AD_Client_ID=? AND DM_DMSEmployee_ID!=? "
//				+ " ",
//				getEMailUser().toLowerCase(), getAD_Client_ID(), get_ID());
//		if (cnt > 0) {
//			Object[] args = new Object[] {"Employee","Employee Email ID  ", getEMailUser()};
//			log.saveError("SaveError", Msg.getMsg(getCtx(), "SAVE_ERROR_NOT_UNIQUE_MSG",args) +" - "+ "Employee Email ID "+"("+getEMailUser()+")");
//			return false;
//		}
		StringBuffer sb = new StringBuffer();
		if(getFName() !=null){
			sb.append(getFName());
		}
		if(getLName() !=null){
			sb.append(".").append(getLName());
		}
		setUserID(sb.toString());
		return true;
	}
	@Override
	protected boolean afterSave (boolean newRecord, boolean success){
		if(!success)
			return success;	
		createBPartner();
		return success;	
	}
	public String getSummary() {	
		StringBuffer sb = new StringBuffer();
		sb.append("----------EMPLOYEE INFORMATION----------");
		sb.append("\n");
		sb.append("\n");
		if(new MOrg(getCtx(),getAD_Org_ID(),get_TrxName()).getName()!=null){
			sb.append("Organization: ").append(new MOrg(getCtx(),getAD_Org_ID(),get_TrxName()).getName());
			sb.append("\n");
		}
		StringBuffer sb1 = new StringBuffer();
		if(getFName() !=null){
			sb1.append(getFName());
		}
		if(getMName() !=null){
			sb1.append(" ").append(getMName());
		}
		if(getLName() !=null){
			sb1.append(" ").append(getLName());
		}
		sb.append("Name: ").append(sb1.toString());
		sb.append("\n");
		sb.append("Employee Code: ").append(getEmployeeCode());
		sb.append("\n");
		
		if(getGender()!=null){
			if(getGender().equals("M"))
				sb.append("\n").append("Gender: ").append("Male");
				else
					sb.append("\n").append("Gender: ").append("Female");
		}
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
		if(getBirthday()!=null){
			String bdate = format.format(new Date(getBirthday().getTime()));
			sb.append("\n").append("Date Of Birth: ").append(bdate);
		}
		if(getHR_Race()!=null){
			sb.append("\n").append("Race: ").append(getHR_Race().getName());
		}
		if(getFathersName()!=null){
			sb.append("\n").append("Father's Name: ").append(getFathersName());
		}
		if(getMaritalStatus()!=null){
			sb.append("\n").append("Marital Status: ").append(getMaritalStatus());
		}
		if(getBloodGroup()!=null){
			sb.append("\n").append("Blood Group: ").append(getBloodGroup());
		}
		if(getDM_EmpStatus()!=null){
			sb.append("\n").append("Employee Status: ").append(getDM_EmpStatus().getName());
		}
		if(getDM_DMSDepartment()!=null){
			sb.append("\n").append("Employee Department: ").append(getDM_DMSDepartment().getName());
		}
		if(getC_Job()!=null){
			sb.append("\n").append("Position: ").append(getC_Job().getName());
		}
		return sb.toString();
	}
	public static MDMSEmployee get (Properties ctx, String EmployeeCode)
	{
		if (EmployeeCode == null || EmployeeCode.length() == 0)
			return null;
		final String whereClause = "EmployeeCode=? AND AD_Client_ID=?";
		MDMSEmployee retValue = new Query(ctx, I_DM_DMSEmployee.Table_Name, whereClause, null)
		.setParameters(EmployeeCode,Env.getAD_Client_ID(ctx))
		.firstOnly();
		return retValue;
	}	//	get

	public boolean createBPartner(){
		MBPartner partner=null;
		MBPGroup bpgroup=null;
		
		String Where="Name='Employees' AND Value='Employees' AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
		int[] partnerGrpID =  MBPGroup.getAllIDs(MBPGroup.Table_Name, Where, get_TrxName());
		if(partnerGrpID.length<=0){
			bpgroup=new MBPGroup(getCtx(), 0, get_TrxName());
			bpgroup.setName("Employees");
			bpgroup.setValue("Employees");
			bpgroup.save();
		}else{
			bpgroup=new MBPGroup(getCtx(), partnerGrpID[0], get_TrxName());
		}
		String WhereClause="Name='Alkem Laboratories Limited' AND Value='Alkem Laboratories Ltd' AND IsEmployee='Y' AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
		int[] partnerID =  MBPartner.getAllIDs(MBPartner.Table_Name, WhereClause, get_TrxName());
		if(partnerID.length>0){
			partner=new MBPartner(getCtx(), partnerID[0], get_TrxName());
		}else{
			partner=new MBPartner(getCtx(), 0, get_TrxName());
			partner.setName("Alkem Laboratories Limited".trim());
			partner.setValue("Alkem Laboratories Ltd".trim());
			partner.setIsEmployee(true);
			partner.setIsSummary(false);
			partner.setC_BP_Group_ID(bpgroup.get_ID());
			partner.setSOCreditStatus("X");
			partner.setAD_Language(Env.getAD_Language(getCtx()));
			if(partner.save()){
				DB.executeUpdate("Update DM_DMSEmployee set C_BPartner_ID="+partner.get_ID()+""
						+ " Where DM_DMSEmployee_ID="+get_ID()+""
								+ " AND AD_Client_ID="+Env.getAD_Client_ID(getCtx()), get_TrxName());
			}
			
		}
		createBPartnerLocation( partner);
		return true;
	}
	public boolean createBPartnerLocation(MBPartner partner){
		MBPartnerLocation partnerloc=null;
		MLocation location=null;
		StringBuffer sb = new StringBuffer();
		if(getFName() !=null){
			sb.append(getFName());
		}
		if(getMName() !=null){
			sb.append(" ").append(getMName());
		}
		if(getLName() !=null){
			sb.append(" ").append(getLName());
		}
		String Where="Address1='Devashish Building'"
				+ " AND Address2='Alkem House'"
				+ " AND Address3='Senapati Bapat Road'"
				+ " AND Address4='Lower Parel'"
				+ " AND C_City_ID=1000000 "
				+ " AND C_Region_ID=1000000 "
				+ " AND C_Country_ID=208 "
				+ " AND postal='400013' "
				+ " AND C_BPArtner_ID= "+partner.get_ID()
				+ " AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
		
		int[] locationID =  MLocation.getAllIDs(MLocation.Table_Name, Where, get_TrxName());
		if(locationID.length<=0){
			location=new MLocation(getCtx(), 0, get_TrxName());
			location.setAddress1("Devashish Building");
			location.setAddress2("Alkem House");
			location.setAddress3("Senapati Bapat Road");
			location.setAddress4("Lower Parel");
			location.setC_City_ID(1000000);
			location.setC_Country_ID(208);
			location.setC_Region_ID(1000000);
			location.setPostal("400013");
			location.setC_BPartner_ID(partner.get_ID());
			location.save();
		}else{
			location=new MLocation(getCtx(), locationID[0], get_TrxName());
		}
		String WhereClause="Name='Alkem House(Mumbai)' AND C_BPartner_ID="+partner.get_ID()+" AND AD_Client_ID="+Env.getAD_Client_ID(getCtx());
		int[] partnerID =  MBPartnerLocation.getAllIDs(MBPartnerLocation.Table_Name, WhereClause, get_TrxName());
		if(partnerID.length>0){
			partnerloc=new MBPartnerLocation(getCtx(),partnerID[0], get_TrxName());
		}else{
			partnerloc=new MBPartnerLocation(getCtx(), 0, get_TrxName());
			partnerloc.setName("Alkem House(Mumbai)");
			partnerloc.setC_Location_ID(location.get_ID());
			partnerloc.setIsShipTo(true);
			partnerloc.setIsBillTo(false);
			partnerloc.setC_BPartner_ID(partner.get_ID());
			if(partnerloc.save()){
				DB.executeUpdate("Update DM_DMSEmployee set C_BPartner_Location_ID="+partnerloc.get_ID()+""
						+ " Where DM_DMSEmployee_ID="+get_ID()+""
								+ " AND AD_Client_ID="+Env.getAD_Client_ID(getCtx()), get_TrxName());
			}
			
		}
		return true;
	}
}
