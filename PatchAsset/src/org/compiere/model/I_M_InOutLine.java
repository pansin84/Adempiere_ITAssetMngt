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

/** Generated Interface for M_InOutLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0
 */
public interface I_M_InOutLine 
{

    /** TableName=M_InOutLine */
    public static final String Table_Name = "M_InOutLine";

    /** AD_Table_ID=320 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 1 - Org 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(1);

    /** Load Meta Data */

    /** Column name A_License_No */
    public static final String COLUMNNAME_A_License_No = "A_License_No";

	/** Set Asset License No	  */
	public void setA_License_No (String A_License_No);

	/** Get Asset License No	  */
	public String getA_License_No();

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

    /** Column name AD_OrgTrx_ID */
    public static final String COLUMNNAME_AD_OrgTrx_ID = "AD_OrgTrx_ID";

	/** Set Trx Organization.
	  * Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID);

	/** Get Trx Organization.
	  * Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID();

    /** Column name AssetManufacturer_ID */
    public static final String COLUMNNAME_AssetManufacturer_ID = "AssetManufacturer_ID";

	/** Set Manufacturer	  */
	public void setAssetManufacturer_ID (int AssetManufacturer_ID);

	/** Get Manufacturer	  */
	public int getAssetManufacturer_ID();

	public org.compiere.model.I_C_BPartner getAssetManufacturer() throws RuntimeException;

    /** Column name C_Activity_ID */
    public static final String COLUMNNAME_C_Activity_ID = "C_Activity_ID";

	/** Set Activity.
	  * Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID);

	/** Get Activity.
	  * Business Activity
	  */
	public int getC_Activity_ID();

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException;

    /** Column name C_Campaign_ID */
    public static final String COLUMNNAME_C_Campaign_ID = "C_Campaign_ID";

	/** Set Campaign.
	  * Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID);

	/** Get Campaign.
	  * Marketing Campaign
	  */
	public int getC_Campaign_ID();

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException;

    /** Column name C_Charge_ID */
    public static final String COLUMNNAME_C_Charge_ID = "C_Charge_ID";

	/** Set Charge.
	  * Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID);

	/** Get Charge.
	  * Additional document charges
	  */
	public int getC_Charge_ID();

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException;

    /** Column name C_OrderLine_ID */
    public static final String COLUMNNAME_C_OrderLine_ID = "C_OrderLine_ID";

	/** Set Sales Order Line.
	  * Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID);

	/** Get Sales Order Line.
	  * Sales Order Line
	  */
	public int getC_OrderLine_ID();

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException;

    /** Column name C_Project_ID */
    public static final String COLUMNNAME_C_Project_ID = "C_Project_ID";

	/** Set Project.
	  * Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID);

	/** Get Project.
	  * Financial Project
	  */
	public int getC_Project_ID();

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException;

    /** Column name C_ProjectPhase_ID */
    public static final String COLUMNNAME_C_ProjectPhase_ID = "C_ProjectPhase_ID";

	/** Set Project Phase.
	  * Phase of a Project
	  */
	public void setC_ProjectPhase_ID (int C_ProjectPhase_ID);

	/** Get Project Phase.
	  * Phase of a Project
	  */
	public int getC_ProjectPhase_ID();

	public org.compiere.model.I_C_ProjectPhase getC_ProjectPhase() throws RuntimeException;

    /** Column name C_ProjectTask_ID */
    public static final String COLUMNNAME_C_ProjectTask_ID = "C_ProjectTask_ID";

	/** Set Project Task.
	  * Actual Project Task in a Phase
	  */
	public void setC_ProjectTask_ID (int C_ProjectTask_ID);

	/** Get Project Task.
	  * Actual Project Task in a Phase
	  */
	public int getC_ProjectTask_ID();

	public org.compiere.model.I_C_ProjectTask getC_ProjectTask() throws RuntimeException;

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

    /** Column name ConfirmedQty */
    public static final String COLUMNNAME_ConfirmedQty = "ConfirmedQty";

	/** Set Confirmed Quantity.
	  * Confirmation of a received quantity
	  */
	public void setConfirmedQty (BigDecimal ConfirmedQty);

	/** Get Confirmed Quantity.
	  * Confirmation of a received quantity
	  */
	public BigDecimal getConfirmedQty();

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

    /** Column name Description */
    public static final String COLUMNNAME_Description = "Description";

