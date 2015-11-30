package org.novelerp.sapmodel;

import com.sap.mw.jco.*; //The JCO
import java.util.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.*;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.model.MDMSDepartment;
import org.compiere.model.MDMSEmployee;
import org.compiere.model.MDMSPosition;
import org.compiere.model.MEmployeeDivision;
import org.compiere.model.MEmployeeLocation;
import org.compiere.model.MEmployeeLocationType;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.novelerp.sapconection.SapConnection;

public class JcoBapiEmployeeGetStatus {
	private SapConnection sapConnection1;
	private IRepository mRepository;
	private JCO.Function jcoFunction;

	// Return parameters
	private String oBapiReturn;
	private String oBapiReturnType;

	Timestamp Date;
	String sb;
	int deptid;
	int DivId;
	int dasgid;
	int Locid;
	int Loctypeid;
	int inoutID;
	String trx;
	Properties ctx;
	MDMSEmployee emp;
	int clientid;

	public JcoBapiEmployeeGetStatus() {
		sapConnection1 = new SapConnection();
	}

	public void GetStatus(String Emppara, String trxs, Properties ctxs,
			int cid)

	throws EmpparaEmptyException, RepositoryNotCreatedException,
			FunctionNotCreatedException, InvalidInputParameterException,
			ExecuteException, GetStatusException, BapiReturnException
	{ // Date format used for date fields
		clientid = cid;
		trx = trxs;
		ctx = ctxs;

		DateFormat dateFormatter;
		Locale dkLocale = new Locale("dk", "DK");
		dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, dkLocale);

		// ---------------------------------------------------------
		// Create metadata with JCO Repository
		// ---------------------------------------------------------
		try 
		{
			mRepository = new JCO.Repository("hFrank", SapConnection.mConnection);
		} 
		catch (Exception mException) 
		{ 
			throw new RepositoryNotCreatedException();
		}

		// ---------------------------------------------------------
		// Get a function template for ZBAPI_ALL_EMP_DETL
		// from the repository and create a function
		// ---------------------------------------------------------
		try { // Get a function template from the repository
			//rfc name
			IFunctionTemplate ftemplate = mRepository.getFunctionTemplate("ZBAPI_ALL_EMP_DETL");
			// Create a function from the template
			jcoFunction = new JCO.Function(ftemplate);
			
			if (jcoFunction == null)
				throw new FunctionNotCreatedException();
		} 
		catch (Exception mException) 
		{ 
			throw new FunctionNotCreatedException();
		}

		// ---------------------------------------------------------
		// Set the EMPCode import parameter
		// ---------------------------------------------------------
		try 
		{
			//set Para to fuction
			JCO.Field empcodeField = jcoFunction.getImportParameterList().getField("EMPCODE");
			empcodeField.setValue(Emppara);
		} 
		catch (Exception mException) 
		{
			throw new InvalidInputParameterException();
		}

		// ---------------------------------------------------------
		// Execute function
		// ---------------------------------------------------------

		try 
		{			
			SapConnection.mConnection.execute(jcoFunction);			
		} 
		catch (Exception mException) 
		{			
			throw new ExecuteException();
			// throw new BapiReturnException(); AQRPJ5696G			
		}
		
		// ---------------------------------------------------------
		// Get sales order status. Item info is saved in the
		// array itemData.
		// ---------------------------------------------------------

