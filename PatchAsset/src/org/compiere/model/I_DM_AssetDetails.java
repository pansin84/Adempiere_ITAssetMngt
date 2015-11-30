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

/** Generated Interface for DM_AssetDetails
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_DM_AssetDetails 
{

    /** TableName=DM_AssetDetails */
    public static final String Table_Name = "DM_AssetDetails";

    /** AD_Table_ID=1000014 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

    /** Column name A_Asset_Status */
    public static final String COLUMNNAME_A_Asset_Status = "A_Asset_Status";

	/** Set Asset Status	  */
	public void setA_Asset_Status (String A_Asset_Status);

	/** Get Asset Status	  */
	public String getA_Asset_Status();

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

    /** Column name AssetCode */
    public static final String COLUMNNAME_AssetCode = "AssetCode";

	/** Set Asset Code	  */
	public void setAssetCode (String AssetCode);

	/** Get Asset Code	  */
	public String getAssetCode();

    /** Column name AssetServiceDate */
    public static final String COLUMNNAME_AssetServiceDate = "AssetServiceDate";

	/** Set In Service Date.
	  * Date when Asset was put into service
	  */
	public void setAssetServiceDate (Timestamp AssetServiceDate);

	/** Get In Service Date.
	  * Date when Asset was put into service
	  */
	public Timestamp getAssetServiceDate();

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

    /** Column name DeviceSerialNo */
    public static final String COLUMNNAME_DeviceSerialNo = "DeviceSerialNo";

	/** Set Device Serial No	  */
	public void setDeviceSerialNo (String DeviceSerialNo);

	/** Get Device Serial No	  */
	public String getDeviceSerialNo();

    /** Column name DM_AssetDetails_ID */
    public static final String COLUMNNAME_DM_AssetDetails_ID = "DM_AssetDetails_ID";

	/** Set Asset Details ID	  */
	public void setDM_AssetDetails_ID (int DM_AssetDetails_ID);

	/** Get Asset Details ID	  */
	public int getDM_AssetDetails_ID();

    /** Column name DM_AssetModelDetails_ID */
    public static final String COLUMNNAME_DM_AssetModelDetails_ID = "DM_AssetModelDetails_ID";

	/** Set Model No Details ID	  */
	public void setDM_AssetModelDetails_ID (int DM_AssetModelDetails_ID);

	/** Get Model No Details ID	  */
	public int getDM_AssetModelDetails_ID();

	public I_DM_AssetModelDetails getDM_AssetModelDetails() throws RuntimeException;

    /** Column name DM_AssetModelNo_ID */
    public static final String COLUMNNAME_DM_AssetModelNo_ID = "DM_AssetModelNo_ID";

	/** Set Asset Model No Master ID	  */
	public void setDM_AssetModelNo_ID (int DM_AssetModelNo_ID);

	/** Get Asset Model No Master ID	  */
	public int getDM_AssetModelNo_ID();

	public I_DM_AssetModelNo getDM_AssetModelNo() throws RuntimeException;

    /** Column name IP_Address */
    public static final String COLUMNNAME_IP_Address = "IP_Address";

	/** Set IP Address.
	  * Defines the IP address to transfer data to
	  */
	public void setIP_Address (String IP_Address);

	/** Get IP Address.
	  * Defines the IP address to transfer data to
	  */
	public String getIP_Address();

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

    /** Column name LastMaintenanceDate */
    public static final String COLUMNNAME_LastMaintenanceDate = "LastMaintenanceDate";

	/** Set Last Maintenance.
	  * Last Maintenance Date
	  */
	public void setLastMaintenanceDate (Timestamp LastMaintenanceDate);

	/** Get Last Maintenance.
	  * Last Maintenance Date
	  */
	public Timestamp getLastMaintenanceDate();

    /** Column name Lease_BPartner_ID */
    public static final String COLUMNNAME_Lease_BPartner_ID = "Lease_BPartner_ID";

	/** Set Lessor.
	  * The Business Partner who rents or leases
	  */
	public void setLease_BPartner_ID (int Lease_BPartner_ID);

	/** Get Lessor.
	  * The Business Partner who rents or leases
	  */
	public int getLease_BPartner_ID();

	public org.compiere.model.I_C_BPartner getLease_BPartner() throws RuntimeException;

    /** Column name LeaseTerminationDate */
    public static final String COLUMNNAME_LeaseTerminationDate = "LeaseTerminationDate";

	/** Set Lease Termination.
	  * Lease Termination Date
	  */
	public void setLeaseTerminationDate (Timestamp LeaseTerminationDate);

	/** Get Lease Termination.
	  * Lease Termination Date
	  */
	public Timestamp getLeaseTerminationDate();

    /** Column name MobilePhone */
    public static final String COLUMNNAME_MobilePhone = "MobilePhone";

	/** Set Mobile Phone.
	  * Identifies an alternate telephone mobile number.
	  */
	public void setMobilePhone (String MobilePhone);

	/** Get Mobile Phone.
	  * Identifies an alternate telephone mobile number.
	  */
	public String getMobilePhone();

    /** Column name ModelNo */
    public static final String COLUMNNAME_ModelNo = "ModelNo";

	/** Set Model No	  */
	public void setModelNo (String ModelNo);

	/** Get Model No	  */
	public String getModelNo();

    /** Column name NextMaintenenceDate */
    public static final String COLUMNNAME_NextMaintenenceDate = "NextMaintenenceDate";

	/** Set Next Maintenence.
	  * Next Maintenence Date
	  */
	public void setNextMaintenenceDate (Timestamp NextMaintenenceDate);

	/** Get Next Maintenence.
	  * Next Maintenence Date
	  */
	public Timestamp getNextMaintenenceDate();

    /** Column name OsInstalled */
    public static final String COLUMNNAME_OsInstalled = "OsInstalled";

	/** Set OS Installed	  */
	public void setOsInstalled (String OsInstalled);

	/** Get OS Installed	  */
	public String getOsInstalled();

    /** Column name OsSerialNo */
    public static final String COLUMNNAME_OsSerialNo = "OsSerialNo";

	/** Set OS Serial No	  */
	public void setOsSerialNo (String OsSerialNo);

	/** Get OS Serial No	  */
	public String getOsSerialNo();

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

    /** Column name UseLifeMonths */
    public static final String COLUMNNAME_UseLifeMonths = "UseLifeMonths";

	/** Set Usable Life - Months.
	  * Months of the usable life of the asset
	  */
	public void setUseLifeMonths (int UseLifeMonths);

	/** Get Usable Life - Months.
	  * Months of the usable life of the asset
	  */
	public int getUseLifeMonths();

    /** Column name UseLifeYears */
    public static final String COLUMNNAME_UseLifeYears = "UseLifeYears";

	/** Set Usable Life - Years.
	  * Years of the usable life of the asset
	  */
	public void setUseLifeYears (int UseLifeYears);

	/** Get Usable Life - Years.
	  * Years of the usable life of the asset
	  */
	public int getUseLifeYears();
}