	/** Set Description.
	  * Optional short description of the record
	  */
	public void setDescription (String Description);

	/** Get Description.
	  * Optional short description of the record
	  */
	public String getDescription();

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

    /** Column name IsDescription */
    public static final String COLUMNNAME_IsDescription = "IsDescription";

	/** Set Description Only.
	  * if true, the line is just description and no transaction
	  */
	public void setIsDescription (boolean IsDescription);

	/** Get Description Only.
	  * if true, the line is just description and no transaction
	  */
	public boolean isDescription();

    /** Column name IsInternet */
    public static final String COLUMNNAME_IsInternet = "IsInternet";

	/** Set Mobile/Internet Accessory	  */
	public void setIsInternet (boolean IsInternet);

	/** Get Mobile/Internet Accessory	  */
	public boolean isInternet();

    /** Column name IsInvoiced */
    public static final String COLUMNNAME_IsInvoiced = "IsInvoiced";

	/** Set Invoiced.
	  * Is this invoiced?
	  */
	public void setIsInvoiced (boolean IsInvoiced);

	/** Get Invoiced.
	  * Is this invoiced?
	  */
	public boolean isInvoiced();

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

    /** Column name IsSerialNoReq */
    public static final String COLUMNNAME_IsSerialNoReq = "IsSerialNoReq";

	/** Set Device Serial No Required	  */
	public void setIsSerialNoReq (boolean IsSerialNoReq);

	/** Get Device Serial No Required	  */
	public boolean isSerialNoReq();

    /** Column name IsWifiSupport */
    public static final String COLUMNNAME_IsWifiSupport = "IsWifiSupport";

	/** Set Is WIFI Support	  */
	public void setIsWifiSupport (boolean IsWifiSupport);

	/** Get Is WIFI Support	  */
	public boolean isWifiSupport();

    /** Column name Line */
    public static final String COLUMNNAME_Line = "Line";

	/** Set Line No.
	  * Unique line for this document
	  */
	public void setLine (int Line);

	/** Get Line No.
	  * Unique line for this document
	  */
	public int getLine();

    /** Column name M_AttributeSetInstance_ID */
    public static final String COLUMNNAME_M_AttributeSetInstance_ID = "M_AttributeSetInstance_ID";

	/** Set Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID);

	/** Get Attribute Set Instance.
	  * Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID();

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException;

    /** Column name M_InOut_ID */
    public static final String COLUMNNAME_M_InOut_ID = "M_InOut_ID";

	/** Set Shipment/Receipt.
	  * Material Shipment Document
	  */
	public void setM_InOut_ID (int M_InOut_ID);

	/** Get Shipment/Receipt.
	  * Material Shipment Document
	  */
	public int getM_InOut_ID();

	public org.compiere.model.I_M_InOut getM_InOut() throws RuntimeException;

    /** Column name M_InOutLine_ID */
    public static final String COLUMNNAME_M_InOutLine_ID = "M_InOutLine_ID";

	/** Set Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID);

	/** Get Shipment/Receipt Line.
	  * Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID();

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

	public I_M_Locator getM_Locator() throws RuntimeException;

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

    /** Column name M_RMALine_ID */
    public static final String COLUMNNAME_M_RMALine_ID = "M_RMALine_ID";

	/** Set RMA Line.
	  * Return Material Authorization Line
	  */
	public void setM_RMALine_ID (int M_RMALine_ID);

	/** Get RMA Line.
	  * Return Material Authorization Line
	  */
	public int getM_RMALine_ID();

	public org.compiere.model.I_M_RMALine getM_RMALine() throws RuntimeException;

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

    /** Column name MovementQty */
    public static final String COLUMNNAME_MovementQty = "MovementQty";

	/** Set Movement Quantity.
	  * Quantity of a product moved.
	  */
	public void setMovementQty (BigDecimal MovementQty);

	/** Get Movement Quantity.
	  * Quantity of a product moved.
	  */
	public BigDecimal getMovementQty();

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

    /** Column name PickedQty */
    public static final String COLUMNNAME_PickedQty = "PickedQty";

	/** Set Picked Quantity	  */
	public void setPickedQty (BigDecimal PickedQty);

	/** Get Picked Quantity	  */
	public BigDecimal getPickedQty();

    /** Column name Processed */
    public static final String COLUMNNAME_Processed = "Processed";

