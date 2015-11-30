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

/** Generated Interface for DM_AssetModelDetails
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_DM_AssetModelDetails 
{

    /** TableName=DM_AssetModelDetails */
    public static final String Table_Name = "DM_AssetModelDetails";

    /** AD_Table_ID=1000012 */
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

    /** Column name AssetCode */
    public static final String COLUMNNAME_AssetCode = "AssetCode";

	/** Set Asset Code	  */
	public void setAssetCode (String AssetCode);

	/** Get Asset Code	  */
	public String getAssetCode();

    /** Column name AssetManufacturer_ID */
    public static final String COLUMNNAME_AssetManufacturer_ID = "AssetManufacturer_ID";

	/** Set Manufacturer	  */
	public void setAssetManufacturer_ID (int AssetManufacturer_ID);

	/** Get Manufacturer	  */
	public int getAssetManufacturer_ID();

	public org.compiere.model.I_C_BPartner getAssetManufacturer() throws RuntimeException;

    /** Column name C_UOM_ID */
    public static final String COLUMNNAME_C_UOM_ID = "C_UOM_ID";

	/** Set UOM.
	  * Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID);

	/** Get UOM.
	  * Unit of Measure
	  */
	public int getC_UOM_ID();

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException;

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

    /** Column name DM_AssetModelDetails_ID */
    public static final String COLUMNNAME_DM_AssetModelDetails_ID = "DM_AssetModelDetails_ID";

	/** Set Model No Details ID	  */
	public void setDM_AssetModelDetails_ID (int DM_AssetModelDetails_ID);

	/** Get Model No Details ID	  */
	public int getDM_AssetModelDetails_ID();

    /** Column name DM_AssetModelNo_ID */
    public static final String COLUMNNAME_DM_AssetModelNo_ID = "DM_AssetModelNo_ID";

	/** Set Asset Model No Master ID	  */
	public void setDM_AssetModelNo_ID (int DM_AssetModelNo_ID);

	/** Get Asset Model No Master ID	  */
	public int getDM_AssetModelNo_ID();

	public I_DM_AssetModelNo getDM_AssetModelNo() throws RuntimeException;

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

    /** Column name IsAccessories */
    public static final String COLUMNNAME_IsAccessories = "IsAccessories";

	/** Set Accessories	  */
	public void setIsAccessories (boolean IsAccessories);

	/** Get Accessories	  */
	public boolean isAccessories();

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

    /** Column name Processor */
    public static final String COLUMNNAME_Processor = "Processor";

	/** Set Processor	  */
	public void setProcessor (String Processor);

	/** Get Processor	  */
	public String getProcessor();

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
