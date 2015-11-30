/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2007 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
/** Generated Model - DO NOT CHANGE */
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for DM_DMSEmployee
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_DM_DMSEmployee extends PO implements I_DM_DMSEmployee, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150831L;

    /** Standard Constructor */
    public X_DM_DMSEmployee (Properties ctx, int DM_DMSEmployee_ID, String trxName)
    {
      super (ctx, DM_DMSEmployee_ID, trxName);
      /** if (DM_DMSEmployee_ID == 0)
        {
			setDM_DMSEmployee_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DM_DMSEmployee (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 3 - Client - Org 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuffer sb = new StringBuffer ("X_DM_DMSEmployee[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Birthday.
		@param Birthday 
		Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday)
	{
		set_Value (COLUMNNAME_Birthday, Birthday);
	}

	/** Get Birthday.
		@return Birthday or Anniversary day
	  */
	public Timestamp getBirthday () 
	{
		return (Timestamp)get_Value(COLUMNNAME_Birthday);
	}

	/** BloodGroup AD_Reference_ID=53616 */
	public static final int BLOODGROUP_AD_Reference_ID=53616;
	/** A - = A - */
	public static final String BLOODGROUP_A_ = "A -";
	/** A + = A + */
	public static final String BLOODGROUP_APlus = "A +";
	/** AB - = AB - */
	public static final String BLOODGROUP_AB_ = "AB -";
	/** AB + = AB + */
	public static final String BLOODGROUP_ABPlus = "AB +";
	/** B - = B - */
	public static final String BLOODGROUP_B_ = "B -";
	/** B + = B + */
	public static final String BLOODGROUP_BPlus = "B +";
	/** O - = O - */
	public static final String BLOODGROUP_O_ = "O -";
	/** O + = O + */
	public static final String BLOODGROUP_OPlus = "O +";
	/** Set Blood Group.
		@param BloodGroup Blood Group	  */
	public void setBloodGroup (String BloodGroup)
	{

		set_Value (COLUMNNAME_BloodGroup, BloodGroup);
	}

	/** Get Blood Group.
		@return Blood Group	  */
	public String getBloodGroup () 
	{
		return (String)get_Value(COLUMNNAME_BloodGroup);
	}

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getC_BPartner_ID(), get_TrxName());	}

	/** Set Business Partner .
		@param C_BPartner_ID 
		Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID)
	{
		if (C_BPartner_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_ID, Integer.valueOf(C_BPartner_ID));
	}

	/** Get Business Partner .
		@return Identifies a Business Partner
	  */
	public int getC_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner_Location)MTable.get(getCtx(), org.compiere.model.I_C_BPartner_Location.Table_Name)
			.getPO(getC_BPartner_Location_ID(), get_TrxName());	}

	/** Set Partner Location.
		@param C_BPartner_Location_ID 
		Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID)
	{
		if (C_BPartner_Location_ID < 1) 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_BPartner_Location_ID, Integer.valueOf(C_BPartner_Location_ID));
	}

	/** Get Partner Location.
		@return Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_BPartner_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Job getC_Job() throws RuntimeException
    {
		return (org.compiere.model.I_C_Job)MTable.get(getCtx(), org.compiere.model.I_C_Job.Table_Name)
			.getPO(getC_Job_ID(), get_TrxName());	}

	/** Set Position.
		@param C_Job_ID 
		Job Position
	  */
	public void setC_Job_ID (int C_Job_ID)
	{
		if (C_Job_ID < 1) 
			set_Value (COLUMNNAME_C_Job_ID, null);
		else 
			set_Value (COLUMNNAME_C_Job_ID, Integer.valueOf(C_Job_ID));
	}

	/** Get Position.
		@return Job Position
	  */
	public int getC_Job_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Job_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Create Login.
		@param CreateLogin Create Login	  */
	public void setCreateLogin (boolean CreateLogin)
	{
		set_Value (COLUMNNAME_CreateLogin, Boolean.valueOf(CreateLogin));
	}

	/** Get Create Login.
		@return Create Login	  */
	public boolean isCreateLogin () 
	{
		Object oo = get_Value(COLUMNNAME_CreateLogin);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_DM_DMSDepartment getDM_DMSDepartment() throws RuntimeException
    {
		return (I_DM_DMSDepartment)MTable.get(getCtx(), I_DM_DMSDepartment.Table_Name)
			.getPO(getDM_DMSDepartment_ID(), get_TrxName());	}

	/** Set DMS Employee Department ID.
		@param DM_DMSDepartment_ID DMS Employee Department ID	  */
	public void setDM_DMSDepartment_ID (int DM_DMSDepartment_ID)
	{
		if (DM_DMSDepartment_ID < 1) 
			set_Value (COLUMNNAME_DM_DMSDepartment_ID, null);
		else 
			set_Value (COLUMNNAME_DM_DMSDepartment_ID, Integer.valueOf(DM_DMSDepartment_ID));
	}

	/** Get DMS Employee Department ID.
		@return DMS Employee Department ID	  */
	public int getDM_DMSDepartment_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_DMSDepartment_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set DMS Employee ID.
		@param DM_DMSEmployee_ID DMS Employee ID	  */
	public void setDM_DMSEmployee_ID (int DM_DMSEmployee_ID)
	{
		if (DM_DMSEmployee_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DM_DMSEmployee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_DMSEmployee_ID, Integer.valueOf(DM_DMSEmployee_ID));
	}

	/** Get DMS Employee ID.
		@return DMS Employee ID	  */
	public int getDM_DMSEmployee_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_DMSEmployee_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DM_DMSEmployeeType getDM_DMSEmployeeType() throws RuntimeException
    {
		return (I_DM_DMSEmployeeType)MTable.get(getCtx(), I_DM_DMSEmployeeType.Table_Name)
			.getPO(getDM_DMSEmployeeType_ID(), get_TrxName());	}

	/** Set DMS Employee type ID.
		@param DM_DMSEmployeeType_ID DMS Employee type ID	  */
	public void setDM_DMSEmployeeType_ID (int DM_DMSEmployeeType_ID)
	{
		if (DM_DMSEmployeeType_ID < 1) 
			set_Value (COLUMNNAME_DM_DMSEmployeeType_ID, null);
		else 
			set_Value (COLUMNNAME_DM_DMSEmployeeType_ID, Integer.valueOf(DM_DMSEmployeeType_ID));
	}

	/** Get DMS Employee type ID.
		@return DMS Employee type ID	  */
	public int getDM_DMSEmployeeType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_DMSEmployeeType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DM_DMSPosition getDM_DMSPosition() throws RuntimeException
    {
		return (I_DM_DMSPosition)MTable.get(getCtx(), I_DM_DMSPosition.Table_Name)
			.getPO(getDM_DMSPosition_ID(), get_TrxName());	}

	/** Set Employee Position ID.
		@param DM_DMSPosition_ID Employee Position ID	  */
	public void setDM_DMSPosition_ID (int DM_DMSPosition_ID)
	{
		if (DM_DMSPosition_ID < 1) 
			set_Value (COLUMNNAME_DM_DMSPosition_ID, null);
		else 
			set_Value (COLUMNNAME_DM_DMSPosition_ID, Integer.valueOf(DM_DMSPosition_ID));
	}

	/** Get Employee Position ID.
		@return Employee Position ID	  */
	public int getDM_DMSPosition_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_DMSPosition_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DM_EmployeeDivision getDM_EmployeeDivision() throws RuntimeException
    {
		return (I_DM_EmployeeDivision)MTable.get(getCtx(), I_DM_EmployeeDivision.Table_Name)
			.getPO(getDM_EmployeeDivision_ID(), get_TrxName());	}

	/** Set Employee Division ID.
		@param DM_EmployeeDivision_ID Employee Division ID	  */
	public void setDM_EmployeeDivision_ID (int DM_EmployeeDivision_ID)
	{
		if (DM_EmployeeDivision_ID < 1) 
			set_Value (COLUMNNAME_DM_EmployeeDivision_ID, null);
		else 
			set_Value (COLUMNNAME_DM_EmployeeDivision_ID, Integer.valueOf(DM_EmployeeDivision_ID));
	}

	/** Get Employee Division ID.
		@return Employee Division ID	  */
	public int getDM_EmployeeDivision_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_EmployeeDivision_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DM_EmployeeLocation getDM_EmployeeLocation() throws RuntimeException
    {
		return (I_DM_EmployeeLocation)MTable.get(getCtx(), I_DM_EmployeeLocation.Table_Name)
			.getPO(getDM_EmployeeLocation_ID(), get_TrxName());	}

	/** Set Employee Location ID.
		@param DM_EmployeeLocation_ID Employee Location ID	  */
	public void setDM_EmployeeLocation_ID (int DM_EmployeeLocation_ID)
	{
		if (DM_EmployeeLocation_ID < 1) 
			set_Value (COLUMNNAME_DM_EmployeeLocation_ID, null);
		else 
			set_Value (COLUMNNAME_DM_EmployeeLocation_ID, Integer.valueOf(DM_EmployeeLocation_ID));
	}

	/** Get Employee Location ID.
		@return Employee Location ID	  */
	public int getDM_EmployeeLocation_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_EmployeeLocation_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DM_EmployeeLocationType getDM_EmployeeLocationType() throws RuntimeException
    {
		return (I_DM_EmployeeLocationType)MTable.get(getCtx(), I_DM_EmployeeLocationType.Table_Name)
			.getPO(getDM_EmployeeLocationType_ID(), get_TrxName());	}

	/** Set Employee Location Type ID.
		@param DM_EmployeeLocationType_ID Employee Location Type ID	  */
	public void setDM_EmployeeLocationType_ID (int DM_EmployeeLocationType_ID)
	{
		if (DM_EmployeeLocationType_ID < 1) 
			set_Value (COLUMNNAME_DM_EmployeeLocationType_ID, null);
		else 
			set_Value (COLUMNNAME_DM_EmployeeLocationType_ID, Integer.valueOf(DM_EmployeeLocationType_ID));
	}

	/** Get Employee Location Type ID.
		@return Employee Location Type ID	  */
	public int getDM_EmployeeLocationType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_EmployeeLocationType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DM_EmpStatus getDM_EmpStatus() throws RuntimeException
    {
		return (I_DM_EmpStatus)MTable.get(getCtx(), I_DM_EmpStatus.Table_Name)
			.getPO(getDM_EmpStatus_ID(), get_TrxName());	}

	/** Set Employee Status ID.
		@param DM_EmpStatus_ID Employee Status ID	  */
	public void setDM_EmpStatus_ID (int DM_EmpStatus_ID)
	{
		if (DM_EmpStatus_ID < 1) 
			set_Value (COLUMNNAME_DM_EmpStatus_ID, null);
		else 
			set_Value (COLUMNNAME_DM_EmpStatus_ID, Integer.valueOf(DM_EmpStatus_ID));
	}

	/** Get Employee Status ID.
		@return Employee Status ID	  */
	public int getDM_EmpStatus_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_EmpStatus_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EMail User ID.
		@param EMailUser 
		User Name (ID) in the Mail System
	  */
	public void setEMailUser (String EMailUser)
	{
		set_Value (COLUMNNAME_EMailUser, EMailUser);
	}

	/** Get EMail User ID.
		@return User Name (ID) in the Mail System
	  */
	public String getEMailUser () 
	{
		return (String)get_Value(COLUMNNAME_EMailUser);
	}

	/** Set Employee Code.
		@param EmployeeCode Employee Code	  */
	public void setEmployeeCode (String EmployeeCode)
	{
		set_Value (COLUMNNAME_EmployeeCode, EmployeeCode);
	}

	/** Get Employee Code.
		@return Employee Code	  */
	public String getEmployeeCode () 
	{
		return (String)get_Value(COLUMNNAME_EmployeeCode);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getEmployeeCode());
    }

	/** Set Employee Image.
		@param EmployeeImage_ID 
		Employee Image
	  */
	public void setEmployeeImage_ID (int EmployeeImage_ID)
	{
		if (EmployeeImage_ID < 1) 
			set_Value (COLUMNNAME_EmployeeImage_ID, null);
		else 
			set_Value (COLUMNNAME_EmployeeImage_ID, Integer.valueOf(EmployeeImage_ID));
	}

	/** Get Employee Image.
		@return Employee Image
	  */
	public int getEmployeeImage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_EmployeeImage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** EmployeeStatus AD_Reference_ID=53617 */
	public static final int EMPLOYEESTATUS_AD_Reference_ID=53617;
	/** Without Reason = 00 */
	public static final String EMPLOYEESTATUS_WithoutReason = "00";
	/** On Leave = 01 */
	public static final String EMPLOYEESTATUS_OnLeave = "01";
	/** Left Service = 02 */
	public static final String EMPLOYEESTATUS_LeftService = "02";
	/** Retired = 03 */
	public static final String EMPLOYEESTATUS_Retired = "03";
	/** Expired = 05 */
	public static final String EMPLOYEESTATUS_Expired = "05";
	/** Non Implemented Area = 06 */
	public static final String EMPLOYEESTATUS_NonImplementedArea = "06";
	/** Compliance by Immediate Ex = 07 */
	public static final String EMPLOYEESTATUS_ComplianceByImmediateEx = "07";
	/** Suspension of work = 08 */
	public static final String EMPLOYEESTATUS_SuspensionOfWork = "08";
	/** Strike/Lockout = 09 */
	public static final String EMPLOYEESTATUS_StrikeLockout = "09";
	/** Retrenchment = 10 */
	public static final String EMPLOYEESTATUS_Retrenchment = "10";
	/** No Work = 11 */
	public static final String EMPLOYEESTATUS_NoWork = "11";
	/** Doesnt Belong To This Employee = 12 */
	public static final String EMPLOYEESTATUS_DoesntBelongToThisEmployee = "12";
	/** Active = 13 */
	public static final String EMPLOYEESTATUS_Active = "13";
	/** Out of Coverage = OC */
	public static final String EMPLOYEESTATUS_OutOfCoverage = "OC";
	/** Set Employee Status.
		@param EmployeeStatus Employee Status	  */
	public void setEmployeeStatus (String EmployeeStatus)
	{

		set_Value (COLUMNNAME_EmployeeStatus, EmployeeStatus);
	}

	/** Get Employee Status.
		@return Employee Status	  */
	public String getEmployeeStatus () 
	{
		return (String)get_Value(COLUMNNAME_EmployeeStatus);
	}

	/** Set End Date.
		@param EndDate 
		Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate)
	{
		set_Value (COLUMNNAME_EndDate, EndDate);
	}

	/** Get End Date.
		@return Last effective date (inclusive)
	  */
	public Timestamp getEndDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_EndDate);
	}

	/** Set Father's Name.
		@param FathersName 
		Father's Name
	  */
	public void setFathersName (String FathersName)
	{
		set_Value (COLUMNNAME_FathersName, FathersName);
	}

	/** Get Father's Name.
		@return Father's Name
	  */
	public String getFathersName () 
	{
		return (String)get_Value(COLUMNNAME_FathersName);
	}

	/** Set First Name.
		@param FName First Name	  */
	public void setFName (String FName)
	{
		set_Value (COLUMNNAME_FName, FName);
	}

	/** Get First Name.
		@return First Name	  */
	public String getFName () 
	{
		return (String)get_Value(COLUMNNAME_FName);
	}

	/** Gender AD_Reference_ID=53612 */
	public static final int GENDER_AD_Reference_ID=53612;
	/** Female = Female */
	public static final String GENDER_Female = "Female";
	/** Male = Male */
	public static final String GENDER_Male = "Male";
	/** Set Gender.
		@param Gender Gender	  */
	public void setGender (String Gender)
	{

		set_Value (COLUMNNAME_Gender, Gender);
	}

	/** Get Gender.
		@return Gender	  */
	public String getGender () 
	{
		return (String)get_Value(COLUMNNAME_Gender);
	}

	public org.eevolution.model.I_HR_Race getHR_Race() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_Race)MTable.get(getCtx(), org.eevolution.model.I_HR_Race.Table_Name)
			.getPO(getHR_Race_ID(), get_TrxName());	}

	/** Set Race.
		@param HR_Race_ID 
		Race
	  */
	public void setHR_Race_ID (int HR_Race_ID)
	{
		if (HR_Race_ID < 1) 
			set_Value (COLUMNNAME_HR_Race_ID, null);
		else 
			set_Value (COLUMNNAME_HR_Race_ID, Integer.valueOf(HR_Race_ID));
	}

	/** Get Race.
		@return Race
	  */
	public int getHR_Race_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_Race_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.eevolution.model.I_HR_SkillType getHR_SkillType() throws RuntimeException
    {
		return (org.eevolution.model.I_HR_SkillType)MTable.get(getCtx(), org.eevolution.model.I_HR_SkillType.Table_Name)
			.getPO(getHR_SkillType_ID(), get_TrxName());	}

	/** Set Skill Type.
		@param HR_SkillType_ID 
		Skill Type for an Employee
	  */
	public void setHR_SkillType_ID (int HR_SkillType_ID)
	{
		if (HR_SkillType_ID < 1) 
			set_Value (COLUMNNAME_HR_SkillType_ID, null);
		else 
			set_Value (COLUMNNAME_HR_SkillType_ID, Integer.valueOf(HR_SkillType_ID));
	}

	/** Get Skill Type.
		@return Skill Type for an Employee
	  */
	public int getHR_SkillType_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HR_SkillType_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Identification Mark.
		@param IdentificationMark 
		Identification Mark
	  */
	public void setIdentificationMark (String IdentificationMark)
	{
		set_Value (COLUMNNAME_IdentificationMark, IdentificationMark);
	}

	/** Get Identification Mark.
		@return Identification Mark
	  */
	public String getIdentificationMark () 
	{
		return (String)get_Value(COLUMNNAME_IdentificationMark);
	}

	/** Set Image URL.
		@param ImageURL 
		URL of  image
	  */
	public void setImageURL (String ImageURL)
	{
		set_Value (COLUMNNAME_ImageURL, ImageURL);
	}

	/** Get Image URL.
		@return URL of  image
	  */
	public String getImageURL () 
	{
		return (String)get_Value(COLUMNNAME_ImageURL);
	}

	/** Set Manager.
		@param IsManager 
		Defines employee as manager
	  */
	public void setIsManager (boolean IsManager)
	{
		set_Value (COLUMNNAME_IsManager, Boolean.valueOf(IsManager));
	}

	/** Get Manager.
		@return Defines employee as manager
	  */
	public boolean isManager () 
	{
		Object oo = get_Value(COLUMNNAME_IsManager);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Resigned.
		@param IsResigned Resigned	  */
	public void setIsResigned (boolean IsResigned)
	{
		set_Value (COLUMNNAME_IsResigned, Boolean.valueOf(IsResigned));
	}

	/** Get Resigned.
		@return Resigned	  */
	public boolean isResigned () 
	{
		Object oo = get_Value(COLUMNNAME_IsResigned);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Last Name (Surname).
		@param LName Last Name (Surname)	  */
	public void setLName (String LName)
	{
		set_Value (COLUMNNAME_LName, LName);
	}

	/** Get Last Name (Surname).
		@return Last Name (Surname)	  */
	public String getLName () 
	{
		return (String)get_Value(COLUMNNAME_LName);
	}

	public org.compiere.model.I_AD_User getLoginUser() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getLoginUser_ID(), get_TrxName());	}

	/** Set Login UserID.
		@param LoginUser_ID Login UserID	  */
	public void setLoginUser_ID (int LoginUser_ID)
	{
		if (LoginUser_ID < 1) 
			set_Value (COLUMNNAME_LoginUser_ID, null);
		else 
			set_Value (COLUMNNAME_LoginUser_ID, Integer.valueOf(LoginUser_ID));
	}

	/** Get Login UserID.
		@return Login UserID	  */
	public int getLoginUser_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_LoginUser_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Logo.
		@param Logo_ID Logo	  */
	public void setLogo_ID (int Logo_ID)
	{
		if (Logo_ID < 1) 
			set_Value (COLUMNNAME_Logo_ID, null);
		else 
			set_Value (COLUMNNAME_Logo_ID, Integer.valueOf(Logo_ID));
	}

	/** Get Logo.
		@return Logo	  */
	public int getLogo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Logo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** MaritalStatus AD_Reference_ID=53614 */
	public static final int MARITALSTATUS_AD_Reference_ID=53614;
	/** Divorced = Divorced */
	public static final String MARITALSTATUS_Divorced = "Divorced";
	/** Live-in = Live-in */
	public static final String MARITALSTATUS_Live_In = "Live-in";
	/** Married = Married */
	public static final String MARITALSTATUS_Married = "Married";
	/** Single = Single */
	public static final String MARITALSTATUS_Single = "Single";
	/** Widow = Widow */
	public static final String MARITALSTATUS_Widow = "Widow";
	/** Windower = Windower */
	public static final String MARITALSTATUS_Windower = "Windower";
	/** Set Marital Status.
		@param MaritalStatus Marital Status	  */
	public void setMaritalStatus (String MaritalStatus)
	{

		set_Value (COLUMNNAME_MaritalStatus, MaritalStatus);
	}

	/** Get Marital Status.
		@return Marital Status	  */
	public String getMaritalStatus () 
	{
		return (String)get_Value(COLUMNNAME_MaritalStatus);
	}

	/** Set Marriage Anniversary Date.
		@param MarriageAnniversaryDate 
		Marriage Anniversary Date
	  */
	public void setMarriageAnniversaryDate (Timestamp MarriageAnniversaryDate)
	{
		set_Value (COLUMNNAME_MarriageAnniversaryDate, MarriageAnniversaryDate);
	}

	/** Get Marriage Anniversary Date.
		@return Marriage Anniversary Date
	  */
	public Timestamp getMarriageAnniversaryDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_MarriageAnniversaryDate);
	}

	/** Set Middle Name.
		@param MName Middle Name	  */
	public void setMName (String MName)
	{
		set_Value (COLUMNNAME_MName, MName);
	}

	/** Get Middle Name.
		@return Middle Name	  */
	public String getMName () 
	{
		return (String)get_Value(COLUMNNAME_MName);
	}

	/** Set Mobile Phone.
		@param MobilePhone 
		Identifies an alternate telephone mobile number.
	  */
	public void setMobilePhone (String MobilePhone)
	{
		set_Value (COLUMNNAME_MobilePhone, MobilePhone);
	}

	/** Get Mobile Phone.
		@return Identifies an alternate telephone mobile number.
	  */
	public String getMobilePhone () 
	{
		return (String)get_Value(COLUMNNAME_MobilePhone);
	}

	/** Set Monthly Salary.
		@param MonthlySalary 
		Monthly Salary
	  */
	public void setMonthlySalary (BigDecimal MonthlySalary)
	{
		set_Value (COLUMNNAME_MonthlySalary, MonthlySalary);
	}

	/** Get Monthly Salary.
		@return Monthly Salary
	  */
	public BigDecimal getMonthlySalary () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MonthlySalary);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set National Code.
		@param NationalCode National Code	  */
	public void setNationalCode (String NationalCode)
	{
		set_Value (COLUMNNAME_NationalCode, NationalCode);
	}

	/** Get National Code.
		@return National Code	  */
	public String getNationalCode () 
	{
		return (String)get_Value(COLUMNNAME_NationalCode);
	}

	/** Set Nationality.
		@param Nationality_ID 
		Nationality
	  */
	public void setNationality_ID (int Nationality_ID)
	{
		if (Nationality_ID < 1) 
			set_Value (COLUMNNAME_Nationality_ID, null);
		else 
			set_Value (COLUMNNAME_Nationality_ID, Integer.valueOf(Nationality_ID));
	}

	/** Get Nationality.
		@return Nationality
	  */
	public int getNationality_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Nationality_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Partners Birth Date.
		@param PartnersBirthDate 
		Partners Birth Date
	  */
	public void setPartnersBirthDate (Timestamp PartnersBirthDate)
	{
		set_Value (COLUMNNAME_PartnersBirthDate, PartnersBirthDate);
	}

	/** Get Partners Birth Date.
		@return Partners Birth Date
	  */
	public Timestamp getPartnersBirthDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_PartnersBirthDate);
	}

	/** Set Partner's Name.
		@param PartnersName 
		Partner's Name
	  */
	public void setPartnersName (String PartnersName)
	{
		set_Value (COLUMNNAME_PartnersName, PartnersName);
	}

	/** Get Partner's Name.
		@return Partner's Name
	  */
	public String getPartnersName () 
	{
		return (String)get_Value(COLUMNNAME_PartnersName);
	}

	/** Set Place of Birth.
		@param PlaceOfBirth 
		Place of Birth
	  */
	public void setPlaceOfBirth (String PlaceOfBirth)
	{
		set_Value (COLUMNNAME_PlaceOfBirth, PlaceOfBirth);
	}

	/** Get Place of Birth.
		@return Place of Birth
	  */
	public String getPlaceOfBirth () 
	{
		return (String)get_Value(COLUMNNAME_PlaceOfBirth);
	}

	/** Set Send EMail.
		@param SendEMail 
		Enable sending Document EMail
	  */
	public void setSendEMail (boolean SendEMail)
	{
		set_Value (COLUMNNAME_SendEMail, Boolean.valueOf(SendEMail));
	}

	/** Get Send EMail.
		@return Enable sending Document EMail
	  */
	public boolean isSendEMail () 
	{
		Object oo = get_Value(COLUMNNAME_SendEMail);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Social Security Code.
		@param SSCode Social Security Code	  */
	public void setSSCode (String SSCode)
	{
		set_Value (COLUMNNAME_SSCode, SSCode);
	}

	/** Get Social Security Code.
		@return Social Security Code	  */
	public String getSSCode () 
	{
		return (String)get_Value(COLUMNNAME_SSCode);
	}

	/** Set Start Date.
		@param StartDate 
		First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate)
	{
		set_Value (COLUMNNAME_StartDate, StartDate);
	}

	/** Get Start Date.
		@return First effective day (inclusive)
	  */
	public Timestamp getStartDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_StartDate);
	}

	/** Set Thumb Image.
		@param ThumbImage_ID 
		Thumb Image
	  */
	public void setThumbImage_ID (int ThumbImage_ID)
	{
		if (ThumbImage_ID < 1) 
			set_Value (COLUMNNAME_ThumbImage_ID, null);
		else 
			set_Value (COLUMNNAME_ThumbImage_ID, Integer.valueOf(ThumbImage_ID));
	}

	/** Get Thumb Image.
		@return Thumb Image
	  */
	public int getThumbImage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ThumbImage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set User ID.
		@param UserID 
		User ID or account number
	  */
	public void setUserID (String UserID)
	{
		set_Value (COLUMNNAME_UserID, UserID);
	}

	/** Get User ID.
		@return User ID or account number
	  */
	public String getUserID () 
	{
		return (String)get_Value(COLUMNNAME_UserID);
	}

	/** UserLevel AD_Reference_ID=1000000 */
	public static final int USERLEVEL_AD_Reference_ID=1000000;
	/** DMS Editor = E */
	public static final String USERLEVEL_DMSEditor = "E";
	/** DMS User = U */
	public static final String USERLEVEL_DMSUser = "U";
	/** Set User Level.
		@param UserLevel 
		System Client Organization
	  */
	public void setUserLevel (String UserLevel)
	{

		set_Value (COLUMNNAME_UserLevel, UserLevel);
	}

	/** Get User Level.
		@return System Client Organization
	  */
	public String getUserLevel () 
	{
		return (String)get_Value(COLUMNNAME_UserLevel);
	}
}