		try
		{
			//retrieve data rows from rfc table
			JCO.Table jcoStatusInfo = jcoFunction.getTableParameterList().getTable("IT_EMP_FINAL");
			int NumRows = jcoStatusInfo.getNumRows();
			
			//iterate all row and set data to emplyee and related master 
			System.out.println("no of rows=== " + NumRows);

			for (int i = 0; i < NumRows; i++) 
			{
				jcoStatusInfo.setRow(i);

				if (jcoStatusInfo.getField("PERNR").getValue().toString().length() > 0 && jcoStatusInfo.getField("VORNA").getValue().toString().length() > 0) 
				{
					emp = new Query(ctx, MDMSEmployee.Table_Name, " EmployeeCode='" + jcoStatusInfo.getField("PERNR").getValue().toString() + "' and AD_Client_ID="+clientid, trx).setOnlyActiveRecords(true).first();

					if (emp == null) 
					{
						emp = new MDMSEmployee(ctx, 0, trx);
					}
					
					try 
					{
						if (jcoStatusInfo.getField("DOJ").getDate().toString().length() > 0) 
						{
							Timestamp Join = getDatepara(dateFormatter.format(jcoStatusInfo.getField("DOJ").getDate()));
							emp.setStartDate(Join);
						}

						if (jcoStatusInfo.getField("VORNA").getValue().toString().length() > 0) 
						{
							emp.setFName(jcoStatusInfo.getField("VORNA").getValue().toString());
						}

						if ((jcoStatusInfo.getField("LOC_TYP").getValue().toString().length() > 0)) 
						{
							Loctypeid = getLoctype(jcoStatusInfo.getField("LOC_TYP").getValue().toString());
							emp.setDM_EmployeeLocationType_ID(Loctypeid);
						}

						if (jcoStatusInfo.getField("LOC").getValue().toString().length() > 0) 
						{
							Locid = getLoc(jcoStatusInfo.getField("LOC").getValue().toString());
							emp.setDM_EmployeeLocation_ID(Locid);							
						}

						if (jcoStatusInfo.getField("NACHN").getValue().toString().length() > 0) 
						{
							emp.setLName(jcoStatusInfo.getField("NACHN").getValue().toString());
						}

						if (jcoStatusInfo.getField("EMAIL").getValue().toString() != null | jcoStatusInfo.getField("EMAIL").getValue().toString().equalsIgnoreCase("")) 
						{
							emp.setEMailUser(jcoStatusInfo.getField("EMAIL").getValue().toString());
						}

						if (jcoStatusInfo.getField("MOBNO").getValue().toString() != null | jcoStatusInfo.getField("MOBNO").getValue().toString().equalsIgnoreCase("")) 
						{
							emp.setMobilePhone(jcoStatusInfo.getField("MOBNO").getValue().toString());
						}

						if (jcoStatusInfo.getField("PERNR").getValue().toString() != null | jcoStatusInfo.getField("PERNR").getValue().toString().equalsIgnoreCase("")) 
						{
							emp.setEmployeeCode(jcoStatusInfo.getField("PERNR").getValue().toString());							
						}

						if (jcoStatusInfo.getField("GENDER").getValue().toString() != null | jcoStatusInfo.getField("GENDER").getValue().toString().equalsIgnoreCase("")) 
						{
							if (jcoStatusInfo.getField("GENDER").getValue().toString().equalsIgnoreCase("M")) 
							{
								emp.setGender("MA");
							}
							else 
							{
								emp.setGender("FE");
							}
						}

						if (jcoStatusInfo.getField("DIV").getValue().toString().length() > 0) 
						{				
							DivId = getDivison(jcoStatusInfo.getField("DIV").getValue().toString());
							if (DivId > 0)
								emp.setDM_EmployeeDivision_ID(DivId);
						}

						if (jcoStatusInfo.getField("DEPT").getValue().toString().length() > 0) 
						{
							deptid = getDepartment(jcoStatusInfo.getField("DEPT").getValue().toString());
							if (deptid > 0)
								emp.setDM_DMSDepartment_ID(deptid);
						}							
						else							
						{
							deptid = getDepartment4Resignee("Not Assigned");
							emp.setDM_DMSDepartment_ID(deptid);
						}

						if (jcoStatusInfo.getField("PLSTX").getValue().toString().length() > 0) 
						{			
							dasgid = getDesignation(jcoStatusInfo.getField("PLSTX").getValue().toString(), deptid);
							if (dasgid > 0)
								emp.setC_Job_ID(dasgid);
						}
						else							
						{
							dasgid = getDesignation4Resignee("Not Assigned",deptid);
							emp.setC_Job_ID(dasgid);
						}
						emp.setDM_DMSEmployeeType_ID(1000002);
						emp.saveEx();
				} catch (Exception mException) {
					mException.printStackTrace();
					continue;				
				}
			}
		}			
	}
		catch (Exception mException) {
			
			throw new GetStatusException();

		}

		// ---------------------------------------------------------
		// Get BAPIRETURN
		// ---------------------------------------------------------

		try {

			JCO.Structure jcoBapiReturn = jcoFunction.getExportParameterList().getStructure("RETURN");

			oBapiReturn = jcoBapiReturn.getField("TYPE").getValue() + " " + jcoBapiReturn.getField("CODE").getValue() + " " + jcoBapiReturn.getField("MESSAGE").getValue();

			oBapiReturnType = jcoBapiReturn.getField("TYPE").getValue().toString();
			
			if ( oBapiReturn.length()>0) {

				//System.out.println(oBapiReturn + "bapi");
			}
		}

