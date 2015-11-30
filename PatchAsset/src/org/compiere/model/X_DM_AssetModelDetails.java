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

/** Generated Model for DM_AssetModelDetails
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_DM_AssetModelDetails extends PO implements I_DM_AssetModelDetails, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150831L;

    /** Standard Constructor */
    public X_DM_AssetModelDetails (Properties ctx, int DM_AssetModelDetails_ID, String trxName)
    {
      super (ctx, DM_AssetModelDetails_ID, trxName);
      /** if (DM_AssetModelDetails_ID == 0)
        {
			setDM_AssetModelDetails_ID (0);
			setDM_AssetModelNo_ID (0);
        } */
    }

    /** Load Constructor */
    public X_DM_AssetModelDetails (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_DM_AssetModelDetails[")
        .append(get_ID()).append("]");
      return sb.toString();
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
			set_Value (COLUMNNAME_C_UOM_ID, null);
		else 
			set_Value (COLUMNNAME_C_UOM_ID, Integer.valueOf(C_UOM_ID));
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

	/** Set Model No Details ID.
		@param DM_AssetModelDetails_ID Model No Details ID	  */
	public void setDM_AssetModelDetails_ID (int DM_AssetModelDetails_ID)
	{
		if (DM_AssetModelDetails_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_DM_AssetModelDetails_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_DM_AssetModelDetails_ID, Integer.valueOf(DM_AssetModelDetails_ID));
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
}