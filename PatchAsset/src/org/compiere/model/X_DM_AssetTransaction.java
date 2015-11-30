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

/** Generated Model for DM_AssetTransaction
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_DM_AssetTransaction extends PO implements I_DM_AssetTransaction, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150831L;

    /** Standard Constructor */
    public X_DM_AssetTransaction (Properties ctx, int DM_AssetTransaction_ID, String trxName)
    {
      super (ctx, DM_AssetTransaction_ID, trxName);
      /** if (DM_AssetTransaction_ID == 0)
        {
			setDM_AssetTransaction_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DM_AssetTransaction (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DM_AssetTransaction[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Date Issued.
		@param DateIssued Date Issued	  */
	public void setDateIssued (Timestamp DateIssued)
	{
		set_Value (COLUMNNAME_DateIssued, DateIssued);
	}

	/** Get Date Issued.
		@return Date Issued	  */
	public Timestamp getDateIssued () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateIssued);
	}

	/** Set Date Returned.
		@param DateReturned Date Returned	  */
	public void setDateReturned (Timestamp DateReturned)
	{
		set_Value (COLUMNNAME_DateReturned, DateReturned);
	}

	/** Get Date Returned.
		@return Date Returned	  */
	public Timestamp getDateReturned () 
	{
		return (Timestamp)get_Value(COLUMNNAME_DateReturned);
	}

	/** Set Asset Transaction ID.
		@param DM_AssetTransaction_ID Asset Transaction ID	  */
	public void setDM_AssetTransaction_ID (int DM_AssetTransaction_ID)
	{
		if (DM_AssetTransaction_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DM_AssetTransaction_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_AssetTransaction_ID, Integer.valueOf(DM_AssetTransaction_ID));
	}

	/** Get Asset Transaction ID.
		@return Asset Transaction ID	  */
	public int getDM_AssetTransaction_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_AssetTransaction_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DM_DMSEmployee getDM_DMSEmployee() throws RuntimeException
    {
		return (I_DM_DMSEmployee)MTable.get(getCtx(), I_DM_DMSEmployee.Table_Name)
			.getPO(getDM_DMSEmployee_ID(), get_TrxName());	}

	/** Set DMS Employee ID.
		@param DM_DMSEmployee_ID DMS Employee ID	  */
	public void setDM_DMSEmployee_ID (int DM_DMSEmployee_ID)
	{
		if (DM_DMSEmployee_ID < 1) 
			set_Value (COLUMNNAME_DM_DMSEmployee_ID, null);
		else 
			set_Value (COLUMNNAME_DM_DMSEmployee_ID, Integer.valueOf(DM_DMSEmployee_ID));
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

	public I_DM_StorageDetails getDM_StorageDetails() throws RuntimeException
    {
		return (I_DM_StorageDetails)MTable.get(getCtx(), I_DM_StorageDetails.Table_Name)
			.getPO(getDM_StorageDetails_ID(), get_TrxName());	}

	/** Set Storage Details ID.
		@param DM_StorageDetails_ID Storage Details ID	  */
	public void setDM_StorageDetails_ID (int DM_StorageDetails_ID)
	{
		if (DM_StorageDetails_ID < 1) 
			set_Value (COLUMNNAME_DM_StorageDetails_ID, null);
		else 
			set_Value (COLUMNNAME_DM_StorageDetails_ID, Integer.valueOf(DM_StorageDetails_ID));
	}

	/** Get Storage Details ID.
		@return Storage Details ID	  */
	public int getDM_StorageDetails_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_StorageDetails_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Returned.
		@param IsReturned Returned	  */
	public void setIsReturned (boolean IsReturned)
	{
		set_Value (COLUMNNAME_IsReturned, Boolean.valueOf(IsReturned));
	}

	/** Get Returned.
		@return Returned	  */
	public boolean isReturned () 
	{
		Object oo = get_Value(COLUMNNAME_IsReturned);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Returned To Vendor.
		@param IsReturnedToVendor Returned To Vendor	  */
	public void setIsReturnedToVendor (boolean IsReturnedToVendor)
	{
		set_Value (COLUMNNAME_IsReturnedToVendor, Boolean.valueOf(IsReturnedToVendor));
	}

	/** Get Returned To Vendor.
		@return Returned To Vendor	  */
	public boolean isReturnedToVendor () 
	{
		Object oo = get_Value(COLUMNNAME_IsReturnedToVendor);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_AD_User getIssuedBy() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getIssuedBy_ID(), get_TrxName());	}

	/** Set Issued By.
		@param IssuedBy_ID Issued By	  */
	public void setIssuedBy_ID (int IssuedBy_ID)
	{
		if (IssuedBy_ID < 1) 
			set_Value (COLUMNNAME_IssuedBy_ID, null);
		else 
			set_Value (COLUMNNAME_IssuedBy_ID, Integer.valueOf(IssuedBy_ID));
	}

	/** Get Issued By.
		@return Issued By	  */
	public int getIssuedBy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_IssuedBy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Storage getM_Storage() throws RuntimeException
    {
		return (org.compiere.model.I_M_Storage)MTable.get(getCtx(), org.compiere.model.I_M_Storage.Table_Name)
			.getPO(getM_Storage_ID(), get_TrxName());	}

	/** Set Storage ID.
		@param M_Storage_ID Storage ID	  */
	public void setM_Storage_ID (int M_Storage_ID)
	{
		if (M_Storage_ID < 1) 
			set_Value (COLUMNNAME_M_Storage_ID, null);
		else 
			set_Value (COLUMNNAME_M_Storage_ID, Integer.valueOf(M_Storage_ID));
	}

	/** Get Storage ID.
		@return Storage ID	  */
	public int getM_Storage_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Storage_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Consumed Quantity.
		@param QtyConsumed Consumed Quantity	  */
	public void setQtyConsumed (BigDecimal QtyConsumed)
	{
		set_Value (COLUMNNAME_QtyConsumed, QtyConsumed);
	}

	/** Get Consumed Quantity.
		@return Consumed Quantity	  */
	public BigDecimal getQtyConsumed () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyConsumed);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Issued Quantity.
		@param QtyIssued Issued Quantity	  */
	public void setQtyIssued (BigDecimal QtyIssued)
	{
		set_Value (COLUMNNAME_QtyIssued, QtyIssued);
	}

	/** Get Issued Quantity.
		@return Issued Quantity	  */
	public BigDecimal getQtyIssued () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyIssued);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Returned Quantity.
		@param QtyReturned Returned Quantity	  */
	public void setQtyReturned (BigDecimal QtyReturned)
	{
		set_Value (COLUMNNAME_QtyReturned, QtyReturned);
	}

	/** Get Returned Quantity.
		@return Returned Quantity	  */
	public BigDecimal getQtyReturned () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyReturned);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Remarks.
		@param Remarks 
		Remarks
	  */
	public void setRemarks (String Remarks)
	{
		set_Value (COLUMNNAME_Remarks, Remarks);
	}

	/** Get Remarks.
		@return Remarks
	  */
	public String getRemarks () 
	{
		return (String)get_Value(COLUMNNAME_Remarks);
	}

	public org.compiere.model.I_AD_User getReturnedBy() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getReturnedBy_ID(), get_TrxName());	}

	/** Set Returned By.
		@param ReturnedBy_ID Returned By	  */
	public void setReturnedBy_ID (int ReturnedBy_ID)
	{
		if (ReturnedBy_ID < 1) 
			set_Value (COLUMNNAME_ReturnedBy_ID, null);
		else 
			set_Value (COLUMNNAME_ReturnedBy_ID, Integer.valueOf(ReturnedBy_ID));
	}

	/** Get Returned By.
		@return Returned By	  */
	public int getReturnedBy_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ReturnedBy_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Vendor Returned Qty.
		@param VendorRetQty Vendor Returned Qty	  */
	public void setVendorRetQty (BigDecimal VendorRetQty)
	{
		set_Value (COLUMNNAME_VendorRetQty, VendorRetQty);
	}

	/** Get Vendor Returned Qty.
		@return Vendor Returned Qty	  */
	public BigDecimal getVendorRetQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_VendorRetQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}