		catch (Exception mException)

		{
			throw new BapiReturnException();

		}


	}

	// *******************************************************************
	// Classes that returns status information
	// *******************************************************************

	public String getBapiReturn() {
		return oBapiReturn;
	}

	public Timestamp getDatepara(String date) {
	 
		java.sql.Timestamp timestamp = null;
		String LARGE_TWITTER_DATE_FORMAT = "MMM dd, yyyy";

		try {

			java.util.Date dd = new SimpleDateFormat(LARGE_TWITTER_DATE_FORMAT,	Locale.ENGLISH).parse(date);
			timestamp = new java.sql.Timestamp(dd.getTime());
			//System.out.println(timestamp);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return timestamp;
	}

	public int getDesignation(String pDesg, int deptid) 
	{
		pDesg=pDesg.replace("'", "");		
		StringBuffer stb1 = new StringBuffer();
		stb1 = stb1.append("Select DM_DMSPosition_ID from DM_DMSPosition where ").append(" Name='").append(pDesg).append("'").append(" And IsActive='Y' And AD_Client_ID=").append(clientid).append(" And DM_DMSDepartment_ID=").append(deptid).append(" ");
		dasgid = DB.getSQLValue(trx, stb1.toString());
		if (dasgid < 0) 
		{
			MDMSPosition job = new MDMSPosition(ctx, 0, trx);
			job.setName(pDesg);
			job.setDM_DMSDepartment_ID(deptid);
			job.setAD_Org_ID(Env.getAD_Org_ID(ctx));
//			job.setIsEmployee(true);
			if (job.save()) 
			{
				dasgid = job.get_ID();
			} else 
			{
				throw new AdempiereException(" Designation Not Saved ");
			}
		}
		return dasgid;
	}
	
	public int getDesignation4Resignee(String pDesg, int deptid) 
	{
		
		pDesg=pDesg.replace("'", "");		
		StringBuffer stb1 = new StringBuffer();
		stb1 = stb1.append("Select DM_DMSPosition_ID from DM_DMSPosition where ").append(" Name='").append(pDesg).append("'").append(" And IsActive='Y' And AD_Client_ID=").append(clientid).append(" And DM_DMSDepartment_ID=").append(deptid).append(" ");
		dasgid = DB.getSQLValue(trx, stb1.toString());
		if (dasgid < 0) 
		{
			MDMSPosition job = new MDMSPosition(ctx, 0, trx);
			job.setName(pDesg);
			job.setDM_DMSDepartment_ID(deptid);
			job.setAD_Org_ID(Env.getAD_Org_ID(ctx));
//			job.setIsEmployee(true);
			if (job.save()) 
			{
				dasgid = job.get_ID();
			} else 
			{
				throw new AdempiereException(" Designation Not Saved ");
			}
		}
		return dasgid;
	}

	public int getDepartment(String dptname) 
	{		
		dptname=dptname.replace("'", "");
		StringBuffer stb2 = new StringBuffer();
		stb2 = stb2.append("Select DM_DMSDepartment_ID from DM_DMSDepartment where ").append(" Name='").append(dptname).append("'").append(" And IsActive='Y' And AD_Client_ID=").append(clientid);
		deptid = DB.getSQLValue(trx, stb2.toString());
		
		if (deptid < 0)
		{			
			MDMSDepartment bpMfg = new MDMSDepartment(ctx, 0, trx);
			bpMfg.setName(dptname);
			bpMfg.setAD_Org_ID(Env.getAD_Org_ID(ctx));
			 if (bpMfg.save()) 
			 {
				 deptid = bpMfg.get_ID();
			 }			
			 else 
			 {			
				 throw new AdempiereException(" Department Not Saved ");
			 }
		}
		return deptid;
	}
	
	public int getDepartment4Resignee(String dptname) 
	{		
		dptname=dptname.replace("'", "");		
		StringBuffer stb2 = new StringBuffer();
		stb2 = stb2.append("Select DM_DMSDepartment_ID from DM_DMSDepartment where ").append(" Name='").append(dptname).append("'").append(" And IsActive='Y' And AD_Client_ID=").append(clientid);
		deptid = DB.getSQLValue(trx, stb2.toString());
		if (deptid < 0)
		{
			MDMSDepartment bpMfg = new MDMSDepartment(ctx, 0, trx);
			bpMfg.setName(dptname);
			bpMfg.setAD_Org_ID(Env.getAD_Org_ID(ctx));
			 if (bpMfg.save()) 
			 {
				 deptid = bpMfg.get_ID();		
			 }			
			 else
			 {			
				 throw new AdempiereException(" Department Not Saved ");
			 }
		}
		return deptid;
	}

	public int getDivison(String mname) 
	{	
		mname=mname.replace("'", "");		
		StringBuffer stb3 = new StringBuffer();
		stb3 = stb3.append("Select DM_EmployeeDivision_ID from DM_EmployeeDivision where ").append(" Name='").append(mname).append("'").append(" And IsActive='Y' And AD_Client_ID=").append(clientid); 
		DivId = DB.getSQLValue(trx, stb3.toString());
		if (DivId < 0) 
		{
			MEmployeeDivision Ed = new MEmployeeDivision(ctx, 0, trx);
			Ed.setAD_Org_ID(Env.getAD_Org_ID(ctx));
			Ed.setName(mname);
			if (Ed.save()) 
			{
				DivId = Ed.get_ID();
			} 
			else 
			{
				throw new AdempiereException(" Employee Divison Not Saved ");
			}
		}
		return DivId;
	}

	public int getLoc(String mname) {
		
		mname=mname.replace("'", "");
		StringBuffer stb4 = new StringBuffer();

		stb4 = stb4
				.append("Select DM_EmployeeLocation_ID from DM_EmployeeLocation where ")
				.append(" Name='").append(mname).append("'")
				.append(" And IsActive='Y' And AD_Client_ID=").append(clientid); // .append(" And value= '").append(mcode).append("'");

		Locid = DB.getSQLValue(trx, stb4.toString());

		if (Locid < 0) {
			MEmployeeLocation Ed = new MEmployeeLocation(ctx, 0, trx);
			Ed.setName(mname);
			Ed.setAD_Org_ID(Env.getAD_Org_ID(ctx));
			// if (mcode != "")
			// type.setValue(mcode);
			if (Ed.save()) {
				Locid = Ed.get_ID();
			} else {
				throw new AdempiereException(" Employee Location Not Saved ");
			}

		}
		return Locid;
	}

	public int getLoctype(String mname) {
		mname=mname.replace("'", "");

		//System.out.println("getLoctype===" + mname);
		StringBuffer stb5 = new StringBuffer();

		stb5 = stb5
				.append("Select DM_EmployeeLocationType_ID from DM_EmployeeLocationType where ")
				.append(" Name='").append(mname).append("'")
				.append(" And IsActive='Y' And AD_Client_ID=").append(clientid); // .append(" And value= '").append(mcode).append("'");

		//System.out.println("stb5.toString() ==" + stb5.toString());

		Loctypeid = DB.getSQLValue(trx, stb5.toString());

		//System.out.println("Loctypeid ==" + Loctypeid);
		if (Loctypeid <= 0) {
			MEmployeeLocationType Ed = new MEmployeeLocationType(ctx, 0, trx);
			Ed.setAD_Org_ID(Env.getAD_Org_ID(ctx));
			Ed.setName(mname);
			// if (mcode != "")
			// type.setValue(mcode);
			if (Ed.save()) {
				Loctypeid = Ed.get_ID();
			} else {
				throw new AdempiereException("Employee LocationType Not Saved ");
			}

		}
		return Loctypeid;
	}
	
	// *******************************************************************
	// Exception classes
	// *******************************************************************
	class EmpparaEmptyException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public EmpparaEmptyException() {
			super("You must enter a Employee parameter");
		}
	}

	class RepositoryNotCreatedException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public RepositoryNotCreatedException() {
			super("Repository object could not be created");
		}
	}

	class FunctionNotCreatedException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public FunctionNotCreatedException() {
			super("Function could not be created");
		}
	}

	class InvalidInputParameterException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public InvalidInputParameterException() {
			super("Invalid parameter");
		}
	}

	class ExecuteException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public ExecuteException() {
			super("Execution failed");
		}
	}

	class GetStatusException extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public GetStatusException() {
			super("Error when reading status information");
		}
	}

	class BapiReturnException extends Exception {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public BapiReturnException() {
			super("Error when reading BAPI return information");
		}
	}

}
