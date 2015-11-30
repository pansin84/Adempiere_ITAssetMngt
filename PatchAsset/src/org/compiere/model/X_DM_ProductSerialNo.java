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

/** Generated Model for DM_ProductSerialNo
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_DM_ProductSerialNo extends PO implements I_DM_ProductSerialNo, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150831L;

    /** Standard Constructor */
    public X_DM_ProductSerialNo (Properties ctx, int DM_ProductSerialNo_ID, String trxName)
    {
      super (ctx, DM_ProductSerialNo_ID, trxName);
      /** if (DM_ProductSerialNo_ID == 0)
        {
			setDM_ProductSerialNo_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DM_ProductSerialNo (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DM_ProductSerialNo[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Device Serial No.
		@param DeviceSerialNo Device Serial No	  */
	public void setDeviceSerialNo (String DeviceSerialNo)
	{
		set_Value (COLUMNNAME_DeviceSerialNo, DeviceSerialNo);
	}

	/** Get Device Serial No.
		@return Device Serial No	  */
	public String getDeviceSerialNo () 
	{
		return (String)get_Value(COLUMNNAME_DeviceSerialNo);
	}

	/** Set Product Serial No ID.
		@param DM_ProductSerialNo_ID Product Serial No ID	  */
	public void setDM_ProductSerialNo_ID (int DM_ProductSerialNo_ID)
	{
		if (DM_ProductSerialNo_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DM_ProductSerialNo_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_ProductSerialNo_ID, Integer.valueOf(DM_ProductSerialNo_ID));
	}

	/** Get Product Serial No ID.
		@return Product Serial No ID	  */
	public int getDM_ProductSerialNo_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_ProductSerialNo_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product)MTable.get(getCtx(), org.compiere.model.I_M_Product.Table_Name)
			.getPO(getM_Product_ID(), get_TrxName());	}

	/** Set Product.
		@param M_Product_ID 
		Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID)
	{
		if (M_Product_ID < 1) 
			set_Value (COLUMNNAME_M_Product_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_ID, Integer.valueOf(M_Product_ID));
	}

	/** Get Product.
		@return Product, Service, Item
	  */
	public int getM_Product_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}