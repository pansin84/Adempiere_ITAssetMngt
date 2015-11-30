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

/** Generated Interface for DM_Asset_Licence
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_DM_Asset_Licence 
{

    /** TableName=DM_Asset_Licence */
    public static final String Table_Name = "DM_Asset_Licence";

    /** AD_Table_ID=1000015 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_Issuing_Agency */
    public static final String COLUMNNAME_A_Issuing_Agency = "A_Issuing_Agency";

	/** Set Issuing Agency	  */
	public void setA_Issuing_Agency (String A_Issuing_Agency);

	/** Get Issuing Agency	  */
	public String getA_Issuing_Agency();

    /** Column name A_License_Fee */
    public static final String COLUMNNAME_A_License_Fee = "A_License_Fee";

	/** Set Asset License Fee	  */
	public void setA_License_Fee (BigDecimal A_License_Fee);

	/** Get Asset License Fee	  */
	public BigDecimal getA_License_Fee();

    /** Column name A_License_No */
    public static final String COLUMNNAME_A_License_No = "A_License_No";

	/** Set Asset License No	  */
	public void setA_License_No (String A_License_No);

	/** Get Asset License No	  */
	public String getA_License_No();

    /** Column name A_Renewal_Date */
    public static final String COLUMNNAME_A_Renewal_Date = "A_Renewal_Date";

	/** Set Asset Renewal Date	  */
	public void setA_Renewal_Date (Timestamp A_Renewal_Date);

	/** Get Asset Renewal Date	  */
	public Timestamp getA_Renewal_Date();

    /** Column name A_State */
    public static final String COLUMNNAME_A_State = "A_State";

	/** Set Account State.
	  * State of the Credit Card or Account holder
	  */
	public void setA_State (String A_State);

	/** Get Account State.
	  * State of the Credit Card or Account holder
	  */
	public String getA_State();

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

    /** Column name DM_Asset_Licence_ID */
    public static final String COLUMNNAME_DM_Asset_Licence_ID = "DM_Asset_Licence_ID";

	/** Set Licence Info ID	  */
	public void setDM_Asset_Licence_ID (int DM_Asset_Licence_ID);

	/** Get Licence Info ID	  */
	public int getDM_Asset_Licence_ID();

    /** Column name DM_AssetDetails_ID */
    public static final String COLUMNNAME_DM_AssetDetails_ID = "DM_AssetDetails_ID";

	/** Set Asset Details ID	  */
	public void setDM_AssetDetails_ID (int DM_AssetDetails_ID);

	/** Get Asset Details ID	  */
	public int getDM_AssetDetails_ID();

	public I_DM_AssetDetails getDM_AssetDetails() throws RuntimeException;

    /** Column name DM_AssetModelDetails_ID */
    public static final String COLUMNNAME_DM_AssetModelDetails_ID = "DM_AssetModelDetails_ID";

	/** Set Model No Details ID	  */
	public void setDM_AssetModelDetails_ID (int DM_AssetModelDetails_ID);

	/** Get Model No Details ID	  */
	public int getDM_AssetModelDetails_ID();

	public I_DM_AssetModelDetails getDM_AssetModelDetails() throws RuntimeException;

    /** Column name DM_AssetModelNo_ID */
    public static final String COLUMNNAME_DM_AssetModelNo_ID = "DM_AssetModelNo_ID";

	/** Set Asset Model No Master ID	  */
	public void setDM_AssetModelNo_ID (int DM_AssetModelNo_ID);

	/** Get Asset Model No Master ID	  */
	public int getDM_AssetModelNo_ID();

	public I_DM_AssetModelNo getDM_AssetModelNo() throws RuntimeException;

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

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Text */
    public static final String COLUMNNAME_Text = "Text";

	/** Set Description	  */
	public void setText (String Text);

	/** Get Description	  */
	public String getText();

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
