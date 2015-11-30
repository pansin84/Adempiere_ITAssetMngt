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

/** Generated Interface for DM_StorageDetails
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_DM_StorageDetails 
{

    /** TableName=DM_StorageDetails */
    public static final String Table_Name = "DM_StorageDetails";

    /** AD_Table_ID=1000013 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 3 - Client - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(3);

    /** Load Meta Data */

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

    /** Column name AssetManufacturer_ID */
    public static final String COLUMNNAME_AssetManufacturer_ID = "AssetManufacturer_ID";

	/** Set Manufacturer	  */
	public void setAssetManufacturer_ID (int AssetManufacturer_ID);

	/** Get Manufacturer	  */
	public int getAssetManufacturer_ID();

	public org.compiere.model.I_C_BPartner getAssetManufacturer() throws RuntimeException;

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

    /** Column name DM_AssetModelDetails_ID */
    public static final String COLUMNNAME_DM_AssetModelDetails_ID = "DM_AssetModelDetails_ID";

	/** Set Model No Details ID	  */
	public void setDM_AssetModelDetails_ID (int DM_AssetModelDetails_ID);

	/** Get Model No Details ID	  */
	public int getDM_AssetModelDetails_ID();

	public I_DM_AssetModelDetails getDM_AssetModelDetails() throws RuntimeException;

    /** Column name DM_StorageDetails_ID */
    public static final String COLUMNNAME_DM_StorageDetails_ID = "DM_StorageDetails_ID";

	/** Set Storage Details ID	  */
	public void setDM_StorageDetails_ID (int DM_StorageDetails_ID);

	/** Get Storage Details ID	  */
	public int getDM_StorageDetails_ID();

    /** Column name GraphicsCard */
    public static final String COLUMNNAME_GraphicsCard = "GraphicsCard";

	/** Set Graphics Card (in GB)	  */
	public void setGraphicsCard (int GraphicsCard);

	/** Get Graphics Card (in GB)	  */
	public int getGraphicsCard();

    /** Column name HDD */
    public static final String COLUMNNAME_HDD = "HDD";

	/** Set HDD/SSD (In GB)	  */
	public void setHDD (int HDD);

	/** Get HDD/SSD (In GB)	  */
	public int getHDD();

    /** Column name InputTrayCapacity */
    public static final String COLUMNNAME_InputTrayCapacity = "InputTrayCapacity";

	/** Set Input Tray Capacity (No Of Pages)	  */
	public void setInputTrayCapacity (int InputTrayCapacity);

	/** Get Input Tray Capacity (No Of Pages)	  */
	public int getInputTrayCapacity();

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

    /** Column name IsCanCopy */
    public static final String COLUMNNAME_IsCanCopy = "IsCanCopy";

	/** Set Copy	  */
	public void setIsCanCopy (boolean IsCanCopy);

	/** Get Copy	  */
	public boolean isCanCopy();

    /** Column name IsCanPrint */
    public static final String COLUMNNAME_IsCanPrint = "IsCanPrint";

	/** Set Print	  */
	public void setIsCanPrint (boolean IsCanPrint);

	/** Get Print	  */
	public boolean isCanPrint();

    /** Column name IsCanScan */
    public static final String COLUMNNAME_IsCanScan = "IsCanScan";

	/** Set Scan	  */
	public void setIsCanScan (boolean IsCanScan);

	/** Get Scan	  */
	public boolean isCanScan();

    /** Column name IsCDROMAvailable */
    public static final String COLUMNNAME_IsCDROMAvailable = "IsCDROMAvailable";

	/** Set CD ROM Available	  */
	public void setIsCDROMAvailable (boolean IsCDROMAvailable);

	/** Get CD ROM Available	  */
	public boolean isCDROMAvailable();

    /** Column name IsColorPrinter */
    public static final String COLUMNNAME_IsColorPrinter = "IsColorPrinter";

	/** Set Printer Type Color	  */
	public void setIsColorPrinter (boolean IsColorPrinter);

	/** Get Printer Type Color	  */
	public boolean isColorPrinter();

    /** Column name IsConsumable */
    public static final String COLUMNNAME_IsConsumable = "IsConsumable";

	/** Set Consumable (Once used cann't return back)	  */
	public void setIsConsumable (boolean IsConsumable);

	/** Get Consumable (Once used cann't return back)	  */
	public boolean isConsumable();

    /** Column name IsInternet */
    public static final String COLUMNNAME_IsInternet = "IsInternet";

	/** Set Mobile/Internet Accessory	  */
	public void setIsInternet (boolean IsInternet);

	/** Get Mobile/Internet Accessory	  */
	public boolean isInternet();

    /** Column name IsLaptopDektop */
    public static final String COLUMNNAME_IsLaptopDektop = "IsLaptopDektop";

	/** Set Laptop/Desktop/AIO	  */
	public void setIsLaptopDektop (boolean IsLaptopDektop);

	/** Get Laptop/Desktop/AIO	  */
	public boolean isLaptopDektop();

    /** Column name IsLDAccessory */
    public static final String COLUMNNAME_IsLDAccessory = "IsLDAccessory";

	/** Set Laptop/Desktop Accessory	  */
	public void setIsLDAccessory (boolean IsLDAccessory);

	/** Get Laptop/Desktop Accessory	  */
	public boolean isLDAccessory();

    /** Column name IsNetComp */
    public static final String COLUMNNAME_IsNetComp = "IsNetComp";

	/** Set Network Component	  */
	public void setIsNetComp (boolean IsNetComp);

	/** Get Network Component	  */
	public boolean isNetComp();

    /** Column name IsPrinter */
    public static final String COLUMNNAME_IsPrinter = "IsPrinter";

	/** Set Printer	  */
	public void setIsPrinter (boolean IsPrinter);

	/** Get Printer	  */
	public boolean isPrinter();

    /** Column name IsPrinterAccessory */
    public static final String COLUMNNAME_IsPrinterAccessory = "IsPrinterAccessory";

	/** Set Printer Accessory	  */
	public void setIsPrinterAccessory (boolean IsPrinterAccessory);

	/** Get Printer Accessory	  */
	public boolean isPrinterAccessory();

    /** Column name IsWifiSupport */
    public static final String COLUMNNAME_IsWifiSupport = "IsWifiSupport";

	/** Set Is WIFI Support	  */
	public void setIsWifiSupport (boolean IsWifiSupport);

	/** Get Is WIFI Support	  */
	public boolean isWifiSupport();

    /** Column name M_Locator_ID */
    public static final String COLUMNNAME_M_Locator_ID = "M_Locator_ID";

	/** Set Locator.
	  * Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID);

	/** Get Locator.
	  * Warehouse Locator
	  */
	public int getM_Locator_ID();

	public org.compiere.model.I_M_Locator getM_Locator() throws RuntimeException;

    /** Column name M_Product_Category_ID */
    public static final String COLUMNNAME_M_Product_Category_ID = "M_Product_Category_ID";

	/** Set Product Category.
	  * Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID);

	/** Get Product Category.
	  * Category of a Product
	  */
	public int getM_Product_Category_ID();

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException;

    /** Column name M_Product_ID */
    public static final String COLUMNNAME_M_Product_ID = "M_Product_ID";

	/** Set Product.
	  * Product, Service, Item
	  */
	public void setM_Product_ID (int M_Product_ID);

	/** Get Product.
	  * Product, Service, Item
	  */
	public int getM_Product_ID();

	public org.compiere.model.I_M_Product getM_Product() throws RuntimeException;

    /** Column name M_Storage_ID */
    public static final String COLUMNNAME_M_Storage_ID = "M_Storage_ID";

	/** Set Storage ID	  */
	public void setM_Storage_ID (int M_Storage_ID);

	/** Get Storage ID	  */
	public int getM_Storage_ID();

	public org.compiere.model.I_M_Storage getM_Storage() throws RuntimeException;

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

    /** Column name Processor */
    public static final String COLUMNNAME_Processor = "Processor";

	/** Set Processor	  */
	public void setProcessor (String Processor);

	/** Get Processor	  */
	public String getProcessor();

    /** Column name QtyAvailable */
    public static final String COLUMNNAME_QtyAvailable = "QtyAvailable";

	/** Set Available Quantity.
	  * Available Quantity (On Hand - Reserved)
	  */
	public void setQtyAvailable (BigDecimal QtyAvailable);

	/** Get Available Quantity.
	  * Available Quantity (On Hand - Reserved)
	  */
	public BigDecimal getQtyAvailable();

    /** Column name RAM */
    public static final String COLUMNNAME_RAM = "RAM";

	/** Set RAM (In GB)	  */
	public void setRAM (int RAM);

	/** Get RAM (In GB)	  */
	public int getRAM();

    /** Column name ScreenSize */
    public static final String COLUMNNAME_ScreenSize = "ScreenSize";

	/** Set Screen Size (In Inch)	  */
	public void setScreenSize (BigDecimal ScreenSize);

	/** Get Screen Size (In Inch)	  */
	public BigDecimal getScreenSize();

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
}
