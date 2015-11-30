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
import java.sql.Timestamp;
import java.util.Properties;
import org.compiere.util.KeyNamePair;

/** Generated Model for DM_AssetDetails
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_DM_AssetDetails extends PO implements I_DM_AssetDetails, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150831L;

    /** Standard Constructor */
    public X_DM_AssetDetails (Properties ctx, int DM_AssetDetails_ID, String trxName)
    {
      super (ctx, DM_AssetDetails_ID, trxName);
      /** if (DM_AssetDetails_ID == 0)
        {
			setDM_AssetDetails_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DM_AssetDetails (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DM_AssetDetails[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	/** A_Asset_Status AD_Reference_ID=53359 */
	public static final int A_ASSET_STATUS_AD_Reference_ID=53359;
	/** Activated = AC */
	public static final String A_ASSET_STATUS_Activated = "AC";
	/** Disposed = DI */
	public static final String A_ASSET_STATUS_Disposed = "DI";
	/** Depreciated = DP */
	public static final String A_ASSET_STATUS_Depreciated = "DP";
	/** New = NW */
	public static final String A_ASSET_STATUS_New = "NW";
	/** Preservation = PR */
	public static final String A_ASSET_STATUS_Preservation = "PR";
	/** Retired = RE */
	public static final String A_ASSET_STATUS_Retired = "RE";
	/** Sold = SO */
	public static final String A_ASSET_STATUS_Sold = "SO";
	/** Set Asset Status.
		@param A_Asset_Status Asset Status	  */
	public void setA_Asset_Status (String A_Asset_Status)
	{

		set_Value (COLUMNNAME_A_Asset_Status, A_Asset_Status);
	}

	/** Get Asset Status.
		@return Asset Status	  */
	public String getA_Asset_Status () 
	{
		return (String)get_Value(COLUMNNAME_A_Asset_Status);
	}

	/** Set Asset Code.
		@param AssetCode Asset Code	  */
	public void setAssetCode (String AssetCode)
	{
		set_Value (COLUMNNAME_AssetCode, AssetCode);
	}

	/** Get Asset Code.
		@return Asset Code	  */
	public String getAssetCode () 
	{
		return (String)get_Value(COLUMNNAME_AssetCode);
	}

	/** Set In Service Date.
		@param AssetServiceDate 
		Date when Asset was put into service
	  */
	public void setAssetServiceDate (Timestamp AssetServiceDate)
	{
		set_Value (COLUMNNAME_AssetServiceDate, AssetServiceDate);
	}

	/** Get In Service Date.
		@return Date when Asset was put into service
	  */
	public Timestamp getAssetServiceDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_AssetServiceDate);
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

	/** Set Asset Details ID.
		@param DM_AssetDetails_ID Asset Details ID	  */
	public void setDM_AssetDetails_ID (int DM_AssetDetails_ID)
	{
		if (DM_AssetDetails_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DM_AssetDetails_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_AssetDetails_ID, Integer.valueOf(DM_AssetDetails_ID));
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

	/** Set IP Address.
		@param IP_Address 
		Defines the IP address to transfer data to
	  */
	public void setIP_Address (String IP_Address)
	{
		set_Value (COLUMNNAME_IP_Address, IP_Address);
	}

	/** Get IP Address.
		@return Defines the IP address to transfer data to
	  */
	public String getIP_Address () 
	{
		return (String)get_Value(COLUMNNAME_IP_Address);
	}

	/** Set Last Maintenance.
		@param LastMaintenanceDate 
		Last Maintenance Date
	  */
	public void setLastMaintenanceDate (Timestamp LastMaintenanceDate)
	{
		set_Value (COLUMNNAME_LastMaintenanceDate, LastMaintenanceDate);
	}

	/** Get Last Maintenance.
		@return Last Maintenance Date
	  */
	public Timestamp getLastMaintenanceDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LastMaintenanceDate);
	}

	public org.compiere.model.I_C_BPartner getLease_BPartner() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getLease_BPartner_ID(), get_TrxName());	}

	/** Set Lessor.
		@param Lease_BPartner_ID 
		The Business Partner who rents or leases
	  */
	public void setLease_BPartner_ID (int Lease_BPartner_ID)
	{
		if (Lease_BPartner_ID < 1) 
			set_Value (COLUMNNAME_Lease_BPartner_ID, null);
		else 
			set_Value (COLUMNNAME_Lease_BPartner_ID, Integer.valueOf(Lease_BPartner_ID));
	}

	/** Get Lessor.
		@return The Business Partner who rents or leases
	  */
	public int getLease_BPartner_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Lease_BPartner_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Lease Termination.
		@param LeaseTerminationDate 
		Lease Termination Date
	  */
	public void setLeaseTerminationDate (Timestamp LeaseTerminationDate)
	{
		set_Value (COLUMNNAME_LeaseTerminationDate, LeaseTerminationDate);
	}

	/** Get Lease Termination.
		@return Lease Termination Date
	  */
	public Timestamp getLeaseTerminationDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_LeaseTerminationDate);
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

	/** Set Model No.
		@param ModelNo Model No	  */
	public void setModelNo (String ModelNo)
	{
		set_Value (COLUMNNAME_ModelNo, ModelNo);
	}

	/** Get Model No.
		@return Model No	  */
	public String getModelNo () 
	{
		return (String)get_Value(COLUMNNAME_ModelNo);
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getModelNo());
    }

	/** Set Next Maintenence.
		@param NextMaintenenceDate 
		Next Maintenence Date
	  */
	public void setNextMaintenenceDate (Timestamp NextMaintenenceDate)
	{
		set_Value (COLUMNNAME_NextMaintenenceDate, NextMaintenenceDate);
	}

	/** Get Next Maintenence.
		@return Next Maintenence Date
	  */
	public Timestamp getNextMaintenenceDate () 
	{
		return (Timestamp)get_Value(COLUMNNAME_NextMaintenenceDate);
	}

	/** Set OS Installed.
		@param OsInstalled OS Installed	  */
	public void setOsInstalled (String OsInstalled)
	{
		set_Value (COLUMNNAME_OsInstalled, OsInstalled);
	}

	/** Get OS Installed.
		@return OS Installed	  */
	public String getOsInstalled () 
	{
		return (String)get_Value(COLUMNNAME_OsInstalled);
	}

	/** Set OS Serial No.
		@param OsSerialNo OS Serial No	  */
	public void setOsSerialNo (String OsSerialNo)
	{
		set_Value (COLUMNNAME_OsSerialNo, OsSerialNo);
	}

	/** Get OS Serial No.
		@return OS Serial No	  */
	public String getOsSerialNo () 
	{
		return (String)get_Value(COLUMNNAME_OsSerialNo);
	}

	/** Set Usable Life - Months.
		@param UseLifeMonths 
		Months of the usable life of the asset
	  */
	public void setUseLifeMonths (int UseLifeMonths)
	{
		set_Value (COLUMNNAME_UseLifeMonths, Integer.valueOf(UseLifeMonths));
	}

	/** Get Usable Life - Months.
		@return Months of the usable life of the asset
	  */
	public int getUseLifeMonths () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UseLifeMonths);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Usable Life - Years.
		@param UseLifeYears 
		Years of the usable life of the asset
	  */
	public void setUseLifeYears (int UseLifeYears)
	{
		set_Value (COLUMNNAME_UseLifeYears, Integer.valueOf(UseLifeYears));
	}

	/** Get Usable Life - Years.
		@return Years of the usable life of the asset
	  */
	public int getUseLifeYears () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_UseLifeYears);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}