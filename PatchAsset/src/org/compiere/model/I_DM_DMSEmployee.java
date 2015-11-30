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
package org.compiere.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.util.KeyNamePair;

/** Generated Interface for DM_DMSEmployee
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_DM_DMSEmployee 
{

    /** TableName=DM_DMSEmployee */
    public static final String Table_Name = "DM_DMSEmployee";

    /** AD_Table_ID=1000008 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name Birthday */
    public static final String COLUMNNAME_Birthday = "Birthday";

	/** Set Birthday.
	  * Birthday or Anniversary day
	  */
	public void setBirthday (Timestamp Birthday);

	/** Get Birthday.
	  * Birthday or Anniversary day
	  */
	public Timestamp getBirthday();

    /** Column name BloodGroup */
    public static final String COLUMNNAME_BloodGroup = "BloodGroup";

	/** Set Blood Group	  */
	public void setBloodGroup (String BloodGroup);

	/** Get Blood Group	  */
	public String getBloodGroup();

    /** Column name C_BPartner_ID */
    public static final String COLUMNNAME_C_BPartner_ID = "C_BPartner_ID";

	/** Set Business Partner .
	  * Identifies a Business Partner
	  */
	public void setC_BPartner_ID (int C_BPartner_ID);

	/** Get Business Partner .
	  * Identifies a Business Partner
	  */
	public int getC_BPartner_ID();

	public org.compiere.model.I_C_BPartner getC_BPartner() throws RuntimeException;

    /** Column name C_BPartner_Location_ID */
    public static final String COLUMNNAME_C_BPartner_Location_ID = "C_BPartner_Location_ID";

	/** Set Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public void setC_BPartner_Location_ID (int C_BPartner_Location_ID);

	/** Get Partner Location.
	  * Identifies the (ship to) address for this Business Partner
	  */
	public int getC_BPartner_Location_ID();

	public org.compiere.model.I_C_BPartner_Location getC_BPartner_Location() throws RuntimeException;

    /** Column name C_Job_ID */
    public static final String COLUMNNAME_C_Job_ID = "C_Job_ID";

	/** Set Position.
	  * Job Position
	  */
	public void setC_Job_ID (int C_Job_ID);

	/** Get Position.
	  * Job Position
	  */
	public int getC_Job_ID();

	public org.compiere.model.I_C_Job getC_Job() throws RuntimeException;

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name CreateLogin */
    public static final String COLUMNNAME_CreateLogin = "CreateLogin";

	/** Set Create Login	  */
	public void setCreateLogin (boolean CreateLogin);

	/** Get Create Login	  */
	public boolean isCreateLogin();

    /** Column name DM_DMSDepartment_ID */
    public static final String COLUMNNAME_DM_DMSDepartment_ID = "DM_DMSDepartment_ID";

	/** Set DMS Employee Department ID	  */
	public void setDM_DMSDepartment_ID (int DM_DMSDepartment_ID);

	/** Get DMS Employee Department ID	  */
	public int getDM_DMSDepartment_ID();

	public I_DM_DMSDepartment getDM_DMSDepartment() throws RuntimeException;

    /** Column name DM_DMSEmployee_ID */
    public static final String COLUMNNAME_DM_DMSEmployee_ID = "DM_DMSEmployee_ID";

	/** Set DMS Employee ID	  */
	public void setDM_DMSEmployee_ID (int DM_DMSEmployee_ID);

	/** Get DMS Employee ID	  */
	public int getDM_DMSEmployee_ID();

    /** Column name DM_DMSEmployeeType_ID */
    public static final String COLUMNNAME_DM_DMSEmployeeType_ID = "DM_DMSEmployeeType_ID";

	/** Set DMS Employee type ID	  */
	public void setDM_DMSEmployeeType_ID (int DM_DMSEmployeeType_ID);

	/** Get DMS Employee type ID	  */
	public int getDM_DMSEmployeeType_ID();

	public I_DM_DMSEmployeeType getDM_DMSEmployeeType() throws RuntimeException;

    /** Column name DM_DMSPosition_ID */
    public static final String COLUMNNAME_DM_DMSPosition_ID = "DM_DMSPosition_ID";

	/** Set Employee Position ID	  */
	public void setDM_DMSPosition_ID (int DM_DMSPosition_ID);

	/** Get Employee Position ID	  */
	public int getDM_DMSPosition_ID();

	public I_DM_DMSPosition getDM_DMSPosition() throws RuntimeException;

    /** Column name DM_EmployeeDivision_ID */
    public static final String COLUMNNAME_DM_EmployeeDivision_ID = "DM_EmployeeDivision_ID";

	/** Set Employee Division ID	  */
	public void setDM_EmployeeDivision_ID (int DM_EmployeeDivision_ID);

	/** Get Employee Division ID	  */
	public int getDM_EmployeeDivision_ID();

	public I_DM_EmployeeDivision getDM_EmployeeDivision() throws RuntimeException;

    /** Column name DM_EmployeeLocation_ID */
    public static final String COLUMNNAME_DM_EmployeeLocation_ID = "DM_EmployeeLocation_ID";

	/** Set Employee Location ID	  */
	public void setDM_EmployeeLocation_ID (int DM_EmployeeLocation_ID);

	/** Get Employee Location ID	  */
	public int getDM_EmployeeLocation_ID();

	public I_DM_EmployeeLocation getDM_EmployeeLocation() throws RuntimeException;

    /** Column name DM_EmployeeLocationType_ID */
    public static final String COLUMNNAME_DM_EmployeeLocationType_ID = "DM_EmployeeLocationType_ID";

	/** Set Employee Location Type ID	  */
	public void setDM_EmployeeLocationType_ID (int DM_EmployeeLocationType_ID);

	/** Get Employee Location Type ID	  */
	public int getDM_EmployeeLocationType_ID();

	public I_DM_EmployeeLocationType getDM_EmployeeLocationType() throws RuntimeException;

    /** Column name DM_EmpStatus_ID */
    public static final String COLUMNNAME_DM_EmpStatus_ID = "DM_EmpStatus_ID";

	/** Set Employee Status ID	  */
	public void setDM_EmpStatus_ID (int DM_EmpStatus_ID);

	/** Get Employee Status ID	  */
	public int getDM_EmpStatus_ID();

	public I_DM_EmpStatus getDM_EmpStatus() throws RuntimeException;

    /** Column name EMailUser */
    public static final String COLUMNNAME_EMailUser = "EMailUser";

	/** Set EMail User ID.
	  * User Name (ID) in the Mail System
	  */
	public void setEMailUser (String EMailUser);

	/** Get EMail User ID.
	  * User Name (ID) in the Mail System
	  */
	public String getEMailUser();

    /** Column name EmployeeCode */
    public static final String COLUMNNAME_EmployeeCode = "EmployeeCode";

	/** Set Employee Code	  */
	public void setEmployeeCode (String EmployeeCode);

	/** Get Employee Code	  */
	public String getEmployeeCode();

    /** Column name EmployeeImage_ID */
    public static final String COLUMNNAME_EmployeeImage_ID = "EmployeeImage_ID";

	/** Set Employee Image.
	  * Employee Image
	  */
	public void setEmployeeImage_ID (int EmployeeImage_ID);

	/** Get Employee Image.
	  * Employee Image
	  */
	public int getEmployeeImage_ID();

    /** Column name EmployeeStatus */
    public static final String COLUMNNAME_EmployeeStatus = "EmployeeStatus";

	/** Set Employee Status	  */
	public void setEmployeeStatus (String EmployeeStatus);

	/** Get Employee Status	  */
	public String getEmployeeStatus();

    /** Column name EndDate */
    public static final String COLUMNNAME_EndDate = "EndDate";

	/** Set End Date.
	  * Last effective date (inclusive)
	  */
	public void setEndDate (Timestamp EndDate);

	/** Get End Date.
	  * Last effective date (inclusive)
	  */
	public Timestamp getEndDate();

    /** Column name FathersName */
    public static final String COLUMNNAME_FathersName = "FathersName";

	/** Set Father's Name.
	  * Father's Name
	  */
	public void setFathersName (String FathersName);

	/** Get Father's Name.
	  * Father's Name
	  */
	public String getFathersName();

    /** Column name FName */
    public static final String COLUMNNAME_FName = "FName";

	/** Set First Name	  */
	public void setFName (String FName);

	/** Get First Name	  */
	public String getFName();

    /** Column name Gender */
    public static final String COLUMNNAME_Gender = "Gender";

	/** Set Gender	  */
	public void setGender (String Gender);

	/** Get Gender	  */
	public String getGender();

    /** Column name HR_Race_ID */
    public static final String COLUMNNAME_HR_Race_ID = "HR_Race_ID";

	/** Set Race.
	  * Race
	  */
	public void setHR_Race_ID (int HR_Race_ID);

	/** Get Race.
	  * Race
	  */
	public int getHR_Race_ID();

	public org.eevolution.model.I_HR_Race getHR_Race() throws RuntimeException;

    /** Column name HR_SkillType_ID */
    public static final String COLUMNNAME_HR_SkillType_ID = "HR_SkillType_ID";

	/** Set Skill Type.
	  * Skill Type for an Employee
	  */
	public void setHR_SkillType_ID (int HR_SkillType_ID);

	/** Get Skill Type.
	  * Skill Type for an Employee
	  */
	public int getHR_SkillType_ID();

	public org.eevolution.model.I_HR_SkillType getHR_SkillType() throws RuntimeException;

    /** Column name IdentificationMark */
    public static final String COLUMNNAME_IdentificationMark = "IdentificationMark";

	/** Set Identification Mark.
	  * Identification Mark
	  */
	public void setIdentificationMark (String IdentificationMark);

	/** Get Identification Mark.
	  * Identification Mark
	  */
	public String getIdentificationMark();

    /** Column name ImageURL */
    public static final String COLUMNNAME_ImageURL = "ImageURL";

	/** Set Image URL.
	  * URL of  image
	  */
	public void setImageURL (String ImageURL);

	/** Get Image URL.
	  * URL of  image
	  */
	public String getImageURL();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name IsManager */
    public static final String COLUMNNAME_IsManager = "IsManager";

	/** Set Manager.
	  * Defines employee as manager
	  */
	public void setIsManager (boolean IsManager);

	/** Get Manager.
	  * Defines employee as manager
	  */
	public boolean isManager();

    /** Column name IsResigned */
    public static final String COLUMNNAME_IsResigned = "IsResigned";

	/** Set Resigned	  */
	public void setIsResigned (boolean IsResigned);

	/** Get Resigned	  */
	public boolean isResigned();

    /** Column name LName */
    public static final String COLUMNNAME_LName = "LName";

	/** Set Last Name (Surname)	  */
	public void setLName (String LName);

	/** Get Last Name (Surname)	  */
	public String getLName();

    /** Column name LoginUser_ID */
    public static final String COLUMNNAME_LoginUser_ID = "LoginUser_ID";

	/** Set Login UserID	  */
	public void setLoginUser_ID (int LoginUser_ID);

	/** Get Login UserID	  */
	public int getLoginUser_ID();

	public org.compiere.model.I_AD_User getLoginUser() throws RuntimeException;

    /** Column name Logo_ID */
    public static final String COLUMNNAME_Logo_ID = "Logo_ID";

	/** Set Logo	  */
	public void setLogo_ID (int Logo_ID);

	/** Get Logo	  */
	public int getLogo_ID();

    /** Column name MaritalStatus */
    public static final String COLUMNNAME_MaritalStatus = "MaritalStatus";

	/** Set Marital Status	  */
	public void setMaritalStatus (String MaritalStatus);

	/** Get Marital Status	  */
	public String getMaritalStatus();

    /** Column name MarriageAnniversaryDate */
    public static final String COLUMNNAME_MarriageAnniversaryDate = "MarriageAnniversaryDate";

	/** Set Marriage Anniversary Date.
	  * Marriage Anniversary Date
	  */
	public void setMarriageAnniversaryDate (Timestamp MarriageAnniversaryDate);

	/** Get Marriage Anniversary Date.
	  * Marriage Anniversary Date
	  */
	public Timestamp getMarriageAnniversaryDate();

    /** Column name MName */
    public static final String COLUMNNAME_MName = "MName";

	/** Set Middle Name	  */
	public void setMName (String MName);

	/** Get Middle Name	  */
	public String getMName();

    /** Column name MobilePhone */
    public static final String COLUMNNAME_MobilePhone = "MobilePhone";

	/** Set Mobile Phone.
	  * Identifies an alternate telephone mobile number.
	  */
	public void setMobilePhone (String MobilePhone);

	/** Get Mobile Phone.
	  * Identifies an alternate telephone mobile number.
	  */
	public String getMobilePhone();

    /** Column name MonthlySalary */
    public static final String COLUMNNAME_MonthlySalary = "MonthlySalary";

	/** Set Monthly Salary.
	  * Monthly Salary
	  */
	public void setMonthlySalary (BigDecimal MonthlySalary);

	/** Get Monthly Salary.
	  * Monthly Salary
	  */
	public BigDecimal getMonthlySalary();

    /** Column name NationalCode */
    public static final String COLUMNNAME_NationalCode = "NationalCode";

	/** Set National Code	  */
	public void setNationalCode (String NationalCode);

	/** Get National Code	  */
	public String getNationalCode();

    /** Column name Nationality_ID */
    public static final String COLUMNNAME_Nationality_ID = "Nationality_ID";

	/** Set Nationality.
	  * Nationality
	  */
	public void setNationality_ID (int Nationality_ID);

	/** Get Nationality.
	  * Nationality
	  */
	public int getNationality_ID();

    /** Column name PartnersBirthDate */
    public static final String COLUMNNAME_PartnersBirthDate = "PartnersBirthDate";

	/** Set Partners Birth Date.
	  * Partners Birth Date
	  */
	public void setPartnersBirthDate (Timestamp PartnersBirthDate);

	/** Get Partners Birth Date.
	  * Partners Birth Date
	  */
	public Timestamp getPartnersBirthDate();

    /** Column name PartnersName */
    public static final String COLUMNNAME_PartnersName = "PartnersName";

	/** Set Partner's Name.
	  * Partner's Name
	  */
	public void setPartnersName (String PartnersName);

	/** Get Partner's Name.
	  * Partner's Name
	  */
	public String getPartnersName();

    /** Column name PlaceOfBirth */
    public static final String COLUMNNAME_PlaceOfBirth = "PlaceOfBirth";

	/** Set Place of Birth.
	  * Place of Birth
	  */
	public void setPlaceOfBirth (String PlaceOfBirth);

	/** Get Place of Birth.
	  * Place of Birth
	  */
	public String getPlaceOfBirth();

    /** Column name SendEMail */
    public static final String COLUMNNAME_SendEMail = "SendEMail";

	/** Set Send EMail.
	  * Enable sending Document EMail
	  */
	public void setSendEMail (boolean SendEMail);

	/** Get Send EMail.
	  * Enable sending Document EMail
	  */
	public boolean isSendEMail();

    /** Column name SSCode */
    public static final String COLUMNNAME_SSCode = "SSCode";

	/** Set Social Security Code	  */
	public void setSSCode (String SSCode);

	/** Get Social Security Code	  */
	public String getSSCode();

    /** Column name StartDate */
    public static final String COLUMNNAME_StartDate = "StartDate";

	/** Set Start Date.
	  * First effective day (inclusive)
	  */
	public void setStartDate (Timestamp StartDate);

	/** Get Start Date.
	  * First effective day (inclusive)
	  */
	public Timestamp getStartDate();

    /** Column name ThumbImage_ID */
    public static final String COLUMNNAME_ThumbImage_ID = "ThumbImage_ID";

	/** Set Thumb Image.
	  * Thumb Image
	  */
	public void setThumbImage_ID (int ThumbImage_ID);

	/** Get Thumb Image.
	  * Thumb Image
	  */
	public int getThumbImage_ID();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name UserID */
    public static final String COLUMNNAME_UserID = "UserID";

	/** Set User ID.
	  * User ID or account number
	  */
	public void setUserID (String UserID);

	/** Get User ID.
	  * User ID or account number
	  */
	public String getUserID();

    /** Column name UserLevel */
    public static final String COLUMNNAME_UserLevel = "UserLevel";

	/** Set User Level.
	  * System Client Organization
	  */
	public void setUserLevel (String UserLevel);

	/** Get User Level.
	  * System Client Organization
	  */
	public String getUserLevel();
}
