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

/** Generated Interface for DM_AssetTransaction
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_DM_AssetTransaction 
{

    /** TableName=DM_AssetTransaction */
    public static final String Table_Name = "DM_AssetTransaction";

    /** AD_Table_ID=1000018 */
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

    /** Column name DateIssued */
    public static final String COLUMNNAME_DateIssued = "DateIssued";

	/** Set Date Issued	  */
	public void setDateIssued (Timestamp DateIssued);

	/** Get Date Issued	  */
	public Timestamp getDateIssued();

    /** Column name DateReturned */
    public static final String COLUMNNAME_DateReturned = "DateReturned";

	/** Set Date Returned	  */
	public void setDateReturned (Timestamp DateReturned);

	/** Get Date Returned	  */
	public Timestamp getDateReturned();

    /** Column name DM_AssetTransaction_ID */
    public static final String COLUMNNAME_DM_AssetTransaction_ID = "DM_AssetTransaction_ID";

	/** Set Asset Transaction ID	  */
	public void setDM_AssetTransaction_ID (int DM_AssetTransaction_ID);

	/** Get Asset Transaction ID	  */
	public int getDM_AssetTransaction_ID();

    /** Column name DM_DMSEmployee_ID */
    public static final String COLUMNNAME_DM_DMSEmployee_ID = "DM_DMSEmployee_ID";

	/** Set DMS Employee ID	  */
	public void setDM_DMSEmployee_ID (int DM_DMSEmployee_ID);

	/** Get DMS Employee ID	  */
	public int getDM_DMSEmployee_ID();

	public I_DM_DMSEmployee getDM_DMSEmployee() throws RuntimeException;

    /** Column name DM_StorageDetails_ID */
    public static final String COLUMNNAME_DM_StorageDetails_ID = "DM_StorageDetails_ID";

	/** Set Storage Details ID	  */
	public void setDM_StorageDetails_ID (int DM_StorageDetails_ID);

	/** Get Storage Details ID	  */
	public int getDM_StorageDetails_ID();

	public I_DM_StorageDetails getDM_StorageDetails() throws RuntimeException;

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

    /** Column name IsReturned */
    public static final String COLUMNNAME_IsReturned = "IsReturned";

	/** Set Returned	  */
	public void setIsReturned (boolean IsReturned);

	/** Get Returned	  */
	public boolean isReturned();

    /** Column name IsReturnedToVendor */
    public static final String COLUMNNAME_IsReturnedToVendor = "IsReturnedToVendor";

	/** Set Returned To Vendor	  */
	public void setIsReturnedToVendor (boolean IsReturnedToVendor);

	/** Get Returned To Vendor	  */
	public boolean isReturnedToVendor();

    /** Column name IssuedBy_ID */
    public static final String COLUMNNAME_IssuedBy_ID = "IssuedBy_ID";

	/** Set Issued By	  */
	public void setIssuedBy_ID (int IssuedBy_ID);

	/** Get Issued By	  */
	public int getIssuedBy_ID();

	public org.compiere.model.I_AD_User getIssuedBy() throws RuntimeException;

    /** Column name M_Storage_ID */
    public static final String COLUMNNAME_M_Storage_ID = "M_Storage_ID";

	/** Set Storage ID	  */
	public void setM_Storage_ID (int M_Storage_ID);

	/** Get Storage ID	  */
	public int getM_Storage_ID();

	public org.compiere.model.I_M_Storage getM_Storage() throws RuntimeException;

    /** Column name QtyConsumed */
    public static final String COLUMNNAME_QtyConsumed = "QtyConsumed";

	/** Set Consumed Quantity	  */
	public void setQtyConsumed (BigDecimal QtyConsumed);

	/** Get Consumed Quantity	  */
	public BigDecimal getQtyConsumed();

    /** Column name QtyIssued */
    public static final String COLUMNNAME_QtyIssued = "QtyIssued";

	/** Set Issued Quantity	  */
	public void setQtyIssued (BigDecimal QtyIssued);

	/** Get Issued Quantity	  */
	public BigDecimal getQtyIssued();

    /** Column name QtyReturned */
    public static final String COLUMNNAME_QtyReturned = "QtyReturned";

	/** Set Returned Quantity	  */
	public void setQtyReturned (BigDecimal QtyReturned);

	/** Get Returned Quantity	  */
	public BigDecimal getQtyReturned();

    /** Column name Remarks */
    public static final String COLUMNNAME_Remarks = "Remarks";

	/** Set Remarks.
	  * Remarks
	  */
	public void setRemarks (String Remarks);

	/** Get Remarks.
	  * Remarks
	  */
	public String getRemarks();

    /** Column name ReturnedBy_ID */
    public static final String COLUMNNAME_ReturnedBy_ID = "ReturnedBy_ID";

	/** Set Returned By	  */
	public void setReturnedBy_ID (int ReturnedBy_ID);

	/** Get Returned By	  */
	public int getReturnedBy_ID();

	public org.compiere.model.I_AD_User getReturnedBy() throws RuntimeException;

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

    /** Column name VendorRetQty */
    public static final String COLUMNNAME_VendorRetQty = "VendorRetQty";

	/** Set Vendor Returned Qty	  */
	public void setVendorRetQty (BigDecimal VendorRetQty);

	/** Get Vendor Returned Qty	  */
	public BigDecimal getVendorRetQty();
}
