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

/** Generated Model for DM_EmployeeLocation
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_DM_EmployeeLocation extends PO implements I_DM_EmployeeLocation, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150831L;

    /** Standard Constructor */
    public X_DM_EmployeeLocation (Properties ctx, int DM_EmployeeLocation_ID, String trxName)
    {
      super (ctx, DM_EmployeeLocation_ID, trxName);
      /** if (DM_EmployeeLocation_ID == 0)
        {
			setDM_EmployeeLocation_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_DM_EmployeeLocation (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DM_EmployeeLocation[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Employee Location ID.
		@param DM_EmployeeLocation_ID Employee Location ID	  */
	public void setDM_EmployeeLocation_ID (int DM_EmployeeLocation_ID)
	{
		if (DM_EmployeeLocation_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DM_EmployeeLocation_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_EmployeeLocation_ID, Integer.valueOf(DM_EmployeeLocation_ID));
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

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }
}