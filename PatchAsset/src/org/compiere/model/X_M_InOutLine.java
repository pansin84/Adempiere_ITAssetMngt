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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for M_InOutLine
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_M_InOutLine extends PO implements I_M_InOutLine, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150829L;

    /** Standard Constructor */
    public X_M_InOutLine (Properties ctx, int M_InOutLine_ID, String trxName)
    {
      super (ctx, M_InOutLine_ID, trxName);
      /** if (M_InOutLine_ID == 0)
        {
			setC_UOM_ID (0);
// @#C_UOM_ID@
			setIsDescription (false);
// N
			setIsInvoiced (false);
			setLine (0);
// @SQL=SELECT NVL(MAX(Line),0)+10 AS DefaultValue FROM M_InOutLine WHERE M_InOut_ID=@M_InOut_ID@
			setM_AttributeSetInstance_ID (0);
			setM_InOut_ID (0);
			setM_InOutLine_ID (0);
			setMovementQty (Env.ZERO);
// 1
			setProcessed (false);
			setQtyEntered (Env.ZERO);
// 1
        } */
    }

    /** Load Constructor */
    public X_M_InOutLine (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 1 - Org 
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
      StringBuffer sb = new StringBuffer ("X_M_InOutLine[")
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

	/** Set Trx Organization.
		@param AD_OrgTrx_ID 
		Performing or initiating organization
	  */
	public void setAD_OrgTrx_ID (int AD_OrgTrx_ID)
	{
		if (AD_OrgTrx_ID < 1) 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, null);
		else 
			set_Value (COLUMNNAME_AD_OrgTrx_ID, Integer.valueOf(AD_OrgTrx_ID));
	}

	/** Get Trx Organization.
		@return Performing or initiating organization
	  */
	public int getAD_OrgTrx_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_OrgTrx_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_BPartner getAssetManufacturer() throws RuntimeException
    {
		return (org.compiere.model.I_C_BPartner)MTable.get(getCtx(), org.compiere.model.I_C_BPartner.Table_Name)
			.getPO(getAssetManufacturer_ID(), get_TrxName());	}

	/** Set Manufacturer.
		@param AssetManufacturer_ID Manufacturer	  */
	public void setAssetManufacturer_ID (int AssetManufacturer_ID)
	{
		if (AssetManufacturer_ID < 1) 
			set_Value (COLUMNNAME_AssetManufacturer_ID, null);
		else 
			set_Value (COLUMNNAME_AssetManufacturer_ID, Integer.valueOf(AssetManufacturer_ID));
	}

	/** Get Manufacturer.
		@return Manufacturer	  */
	public int getAssetManufacturer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AssetManufacturer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Activity getC_Activity() throws RuntimeException
    {
		return (org.compiere.model.I_C_Activity)MTable.get(getCtx(), org.compiere.model.I_C_Activity.Table_Name)
			.getPO(getC_Activity_ID(), get_TrxName());	}

	/** Set Activity.
		@param C_Activity_ID 
		Business Activity
	  */
	public void setC_Activity_ID (int C_Activity_ID)
	{
		if (C_Activity_ID < 1) 
			set_Value (COLUMNNAME_C_Activity_ID, null);
		else 
			set_Value (COLUMNNAME_C_Activity_ID, Integer.valueOf(C_Activity_ID));
	}

	/** Get Activity.
		@return Business Activity
	  */
	public int getC_Activity_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Activity_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Campaign getC_Campaign() throws RuntimeException
    {
		return (org.compiere.model.I_C_Campaign)MTable.get(getCtx(), org.compiere.model.I_C_Campaign.Table_Name)
			.getPO(getC_Campaign_ID(), get_TrxName());	}

	/** Set Campaign.
		@param C_Campaign_ID 
		Marketing Campaign
	  */
	public void setC_Campaign_ID (int C_Campaign_ID)
	{
		if (C_Campaign_ID < 1) 
			set_Value (COLUMNNAME_C_Campaign_ID, null);
		else 
			set_Value (COLUMNNAME_C_Campaign_ID, Integer.valueOf(C_Campaign_ID));
	}

	/** Get Campaign.
		@return Marketing Campaign
	  */
	public int getC_Campaign_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Campaign_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Charge getC_Charge() throws RuntimeException
    {
		return (org.compiere.model.I_C_Charge)MTable.get(getCtx(), org.compiere.model.I_C_Charge.Table_Name)
			.getPO(getC_Charge_ID(), get_TrxName());	}

	/** Set Charge.
		@param C_Charge_ID 
		Additional document charges
	  */
	public void setC_Charge_ID (int C_Charge_ID)
	{
		if (C_Charge_ID < 1) 
			set_Value (COLUMNNAME_C_Charge_ID, null);
		else 
			set_Value (COLUMNNAME_C_Charge_ID, Integer.valueOf(C_Charge_ID));
	}

	/** Get Charge.
		@return Additional document charges
	  */
	public int getC_Charge_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Charge_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_OrderLine getC_OrderLine() throws RuntimeException
    {
		return (org.compiere.model.I_C_OrderLine)MTable.get(getCtx(), org.compiere.model.I_C_OrderLine.Table_Name)
			.getPO(getC_OrderLine_ID(), get_TrxName());	}

	/** Set Sales Order Line.
		@param C_OrderLine_ID 
		Sales Order Line
	  */
	public void setC_OrderLine_ID (int C_OrderLine_ID)
	{
		if (C_OrderLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_OrderLine_ID, Integer.valueOf(C_OrderLine_ID));
	}

	/** Get Sales Order Line.
		@return Sales Order Line
	  */
	public int getC_OrderLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_OrderLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_Project getC_Project() throws RuntimeException
    {
		return (org.compiere.model.I_C_Project)MTable.get(getCtx(), org.compiere.model.I_C_Project.Table_Name)
			.getPO(getC_Project_ID(), get_TrxName());	}

	/** Set Project.
		@param C_Project_ID 
		Financial Project
	  */
	public void setC_Project_ID (int C_Project_ID)
	{
		if (C_Project_ID < 1) 
			set_Value (COLUMNNAME_C_Project_ID, null);
		else 
			set_Value (COLUMNNAME_C_Project_ID, Integer.valueOf(C_Project_ID));
	}

	/** Get Project.
		@return Financial Project
	  */
	public int getC_Project_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_Project_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ProjectPhase getC_ProjectPhase() throws RuntimeException
    {
		return (org.compiere.model.I_C_ProjectPhase)MTable.get(getCtx(), org.compiere.model.I_C_ProjectPhase.Table_Name)
			.getPO(getC_ProjectPhase_ID(), get_TrxName());	}

	/** Set Project Phase.
		@param C_ProjectPhase_ID 
		Phase of a Project
	  */
	public void setC_ProjectPhase_ID (int C_ProjectPhase_ID)
	{
		if (C_ProjectPhase_ID < 1) 
			set_Value (COLUMNNAME_C_ProjectPhase_ID, null);
		else 
			set_Value (COLUMNNAME_C_ProjectPhase_ID, Integer.valueOf(C_ProjectPhase_ID));
	}

	/** Get Project Phase.
		@return Phase of a Project
	  */
	public int getC_ProjectPhase_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectPhase_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ProjectTask getC_ProjectTask() throws RuntimeException
    {
		return (org.compiere.model.I_C_ProjectTask)MTable.get(getCtx(), org.compiere.model.I_C_ProjectTask.Table_Name)
			.getPO(getC_ProjectTask_ID(), get_TrxName());	}

	/** Set Project Task.
		@param C_ProjectTask_ID 
		Actual Project Task in a Phase
	  */
	public void setC_ProjectTask_ID (int C_ProjectTask_ID)
	{
		if (C_ProjectTask_ID < 1) 
			set_Value (COLUMNNAME_C_ProjectTask_ID, null);
		else 
			set_Value (COLUMNNAME_C_ProjectTask_ID, Integer.valueOf(C_ProjectTask_ID));
	}

	/** Get Project Task.
		@return Actual Project Task in a Phase
	  */
	public int getC_ProjectTask_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_ProjectTask_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_UOM getC_UOM() throws RuntimeException
    {
		return (org.compiere.model.I_C_UOM)MTable.get(getCtx(), org.compiere.model.I_C_UOM.Table_Name)
			.getPO(getC_UOM_ID(), get_TrxName());	}

	/** Set UOM.
		@param C_UOM_ID 
		Unit of Measure
	  */
	public void setC_UOM_ID (int C_UOM_ID)
	{
		if (C_UOM_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_C_UOM_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
	}

	/** Get UOM.
		@return Unit of Measure
	  */
	public int getC_UOM_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_C_UOM_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Confirmed Quantity.
		@param ConfirmedQty 
		Confirmation of a received quantity
	  */
	public void setConfirmedQty (BigDecimal ConfirmedQty)
	{
		set_Value (COLUMNNAME_ConfirmedQty, ConfirmedQty);
	}

	/** Get Confirmed Quantity.
		@return Confirmation of a received quantity
	  */
	public BigDecimal getConfirmedQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ConfirmedQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
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

	/** Set Storage Details ID.
		@param DM_StorageDetails_ID Storage Details ID	  */
	public void setDM_StorageDetails_ID (int DM_StorageDetails_ID)
	{
		if (DM_StorageDetails_ID < 1) 
			set_Value (COLUMNNAME_DM_StorageDetails_ID, null);
		else 
			set_Value (COLUMNNAME_DM_StorageDetails_ID, Integer.valueOf(DM_StorageDetails_ID));
	}

	/** Get Storage Details ID.
		@return Storage Details ID	  */
	public int getDM_StorageDetails_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_DM_StorageDetails_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Graphics Card (in GB).
		@param GraphicsCard Graphics Card (in GB)	  */
	public void setGraphicsCard (int GraphicsCard)
	{
		set_Value (COLUMNNAME_GraphicsCard, Integer.valueOf(GraphicsCard));
	}

	/** Get Graphics Card (in GB).
		@return Graphics Card (in GB)	  */
	public int getGraphicsCard () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_GraphicsCard);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set HDD/SSD (In GB).
		@param HDD HDD/SSD (In GB)	  */
	public void setHDD (int HDD)
	{
		set_Value (COLUMNNAME_HDD, Integer.valueOf(HDD));
	}

	/** Get HDD/SSD (In GB).
		@return HDD/SSD (In GB)	  */
	public int getHDD () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_HDD);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Input Tray Capacity (No Of Pages).
		@param InputTrayCapacity Input Tray Capacity (No Of Pages)	  */
	public void setInputTrayCapacity (int InputTrayCapacity)
	{
		set_Value (COLUMNNAME_InputTrayCapacity, Integer.valueOf(InputTrayCapacity));
	}

	/** Get Input Tray Capacity (No Of Pages).
		@return Input Tray Capacity (No Of Pages)	  */
	public int getInputTrayCapacity () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_InputTrayCapacity);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Accessories.
		@param IsAccessories Accessories	  */
	public void setIsAccessories (boolean IsAccessories)
	{
		set_Value (COLUMNNAME_IsAccessories, Boolean.valueOf(IsAccessories));
	}

	/** Get Accessories.
		@return Accessories	  */
	public boolean isAccessories () 
	{
		Object oo = get_Value(COLUMNNAME_IsAccessories);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Copy.
		@param IsCanCopy Copy	  */
	public void setIsCanCopy (boolean IsCanCopy)
	{
		set_Value (COLUMNNAME_IsCanCopy, Boolean.valueOf(IsCanCopy));
	}

	/** Get Copy.
		@return Copy	  */
	public boolean isCanCopy () 
	{
		Object oo = get_Value(COLUMNNAME_IsCanCopy);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Print.
		@param IsCanPrint Print	  */
	public void setIsCanPrint (boolean IsCanPrint)
	{
		set_Value (COLUMNNAME_IsCanPrint, Boolean.valueOf(IsCanPrint));
	}

	/** Get Print.
		@return Print	  */
	public boolean isCanPrint () 
	{
		Object oo = get_Value(COLUMNNAME_IsCanPrint);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Scan.
		@param IsCanScan Scan	  */
	public void setIsCanScan (boolean IsCanScan)
	{
		set_Value (COLUMNNAME_IsCanScan, Boolean.valueOf(IsCanScan));
	}

	/** Get Scan.
		@return Scan	  */
	public boolean isCanScan () 
	{
		Object oo = get_Value(COLUMNNAME_IsCanScan);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set CD ROM Available.
		@param IsCDROMAvailable CD ROM Available	  */
	public void setIsCDROMAvailable (boolean IsCDROMAvailable)
	{
		set_Value (COLUMNNAME_IsCDROMAvailable, Boolean.valueOf(IsCDROMAvailable));
	}

	/** Get CD ROM Available.
		@return CD ROM Available	  */
	public boolean isCDROMAvailable () 
	{
		Object oo = get_Value(COLUMNNAME_IsCDROMAvailable);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printer Type Color.
		@param IsColorPrinter Printer Type Color	  */
	public void setIsColorPrinter (boolean IsColorPrinter)
	{
		set_Value (COLUMNNAME_IsColorPrinter, Boolean.valueOf(IsColorPrinter));
	}

	/** Get Printer Type Color.
		@return Printer Type Color	  */
	public boolean isColorPrinter () 
	{
		Object oo = get_Value(COLUMNNAME_IsColorPrinter);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Description Only.
		@param IsDescription 
		if true, the line is just description and no transaction
	  */
	public void setIsDescription (boolean IsDescription)
	{
		set_Value (COLUMNNAME_IsDescription, Boolean.valueOf(IsDescription));
	}

	/** Get Description Only.
		@return if true, the line is just description and no transaction
	  */
	public boolean isDescription () 
	{
		Object oo = get_Value(COLUMNNAME_IsDescription);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Mobile/Internet Accessory.
		@param IsInternet Mobile/Internet Accessory	  */
	public void setIsInternet (boolean IsInternet)
	{
		set_Value (COLUMNNAME_IsInternet, Boolean.valueOf(IsInternet));
	}

	/** Get Mobile/Internet Accessory.
		@return Mobile/Internet Accessory	  */
	public boolean isInternet () 
	{
		Object oo = get_Value(COLUMNNAME_IsInternet);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Invoiced.
		@param IsInvoiced 
		Is this invoiced?
	  */
	public void setIsInvoiced (boolean IsInvoiced)
	{
		set_Value (COLUMNNAME_IsInvoiced, Boolean.valueOf(IsInvoiced));
	}

	/** Get Invoiced.
		@return Is this invoiced?
	  */
	public boolean isInvoiced () 
	{
		Object oo = get_Value(COLUMNNAME_IsInvoiced);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Laptop/Desktop/AIO.
		@param IsLaptopDektop Laptop/Desktop/AIO	  */
	public void setIsLaptopDektop (boolean IsLaptopDektop)
	{
		set_Value (COLUMNNAME_IsLaptopDektop, Boolean.valueOf(IsLaptopDektop));
	}

	/** Get Laptop/Desktop/AIO.
		@return Laptop/Desktop/AIO	  */
	public boolean isLaptopDektop () 
	{
		Object oo = get_Value(COLUMNNAME_IsLaptopDektop);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Network Component.
		@param IsNetComp Network Component	  */
	public void setIsNetComp (boolean IsNetComp)
	{
		set_Value (COLUMNNAME_IsNetComp, Boolean.valueOf(IsNetComp));
	}

	/** Get Network Component.
		@return Network Component	  */
	public boolean isNetComp () 
	{
		Object oo = get_Value(COLUMNNAME_IsNetComp);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printer.
		@param IsPrinter Printer	  */
	public void setIsPrinter (boolean IsPrinter)
	{
		set_Value (COLUMNNAME_IsPrinter, Boolean.valueOf(IsPrinter));
	}

	/** Get Printer.
		@return Printer	  */
	public boolean isPrinter () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinter);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Printer Accessory.
		@param IsPrinterAccessory Printer Accessory	  */
	public void setIsPrinterAccessory (boolean IsPrinterAccessory)
	{
		set_Value (COLUMNNAME_IsPrinterAccessory, Boolean.valueOf(IsPrinterAccessory));
	}

	/** Get Printer Accessory.
		@return Printer Accessory	  */
	public boolean isPrinterAccessory () 
	{
		Object oo = get_Value(COLUMNNAME_IsPrinterAccessory);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Device Serial No Required.
		@param IsSerialNoReq Device Serial No Required	  */
	public void setIsSerialNoReq (boolean IsSerialNoReq)
	{
		set_Value (COLUMNNAME_IsSerialNoReq, Boolean.valueOf(IsSerialNoReq));
	}

	/** Get Device Serial No Required.
		@return Device Serial No Required	  */
	public boolean isSerialNoReq () 
	{
		Object oo = get_Value(COLUMNNAME_IsSerialNoReq);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Is WIFI Support.
		@param IsWifiSupport Is WIFI Support	  */
	public void setIsWifiSupport (boolean IsWifiSupport)
	{
		set_Value (COLUMNNAME_IsWifiSupport, Boolean.valueOf(IsWifiSupport));
	}

	/** Get Is WIFI Support.
		@return Is WIFI Support	  */
	public boolean isWifiSupport () 
	{
		Object oo = get_Value(COLUMNNAME_IsWifiSupport);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Line No.
		@param Line 
		Unique line for this document
	  */
	public void setLine (int Line)
	{
		set_Value (COLUMNNAME_Line, Integer.valueOf(Line));
	}

	/** Get Line No.
		@return Unique line for this document
	  */
	public int getLine () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Line);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getLine()));
    }

	public I_M_AttributeSetInstance getM_AttributeSetInstance() throws RuntimeException
    {
		return (I_M_AttributeSetInstance)MTable.get(getCtx(), I_M_AttributeSetInstance.Table_Name)
			.getPO(getM_AttributeSetInstance_ID(), get_TrxName());	}

	/** Set Attribute Set Instance.
		@param M_AttributeSetInstance_ID 
		Product Attribute Set Instance
	  */
	public void setM_AttributeSetInstance_ID (int M_AttributeSetInstance_ID)
	{
		if (M_AttributeSetInstance_ID < 0) 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, null);
		else 
			set_Value (COLUMNNAME_M_AttributeSetInstance_ID, Integer.valueOf(M_AttributeSetInstance_ID));
	}

	/** Get Attribute Set Instance.
		@return Product Attribute Set Instance
	  */
	public int getM_AttributeSetInstance_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_AttributeSetInstance_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_InOut getM_InOut() throws RuntimeException
    {
		return (org.compiere.model.I_M_InOut)MTable.get(getCtx(), org.compiere.model.I_M_InOut.Table_Name)
			.getPO(getM_InOut_ID(), get_TrxName());	}

	/** Set Shipment/Receipt.
		@param M_InOut_ID 
		Material Shipment Document
	  */
	public void setM_InOut_ID (int M_InOut_ID)
	{
		if (M_InOut_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_InOut_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_InOut_ID, Integer.valueOf(M_InOut_ID));
	}

	/** Get Shipment/Receipt.
		@return Material Shipment Document
	  */
	public int getM_InOut_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOut_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Shipment/Receipt Line.
		@param M_InOutLine_ID 
		Line on Shipment or Receipt document
	  */
	public void setM_InOutLine_ID (int M_InOutLine_ID)
	{
		if (M_InOutLine_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_InOutLine_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_InOutLine_ID, Integer.valueOf(M_InOutLine_ID));
	}

	/** Get Shipment/Receipt Line.
		@return Line on Shipment or Receipt document
	  */
	public int getM_InOutLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_InOutLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public I_M_Locator getM_Locator() throws RuntimeException
    {
		return (I_M_Locator)MTable.get(getCtx(), I_M_Locator.Table_Name)
			.getPO(getM_Locator_ID(), get_TrxName());	}

	/** Set Locator.
		@param M_Locator_ID 
		Warehouse Locator
	  */
	public void setM_Locator_ID (int M_Locator_ID)
	{
		if (M_Locator_ID < 1) 
			set_Value (COLUMNNAME_M_Locator_ID, null);
		else 
			set_Value (COLUMNNAME_M_Locator_ID, Integer.valueOf(M_Locator_ID));
	}

	/** Get Locator.
		@return Warehouse Locator
	  */
	public int getM_Locator_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Locator_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_Product_Category getM_Product_Category() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Category)MTable.get(getCtx(), org.compiere.model.I_M_Product_Category.Table_Name)
			.getPO(getM_Product_Category_ID(), get_TrxName());	}

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
	}

	/** Get Product Category.
		@return Category of a Product
	  */
	public int getM_Product_Category_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_ID);
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

	public org.compiere.model.I_M_RMALine getM_RMALine() throws RuntimeException
    {
		return (org.compiere.model.I_M_RMALine)MTable.get(getCtx(), org.compiere.model.I_M_RMALine.Table_Name)
			.getPO(getM_RMALine_ID(), get_TrxName());	}

	/** Set RMA Line.
		@param M_RMALine_ID 
		Return Material Authorization Line
	  */
	public void setM_RMALine_ID (int M_RMALine_ID)
	{
		if (M_RMALine_ID < 1) 
			set_Value (COLUMNNAME_M_RMALine_ID, null);
		else 
			set_Value (COLUMNNAME_M_RMALine_ID, Integer.valueOf(M_RMALine_ID));
	}

	/** Get RMA Line.
		@return Return Material Authorization Line
	  */
	public int getM_RMALine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_RMALine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Movement Quantity.
		@param MovementQty 
		Quantity of a product moved.
	  */
	public void setMovementQty (BigDecimal MovementQty)
	{
		set_Value (COLUMNNAME_MovementQty, MovementQty);
	}

	/** Get Movement Quantity.
		@return Quantity of a product moved.
	  */
	public BigDecimal getMovementQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_MovementQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
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

	/** Set Picked Quantity.
		@param PickedQty Picked Quantity	  */
	public void setPickedQty (BigDecimal PickedQty)
	{
		set_Value (COLUMNNAME_PickedQty, PickedQty);
	}

	/** Get Picked Quantity.
		@return Picked Quantity	  */
	public BigDecimal getPickedQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PickedQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Processed.
		@param Processed 
		The document has been processed
	  */
	public void setProcessed (boolean Processed)
	{
		set_Value (COLUMNNAME_Processed, Boolean.valueOf(Processed));
	}

	/** Get Processed.
		@return The document has been processed
	  */
	public boolean isProcessed () 
	{
		Object oo = get_Value(COLUMNNAME_Processed);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Processor.
		@param Processor Processor	  */
	public void setProcessor (String Processor)
	{
		set_Value (COLUMNNAME_Processor, Processor);
	}

	/** Get Processor.
		@return Processor	  */
	public String getProcessor () 
	{
		return (String)get_Value(COLUMNNAME_Processor);
	}

	/** PurchaseType AD_Reference_ID=1000003 */
	public static final int PURCHASETYPE_AD_Reference_ID=1000003;
	/** New Purchase = N */
	public static final String PURCHASETYPE_NewPurchase = "N";
	/** HPFS = H */
	public static final String PURCHASETYPE_HPFS = "H";
	/** Set Purchase Type.
		@param PurchaseType Purchase Type	  */
	public void setPurchaseType (String PurchaseType)
	{

		set_Value (COLUMNNAME_PurchaseType, PurchaseType);
	}

	/** Get Purchase Type.
		@return Purchase Type	  */
	public String getPurchaseType () 
	{
		return (String)get_Value(COLUMNNAME_PurchaseType);
	}

	/** Set Quantity.
		@param QtyEntered 
		The Quantity Entered is based on the selected UoM
	  */
	public void setQtyEntered (BigDecimal QtyEntered)
	{
		set_Value (COLUMNNAME_QtyEntered, QtyEntered);
	}

	/** Get Quantity.
		@return The Quantity Entered is based on the selected UoM
	  */
	public BigDecimal getQtyEntered () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_QtyEntered);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set RAM (In GB).
		@param RAM RAM (In GB)	  */
	public void setRAM (int RAM)
	{
		set_Value (COLUMNNAME_RAM, Integer.valueOf(RAM));
	}

	/** Get RAM (In GB).
		@return RAM (In GB)	  */
	public int getRAM () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_RAM);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Referenced Shipment Line.
		@param Ref_InOutLine_ID Referenced Shipment Line	  */
	public void setRef_InOutLine_ID (int Ref_InOutLine_ID)
	{
		if (Ref_InOutLine_ID < 1) 
			set_Value (COLUMNNAME_Ref_InOutLine_ID, null);
		else 
			set_Value (COLUMNNAME_Ref_InOutLine_ID, Integer.valueOf(Ref_InOutLine_ID));
	}

	/** Get Referenced Shipment Line.
		@return Referenced Shipment Line	  */
	public int getRef_InOutLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_Ref_InOutLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_M_InOutLine getReversalLine() throws RuntimeException
    {
		return (org.compiere.model.I_M_InOutLine)MTable.get(getCtx(), org.compiere.model.I_M_InOutLine.Table_Name)
			.getPO(getReversalLine_ID(), get_TrxName());	}

	/** Set Reversal Line.
		@param ReversalLine_ID 
		Use to keep the reversal line ID for reversing costing purpose
	  */
	public void setReversalLine_ID (int ReversalLine_ID)
	{
		if (ReversalLine_ID < 1) 
			set_Value (COLUMNNAME_ReversalLine_ID, null);
		else 
			set_Value (COLUMNNAME_ReversalLine_ID, Integer.valueOf(ReversalLine_ID));
	}

	/** Get Reversal Line.
		@return Use to keep the reversal line ID for reversing costing purpose
	  */
	public int getReversalLine_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_ReversalLine_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Scrapped Quantity.
		@param ScrappedQty 
		The Quantity scrapped due to QA issues
	  */
	public void setScrappedQty (BigDecimal ScrappedQty)
	{
		set_Value (COLUMNNAME_ScrappedQty, ScrappedQty);
	}

	/** Get Scrapped Quantity.
		@return The Quantity scrapped due to QA issues
	  */
	public BigDecimal getScrappedQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScrappedQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Screen Size (In Inch).
		@param ScreenSize Screen Size (In Inch)	  */
	public void setScreenSize (BigDecimal ScreenSize)
	{
		set_Value (COLUMNNAME_ScreenSize, ScreenSize);
	}

	/** Get Screen Size (In Inch).
		@return Screen Size (In Inch)	  */
	public BigDecimal getScreenSize () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_ScreenSize);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Target Quantity.
		@param TargetQty 
		Target Movement Quantity
	  */
	public void setTargetQty (BigDecimal TargetQty)
	{
		set_Value (COLUMNNAME_TargetQty, TargetQty);
	}

	/** Get Target Quantity.
		@return Target Movement Quantity
	  */
	public BigDecimal getTargetQty () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_TargetQty);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set To be Invoiced.
		@param ToBeInvoiced 
		If Shipment line is marked as ToBeInvoiced='N' then it is included in the generated Invoice line with price 0.0
	  */
	public void setToBeInvoiced (boolean ToBeInvoiced)
	{
		set_Value (COLUMNNAME_ToBeInvoiced, Boolean.valueOf(ToBeInvoiced));
	}

	/** Get To be Invoiced.
		@return If Shipment line is marked as ToBeInvoiced='N' then it is included in the generated Invoice line with price 0.0
	  */
	public boolean isToBeInvoiced () 
	{
		Object oo = get_Value(COLUMNNAME_ToBeInvoiced);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public org.compiere.model.I_C_ElementValue getUser1() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser1_ID(), get_TrxName());	}

	/** Set User List 1.
		@param User1_ID 
		User defined list element #1
	  */
	public void setUser1_ID (int User1_ID)
	{
		if (User1_ID < 1) 
			set_Value (COLUMNNAME_User1_ID, null);
		else 
			set_Value (COLUMNNAME_User1_ID, Integer.valueOf(User1_ID));
	}

	/** Get User List 1.
		@return User defined list element #1
	  */
	public int getUser1_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User1_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_C_ElementValue getUser2() throws RuntimeException
    {
		return (org.compiere.model.I_C_ElementValue)MTable.get(getCtx(), org.compiere.model.I_C_ElementValue.Table_Name)
			.getPO(getUser2_ID(), get_TrxName());	}

	/** Set User List 2.
		@param User2_ID 
		User defined list element #2
	  */
	public void setUser2_ID (int User2_ID)
	{
		if (User2_ID < 1) 
			set_Value (COLUMNNAME_User2_ID, null);
		else 
			set_Value (COLUMNNAME_User2_ID, Integer.valueOf(User2_ID));
	}

	/** Get User List 2.
		@return User defined list element #2
	  */
	public int getUser2_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_User2_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}