	/** Set Processed.
	  * The document has been processed
	  */
	public void setProcessed (boolean Processed);

	/** Get Processed.
	  * The document has been processed
	  */
	public boolean isProcessed();

    /** Column name Processor */
    public static final String COLUMNNAME_Processor = "Processor";

	/** Set Processor	  */
	public void setProcessor (String Processor);

	/** Get Processor	  */
	public String getProcessor();

    /** Column name PurchaseType */
    public static final String COLUMNNAME_PurchaseType = "PurchaseType";

	/** Set Purchase Type	  */
	public void setPurchaseType (String PurchaseType);

	/** Get Purchase Type	  */
	public String getPurchaseType();

    /** Column name QtyEntered */
    public static final String COLUMNNAME_QtyEntered = "QtyEntered";

	/** Set Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered);

	/** Get Quantity.
	  * The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered();

    /** Column name RAM */
    public static final String COLUMNNAME_RAM = "RAM";

	/** Set RAM (In GB)	  */
	public void setRAM (int RAM);

	/** Get RAM (In GB)	  */
	public int getRAM();

    /** Column name Ref_InOutLine_ID */
    public static final String COLUMNNAME_Ref_InOutLine_ID = "Ref_InOutLine_ID";

	/** Set Referenced Shipment Line	  */
	public void setRef_InOutLine_ID (int Ref_InOutLine_ID);

	/** Get Referenced Shipment Line	  */
	public int getRef_InOutLine_ID();

    /** Column name ReversalLine_ID */
    public static final String COLUMNNAME_ReversalLine_ID = "ReversalLine_ID";

	/** Set Reversal Line.
	  * Use to keep the reversal line ID for reversing costing purpose
	  */
	public void setReversalLine_ID (int ReversalLine_ID);

	/** Get Reversal Line.
	  * Use to keep the reversal line ID for reversing costing purpose
	  */
	public int getReversalLine_ID();

	public org.compiere.model.I_M_InOutLine getReversalLine() throws RuntimeException;

    /** Column name ScrappedQty */
    public static final String COLUMNNAME_ScrappedQty = "ScrappedQty";

	/** Set Scrapped Quantity.
	  * The Quantity scrapped due to QA issues
	  */
	public void setScrappedQty (BigDecimal ScrappedQty);

	/** Get Scrapped Quantity.
	  * The Quantity scrapped due to QA issues
	  */
	public BigDecimal getScrappedQty();

    /** Column name ScreenSize */
    public static final String COLUMNNAME_ScreenSize = "ScreenSize";

	/** Set Screen Size (In Inch)	  */
	public void setScreenSize (BigDecimal ScreenSize);

	/** Get Screen Size (In Inch)	  */
	public BigDecimal getScreenSize();

    /** Column name TargetQty */
    public static final String COLUMNNAME_TargetQty = "TargetQty";

	/** Set Target Quantity.
	  * Target Movement Quantity
	  */
	public void setTargetQty (BigDecimal TargetQty);

	/** Get Target Quantity.
	  * Target Movement Quantity
	  */
	public BigDecimal getTargetQty();

    /** Column name ToBeInvoiced */
    public static final String COLUMNNAME_ToBeInvoiced = "ToBeInvoiced";

	/** Set To be Invoiced.
	  * If Shipment line is marked as ToBeInvoiced='N' then it is included in the generated Invoice line with price 0.0
	  */
	public void setToBeInvoiced (boolean ToBeInvoiced);

	/** Get To be Invoiced.
	  * If Shipment line is marked as ToBeInvoiced='N' then it is included in the generated Invoice line with price 0.0
	  */
	public boolean isToBeInvoiced();

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

    /** Column name User1_ID */
    public static final String COLUMNNAME_User1_ID = "User1_ID";

	/** Set User List 1.
	  * User defined list element #1
	  */
	public void setUser1_ID (int User1_ID);

	/** Get User List 1.
	  * User defined list element #1
	  */
	public int getUser1_ID();

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException;

    /** Column name User2_ID */
    public static final String COLUMNNAME_User2_ID = "User2_ID";

	/** Set User List 2.
	  * User defined list element #2
	  */
	public void setUser2_ID (int User2_ID);

	/** Get User List 2.
	  * User defined list element #2
	  */
	public int getUser2_ID();

	public org.compiere.model.I_C_ElementValue getUser2() throws RuntimeException;
}
