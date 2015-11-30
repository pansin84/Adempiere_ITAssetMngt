package org.asset.callout;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MBPGroup;
import org.compiere.model.MBPartner;
import org.compiere.model.MBPartnerLocation;
import org.compiere.model.MDMSEmployee;
import org.compiere.model.MLocation;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class CalloutIssueReturn extends CalloutEngine {

	public CalloutIssueReturn() {
	}
	public String setBusinessPartnerbyid (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue)
	{
		if (isCalloutActive())
			return "";
		Integer employeeid = (Integer)value;
		if (employeeid == null || employeeid <= 0)
			return "";
		MDMSEmployee emp=new MDMSEmployee(ctx, employeeid, null);
		
		if(emp.getC_BPartner()!=null){
			mTab.setValue("C_BPartner_ID", emp.getC_BPartner_ID());
		}else{
			mTab.setValue("C_BPartner_ID",createBPartner(ctx, employeeid));
		}
		if(emp.getC_BPartner_Location()!=null){
			mTab.setValue("C_BPartner_Location_ID", emp.getC_BPartner_Location_ID());
		}else{
			if(emp.getC_BPartner()!=null){
				mTab.setValue("C_BPartner_Location_ID",createBPartnerLocation(emp.getC_BPartner_ID(), ctx, employeeid));
			}else{
				
			}
		}
		mTab.setValue("EmployeeCode", emp.getEmployeeCode());
		if(emp.getDM_EmployeeLocationType()!=null){
			mTab.setValue("DM_EmployeeLocationType_ID", emp.getDM_EmployeeLocationType_ID());
		}
		if(emp.getDM_EmployeeLocation()!=null){
			mTab.setValue("DM_EmployeeLocation_ID", emp.getDM_EmployeeLocation_ID());
		}
		if(emp.getDM_DMSPosition()!=null){
			mTab.setValue("DM_DMSPosition_ID", emp.getDM_DMSPosition_ID());
		}
		return "";
	}
	public String setBusinessPartnerbycode (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue)
	{
		if (isCalloutActive())
			return "";
		String employeecode = (String)value;
		if (employeecode == null || employeecode == "")
			return "";
		String sql="select DM_DMSEmployee_ID from DM_DMSEmployee where EmployeeCode='"+employeecode+"' "
				+ " AND AD_Client_ID="+Env.getAD_Client_ID(ctx);
		int empid=DB.getSQLValue(null, sql);
		if(empid>0){
			MDMSEmployee emp=new MDMSEmployee(ctx, DB.getSQLValue(null, sql), null);
			if(emp.getC_BPartner()!=null){
				mTab.setValue("C_BPartner_ID", emp.getC_BPartner_ID());
			}else{
				mTab.setValue("C_BPartner_ID",createBPartner(ctx, emp.get_ID()));
			}
			if(emp.getC_BPartner_Location()!=null){
				mTab.setValue("C_BPartner_Location_ID", emp.getC_BPartner_Location_ID());
			}else{
				if(emp.getC_BPartner()!=null){
					mTab.setValue("C_BPartner_Location_ID",createBPartnerLocation(emp.getC_BPartner_ID(), ctx, emp.get_ID()));
				}else{
					
				}
			}
			mTab.setValue("DM_DMSEmployee_ID", emp.get_ID());
			if(emp.getDM_EmployeeLocationType()!=null){
				mTab.setValue("DM_EmployeeLocationType_ID", emp.getDM_EmployeeLocationType_ID());
			}
			if(emp.getDM_EmployeeLocation()!=null){
				mTab.setValue("DM_EmployeeLocation_ID", emp.getDM_EmployeeLocation_ID());
			}
			if(emp.getDM_DMSPosition()!=null){
				mTab.setValue("DM_DMSPosition_ID", emp.getDM_DMSPosition_ID());
			}
		}else{
			mTab.fireDataStatusEEvent("Employee code is Invalid", "Please Check Employee code", true);
		}
		return "";
	}
	
	public int createBPartner(Properties ctx ,int employeeid){
		MBPartner partner=null;
		MBPGroup bpgroup=null;
	
		String Where="Name='Employees' AND Value='Employees' AND AD_Client_ID="+Env.getAD_Client_ID(ctx);
		int[] partnerGrpID =  MBPGroup.getAllIDs(MBPGroup.Table_Name, Where, null);
		if(partnerGrpID.length<=0){
			bpgroup=new MBPGroup(ctx, 0, null);
			bpgroup.setName("Employees");
			bpgroup.setValue("Employees");
			bpgroup.save();
		}else{
			bpgroup=new MBPGroup(ctx, partnerGrpID[0], null);
		}
		String WhereClause="Name='Alkem Laboratories Limited' AND Value='Alkem Laboratories Ltd' AND IsEmployee='Y' AND AD_Client_ID="+Env.getAD_Client_ID(ctx);
		int[] partnerID =  MBPartner.getAllIDs(MBPartner.Table_Name, WhereClause, null);
		if(partnerID.length>0){
			partner=new MBPartner(ctx, partnerID[0], null);
		}else{
			partner=new MBPartner(ctx, 0, null);
			partner.setName("Alkem Laboratories Limited".trim());
			partner.setValue("Alkem Laboratories Ltd".trim());
			partner.setIsEmployee(true);
			partner.setIsSummary(false);
			partner.setC_BP_Group_ID(bpgroup.get_ID());
			partner.setSOCreditStatus("X");
			partner.setAD_Language(Env.getAD_Language(ctx));
			if(partner.save()){
				DB.executeUpdate("Update DM_DMSEmployee set C_BPartner_ID="+partner.get_ID()+""
						+ " Where DM_DMSEmployee_ID="+employeeid+""
								+ " AND AD_Client_ID="+Env.getAD_Client_ID(ctx), null);
			}
			
		}
		return partner.get_ID();
	}
	public boolean createBPartnerbyid(Properties ctx ,int employeeid,GridTab mTab){
		MBPartner partner=null;
		MBPGroup bpgroup=null;
	
		String Where="Name='Employees' AND Value='Employees' AND AD_Client_ID="+Env.getAD_Client_ID(ctx);
		int[] partnerGrpID =  MBPGroup.getAllIDs(MBPGroup.Table_Name, Where, null);
		if(partnerGrpID.length<=0){
			bpgroup=new MBPGroup(ctx, 0, null);
			bpgroup.setName("Employees");
			bpgroup.setValue("Employees");
			bpgroup.save();
		}else{
			bpgroup=new MBPGroup(ctx, partnerGrpID[0], null);
		}
		String WhereClause="Name='Alkem Laboratories Limited' AND Value='Alkem Laboratories Ltd' AND IsEmployee='Y' AND AD_Client_ID="+Env.getAD_Client_ID(ctx);
		int[] partnerID =  MBPartner.getAllIDs(MBPartner.Table_Name, WhereClause, null);
		if(partnerID.length>0){
			partner=new MBPartner(ctx, partnerID[0], null);
		}else{
			partner=new MBPartner(ctx, 0, null);
			partner.setName("Alkem Laboratories Limited".trim());
			partner.setValue("Alkem Laboratories Ltd".trim());
			partner.setIsEmployee(true);
			partner.setIsSummary(false);
			partner.setC_BP_Group_ID(bpgroup.get_ID());
			partner.setSOCreditStatus("X");
			partner.setAD_Language(Env.getAD_Language(ctx));
			if(partner.save()){
				DB.executeUpdate("Update DM_DMSEmployee set C_BPartner_ID="+partner.get_ID()+""
						+ " Where DM_DMSEmployee_ID="+employeeid+""
								+ " AND AD_Client_ID="+Env.getAD_Client_ID(ctx), null);
			}
			mTab.setValue("C_BPartner_ID", partner.get_ID());
			
		}
		createBPartnerLocationbyid( partner,ctx,employeeid,mTab);
		return true;
	}
	public boolean createBPartnerLocationbyid(MBPartner partner,Properties ctx,int employeeid,GridTab mTab){
		MBPartnerLocation partnerloc=null;
		MLocation location=null;

		String Where="Address1='Devashish Building'"
				+ " AND Address2='Alkem House'"
				+ " AND Address3='Senapati Bapat Road'"
				+ " AND Address4='Lower Parel'"
				+ " AND C_City_ID=1000000 "
				+ " AND C_Region_ID=1000000 "
				+ " AND C_Country_ID=208 "
				+ " AND postal='400013' "
				+ " AND C_BPArtner_ID= "+partner.get_ID()
				+ " AND AD_Client_ID="+Env.getAD_Client_ID(ctx);
		
		int[] locationID =  MLocation.getAllIDs(MLocation.Table_Name, Where, null);
		if(locationID.length<=0){
			location=new MLocation(ctx, 0, null);
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
			location=new MLocation(ctx, locationID[0], null);
		}
		String WhereClause="Name='Alkem House(Mumbai)' AND C_BPartner_ID="+partner.get_ID()+" AND AD_Client_ID="+Env.getAD_Client_ID(ctx);
		int[] partnerID =  MBPartnerLocation.getAllIDs(MBPartnerLocation.Table_Name, WhereClause, null);
		if(partnerID.length>0){
			partnerloc=new MBPartnerLocation(ctx,partnerID[0], null);
		}else{
			partnerloc=new MBPartnerLocation(ctx, 0, null);
			partnerloc.setName("Alkem House(Mumbai)");
			partnerloc.setC_Location_ID(location.get_ID());
			partnerloc.setIsShipTo(true);
			partnerloc.setIsBillTo(false);
			partnerloc.setC_BPartner_ID(partner.get_ID());
			if(partnerloc.save()){
				DB.executeUpdate("Update DM_DMSEmployee set C_BPartner_Location_ID="+partnerloc.get_ID()+""
						+ " Where DM_DMSEmployee_ID="+employeeid+""
								+ " AND AD_Client_ID="+Env.getAD_Client_ID(ctx), null);
				mTab.setValue("C_BPartner_Location_ID", partnerloc.get_ID());
			}
			
		}
		return true;
	}
	public int createBPartnerLocation(int partnerid,Properties ctx,int employeeid){
		MBPartnerLocation partnerloc=null;
		MLocation location=null;

		String Where="Address1='Devashish Building'"
				+ " AND Address2='Alkem House'"
				+ " AND Address3='Senapati Bapat Road'"
				+ " AND Address4='Lower Parel'"
				+ " AND C_City_ID=1000000 "
				+ " AND C_Region_ID=1000000 "
				+ " AND C_Country_ID=208 "
				+ " AND postal='400013' "
				+ " AND C_BPArtner_ID= "+partnerid
				+ " AND AD_Client_ID="+Env.getAD_Client_ID(ctx);
		
		int[] locationID =  MLocation.getAllIDs(MLocation.Table_Name, Where, null);
		if(locationID.length<=0){
			location=new MLocation(ctx, 0, null);
			location.setAddress1("Devashish Building");
			location.setAddress2("Alkem House");
			location.setAddress3("Senapati Bapat Road");
			location.setAddress4("Lower Parel");
			location.setC_City_ID(1000000);
			location.setC_Country_ID(208);
			location.setC_Region_ID(1000000);
			location.setPostal("400013");
			location.setC_BPartner_ID(partnerid);
			location.save();
		}else{
			location=new MLocation(ctx, locationID[0], null);
		}
		String WhereClause="Name='Alkem House(Mumbai)' AND C_BPartner_ID="+partnerid+" AND AD_Client_ID="+Env.getAD_Client_ID(ctx);
		int[] partnerID =  MBPartnerLocation.getAllIDs(MBPartnerLocation.Table_Name, WhereClause, null);
		if(partnerID.length>0){
			partnerloc=new MBPartnerLocation(ctx,partnerID[0], null);
		}else{
			partnerloc=new MBPartnerLocation(ctx, 0, null);
			partnerloc.setName("Alkem House(Mumbai)");
			partnerloc.setC_Location_ID(location.get_ID());
			partnerloc.setIsShipTo(true);
			partnerloc.setIsBillTo(false);
			partnerloc.setC_BPartner_ID(partnerid);
			if(partnerloc.save()){
				DB.executeUpdate("Update DM_DMSEmployee set C_BPartner_Location_ID="+partnerloc.get_ID()+""
						+ " Where DM_DMSEmployee_ID="+employeeid+""
								+ " AND AD_Client_ID="+Env.getAD_Client_ID(ctx), null);
			}
			
		}
		return partnerloc.get_ID();
	}
}
