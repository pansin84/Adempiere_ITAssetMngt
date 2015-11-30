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

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for DM_AssetModelNo
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_DM_AssetModelNo extends PO implements I_DM_AssetModelNo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150831L;

    /** Standard Constructor */
    public X_DM_AssetModelNo (Properties ctx, int DM_AssetModelNo_ID, String trxName)
    {
      super (ctx, DM_AssetModelNo_ID, trxName);
      /** if (DM_AssetModelNo_ID == 0)
        {
			setDM_AssetModelNo_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DM_AssetModelNo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DM_AssetModelNo[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_C_BPartner getAssetManufacturer() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getAssetManufacturer_ID(), get_TrxName());	}

	/** Set Manufacturer.
		@param AssetManufacturer_ID Manufacturer	  */
	public void setAssetManufacturer_ID (int AssetManufacturer_ID)
	{
		if (AssetManufacturer_ID < 1) 
			set_Value (COLUMNNAME_AssetManufacturer_ID, null);
		else 
			set_Value (COLUMNNAME_AssetManufacturer_ID, Integer.valueOf(AssetManufacturer_ID));
	}

	/** Get Manufacturer.
		@return Manufacturer	  */
	public int getAssetManufacturer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AssetManufacturer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Asset Model No Master ID.
		@param DM_AssetModelNo_ID Asset Model No Master ID	  */
	public void setDM_AssetModelNo_ID (int DM_AssetModelNo_ID)
	{
		if (DM_AssetModelNo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DM_AssetModelNo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_AssetModelNo_ID, Integer.valueOf(DM_AssetModelNo_ID));
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

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Category)MTable.get(getCtx(), org.compiere.model.I_M_Product_Category.Table_Name)
			.getPO(getM_Product_Category_ID(), get_TrxName());	}

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getM_Product_Category_ID()));
    }
}