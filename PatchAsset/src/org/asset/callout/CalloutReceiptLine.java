package org.asset.callout;

import java.util.Properties;

import org.compiere.model.CalloutEngine;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MAssetModelDetails;
import org.compiere.model.MLocator;
import org.compiere.model.MWarehouse;
import org.compiere.util.Env;

public class CalloutReceiptLine extends CalloutEngine {

	public CalloutReceiptLine() {
	}
	public String assetType (Properties ctx, int WindowNo, GridTab mTab, GridField mField, Object value, Object oldValue)
	{
		if (isCalloutActive())
			return "";
		
		Integer DM_AssetModelDetails_ID = (Integer)value;
		if (DM_AssetModelDetails_ID == null || DM_AssetModelDetails_ID <= 0)
		{
			mTab.setValue("IsPrinter",false);
			mTab.setValue("IsLaptopDektop",false);
			mTab.setValue("IsNetComp",false);
			mTab.setValue("IsAccessories",false);
			mTab.setValue("IsPrinterAccessory",false);
			mTab.setValue("M_Product_ID",-1);
			mTab.setValue("IsInternet",false);
			mTab.setValue("MobilePhone", null);
			mTab.setValue("ModelNo",null);
			mTab.setValue("C_UOM_ID",-1);
			mTab.setValue("RAM",0);
			mTab.setValue("Processor",null);
			mTab.setValue("HDD",0);
			mTab.setValue("GraphicsCard",0);
			mTab.setValue("IsCDROMAvailable",false);
			mTab.setValue("IsCanCopy",false);
			mTab.setValue("IsCanPrint",false);
			mTab.setValue("IsCanScan",false);
			mTab.setValue("IsColorPrinter",false);
			mTab.setValue("IsWifiSupport",false);
			mTab.setValue("InputTrayCapacity",0);
			mTab.setValue("M_Locator_ID",-1);
			return "";
		}
		
		
		MAssetModelDetails cat = new MAssetModelDetails(ctx, DM_AssetModelDetails_ID, null);
		
		if(cat.isPrinter()){
			mTab.setValue("IsPrinter",true);
			mTab.setValue("IsLaptopDektop",false);
			mTab.setValue("IsNetComp",false);
			mTab.setValue("IsAccessories",false);
			mTab.setValue("IsPrinterAccessory",false);
			mTab.setValue("IsInternet",false);
			mTab.setValue("RAM",0);
			mTab.setValue("Processor",null);
			mTab.setValue("HDD",0);
			mTab.setValue("GraphicsCard",0);
			mTab.setValue("IsCDROMAvailable",false);
			mTab.setValue("ScreenSize",Env.ZERO);
			mTab.setValue("MobilePhone",null);
		}
		if(cat.isLaptopDektop()){
			mTab.setValue("IsPrinter",false);
			mTab.setValue("IsLaptopDektop",true);
			mTab.setValue("IsNetComp",false);
			mTab.setValue("IsAccessories",false);
			mTab.setValue("IsPrinterAccessory",false);
			mTab.setValue("IsInternet",false);
			mTab.setValue("IsCanCopy",false);
			mTab.setValue("IsCanPrint",false);
			mTab.setValue("IsCanScan",false);
			mTab.setValue("IsColorPrinter",false);
			mTab.setValue("IsWifiSupport",false);
			mTab.setValue("InputTrayCapacity",0);
			mTab.setValue("MobilePhone",null);
		}
		if(cat.isNetComp()){
			mTab.setValue("IsPrinter",false);
			mTab.setValue("IsLaptopDektop",false);
			mTab.setValue("IsNetComp",true);
			mTab.setValue("IsAccessories",false);
			mTab.setValue("IsPrinterAccessory",false);
			mTab.setValue("IsInternet",false);
			mTab.setValue("IsCanCopy",false);
			mTab.setValue("IsCanPrint",false);
			mTab.setValue("IsCanScan",false);
			mTab.setValue("IsColorPrinter",false);
			mTab.setValue("IsWifiSupport",false);
			mTab.setValue("InputTrayCapacity",0);
			mTab.setValue("RAM",0);
			mTab.setValue("Processor",null);
			mTab.setValue("HDD",0);
			mTab.setValue("GraphicsCard",0);
			mTab.setValue("IsCDROMAvailable",false);
			mTab.setValue("ScreenSize",Env.ZERO);
			mTab.setValue("MobilePhone",null);
		}
		if(cat.isAccessories()){
			mTab.setValue("IsPrinter",false);
			mTab.setValue("IsLaptopDektop",false);
			mTab.setValue("IsNetComp",false);
			mTab.setValue("IsAccessories",true);
			mTab.setValue("IsPrinterAccessory",false);
			mTab.setValue("IsCanCopy",false);
			mTab.setValue("IsCanPrint",false);
			mTab.setValue("IsCanScan",false);
			mTab.setValue("IsColorPrinter",false);
			mTab.setValue("IsWifiSupport",false);
			mTab.setValue("InputTrayCapacity",0);
			mTab.setValue("RAM",0);
			mTab.setValue("Processor",null);
			mTab.setValue("HDD",0);
			mTab.setValue("GraphicsCard",0);
			mTab.setValue("IsCDROMAvailable",false);
			mTab.setValue("IsInternet",false);
			mTab.setValue("ScreenSize",Env.ZERO);
			mTab.setValue("MobilePhone",null);
		}
		if(cat.isPrinterAccessory() ){
			mTab.setValue("IsPrinter",false);
			mTab.setValue("IsLaptopDektop",false);
			mTab.setValue("IsNetComp",false);
			mTab.setValue("IsAccessories",false);
			mTab.setValue("IsPrinterAccessory",true);
			mTab.setValue("IsCanCopy",false);
			mTab.setValue("IsCanPrint",false);
			mTab.setValue("IsCanScan",false);
			mTab.setValue("IsColorPrinter",false);
			mTab.setValue("IsWifiSupport",false);
			mTab.setValue("InputTrayCapacity",0);
			mTab.setValue("RAM",0);
			mTab.setValue("Processor",null);
			mTab.setValue("HDD",0);
			mTab.setValue("GraphicsCard",0);
			mTab.setValue("IsCDROMAvailable",false);
			mTab.setValue("IsInternet",false);
			mTab.setValue("ScreenSize",Env.ZERO);
			mTab.setValue("MobilePhone",null);
		}
		if(cat.isInternet() ){
			mTab.setValue("IsPrinter",false);
			mTab.setValue("IsLaptopDektop",false);
			mTab.setValue("IsNetComp",false);
			mTab.setValue("IsAccessories",false);
			mTab.setValue("IsPrinterAccessory",false);
			mTab.setValue("IsCanCopy",false);
			mTab.setValue("IsCanPrint",false);
			mTab.setValue("IsCanScan",false);
			mTab.setValue("IsColorPrinter",false);
			mTab.setValue("IsWifiSupport",false);
			mTab.setValue("InputTrayCapacity",0);
			mTab.setValue("RAM",0);
			mTab.setValue("Processor",null);
			mTab.setValue("HDD",0);
			mTab.setValue("GraphicsCard",0);
			mTab.setValue("IsCDROMAvailable",false);
			mTab.setValue("IsInternet",true);
			mTab.setValue("ScreenSize",Env.ZERO);
		}
		if(cat.getM_Product()!=null){
			mTab.setValue("M_Product_ID",cat.getM_Product_ID());
			mTab.setValue("ModelNo",cat.getModelNo());
			mTab.setValue("C_UOM_ID",cat.getC_UOM_ID());
		}
		if(cat.getRAM()>0){
			mTab.setValue("RAM",cat.getRAM());
		}
		if(cat.getScreenSize().compareTo(Env.ZERO)>0){
			mTab.setValue("ScreenSize",cat.getScreenSize());
		}
		if(cat.getProcessor()!=null){
			mTab.setValue("Processor",cat.getProcessor());
		}
		if(cat.getHDD()>0){
			mTab.setValue("HDD",cat.getHDD());
		}
		
		if(cat.getGraphicsCard()>0){
			mTab.setValue("GraphicsCard",cat.getGraphicsCard());
		}
		mTab.setValue("IsSerialNoReq",cat.getM_Product_Category().isSerialNoReq());
		if(!cat.getM_Product_Category().isSerialNoReq()){
			mTab.setValue("DeviceSerialNo",cat.getModelNo());
		}
		mTab.setValue("IsCDROMAvailable",cat.isCDROMAvailable());
		mTab.setValue("IsCanCopy",cat.isCanCopy());
		mTab.setValue("IsCanPrint",cat.isCanPrint());
		mTab.setValue("IsCanScan",cat.isCanScan());
		mTab.setValue("IsColorPrinter",cat.isColorPrinter());
		mTab.setValue("IsWifiSupport",cat.isWifiSupport());
		if(cat.getInputTrayCapacity()>0){
			mTab.setValue("InputTrayCapacity",cat.getInputTrayCapacity());
		}
		int M_Warehouse_ID = Env.getContextAsInt(ctx, WindowNo, "M_Warehouse_ID");
		MWarehouse wh = new MWarehouse(ctx, M_Warehouse_ID, null);
		if(MLocator.getDefault(wh)!=null){
			mTab.setValue("M_Locator_ID",MLocator.getDefault(wh).get_ID());
		}
		
		return "";
	}
}
