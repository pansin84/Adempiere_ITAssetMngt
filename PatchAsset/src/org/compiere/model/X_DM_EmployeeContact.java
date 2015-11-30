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

/** Generated Model for DM_EmployeeContact
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_DM_EmployeeContact extends PO implements I_DM_EmployeeContact, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150831L;

    /** Standard Constructor */
    public X_DM_EmployeeContact (Properties ctx, int DM_EmployeeContact_ID, String trxName)
    {
      super (ctx, DM_EmployeeContact_ID, trxName);
      /** if (DM_EmployeeContact_ID == 0)
        {
			setC_Location_ID (0);
			setDM_EmployeeContact_ID (0);
			setName (null);
// .
        } */
    }

    /** Load Constructor */
    public X_DM_EmployeeContact (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DM_EmployeeContact[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public I_C_Location getC_Location() throws RuntimeException
    {
		return (I_C_Location)MTable.get(getCtx(), I_C_Location.Table_Name)
			.getPO(getC_Location_ID(), get_TrxName());	}

	/** Set Address.
		@param C_Location_ID 
		Location or Address
	  */
	public void setC_Location_ID (int C_Location_ID)
	{
		if (C_Location_ID < 1) 
			set_Value (COLUMNNAME_C_Location_ID, null);
		else 
			set_Value (COLUMNNAME_C_Location_ID, Integer.valueOf(C_Location_ID));
	}

	/** Get Address.
		@return Location or Address
	  */
	public int getC_Location_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Location_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Contact Person.
		@param ContactPerson 
		Contact Person Defines the contact person for this location
	  */
	public void setContactPerson (String ContactPerson)
	{
		set_Value (COLUMNNAME_ContactPerson, ContactPerson);
	}

	/** Get Contact Person.
		@return Contact Person Defines the contact person for this location
	  */
	public String getContactPerson () 
	{
		return (String)get_Value(COLUMNNAME_ContactPerson);
	}

	/** ContactType AD_Reference_ID=53619 */
	public static final int CONTACTTYPE_AD_Reference_ID=53619;
	/** Emergency = Emergency */
	public static final String CONTACTTYPE_Emergency = "Emergency";
	/** Primary = Primary */
	public static final String CONTACTTYPE_Primary = "Primary";
	/** Set Contact Type.
		@param ContactType 
		Contact Type defines the type of contact for this location
	  */
	public void setContactType (String ContactType)
	{

		set_Value (COLUMNNAME_ContactType, ContactType);
	}

	/** Get Contact Type.
		@return Contact Type defines the type of contact for this location
	  */
	public String getContactType () 
	{
		return (String)get_Value(COLUMNNAME_ContactType);
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
			set_ValueNoCheck (COLUMNNAME_DM_DMSEmployee_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_DMSEmployee_ID, Integer.valueOf(DM_DMSEmployee_ID));
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

	/** Set Contact Info ID.
		@param DM_EmployeeContact_ID Contact Info ID	  */
	public void setDM_EmployeeContact_ID (int DM_EmployeeContact_ID)
	{
		if (DM_EmployeeContact_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DM_EmployeeContact_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_EmployeeContact_ID, Integer.valueOf(DM_EmployeeContact_ID));
	}

	/** Get Contact Info ID.
		@return Contact Info ID	  */
	public int getDM_EmployeeContact_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_EmployeeContact_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set EMail Address.
		@param EMail 
		Electronic Mail Address
	  */
	public void setEMail (String EMail)
	{
		set_Value (COLUMNNAME_EMail, EMail);
	}

	/** Get EMail Address.
		@return Electronic Mail Address
	  */
	public String getEMail () 
	{
		return (String)get_Value(COLUMNNAME_EMail);
	}

	/** Set Fax.
		@param Fax 
		Facsimile number
	  */
	public void setFax (String Fax)
	{
		set_Value (COLUMNNAME_Fax, Fax);
	}

	/** Get Fax.
		@return Facsimile number
	  */
	public String getFax () 
	{
		return (String)get_Value(COLUMNNAME_Fax);
	}

	/** Set ISDN.
		@param ISDN 
		ISDN or modem line
	  */
	public void setISDN (String ISDN)
	{
		set_Value (COLUMNNAME_ISDN, ISDN);
	}

	/** Get ISDN.
		@return ISDN or modem line
	  */
	public String getISDN () 
	{
		return (String)get_Value(COLUMNNAME_ISDN);
	}

	/** Set Mobile Phone.
		@param MobilePhone 
		Identifies an alternate telephone mobile number.
	  */
	public void setMobilePhone (String MobilePhone)
	{
		set_Value (COLUMNNAME_MobilePhone, MobilePhone);
	}

	/** Get Mobile Phone.
		@return Identifies an alternate telephone mobile number.
	  */
	public String getMobilePhone () 
	{
		return (String)get_Value(COLUMNNAME_MobilePhone);
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

	/** Set Phone.
		@param Phone 
		Identifies a telephone number
	  */
	public void setPhone (String Phone)
	{
		set_Value (COLUMNNAME_Phone, Phone);
	}

	/** Get Phone.
		@return Identifies a telephone number
	  */
	public String getPhone () 
	{
		return (String)get_Value(COLUMNNAME_Phone);
	}

	/** Set 2nd Phone.
		@param Phone2 
		Identifies an alternate telephone number.
	  */
	public void setPhone2 (String Phone2)
	{
		set_Value (COLUMNNAME_Phone2, Phone2);
	}

	/** Get 2nd Phone.
		@return Identifies an alternate telephone number.
	  */
	public String getPhone2 () 
	{
		return (String)get_Value(COLUMNNAME_Phone2);
	}
}