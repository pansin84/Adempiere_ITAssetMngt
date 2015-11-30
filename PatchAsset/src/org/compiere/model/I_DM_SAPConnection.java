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

/** Generated Interface for DM_SAPConnection
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_DM_SAPConnection 
{

    /** TableName=DM_SAPConnection */
    public static final String Table_Name = "DM_SAPConnection";

    /** AD_Table_ID=1000007 */
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

    /** Column name ClientCode */
    public static final String COLUMNNAME_ClientCode = "ClientCode";

	/** Set Client Code	  */
	public void setClientCode (String ClientCode);

	/** Get Client Code	  */
	public String getClientCode();

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

    /** Column name DM_SAPConnection_ID */
    public static final String COLUMNNAME_DM_SAPConnection_ID = "DM_SAPConnection_ID";

	/** Set SAP Connection Configuration ID	  */
	public void setDM_SAPConnection_ID (int DM_SAPConnection_ID);

	/** Get SAP Connection Configuration ID	  */
	public int getDM_SAPConnection_ID();

    /** Column name Host */
    public static final String COLUMNNAME_Host = "Host";

	/** Set Host	  */
	public void setHost (String Host);

	/** Get Host	  */
	public String getHost();

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

    /** Column name IsDefault */
    public static final String COLUMNNAME_IsDefault = "IsDefault";

	/** Set Default.
	  * Default value
	  */
	public void setIsDefault (boolean IsDefault);

	/** Get Default.
	  * Default value
	  */
	public boolean isDefault();

    /** Column name LanguageISO */
    public static final String COLUMNNAME_LanguageISO = "LanguageISO";

	/** Set ISO Language Code.
	  * Lower-case two-letter ISO-3166 code - http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt 
	  */
	public void setLanguageISO (String LanguageISO);

	/** Get ISO Language Code.
	  * Lower-case two-letter ISO-3166 code - http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt 
	  */
	public String getLanguageISO();

    /** Column name LoginUserName */
    public static final String COLUMNNAME_LoginUserName = "LoginUserName";

	/** Set Login User Name	  */
	public void setLoginUserName (String LoginUserName);

	/** Get Login User Name	  */
	public String getLoginUserName();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Password */
    public static final String COLUMNNAME_Password = "Password";

	/** Set Password.
	  * Password of any length (case sensitive)
	  */
	public void setPassword (String Password);

	/** Get Password.
	  * Password of any length (case sensitive)
	  */
	public String getPassword();

    /** Column name SAPDestination */
    public static final String COLUMNNAME_SAPDestination = "SAPDestination";

	/** Set SAP Destination	  */
	public void setSAPDestination (String SAPDestination);

	/** Get SAP Destination	  */
	public String getSAPDestination();

    /** Column name SystemCode */
    public static final String COLUMNNAME_SystemCode = "SystemCode";

	/** Set System Code	  */
	public void setSystemCode (String SystemCode);

	/** Get System Code	  */
	public String getSystemCode();

    /** Column name TestConnection */
    public static final String COLUMNNAME_TestConnection = "TestConnection";

	/** Set Test Connection	  */
	public void setTestConnection (String TestConnection);

	/** Get Test Connection	  */
	public String getTestConnection();

    /** Column name Type */
    public static final String COLUMNNAME_Type = "Type";

	/** Set Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type);

	/** Get Type.
	  * Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType();

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
}
