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

/** Generated Model for DM_Asset_Licence
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_DM_Asset_Licence extends PO implements I_DM_Asset_Licence, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150831L;

    /** Standard Constructor */
    public X_DM_Asset_Licence (Properties ctx, int DM_Asset_Licence_ID, String trxName)
    {
      super (ctx, DM_Asset_Licence_ID, trxName);
      /** if (DM_Asset_Licence_ID == 0)
        {
			setDM_Asset_Licence_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DM_Asset_Licence (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DM_Asset_Licence[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Issuing Agency.
		@param A_Issuing_Agency Issuing Agency	  */
	public void setA_Issuing_Agency (String A_Issuing_Agency)
	{
		set_Value (COLUMNNAME_A_Issuing_Agency, A_Issuing_Agency);
	}

	/** Get Issuing Agency.
		@return Issuing Agency	  */
	public String getA_Issuing_Agency () 
	{
		return (String)get_Value(COLUMNNAME_A_Issuing_Agency);
	}

	/** Set Asset License Fee.
		@param A_License_Fee Asset License Fee	  */
	public void setA_License_Fee (BigDecimal A_License_Fee)
	{
		set_Value (COLUMNNAME_A_License_Fee, A_License_Fee);
	}

	/** Get Asset License Fee.
		@return Asset License Fee	  */
	public BigDecimal getA_License_Fee () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_A_License_Fee);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Asset License No.
		@param A_License_No Asset License No	  */
	public void setA_License_No (String A_License_No)
	{
		set_Value (COLUMNNAME_A_License_No, A_License_No);
	}

	/** Get Asset License No.
		@return Asset License No	  */
	public String getA_License_No () 
	{
		return (String)get_Value(COLUMNNAME_A_License_No);
	}

	/** Set Asset Renewal Date.
		@param A_Renewal_Date Asset Renewal Date	  */
	public void setA_Renewal_Date (Timestamp A_Renewal_Date)
	{
		set_Value (COLUMNNAME_A_Renewal_Date, A_Renewal_Date);
	}

	/** Get Asset Renewal Date.
		@return Asset Renewal Date	  */
	public Timestamp getA_Renewal_Date () 
	{
		return (Timestamp)get_Value(COLUMNNAME_A_Renewal_Date);
	}

	/** Set Account State.
		@param A_State 
		State of the Credit Card or Account holder
	  */
	public void setA_State (String A_State)
	{
		set_Value (COLUMNNAME_A_State, A_State);
	}

	/** Get Account State.
		@return State of the Credit Card or Account holder
	  */
	public String getA_State () 
	{
		return (String)get_Value(COLUMNNAME_A_State);
	}

	/** Set Licence Info ID.
		@param DM_Asset_Licence_ID Licence Info ID	  */
	public void setDM_Asset_Licence_ID (int DM_Asset_Licence_ID)
	{
		if (DM_Asset_Licence_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DM_Asset_Licence_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_Asset_Licence_ID, Integer.valueOf(DM_Asset_Licence_ID));
	}

	/** Get Licence Info ID.
		@return Licence Info ID	  */
	public int getDM_Asset_Licence_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_Asset_Licence_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DM_AssetDetails getDM_AssetDetails() throws RuntimeException
    {
		return (I_DM_AssetDetails)MTable.get(getCtx(), I_DM_AssetDetails.Table_Name)
			.getPO(getDM_AssetDetails_ID(), get_TrxName());	}

	/** Set Asset Details ID.
		@param DM_AssetDetails_ID Asset Details ID	  */
	public void setDM_AssetDetails_ID (int DM_AssetDetails_ID)
	{
		if (DM_AssetDetails_ID < 1) 
			set_Value (COLUMNNAME_DM_AssetDetails_ID, null);
		else 
			set_Value (COLUMNNAME_DM_AssetDetails_ID, Integer.valueOf(DM_AssetDetails_ID));
	}

	/** Get Asset Details ID.
		@return Asset Details ID	  */
	public int getDM_AssetDetails_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_AssetDetails_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DM_AssetModelDetails getDM_AssetModelDetails() throws RuntimeException
    {
		return (I_DM_AssetModelDetails)MTable.get(getCtx(), I_DM_AssetModelDetails.Table_Name)
			.getPO(getDM_AssetModelDetails_ID(), get_TrxName());	}

	/** Set Model No Details ID.
		@param DM_AssetModelDetails_ID Model No Details ID	  */
	public void setDM_AssetModelDetails_ID (int DM_AssetModelDetails_ID)
	{
		if (DM_AssetModelDetails_ID < 1) 
			set_Value (COLUMNNAME_DM_AssetModelDetails_ID, null);
		else 
			set_Value (COLUMNNAME_DM_AssetModelDetails_ID, Integer.valueOf(DM_AssetModelDetails_ID));
	}

	/** Get Model No Details ID.
		@return Model No Details ID	  */
	public int getDM_AssetModelDetails_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_AssetModelDetails_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DM_AssetModelNo getDM_AssetModelNo() throws RuntimeException
    {
		return (I_DM_AssetModelNo)MTable.get(getCtx(), I_DM_AssetModelNo.Table_Name)
			.getPO(getDM_AssetModelNo_ID(), get_TrxName());	}

	/** Set Asset Model No Master ID.
		@param DM_AssetModelNo_ID Asset Model No Master ID	  */
	public void setDM_AssetModelNo_ID (int DM_AssetModelNo_ID)
	{
		if (DM_AssetModelNo_ID < 1) 
			set_Value (COLUMNNAME_DM_AssetModelNo_ID, null);
		else 
			set_Value (COLUMNNAME_DM_AssetModelNo_ID, Integer.valueOf(DM_AssetModelNo_ID));
	}

	/** Get Asset Model No Master ID.
		@return Asset Model No Master ID	  */
	public int getDM_AssetModelNo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_AssetModelNo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_ValueNoCheck (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Description.
		@param Text Description	  */
	public void setText (String Text)
	{
		set_Value (COLUMNNAME_Text, Text);
	}

	/** Get Description.
		@return Description	  */
	public String getText () 
	{
		return (String)get_Value(COLUMNNAME_Text);
	}
}