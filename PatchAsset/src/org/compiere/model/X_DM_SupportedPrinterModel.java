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

/** Generated Model for DM_SupportedPrinterModel
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_DM_SupportedPrinterModel extends PO implements I_DM_SupportedPrinterModel, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150831L;

    /** Standard Constructor */
    public X_DM_SupportedPrinterModel (Properties ctx, int DM_SupportedPrinterModel_ID, String trxName)
    {
      super (ctx, DM_SupportedPrinterModel_ID, trxName);
      /** if (DM_SupportedPrinterModel_ID == 0)
        {
			setDM_SupportedPrinterModel_ID (0);
			setSupportedPrinter_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DM_SupportedPrinterModel (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DM_SupportedPrinterModel[")
        .append(get_ID()).append("]");
      return sb.toString();
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

	/** Set Supported printer ID.
		@param DM_SupportedPrinterModel_ID Supported printer ID	  */
	public void setDM_SupportedPrinterModel_ID (int DM_SupportedPrinterModel_ID)
	{
		if (DM_SupportedPrinterModel_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DM_SupportedPrinterModel_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_SupportedPrinterModel_ID, Integer.valueOf(DM_SupportedPrinterModel_ID));
	}

	/** Get Supported printer ID.
		@return Supported printer ID	  */
	public int getDM_SupportedPrinterModel_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_SupportedPrinterModel_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_DM_AssetModelDetails getSupportedPrinter() throws RuntimeException
    {
		return (I_DM_AssetModelDetails)MTable.get(getCtx(), I_DM_AssetModelDetails.Table_Name)
			.getPO(getSupportedPrinter_ID(), get_TrxName());	}

	/** Set Supported Printer.
		@param SupportedPrinter_ID Supported Printer	  */
	public void setSupportedPrinter_ID (int SupportedPrinter_ID)
	{
		if (SupportedPrinter_ID < 1) 
			set_Value (COLUMNNAME_SupportedPrinter_ID, null);
		else 
			set_Value (COLUMNNAME_SupportedPrinter_ID, Integer.valueOf(SupportedPrinter_ID));
	}

	/** Get Supported Printer.
		@return Supported Printer	  */
	public int getSupportedPrinter_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SupportedPrinter_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}