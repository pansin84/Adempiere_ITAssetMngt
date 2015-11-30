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

/** Generated Model for M_Product_Category
 *  @author Adempiere (generated) 
 *  @version Release 3.8.0 - $Id$ */
public class X_M_Product_Category extends PO implements I_M_Product_Category, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20150828L;

    /** Standard Constructor */
    public X_M_Product_Category (Properties ctx, int M_Product_Category_ID, String trxName)
    {
      super (ctx, M_Product_Category_ID, trxName);
      /** if (M_Product_Category_ID == 0)
        {
			setIsDefault (false);
			setIsSelfService (true);
// Y
			setM_Product_Category_ID (0);
			setMMPolicy (null);
// F
			setName (null);
			setPlannedMargin (Env.ZERO);
			setValue (null);
        } */
    }

    /** Load Constructor */
    public X_M_Product_Category (Properties ctx, ResultSet rs, String trxName)
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
      StringBuffer sb = new StringBuffer ("X_M_Product_Category[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_A_Asset_Group getA_Asset_Group() throws RuntimeException
    {
		return (org.compiere.model.I_A_Asset_Group)MTable.get(getCtx(), org.compiere.model.I_A_Asset_Group.Table_Name)
			.getPO(getA_Asset_Group_ID(), get_TrxName());	}

	/** Set Asset Group.
		@param A_Asset_Group_ID 
		Group of Assets
	  */
	public void setA_Asset_Group_ID (int A_Asset_Group_ID)
	{
		if (A_Asset_Group_ID < 1) 
			set_Value (COLUMNNAME_A_Asset_Group_ID, null);
		else 
			set_Value (COLUMNNAME_A_Asset_Group_ID, Integer.valueOf(A_Asset_Group_ID));
	}

	/** Get Asset Group.
		@return Group of Assets
	  */
	public int getA_Asset_Group_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_A_Asset_Group_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_PrintColor getAD_PrintColor() throws RuntimeException
    {
		return (org.compiere.model.I_AD_PrintColor)MTable.get(getCtx(), org.compiere.model.I_AD_PrintColor.Table_Name)
			.getPO(getAD_PrintColor_ID(), get_TrxName());	}

	/** Set Print Color.
		@param AD_PrintColor_ID 
		Color used for printing and display
	  */
	public void setAD_PrintColor_ID (int AD_PrintColor_ID)
	{
		if (AD_PrintColor_ID < 1) 
			set_Value (COLUMNNAME_AD_PrintColor_ID, null);
		else 
			set_Value (COLUMNNAME_AD_PrintColor_ID, Integer.valueOf(AD_PrintColor_ID));
	}

	/** Get Print Color.
		@return Color used for printing and display
	  */
	public int getAD_PrintColor_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_PrintColor_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Consumable (Once used cann't return back).
		@param IsConsumable Consumable (Once used cann't return back)	  */
	public void setIsConsumable (boolean IsConsumable)
	{
		set_Value (COLUMNNAME_IsConsumable, Boolean.valueOf(IsConsumable));
	}

	/** Get Consumable (Once used cann't return back).
		@return Consumable (Once used cann't return back)	  */
	public boolean isConsumable () 
	{
		Object oo = get_Value(COLUMNNAME_IsConsumable);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

	/** Set Laptop/Desktop Accessory.
		@param IsLDAccessory Laptop/Desktop Accessory	  */
	public void setIsLDAccessory (boolean IsLDAccessory)
	{
		set_Value (COLUMNNAME_IsLDAccessory, Boolean.valueOf(IsLDAccessory));
	}

	/** Get Laptop/Desktop Accessory.
		@return Laptop/Desktop Accessory	  */
	public boolean isLDAccessory () 
	{
		Object oo = get_Value(COLUMNNAME_IsLDAccessory);
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

	/** Set Self-Service.
		@param IsSelfService 
		This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public void setIsSelfService (boolean IsSelfService)
	{
		set_Value (COLUMNNAME_IsSelfService, Boolean.valueOf(IsSelfService));
	}

	/** Get Self-Service.
		@return This is a Self-Service entry or this entry can be changed via Self-Service
	  */
	public boolean isSelfService () 
	{
		Object oo = get_Value(COLUMNNAME_IsSelfService);
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

	/** Set Product Category.
		@param M_Product_Category_ID 
		Category of a Product
	  */
	public void setM_Product_Category_ID (int M_Product_Category_ID)
	{
		if (M_Product_Category_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_M_Product_Category_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_M_Product_Category_ID, Integer.valueOf(M_Product_Category_ID));
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

	public org.compiere.model.I_M_Product_Category getM_Product_Category_Parent() throws RuntimeException
    {
		return (org.compiere.model.I_M_Product_Category)MTable.get(getCtx(), org.compiere.model.I_M_Product_Category.Table_Name)
			.getPO(getM_Product_Category_Parent_ID(), get_TrxName());	}

	/** Set Parent Product Category.
		@param M_Product_Category_Parent_ID Parent Product Category	  */
	public void setM_Product_Category_Parent_ID (int M_Product_Category_Parent_ID)
	{
		if (M_Product_Category_Parent_ID < 1) 
			set_Value (COLUMNNAME_M_Product_Category_Parent_ID, null);
		else 
			set_Value (COLUMNNAME_M_Product_Category_Parent_ID, Integer.valueOf(M_Product_Category_Parent_ID));
	}

	/** Get Parent Product Category.
		@return Parent Product Category	  */
	public int getM_Product_Category_Parent_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_M_Product_Category_Parent_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** MMPolicy AD_Reference_ID=335 */
	public static final int MMPOLICY_AD_Reference_ID=335;
	/** LiFo = L */
	public static final String MMPOLICY_LiFo = "L";
	/** FiFo = F */
	public static final String MMPOLICY_FiFo = "F";
	/** Set Material Policy.
		@param MMPolicy 
		Material Movement Policy
	  */
	public void setMMPolicy (String MMPolicy)
	{

		set_Value (COLUMNNAME_MMPolicy, MMPolicy);
	}

	/** Get Material Policy.
		@return Material Movement Policy
	  */
	public String getMMPolicy () 
	{
		return (String)get_Value(COLUMNNAME_MMPolicy);
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

	/** Set Planned Margin %.
		@param PlannedMargin 
		Project's planned margin as a percentage
	  */
	public void setPlannedMargin (BigDecimal PlannedMargin)
	{
		set_Value (COLUMNNAME_PlannedMargin, PlannedMargin);
	}

	/** Get Planned Margin %.
		@return Project's planned margin as a percentage
	  */
	public BigDecimal getPlannedMargin () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_PlannedMargin);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Search Key.
		@param Value 
		Search key for the record in the format required - must be unique
	  */
	public void setValue (String Value)
	{
		set_Value (COLUMNNAME_Value, Value);
	}

	/** Get Search Key.
		@return Search key for the record in the format required - must be unique
	  */
	public String getValue () 
	{
		return (String)get_Value(COLUMNNAME_Value);
	}
}