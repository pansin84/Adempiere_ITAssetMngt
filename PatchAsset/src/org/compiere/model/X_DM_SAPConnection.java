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

/** Generated Model for DM_SAPConnection
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_DM_SAPConnection extends PO implements I_DM_SAPConnection, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150831L;

    /** Standard Constructor */
    public X_DM_SAPConnection (Properties ctx, int DM_SAPConnection_ID, String trxName)
    {
      super (ctx, DM_SAPConnection_ID, trxName);
      /** if (DM_SAPConnection_ID == 0)
        {
			setClientCode (null);
			setDM_SAPConnection_ID (0);
			setHost (null);
			setLanguageISO (null);
			setLoginUserName (null);
			setName (null);
			setPassword (null);
			setSystemCode (null);
        } */
    }

    /** Load Constructor */
    public X_DM_SAPConnection (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DM_SAPConnection[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** Set Client Code.
		@param ClientCode Client Code	  */
	public void setClientCode (String ClientCode)
	{
		set_Value (COLUMNNAME_ClientCode, ClientCode);
	}

	/** Get Client Code.
		@return Client Code	  */
	public String getClientCode () 
	{
		return (String)get_Value(COLUMNNAME_ClientCode);
	}

	/** Set SAP Connection Configuration ID.
		@param DM_SAPConnection_ID SAP Connection Configuration ID	  */
	public void setDM_SAPConnection_ID (int DM_SAPConnection_ID)
	{
		if (DM_SAPConnection_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DM_SAPConnection_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_SAPConnection_ID, Integer.valueOf(DM_SAPConnection_ID));
	}

	/** Get SAP Connection Configuration ID.
		@return SAP Connection Configuration ID	  */
	public int getDM_SAPConnection_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_SAPConnection_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Host.
		@param Host Host	  */
	public void setHost (String Host)
	{
		set_Value (COLUMNNAME_Host, Host);
	}

	/** Get Host.
		@return Host	  */
	public String getHost () 
	{
		return (String)get_Value(COLUMNNAME_Host);
	}

	/** Set Default.
		@param IsDefault 
		Default value
	  */
	public void setIsDefault (boolean IsDefault)
	{
		set_Value (COLUMNNAME_IsDefault, Boolean.valueOf(IsDefault));
	}

	/** Get Default.
		@return Default value
	  */
	public boolean isDefault () 
	{
		Object oo = get_Value(COLUMNNAME_IsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set ISO Language Code.
		@param LanguageISO 
		Lower-case two-letter ISO-3166 code - http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt 
	  */
	public void setLanguageISO (String LanguageISO)
	{
		set_Value (COLUMNNAME_LanguageISO, LanguageISO);
	}

	/** Get ISO Language Code.
		@return Lower-case two-letter ISO-3166 code - http://www.ics.uci.edu/pub/ietf/http/related/iso639.txt 
	  */
	public String getLanguageISO () 
	{
		return (String)get_Value(COLUMNNAME_LanguageISO);
	}

	/** Set Login User Name.
		@param LoginUserName Login User Name	  */
	public void setLoginUserName (String LoginUserName)
	{
		set_Value (COLUMNNAME_LoginUserName, LoginUserName);
	}

	/** Get Login User Name.
		@return Login User Name	  */
	public String getLoginUserName () 
	{
		return (String)get_Value(COLUMNNAME_LoginUserName);
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

	/** Set Password.
		@param Password 
		Password of any length (case sensitive)
	  */
	public void setPassword (String Password)
	{
		set_Value (COLUMNNAME_Password, Password);
	}

	/** Get Password.
		@return Password of any length (case sensitive)
	  */
	public String getPassword () 
	{
		return (String)get_Value(COLUMNNAME_Password);
	}

	/** Set SAP Destination.
		@param SAPDestination SAP Destination	  */
	public void setSAPDestination (String SAPDestination)
	{
		set_Value (COLUMNNAME_SAPDestination, SAPDestination);
	}

	/** Get SAP Destination.
		@return SAP Destination	  */
	public String getSAPDestination () 
	{
		return (String)get_Value(COLUMNNAME_SAPDestination);
	}

	/** Set System Code.
		@param SystemCode System Code	  */
	public void setSystemCode (String SystemCode)
	{
		set_Value (COLUMNNAME_SystemCode, SystemCode);
	}

	/** Get System Code.
		@return System Code	  */
	public String getSystemCode () 
	{
		return (String)get_Value(COLUMNNAME_SystemCode);
	}

	/** Set Test Connection.
		@param TestConnection Test Connection	  */
	public void setTestConnection (String TestConnection)
	{
		set_Value (COLUMNNAME_TestConnection, TestConnection);
	}

	/** Get Test Connection.
		@return Test Connection	  */
	public String getTestConnection () 
	{
		return (String)get_Value(COLUMNNAME_TestConnection);
	}

	/** Type AD_Reference_ID=101 */
	public static final int TYPE_AD_Reference_ID=101;
	/** SQL = S */
	public static final String TYPE_SQL = "S";
	/** Java Language = J */
	public static final String TYPE_JavaLanguage = "J";
	/** Java Script = E */
	public static final String TYPE_JavaScript = "E";
	/** Set Type.
		@param Type 
		Type of Validation (SQL, Java Script, Java Language)
	  */
	public void setType (String Type)
	{

		set_Value (COLUMNNAME_Type, Type);
	}

	/** Get Type.
		@return Type of Validation (SQL, Java Script, Java Language)
	  */
	public String getType () 
	{
		return (String)get_Value(COLUMNNAME_Type);
	}
}